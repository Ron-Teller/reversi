/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.game_request;

import java.awt.event.ActionListener;

/**
 *
 * @author Others
 */
public interface IGameRequestOutgoing {
    public void setConnection(boolean isConnected);
    public void setAccepted(boolean hasAccepted);
    public void setGameStarted(boolean hasStarted);
    public void setStatus(String status);
    public void setPeerIP(String ip);
    public void setPeerPort(int port);
    public void addCancledListener(ActionListener listener);
    public void close();
    public void start();
}
