package controller.adapter;

import model.Color;
import model.Point;

public class ViewConversionAdapter {
	
	public static view.board.Point convert(Point point) {
		if (point == null) {
			return null;
		}
		return new view.board.Point(point.getRow(), point.getColomn());
	}
	
	public static view.Color convert(Color color) {
		if (color == null) {
			return null;
		}
		return (color.equals(Color.WHITE)) ? view.Color.WHITE : view.Color.BLACK;
	}
	
	public static Color convert(view.Color color) {
		if (color == null) {
			return null;
		}
		return (color.equals(Color.WHITE)) ? Color.WHITE : Color.BLACK;
	}
	
	public static Point convert(view.board.Point point) {
		if (point == null) {
			return null;
		}
		return new Point(point.getRow(), point.getColomn());
	}	
}
