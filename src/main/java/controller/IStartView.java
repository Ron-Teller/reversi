package controller;

import java.awt.event.ActionListener;

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
