package milkyway.earth.object;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.interfaces.ICollidable;
import milkyway.earth.game.interfaces.IRenderable;
import milkyway.earth.game.interfaces.ISelectable;
import milkyway.earth.game.interfaces.IUpdatable;
import milkyway.earth.game.world.GameCam;

public class MovableObject extends GameObject implements ICollidable, ISelectable, IUpdatable, IRenderable {

	private int tempCounter;
	private ConcurrentHashMap<Long, GameObject> objects;
	public float speed = 0.1F;

	public MovableObject() {
	}

	public MovableObject(long id, float posX, float posY, Image image) {
		super(id, posX, posY, image);
		objects = new ConcurrentHashMap<>();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		setAnimation();
		checkSelection(gc);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		// TODO ADD DRAW EMBEDDED
		if (animation != null) {

			animation.draw(renderX, renderY, renderW, renderH, null);

		} else

		if (image != null) {
			image.draw(renderX, renderY, renderW, renderH, null);
		}

		if(selected) {
			ShapeRenderer.draw(hitbox);
			ShapeRenderer.draw(outline);
		}

	}

	@Override
	public void setPosition(float posX, float posY) {
		// REMOTE OBJECTS
		setDirection(posX, posY);
		super.setPosition(posX, posY);
	}

	public void setPosition(float posX, float posY, float speed, int delta) {
		// LOCAL OBJECTS
		posX = posX - checkCollisionH(posX, speed, delta);
		posY = posY - checkCollisionV(posY, speed, delta);
		setDirection(posX, posY);
		super.setPositionToSend(posX, posY);
	}

	private float checkCollisionH(float x, float speed, int delta) {

		if (objects != null && hitbox != null) {
			for (long l : objects.keySet()) {
				GameObject currentObject = objects.get(l);
				
				if (currentObject.getOutline().contains(this.outline.getCenterX(), this.outline.getCenterY())) {
					((ICollidable) currentObject).setContains(this);
				}
				
				if (currentObject.getHitbox() != null && currentObject.getHitbox().intersects(hitbox)) {

					((ICollidable) currentObject).isCollidingWith(this);
					
					if (currentObject.getHitbox().getCenterX() < hitbox.getCenterX()) {

						return -(speed * delta);

					} else {

						return speed * delta;
					}
				}
			}
		}
		return 0;
	}

	private float checkCollisionV(float y, float speed, int delta) {

		if (objects != null) {
			for (long l : objects.keySet()) {
				GameObject currentObject = objects.get(l);
				if (currentObject.getHitbox().intersects(hitbox)) {

					((ICollidable) currentObject).isCollidingWith(this);

					if (currentObject.getHitbox().getCenterY() < hitbox.getCenterY()) {

						return -(speed * delta);

					} else {

						return speed * delta;
					}
				}
			}
		}

		return 0;
	}

	private void setDirection(float posX, float posY) {

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
	}

	private void setAnimation() {

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
		colliding = true;
		System.out.println(object);
	}
	
	@Override
	public void checkSelection(GameContainer gc) {
		if (outline.contains(GameCam.mX, GameCam.mY) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			selected = !selected;
		}
	}

	@Override
	public void checkContains(GameObject object) {
		if ( object != null) {
			if (!this.outline.contains(movableObject.getOutline().getCenterX(), movableObject.getOutline().getCenterY())){
				contains = false;
			};
		}
		
	}

	@Override
	public void setContains(GameObject object) {
		this.movableObject = (MovableObject) object;
		
	}
}
