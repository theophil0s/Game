package milkyway.earth.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.world.GameCam;

public abstract class GameObject {

	public static final int RENDER_TYPE_STATIC = 1;
	public static final int RENDER_LAYER_1 = 1;
	public static final int RENDER_LAYER_2 = 2;
	public static final int RENDER_LAYER_3 = 3;

	public boolean colliding;
	public boolean selected;
	public boolean contains;
	
	private long id;
	protected String name;
	protected int viewDistance;
	protected int renderType;
	protected int renderLayer;
	protected int posTile[];
	protected float scale;
	protected float posX;
	protected float posY;
	protected float posXToSend;
	protected float posYToSend;
	protected float renderX;
	protected float renderY;
	protected float renderW;
	protected float renderH;
	protected float width;
	protected float height;
	protected Point position;
	protected Rectangle hitbox;
	protected Rectangle outline;

	protected Image image;
	protected Animation animation;
	protected SpriteSheet sprite;
	protected Fixture fixture;
	protected int spriteX;
	protected int spriteY;

	protected Boolean moveUp = false;
	protected Boolean moveDown = false;
	protected Boolean moveLeft = false;
	protected Boolean moveRight = false;
	protected MovableObject movableObject;

	public GameObject() {
	}

	public GameObject(long id, float posX, float posY, Image image) {
		this.id = id;
		this.posXToSend = posX;
		this.posYToSend = posY;
		this.posX = posX;
		this.posY = posY;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public GameObject(long id, float posX, float posY, int renderLayer) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.posXToSend = posX;
		this.posYToSend = posY;
		this.renderLayer = renderLayer;
	}

	public GameObject(long id, float posX, float posY, int renderLayer, Image image) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.posXToSend = posX;
		this.posYToSend = posY;
		this.renderLayer = renderLayer;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public GameObject(long id, float posX, float posY, int renderLayer, Animation animation) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.posXToSend = posX;
		this.posYToSend = posY;
		this.renderLayer = renderLayer;
		this.animation = animation;
		this.width = animation.getWidth();
		this.height = animation.getHeight();
	}

	public void init(GameContainer gc, StateBasedGame game) {

		posTile = new int[2];
		outline = new Rectangle(renderX, renderY, renderW, renderH);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {

		renderX = getPosXToScreen() - (GameCam.offX);
		renderY = getPosYToScreen() - (GameCam.offY);
		renderW = getWidthToScreen();
		renderH = getHeightToScreen();

		((Rectangle) outline).setBounds(renderX, renderY, renderW, renderH);

		colliding = false;
	}

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

	public float translateX(float posX) {
		return (posX + GameCam.offX) / scale;
	}

	public float translateY(float posY) {
		return (posY + GameCam.offY) / scale;
	}

	public float translateWidth(float width) {
		return width / scale;
	}

	public float translateHeight(float height) {
		return height / scale;
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

	public void setRenderLayer(int renderLayer) {
		this.renderLayer = renderLayer;
	}

	public int getRenderLayer() {
		return renderLayer;
	}

	public float getRenderX() {
		return renderX;
	}

	public void setRenderX(float renderX) {
		this.renderX = renderX;
	}

	public float getRenderY() {
		return renderY;
	}

	public void setRenderY(float renderY) {
		this.renderY = renderY;
	}

	public float getRenderW() {
		return renderW;
	}

	public void setRenderW(float renderW) {
		this.renderW = renderW;
	}

	public float getRenderH() {
		return renderH;
	}

	public void setRenderH(float renderH) {
		this.renderH = renderH;
	}

	public Rectangle getOutline() {
		return outline;
	}

	public void setOutline(Rectangle outline) {
		this.outline = outline;
	}

	public void setId(long i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPosition(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
		this.position = new Point(posX, posY);
	}

	public void setPositionToSend(float posXToSend, float posYToSend) {
		this.posXToSend = posXToSend;
		this.posYToSend = posYToSend;
	}

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
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

	public int[] getPosTile() {
		return posTile;
	}

	public void setPosTile(int[] posTile) {
		this.posTile = posTile;
	}

	public boolean isColliding() {
		return colliding;
	}

	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
