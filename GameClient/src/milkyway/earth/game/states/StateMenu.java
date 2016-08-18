package milkyway.earth.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateMenu extends BasicGameState {

	float x, y, w, h;
	Rectangle shape;

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		x = 100F;
		y = 100F;
		w = 100F;
		h = 100F;
		shape = new Rectangle(x, y, w, h);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

		g.drawString("MENU", 400, 400);
		// g.draw(shape);
	}

	@Override
	public int getID() {
		return 2;
	}

}
