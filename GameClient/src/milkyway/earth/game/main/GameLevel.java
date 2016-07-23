package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.object.Block;

public class GameLevel {

	int sizeX = 50;
	int sizeY = 50;
	
	Block block[][];
	
	public GameLevel() {

		block = new Block[sizeX][sizeY];
		
	}

	public void init(GameContainer gc) {
		
		for (int x = 0 ; x < sizeX ; x++) {
			for (int y= 0 ; y < sizeY ; y++) {
				block[x][y] = new Block();
				block[x][y].setImage(GameResources.player);
			}
		}
	}

	public void update(GameContainer gc, int delta) {

	}

	public void render(GameContainer gc, Graphics g) {
		for (int x = 0 ; x < sizeX ; x++) {
			for (int y= 0 ; y < sizeY ; y++) {
				block[x][y].setPostition(x * block[x][y].getImage().getWidth() * Game.getScale(), y * block[x][y].getImage().getHeight() * Game.getScale());
				block[x][y].render(gc, g, Game.getScale());
				g.drawRect(0, 0, 100, 100);
			}
		}
	}
}
