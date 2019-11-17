package controller.adapter;

import java.awt.event.ActionListener;

import controller.IStartView;
import view.StartGameView;

public class StartViewAdapter implements IStartView {

	private view.IStartView view;
	
	public StartViewAdapter() {
		super();
		view = new StartGameView();
	}

	@Override
	public void addHumanGameStartListener(ActionListener listener) {
		view.addHumanGameStartListener(listener);
	}

	@Override
	public void addBotGameStartListener(ActionListener listener) {
		view.addBotGameStartListener(listener);
	}

	@Override
	public String getBotFirstMove() {
		return view.getBotFirstMove();
	}

	@Override
	public int getBotDifficulity() {
		return view.getBotDifficulity();
	}

	@Override
	public void addEnableConnectionsListener(ActionListener listener) {
		view.addEnableConnectionsListener(listener);
	}

	@Override
	public boolean connectionsEnabled() {
		return view.connectionsEnabled();
	}

	@Override
	public void setHostIP(String ip) {
		view.setHostIP(ip);
	}

	@Override
	public void setHostPort(String port) {
		view.setHostPort(port);
	}

	@Override
	public void addJoinListener(ActionListener listener) {
		view.addJoinListener(listener);
	}

	@Override
	public String getPeerIP() {
		return view.getPeerIP();
	}

	@Override
	public String getPeerPort() {
		return view.getPeerPort();
	}

	@Override
	public void setJoinStatus(String status) {
		view.setJoinStatus(status);
	}

	@Override
	public void start() {
		view.start();
	}

}
