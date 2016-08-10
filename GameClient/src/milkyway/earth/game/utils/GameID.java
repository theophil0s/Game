package milkyway.earth.game.utils;

public class GameID {
	private static long lastID;
	private static GameID ID;
	
	private GameID() {	}
	
	public static GameID newID() {
		ID = new GameID();
		return ID;
	}
	
	public static long getID () {
		long id = System.currentTimeMillis();
		while (id <= lastID) {
			id++;
		}
		lastID = id;
		System.out.println("ID: " + id);
		return id;
	}
}
