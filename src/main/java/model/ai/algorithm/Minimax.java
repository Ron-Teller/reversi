package model.ai.algorithm;

import java.util.List;

public class Minimax {

	private int maxDepth;
	private StateChanger stateChanger;
	private ActionGenerator actionGenerator;
	private StaticEvaluationFunction staticEvalutionFunction;
	private Action bestAction;

	public Minimax(int maxDepth, StateChanger stateChanger, ActionGenerator actionGenerator,
			StaticEvaluationFunction staticEvalutionFunction) {
		super();
		this.maxDepth = maxDepth;
		this.stateChanger = stateChanger;
		this.actionGenerator = actionGenerator;
		this.staticEvalutionFunction = staticEvalutionFunction;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public Action run(State state) {
//		minimax(0, state);
		alphabeta(0, state, null, null);
		return bestAction;
	}

	private double minimax(int depth, State state) {
		Double bestScore = null;
		List<Action> actions = actionGenerator.getActions(state);
		Action bestAction = null;
		State changedState;
		double branchScore;

		if (depth == maxDepth || actions.isEmpty()) {
			bestScore = staticEvalutionFunction.evaluate(state);
		}

		else if (state.isMaxTurn()) {
			for (Action action : actions) {
				changedState = stateChanger.change(state, action);
				branchScore = minimax(depth + 1, changedState);
				if (bestScore == null) {
					bestScore = branchScore;
					bestAction = action;
				} else {
					if (branchScore >= bestScore) {
						bestScore = branchScore;
						bestAction = action;
					}
				}
			}
		} else {
			for (Action action : actions) {
				changedState = stateChanger.change(state, action);
				branchScore = minimax(depth + 1, changedState);
				if (bestScore == null) {
					bestScore = branchScore;
					bestAction = action;
				} else {
					if (branchScore < bestScore) {
						bestScore = branchScore;
						bestAction = action;
					}
				}
			}
		}
		if (depth == 0) {
			this.bestAction = bestAction;
		}
		return bestScore;
	}

	private double alphabeta(int depth, State state, Double _alpha, Double _beta) {
		// Finds best game move and places it in object global variable
		Double bestScore = null;
		Double alpha;
		Double beta;
		// Make new reference to alpha so recursive calls wont change
		// same variable
		if (_alpha == null) {
			alpha = null;
		} else {
			alpha = new Double(_alpha.doubleValue());
		}

		if (_beta == null) {
			beta = null;
		} else {
			beta = new Double(_beta.doubleValue());
		}

		double childScore;
		List<Action> actions = actionGenerator.getActions(state);
		Action best = null;
		State changedState;

		// If level is a node return static evaluation function or no moves left
		if (depth == maxDepth || actions.isEmpty()) {
			bestScore = staticEvalutionFunction.evaluate(state);
		}

		// If current level is calculating maximum move for player
		else if (state.isMaxTurn()) {
			for (Action action : actions) {
				changedState = stateChanger.change(state, action);
				childScore = alphabeta(depth + 1, changedState, alpha, beta);
				if (bestScore == null) {
					bestScore = childScore;
					best = action;
				} else {
					if (childScore >= bestScore) {
						bestScore = childScore;
						best = action;
					}
				}

				if (alpha == null) {
					alpha = new Double(bestScore.doubleValue());
				} else if (alpha.compareTo(bestScore) < 0) {
					alpha = new Double(bestScore.doubleValue());
				}

				// If beta <= alpha
				if (beta != null) {
					if (alpha.compareTo(beta) >= 0) {
						break;
					}
				}
			}
		}

		// If current level is calculating minimum move for opponent
		else {
			for (Action action : actions) {
				changedState = stateChanger.change(state, action);
				childScore = alphabeta(depth + 1, changedState, alpha, beta);
				if (bestScore == null) {
					bestScore = childScore;
					best = action;
				} else {
					if (childScore < bestScore) {
						bestScore = childScore;
						best = action;
					}
				}
				if (beta == null) {
					beta = new Double(bestScore.doubleValue());
				} else if (beta.compareTo(bestScore) > 0) {
					beta = new Double(bestScore.doubleValue());
				}

				// If beta <= alpha
				if (alpha != null) {
					if (alpha.compareTo(beta) >= 0) {
						break;
					}
				}
			}
		}

		if (depth == 0) {
			this.bestAction = best;
		}

		return bestScore;
	}
}
