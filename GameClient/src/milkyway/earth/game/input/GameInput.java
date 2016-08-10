package milkyway.earth.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import milkyway.earth.object.Player;

public class GameInput {

	private Input input;
	private Player player;

	float x;
	float y;

	public static Boolean moveUp = false;
	public static Boolean moveDown = false;
	public static Boolean moveLeft = false;
	public static Boolean moveRight = false;

	public static Boolean getMoveUp() {
		return moveUp;
	}

	public static Boolean getMoveDown() {
		return moveDown;
	}

	public static Boolean getMoveLeft() {
		return moveLeft;
	}

	public static Boolean getMoveRight() {
		return moveRight;
	}

	public GameInput(GameContainer gc, int height, Player player) throws SlickException {
		this.player = player;
		System.out.println("Input init....");
		input = gc.getInput();

	}

	public void update() {

		if (input.isKeyDown(Input.KEY_UP)) {
			y -= 1F;
			moveUp = true;
			player.move(x , y);
		} else
			moveUp = false;

		if (input.isKeyDown(Input.KEY_DOWN)) {
			y += 1F;
			moveDown = true;
			player.move(x , y);
		} else
			moveDown = false;

		if (input.isKeyDown(Input.KEY_LEFT)) {
			x -= 1F;
			moveLeft = true;
			player.move(x , y);
		} else
			moveLeft = false;

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			x += 1F;
			moveRight = true;
			player.move(x , y);
		} else
			moveRight = false;
	}
}
