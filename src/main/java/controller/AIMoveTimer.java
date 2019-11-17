package controller;

import java.util.ArrayList;
import java.util.List;

import controller.listener.AIMoveTimerListener;
import model.IRound;
import model.Point;
import model.ai.ReversiMinimaxMoveCalculator;

public class AIMoveTimer implements Runnable {

	private List<AIMoveTimerListener> listeners = new ArrayList<>();
	private IRound round;
	private int minimaxDepth;
	
	public AIMoveTimer(IRound round, int minimaxDepth) {
		super();
		this.round = round;
		this.minimaxDepth = minimaxDepth;
	}

	public void addListener(AIMoveTimerListener listener) {
		listeners.add(listener);
	}
	
	public void setMininaxDepth(int minimaxDepth) {
		this.minimaxDepth = minimaxDepth;
	}
	
	@Override
	public void run() {
		long minWaitTimeUntilBotMakesMoveMillis = 1000;
		long startTime = System.currentTimeMillis();
		Point point = ReversiMinimaxMoveCalculator.calculateNextBestMove(round, minimaxDepth);
		long endTime = System.currentTimeMillis();
		long timeWaited = endTime-startTime;
		if (timeWaited < minWaitTimeUntilBotMakesMoveMillis) {
			try {
				Thread.sleep(minWaitTimeUntilBotMakesMoveMillis-timeWaited);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		listeners.stream().forEach(listener -> listener.botMove(point));
	}

}
