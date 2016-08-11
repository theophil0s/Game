package milkyway.earth.game.main;

import java.util.Random;

import org.newdawn.slick.GameContainer;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.network.util.GameID;
import milkyway.earth.object.Block;
import milkyway.earth.object.GameResources;

public class GameLevel {

	int sizeX = 10;
	int sizeY = 10;
	
	private Block block[][];

	public GameLevel() {

		block = new Block[sizeX][sizeY];

	}

	public void init(GameContainer gc) {

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				block[x][y] = new Block();
				block[x][y].setSprite(GameResources.colorTiles, new Random().nextInt(5), 0);
				block[x][y].setId(GameID.getID());
				block[x][y].setPosition(
						(float) (x * block[x][y].getWidth()),
						(float) (y * block[x][y].getHeight()));
				
				GameObjects.addObject(block[x][y]);
			}
		}
	}

	public void update(GameContainer gc, int delta, GameCam camera) {
		
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
//				if ((block[x][y]).getPosX() - camera.offX >= 0 - 30
//						&& (block[x][y]).getPosX() - camera.offX < gc.getWidth() - 30
//						&& (block[x][y]).getPosY() - camera.offY >= 0 - 30
//						&& (block[x][y]).getPosY() - camera.offY < gc.getHeight() - 30) {
//					
//					// -30 just for Reasons
//					// fckng autoformat!
//					
//					GameObjects.addObject(block[x][y]);
//
//				} else {
//					
//					GameObjects.removeObject(block[x][y]);
//				}
			}
		}
	}
}
