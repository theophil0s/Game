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
import milkyway.earth.game.main.GameLevel;
import milkyway.earth.game.main.GameOverlay;
import milkyway.earth.game.main.GameResources;
import milkyway.earth.game.network.GameClient;
import milkyway.earth.game.utils.GameObjects;

public class StatePlay extends BasicGameState {

	private String playerName;
	public static GameClient gameClient;

	public static GameLevel level;
	public static GameObjects objects;
	public static GameOverlay overlay;
	public static GameInput input;

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

		new GameResources();

		input = new GameInput(gc, gc.getHeight());

		level = new GameLevel();
		objects = new GameObjects();
		overlay = new GameOverlay();

		level.init(gc);
		objects.init(gc);
		overlay.init(gc);

		playerName = (String) JOptionPane.showInputDialog(null, "Name:", "Connect to server",
				JOptionPane.QUESTION_MESSAGE, null, null,
				"Player_" + new Random(System.currentTimeMillis()).nextInt(999999));

		String host = (String) JOptionPane.showInputDialog(null, "Host:", "Connect to server",
				JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

		gameClient = new GameClient(playerName, host, 13001, null, objects);

		try {
			gameClient.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {

		input.update();
		level.update(gc, delta);
		objects.update(gc, delta);
		overlay.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

		level.render(gc, g);
		objects.render(gc, g);
		overlay.render(gc, g);
	}

	@Override
	public int getID() {
		return 0;
	}
}
