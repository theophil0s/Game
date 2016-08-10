package milkyway.earth.game.main;

import milkyway.earth.object.GameObject;

public class GameCam {

	public float offX;
	public float offY;
	
	public void update(GameObject go) {
		
		offX = go.getPosXToScreen() + go.getWidthToScreen() / 2;
		offY = go.getPosYToScreen() + go.getHeightToScreen() / 2;
	}
}
