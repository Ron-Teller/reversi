/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Others
 */
public interface IViewCommunication {
    public void addMessage(String message);
    public void addMessageSubmitListener(MessageSubmitListener listener);
    public void setStatusBar(String status);
}
