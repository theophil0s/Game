package milkyway.earth.game.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import milkyway.earth.object.Block;
import milkyway.earth.object.GameObject;

public class GameObjects {

	private volatile static long playerId = -1;
	private volatile static GameObjects go = new GameObjects();
	private volatile static ConcurrentHashMap<Long, GameObject> objects;
	private static HashMap<Long, GameObject> layer01;
	private static HashMap<Long, GameObject> layer02Before;
	private static HashMap<Long, GameObject> layer02After;
	private static HashMap<Long, GameObject> layer03;

	private static GameContainer gc;
	private static StateBasedGame game;

	private GameObjects() {

		objects = new ConcurrentHashMap<Long, GameObject>();
		layer01 = new HashMap<Long, GameObject>();
		layer02Before = new HashMap<Long, GameObject>();
		layer02After = new HashMap<Long, GameObject>();
		layer03 = new HashMap<Long, GameObject>();
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

	public static int getLayer01Size() {
		return layer01.size();
	}

	public static int getLayer02BeforeSize() {
		return layer02Before.size();
	}

	public static int getLayer02AfterSize() {
		return layer02After.size();
	}

	public static int getLayer03Size() {
		return layer03.size();
	}

	public static long getPlayerId() {
		return playerId;
	}

	public static void setPlayerId(long playerId) {
		GameObjects.playerId = playerId;
	}

	public static void addObject(GameObject object) {
		object.init(gc, game);
		objects.put(object.getId(), object);
	}

	public static void removeObject(GameObject object) {

		objects.remove(object.getId());

		if (object.getRenderLayer() == GameObject.RENDER_LAYER_1) {
			layer01.remove(object.getId());

		} else

		if (object.getRenderLayer() == GameObject.RENDER_LAYER_2) {

			if (layer02Before.containsKey(object.getId())) {
				layer02Before.remove(object.getId());

			} else

			if (layer02After.containsKey(object.getId())) {
				layer02After.remove(object.getId());
			}

		} else

		if (object.getRenderLayer() == GameObject.RENDER_LAYER_3) {
			layer03.remove(object.getId());
		}
	}

	public void init(GameContainer gc, StateBasedGame game) {
		GameObjects.gc = gc;
		GameObjects.game = game;
	}

	public void update(GameContainer gc, StateBasedGame game, int delta, GameObject object) {

		for (long l : objects.keySet()) {
			GameObject currentObject = objects.get(l);
			currentObject.update(gc, game, delta);
			sortObjectsToRender(currentObject, object);

			if (!(currentObject instanceof Block)) {
				currentObject.getPosTile()[0] = (int) ((currentObject.getPosX() + currentObject.getWidth() / 2) / Block.BLOCK_SIZE);
				currentObject.getPosTile()[1] = (int) ((currentObject.getPosY() + currentObject.getHeight() / 2) / Block.BLOCK_SIZE);
				
				// TODO set block information
			}
		}
	}

	private void sortObjectsToRender(GameObject currentObject, GameObject object) {
		if (currentObject.getRenderLayer() == GameObject.RENDER_LAYER_1) {
			layer01.put(currentObject.getId(), currentObject);
		} else

		if (currentObject.getRenderLayer() == GameObject.RENDER_LAYER_2) {

			if (object != null
					&& currentObject.getPosY() + currentObject.getHeight() < object.getPosY() + object.getHeight()) {

				layer02After.remove(currentObject.getId(), currentObject);
				layer02Before.put(currentObject.getId(), currentObject);
			} else {
				layer02Before.remove(currentObject.getId(), currentObject);
				layer02After.put(currentObject.getId(), currentObject);
			}

		} else

		if (currentObject.getRenderLayer() == GameObject.RENDER_LAYER_3) {
			layer03.put(currentObject.getId(), currentObject);
		}
	}

	public static void renderLayer01(GameContainer gc, StateBasedGame game, Graphics g, float scale,
			GameObject object) {

		for (long l : layer01.keySet()) {
			GameObject currentObject = objects.get(l);
			currentObject.render(gc, game, g, scale);
		}
	}

	public static void renderLayer02Before(GameContainer gc, StateBasedGame game, Graphics g, float scale,
			GameObject object) {

		List<GameObject> list = new ArrayList<GameObject>(layer02Before.values());
		Collections.sort(list, new Comparator<GameObject>() {
			public int compare(GameObject o1, GameObject o2) {
				return (int) ((o1.getPosY() + o1.getHeight()) - (o2.getPosY() + o2.getHeight()));
			}
		});

		for (GameObject go : list) {
			if (go != object) {
				go.render(gc, game, g, scale);
			}
		}
	}

	public static void renderLayer02After(GameContainer gc, StateBasedGame game, Graphics g, float scale,
			GameObject object) {

		List<GameObject> list = new ArrayList<GameObject>(layer02After.values());
		Collections.sort(list, new Comparator<GameObject>() {
			public int compare(GameObject o1, GameObject o2) {
				return (int) ((o1.getPosY() + o1.getHeight()) - (o2.getPosY() + o2.getHeight()));
			}
		});

		for (GameObject go : list) {
			if (go != object) {
				go.render(gc, game, g, scale);
			}
		}
	}

	public static void renderLayer03(GameContainer gc, StateBasedGame game, Graphics g, float scale,
			GameObject object) {

		for (long l : layer03.keySet()) {
			GameObject currentObject = objects.get(l);
			currentObject.render(gc, game, g, scale);
		}
	}
}
