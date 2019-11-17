package model.ai;

import model.Color;
import model.IRound;
import model.ai.algorithm.State;

public class ReversiState implements State {

	private final IRound round;
	private final Color max;
	
	public ReversiState(IRound round, Color max) {
		super();
		this.round = round;
		this.max = max;
	}

	@Override
	public boolean isMaxTurn() {
		return round.getCurrentPlayer().equals(max);
	}

	public IRound getRound() {
		return round;
	}

	public Color getMax() {
		return max;
	}
}
