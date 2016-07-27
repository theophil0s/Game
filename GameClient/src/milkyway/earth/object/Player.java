package milkyway.earth.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import milkyway.earth.game.input.GameInput;
import milkyway.earth.game.main.GameResources;

public class Player extends GameObject {

	private boolean local;
	private String name;

	Image[] down = { GameResources.character.getSubImage(0, 0), GameResources.character.getSubImage(1, 0),
			GameResources.character.getSubImage(2, 0) };
	Image[] up = { GameResources.character.getSubImage(0, 3), GameResources.character.getSubImage(1, 3),
			GameResources.character.getSubImage(2, 3) };
	Image[] left = { GameResources.character.getSubImage(0, 1), GameResources.character.getSubImage(1, 1),
			GameResources.character.getSubImage(2, 1) };
	Image[] right = { GameResources.character.getSubImage(0, 2), GameResources.character.getSubImage(1, 2),
			GameResources.character.getSubImage(2, 2) };

	private Animation animationDown = new Animation(down, 150);
	private Animation animationUp = new Animation(up, 150);
	private Animation animationLeft = new Animation(left, 150);
	private Animation animationRight = new Animation(right, 150);

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
	public void init(GameContainer gc) {
		super.init(gc);

	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g, float worldScale) {

		if (local) {
			if (GameInput.getMoveLeft()) {
				animationLeft.draw(gc.getWidth() / 2 - animationLeft.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - animationLeft.getHeight() / 2 * worldScale,
						animationLeft.getWidth() * worldScale, animationLeft.getHeight() * worldScale);
			} else if (GameInput.getMoveRight()) {
				animationRight.draw(gc.getWidth() / 2 - animationRight.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - animationRight.getHeight() / 2 * worldScale,
						animationRight.getWidth() * worldScale, animationRight.getHeight() * worldScale);
			} else if (GameInput.getMoveDown()) {
				animationDown.draw(gc.getWidth() / 2 - animationDown.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - animationDown.getHeight() / 2 * worldScale,
						animationDown.getWidth() * worldScale, animationDown.getHeight() * worldScale);
			} else if (GameInput.getMoveUp()) {
				animationUp.draw(gc.getWidth() / 2 - animationUp.getWidth() / 2 * worldScale,
						gc.getHeight() / 2 - animationUp.getHeight() / 2 * worldScale, animationUp.getWidth() * worldScale,
						animationUp.getHeight() * worldScale);
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
