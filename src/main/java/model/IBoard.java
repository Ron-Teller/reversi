package model;

public interface IBoard {
	public void setPoint(Point point, Color color);
	public void clearPoint(Point point);
	public void initialize();
	public boolean isOccupied(Point point);
	public Color getPoint(Point point);
	public int getWidth();
	public int getHeight();
	public boolean inBound(Point point);
	public IBoard copy();
}
