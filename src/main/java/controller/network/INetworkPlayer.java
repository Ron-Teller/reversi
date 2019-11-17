package controller.network;

import java.io.IOException;

import model.Point;

public interface INetworkPlayer {
	public void addListener(NetworkOpponentListener listener);
	public void sendResetRequest() throws IOException;
	public void sendMoveRequest(Point point) throws IOException;
	public void disconnect() throws IOException;
	public void sendMessage(String message);
	public String getAddress();
}
