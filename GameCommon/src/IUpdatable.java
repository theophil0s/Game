

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public interface IUpdatable {

	public void init(GameContainer gc, StateBasedGame game);

	public void update(GameContainer gc, StateBasedGame game, int delta);

}
