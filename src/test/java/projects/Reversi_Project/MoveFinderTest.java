package projects.Reversi_Project;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.FlankDetector;
import model.IBoard;
import model.IFlankDetector;
import model.MoveFinder;
import model.Point;
import model.ReversiBoard;

public class MoveFinderTest {

	IFlankDetector flankDetector = new FlankDetector();
	MoveFinder moveFinder = new MoveFinder(flankDetector);
	IBoard board = new ReversiBoard();

	@Before
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	public void shouldFindAllPossibleOccupiablePoints() {
		List<Point> blackOccupiablePoints = Arrays.asList(new Point(4, 3), new Point(3, 4), new Point(5, 6),
				new Point(6, 5));
		assertTrue(blackOccupiablePoints.containsAll(moveFinder.getOccupiablePoints(board, Color.BLACK)));
		assertTrue(moveFinder.getOccupiablePoints(board, Color.BLACK).containsAll(blackOccupiablePoints));

		List<Point> whiteOccupiablePoints = Arrays.asList(new Point(5, 3), new Point(6, 4), new Point(3, 5),
				new Point(4, 6));
		assertTrue(whiteOccupiablePoints.containsAll(moveFinder.getOccupiablePoints(board, Color.WHITE)));
		assertTrue(moveFinder.getOccupiablePoints(board, Color.WHITE).containsAll(whiteOccupiablePoints));
	}
}
