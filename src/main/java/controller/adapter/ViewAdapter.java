package controller.adapter;

import java.awt.event.ActionListener;

import controller.IView;
import controller.IViewAI;
import controller.IViewBoard;
import controller.IViewCommunication;
import controller.IViewGameControls;
import view.ReversiView;

public class ViewAdapter implements IView{
	
	private view.ReversiView view;
	private ViewBoardAdapter boardAdapter;
	private ViewGameControlsAdapter gameControlsAdapter;
	private ViewAIAdapter aiAdapter;
	private ViewCommunicationAdapter communicationAdapter;
	
	public ViewAdapter() {
		super();
		view = new ReversiView();
		boardAdapter = new ViewBoardAdapter(view.getBoard());
		gameControlsAdapter = new ViewGameControlsAdapter(view.getGameControls());
		aiAdapter = new ViewAIAdapter(view.getAI());
		communicationAdapter = new ViewCommunicationAdapter(view.getCommunication());
	}

	@Override
	public IViewBoard getBoard() {
		return boardAdapter;
	}

	@Override
	public IViewCommunication getCommunication() {
		return communicationAdapter;
	}

	@Override
	public IViewGameControls getGameControls() {
		return gameControlsAdapter;
	}

	@Override
	public void start() {
		view.start();
	}

	@Override
	public IViewAI getAIControls() {
		return aiAdapter;
	}

	@Override
	public void addClosedListener(ActionListener listener) {
		view.addClosedListener(listener);
	}
}
