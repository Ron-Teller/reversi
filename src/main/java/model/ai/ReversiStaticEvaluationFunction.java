package model.ai;

import model.Scorer;
import model.ai.algorithm.State;
import model.ai.algorithm.StaticEvaluationFunction;

public class ReversiStaticEvaluationFunction implements StaticEvaluationFunction {

	@Override
	public double evaluate(State state) {
		ReversiState reversiState = (ReversiState) state;
		return Scorer.getScore(reversiState.getRound().getBoard(), reversiState.getMax());
	}

}
