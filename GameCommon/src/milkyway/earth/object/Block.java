package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Block extends GameObject {

	public static final float BLOCK_SIZE = 64;
	private int row;
	private int col;

	public Block(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (sprite != null) {
			sprite.startUse();
			sprite.getSubImage(spriteX, spriteY).drawEmbedded(renderX, renderY, renderW, renderH);
			sprite.endUse();
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
