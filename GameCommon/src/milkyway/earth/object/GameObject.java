package milkyway.earth.object;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	private int id;
	private Image image;
	private Point position;
	private float scale = 0.5F;
	private Color filter = null;
	private Shape hitbox;

	public void init(GameContainer gc) {

	}

	public void update(GameContainer gc, int delta) {

	}

	public void render(GameContainer gc, Graphics g, float worldScale) {

		if (image != null && position != null)
			image.draw(position.getX(), position.getY(), worldScale, filter);
	}

	public Image getImage() {
		return image.getScaledCopy(scale);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}