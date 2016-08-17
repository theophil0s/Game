package milkyway.earth.game.world;

import java.util.Random;

import org.newdawn.slick.GameContainer;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.network.util.GameID;
import milkyway.earth.object.Block;
import milkyway.earth.object.GameObject;
import milkyway.earth.object.GameResources;
import milkyway.earth.object.Tree;

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
//				block[x][y].setSprite(GameResources.colorTiles, new Random().nextInt(3), 0);
				block[x][y].setSprite(GameResources.sprite, 4, 0);
				block[x][y].setId(GameID.getID());
				block[x][y].setPosition(
						(float) (x * block[x][y].getWidth()),
						(float) (y * block[x][y].getHeight()));
			}
		}
		
		for (int i = 0; i < 100; i++) {
			Tree tree = new Tree(GameID.getID(), new Random().nextInt(2000), new Random().nextInt(2000), GameObject.RENDER_LAYER_2, GameResources.tree);
			GameObjects.addObject(tree);
		}
	}

	public void update(GameContainer gc, int delta, GameObject object, GameCam camera) {
		
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {

// RENDER VISIBLE OBJECTS _ NOT WORKING YET				
//				if (
//						(int)block[x][y].getPosXToScreen() >= (int)camera.offX
//						&& (int)block[x][y].getPosXToScreen() <= (int)camera.camWidth
//						&& (int)block[x][y].getPosYToScreen() >= (int)camera.offY
//						&& (int)block[x][y].getPosYToScreen() <= (int)camera.camHeight) {

				
				if (object != null 
						&& block[x][y].getPosX() > object.getPosX() - object.getViewDistance()
						&& block[x][y].getPosX() < object.getPosX() + object.getViewDistance()
						&& block[x][y].getPosY() > object.getPosY() - object.getViewDistance()
						&& block[x][y].getPosY() < object.getPosY() + object.getViewDistance()) {
					
					if (!GameObjects.getObjectList().containsKey((block[x][y]).getId())) {
						GameObjects.addObject(block[x][y]);
					}
					
				} else {
					
					GameObjects.removeObject(block[x][y]);
				}
			}
		}
	}
}
