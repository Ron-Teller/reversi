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
public interface IGameRequestIncoming {
    public void addAcceptListener(ActionListener listener);
    public void addDeclineListener(ActionListener listener);
    public void close();
    public void start();
    public void setRequester(String requester);
}
