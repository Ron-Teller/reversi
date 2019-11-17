package model;

import java.util.List;
import java.util.stream.Collectors;

public class MoveFinder implements IMoveFinder {

	private IFlankDetector flankDetector;

	public MoveFinder(IFlankDetector flankDetector) {
		super();
		this.flankDetector = flankDetector;
	}

	@Override
	public List<Point> getOccupiablePoints(IBoard board, Color player) {
		return BoardUtil.getUnoccupiedPoints(board).stream().filter(point -> neighboringOpponentPoint(board, player, point))
				.filter(point -> playerOccupyingPointFlanksOpponent(board,player,point)).collect(Collectors.toList());
	}

	private boolean playerOccupyingPointFlanksOpponent(IBoard board, Color player, Point point) {
		return flankDetector.findFlankedPoints(board, point, player).size() > 0;
	}

	private boolean neighboringOpponentPoint(IBoard board, Color player, Point point) {
		return getAdjacentColors(board, point).contains(player.opponent());
	}

	private List<Color> getAdjacentColors(IBoard board, Point point) {
		return BoardUtil.getAdjacentPoints(board,point).stream().map(adjacentPoint -> board.getPoint(adjacentPoint))
				.collect(Collectors.toList());
	}
}
