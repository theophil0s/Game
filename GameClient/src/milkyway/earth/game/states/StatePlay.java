package milkyway.earth.game.states;

import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

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
		objects = GameObjects.getGo();
		objects.init(gc, game);
		
		level = new GameLevel();
		overlay = new GameOverlay();
		camera = new GameCam();

		level.init(gc);
		overlay.init(gc);

		playerName = (String) JOptionPane.showInputDialog(null, "Name:", "Connect to server",
				JOptionPane.QUESTION_MESSAGE, null, null,
				"Player_" + new Random(System.currentTimeMillis()).nextInt(999999));

		String host = (String) JOptionPane.showInputDialog(null, "Host:", "Connect to server",
				JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

		gameClient = new GameClient(playerName, host, 13001, null, GameObjects.getGo());


		try {
			gameClient.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		objects.update(gc, game, delta, player);

		if (player == null) {
			for (long l : GameObjects.getObjectList().keySet()) {
				if (GameObjects.getPlayerId() == GameObjects.getObjectList().get(l).getId()) {
					player = (Player) GameObjects.getObjectList().get(l);
					player.setRenderType(Player.RENDER_TYPE_STATIC);
					input = new GameInput(gc, gc.getHeight(), player);
				}
			}
		} else {

			input.update(delta);
			player.update(gc, game, delta);
			
		}
		level.update(gc, delta, player, camera);
		overlay.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		g.translate(-(camera.offX), -(camera.offY));
		GameObjects.getGo().renderLayer01(gc, game, g, Game.getScale(), player);
		GameObjects.getGo().renderLayer02Before(gc, game, g, Game.getScale(), player);
		
		if (player != null && player.getRenderType() != Player.RENDER_TYPE_STATIC) {
			player.render(gc, game, g, Game.getScale());
		}
		g.translate((camera.offX), (camera.offY));
		

		if (player != null && player.getRenderType() == Player.RENDER_TYPE_STATIC) {
			player.render(gc, game, g, Game.getScale());
		}
		
		
		g.translate(-(camera.offX), -(camera.offY));
		GameObjects.getGo().renderLayer02After(gc, game, g, Game.getScale(), player);
		GameObjects.getGo().renderLayer03(gc, game, g, Game.getScale(), player);
		g.translate((camera.offX), (camera.offY));
		
		overlay.render(gc, g, camera);
		camera.update(gc, Game.getScale(), player);
		
	}
	

	public GameInput getInput() {
		return input;
	}
	
	public GameCam getCamera() {
		return camera;
	}

}
