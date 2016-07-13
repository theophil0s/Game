package milkyway.earth.game.input;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;

public class GameInput extends Input{

	Input in;
	
	public GameInput(AppGameContainer container, int height) {
		super(height);
		
		in = container.getInput();
		
	}
}
