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
public interface IStartView {
    public void addHumanGameStartListener(ActionListener listener);
    public void addBotGameStartListener(ActionListener listener);
    public String getBotFirstMove();
    public int getBotDifficulity();
    public void addEnableConnectionsListener(ActionListener listener);
    public boolean connectionsEnabled();
    public void setHostIP(String ip);
    public void setHostPort(String port);
    public void addJoinListener(ActionListener listener);
    public String getPeerIP();
    public String getPeerPort();
    public void setJoinStatus(String status);
    public void start();
}
