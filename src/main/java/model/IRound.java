package model;

public interface IRound {
	public void makeMove(Point point);
	public Color getCurrentPlayer();
	public boolean hasEnded();
	public IBoard getBoard();
	public IMoveFinder getMoveFinder();
	public IFlankDetector getFlankDetector();
	public IRound copy();
	public void reset();
}
