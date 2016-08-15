package milkyway.earth.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameObject {

	public static final int RENDER_TYPE_STATIC = 1;
	
	private long id;
	protected int viewDistance;
	protected int renderType;
	protected float scale;
	protected float posX;
	protected float posY;
	protected float posXToSend;
	protected float posYToSend;
	protected float tempX;
	protected float tempY;
	protected float width;
	protected float height;
	protected Point position;
	protected Shape hitbox;
	protected Shape outline;
	
	protected Image image;
	protected Animation animation;
	protected SpriteSheet sprite;
	protected int spriteX;
	protected int spriteY;

	protected Boolean moveUp = false;
	protected Boolean moveDown = false;
	protected Boolean moveLeft = false;
	protected Boolean moveRight = false;
	
	public void init(GameContainer gc, StateBasedGame game) {}

	public void update(GameContainer gc, StateBasedGame game, int delta) {}

	public abstract void render(GameContainer gc, StateBasedGame game, Graphics g, float scale);

	
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

	public int getViewDistance() {
		return viewDistance;
	}

	public void setViewDistance(int viewDistance) {
		this.viewDistance = viewDistance;
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

	public float getPosXToSend() {
		return posXToSend;
	}

	public void setPosXToSend(float posXToSend) {
		this.posXToSend = posXToSend;
	}

	public float getPosYToSend() {
		return posYToSend;
	}

	public void setPosYToSend(float posYToSend) {
		this.posYToSend = posYToSend;
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
	
	public void sendPosition(float posX, float posY) {
		this.setPosXToSend(posX);
		this.setPosYToSend(posY);
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

	public Boolean getMoveUp() {
		return moveUp;
	}

	public void setMoveUp(Boolean moveUp) {
		this.moveUp = moveUp;
	}

	public Boolean getMoveDown() {
		return moveDown;
	}

	public void setMoveDown(Boolean moveDown) {
		this.moveDown = moveDown;
	}

	public Boolean getMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(Boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public Boolean getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(Boolean moveRight) {
		this.moveRight = moveRight;
	}
}
