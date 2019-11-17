/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.game_request;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author Others
 */
public class GameRequestOutgoingView extends GameRequestOutgoingFrame implements IGameRequestOutgoing {

    private GameRequestIconFactory iconFactory = new GameRequestIconFactory();
    private List<ActionListener> cancledListeners = new ArrayList<>();
    
    private Icon getConnectionIcon(boolean isConnected) {
        return (isConnected == true) ? iconFactory.getConnectedIcon() : iconFactory.getNotConnectedIcon();
    }
    
    private Icon getAcceptionIcon(boolean hasAccepted) {
        return (hasAccepted == true) ? iconFactory.getGameRequestAcceptedIcon() : iconFactory.getAwaitingGameRequestReplyIcon();
    }
    
    private Icon getGameIcon(boolean hasStarted) {
        return (hasStarted == true) ? iconFactory.getGameStartedIcon() : iconFactory.getGameNotStartedIcon();
    }
    
    @Override
    public void setConnection(boolean isConnected) {
        connectionLabel.setIcon(getConnectionIcon(isConnected));
    }

    @Override
    public void setAccepted(boolean hasAccepted) {
        acceptionLabel.setIcon(getAcceptionIcon(hasAccepted));
    }

    @Override
    public void setGameStarted(boolean hasStarted) {
        gameLabel.setIcon(getGameIcon(hasStarted));
    }

    @Override
    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    @Override
    public void setPeerIP(String ip) {
        peerIP.setText(ip);
    }

    @Override
    public void setPeerPort(int port) {
        peerPort.setText(Integer.toString(port));
    }

    @Override
    public void addCancledListener(ActionListener listener) {
        
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void start() {
        this.setVisible(true);
    }
    
}
