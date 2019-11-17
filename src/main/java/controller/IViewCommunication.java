package controller;

import controller.listener.MessageSubmitListener;

public interface IViewCommunication {
	public void addMessage(String message);
	public void addMessageSubmitListener(MessageSubmitListener listener);
}
