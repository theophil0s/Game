package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GameOverlay {
	
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
		g.drawString("fpsCounter:"+String.valueOf(fpsCounter), 10, 30);
		g.drawString("updateCounter:"+String.valueOf(updateCounter), 10, 50);
		g.drawString("delta:"+String.valueOf(delta), 10, 70);
	}
}
