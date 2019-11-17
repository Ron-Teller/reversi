package controller.adapter;

import java.util.HashMap;
import java.util.Map;

import controller.IViewCommunication;
import controller.listener.MessageSubmitListener;

public class ViewCommunicationAdapter implements IViewCommunication {

	private view.IViewCommunication communication;
	private Map<MessageSubmitListener, view.MessageSubmitListener> messageSubmitListenersConversion;
	
	public ViewCommunicationAdapter(view.IViewCommunication communication) {
		super();
		this.communication = communication;
		messageSubmitListenersConversion = new HashMap<>();
	}

	@Override
	public void addMessage(String message) {
		communication.addMessage(message);
	}

	@Override
	public void addMessageSubmitListener(MessageSubmitListener listener) {
		view.MessageSubmitListener convertedListener = new view.MessageSubmitListener() {
			@Override
			public void submit(String message) {
				listener.submit(message);
			}
		};
		messageSubmitListenersConversion.put(listener, convertedListener);
		communication.addMessageSubmitListener(convertedListener);
	}

}
