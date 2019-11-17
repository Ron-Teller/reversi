package controller.network;

public interface MessageListener {
	public void received(String message);
	public void connectionClosed();
}
