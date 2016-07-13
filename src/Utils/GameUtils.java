package Utils;
import milkyway.earth.game.main.Game;
import milkyway.earth.game.main.GameObject;
import org.newdawn.slick.geom.Point;

public class GameUtils {
	
	public static Point getCenter(GameObject go) {
		System.out.println(go.getImage());
		float x = (float) (Game.getContainer().getWidth() / 2 - go.getImage().getWidth() / 2);
		float y = (float) (Game.getContainer().getHeight() / 2 - go.getImage().getHeight() / 2);
		return new Point(x, y);
	}
}
