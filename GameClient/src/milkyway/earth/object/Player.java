package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.game.input.GameInput;
import milkyway.earth.game.main.Game;
import milkyway.earth.game.main.GameLevel;
import milkyway.earth.game.main.GameResources;

public class Player extends GameObject {

	private boolean set; // 4debugging
	private boolean local;
	private float speed;
	private String name;
	
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
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void init(GameContainer gc) {
		super.init(gc);
		System.out.println("Player Init!");
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g, float worldScale) {


		if (local) {
			// DIRTY SOLUTION 4 DEBUGGING
			if (!set) {
				GameLevel.setOffX((int) -(gc.getWidth() / 2 - GameResources.animationLeft.getWidth() / 2 * worldScale));
				GameLevel.setOffY((int) -(gc.getHeight() / 2 - GameResources.animationLeft.getHeight() / 2 * worldScale));
				set = !set;
			}
			
			if (GameInput.getMoveLeft()) {
				GameResources.animationLeft.draw(gc.getWidth() / 2 - GameResources.animationLeft.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - GameResources.animationLeft.getHeight() / 2 * (float) Game.getScale(),
						GameResources.animationLeft.getWidth() * worldScale, GameResources.animationLeft.getHeight() * worldScale);
			} else if (GameInput.getMoveRight()) {
				GameResources.animationRight.draw(gc.getWidth() / 2 - GameResources.animationRight.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - GameResources.animationRight.getHeight() / 2 * (float) Game.getScale(),
						GameResources.animationRight.getWidth() * worldScale, GameResources.animationRight.getHeight() * worldScale);
			} else if (GameInput.getMoveDown()) {
				GameResources.animationDown.draw(gc.getWidth() / 2 - GameResources.animationDown.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - GameResources.animationDown.getHeight() / 2 * worldScale,
						GameResources.animationDown.getWidth() * worldScale, GameResources.animationDown.getHeight() * worldScale);
			} else if (GameInput.getMoveUp()) {
				GameResources.animationUp.draw(gc.getWidth() / 2 - GameResources.animationUp.getWidth() / 2 * (float) Game.getScale(),
						gc.getHeight() / 2 - GameResources.animationUp.getHeight() / 2 * worldScale, GameResources.animationUp.getWidth() * worldScale,
						GameResources.animationUp.getHeight() * worldScale);
			} else {
				getImage().draw(gc.getWidth() / 2 - getImage().getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - getImage().getHeight() / 2 * worldScale, getImage().getWidth() * worldScale,
						getImage().getHeight() * worldScale);
			}
		} else {
			super.render(gc, g, worldScale);
		}
	}
}
