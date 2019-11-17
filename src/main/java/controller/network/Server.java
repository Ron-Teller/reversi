
package controller.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private boolean runServer;
	private ServerSocket serverSocket;
	private List<ServerListener> listeners = new ArrayList<>();
	
	public Server() throws IOException {
		serverSocket = new ServerSocket();
	}

	public void addListener(ServerListener listener) {
		listeners.add(listener);
	}
	
	public void stop() {
		runServer = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
		}
	}
	
	public InetAddress getAddress() {
		return serverSocket.getInetAddress();
	}
	
	public void start() throws IOException {
		runServer = true;
		serverSocket = new ServerSocket(0);
	}
	
	public void listen() {
		try {
			while(shouldRunServer()) {
				handleClient(serverSocket.accept());
			}
		} catch (IOException e) {
		}
	}
	
	private void handleClient(Socket socket) throws IOException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				listeners.forEach(listener -> listener.clientConnected(socket));
			}
		}).start();
	}
	
	private boolean shouldRunServer() {
		return runServer;
	}
	
	public int getPort() {
		return serverSocket.getLocalPort();
	}
	
}
