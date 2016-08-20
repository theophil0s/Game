package milkyway.earth.game.world;

import org.newdawn.slick.GameContainer;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.network.util.GameID;
import milkyway.earth.object.Block;
import milkyway.earth.object.GameObject;
import milkyway.earth.object.GameResources;
import milkyway.earth.object.Tree;

public class GameLevel {

	int sizeX = 20;
	int sizeY = 20;

	public static Block block[][];

	public GameLevel() {

		block = new Block[sizeX][sizeY];

	}

	public void init(GameContainer gc) {

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				block[x][y] = new Block(x, y);
				block[x][y].setSprite(GameResources.sprite, 5, 0);
				block[x][y].setId(GameID.getID());
				block[x][y].setRenderLayer(GameObject.RENDER_LAYER_1);
				block[x][y].setPosition((float) (x * block[x][y].getWidth()), (float) (y * block[x][y].getHeight()));
			}
		}

		Tree tree1 = new Tree(GameID.getID(), 200, 400, GameObject.RENDER_LAYER_2, GameResources.tree);
		GameObjects.addObject(tree1);
		Tree tree2 = new Tree(GameID.getID(), 700, 500, GameObject.RENDER_LAYER_2, GameResources.tree);
		GameObjects.addObject(tree2);
		Tree tree3 = new Tree(GameID.getID(), 500, 250, GameObject.RENDER_LAYER_2, GameResources.tree);
		GameObjects.addObject(tree3);
	}

	public void update(GameContainer gc, int delta, GameObject object) {
		
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {

				// RENDER VISIBLE OBJECTS _ NOT WORKING YET
				// if (
				// (int)block[x][y].getPosXToScreen() >= (int)camera.offX
				// && (int)block[x][y].getPosXToScreen() <= (int)camera.camWidth
				// && (int)block[x][y].getPosYToScreen() >= (int)camera.offY
				// && (int)block[x][y].getPosYToScreen() <=
				// (int)camera.camHeight) {

				if (object != null && block[x][y].getPosX() > object.getPosX() - object.getViewDistance()
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
