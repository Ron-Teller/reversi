package controller.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutgoingGameRequest {

	private P2PMessenger messenger;
	private List<OutgoingGameRequestListener> listeners;
	Random random = new Random();
	boolean requesterMovesFirstInGame;
	
	public OutgoingGameRequest(P2PMessenger messenger) {
		super();
		this.messenger = messenger;
		listeners = new ArrayList<>();
		addMessengerListeners();
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
	
	private boolean peerHasAcceptedGame(String message) {
		boolean peerHasAcceptedGame = false;
		if (message.startsWith("accept")) {
			peerHasAcceptedGame = true;
		} 
		return peerHasAcceptedGame;
	}
	
	private boolean isValidMessage(String message) {
		return (message.startsWith("accept") || message.startsWith("decline"));
	}
	
	private void onMessageReceived(String message) {
		if (isValidMessage(message)) {
			notifyListenersGameRequestDecision(peerHasAcceptedGame(message));
		}
	}
	
	private void notifyListenersGameRequestDecision(boolean peerHasAcceptedGame) {
		listeners.forEach(listener -> listener.hasAccepted(peerHasAcceptedGame, requesterMovesFirstInGame));
	}
	
	private void onConnectionClosed() {
		listeners.forEach(listener -> listener.connectionClosed());
		cancel();
	}

	public void addListener(OutgoingGameRequestListener listener) {
		listeners.add(listener);
	}
	
	public void request() throws IOException {
		requesterMovesFirstInGame = random.nextBoolean();
		messenger.send(createGameRequestMessage());
	}
	
	private String createGameRequestMessage() {
		return "game "+requesterMovesFirstInGame;
	}
	
	public boolean hostMovesFirst(){
		return requesterMovesFirstInGame;
	}
	
	public void cancel() {
		try {
			messenger.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
