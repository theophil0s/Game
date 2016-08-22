package milkyway.earth.game.world;

import org.newdawn.slick.GameContainer;

import milkyway.earth.object.GameObject;

public class GameCam {

	public static float offX;
	public static float offY;

	public static float camWidth;
	public static float camHeight;

	public static float mX , mY;
	
	public static void update(GameContainer gc, float scale, GameObject go) {
		
		mX = gc.getInput().getMouseX() - offX;
		mX = gc.getInput().getMouseY() - offY;
		
		if (go != null) {
			offX = go.getPosXToScreen() + go.getWidthToScreen() / 2 - gc.getWidth() / 2;
			offY = go.getPosYToScreen() + go.getHeightToScreen() / 2 - gc.getHeight() / 2;

			mX = gc.getInput().getMouseX();
			mY = gc.getInput().getMouseY();
			
			camWidth = offX * 2;
			camHeight = offY * 2;
		}
	}

	public static float getmX() {
		return mX;
	}

	public static float getmY() {
		return mY;
	}
	
	public static void setmX(float mX) {
		GameCam.mX = mX;
	}

	public static void setmY(float mY) {
		GameCam.mY = mY;
	}
}
