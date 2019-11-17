package model;

import java.util.List;

public interface IFlankDetector {
	public List<Point> findFlankedPoints(IBoard board, Point point, Color playerColor);
}
