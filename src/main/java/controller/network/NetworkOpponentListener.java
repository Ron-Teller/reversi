package controller.network;

import model.Point;

public interface NetworkOpponentListener {
	public void moved(Point point);
	public void disconnected();
	public void reset();
	public void message(String message);
}
