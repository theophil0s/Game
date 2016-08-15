package milkyway.earth.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import milkyway.earth.game.states.StatePlay;
import milkyway.earth.object.Player;

public class GameInput {

	private Input input;
	private Player player;

	float x = 0;
	float y = 0;

	public GameInput(GameContainer gc, int height, Player player) throws SlickException {
		this.player = player;
		input = gc.getInput();

	}

	public void update(int delta) {

		x = player.getPosX();
		y= player.getPosY();
		
		if (input.isKeyDown(Input.KEY_UP)) {
			y -= 0.1F * delta;
			player.setMoveUp(true);
			player.move(x , y);

		} else
			player.setMoveUp(false);

		if (input.isKeyDown(Input.KEY_DOWN)) {
			y += 0.1F * delta;
			player.setMoveDown(true);
			player.move(x , y);

		} else
			player.setMoveDown(false);

		if (input.isKeyDown(Input.KEY_LEFT)) {
			x -= 0.1F * delta;
			player.setMoveLeft(true);
			player.move(x , y);

		} else
			player.setMoveLeft(false);

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			x += 0.1F * delta;
			player.setMoveRight(true);
			player.move(x , y);

		} else {
			player.setMoveRight(false);
		}
		
		send();
		
	}

	private void send() {
		if(StatePlay.gameClient.isRunning())
			StatePlay.gameClient.moveGameObject(player);
		
	}
}


