package milkyway.earth.game.states;

import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.input.GameInput;
import milkyway.earth.game.main.Game;
import milkyway.earth.game.main.GameOverlay;
import milkyway.earth.game.network.GameClient;
import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.game.world.GameCam;
import milkyway.earth.game.world.GameLevel;
import milkyway.earth.object.GameResources;
import milkyway.earth.object.Player;

public class StatePlay extends BasicGameState {

	private boolean connected;

	private String playerName;
	public static GameClient gameClient;

	private GameObjects objects;
	private GameLevel level;
	private GameOverlay overlay;
	private GameInput input;
	private GameCam camera;
	private Player player;

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

		new GameResources();
		new GameCam();
		objects = GameObjects.getGo();
		objects.init(gc, game);

		level = new GameLevel();
		overlay = new GameOverlay();

		level.init(gc);
		overlay.init(gc);

		if (!connected) {
			playerName = (String) JOptionPane.showInputDialog(null, "Name:", "Connect to server",
					JOptionPane.QUESTION_MESSAGE, null, null,
					"Player_" + new Random(System.currentTimeMillis()).nextInt(999999));

			String host = (String) JOptionPane.showInputDialog(null, "Host:", "Connect to server",
					JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

			gameClient = new GameClient(playerName, host, 13001, null, objects);

			try {
				gameClient.start();
				connected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {

		objects.update(gc, game, delta, player);

		if (player == null) {
			for (long l : GameObjects.getObjectList().keySet()) {
				if (GameObjects.getPlayerId() == GameObjects.getObjectList().get(l).getId()) {
					player = (Player) GameObjects.getObjectList().get(l);
					input = new GameInput(gc, gc.getHeight(), player);
				}
			}
		} else {

			input.update(delta);
			level.update(gc, delta, player);
			overlay.update(gc, delta);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
		// ambient.put(new float[] { 0.05f, 0.05f, .5f, 1f, });
		// ambient.flip();
		//
		// FloatBuffer position = BufferUtils.createFloatBuffer(4);
		// position.put(new float[] { 1f, 0f, 1f, 1f, });
		// position.flip();
		//
		// GL11.glEnable(GL11.GL_LIGHTING);
		// GL11.glEnable(GL11.GL_LIGHT0);
		// GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, ambient);
		// GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
		// GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT |GL11.GL_DEPTH_BUFFER_BIT); //
		// Clean the screen and the depth buffer
		// GL11.glLoadIdentity(); // Reset The Projection Matrix
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

		GameObjects.renderLayer01(gc, game, g, Game.getScale(), player);
		GameObjects.renderLayer02Before(gc, game, g, Game.getScale(), player);

		if (player != null)
			player.render(gc, game, g, Game.getScale());

		GameObjects.renderLayer02After(gc, game, g, Game.getScale(), player);
		GameObjects.renderLayer03(gc, game, g, Game.getScale(), player);
		
		GameCam.update(gc, Game.getScale(), player);
		overlay.render(gc, g, camera);

	}

	public GameInput getInput() {
		return input;
	}

	public GameCam getCamera() {
		return camera;
	}

}
