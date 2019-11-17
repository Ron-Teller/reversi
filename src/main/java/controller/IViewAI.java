package controller;

import java.awt.event.ActionListener;

public interface IViewAI {
	public int getMinimaxDepth();
	public void addMinimaxDepthChangedListener(ActionListener listener);
	public void addMoveForMeListener(ActionListener listener);
	public void setMoveForMeEnabled(boolean isEnabled);
	public void setMinimaxDepthEnabled(boolean isEnabled);
	public void setMinimaxDepth(int depth);
}
