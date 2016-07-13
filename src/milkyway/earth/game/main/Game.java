package milkyway.earth.game.main;

import java.awt.Dimension;

import milkyway.earth.game.input.GameInput;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Utils.GameObjects;

public class Game extends BasicGame{


	private static final String NAME = "Game";
	private static final int FRAMERATE_MAX = 60;								
	private static final Boolean SHOW_FPS = true;
	private static final Boolean FULLSCREEN = false;
	private static final Boolean FULLSCREEN_WINDOW = false;
	private static final Boolean VSYNC = false;
	private static final Boolean ALWAYS_RENDER = false;
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	
	private static Game game;
	private static Dimension size;
	private static AppGameContainer container;
	
	public static Dimension resolution;
	public static GameLevel level;
	public static GameObjects objects;
	
	public Game(String title) throws SlickException {
		super(title);
	}

	public static void main(String[] args) throws SlickException {

		game = new Game(NAME);
		start(game);
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
		level.render();
		objects.render();
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		
		new GameResources();
		new GameInput(container, (int) resolution.getHeight());

		level = new GameLevel();
		objects = new GameObjects();
		
		level.init();
		objects.init();
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
		level.update();
		objects.update();
	}

	public static void start(Game game) throws SlickException {
		
		resolution = new Dimension(WIDTH, HEIGHT);
		container = new AppGameContainer(game);

		if (FULLSCREEN) {
			size = new Dimension(container.getScreenWidth() , container.getScreenHeight());
		}else if (FULLSCREEN_WINDOW) {
			size = new Dimension(container.getScreenWidth() , container.getScreenHeight());
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		} else {
			size = resolution;
		}
		
		container.setDisplayMode(size.width, size.height, false);
		container.setShowFPS(SHOW_FPS);
		container.setFullscreen(FULLSCREEN);
		container.setTargetFrameRate(FRAMERATE_MAX -1);
		container.setVSync(VSYNC);
		container.setAlwaysRender(ALWAYS_RENDER);
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
}
