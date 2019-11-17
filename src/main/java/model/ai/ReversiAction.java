package model.ai;

import model.Color;
import model.Point;
import model.ai.algorithm.Action;

public class ReversiAction implements Action {
	private final Color player;
	private final Point pointOccupied;
	
	public ReversiAction(Color player, Point pointOccupied) {
		super();
		this.player = player;
		this.pointOccupied = pointOccupied;
	}

	public Color getPlayer() {
		return player;
	}

	public Point getPointOccupied() {
		return pointOccupied;
	}
}
