package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.validator.routines.InetAddressValidator;

import controller.adapter.ViewAdapter;
import controller.network.P2PMessenger;
import controller.network.Server;
import controller.network.ServerListener;
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

public class StartGameController {

	private IStartView view;
	private Server server;

	public StartGameController(IStartView view) {
		super();
		this.view = view;
		try {
			server = new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addListeners();
	}

	private void addListeners() {
		view.addBotGameStartListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onBotGamePlayClicked();
			}
		});
		view.addEnableConnectionsListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onEnableConnectionsToggled();
			}
		});
		view.addHumanGameStartListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onHumanGamePlayClicked();
			}
		});
		view.addJoinListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onJoinNetworkGameClicked();
			}
		});
	}

	private void onBotGamePlayClicked() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				IView gameView = new ViewAdapter();
				gameView.start();
				IFlankDetector flankDetector = new FlankDetector();
				IMoveFinder moveFinder = new MoveFinder(flankDetector);
				Color firstToMove = Color.BLACK;
				int difficulity = view.getBotDifficulity();
				gameView.getAIControls().setMinimaxDepth(difficulity);
				IBoard board = new ReversiBoard();
				IRound round = new Round(board, firstToMove, moveFinder, flankDetector);
				ISound sound = new SoundEffectsPlayer();
				Color botColor = view.getBotFirstMove().equals("bot") ? firstToMove : firstToMove.opponent();
				BotGameController controller = new BotGameController(gameView, round, sound, botColor);
			}
		}).start();
	}

	private void updateHostAddress() {
		try {
			view.setHostIP(server.getAddress().getLocalHost().getHostAddress());
			view.setHostPort(Integer.toString(server.getPort()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private void enableConnections() {
		try {
			server.start();
			updateHostAddress();
			server.addListener(new ServerListener() {
				@Override
				public void clientConnected(Socket socket) {
					onClientConnected(socket);
				}
			});
			new Thread(new Runnable() {
				public void run() {
					server.listen();
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void onClientConnected(Socket socket) {
		new Thread(new Runnable() {
			public void run() {
				IncomingGameRequestController requestHandler = new IncomingGameRequestController(
						new P2PMessenger(socket));
				requestHandler.handleRequest();
			}
		}).start();
	}

	private void disableConnections() {
		server.stop();
		view.setHostIP(" ");
		view.setHostPort(" ");
	}

	private void onEnableConnectionsToggled() {
		if (view.connectionsEnabled()) {
			enableConnections();
		} else {
			disableConnections();
		}
	}

	private void onHumanGamePlayClicked() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				IView view = new ViewAdapter();
				view.start();
				IFlankDetector flankDetector = new FlankDetector();
				IMoveFinder moveFinder = new MoveFinder(flankDetector);
				Color firstToMove = Color.BLACK;
				IBoard board = new ReversiBoard();
				IRound round = new Round(board, firstToMove, moveFinder, flankDetector);
				ISound sound = new SoundEffectsPlayer();
				GameController controller = new GameController(view, round, sound);
			}
		}).start();
	}

	private boolean peerAddressIsValid() {
		String peerIP = view.getPeerIP();
		String peerPort = view.getPeerPort();
		boolean isValidIP = InetAddressValidator.getInstance().isValid(peerIP);
		boolean isValidPort = true;
		try {
			int num = Integer.parseInt(peerPort);
			if (num < 0 || num > 65535) {
				isValidPort = false;
			}
		} catch (Exception e) {
			isValidPort = false;
		}
		return (isValidIP && isValidPort);
	}

	private void onJoinNetworkGameClicked() {
		if (peerAddressIsValid()) {
			view.setJoinStatus("");
			String peerIP = view.getPeerIP();
			int peerPort = Integer.parseInt(view.getPeerPort());
			OutgoingGameRequestController requester = new OutgoingGameRequestController(peerIP, peerPort);
			new Thread(new Runnable() {
				public void run() {
					requester.requestGame();
				}
			}).start();
		} else {
			view.setJoinStatus("Invalid peer address.");
		}
	}
}
