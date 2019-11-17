package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.listener.BoardPointClickedListener;
import controller.listener.MessageSubmitListener;
import controller.listener.MouseHoveredOnPointListener;
import controller.listener.ShowMovesListener;
import controller.listener.SoundEnabledListener;
import model.BoardUtil;
import model.Color;
import model.IBoard;
import model.IRound;
import model.Point;
import model.Scorer;
import model.ai.ReversiAction;
import model.ai.ReversiActionGenerator;
import model.ai.ReversiMinimaxMoveCalculator;
import model.ai.ReversiState;
import model.ai.ReversiStateChanger;
import model.ai.ReversiStaticEvaluationFunction;
import model.ai.algorithm.Minimax;
import sound.ISound;

public class GameController {
	protected IView view;
	protected IRound round;
	protected ISound sound;

	public GameController(IView view, IRound round, ISound sound) {
		super();
		this.view = view;
		this.round = round;
		this.sound = sound;
		initializeView();
	}

	protected void initializeView() {
		addListenersToView();
		updateBoardInView();
		sound.setEnabled(view.getGameControls().isSoundEnabled());
		onNewTurn();
		view.getAIControls().setMinimaxDepthEnabled(false);
		view.getAIControls().setMoveForMeEnabled(true);
	}

	protected void setCurrentPlayerTurnInView() {
		view.getGameControls().setTurn(round.getCurrentPlayer());
	}

	protected void updatePlayersScoresInView() {
		view.getGameControls().setScore(Color.WHITE, Scorer.getScore(round.getBoard(), Color.WHITE));
		view.getGameControls().setScore(Color.BLACK, Scorer.getScore(round.getBoard(), Color.BLACK));
	}

	protected void addListenersToView() {
		view.getBoard().addBoardPointClickedListener(new BoardPointClickedListener() {
			@Override
			public void boardPointClicked(Point point) {
				onBoardPointClicked(point);
			}
		});

		view.getBoard().addMouseHoveredOnPointListener(new MouseHoveredOnPointListener() {
			@Override
			public void mouseHovered(Point point) {
				onMouseHoveredOnPoint(point);
			}
		});

		view.getGameControls().addShowMovesListener(new ShowMovesListener() {
			@Override
			public void update(boolean showMoves) {
				onShowMovesToggled(showMoves);
			}
		});

		view.getGameControls().addSoundListener(new SoundEnabledListener() {
			@Override
			public void update(boolean isEnabled) {
				onSoundToggled(isEnabled);
			}
		});

		view.getGameControls().addResetListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onResetClicked();
			}
		});

		view.getAIControls().addMinimaxDepthChangedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onMinimaxDepthChanged();
			}
		});

		view.getAIControls().addMoveForMeListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onMoveMePressed();
			}
		});

		view.getCommunication().addMessageSubmitListener(new MessageSubmitListener() {
			@Override
			public void submit(String message) {
				onMessageSubmitted(message);
			}
		});

		view.addClosedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onClosed();
			}
		});
	}

	protected void onClosed() {

	}

	protected void onMessageSubmitted(String message) {
	}

	protected void onMoveMePressed() {
		view.getBoard().removeHighlights();
		Point playerMove = ReversiMinimaxMoveCalculator.calculateNextBestMove(round, 5);
		view.getBoard().highlightPoint(playerMove, java.awt.Color.GREEN);
		moveCurrentPlayer(playerMove);
	}

	protected void onMinimaxDepthChanged() {

	}

	protected void onResetClicked() {
		round.reset();
		updateBoardInView();
		onNewTurn();
	}

	protected void onGameEnded() {
		Color winner = Scorer.getLeader(round.getBoard());
		if (winner == null) {
			view.getGameControls().setGameStatus("Draw!");
		} else if (winner.equals(Color.WHITE)) {
			view.getGameControls().setGameStatus("White wins!");
		} else if (winner.equals(Color.BLACK)) {
			view.getGameControls().setGameStatus("Black wins!");
		}
	}

	protected void onSoundToggled(boolean isEnabled) {
		sound.setEnabled(isEnabled);
		sound.playOptionToggle();
	}

	protected void onShowMovesToggled(boolean showMoves) {
		updatePossibleMovesForCurrentPlayerInView();
		sound.playOptionToggle();
	}

	protected void onMouseHoveredOnPoint(Point point) {
		view.getBoard().removeHighlights();
		if (currentPlayerCanOccupyPoint(point)) {
			sound.playHoverOnOccupiablePoint();
			view.getBoard().highlightPoint(point, java.awt.Color.GREEN);
			hightlightFlankedPoints(point);
		}
	}

	protected void hightlightFlankedPoints(Point point) {
		List<Point> flankedPoints = round.getFlankDetector().findFlankedPoints(round.getBoard(), point,
				round.getCurrentPlayer());
		flankedPoints.stream()
				.forEach(flankedPoint -> view.getBoard().highlightPoint(flankedPoint, java.awt.Color.BLUE));
	}

	protected void onBoardPointClicked(Point point) {
		if (currentPlayerCanOccupyPoint(point)) {
			beforeMovePlayer(point);
			moveCurrentPlayer(point);
		}
	}

	protected void beforeMovePlayer(Point point) {

	}

	protected void showFlipAnimationForFlankedPoints(Point point) {
		List<Point> flankedPoints = round.getFlankDetector().findFlankedPoints(round.getBoard(), point,
				round.getCurrentPlayer());
		flankedPoints.stream().forEach(
				flankedPoint -> view.getBoard().showFlipAnimation(round.getCurrentPlayer().opponent(), flankedPoint));

	}

	protected void moveCurrentPlayer(Point point) {
		sound.playPlayerMove();
		removePossibleMovesForCurrentPlayerInView();
		view.getBoard().setPointColor(round.getCurrentPlayer(), point);
		showFlipAnimationForFlankedPoints(point);
		messagePlayerMoved(point);
		round.makeMove(point);
		onNewTurn();
	}

	private void messagePlayerMoved(Point point) {
		// view.getCommunication().addMessage(round.getCurrentPlayer().toString()+"
		// - moved to "+point);
	}

	public void onNewTurn() {
		updatePlayersScoresInView();
		setCurrentPlayerTurnInView();
		updatePossibleMovesForCurrentPlayerInView();
		updateGameStatus();
		if (round.hasEnded()) {
			onGameEnded();
		}
	}

	protected void updateGameStatus() {
		if (!round.hasEnded()) {
			updateTurnStatus();
		} else {
			setEndGameStatus();
		}
	}

	protected void updateTurnStatus() {
		String current = round.getCurrentPlayer().toString();
		view.getGameControls().setGameStatus(current.substring(0, 1).toUpperCase() + current.substring(1).toLowerCase()+ " turn!");
	}

	protected void setEndGameStatus() {
		Color winner = Scorer.getLeader(round.getBoard());
		if (winner == null) {
			view.getGameControls().setGameStatus("Draw!");
		} else {
			view.getGameControls().setGameStatus(winner.toString().substring(0, 1).toUpperCase() + winner.toString().substring(1)+" wins!");
		}
	}

	protected boolean currentPlayerCanOccupyPoint(Point point) {
		return round.getMoveFinder().getOccupiablePoints(round.getBoard(), round.getCurrentPlayer()).contains(point);
	}

	protected void updateBoardInView() {
		IBoard board = round.getBoard();
		view.getBoard().clear();
		BoardUtil.getOccupiedPoints(board).stream()
				.forEach(point -> view.getBoard().setPointColor(board.getPoint(point), point));
	}

	protected void updatePossibleMovesForCurrentPlayerInView() {
		if (view.getGameControls().isShowMovesEnabled()) {
			addPossibleMovesForCurrentPlayerInView();
		} else {
			removePossibleMovesForCurrentPlayerInView();
		}
	}

	protected void addPossibleMovesForCurrentPlayerInView() {
		round.getMoveFinder().getOccupiablePoints(round.getBoard(), round.getCurrentPlayer()).stream()
				.forEach(point -> view.getBoard().setPointTransparentColor(round.getCurrentPlayer(), point));
	}

	protected void removePossibleMovesForCurrentPlayerInView() {
		round.getMoveFinder().getOccupiablePoints(round.getBoard(), round.getCurrentPlayer()).stream()
				.forEach(point -> view.getBoard().clearPoint(point));
	}

}
