package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.game.main.GameLevel;

public class Block extends GameObject {

	public static float blockSize;

	public Block() {

	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	public void update() {
	}

	@Override
	public void render(GameContainer gc, Graphics g, float worldScale) {

		blockSize = getSprite().getSubImage(getSpriteX(), getSpriteY()).getWidth() * worldScale;
		
		if (getPosition() != null) {
			if (getSprite() != null) {
				getSprite().startUse();

				getSprite().getSubImage(
						getSpriteX(), getSpriteY()).drawEmbedded(
							getPosition().getX() - GameLevel.offX,
							getPosition().getY() - GameLevel.offY,
							getSprite().getSubImage(getSpriteX(), getSpriteY()).getWidth() * worldScale,
							getSprite().getSubImage(getSpriteX(), getSpriteY()).getHeight() * worldScale);

				getSprite().endUse();
			}
		}
	}
}
