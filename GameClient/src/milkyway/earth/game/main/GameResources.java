package milkyway.earth.game.main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GameResources {

	
	private static GameResources res;
	
	public static Image player;
	public static SpriteSheet sprite;
	public static SpriteSheet character;

	private static Image[] down;
	private static Image[] up;
	private static Image[] left;
	private static Image[] right;
	
	public static Animation animationDown;
	public static Animation animationUp;
	public static Animation animationLeft;
	public static  Animation animationRight;
	
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
		
		down = new Image[3];
		up = new Image[3];
		left = new Image[3];
		right = new Image[3];
		
		if (character != null) {
			for (int i = 0; i < 3; i++) {
				down[i] = character.getSubImage(i, 0);
			}
	
			for (int i = 0; i < 3; i++) {
				up[i] = character.getSubImage(i, 3);
			}
			
			for (int i = 0; i < 3; i++) {
				left[i] = character.getSubImage(i, 1);
			}
			
			for (int i = 0; i < 3; i++) {
				right[i] = character.getSubImage(i, 2);
			}
		}
		
		animationDown = new Animation(down, 150);
		animationUp = new Animation(up, 150);
		animationLeft = new Animation(left, 150);
		animationRight = new Animation(right, 150);
		
	}
}
