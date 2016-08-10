package milkyway.earth.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	public static final int RENDER_TYPE_STATIC = 1;
	
	private long id;
	protected int renderType;
	protected float scale;
	private float posX;
	private float posY;
	private float width;
	private float height;
	private Point position;
	private Shape hitbox;

	protected Image image;
	private Animation animation;
	protected SpriteSheet sprite;
	protected int spriteX;
	protected int spriteY;

	public void init(GameContainer gc) {}

	public void update(GameContainer gc, int delta) {}

	public void render(GameContainer gc, Graphics g, float scale) {this.scale = scale;}

	
	public float getPosXToScreen() {
		return posX * scale;
	}

	public float getPosYToScreen() {
		return posY * scale;
	}

	public float getWidthToScreen() {
		return width * scale;
	}

	public float getHeightToScreen() {
		return height * scale;
	}

	public long getId() {
		return id;
	}

	public int getRenderType() {
		return renderType;
	}

	public void setRenderType(int renderType) {
		this.renderType = renderType;
	}

	public void setId(long i) {
		this.id = i;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
		this.position = new Point(posX, getPosY());
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
		this.position = new Point(getPosX(), posY);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
		this.posX = position.getX();
		this.posY = position.getY();
	}
	
	public void setPosition(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
		this.position = new Point(posX, posY);
	}

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public SpriteSheet getSprite() {
		return sprite;
	}

	public void setSprite(SpriteSheet sprite) {
		this.sprite = sprite;
	}

	public void setSprite(SpriteSheet sprite, int spriteX, int spriteY) {
		setSprite(sprite);
		setSpriteX(spriteX);
		setSpriteY(spriteY);
		this.width = sprite.getSubImage(spriteX, spriteY).getWidth();
		this.height = sprite.getSubImage(spriteX, spriteY).getHeight();
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
