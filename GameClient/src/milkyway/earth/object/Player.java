package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.game.input.GameInput;
import milkyway.earth.game.main.GameResources;
import milkyway.earth.game.states.StatePlay;
import milkyway.earth.game.utils.Renderable;

public class Player extends GameObject implements Renderable{
	
	private boolean local;
	private String name;
	
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

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		
		if (GameInput.moveLeft) {
			setAnimation(GameResources.animationLeft);
		} else
		
		if (GameInput.moveRight) {
			setAnimation(GameResources.animationRight);
		} else
		
		if (GameInput.moveUp) {
			setAnimation(GameResources.animationUp);
		} else 
		
		if (GameInput.moveDown) {
			setAnimation(GameResources.animationDown);
		} else {
			setAnimation(null);
		}
	}

	public void move(float posX , float posY) {

		setPosition(posX, posY);

		if (StatePlay.gameClient.isRunning()) {
			StatePlay.gameClient.moveGameObject(this);
		}
	}
	
	public void render(GameContainer gc, Graphics g, float scale) {
		this.scale = scale;

		if (image != null) {
			if (renderType == RENDER_TYPE_STATIC) {
				image.draw(gc.getWidth() / 2 - getWidthToScreen() / 2, gc.getHeight() / 2 - getHeightToScreen() / 2, getWidthToScreen(), getHeightToScreen(), null);
			} else {
				image.draw((getPosXToScreen()), (getPosYToScreen()), getWidthToScreen(), getHeightToScreen(), null);
			}
			
		}
	}
}
