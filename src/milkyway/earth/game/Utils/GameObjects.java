package milkyway.earth.game.Utils;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import milkyway.earth.game.main.GameObject;
import milkyway.earth.game.main.GameResources;
import milkyway.earth.game.objects.Player;

public class GameObjects {

	public static List<GameObject> objects;
	
	public GameObjects() {
		
		objects = new ArrayList<GameObject>();
	}
	
	public void addObject(GameObject object) {
		
		objects.add(object);
		System.out.println("GameObjects Objects: "+objects.size());
	}
	
	public void removeObject(GameObject object)  {
		
		objects.remove(object);
		System.out.println("GameObjects Objects: "+objects.size());
	}
	
	public void init() throws SlickException {
		
		Player player = new Player();
		player.setImage(GameResources.player);
		player.setPostition(GameUtils.getCenter(player));
		
		for (GameObject go: objects) {
			go.init();
		}
	}
	
	public void update() {
		
		for (GameObject go: objects) {
			go.update();
		}
	}
	
	public void render() {

		for (GameObject go: objects) {
			go.render();
		}
	}
}
