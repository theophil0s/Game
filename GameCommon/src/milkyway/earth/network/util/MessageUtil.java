package milkyway.earth.network.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import milkyway.earth.network.message.AddPlayer;
import milkyway.earth.network.message.LogIn;
import milkyway.earth.network.message.MovePlayer;
import milkyway.earth.network.message.RemovePlayer;
import milkyway.earth.network.message.UpdatePlayer;

public class MessageUtil {

	public static void registerMessages(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(RemovePlayer.class);
		kryo.register(LogIn.class);
		kryo.register(Error.class);
		kryo.register(AddPlayer.class);
		kryo.register(MovePlayer.class);
		kryo.register(UpdatePlayer.class);
	}

}
