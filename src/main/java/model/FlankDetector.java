package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlankDetector implements IFlankDetector {

	BoardPointUtil util = new BoardPointUtil();

	@Override
	public List<Point> findFlankedPoints(IBoard board, Point point, Color occupyingPlayer) {
		
		return findOpponentsPointsThatAreInAStraightLineAndBoundedByPointAndPlayersColors(board, point,
				occupyingPlayer);
	}

	public List<List<Point>> getStraightLinesExtendingPointInAllDirections(IBoard board, Point point) {
		List<List<Point>> straightLinesExtendingPointInAllDirections = new ArrayList<>();
		util.setBoard(board);
		util.setPoint(point);
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingTopOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingBottomOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingLeftOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingRightOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingTopLeftDiagonalOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingTopRightDiagonalOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingBottomLeftDiagonalOfPoint());
		straightLinesExtendingPointInAllDirections.add(util.getPointsExtendingBottomRightDiagonalOfPoint());
		return straightLinesExtendingPointInAllDirections;
	}
	
	public List<Point> findOpponentsPointsThatAreInAStraightLineAndBoundedByPointAndPlayersColors(IBoard board,
			Point point, Color player) {
		util.setBoard(board);
		util.setPoint(point);
		List<Point> opponentPointsBoundedByPointAndPlayer = new ArrayList<>();
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingTopOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingBottomOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingLeftOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingRightOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingTopLeftDiagonalOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingTopRightDiagonalOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingBottomLeftDiagonalOfPoint(), player));
		opponentPointsBoundedByPointAndPlayer.addAll(getConsequtiveOpponentPointsBoundedByPlayerPoint(
				board, util.getPointsExtendingBottomRightDiagonalOfPoint(), player));
		return opponentPointsBoundedByPointAndPlayer;
	}

	public List<Point> getConsequtiveOpponentPointsBoundedByPlayerPoint(IBoard board,
			List<Point> points, Color player) {
		List<Point> consequtiveOpponentPointsBoundedByPlayer = new ArrayList<>();
		List<Color> colorsOccupyingPoints = points.stream().map(point -> board.getPoint(point))
				.collect(Collectors.toList());

		int indexOfFirstUnoccupiedPoint = colorsOccupyingPoints.indexOf(null);
		int indexOfFirstPlayerPoint = colorsOccupyingPoints.indexOf(player);
		int indexOfFirstOpponentPoint = colorsOccupyingPoints.indexOf(player.opponent());

		boolean atleastOnePointOccupiedByOpponentExists = indexOfFirstOpponentPoint != -1;
		boolean atleastOnePointOccupiedByPlayerExists = indexOfFirstPlayerPoint != -1;
		boolean atleastOneUnoccupiedPointExists = indexOfFirstUnoccupiedPoint != -1;

		if (atleastOnePointOccupiedByOpponentExists && atleastOnePointOccupiedByPlayerExists) {
			if (atleastOneUnoccupiedPointExists) {
				if (indexOfFirstOpponentPoint < indexOfFirstPlayerPoint
						&& indexOfFirstPlayerPoint < indexOfFirstUnoccupiedPoint) {
					consequtiveOpponentPointsBoundedByPlayer = points.subList(0,
							indexOfFirstPlayerPoint);
				}
			} else {
				if (indexOfFirstOpponentPoint < indexOfFirstPlayerPoint) {
					consequtiveOpponentPointsBoundedByPlayer = points.subList(0,
							indexOfFirstPlayerPoint);
				}
			}
		}
		return consequtiveOpponentPointsBoundedByPlayer;
	}

}
