package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.object.Block;

public class GameLevel {

	int sizeX = 200;
	int sizeY = 200;

	public static int offX = 0;
	public static int offY = 0;

	Block block[][];

	public GameLevel() {

		block = new Block[sizeX][sizeY];

	}

	public void init(GameContainer gc) {

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				block[x][y] = new Block();
				block[x][y].setSprite(GameResources.sprite, 0, 0);
			}
		}
	}

	public void update(GameContainer gc, int delta) {

	}

	public void render(GameContainer gc, Graphics g) {

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				block[x][y].setPostition(

						(float) (x
								* block[x][y].getSprite()
										.getSubImage(block[x][y].getSpriteX(), block[x][y].getSpriteY()).getWidth()
								* Game.getScale()),
						(float) (y
								* block[x][y].getSprite()
										.getSubImage(block[x][y].getSpriteX(), block[x][y].getSpriteY()).getHeight()
								* Game.getScale()));

				if ((block[x][y]).getPosition().getX() - GameLevel.offX >= 0
						&& (block[x][y]).getPosition().getX() - GameLevel.offX < gc.getWidth() - 30
						&& (block[x][y]).getPosition().getY() - GameLevel.offY >= 0
						&& (block[x][y]).getPosition().getY() - GameLevel.offY < gc.getHeight() - 30) {
					// -30 just for Reasons
					// fckng autoformat!
					block[x][y].render(gc, g, (float) Game.getScale());

				}
			}
		}
	}
}
