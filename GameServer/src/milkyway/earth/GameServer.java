package milkyway.earth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import milkyway.earth.network.message.AddPlayer;
import milkyway.earth.network.message.LogIn;
import milkyway.earth.network.message.LogInError;
import milkyway.earth.network.message.MovePlayer;
import milkyway.earth.network.message.RemovePlayer;
import milkyway.earth.network.message.UpdatePlayer;
import milkyway.earth.network.util.GameID;
import milkyway.earth.network.util.MessageUtil;
import milkyway.earth.object.Player;
import milkyway.earth.object.PlayerConnection;

public class GameServer {

	private Server server;

	// TODO replace with correct logic
	// private int playerId = 0;

	private int port;
	private String name;
	private String password;

	private boolean running = false;

	private Set<PlayerConnection> playerConnections = new HashSet<>(4);

	public GameServer(String name, String password, int port) {
		this.setName(name);
		this.password = password;
		this.port = port;
	}

	public void start() throws IOException {
		this.server = new Server() {
			@Override
			protected Connection newConnection() {
				return new PlayerConnection();
			}
		};

		MessageUtil.registerMessages(this.server);

		this.server.addListener(new GameServerListener());

		this.server.bind(this.port);
		this.server.start();
		this.running = true;
	}

	public void stop() {
		if (this.isRunning()) {
			this.server.stop();
			this.running = false;
		}
	}

	public boolean isRunning() {
		return this.running;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private class GameServerListener extends Listener {
		@Override
		public void received(Connection connection, Object object) {
			PlayerConnection playerConnection = (PlayerConnection) connection;

			if (object instanceof LogIn) {
				if (playerConnections.contains(connection)) {
					Log.debug(String.format("LogIn received from already logged in player ID: %s Name: %s",
							playerConnection.getPlayer().getId(), playerConnection.getPlayer().getName()));
					return;
				}

				String error = null;

				String name = ((LogIn) object).getName();
				String password = ((LogIn) object).getPassword();
				// TODO name validation
				if (StringUtils.isEmpty(name)) {
					error = "Invalid name";
				}

				// Reject if already logged in.
				for (PlayerConnection pC : playerConnections) {
					if (pC.getPlayer().getName().equals(name)) {
						error = "Duplicated name";
						break;
					}
				}

				if (GameServer.this.password != null) {
					if (!GameServer.this.password.equals(password)) {
						error = "Wrong password";
					}
				}

				if (error != null) {
					Log.debug(String.format("Failed login (%s ; %s): %s", name, password, error));
					milkyway.earth.network.message.LogInError err = new LogInError();
					err.setMsg(error);
					connection.sendTCP(error);
					connection.close();
					return;
				}

				// TODO player init
				Player player = new Player();
				// no Image , no Dimensions
				player.setName(name);
				player.setId(GameID.getID() - 1000000000000L);
				player.setPosition(600, 600);
				playerConnection.setPlayer(player);

				for (PlayerConnection pC : playerConnections) {
					AddPlayer addPlayer = new AddPlayer();
					addPlayer.setId(pC.getPlayer().getId());
					addPlayer.setName(pC.getPlayer().getName());
					addPlayer.setX(pC.getPlayer().getPosition().getX());
					addPlayer.setY(pC.getPlayer().getPosition().getY());
					playerConnection.sendTCP(addPlayer);
				}

				AddPlayer addPlayer = new AddPlayer();
				addPlayer.setId(player.getId());
				addPlayer.setName(player.getName());
				addPlayer.setX(player.getPosition().getX());
				addPlayer.setY(player.getPosition().getY());

				server.sendToAllTCP(addPlayer);

				playerConnections.add(playerConnection);
				return;
			} else if (object instanceof MovePlayer) {
				if (!playerConnections.contains(connection)) {
					Log.debug("MovePlayer received, but player not logged in");
					return;
				}

				MovePlayer movePlayer = (MovePlayer) object;
				// TODO sanity checks for input
				UpdatePlayer updatePlayer = new UpdatePlayer();
				updatePlayer.setId(movePlayer.getId());
				updatePlayer.setX(movePlayer.getX());
				updatePlayer.setY(movePlayer.getY());
				server.sendToAllTCP(updatePlayer);
				// TODO map update
			}
		}

		@Override
		public void disconnected(Connection connection) {
			PlayerConnection playerConnection = (PlayerConnection) connection;
			if (playerConnections.contains(playerConnection)) {
				playerConnections.remove(playerConnection);

				RemovePlayer remove = new RemovePlayer();
				remove.setId(playerConnection.getPlayer().getId());
				server.sendToAllTCP(remove);

				Log.debug(String.format("Player ID: %s Name: %s disconnected", playerConnection.getPlayer().getId(),
						playerConnection.getPlayer().getName()));
			}
		}
	}
}
