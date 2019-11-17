package model;

import java.io.IOException;

import controller.IStartView;
import controller.StartGameController;
import controller.adapter.StartViewAdapter;

public class Runner {

	public static void main(String[] args) throws IOException, InterruptedException {
		IStartView view = new StartViewAdapter();
		view.start();
		StartGameController controller = new StartGameController(view);
	}
}
