package milkyway.earth.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import milkyway.earth.game.main.GameLevel;
import milkyway.earth.game.states.StatePlay;
import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.object.GameObject;
import milkyway.earth.object.Player;

public class GameInput {

	private boolean set;
	
	private Input input;

	int x;
	int y;

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

	public GameInput(GameContainer gc, int height) throws SlickException {

		input = gc.getInput();

	}
	
	public void update() {
		

		
		if (!set) {
			move();
			set = !set;
		}
		
		if (input.isKeyDown(Input.KEY_UP)) {
			y -= 1;
			GameLevel.offY -= 1;
			moveUp = true;
			move();
		} else
			moveUp = false;

		if (input.isKeyDown(Input.KEY_DOWN)) {
			y += 1;
			GameLevel.offY += 1;
			moveDown = true;
			move();
		} else
			moveDown = false;

		if (input.isKeyDown(Input.KEY_LEFT)) {
			x -= 1;
			GameLevel.offX -= 1;
			moveLeft = true;
			move();
		} else
			moveLeft = false;

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			x += 1;
			GameLevel.offX += 1;
			moveRight = true;
			move();
		} else
			moveRight = false;
	}

	public void move() {
		for (GameObject go : GameObjects.objects) {
			if (GameObjects.playerId == go.getId()) {

				//TODO separate local and remote players
				((Player) go).setLocal(true);
				
				go.setPostition(x, y);
				if (StatePlay.gameClient.isRunning()) {
					StatePlay.gameClient.moveGameObject(go);
				}
			}
			
			// if (GameObjects.playerId == go.getId()) {
			// go.setPostition(go.getPosition().getX() + x,
			// go.getPosition().getY() + y);
			// if (StatePlay.gameClient.isRunning()) {
			// StatePlay.gameClient.moveGameObject(go);
			// }
			// }
		}
	}
}
