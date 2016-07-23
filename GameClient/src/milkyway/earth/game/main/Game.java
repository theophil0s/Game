package milkyway.earth.game.main;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import milkyway.earth.game.input.GameInput;
import milkyway.earth.game.network.GameClient;
import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.object.GameObject;

public class Game extends BasicGame {

	private static final String NAME = "Game";
	private static final int FRAMERATE_MAX = 0;
	private static final int UPDATERATE_MAX = 100;
	private static final int UPDATERATE_MIN = 100;
	private static final Boolean SHOW_FPS = true;
	private static final Boolean FULLSCREEN = false;
	private static final Boolean FULLSCREEN_WINDOW = false;
	private static final Boolean VSYNC = false;
	private static final Boolean ALWAYS_RENDER = true;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	private static Game game;
	private static float scale;
	private static Dimension size;
	private static Dimension resolution;
	private static AppGameContainer container;

	public static GameLevel level;
	public static GameObjects objects;
	public static GameOverlay overlay;
	public static GameInput input;

	private String playerName;
	private GameClient gameClient;

	public Game(String title) throws SlickException {
		super(title);
	}

	public static void main(String[] args) throws SlickException {

		game = new Game(NAME);
		start(game);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {

		new GameResources();

		setScale(1);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {

		level.update(gc, delta);
		objects.update(gc, delta);
		overlay.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		level.render(gc, g);
		objects.render(gc, g);
		overlay.render(gc, g);
	}

	public static void start(Game game) throws SlickException {

		resolution = new Dimension(WIDTH, HEIGHT);
		container = new AppGameContainer(game);

		if (FULLSCREEN) {
			size = new Dimension(container.getScreenWidth(), container.getScreenHeight());
		} else if (FULLSCREEN_WINDOW) {
			size = new Dimension(container.getScreenWidth(), container.getScreenHeight());
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		} else {
			size = resolution;
		}

		container.setDisplayMode(size.width, size.height, false);
		container.setShowFPS(SHOW_FPS);
		container.setFullscreen(FULLSCREEN);
		container.setTargetFrameRate(FRAMERATE_MAX - 1);
		container.setVSync(VSYNC);
		container.setAlwaysRender(ALWAYS_RENDER);
		container.setMinimumLogicUpdateInterval(1000 / UPDATERATE_MIN);
		container.setMaximumLogicUpdateInterval(1000 / UPDATERATE_MAX);
		container.start();
	}

	public static void stop() throws SlickException {

		container.exit();
		container.destroy();
	}

	public static void pause() throws SlickException {

		container.pause();
	}

	public static void resume() throws SlickException {

		container.resume();
	}

	public static AppGameContainer getContainer() {
		return container;
	}

	public static float getScale() {
		return scale;
	}

	public static void setScale(float scale) {
		Game.scale = scale;
	}

	@Override
	public void mouseWheelMoved(int change) {
		System.out.println(change + " " + scale);
		if (change > 0) {
			setScale(getScale() + 0.05F);
		} else {
			setScale(getScale() - 0.05F);
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if (GameObjects.playerId != -1) {
			int x = 0;
			int y = 0;
			switch (key) {
			case 203:
				// <
				x -= 10;
				break;
			case 200:
				// ^
				y -= 10;
				break;
			case 205:
				// >
				x += 10;
				break;
			case 208:
				// v
				y += 10;
				break;
			}
			for (GameObject go : GameObjects.objects) {
				if (GameObjects.playerId == go.getId()) {
					go.setPostition(go.getPosition().getX() + x, go.getPosition().getY() + y);
					if (gameClient.isRunning()) {
						gameClient.moveGameObject(go);
					}
					break;
				}
			}
		}
	}
}
