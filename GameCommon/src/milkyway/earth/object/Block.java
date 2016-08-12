package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


public class Block extends GameObject implements Renderable{

	public static float blockSize;

	public Block() {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;
		
		if (sprite != null) {
			sprite.startUse();
			sprite.getSubImage(spriteX, spriteY).drawEmbedded(getPosXToScreen(), getPosYToScreen(), getWidthToScreen(), getHeightToScreen());
			sprite.endUse();
		}
		
//		g.setFont(GameResources.ttf);
//		g.drawString(String.valueOf(getId()), getPosXToScreen(), getPosYToScreen());
//		g.drawString(String.valueOf(getPosXToScreen()), getPosXToScreen(), getPosYToScreen() + 10);
//		g.drawString(String.valueOf(getPosYToScreen()), getPosXToScreen(), getPosYToScreen() + 20);
	}
}
