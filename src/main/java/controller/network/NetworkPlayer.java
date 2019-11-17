package controller.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Point;

public class NetworkPlayer implements INetworkPlayer {

	private P2PMessenger messenger;
	private List<NetworkOpponentListener> listeners;
	
	public NetworkPlayer(P2PMessenger messenger) {
		this.listeners = new ArrayList<>();
		this.messenger = messenger;
		this.messenger.removeAllListeners();
		this.messenger.addListener(new MessageListener() {
			@Override
			public void received(String message) {
				onOpponentMessageReceived(message);
			}

			@Override
			public void connectionClosed() {
				onOpponentDisconnect();
			}
		});
	}

	private void onOpponentMessageReceived(String message) {
		String command = message.split(" ")[0];
		if (command.equals("move")) {
			int row = Integer.parseInt(message.split(" ")[1]);
			int colomn = Integer.parseInt(message.split(" ")[2]);
			onOpponentMove(new Point(row,colomn));
		} else if (command.equals("reset")) {
			onOpponentReset();
		} else if (command.equals("disconnect")) {
			onOpponentDisconnect();
		} else if (command.equals("message")) {
			onOpponentMessage(message.substring(8));
		} else {
		}
	}
	
	private void onOpponentMessage(String message) {
		listeners.forEach(listener -> listener.message(message));
	}
	
	private void onOpponentMove(Point point) {
		listeners.forEach(listener -> listener.moved(point));
	}
	
	private void onOpponentReset() {
		listeners.forEach(listener -> listener.reset());
	}
	
	private void onOpponentDisconnect() {
		listeners.forEach(listener -> listener.disconnected());
	}
	
	@Override
	public void addListener(NetworkOpponentListener listener) {
		listeners.add(listener);
	}

	@Override
	public void sendResetRequest() throws IOException {
		messenger.send("reset");
	}

	@Override
	public void sendMoveRequest(Point point) throws IOException {
		messenger.send("move "+point.getRow()+" "+point.getColomn());
	}

	@Override
	public void disconnect() throws IOException {
		messenger.send("disconnect");
		messenger.stop();
	}

	@Override
	public void sendMessage(String message) {
		messenger.send("message "+message);
	}

	@Override
	public String getAddress() {
		return messenger.getPeerAddress();
	}
	

}
