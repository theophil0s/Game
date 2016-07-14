package milkyway.earth.game.utils;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import milkyway.earth.game.main.Game;
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
	
	public void init(GameContainer gc) throws SlickException {
		
		Player player = new Player();
		player.setImage(GameResources.player.getScaledCopy(Game.getScale()));
		player.setPostition(GameUtils.getCenter(player));
		
		for (GameObject go: objects) {
			go.init(gc);
		}
	}
	
	public void update(GameContainer gc, int delta) {
		
		for (GameObject go: objects) {
			go.update(gc, delta);
		}
	}
	
	public void render(GameContainer container, Graphics g) {

		for (GameObject go: objects) {
			go.render(container, g);
		}
	}
}
