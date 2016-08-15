package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends GameObject implements Renderable {

	private String name;

	int tempCounter;
	
	public Player() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		
		renderType = GameObject.RENDER_LAYER_2;
		viewDistance = 1000;
		hitbox = new Circle(0, 0, 0);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

		((Circle) hitbox).setLocation(renderX + renderW / 6, renderY + renderH / 2);
		((Circle) hitbox).setRadius(renderW /3);

		if (tempX == posX && tempY > posY && animation != GameResources.animationUp) {
			setAnimation(GameResources.animationUp);
			tempCounter = 0;
		} else

		if (tempX == posX && tempY < posY && animation != GameResources.animationDown) {
			setAnimation(GameResources.animationDown);
			tempCounter = 0;
		} else

		if (tempX > posX && tempY == posY && animation != GameResources.animationLeft) {
			setAnimation(GameResources.animationLeft);
			tempCounter = 0;
		} else

		if (tempX < posX && tempY == posY && animation != GameResources.animationRight) {
			setAnimation(GameResources.animationRight);
			tempCounter = 0;
		} else

		if (tempX > posX && tempY > posY && animation != GameResources.animationLeft) {
			setAnimation(GameResources.animationLeft);
			tempCounter = 0;
		} else

		if (tempX > posX && tempY < posY && animation != GameResources.animationLeft) {
			setAnimation(GameResources.animationLeft);
			tempCounter = 0;
		} else

		if (tempX < posX && tempY < posY && animation != GameResources.animationRight) {
			setAnimation(GameResources.animationRight);
			tempCounter = 0;
		} else

		if (tempX < posX && tempY > posY && animation != GameResources.animationRight) {
			setAnimation(GameResources.animationRight);
			tempCounter = 0;
		} else
			
		if (tempX == posX && tempY == posY) {

			tempCounter++;
			if (tempCounter > 5){
				if (animation == GameResources.animationLeft && image != GameResources.animationLeft.getImage(1)) {
					
					image = GameResources.animationLeft.getImage(1);
				}
				if (animation == GameResources.animationRight && image != GameResources.animationRight.getImage(1)) {
					
					image = GameResources.animationRight.getImage(1);
				}
				if (animation == GameResources.animationUp && image != GameResources.animationUp.getImage(1)){
					
					image = GameResources.animationUp.getImage(1);
				}
				if (animation == GameResources.animationDown && image != GameResources.animationDown.getImage(1)){
					
					image = GameResources.animationDown.getImage(1);
				}
				setAnimation(null);
				tempCounter = 0;
			}
		}

		tempX = getPosX();
		tempY = getPosY();

	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (animation != null) {

			animation.draw(renderX, renderY, renderW, renderH, null);

		} else if (image != null) {

			image.draw(renderX, renderY, renderW, renderH, null);

		}

		g.draw(outline);
		g.draw(hitbox);
	}

}
