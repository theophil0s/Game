package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.object.Player;

public class GameOverlay {

	final static int vDist = 20;
	final static int hDist = 10;
	static int pos;
	
	int fpsCounter;
	int updateCounter;
	int delta;

	public void init(GameContainer gc) {
		fpsCounter = 0;
		updateCounter = 0;
	}

	public void update(GameContainer gc, int delta) {
		this.delta = delta;
		updateCounter++;
	}

	public void render(GameContainer container, Graphics g) {
		
		fpsCounter++;
		
		pos = 30;
		g.drawString("fpsCounter: " + String.valueOf(fpsCounter), hDist, pos);
		pos+=vDist;
		g.drawString("updateCounter: " + String.valueOf(updateCounter), hDist, pos);
		pos+=vDist;
		g.drawString("delta: " + String.valueOf(delta), hDist, pos);
		pos+=vDist;
		g.drawString("Scale: " + Game.getScale(), hDist, pos);
		pos+=vDist;
		g.drawString("offX: " + GameLevel.offX, hDist, pos);
		pos+=vDist;
		g.drawString("offX: " + GameLevel.offY, hDist, pos);
		pos+=vDist;
		for (int i = 0; i < GameObjects.objects.size(); i++) {
			if (GameObjects.objects.get(i) instanceof Player) {
				g.drawString("Player"+i+" x: "+GameObjects.objects.get(i).getPosition().getX()+" y: "+GameObjects.objects.get(i).getPosition().getY(), hDist, pos);
				pos+=vDist;
			}
		}
	}
}
