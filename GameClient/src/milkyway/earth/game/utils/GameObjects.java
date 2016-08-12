package milkyway.earth.game.utils;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.object.GameObject;
import milkyway.earth.object.Player;

public class GameObjects {

	private volatile static long playerId = -1;
	private volatile static ConcurrentHashMap<Long, GameObject> objects;
	private volatile static GameObjects go  = new GameObjects();
	
	private static GameContainer gc;
	private static StateBasedGame game;
	
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
		System.out.println(object.getId());
		object.init(gc , game);
		objects.put(object.getId(), object);
	}

	public static void removeObject(GameObject object) {
		objects.remove(object.getId());
	}

	public void init(GameContainer gc, StateBasedGame game) {
		GameObjects.gc = gc;
		GameObjects.game = game;
	}

	public void update(GameContainer gc, StateBasedGame game, int delta, Player player) {
		
		for (long l : objects.keySet()) {
			if (objects.get(l) != player) objects.get(l).update(gc, game, delta);
		}
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g, float scale, Player player) {
		
		for (long l : objects.keySet()) {
			
			if (!(objects.get(l) instanceof Player)){
				objects.get(l).render(gc, game, g, scale);
			}
		}
		
		for (long l : objects.keySet()) {
			if (objects.get(l) instanceof Player && !(objects.get(l) == player)) {
				objects.get(l).render(gc, game, g, scale);
			}
		}
	}
}
