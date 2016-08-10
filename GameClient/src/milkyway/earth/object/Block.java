package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.game.utils.Renderable;

public class Block extends GameObject implements Renderable{

	public static float blockSize;

	public Block() {

	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		

	}
	
	@Override
	public void render(GameContainer gc, Graphics g, float scale) {
		this.scale = scale;
		
		if (sprite != null) {
			sprite.startUse();
			sprite.getSubImage(spriteX, spriteY).drawEmbedded(getPosXToScreen(), getPosYToScreen(), getWidthToScreen(), getHeightToScreen());
			sprite.endUse();
		}
	}
}
