package model;

import java.util.ArrayList;
import java.util.List;

public class BoardPointUtil {

	private IBoard board;
	private Point point;

	public void setBoard(IBoard board) {
		this.board = board;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<Point> getPointsExtendingTopLeftDiagonalOfPoint() {
		int rowIncrease = -1;
		int colomnIncrease = -1;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);
	}

	public List<Point> getPointsExtendingTopRightDiagonalOfPoint() {
		int rowIncrease = -1;
		int colomnIncrease = 1;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);
	}

	public List<Point> getPointsExtendingBottomLeftDiagonalOfPoint() {
		int rowIncrease = 1;
		int colomnIncrease = -1;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);		
	}

	public List<Point> getPointsExtendingBottomRightDiagonalOfPoint() {
		int rowIncrease = 1;
		int colomnIncrease = 1;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);		
	}
	
	public List<Point> getPointsExtendingTopOfPoint() {
		int rowIncrease = -1;
		int colomnIncrease = 0;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);		
	}
	
	public List<Point> getPointsExtendingBottomOfPoint() {
		int rowIncrease = 1;
		int colomnIncrease = 0;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);		
	}		
	
	public List<Point> getPointsExtendingRightOfPoint() {
		int rowIncrease = 0;
		int colomnIncrease = 1;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);		
	}	
	
	public List<Point> getPointsExtendingLeftOfPoint() {
		int rowIncrease = 0;
		int colomnIncrease = -1;
		return getPointsExtendingPointInStraightLine(rowIncrease,colomnIncrease);		
	}		
	
	private List<Point> getPointsExtendingPointInStraightLine(int rowIncrease, int colomnIncrease) {
		Point extendingPoint = new Point(point.getRow()+rowIncrease, point.getColomn()+colomnIncrease);
		List<Point> extendingPoints = new ArrayList<>();
		while (board.inBound(extendingPoint)) {
			extendingPoints.add(extendingPoint);
			extendingPoint = new Point(extendingPoint.getRow()+rowIncrease, extendingPoint.getColomn()+colomnIncrease);
		}
		return extendingPoints;
	}
}
