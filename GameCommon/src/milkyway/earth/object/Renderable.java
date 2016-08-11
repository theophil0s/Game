package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public interface Renderable {

	public boolean isRenderable = true;

	public void init(GameContainer gc, StateBasedGame game);

	public void update(GameContainer gc, StateBasedGame game, int delta);
	
	public void render (GameContainer gc, StateBasedGame game, Graphics g, float scale); 
	
}
