package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;

import milkyway.earth.object.GameObject;

public class GameCam {

	public float offX;
	public float offY;
	
	public float camWidth;
	public float camHeight;
	
	public void update(GameContainer gc, float scale, GameObject go) {
		
		if (go != null) {
			offX = go.getPosXToScreen() + go.getWidthToScreen() / 2  - gc.getWidth() / 2;
			offY = go.getPosYToScreen() + go.getHeightToScreen() / 2  - gc.getHeight() / 2;
			
			camWidth = offX *2;
			camHeight = offY*2;
		}
	}
}
