package milkyway.earth.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import milkyway.earth.game.states.StatePlay;
import milkyway.earth.object.GameObject;
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

	public void update(int delta) {

		x = player.getPosX();
		y = player.getPosY();

		if (input.isKeyDown(Input.KEY_UP)) {
			y -= 0.1F * delta;
			player.setPositionToSend(x, y);
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			y += 0.1F * delta;
			player.setPositionToSend(x, y);
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			x -= 0.1F * delta;
			player.setPositionToSend(x, y);
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			x += 0.1F * delta;
			player.setPositionToSend(x, y);
		}

		send(player);

	}

	private void send(GameObject object) {
		if (StatePlay.gameClient.isRunning())
			StatePlay.gameClient.moveGameObject(object);

	}
}
