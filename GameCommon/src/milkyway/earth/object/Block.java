package milkyway.earth.object;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.effects.RenderEffect;
import milkyway.earth.game.interfaces.IRenderable;
import milkyway.earth.game.utils.GameObjects;

public class Block extends GameObject implements IRenderable{

	public static final float BLOCK_SIZE = 64;
	protected int[] posTile;
	protected Fixture fixture;
	
	private ConcurrentHashMap<Long, GameObject> objects;

	public Block(int row, int col, Fixture fixture) {
		this.fixture = fixture;
		
		this.posTile = new int[2];
		this.posTile[0] = row;
		this.posTile[1] = col;

		objects = new ConcurrentHashMap<Long, GameObject>();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);

		initFixture();

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
		for (long l : objects.keySet()) {
			// EVEN IF OBJECT = NULL - IT WONT BE REMOVED
			if (objects.get(l).getId() == -1) {
				objects.remove(l);
				
			} else {

				if (objects.get(l) instanceof MovableObject) {
					for (long l2 : objects.keySet()) {
						if (objects.get(l) != objects.get(l2)) {
							((MovableObject) objects.get(l)).addObject(objects.get(l2));
						}
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale) {
		this.scale = scale;

		if (sprite != null) {
			sprite.startUse();
			sprite.getSubImage(spriteX, spriteY).drawEmbedded(renderX, renderY, renderW, renderH);
			sprite.endUse();
		}
		
		// DEBUG BEGIN
		if (objects.size() > 0) {
			RenderEffect.renderAsGhost(sprite.getSubImage(spriteX, spriteY), animation, renderX, renderY, renderW,
					renderH, 0.012F, 1.000F, 0.043F, 1.000F);
		}
		if (objects.size() > 1) {
			RenderEffect.renderAsGhost(sprite.getSubImage(spriteX, spriteY), animation, renderX, renderY, renderW,
					renderH, 0.043F, 0.012F, 1.000F, 1.000F);
		}
		
//		g.drawString(String.valueOf(objects.size()), renderX + 1, renderY + 1);
//		ShapeRenderer.draw(outline);
		// DEBUG END
	}

	public void addObject(GameObject object) {
		if (!objects.contains(object)) {
			objects.put(object.getId(), object);
		}
	}

	public void removeObject(GameObject object) {
		if (objects.contains(object)) {
			for (long l : objects.keySet()) {
				if (objects.get(l) instanceof MovableObject) {
					MovableObject mo = (MovableObject) objects.get(l);
					for (long l2 : objects.keySet()) {
						mo.removeObject(objects.get(l2));
					}
				}
			}
			
			objects.remove(object.getId());
		}
	}

	private void initFixture() {
		if (fixture != null) {
			fixture.setPosX(getPosX());
			fixture.setPosY(getPosY());
			GameObjects.addObject(fixture);
		}
	}
	
	public int[] getPosTile() {
		return posTile;
	}

	public void setPosTile(int[] posTile) {
		this.posTile = posTile;
	}
}
