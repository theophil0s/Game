package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Character {



	public Player() {
	}

	public Player(float posX, float posY, Image image) {
		super(posX, posY, image);
	}

	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		
		renderLayer = GameObject.RENDER_LAYER_2;
		viewDistance = 1000;
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
		((Circle) hitbox).setLocation(renderX + renderW / 6, renderY + renderH / 2);
		((Circle) hitbox).setRadius(renderW / 3);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		super.render(gc, game, g, scale);
	}
}
