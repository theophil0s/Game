package milkyway.earth.game.world;

import java.util.Random;

import org.newdawn.slick.GameContainer;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.network.util.GameID;
import milkyway.earth.object.Block;
import milkyway.earth.object.GameObject;
import milkyway.earth.object.GameResources;

public class GameLevel {

	int sizeX = 100;
	int sizeY = 100;
	
	private Block block[][];

	public GameLevel() {

		block = new Block[sizeX][sizeY];

	}

	public void init(GameContainer gc) {

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				
				block[x][y] = new Block();
				
				int random = new Random().nextInt(3);
				
				block[x][y].setSprite(GameResources.colorTiles, random, 0);
				block[x][y].setId(GameID.getID());
				block[x][y].setPosition(
						(float) (x * block[x][y].getWidth()),
						(float) (y * block[x][y].getHeight()));
				
				if (random == 0) {
					block[x][y].setRenderLayer(GameObject.RENDER_LAYER_1);
				} else
				if (random == 1){
					block[x][y].setRenderLayer(GameObject.RENDER_LAYER_2);
				} else
				if (random == 2){
					block[x][y].setRenderLayer(GameObject.RENDER_LAYER_3);
				} 
			}
		}
	}

	public void update(GameContainer gc, int delta, GameObject object, GameCam camera) {
		
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				
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
