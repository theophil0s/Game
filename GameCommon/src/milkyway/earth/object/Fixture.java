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

public class Fixture extends GameObject implements ICollidable, ISelectable, IUpdatable, IRenderable{

	protected float fixOffsetX;
	protected float fixOffsetY;
	
	public Fixture() {}
	
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
		
		System.out.print(translateX(hitbox.getCenterX()) - getPosX());
		System.out.println(" "+ (translateX(hitbox.getCenterY()) - getPosY()));
		
		System.out.println(this.fixOffsetX * scale + " " + this.fixOffsetY * scale);
		
		renderX = (getPosXToScreen() - (GameCam.offX)) - this.fixOffsetX * scale;
		renderY = (getPosYToScreen() - (GameCam.offY)) - this.fixOffsetY * scale;
		renderW = getWidthToScreen();
		renderH = getHeightToScreen();
		
		((Rectangle) outline).setBounds(renderX , renderY, renderW, renderH);
		
		colliding = false;
		
		checkSelection(gc);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;
		
		ShapeRenderer.draw(outline);
		if (colliding) {
			RenderEffect.renderAsGhost(image, animation, renderX, renderY, renderW, renderH, 0, 1, 0, 0.5F);
			ShapeRenderer.draw(outline);
			ShapeRenderer.draw(hitbox);
			
		} else

		
		if (selected) {
			RenderEffect.renderAsGhost(image, animation, renderX, renderY, renderW, renderH, 1, 1, 1, 1);
		} else
		
		// ^ ALL4DEBUG	
			
		if (image != null) {
			image.startUse();
			image.drawEmbedded(renderX, renderY, renderW, renderH);
			image.endUse();
		}
		ShapeRenderer.draw(hitbox);
		ShapeRenderer.draw(outline);
	}

	@Override
	public void isCollidingWith(GameObject object) {
		colliding = true;
	}

	@Override
	public void checkSelection(GameContainer gc) {
		if (outline.contains(GameCam.mX , GameCam.mY) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			selected = !selected;
		}
	}
}
