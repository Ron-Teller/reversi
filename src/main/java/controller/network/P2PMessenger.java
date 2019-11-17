package controller.network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class P2PMessenger {

	private volatile Socket socket;
	private volatile PrintWriter outToPeer;
	private volatile BufferedReader inFromPeer;
	private volatile List<MessageListener> listeners = new ArrayList<>();

	public P2PMessenger(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public void start() throws IOException {
		outToPeer = new PrintWriter(socket.getOutputStream(),true);
		inFromPeer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		listenToPeerMessages();
	}
	
	private void listenToPeerMessages() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String line;
				try {
					while ((line = inFromPeer.readLine()) != null) {
						onMessageReceived(line);
					}
				} catch (IOException e) {
					onConnectionClosed();
				}
			}
		}).start();
	}
	
	public void removeAllListeners() {
		listeners.clear();
	}
	
	private void onConnectionClosed() {
		listeners.forEach(listener -> listener.connectionClosed());
	}
	
	public void send(String message) {
		outToPeer.println(message);
	}
	
	public void peerIsConnected() {
		
	}
	
	public void addListener(MessageListener listener) {
		listeners.add(listener);
	}
	
	private void onMessageReceived(String message) {
		listeners.stream().forEach(listener -> listener.received(message));
	}	
	
	public void stop() throws IOException {
		socket.close();
	}
	
	public String getPeerAddress() {
		return socket.getRemoteSocketAddress().toString();
	}
	
}
