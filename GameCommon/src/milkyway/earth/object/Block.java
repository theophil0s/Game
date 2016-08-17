package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


public class Block extends GameObject implements IRenderable{

	public static float blockSize;

	public Block() {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		setRenderLayer(GameObject.RENDER_LAYER_1);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;
		
		
		if (sprite != null) {
			sprite.startUse();
			sprite.getSubImage(spriteX, spriteY).drawEmbedded(renderX, renderY, renderW, renderH);
			sprite.endUse();
		}
		
//		g.setFont(GameResources.ttf);
//		g.drawString(String.valueOf(getId()), getPosXToScreen(), getPosYToScreen());
//		g.drawString(String.valueOf(getPosXToScreen()), getPosXToScreen(), getPosYToScreen() + 10);
//		g.drawString(String.valueOf(getPosYToScreen()), getPosXToScreen(), getPosYToScreen() + 20);
	}
}
