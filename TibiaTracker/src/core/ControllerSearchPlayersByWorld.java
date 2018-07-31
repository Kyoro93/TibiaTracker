package core;

import java.util.ArrayList;

class ControllerSearchPlayersByWorld implements Runnable {
	private Thread t;
	private String strWorldName;

	ControllerSearchPlayersByWorld(String strWorldName) {
		this.strWorldName = strWorldName;
		//System.out.println("Creating: " + strWorldName);
	}

	public void run() {
		//System.out.println("Running " + strWorldName);
		ArrayList<String> players = new ArrayList<String>();
		GetInfos gi = new GetInfos();
		players = gi.getPlayersByWorld(strWorldName);
		for(String c : players) {
			System.out.println(gi.getFullCharacterInfo(c));
		}
		//System.out.println("Thread " + strWorldName + " exiting.");
	}

	public void start() {
		//System.out.println("Starting " + strWorldName);
		if (t == null) {
			t = new Thread(this, strWorldName);
			t.start();
		}
	}
}

