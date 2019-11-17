package model.ai;

import model.IRound;
import model.Point;
import model.ai.algorithm.Minimax;

public class ReversiMinimaxMoveCalculator {
	public static Point calculateNextBestMove(IRound round, int minimaxDepth) {
		ReversiStateChanger stateChanger = new ReversiStateChanger();
		ReversiActionGenerator actionGenerator = new ReversiActionGenerator();
		ReversiStaticEvaluationFunction staticEvalutionFunction = new ReversiStaticEvaluationFunction();
		ReversiState state = new ReversiState(round.copy(), round.getCurrentPlayer());
		Minimax minimax = new Minimax(minimaxDepth, stateChanger, actionGenerator, staticEvalutionFunction);
		ReversiAction action = (ReversiAction) minimax.run(state);
		return action.getPointOccupied();
	}
}
