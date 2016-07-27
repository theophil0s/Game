package milkyway.earth.game.main;

import java.awt.Dimension;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.states.StateMap;
import milkyway.earth.game.states.StateMenu;
import milkyway.earth.game.states.StatePlay;

public class Game extends StateBasedGame {

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
	private static double scale;
	private static Dimension size;
	private static Dimension resolution;
	private static AppGameContainer container;

	public Game(String title) throws SlickException {
		super(title);
		setScale(1);
	}

	public static void main(String[] args) throws SlickException {

		game = new Game(NAME);
		start(game);
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

	public static AppGameContainer getMainContainer() {
		return container;
	}

	public static double getScale() {
		return scale;
	}

	public static void setScale(double scale) {
		Game.scale = scale;
	}

	@Override
	public void mouseWheelMoved(int change) {

		System.out.println("SCALE: " + scale);

		// TODO GameLevel offX offY

		if (scale >= 0 && scale <= 2) {
			enterState(0);
			if (change > 0) {
				setScale(getScale() + 0.05D);
			} else {
				setScale(getScale() - 0.05D);
			}
		} else if (scale < 0) {
			enterState(1);
			if (change > 0) {
				setScale(getScale() + 0.05D);
			}
		} else if (scale > 2) {
			enterState(2);
			if (change < 0) {
				setScale(getScale() - 0.05D);
			}
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

		addState(new StatePlay());
		addState(new StateMap());
		addState(new StateMenu());

		enterState(0);
	}
}
