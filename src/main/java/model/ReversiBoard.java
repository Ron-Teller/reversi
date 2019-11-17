package model;

public class ReversiBoard implements IBoard {

	private final int height = 8;
	private final int width = 8;
	private Color[][] points = new Color[height][width];

	@Override
	public void setPoint(Point point, Color color) {
		points[point.getRow() - 1][point.getColomn() - 1] = color;
	}

	@Override
	public void clearPoint(Point point) {
		points[point.getRow() - 1][point.getColomn() - 1] = null;
	}

	@Override
	public void initialize() {
		for (int row = 1; row <= height; row++) {
			for (int colomn = 1; colomn <= width; colomn++) {
				clearPoint(new Point(row, colomn));
			}
		}
		setPoint(new Point(4, 4), Color.WHITE);
		setPoint(new Point(5, 5), Color.WHITE);
		setPoint(new Point(4, 5), Color.BLACK);
		setPoint(new Point(5, 4), Color.BLACK);
	}

	@Override
	public boolean isOccupied(Point point) {
		return getPoint(point) != null;
	}

	@Override
	public Color getPoint(Point point) {
		return points[point.getRow() - 1][point.getColomn() - 1];
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean inBound(Point point) {
		return point.getRow() >= 1 && point.getColomn() >= 1 && point.getRow() <= height && point.getColomn() <= width;
	}

	@Override
	public IBoard copy() {
		ReversiBoard copy = new ReversiBoard();
		BoardUtil.getPoints(this).stream().forEach(point -> copy.setPoint(point, getPoint(point)));
		return copy;
	}

}
