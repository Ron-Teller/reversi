package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import controller.adapter.ViewAdapter;
import controller.network.NetworkPlayer;
import controller.network.OutgoingGameRequest;
import controller.network.OutgoingGameRequestListener;
import controller.network.P2PMessenger;
import model.Color;
import model.FlankDetector;
import model.IBoard;
import model.IFlankDetector;
import model.IMoveFinder;
import model.IRound;
import model.MoveFinder;
import model.ReversiBoard;
import model.Round;
import sound.ISound;
import sound.SoundEffectsPlayer;
import view.game_request.GameRequestOutgoingView;

public class OutgoingGameRequestController {

	private String peerIP;
	private int peerPort;
	private volatile Socket socket;
	private volatile view.game_request.IGameRequestOutgoing view;
	private volatile P2PMessenger messenger;
	
	public OutgoingGameRequestController(String peerIP, int peerPort) {
		super();
		this.peerIP = peerIP;
		this.peerPort = peerPort;
	}
	
	public void requestGame() {
		view = new GameRequestOutgoingView();
		initializeView(view);
		view.setStatus("Attempting to connect to peer...");
		new Thread(new Runnable() {
			public void run() {
				try {
					socket = new Socket(peerIP, peerPort);
					onConnectionToPeerEstablished();
					messenger = new P2PMessenger(socket);
					messenger.start();
					OutgoingGameRequest outgoingRequest = new OutgoingGameRequest(messenger);
					outgoingRequest.addListener(new OutgoingGameRequestListener() {
						
						@Override
						public void hasAccepted(boolean hasAccepted, boolean hostFirstToMove) {
							if (hasAccepted) {
								onRequestAccepted(hostFirstToMove);
							} else {
								onRequestDeclined();
							}
						}
						
						@Override
						public void connectionClosed() {
							onConnectionClosed();
						}
					});
					outgoingRequest.request();
				} catch (IOException e) {
					onFailedToConnectToPeer(view);
				}
			}
		}).start();
	}

	private void onRequestDeclined() {
		view.setStatus("Peer has declined your game request.");
		close();
	}
	
	private void onInitializedGame() {
		view.setGameStarted(true);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		view.close();
	}
	
	private void onRequestAccepted(boolean hostFirstToMove) {
		view.setStatus("Initializing game...");
		view.setAccepted(true);
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				IView gameView = new ViewAdapter();
				gameView.start();
				IFlankDetector flankDetector = new FlankDetector();
				IMoveFinder moveFinder = new MoveFinder(flankDetector);
				Color firstToMove = Color.BLACK;
				Color opponentColor = (hostFirstToMove) ? firstToMove.opponent() : firstToMove;
				IBoard board = new ReversiBoard();
				IRound round = new Round(board, firstToMove, moveFinder, flankDetector);
				ISound sound = new SoundEffectsPlayer();
				NetworkPlayer opponent = new NetworkPlayer(messenger);
				NetworkGameController controller = new NetworkGameController(gameView, round, sound, opponent, opponentColor);	
				onInitializedGame();
			}
		}).start();
	}
	
	private void onConnectionClosed() {
		view.setStatus("Disconnected from peer.");
		close();
	}
	
	private void onConnectionToPeerEstablished() {
		view.setConnection(true);
		view.setStatus("Waiting for peer to accept game request...");
	}
	
	private void onRequestCancledByHost() {
		close();
	}
	
	private void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		view.close();
	}
	
	private void onFailedToConnectToPeer(view.game_request.IGameRequestOutgoing view) {
		view.setStatus("Failed to connect to peer.");
	}
	
	private void initializeView(view.game_request.IGameRequestOutgoing view) {
		view.setAccepted(false);
		view.setConnection(false);
		view.setGameStarted(false);
		view.setPeerIP(peerIP);
		view.setPeerPort(peerPort);
		view.setStatus("");
		view.addCancledListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onRequestCancledByHost();
			}
		});
		view.start();
		
	}
}
