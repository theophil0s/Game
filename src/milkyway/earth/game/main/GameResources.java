package milkyway.earth.game.main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameResources {

	public static Image player;
	
	public GameResources() {
		
		try {
		
			player = new Image("res/img/t.jpg");
		
		} catch (SlickException e) {

			e.printStackTrace();
		} 
	}
}
