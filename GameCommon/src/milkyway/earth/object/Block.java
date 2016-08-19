package milkyway.earth.object;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.game.effects.RenderEffect;
import milkyway.earth.game.world.GameLevel;

public class Block extends GameObject {

	public static final float BLOCK_SIZE = 64;
	private int[] posTile;
	
	private ConcurrentHashMap<Long, GameObject> objects;
	private HashMap<Long , Block> blocksNear;
	
	public Block(int row, int col) {
		
		posTile = new int[2];
		objects = new ConcurrentHashMap<Long, GameObject>();
		blocksNear = new HashMap<Long , Block>();
		
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
		
		if (objects.size() > 0)
			for (Long l: objects.keySet()) {
				if ((objects.get(l).getPosTile()[0] != getPosTile()[0]) || (objects.get(l).getPosTile()[1] != getPosTile()[1])) {
					objects.remove(l);
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
		if (objects.size() > 0) {
			RenderEffect.renderAsGhost(sprite.getSubImage(spriteX, spriteY), animation, renderX, renderY, renderW, renderH, 0.012F, 1.000F, 0.043F, 1.000F);
		}
		if (objects.size() > 1) {
			RenderEffect.renderAsGhost(sprite.getSubImage(spriteX, spriteY), animation, renderX, renderY, renderW, renderH, 1.000F, 0.012F, 0.043F, 1.000F);
		}
	}


	public void addObject(GameObject object) {
		if (!objects.contains(object)) {
			System.out.println("ADD");
			objects.put(object.getId(), object);
			
			int range = 2;
			for (int i = -range ; i <= range; i ++) {
				for (int j = -range; j <= range; j++) {
					System.out.println(GameLevel.block[(getPosTile()[1] + i)][(getPosTile()[0] + j)]);
				}
			}
		}
	}
	
	public int[] getPosTile() {
		return posTile;
	}

	public void setPosTile(int[] posTile) {
		this.posTile = posTile;
	}
}
