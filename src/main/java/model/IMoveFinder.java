package model;
import java.util.List;

public interface IMoveFinder {
	public List<Point> getOccupiablePoints(IBoard board, Color player);
}
