package milkyway.earth;

import java.io.IOException;
import java.util.Scanner;

import com.esotericsoftware.minlog.Log;

public class StandaloneGameServer {

	public static void main(String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		GameServer gameServer = new GameServer("Game", null, 13001);
		try {
			gameServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner scanIn = new Scanner(System.in);
		while (true) {
			if ("quit".equals(scanIn.nextLine())) {
				System.out.println("Stopping server...");
				gameServer.stop();
				break;
			} else {
				System.out.println("No valid input.");
			}
		}
		scanIn.close();
	}
}
