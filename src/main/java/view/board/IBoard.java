/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.board;

import view.Color;

/**
 *
 * @author Others
 */
public interface IBoard {
   public void setPointColor(Color color, Point point);
   public void clearPoint(Point point);
   public void showFlipAnimation(Color color, Point point);
   public void clear();
   public void setPointTransparentColor(Color color, Point point);
   public void addBoardPointClickedListener(BoardPointClickedListener listener);
   public void removeBoardPointClickedListener(BoardPointClickedListener listener);
   public void highlightPoint(Point point, java.awt.Color color);
   public void removeHighlights();
   public void addMouseHoveredOnPointListener(MouseHoveredOnPointListener listener);
}
