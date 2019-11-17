package controller;

import controller.listener.AIMoveTimerListener;
import model.Color;
import model.IRound;
import model.Point;
import model.Scorer;
import sound.ISound;

public class BotGameController extends GameController {

	private Color bot;
	private int minimaxDepth = 5;
	
	public BotGameController(IView view, IRound round, ISound sound, Color bot) {
		super(view, round, sound);
		this.bot = bot;
		view.getAIControls().setMinimaxDepthEnabled(true);
		updateMinimaxDepth();
		onNewTurn();
	}

	@Override
	public void onNewTurn() {
		super.onNewTurn();
		if (isBotsTurn()) {
			view.getAIControls().setMoveForMeEnabled(false);
			moveBot();
		} else {
			view.getAIControls().setMoveForMeEnabled(true);
		}
	}
	
	@Override
	protected void updateTurnStatus() {
		if (isBotsTurn()) {
			view.getGameControls().setGameStatus("Bots turn!");
		} else {
			view.getGameControls().setGameStatus("Your turn!");
		}
	}
	
	@Override
	protected void setEndGameStatus() {
		Color winner = Scorer.getLeader(round.getBoard());
		if (winner == null) {
			view.getGameControls().setGameStatus("Draw!");
		} else if (winner.equals(bot)) {
			view.getGameControls().setGameStatus("You lose.");
		} else if (winner.equals(bot.opponent())) {
			view.getGameControls().setGameStatus("You Win!");
		}
	}
	
	public void moveBot() {
		AIMoveTimer timer = new AIMoveTimer(round,minimaxDepth);
		timer.addListener(new AIMoveTimerListener() {
			@Override
			public void botMove(Point point) {
				moveCurrentPlayer(point);
			}
		});
		Thread thread = new Thread(timer);
		thread.start();
	}
	
	private boolean isBotsTurn() {
		return round.getCurrentPlayer().equals(bot);
	}
	
	@Override
	protected void onBoardPointClicked(Point point) {
		if (!isBotsTurn()) {
			super.onBoardPointClicked(point);
		}
	}
	
	@Override
	protected void onMinimaxDepthChanged() {
		super.onMinimaxDepthChanged();
		updateMinimaxDepth();
	}
	
	private void updateMinimaxDepth() {
		minimaxDepth = view.getAIControls().getMinimaxDepth();
	}
}
