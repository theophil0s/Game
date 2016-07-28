package milkyway.earth.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import milkyway.earth.game.input.GameInput;
import milkyway.earth.game.main.Game;
import milkyway.earth.game.main.GameLevel;
import milkyway.earth.game.main.GameResources;

public class Player extends GameObject {

	private boolean set; // 4debugging
	private boolean local;
	private String name;
	
	private Image[] down = { GameResources.character.getSubImage(0, 0), GameResources.character.getSubImage(1, 0),
			GameResources.character.getSubImage(2, 0) };
	private Image[] up = { GameResources.character.getSubImage(0, 3), GameResources.character.getSubImage(1, 3),
			GameResources.character.getSubImage(2, 3) };
	private Image[] left = { GameResources.character.getSubImage(0, 1), GameResources.character.getSubImage(1, 1),
			GameResources.character.getSubImage(2, 1) };
	private Image[] right = { GameResources.character.getSubImage(0, 2), GameResources.character.getSubImage(1, 2),
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
				GameLevel.setOffX((int) -(gc.getWidth() / 2 - animationLeft.getWidth() / 2 * (float) Game.getScale()));
				GameLevel.setOffY((int) -(gc.getHeight() / 2 - animationLeft.getHeight() / 2 * (float) Game.getScale()));
				set = !set;
			}
			
			if (GameInput.getMoveLeft()) {
				animationLeft.draw(gc.getWidth() / 2 - animationLeft.getWidth() / 2 * (float) Game.getScale(),
						gc.getHeight() / 2 - animationLeft.getHeight() / 2 * (float) Game.getScale(),
						animationLeft.getWidth() * (float) Game.getScale(), animationLeft.getHeight() * (float) Game.getScale());
			} else if (GameInput.getMoveRight()) {
				animationRight.draw(gc.getWidth() / 2 - animationRight.getWidth() / 2 * (float) Game.getScale(),
						gc.getHeight() / 2 - animationRight.getHeight() / 2 * (float) Game.getScale(),
						animationRight.getWidth() * (float) Game.getScale(), animationRight.getHeight() * (float) Game.getScale());
			} else if (GameInput.getMoveDown()) {
				animationDown.draw(gc.getWidth() / 2 - animationDown.getWidth() / 2 * (float) Game.getScale(),
						gc.getHeight() / 2 - animationDown.getHeight() / 2 * (float) Game.getScale(),
						animationDown.getWidth() * (float) Game.getScale(), animationDown.getHeight() * (float) Game.getScale());
			} else if (GameInput.getMoveUp()) {
				animationUp.draw(gc.getWidth() / 2 - animationUp.getWidth() / 2 * (float) Game.getScale(),
						gc.getHeight() / 2 - animationUp.getHeight() / 2 * (float) Game.getScale(), animationUp.getWidth() * (float) Game.getScale(),
						animationUp.getHeight() * (float) Game.getScale());
			} else {
				getImage().draw(gc.getWidth() / 2 - getImage().getWidth() / 2 * (float) Game.getScale(),
						gc.getHeight() / 2 - getImage().getHeight() / 2 * (float) Game.getScale(), getImage().getWidth() * (float) Game.getScale(),
						getImage().getHeight() * (float) Game.getScale());
			}
		} else {
			super.render(gc, g, worldScale);
		}
	}
}
