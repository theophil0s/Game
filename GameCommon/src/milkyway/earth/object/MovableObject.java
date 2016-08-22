package milkyway.earth.object;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.world.GameCam;

public class MovableObject extends GameObject {

	private int tempCounter;
	private ConcurrentHashMap<Long, GameObject> objects;

	public MovableObject () { }
	
	public MovableObject(long id, float posX, float posY, Image image) {
		super(id, posX, posY, image);
		objects = new ConcurrentHashMap<>();
	}

	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
		if (outline.contains(GameCam.mX , GameCam.mY) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			selected = !selected;
		}
		
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

//		ShapeRenderer.draw(outline);
//		ShapeRenderer.draw(hitbox);
	}

	@Override
	public void setPosition(float posX, float posY) {

		// TODO Add Speed and delta
		posX = posX - checkCollisionH(posX);
		posY = posY - checkCollisionV(posY);
		
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

	private float checkCollisionH(float x) {

		if (objects != null && hitbox != null) {
			for (long l : objects.keySet()) {
				GameObject currentObject = objects.get(l);
				if (currentObject.getHitbox() != null && currentObject.getHitbox().intersects(hitbox)) {
					currentObject.isCollidingWith(this);
					if (currentObject.getHitbox().getCenterX() < hitbox.getCenterX()) {
						return -1F; // TODO Add Speed and delta
					} else {
						return 1; // TODO Add Speed and delta
					}
				}
			}
		}
		return 0;
	}

	private float checkCollisionV(float y) {
		
		if (objects != null) {
			for (long l : objects.keySet()) {
				GameObject currentObject = objects.get(l);
				if (currentObject.getHitbox().intersects(hitbox)) {
					
					currentObject.isCollidingWith(this);
					
					if (currentObject.getHitbox().getCenterY() < hitbox.getCenterY()) {
						return - 1F; // TODO Add Speed and delta
					} else {
						return 1F; // TODO Add Speed and delta
					} 
				}
			}
		}
		
		return 0;
	}

	public void addObject(GameObject object) {
		if (!objects.containsKey(object.getId()) && object != this) {
			objects.put(object.getId(), object);
		}
	}

	public void removeObject(GameObject object) {
		if (objects.containsKey(object.getId())) {
			objects.remove(object.getId(), object);
		}
	}

	public int getObjectsSize() {
		return objects.size();
	}

	@Override
	public void isCollidingWith(GameObject object) {
		// TODO Auto-generated method stub
		
	}
}
