package milkyway.earth.object;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

import milkyway.earth.game.main.GameLevel;

public class GameObject {

	private int id;
	private Image image;
	private SpriteSheet sprite;
	private int spriteX;
	private int spriteY;
	private Point position;
	private Color filter = null;
	private Shape hitbox;

	public void init(GameContainer gc) {

	}

	public void update(GameContainer gc, int delta) {

	}

	public void render(GameContainer gc, Graphics g, float worldScale) {

		if (position != null) {
			if (sprite != null) {
				sprite.startUse();

				sprite.getSubImage(spriteX, spriteY).drawEmbedded(position.getX() - GameLevel.offX,
						position.getY() - GameLevel.offY, sprite.getSubImage(spriteX, spriteY).getWidth() * worldScale,
						sprite.getSubImage(spriteX, spriteY).getHeight() * worldScale);

				sprite.endUse();
			}

			if (image != null) {
				image.draw(position.getX() - GameLevel.offX, position.getY() - GameLevel.offY,
						image.getWidth() * worldScale, image.getHeight() * worldScale, filter);
			}
		}

	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setPostition(float x, float y) {
		this.position = new Point(x, y);
	}

	public void setPostition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return position;
	}

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	public SpriteSheet getSprite() {
		return sprite;
	}

	public void setSprite(SpriteSheet sprite, int spriteX, int spriteY) {
		this.sprite = sprite;
		this.setSpriteX(spriteX);
		this.setSpriteY(spriteY);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpriteX() {
		return spriteX;
	}

	public void setSpriteX(int spriteX) {
		this.spriteX = spriteX;
	}

	public int getSpriteY() {
		return spriteY;
	}

	public void setSpriteY(int spriteY) {
		this.spriteY = spriteY;
	}

}
