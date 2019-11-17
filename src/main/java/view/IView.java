/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import view.board.IBoard;

/**
 *
 * @author Others
 */
public interface IView {
    public IBoard getBoard();
    public IViewCommunication getCommunication();
    public IViewGameControls getGameControls();
    public IViewAI getAI();
    public void start();
    public void addClosedListener(ActionListener listener);
}
