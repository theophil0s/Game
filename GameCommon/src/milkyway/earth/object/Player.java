package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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

		setPosition(posX, posY);


	}
	
	public void init(GameContainer gc, StateBasedGame game) {

		outline = new Rectangle(0, 0, 100, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		((Rectangle) outline).setBounds(renderX, renderY, renderW, renderH);
		
		// BEST SOLUTION?

		// if (moveLeft || tempX > getPosX()) {
		// setAnimation(GameResources.animationLeft);
		// } else
		//
		// if (moveRight|| tempX < getPosX()) {
		// setAnimation(GameResources.animationRight);
		// } else
		//
		// if (moveUp || tempY > getPosY()) {
		// setAnimation(GameResources.animationUp);
		// } else
		//
		// if (moveDown || tempY < getPosY()) {
		// setAnimation(GameResources.animationDown);
		// } else {
		// setAnimation(null);
		// }

		if (tempX > getPosX()) {
			setAnimation(GameResources.animationLeft);
		} else

		if (tempX < getPosX()) {
			setAnimation(GameResources.animationRight);
		} else

		if (tempY > getPosY()) {
			setAnimation(GameResources.animationUp);
		} else

		if (tempY < getPosY()) {
			setAnimation(GameResources.animationDown);
		} else

		if (tempX == getPosX() && tempY == getPosY()) {
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
	}

}
