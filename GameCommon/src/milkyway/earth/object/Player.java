package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.world.GameCam;

public class Player extends MovableObject {


	public Player() {
	}

	public Player(float posX, float posY, Image image) {
		super(posX, posY, image);
	}

	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		
		hitbox = new Circle(0, 0, 0);
		renderLayer = GameObject.RENDER_LAYER_2;
		viewDistance = 1000;
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
		((Circle) hitbox).setLocation(renderX + renderW / 6, renderY + renderH / 2);
		((Circle) hitbox).setRadius(renderW / 3);
		
		if (hitbox.contains(GameCam.mX , GameCam.mY)) {
			colliding = true;
			System.out.println("YEAH");
		} else {
			colliding = false;
		}
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		super.render(gc, game, g, scale);
	}
}
