package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

public class Character extends GameObject {

	private int tempCounter;
	
	public Character() {}
	
	public Character(float posX, float posY, Image image) {
		super(posX, posY, image);
	}

	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);

		hitbox = new Circle(0, 0, 0);
	}

	@Override
	public void setPosition(float posX, float posY) {

		if (posX == this.posX && posY < this.posY) {
			moveRight = false;
			moveLeft = false;
			moveUp = true;
			moveDown = false;
		} else

		if (posX == this.posX && posY > this.posY) {
			moveRight = false;
			moveLeft = false;
			moveUp = false;
			moveDown = true;
		} else

		if (posX < this.posX && posY == this.posY) {
			moveRight = false;
			moveLeft = true;
			moveUp = false;
			moveDown = false;
		} else

		if (posX > this.posX && posY == this.posY) {
			moveRight = true;
			moveLeft = false;
			moveUp = false;
			moveDown = false;
		} else

		if (posX < this.posX && posY < this.posY) {
			moveRight = false;
			moveLeft = true;
			moveUp = true;
			moveDown = false;
		} else

		if (posX < this.posX && posY > this.posY) {
			moveRight = false;
			moveLeft = true;
			moveUp = false;
			moveDown = true;
		} else

		if (posX > this.posX && posY > this.posY) {
			moveRight = true;
			moveLeft = false;
			moveUp = false;
			moveDown = true;
		} else

		if (posX > this.posX && posY < this.posY) {
			moveRight = true;
			moveLeft = false;
			moveUp = true;
			moveDown = false;
		} else

		if (posX == this.posX && posY == this.posY) {

			tempCounter++;
			if (tempCounter == 5) {
				moveRight = false;
				moveLeft = false;
				moveUp = false;
				moveDown = false;
				tempCounter = 0;
			}
		}

		super.setPosition(posX, posY);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

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

		ShapeRenderer.draw(outline);
		g.draw(hitbox);
	}
}
