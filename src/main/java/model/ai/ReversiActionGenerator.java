package model.ai;

import java.util.List;
import java.util.stream.Collectors;

import model.Color;
import model.IBoard;
import model.Point;
import model.ai.algorithm.Action;
import model.ai.algorithm.ActionGenerator;
import model.ai.algorithm.State;

public class ReversiActionGenerator implements ActionGenerator {

	@Override
	public List<Action> getActions(State state) {
		ReversiState reversiState = (ReversiState) state;
		Color currentPlayer = reversiState.getRound().getCurrentPlayer();
		return getOccupiablePointsForCurrentPlayer(reversiState).stream()
				.map(occupiablePoint -> new ReversiAction(currentPlayer, occupiablePoint))
				.collect(Collectors.toList());
	}

	private List<Point> getOccupiablePointsForCurrentPlayer(ReversiState reversiState) {
		IBoard board = reversiState.getRound().getBoard();
		Color currentPlayer = reversiState.getRound().getCurrentPlayer();
		return reversiState.getRound().getMoveFinder().getOccupiablePoints(board, currentPlayer);
	}
}
