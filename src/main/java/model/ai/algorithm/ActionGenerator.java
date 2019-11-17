package model.ai.algorithm;

import java.util.List;

public interface ActionGenerator {
	public List<Action> getActions(State state);
}
