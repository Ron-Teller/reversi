package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardUtil {

	public static List<Point> getPoints(IBoard board) {
		List<Point> points = new ArrayList<Point>();
		for (int row = 1; row <= board.getHeight(); row++) {
			for (int colomn = 1; colomn <= board.getHeight(); colomn++) {
				points.add(new Point(row, colomn));
			}
		}
		return points;
	}

	public static List<Point> getUnoccupiedPoints(IBoard board) {
		return getPoints(board).stream().filter(point -> !board.isOccupied(point)).collect(Collectors.toList());
	}

	public static List<Point> getPointsOccupiedBy(IBoard board, Color color) {
		return getPoints(board).stream().filter(point -> board.isOccupied(point) && board.getPoint(point).equals(color))
				.collect(Collectors.toList());
	}
	
	public static List<Point> getOccupiedPoints(IBoard board) {
		return getPoints(board).stream().filter(point -> board.isOccupied(point)).collect(Collectors.toList());
	}
	
	public static List<Point> getAdjacentPoints(IBoard board, Point point) {
		Point neighbor;
		List<Point> surroundingPoints = new ArrayList<>();
		for (int row = point.getRow() - 1; row <= point.getRow() + 1; row++) {
			for (int colomn = point.getColomn() - 1; colomn <= point.getColomn() + 1; colomn++) {
				neighbor = new Point(row, colomn);
				if (board.inBound(neighbor) && (!neighbor.equals(point))) {
					surroundingPoints.add(neighbor);
				}
			}
		}
		return surroundingPoints;
	}
}
