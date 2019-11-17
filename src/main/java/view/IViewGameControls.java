/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;

/**
 *
 * @author Others
 */
public interface IViewGameControls {
    public void setScore(Color player, int score);
    public void setTurn(Color color);
    public void setHost(Color color);
    public void setGameStatus(String status);
    public void addShowMovesListener(ShowMovesListener listener);
    public void addSoundListener(SoundEnabledListener listener);
    public boolean isSoundEnabled();
    public boolean isShowMovesEnabled();
    public void addResetListener(ActionListener listener);
    public void setResetEnabled(boolean isEnabled);
}
