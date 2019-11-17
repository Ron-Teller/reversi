package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.adapter.ViewAdapter;
import controller.network.IncomingGameRequest;
import controller.network.IncomingGameRequestListener;
import controller.network.MessageListener;
import controller.network.NetworkPlayer;
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
import view.game_request.GameRequestIncomingView;

public class IncomingGameRequestController {

	private volatile P2PMessenger messenger;
	private volatile view.game_request.IGameRequestIncoming view;
	private volatile IncomingGameRequest incoming;
	private volatile boolean hostMovesFirst;

	public IncomingGameRequestController(P2PMessenger messenger) {
		super();
		this.messenger = messenger;
	}
	
	private void addViewListeners() {
		view.addAcceptListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAccept();
			}
		});
		view.addDeclineListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onDecline();
			}
		});
		
	}
	
	private void onAccept() {
		incoming.accept();
		new Thread(new Runnable() {
			public void run() {
				IView gameView = new ViewAdapter();
				gameView.start();
				IFlankDetector flankDetector = new FlankDetector();
				IMoveFinder moveFinder = new MoveFinder(flankDetector);
				Color firstToMove = Color.BLACK;
				Color opponentColor = (hostMovesFirst) ? firstToMove.opponent() : firstToMove;
				IBoard board = new ReversiBoard();
				IRound round = new Round(board, firstToMove, moveFinder, flankDetector);
				ISound sound = new SoundEffectsPlayer();
				NetworkPlayer opponent = new NetworkPlayer(messenger);
				NetworkGameController controller = new NetworkGameController(gameView, round, sound, opponent, opponentColor);	
			}
		}).start();
	}

	private void onDecline() {
		incoming.decline();
		incoming.cancel();
	}
	
	private void onGameRequestReceived(boolean hostMovesFirst) {
		this.hostMovesFirst = hostMovesFirst;
		new SoundEffectsPlayer().playMessageNotification();
		String peerIP = messenger.getPeerAddress().substring(1).split(":")[0];
		String peerPort = messenger.getPeerAddress().substring(1).split(":")[1];
		view.setRequester("IP: "+peerIP+"    Port: "+peerPort);
		view.start();
	}
	
	private void onConnectionClosed() {
		view.close();
		incoming.cancel();
	}
	
	public void handleRequest() {
		view = new GameRequestIncomingView();
		addViewListeners();
		incoming = new IncomingGameRequest(messenger);
		incoming.addListener(new IncomingGameRequestListener() {
			
			@Override
			public void incomingRequest(boolean hostMovesFirst) {
				onGameRequestReceived(hostMovesFirst);
			}
			
			@Override
			public void connectionClosed() {
				onConnectionClosed();
			}
		});
		incoming.start();
		try {
			messenger.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
