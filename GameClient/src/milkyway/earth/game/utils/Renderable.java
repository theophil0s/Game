package milkyway.earth.game.utils;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Renderable {

	public boolean isRenderable = true;
	
	public void render (GameContainer gc, Graphics g, float scale); 
	
}
