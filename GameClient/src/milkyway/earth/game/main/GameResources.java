package milkyway.earth.game.main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GameResources {

	
	private static GameResources res;
	
	public static Image player;
	public static SpriteSheet sprite;
	public static SpriteSheet character;

	public GameResources getResourses() {
		return res;
	}
	
	public GameResources() {

		try {

			player = new Image("assets/img/t.jpg");
			sprite = new SpriteSheet("assets/img/tiles64_terrain.png", 64, 64);
			character = new SpriteSheet("assets/img/character_sprite2.png", 32, 48);

		} catch (SlickException e) {

			e.printStackTrace();
		}
	}
}
