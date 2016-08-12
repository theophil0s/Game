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

	private static float scale;
	private static Game game;
	private static Dimension size;
	private static Dimension resolution;
	private static AppGameContainer container;
	
	private static StatePlay statePlay;

	public Game(String title) throws SlickException {
		super(title);
	}

	public static void main(String[] args) throws SlickException {

		game = new Game(NAME);
		statePlay = new StatePlay();

		start(game);
		
	}

	public static void start(Game game) throws SlickException {

		
		resolution = new Dimension(WIDTH, HEIGHT);
		container = new AppGameContainer(game);

		if (FULLSCREEN_WINDOW) {
			size = new Dimension(container.getScreenWidth(), container.getScreenHeight());
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

		} else {

			size = resolution;
		}

		setScale(size.width / 1280F);

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

	public static float getScale() {
		return scale;
	}

	public static void setScale(float scale) {
		Game.scale = scale;
	}

	public static StatePlay getStatePlay() {
		return statePlay;
	}

	@Override
	public void mouseWheelMoved(int change) {

		if (scale >= 0F && scale <= 2F) {
			
			enterState(0);
			
			if (change > 0F) {
				setScale(getScale() + 0.2F);
			} else {
				setScale(getScale() - 0.2F);
			}

		} else if (scale < 0F) {
			
			enterState(1);

			if (change > 0F) {
				setScale(getScale() + 0.2F);
			}

		} else if (scale > 2F) {
			
			enterState(2);

			if (change < 0F) {
				setScale(getScale() - 0.2F);
			}
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

		addState(statePlay);
		addState(new StateMap());
		addState(new StateMenu());

		enterState(0);
	}
}
