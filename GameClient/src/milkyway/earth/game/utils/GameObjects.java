package milkyway.earth.game.utils;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import milkyway.earth.object.GameObject;
import milkyway.earth.object.Player;

public class GameObjects {

	private volatile static long playerId = -1;
	private volatile static ConcurrentHashMap<Long, GameObject> objects;
	private volatile static GameObjects go  = new GameObjects();

	private GameObjects() {
		objects = new ConcurrentHashMap<Long, GameObject>();
	}
	
	public static GameObjects getGo() {
		return go;
	}
	
	public static ConcurrentHashMap<Long, GameObject> getObjectList() {
		return objects;
	}

	public static int getSize() {
		return objects.size();
	}
	
	public static long getPlayerId() {
		return playerId;
	}

	public static void setPlayerId(long playerId) {
		GameObjects.playerId = playerId;
	}

	public static void addObject(GameObject object) {
		objects.put(object.getId(), object);
		System.out.println("GameObjects Objects: " + objects.size());
	}

	public static void removeObject(GameObject object) {
		objects.remove(object.getId());
		// 4DEBUG
		System.out.println("GameObjects Objects: " + objects.size());
		System.out.println(objects);
	}

	public void init(GameContainer gc) throws SlickException {

		for (long l : objects.keySet()) {
			objects.get(l).init(gc);
		}
	}

	public void update(GameContainer gc, int delta, Player player) {
		for (long l : objects.keySet()) {
			if (objects.get(l) != player) objects.get(l).update(gc, delta);;
		}
	}

	public void render(GameContainer gc, Graphics g, float scale, Player player) {
		
		for (long l : objects.keySet()) {
			if (!(objects.get(l) instanceof Player)){
				objects.get(l).render(gc, g, scale);
			}
		}
		
		for (long l : objects.keySet()) {
			if (objects.get(l) instanceof Player && !(objects.get(l) == player)) {
				objects.get(l).render(gc, g, scale);
			}
		}
	}
}
