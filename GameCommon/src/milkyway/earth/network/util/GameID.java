package milkyway.earth.network.util;

public class GameID {
	private static long lastID;
	private static GameID ID;

	private GameID() {
	}

	public static GameID newID() {
		ID = new GameID();
		return ID;
	}

	public static synchronized long getID() {
		long id = System.currentTimeMillis();
		while (id <= lastID) {
			id++;
		}
		lastID = id;
		return id;
	}
}
