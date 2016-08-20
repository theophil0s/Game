package milkyway.earth.object;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Block extends GameObject {

	public static final float BLOCK_SIZE = 64;
	private int[] posTile;

	private ConcurrentHashMap<Long, GameObject> objects;

	public Block(int row, int col) {

		posTile = new int[2];
		objects = new ConcurrentHashMap<Long, GameObject>();

		this.posTile[0] = row;
		this.posTile[1] = col;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) {
		super.init(gc, game);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		super.update(gc, game, delta);
		
		for (long l : objects.keySet()) {
			
			if (objects.get(l).getId() == -1) {
				objects.remove(l);
				
			} else {

				for (long l2 : objects.keySet()) {
					if (objects.get(l) != objects.get(l2)) {
						
						if (objects.get(l2).getHitbox().intersects(objects.get(l).getHitbox())) {
							
							System.out.println("COL");
							
							objects.get(l).colliding = true;
							objects.get(l2).colliding = true;
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
//		if (objects.size() > 0) {
//			RenderEffect.renderAsGhost(sprite.getSubImage(spriteX, spriteY), animation, renderX, renderY, renderW,
//					renderH, 0.012F, 1.000F, 0.043F, 1.000F);
//		}
//		if (objects.size() > 1) {
//			RenderEffect.renderAsGhost(sprite.getSubImage(spriteX, spriteY), animation, renderX, renderY, renderW,
//					renderH, 0.043F, 0.012F, 1.000F, 1.000F);
//		}
	}

	public void addObject(GameObject object) {
		if (!objects.contains(object)) {
			objects.put(object.getId(), object);
		}
	}

	public void removeObject(GameObject object) {
		if (objects.contains(object)) {
			objects.remove(object.getId());
		}
	}

	public int[] getPosTile() {
		return posTile;
	}

	public void setPosTile(int[] posTile) {
		this.posTile = posTile;
	}
}
