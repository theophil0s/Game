package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.game.world.GameCam;
import milkyway.earth.object.GameResources;
import milkyway.earth.object.Player;

public class GameOverlay {

	final static int vDist = 15;
	final static int hDist = 10;
	static int pos;
	
	int fpsCounter;
	int updateCounter;
	int delta;

	
	public void init(GameContainer gc) {
		fpsCounter = 0;
		updateCounter = 0;
		
		gc.setDefaultFont(GameResources.ttf);
	}

	public void update(GameContainer gc, int delta) {
		this.delta = delta;
		updateCounter++;
	}

	public void render(GameContainer gc, Graphics g, GameCam camera) {
		
		fpsCounter++;
		
		g.setFont(GameResources.ttf);
		
		pos = 30;
		g.drawString("fpsCounter: " + String.valueOf(fpsCounter), hDist, pos);
		pos+=vDist;
		g.drawString("updateCounter: " + String.valueOf(updateCounter), hDist, pos);
		pos+=vDist;
		g.drawString("delta: " + String.valueOf(delta), hDist, pos);
		pos+=vDist;
		g.drawString("Scale: " + Game.getScale(), hDist, pos);
		pos+=vDist;
		g.drawString("Zoom: " + Game.getZoom(), hDist, pos);
		pos+=vDist;
		g.drawString("OffX: " + camera.offX, hDist, pos);
		pos+=vDist;
		g.drawString("OffY: " + camera.offY, hDist, pos);
		pos+=vDist;
		g.drawString("CamW: " + camera.camWidth, hDist, pos);
		pos+=vDist;
		g.drawString("CamH: " + camera.camHeight, hDist, pos);
		pos+=vDist;
		g.drawString("Objects: " + GameObjects.getSize(), hDist, pos);
		pos+=vDist;
		g.drawString("Layer1: " + GameObjects.getLayer01Size(), hDist, pos);
		pos+=vDist;
		g.drawString("L2Before: " + GameObjects.getLayer02BeforeSize(), hDist, pos);
		pos+=vDist;
		g.drawString("L2After: " + GameObjects.getLayer02AfterSize(), hDist, pos);
		pos+=vDist;
		g.drawString("Layer3: " + GameObjects.getLayer03Size(), hDist, pos);
		pos+=vDist;
		
		for (long l : GameObjects.getObjectList().keySet()) {
			if (GameObjects.getObjectList().get(l) instanceof Player) {
				g.drawString("Player ID "+ l +" x: "+GameObjects.getObjectList().get(l).getPosX()+" y: "+GameObjects.getObjectList().get(l).getPosY(), hDist, pos);
				pos+=vDist;
			}
		}
		
		
	}
}
