package controller.adapter;

import java.awt.event.ActionListener;

import controller.IViewAI;

public class ViewAIAdapter implements IViewAI{

	private view.IViewAI ai;
	
	public ViewAIAdapter(view.IViewAI ai) {
		super();
		this.ai = ai;
	}

	@Override
	public int getMinimaxDepth() {
		return ai.getMinimaxDepth();
	}

	@Override
	public void addMinimaxDepthChangedListener(ActionListener listener) {
		ai.addMinimaxDepthChangedListener(listener);
	}

	@Override
	public void addMoveForMeListener(ActionListener listener) {
		ai.addMoveMeListener(listener);
	}

	@Override
	public void setMoveForMeEnabled(boolean isEnabled) {
		ai.setMoveMeEnabled(isEnabled);
	}

	@Override
	public void setMinimaxDepthEnabled(boolean isEnabled) {
		ai.setMinimaxDepthEnabled(isEnabled);
	}

	@Override
	public void setMinimaxDepth(int depth) {
		ai.setMinimaxDepth(depth);
	}

}
