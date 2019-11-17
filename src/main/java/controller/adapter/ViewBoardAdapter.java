package controller.adapter;

import static controller.adapter.ViewConversionAdapter.convert;

import java.util.HashMap;
import java.util.Map;

import controller.IViewBoard;
import controller.listener.BoardPointClickedListener;
import controller.listener.MouseHoveredOnPointListener;
import model.Color;
import model.Point;
import view.board.IBoard;

public class ViewBoardAdapter implements IViewBoard {

	private view.board.IBoard board;
	private Map<BoardPointClickedListener, view.board.BoardPointClickedListener> boardPointClickedListenersConversion;
	private Map<MouseHoveredOnPointListener, view.board.MouseHoveredOnPointListener> mouseHoveredOnPointListenersConversion;

	public ViewBoardAdapter(IBoard board) {
		super();
		this.board = board;
		boardPointClickedListenersConversion = new HashMap<>();
		mouseHoveredOnPointListenersConversion = new HashMap<>();
	}

	@Override
	public void setPointColor(Color color, Point point) {
		board.setPointColor(convert(color), convert(point));
	}

	@Override
	public void clearPoint(Point point) {
		board.clearPoint(convert(point));
	}

	@Override
	public void showFlipAnimation(Color beforeFlip, Point point) {
		board.showFlipAnimation(convert(beforeFlip), convert(point));
	}

	@Override
	public void clear() {
		board.clear();
	}

	@Override
	public void setPointTransparentColor(Color color, Point point) {
		board.setPointTransparentColor(convert(color), convert(point));
	}

	@Override
	public void addBoardPointClickedListener(BoardPointClickedListener listener) {
		view.board.BoardPointClickedListener convertedListener = new view.board.BoardPointClickedListener() {
			
			@Override
			public void boardPointClicked(view.board.Point point) {
				listener.boardPointClicked(convert(point));
			}
		};
		boardPointClickedListenersConversion.put(listener, convertedListener);
		board.addBoardPointClickedListener(convertedListener);
	}

	@Override
	public void removeBoardPointClickedListener(BoardPointClickedListener listener) {
		board.removeBoardPointClickedListener(boardPointClickedListenersConversion.get(listener));
		boardPointClickedListenersConversion.remove(listener);
	}

	@Override
	public void highlightPoint(Point point, java.awt.Color color) {
		board.highlightPoint(convert(point), color);
	}

	@Override
	public void removeHighlights() {
		board.removeHighlights();
	}

	@Override
	public void addMouseHoveredOnPointListener(MouseHoveredOnPointListener listener) {
		view.board.MouseHoveredOnPointListener convertedListener = new view.board.MouseHoveredOnPointListener() {
			@Override
			public void mouseHovered(view.board.Point point) {
				listener.mouseHovered(convert(point));
			}
		};
		mouseHoveredOnPointListenersConversion.put(listener, convertedListener);
		board.addMouseHoveredOnPointListener(convertedListener);
	}

}
