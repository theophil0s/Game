package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.world.GameCam;

public class Tree extends GameObject {

	public Tree() {

	}

	public Tree(long id, float posX, float posY, int renderLayer, Image image) {
		super(id, posX, posY, renderLayer, image);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);

		hitbox = new Circle(0,0,0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		((Circle) hitbox).setLocation(renderX + renderW / 2.2F, renderY + renderH / 1.2F);
		((Circle) hitbox).setRadius(renderW / 12);

		if (hitbox.contains(GameCam.mX , GameCam.mY)) {
			colliding = true;
			System.out.println("YEAH");
		} else {
			colliding = false;
		}
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (image != null) {
			image.startUse();
			image.drawEmbedded(renderX, renderY, renderW, renderH);
			image.endUse();
		}

		ShapeRenderer.draw(outline);
		ShapeRenderer.draw(hitbox);
	}
}
