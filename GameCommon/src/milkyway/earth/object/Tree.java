package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Tree extends GameObject implements IRenderable{

	public Tree() {
	}
	
	public Tree(long id, float posX, float posY, int renderLayer, Image image) {
		super(id, posX, posY, renderLayer, image);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;
		
		if (image != null) {
			image.startUse();
			image.drawEmbedded(renderX, renderY, renderW, renderH);
			image.endUse();
		}
		
//		g.draw(outline);
		
	}
}
