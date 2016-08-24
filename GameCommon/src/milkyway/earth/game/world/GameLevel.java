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

	public static final int SIZE_X = 200;
	public static final int SIZE_Y = 200;

	public static Block block[][];

	public GameLevel() {

		block = new Block[SIZE_X][SIZE_Y];

	}

	public void init(GameContainer gc) {


		for (int x = 0; x < SIZE_X; x++) {
			for (int y = 0; y < SIZE_Y; y++) {
				int r = new Random().nextInt(10);
				if (r == 4) {
					block[x][y] = new Block(x, y, new Tree(GameID.getID(), GameObject.RENDER_LAYER_2, GameResources.tree, null, Tree.OFFSET_X,
							Tree.OFFSET_Y));

				} else {

					block[x][y] = new Block(x, y, null);
				}

				block[x][y].setSprite(GameResources.sprite, 5, 0);
				block[x][y].setId(GameID.getID());
				block[x][y].setRenderLayer(GameObject.RENDER_LAYER_1);
				block[x][y].setPosition((float) (x * block[x][y].getWidth()), (float) (y * block[x][y].getHeight()));
			}
		}

	}

	public void update(GameContainer gc, int delta, GameObject object) {

		for (int x = 0; x < SIZE_X; x++) {
			for (int y = 0; y < SIZE_Y; y++) {

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
