package milkyway.earth.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.effects.RenderEffect;
import milkyway.earth.game.interfaces.ICollidable;
import milkyway.earth.game.interfaces.IRenderable;
import milkyway.earth.game.interfaces.ISelectable;
import milkyway.earth.game.interfaces.IUpdatable;
import milkyway.earth.game.world.GameCam;

public class Fixture extends GameObject implements ICollidable, ISelectable, IUpdatable, IRenderable {

	protected float fixOffsetX;
	protected float fixOffsetY;

	public Fixture() {
	}

	public Fixture(long id, int renderLayer, Image image, float fixOffsetX, float fixOffsetY) {
		super.setId(id);
		super.setRenderLayer(renderLayer);
		super.setImage(image);
		setRenderLayer(GameObject.RENDER_LAYER_2);
		this.fixOffsetX = fixOffsetX;
		this.fixOffsetY = fixOffsetY;
	}

	public Fixture(long id, int renderLayer, Animation animation, float fixOffsetX, float fixOffsetY) {

		// ANIMATION NOT IN RENDER METHOD ATM !!!

		super.setId(id);
		super.setRenderLayer(renderLayer);
		super.setAnimation(animation);
		setRenderLayer(GameObject.RENDER_LAYER_2);
		this.fixOffsetX = fixOffsetX;
		this.fixOffsetY = fixOffsetY;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		renderX = (getPosXToScreen() - (GameCam.offX)) - this.fixOffsetX * scale;
		renderY = (getPosYToScreen() - (GameCam.offY)) - this.fixOffsetY * scale;
		renderW = getWidthToScreen();
		renderH = getHeightToScreen();

		((Rectangle) outline).setBounds(renderX, renderY, renderW, renderH);
		
		checkContains(movableObject);
		checkSelection(gc);

		colliding = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;
		
		if (contains) {
			RenderEffect.renderAsGhost(image, animation, renderX, renderY, renderW, renderH, 1, 1, 1, 1);
		} else


		if (image != null) {
			image.startUse();
			image.drawEmbedded(renderX, renderY, renderW, renderH);
			image.endUse();
		}

		if(selected) {
			ShapeRenderer.draw(outline);
			ShapeRenderer.draw(hitbox);
		}
	}

	@Override
	public void isCollidingWith(GameObject object) {
		colliding = true;
	}

	@Override
	public void checkSelection(GameContainer gc) {
		if (outline.contains(GameCam.mX, GameCam.mY) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			selected = !selected;
		}
	}

	@Override
	public void checkContains(GameObject object) {
		if (object != null) {
			if (!this.outline.contains(object.getOutline().getCenterX(), object.getOutline().getCenterY())){
				contains = false;
				object = null;
			};
		}
		
	}

	@Override
	public void setContains(GameObject object) {
		this.movableObject = (MovableObject) object;
		contains = true;
		
	}
}
