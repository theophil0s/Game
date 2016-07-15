package milkyway.earth.game.utils;

import org.newdawn.slick.geom.Point;

import milkyway.earth.game.main.Game;
import milkyway.earth.object.GameObject;

public class GameUtils {

	public static Point getCenter(GameObject go) {
		System.out.println(go.getImage());
		float x = (float) (Game.getContainer().getWidth() / 2 - go.getImage().getWidth() / 2);
		float y = (float) (Game.getContainer().getHeight() / 2 - go.getImage().getHeight() / 2);
		return new Point(x, y);
	}
}
