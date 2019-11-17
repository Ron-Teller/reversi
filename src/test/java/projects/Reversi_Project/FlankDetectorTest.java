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
import model.Point;
import model.ReversiBoard;

public class FlankDetectorTest {

	IFlankDetector flankDetector = new FlankDetector();

	IBoard board = new ReversiBoard();

	@Before
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	public void shouldFindAllFlankedOpponentsPoints() {
		List<Point> whiteFlankedPoints = Arrays.asList(new Point(4,4));
		assertTrue(whiteFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(4,3), Color.BLACK)));
		assertTrue(whiteFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(3,4), Color.BLACK)));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(4,3), Color.BLACK).containsAll(whiteFlankedPoints));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(3,4), Color.BLACK).containsAll(whiteFlankedPoints));
		
		whiteFlankedPoints = Arrays.asList(new Point(5,5));
		assertTrue(whiteFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(5,6), Color.BLACK)));
		assertTrue(whiteFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(6,5), Color.BLACK)));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(5,6), Color.BLACK).containsAll(whiteFlankedPoints));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(6,5), Color.BLACK).containsAll(whiteFlankedPoints));
		
		List<Point> blackFlankedPoints = Arrays.asList(new Point(4,5));
		assertTrue(blackFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(3,5), Color.WHITE)));
		assertTrue(blackFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(4,6), Color.WHITE)));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(3,5), Color.WHITE).containsAll(blackFlankedPoints));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(4,6), Color.WHITE).containsAll(blackFlankedPoints));	
		
		blackFlankedPoints = Arrays.asList(new Point(5,4));
		assertTrue(blackFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(6,4), Color.WHITE)));
		assertTrue(blackFlankedPoints.containsAll(flankDetector.findFlankedPoints(board, new Point(5,3), Color.WHITE)));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(6,4), Color.WHITE).containsAll(blackFlankedPoints));
		assertTrue(flankDetector.findFlankedPoints(board, new Point(5,3), Color.WHITE).containsAll(blackFlankedPoints));
		
	}

}
