package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.effects.RenderEffect;
import milkyway.earth.game.world.GameCam;

public class Tree extends GameObject {

	public Tree() {	}

	public Tree(long id, float posX, float posY, int renderLayer, Image image) {
		super(id, posX, posY, renderLayer, image);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);

		hitbox = new Rectangle(0,0,0,0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		((Rectangle) hitbox).setBounds(renderX + renderW / 2.2F, renderY + renderH / 1.1F, renderW / 6, renderH / 12);

		colliding = false;
		
		if (outline.contains(GameCam.mX , GameCam.mY) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			selected = !selected;
			// MOUSE IN SHAPE HERE
		}
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (colliding) {
			RenderEffect.renderAsGhost(image, animation, renderX, renderY, renderW, renderH, 0, 1, 0, 0.5F);
			ShapeRenderer.draw(outline);
			ShapeRenderer.draw(hitbox);
		} else

		if (image != null) {
			image.startUse();
			image.drawEmbedded(renderX, renderY, renderW, renderH);
			image.endUse();
		}
		
		if (selected) {
			RenderEffect.renderAsGhost(image, animation, renderX, renderY, renderW, renderH, 1, 1, 1, 1);
			ShapeRenderer.draw(outline);
			ShapeRenderer.draw(hitbox);
		}
		

	}

	@Override
	public void isCollidingWith(GameObject object) {
		colliding = true;
	}
}
