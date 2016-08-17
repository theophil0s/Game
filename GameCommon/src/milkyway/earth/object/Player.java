package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends GameObject implements IRenderable {

	private String name;
	private int delta;
	private int tempCounter;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			if (tempCounter == 100 / delta) {
				moveRight = false; moveLeft = false; moveUp = false; moveDown = false;
				tempCounter = 0;
			}
		}

		super.setPosition(posX, posY);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.delta = delta;
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

		if (animation != null) {

			animation.draw(renderX, renderY, renderW, renderH, null);

		} else if (image != null) {

			image.draw(renderX, renderY, renderW, renderH, null);

		}

//		g.draw(outline);
//		g.draw(hitbox);
	}
}
