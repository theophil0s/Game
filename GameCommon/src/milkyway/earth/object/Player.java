package milkyway.earth.object;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends GameObject implements IRenderable {

	private int tempCounter;

	public Player() {
		
	}

	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);

		renderLayer = GameObject.RENDER_LAYER_2;
		viewDistance = 1000;
		hitbox = new Circle(0, 0, 0);
	}


	@Override
	public void setPosition(float posX, float posY) {

		if (posX == this.posX && posY < this.posY) {
			moveRight = false; moveLeft = false; moveUp = true; moveDown = false;
		} else

		if (posX == this.posX && posY > this.posY) {
			moveRight = false; moveLeft = false; moveUp = false; moveDown = true;
		} else

		if (posX < this.posX && posY == this.posY) {
			moveRight = false; moveLeft = true; moveUp = false; moveDown = false;
		} else

		if (posX > this.posX && posY == this.posY) {
			moveRight = true; moveLeft = false; moveUp = false; moveDown = false;
		} else

		if (posX < this.posX && posY < this.posY) {
			moveRight = false; moveLeft = true; moveUp = true; moveDown = false;
		} else

		if (posX < this.posX && posY > this.posY) {
			moveRight = false; moveLeft = true; moveUp = false; moveDown = true;
		} else

		if (posX > this.posX && posY > this.posY) {
			moveRight = true; moveLeft = false; moveUp = false; moveDown = true;
		} else

		if (posX > this.posX && posY < this.posY) {
			moveRight = true; moveLeft = false; moveUp = true; moveDown = false;
		} else
		
		if (posX == this.posX && posY == this.posY) {
			
			tempCounter++;
			if (tempCounter == 5) {
				moveRight = false; moveLeft = false; moveUp = false; moveDown = false;
				tempCounter = 0;
			}
		}

		super.setPosition(posX, posY);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		((Circle) hitbox).setLocation(renderX + renderW / 6, renderY + renderH / 2);
		((Circle) hitbox).setRadius(renderW / 3);
		
		if (moveRight) {
			animation = GameResources.animationRight;
		} else
			
		if (moveLeft) {
			animation = GameResources.animationLeft;
		} else
		
		if (moveUp) {
			animation = GameResources.animationUp;
		} else
		
		if (moveDown) {
			animation = GameResources.animationDown;
		} else {
		
			if (animation != null && image != animation.getImage(1)) {
				
				image = animation.getImage(1);
			}
			animation = null;

		}
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		GL11.glBlendFunc(GL11.GL_CONSTANT_COLOR, GL11.GL_ONE);
		GL14.glBlendEquation(GL14.GL_FUNC_ADD);
		GL14.glBlendColor(1.000F, 0.012F, 0.973F, 0.835F);
		
		if (animation != null) {

			animation.draw(renderX, renderY, renderW, renderH, null);

		} else if (image != null) {

			image.draw(renderX, renderY, renderW, renderH, null);

		}
		
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		ShapeRenderer.draw(outline);
		g.draw(hitbox);
	}
}
