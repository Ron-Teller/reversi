package controller.adapter;

import static controller.adapter.ViewConversionAdapter.convert;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import controller.IViewGameControls;
import controller.listener.ShowMovesListener;
import controller.listener.SoundEnabledListener;
import model.Color;

public class ViewGameControlsAdapter implements IViewGameControls {

	private view.IViewGameControls gameControls;
	private Map<SoundEnabledListener, view.SoundEnabledListener> soundEnabledListenerConversion;
	private Map<ShowMovesListener, view.ShowMovesListener> showMovesListenerConversion;

	public ViewGameControlsAdapter(view.IViewGameControls gameControls) {
		super();
		this.gameControls = gameControls;
		soundEnabledListenerConversion = new HashMap<>();
		showMovesListenerConversion = new HashMap<>();
	}

	@Override
	public void setScore(Color player, int score) {
		gameControls.setScore(convert(player), score);
	}

	@Override
	public void setTurn(Color color) {
		gameControls.setTurn(convert(color));
	}

	@Override
	public void addShowMovesListener(ShowMovesListener listener) {
		view.ShowMovesListener convertedListener = new view.ShowMovesListener() {
			@Override
			public void update(boolean showMoves) {
				listener.update(showMoves);
			}
		};
		showMovesListenerConversion.put(listener, convertedListener);
		gameControls.addShowMovesListener(convertedListener);
	}

	@Override
	public void addSoundListener(SoundEnabledListener listener) {
		view.SoundEnabledListener convertedListener = new view.SoundEnabledListener() {
			@Override
			public void update(boolean soundEnabled) {
				listener.update(soundEnabled);
			}
		};
		soundEnabledListenerConversion.put(listener, convertedListener);
		gameControls.addSoundListener(convertedListener);
	}

	@Override
	public boolean isSoundEnabled() {
		return gameControls.isSoundEnabled();
	}

	@Override
	public boolean isShowMovesEnabled() {
		return gameControls.isShowMovesEnabled();
	}

	@Override
	public void addResetListener(ActionListener listener) {
		gameControls.addResetListener(listener);
	}

	@Override
	public void setResetEnabled(boolean isEnabled) {
		gameControls.setResetEnabled(isEnabled);
	}

	@Override
	public void setHost(Color color) {
		gameControls.setHost(convert(color));
	}

	@Override
	public void setGameStatus(String status) {
		gameControls.setGameStatus(status);
	}

}
