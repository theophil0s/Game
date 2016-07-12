import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{

	static Boolean SHOW_FPS = true;
	static Boolean FULLSCREEN = true;
	static String NAME = "Game";
	static Dimension size; 
	static Game game;
	
	public List<GameImage> images;
	
	public Game(String title) {
		super(title);
		
		size = new Dimension(1024, 768);
		images = new ArrayList<GameImage>();
		
	}

	public static void main(String[] args) throws SlickException {
		
		game = new Game(NAME);
		AppGameContainer container = new AppGameContainer(game);
		container.setDisplayMode(size.width, size.height, false);
		container.setShowFPS(SHOW_FPS);
		container.setFullscreen(FULLSCREEN);
		container.start();
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
		for (GameImage i: images) {
			i.draw(i.getLocation().x, i.getLocation().y);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		GameImage screen = new GameImage("res/img/99.gif");
		screen.setLocation(new Point(100,100));
		images.add(screen);
		
		GameImage screen2 = new GameImage("res/img/99.gif");
		screen2.setLocation(new Point(100,300));
		images.add(screen2);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
	}

}
