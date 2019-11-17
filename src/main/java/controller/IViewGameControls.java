package controller;

import java.awt.event.ActionListener;

import controller.listener.ShowMovesListener;
import controller.listener.SoundEnabledListener;
import model.Color;

public interface IViewGameControls {
    public void setScore(Color player, int score);
    public void setTurn(Color color);
    public void addShowMovesListener(ShowMovesListener listener);
    public void addSoundListener(SoundEnabledListener listener);
    public boolean isSoundEnabled();
    public boolean isShowMovesEnabled();
    public void addResetListener(ActionListener listener);
    public void setResetEnabled(boolean isEnabled);
    public void setHost(Color color);
    public void setGameStatus(String status);
}
