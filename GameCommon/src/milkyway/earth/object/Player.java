package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.effects.RenderEffect;

public class Player extends MovableObject {

	public Player() { };
	
	public Player(long id, float posX, float posY, Image image) {
		super(id, posX, posY, image);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		
		hitbox = new Rectangle(0, 0, 0, 0);

		renderLayer = GameObject.RENDER_LAYER_2;
		viewDistance = 1000;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
		((Rectangle) hitbox).setBounds(renderX + renderW / 4, renderY + renderH / 1.4F, renderW / 2, renderH / 4);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		
		if (selected) {
			RenderEffect.renderAsGhost(image, animation, renderX, renderY, renderW, renderH, 0, 1, 0, 0.5F);
			ShapeRenderer.draw(outline);
			ShapeRenderer.draw(hitbox);
		} else
			
		super.render(gc, game, g, scale);
	}
}
