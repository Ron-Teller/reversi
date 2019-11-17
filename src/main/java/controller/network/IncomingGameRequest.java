package controller.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IncomingGameRequest {

	private P2PMessenger messenger;
	private List<IncomingGameRequestListener> listeners;
	Random random = new Random();
	boolean requesterMovesFirstInGame;
	
	public IncomingGameRequest(P2PMessenger messenger) {
		super();
		this.messenger = messenger;
		listeners = new ArrayList<>();
	}
	
	private void addMessengerListeners() {
		messenger.addListener(new MessageListener() {
			@Override
			public void received(String message) {
				onMessageReceived(message);
			}
			@Override
			public void connectionClosed() {
				onConnectionClosed();
			}
		});
	}
	
	public void start() {
		addMessengerListeners();
	}
	
	public void accept() {
		messenger.send("accept");
	}
	
	public void decline() {
		messenger.send("decline");
		cancel();
	}
	
	private boolean hostMovesFirst(String message) {
		boolean hostMovesFirst = message.split(" ")[1].startsWith("false");
		return hostMovesFirst;
	}
	
	private void onMessageReceived(String message) {
		if (message.startsWith("game")) {
			listeners.forEach(listener -> listener.incomingRequest(hostMovesFirst(message)));
		}
	}
	
	private void onConnectionClosed() {
		listeners.forEach(listener -> listener.connectionClosed());
	}

	public void addListener(IncomingGameRequestListener listener) {
		listeners.add(listener);
	}
	
	public void cancel() {
		try {
			messenger.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
