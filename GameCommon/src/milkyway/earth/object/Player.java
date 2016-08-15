package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends GameObject implements Renderable {

	private boolean local;
	private String name;

	float renderX;
	float renderY;
	float renderW;
	float renderH;

	public Player() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public void move(float posX, float posY) {
		this.posXToSend = posX;
		this.posYToSend = posY;
	}

	public void init(GameContainer gc, StateBasedGame game) {

		outline = new Rectangle(0, 0, 0, 0);
		hitbox = new Circle(0, 0, 0);
		setViewDistance(1000);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		((Rectangle) outline).setBounds(renderX, renderY, renderW, renderH);
		((Circle) hitbox).setLocation(renderX + renderW / 6, renderY + renderH / 2);
		((Circle) hitbox).setRadius(renderW /3);

		if (tempX == posX && tempY > posY && animation != GameResources.animationUp) {
			setAnimation(GameResources.animationUp);
		} else

		if (tempX == posX && tempY < posY && animation != GameResources.animationDown) {
			setAnimation(GameResources.animationDown);
		} else

		if (tempX > posX && tempY == posY && animation != GameResources.animationLeft) {
			setAnimation(GameResources.animationLeft);
		} else

		if (tempX < posX && tempY == posY && animation != GameResources.animationRight) {
			setAnimation(GameResources.animationRight);
		} else

		if (tempX > posX && tempY > posY && animation != GameResources.animationLeft) {
			setAnimation(GameResources.animationLeft);
		} else

		if (tempX > posX && tempY < posY && animation != GameResources.animationLeft) {
			setAnimation(GameResources.animationLeft);
		} else

		if (tempX < posX && tempY < posY && animation != GameResources.animationRight) {
			setAnimation(GameResources.animationRight);
		} else

		if (tempX < posX && tempY > posY && animation != GameResources.animationRight) {
			setAnimation(GameResources.animationRight);
		} else
			
		if (tempX == posX && tempY == posY) {
			
			if (animation == GameResources.animationLeft ) image = GameResources.animationLeft.getImage(1);
			if (animation == GameResources.animationRight) image = GameResources.animationRight.getImage(1);
			if (animation == GameResources.animationUp ) image = GameResources.animationUp.getImage(1);
			if (animation == GameResources.animationDown ) image = GameResources.animationDown.getImage(1);
			
			setAnimation(null);
		}

		tempX = getPosX();
		tempY = getPosY();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (renderType == RENDER_TYPE_STATIC) {
			renderX = gc.getWidth() / 2 - getWidthToScreen() / 2;
			renderY = gc.getHeight() / 2 - getHeightToScreen() / 2;
			renderW = getWidthToScreen();
			renderH = getHeightToScreen();
		} else {
			renderX = getPosXToScreen();
			renderY = getPosYToScreen();
			renderW = getWidthToScreen();
			renderH = getHeightToScreen();
		}

		if (animation != null) {

			animation.draw(renderX, renderY, renderW, renderH, null);

		} else if (image != null) {

			image.draw(renderX, renderY, renderW, renderH, null);

		}

		g.draw(outline);
		g.draw(hitbox);
	}

}
