

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public interface IRenderable {

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale);
}
