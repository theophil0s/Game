package milkyway.earth.object;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.world.GameCam;

public class Chest extends Fixture {

	private boolean open;

	public Chest() {
	}

	public Chest(long id, int renderLayer, Image image, float fixOffSetX, float fixOffSetY) {
		super(id, renderLayer, image, fixOffSetX, fixOffSetY);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);

		hitbox = new Rectangle(0, 0, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		colliding = false;

		((Rectangle) hitbox).setBounds(renderX + renderW / 4, renderY + renderH / 3, renderW / 2, renderH / 2);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (image != null) {
			image.startUse();
			image.drawEmbedded(renderX, renderY, renderW, renderH);
			image.endUse();
		}
	}

	@Override
	public void isCollidingWith(GameObject object) {
		open();
	}

	private void open() {

		if (!open) {
			image = GameResources.chest.getSprite(1, 0);
			open = true;
		}

		if (selected) {
			image = GameResources.chest.getSprite(0, 0);
			open = false;
		}
	}

	@Override
	public void checkSelection(GameContainer gc) {

		if (outline.contains(GameCam.mX, GameCam.mY) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			selected = !selected;
		}
	}
}
