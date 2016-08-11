package milkyway.earth.game.main;

import org.newdawn.slick.GameContainer;

import milkyway.earth.object.GameObject;

public class GameCam {

	public float offX;
	public float offY;
	
	public void update(GameContainer gc,  GameObject go) {
		
		offX = go.getPosXToScreen() + go.getWidthToScreen() / 2  - gc.getWidth() / 2;
		offY = go.getPosYToScreen() + go.getHeightToScreen() / 2  - gc.getHeight() / 2;
	}
}
