package milkyway.earth.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import milkyway.earth.game.states.StatePlay;
import milkyway.earth.object.Player;

public class GameInput {

	private Input input;
	private Player player;

	float x;
	float y;

	public GameInput(GameContainer gc, int height, Player player) throws SlickException {
		this.player = player;
		input = gc.getInput();

	}

	public void update() {

		if (input.isKeyDown(Input.KEY_UP)) {
			y -= 1F;
			player.setMoveUp(true);
			player.move(x , y);
		} else
			player.setMoveUp(false);

		if (input.isKeyDown(Input.KEY_DOWN)) {
			y += 1F;
			player.setMoveDown(true);
			player.move(x , y);
		} else
			player.setMoveDown(false);

		if (input.isKeyDown(Input.KEY_LEFT)) {
			x -= 1F;
			player.setMoveLeft(true);
			player.move(x , y);
		} else
			player.setMoveLeft(false);

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			x += 1F;
			player.setMoveRight(true);
			player.move(x , y);
		} else {
			player.setMoveRight(false);
		}
		
	if(StatePlay.gameClient.isRunning())
		StatePlay.gameClient.moveGameObject(player);
	}

}
