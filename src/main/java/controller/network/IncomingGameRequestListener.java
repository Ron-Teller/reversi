package controller.network;

public interface IncomingGameRequestListener {
	public void incomingRequest(boolean hostMovesFirst);
	public void connectionClosed();
}
