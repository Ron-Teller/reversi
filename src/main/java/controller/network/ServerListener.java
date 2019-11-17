package controller.network;

import java.net.Socket;

public interface ServerListener {
	public void clientConnected(Socket socket);
}
