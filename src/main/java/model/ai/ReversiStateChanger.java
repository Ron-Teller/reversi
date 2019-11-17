package model.ai;

import model.IRound;
import model.ai.algorithm.Action;
import model.ai.algorithm.State;
import model.ai.algorithm.StateChanger;

public class ReversiStateChanger implements StateChanger {

	@Override
	public State change(State state, Action action) {
		ReversiState reversiState = (ReversiState) state;
		ReversiAction reversiAction = (ReversiAction) action;
		IRound round = reversiState.getRound().copy();
		round.makeMove(reversiAction.getPointOccupied());
		return new ReversiState(round, reversiState.getMax());
	}

}
