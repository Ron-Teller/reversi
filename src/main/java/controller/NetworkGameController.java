package controller;

import java.io.IOException;

import controller.network.INetworkPlayer;
import controller.network.NetworkOpponentListener;
import model.Color;
import model.IRound;
import model.Point;
import model.Scorer;
import sound.ISound;

public class NetworkGameController extends GameController {

	private INetworkPlayer opponent;
	private Color opponentColor;
	
	public NetworkGameController(IView view, IRound round, ISound sound, INetworkPlayer opponent, Color opponentColor) {
		super(view, round, sound);
		this.opponent = opponent;
		this.opponentColor = opponentColor;
		view.getGameControls().setResetEnabled(false);
		view.getAIControls().setMoveForMeEnabled(false);
		addListenersToNetworkOpponent();
		view.getCommunication().addMessage(opponent.getAddress().substring(1)+" has joined the game!");
		updateGameStatus();
		view.getGameControls().setHost(opponentColor.opponent());
	}
	
	private void addListenersToNetworkOpponent() {
		opponent.addListener(new NetworkOpponentListener() {
			
			@Override
			public void reset() {
				onOpponentReset();				
			}
			
			@Override
			public void moved(Point point) {
				onOpponentMoved(point);
			}
			
			@Override
			public void disconnected() {
				onOpponentDisconnected();
			}

			@Override
			public void message(String message) {
				onOpponentMessage(message);
			}
		});
	}
	
	private void onOpponentMessage(String message) {
		sound.playMessageNotification();
		view.getCommunication().addMessage("Opponent: "+message);
	}
	
	private void onOpponentReset() {
		super.onResetClicked();
		view.getGameControls().setResetEnabled(false);
	}
	
	private void onOpponentMoved(Point point) {
		if (isOpponentsTurn()) {
			moveCurrentPlayer(point);
		}
	}
	
	private void onOpponentDisconnected() {
		view.getCommunication().addMessage("Opponent has disconnected.");
	}
	
	@Override
	protected void setEndGameStatus() {
		Color winner = Scorer.getLeader(round.getBoard());
		if (winner == null) {
			view.getGameControls().setGameStatus("Draw!");
		} else if (winner.equals(opponentColor)) {
			view.getGameControls().setGameStatus("You lose.");
		} else if (winner.equals(opponentColor.opponent())) {
			view.getGameControls().setGameStatus("You Win!");
		}
	}
	
	@Override
	protected void updateTurnStatus() {
		if (isOpponentsTurn()) {
			view.getGameControls().setGameStatus("Opponents turn");
		} else {
			view.getGameControls().setGameStatus("Your turn!");
		}
	}
	
	@Override
	protected void onResetClicked() {
		super.onResetClicked();
		try {
			opponent.sendResetRequest();
			view.getGameControls().setResetEnabled(false);
		} catch (IOException e) {
			e.printStackTrace();
			onConnectionToPeerLost();
		}
	}

	@Override
	protected void onBoardPointClicked(Point point) {
		if (!isOpponentsTurn()) {
			super.onBoardPointClicked(point);
		}
	}
	
	@Override
	protected void beforeMovePlayer(Point point) {
		try {
			opponent.sendMoveRequest(point);
		} catch (IOException e) {
			e.printStackTrace();
			onConnectionToPeerLost();
		}
	}
	@Override
	protected void onGameEnded() {
		view.getGameControls().setResetEnabled(true);
	}
	
	private boolean isOpponentsTurn() {
		return (round.getCurrentPlayer().equals(opponentColor));
	}
	
	private void onConnectionToPeerLost() {
		
	}
	
	@Override
	protected void onClosed() {
		super.onClosed();
		try {
			opponent.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			onConnectionToPeerLost();
		}
	}
	
	@Override
	protected void onMessageSubmitted(String message) {
		super.onMessageSubmitted(message); 
		view.getCommunication().addMessage("You: "+message);
		opponent.sendMessage(message);
	}
	
}
