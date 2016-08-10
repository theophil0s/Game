package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;

import milkyway.earth.game.utils.GameID;
import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.object.Block;

public class GameLevel {

	int sizeX = 50;
	int sizeY = 50;
	
	private Block block[][];

	public GameLevel() {

		block = new Block[sizeX][sizeY];

	}

	public void init(GameContainer gc) {

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				block[x][y] = new Block();
				block[x][y].setSprite(GameResources.sprite, 0, 0);
				block[x][y].setId(GameID.getID());
				block[x][y].setPosition(
						(float) (x * block[x][y].getWidth()),
						(float) (y * block[x][y].getHeight()));
				
				GameObjects.addObject(block[x][y]);
			}
		}
	}

	public void update(GameContainer gc, int delta) {
		// TODO
	}
}
