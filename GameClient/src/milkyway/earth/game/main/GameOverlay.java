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
		pos += vDist;
		g.drawString("updateCounter: " + String.valueOf(updateCounter), hDist, pos);
		pos += vDist;
		g.drawString("delta: " + String.valueOf(delta), hDist, pos);
		pos += vDist;
		g.drawString("Scale: " + Game.getScale(), hDist, pos);
		pos += vDist;
		g.drawString("Zoom: " + Game.getZoom(), hDist, pos);
		pos += vDist;
		g.drawString("mX: " + GameCam.mX + "mY: " + GameCam.mY, hDist, pos);
		pos += vDist;
		g.drawString("OffX: " + GameCam.offX, hDist, pos);
		pos += vDist;
		g.drawString("OffY: " + GameCam.offY, hDist, pos);
		pos += vDist;
		g.drawString("CamW: " + GameCam.camWidth, hDist, pos);
		pos += vDist;
		g.drawString("CamH: " + GameCam.camHeight, hDist, pos);
		pos += vDist;
		g.drawString("Objects: " + GameObjects.getSize(), hDist, pos);
		pos += vDist;
		g.drawString("L1: " + GameObjects.getLayer01Size(), hDist, pos);
		pos += vDist;
		g.drawString("L2B: " + GameObjects.getLayer02BeforeSize(), hDist, pos);
		pos += vDist;
		g.drawString("L2A: " + GameObjects.getLayer02AfterSize(), hDist, pos);
		pos += vDist;
		g.drawString("L3: " + GameObjects.getLayer03Size(), hDist, pos);
		pos += vDist;

		for (long l : GameObjects.getObjectList().keySet()) {
			if (GameObjects.getObjectList().get(l) instanceof Player) {
				Player p = (Player) GameObjects.getObjectList().get(l);
				g.drawString("Player ID " + l + " x: " + p.getPosX() + " y: "
						+ p.getPosY(), hDist, pos);
				pos += vDist;
				g.drawString("Tile: " + " col: " + p.getPosTile()[0] + " row: "
						+ p.getPosTile()[1] + " Obj: " + p.getObjectsSize(), hDist, pos);
				pos += vDist;
			}
		}
	}
}
