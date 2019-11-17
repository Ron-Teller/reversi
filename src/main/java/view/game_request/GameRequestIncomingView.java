/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.game_request;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Others
 */
public class GameRequestIncomingView extends GameRequestIncomingFrame implements IGameRequestIncoming{

    private List<ActionListener> acceptListeners = new ArrayList<>();
    private List<ActionListener> declineListeners = new ArrayList<>();
    
    @Override
    public void addAcceptListener(ActionListener listener) {
        acceptListeners.add(listener);
    }

    @Override
    public void addDeclineListener(ActionListener listener) {
        declineListeners.add(listener);
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void setRequester(String requester) {
        this.requester.setText(requester);
    }

    @Override
    protected void onAccept() {
        acceptListeners.forEach(listener -> listener.actionPerformed(null));
    }

    @Override
    protected void onDecline() {
        declineListeners.forEach(listener -> listener.actionPerformed(null));
    }
    
}
