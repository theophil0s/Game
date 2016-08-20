package milkyway.earth.game.network;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

import milkyway.earth.game.utils.GameObjects;
import milkyway.earth.network.message.AddPlayer;
import milkyway.earth.network.message.LogIn;
import milkyway.earth.network.message.LogInError;
import milkyway.earth.network.message.MovePlayer;
import milkyway.earth.network.message.RemovePlayer;
import milkyway.earth.network.message.UpdatePlayer;
import milkyway.earth.network.util.MessageUtil;
import milkyway.earth.object.GameObject;
import milkyway.earth.object.GameResources;
import milkyway.earth.object.Player;

public class GameClient {

	private Client client;

	private String playerName;
	private String host;
	private int port;
	private String password;
	// TODO replace

	private boolean running = false;

	private String lastError;

	public GameClient(String playerName, String host, int port, String password, GameObjects gameObjects) {
		super();

		if (playerName == null || host == null) {
			throw new IllegalArgumentException("Null values");
		}

		this.playerName = playerName;
		this.host = host;
		this.port = port;
		this.password = password;

	}

	public void start() throws IOException {

		this.client = new Client();
		this.client.start();

		MessageUtil.registerMessages(this.client);

		this.client.addListener(new ThreadedListener(new GameClientListener()));

		this.client.connect(5000, this.host, this.port);

		LogIn logIn = new LogIn();
		logIn.setName(this.playerName);
		logIn.setPassword(password);

		this.client.sendTCP(logIn);

		this.running = true;
	}

	public void close() {
		if (this.isRunning()) {
			this.client.stop();
			this.running = false;
		}
	}

	public boolean isRunning() {
		return this.running;
	}

	private void setLastError(String lastError) {
		this.lastError = lastError;
	}

	public String getLastError() {
		return lastError;
	}

	// TODO replace
	public void moveGameObject(GameObject go) {
		if (!isRunning()) {
			return;
		}

		MovePlayer movePlayer = new MovePlayer();
		movePlayer.setId(go.getId());
		movePlayer.setX(go.getPosXToSend());
		movePlayer.setY(go.getPosYToSend());
		this.client.sendTCP(movePlayer);
	}

	private class GameClientListener extends Listener {
		@Override
		public void received(Connection connection, Object object) {
			if (object instanceof LogInError) {
				GameClient.this.close();
				// TODO replace
				setLastError(((LogInError) object).getMsg());
				System.out.println(String.format("LogInError -> %s", getLastError()));
			}

			if (object instanceof AddPlayer) {
				
				AddPlayer addPlayer = (AddPlayer) object;

				Player player = new Player(addPlayer.getX(), addPlayer.getY(),
						GameResources.character.getSubImage(0, 0));
				
				player.setId(addPlayer.getId());
				player.setName(addPlayer.getName());
				GameObjects.addObject(player);
				
				// TODO replace
				if (playerName.equals(addPlayer.getName())) {
					GameObjects.setPlayerId(addPlayer.getId());
				}

				System.out.println(String.format("AddPlayer -> ID: %s Name: %s", player.getId(), player.getName()));
				return;
			}

			if (object instanceof UpdatePlayer) {
				UpdatePlayer updatePlayer = (UpdatePlayer) object;
				// TODO replace
				for (long l : GameObjects.getObjectList().keySet()) {
					GameObject currentObject = GameObjects.getObjectList().get(l);
					if (currentObject != null && currentObject.getId() == updatePlayer.getId()) {
						currentObject.setPosition(updatePlayer.getX(), updatePlayer.getY());
						// System.out.println(String.format("UpdatePlayer -> ID:
						// %s X: %.2f Y: %.2f", updatePlayer.getId(),
						// updatePlayer.getX(), updatePlayer.getY()));
						break;
					}
				}
				return;
			}

			if (object instanceof RemovePlayer) {
				RemovePlayer removePlayer = (RemovePlayer) object;
				// TODO replace
				for (long l : GameObjects.getObjectList().keySet()) {
					GameObject currentObject = GameObjects.getObjectList().get(l);
					if (currentObject.getId() == removePlayer.getId()) {
						GameObjects.removeObject(currentObject);
						currentObject.setId( -1);
						System.out.println(String.format("RemovePlayer -> ID: %s", removePlayer.getId()));
						break;
					}
				}
				return;
			}
		}

		@Override
		public void disconnected(Connection connection) {
			// TODO logic implementation
			System.exit(0);
		}

	}

}
