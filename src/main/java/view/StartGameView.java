/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Others
 */
public class StartGameView extends StartGameFrame implements IStartView{

    private List<ActionListener> humanGameStartListeners = new ArrayList<>();
    private List<ActionListener> botGameStartListeners = new ArrayList<>();
    private List<ActionListener> enableConnectionsListeners = new ArrayList<>();
    private List<ActionListener> networkJoinListeners = new ArrayList<>();

    @Override
    public String getBotFirstMove() {
        return (firstMoveBotRadio.isSelected()) ? "bot" : "human";
    }

    @Override
    public int getBotDifficulity() {
        return difficulitySlider.getValue();
    }

    @Override
    public void addEnableConnectionsListener(ActionListener listener) {
        enableConnectionsListeners.add(listener);
    }

    @Override
    public boolean connectionsEnabled() {
        return enableConnection.isSelected();
    }

    @Override
    public void setHostIP(String ip) {
        hostIP.setText(ip);
    }

    @Override
    public void setHostPort(String port) {
        hostPort.setText(port);
    }

    @Override
    public void addJoinListener(ActionListener listener) {
        networkJoinListeners.add(listener);
    }

    @Override
    public String getPeerIP() {
        return peerIP.getText();
    }

    @Override
    public String getPeerPort() {
        return peerPort.getText();
    }

    @Override
    public void setJoinStatus(String status) {
        joinStatusLabel.setText(status);
    }

    @Override
    public void addHumanGameStartListener(ActionListener listener) {
        humanGameStartListeners.add(listener);
    }

    @Override
    public void addBotGameStartListener(ActionListener listener) {
        botGameStartListeners.add(listener);
    }

    @Override
    protected void onBotPlay() {
        botGameStartListeners.forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    protected void onHumanPlay() {
        humanGameStartListeners.forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    protected void onEnableConnectionsToggled() {
        enableConnectionsListeners.forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    protected void onJoinNetworkGame() {
        networkJoinListeners.forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    public void start() {
        this.setVisible(true);
    }
    
}
