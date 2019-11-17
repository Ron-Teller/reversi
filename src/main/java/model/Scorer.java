package model;

public class Scorer{

	public static int getScore(IBoard board, Color color) {
		int pointsOccupiedByColor = 0;
		Point point;
		for (int row=1; row<=board.getHeight(); row++) {
			for (int colomn=1; colomn<=board.getWidth(); colomn++) {
				point = new Point(row, colomn);
				if (board.isOccupied(point) && board.getPoint(point).equals(color)) {
					pointsOccupiedByColor ++;
				}
			}
		}
		return pointsOccupiedByColor;
	}
	
	public static boolean isDraw(IBoard board) {
		return (getScore(board,Color.BLACK) == getScore(board, Color.WHITE));
	}
	
	public static Color getLeader(IBoard board) {
		Color winner = null;
		int whiteScore = getScore(board, Color.WHITE);
		int blackScore = getScore(board, Color.BLACK);
		if (whiteScore > blackScore) {
			winner = Color.WHITE;
		} else if (blackScore > whiteScore) {
			winner = Color.BLACK;
		} else {
			winner = null;
		}
		return winner;
	}
}
