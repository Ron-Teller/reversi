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
public interface IViewAI {
    public int getMinimaxDepth();
    public void addMinimaxDepthChangedListener(ActionListener listener);
    public void addMoveMeListener(ActionListener listener);
    public void setMoveMeEnabled(boolean isEnabled);
    public void setMinimaxDepthEnabled(boolean isEnabled);
    public void setMinimaxDepth(int depth);
}
