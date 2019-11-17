package controller;

import java.awt.event.ActionListener;

public interface IView {
    public IViewBoard getBoard();
    public IViewCommunication getCommunication();
    public IViewGameControls getGameControls();
    public IViewAI getAIControls();
    public void start();
    public void addClosedListener(ActionListener listener);
}
