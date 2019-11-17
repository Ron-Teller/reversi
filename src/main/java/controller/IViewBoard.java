package controller;

import controller.listener.BoardPointClickedListener;
import controller.listener.MouseHoveredOnPointListener;
import model.Color;
import model.Point;

public interface IViewBoard {
	   public void setPointColor(Color color, Point point);
	   public void clearPoint(Point point);
	   public void showFlipAnimation(Color beforeFlip, Point point);
	   public void clear();
	   public void setPointTransparentColor(Color color, Point point);
	   public void addBoardPointClickedListener(BoardPointClickedListener listener);
	   public void removeBoardPointClickedListener(BoardPointClickedListener listener);
	   public void highlightPoint(Point point, java.awt.Color color);
	   public void removeHighlights();
	   public void addMouseHoveredOnPointListener(MouseHoveredOnPointListener listener);
}
