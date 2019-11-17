package model;

public class Round implements IRound {

	private IBoard board;
	private Color currentPlayer;
	private IMoveFinder moveFinder;
	private IFlankDetector flankDetector;
	private Color firstToMove;

	public Round(IBoard board, Color firstToMove, IMoveFinder moveFinder, IFlankDetector flankDetector) {
		super();
		this.board = board;
		this.currentPlayer = firstToMove;
		this.firstToMove = firstToMove;
		this.moveFinder = moveFinder;
		this.flankDetector = flankDetector;
		board.initialize();
	}
	
	@Override
	public void makeMove(Point point) {
		if (moveIsLegal(point)) {
			board.setPoint(point, currentPlayer);
			flipOpponentsFlankedPieces(point);
			switchToNextPlayer();
		}
	}

	@Override
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	private void switchToNextPlayer() {
		if (canMove(currentPlayer.opponent())) {
			currentPlayer = currentPlayer.opponent();
		}
	}

	private boolean canMove(Color player) {
		return moveFinder.getOccupiablePoints(board, currentPlayer.opponent()).size() > 0;
	}

	public boolean moveIsLegal(Point point) {
		return moveFinder.getOccupiablePoints(board, currentPlayer).contains(point);
	}

	private void flipOpponentsFlankedPieces(Point point) {
		flankDetector.findFlankedPoints(board, point, currentPlayer).stream()
				.forEach(flankedPoint -> board.setPoint(flankedPoint, currentPlayer));
	}

	@Override
	public boolean hasEnded() {
		boolean currentPlayerHasMovesRemaining = moveFinder.getOccupiablePoints(board, currentPlayer).size() > 0;
		boolean opponentHasMovesRemaining = moveFinder.getOccupiablePoints(board, currentPlayer.opponent()).size() > 0;
		return ((!currentPlayerHasMovesRemaining) && (!opponentHasMovesRemaining));
	}

	@Override
	public IBoard getBoard() {
		return board;
	}

	public IMoveFinder getMoveFinder() {
		return moveFinder;
	}

	public IFlankDetector getFlankDetector() {
		return flankDetector;
	}

	@Override
	public IRound copy() {
		Round copy = new Round(board.copy(), firstToMove, moveFinder, flankDetector);
		copy.board = board.copy();
		copy.currentPlayer = getCurrentPlayer();
		return copy;
	}

	@Override
	public void reset() {
		board.initialize();
		currentPlayer = firstToMove;
	}
}
