package milkyway.earth.object;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

public class GameResources {

	private static GameResources res;

	public static Image player;
	public static Image tree;
	public static SpriteSheet sprite;
	public static SpriteSheet character;
	public static SpriteSheet colorTiles;
	public static SpriteSheet chest;

	private static Image[] down;
	private static Image[] up;
	private static Image[] left;
	private static Image[] right;

	public static Animation animationDown;
	public static Animation animationUp;
	public static Animation animationLeft;
	public static Animation animationRight;

	public static Font awtFont = new Font("ARIAL", Font.BOLD, 10);
	public static TrueTypeFont ttf = new TrueTypeFont(awtFont, true);

	public GameResources getResourses() {
		return res;
	}

	public GameResources() {

		try {

			player = new Image("assets/img/t.jpg");
			tree = new Image("assets/img/tree_256.png");
			sprite = new SpriteSheet("assets/img/tiles64_terrain.png", 64, 64);
			character = new SpriteSheet("assets/img/character_sprite2.png", 32, 48);
			colorTiles = new SpriteSheet("assets/img/colors.png", 64, 64);
			chest = new SpriteSheet("assets/img/chest.png", 64, 50);

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
				up[i] = character.getSubImage(i, 3);
				left[i] = character.getSubImage(i, 1);
				right[i] = character.getSubImage(i, 2);
			}
		}

		animationDown = new Animation(down, 150);
		animationUp = new Animation(up, 150);
		animationLeft = new Animation(left, 150);
		animationRight = new Animation(right, 150);

	}
}
