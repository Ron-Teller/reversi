package controller.network;

public interface OutgoingGameRequestListener {
	public void hasAccepted(boolean hasAccepted, boolean hostFirstToMove);
	public void connectionClosed();
}
