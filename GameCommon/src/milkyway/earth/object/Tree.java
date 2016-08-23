package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Tree extends Fixture {

	public static final float OFFSET_X = 105;
	public static final float OFFSET_Y = 210;
	
	public Tree() {	}

	public Tree(long id, int renderLayer, Image image, float fixOffSetX, float fixOffSetY) {
		super(id, renderLayer, image, fixOffSetX, fixOffSetY);
	}
	
//	public Tree(long id, int renderLayer, Animation animation, float fixOffSetX, float fixOffSetY) {
//		super(id, renderLayer, animation, fixOffSetX, fixOffSetY);
//	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
		hitbox = new Rectangle(0,0,0,0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		((Rectangle) hitbox).setBounds(renderX + renderW / 2.2F, renderY + renderH / 1.1F, renderW / 6, renderH / 12);
	}
}

