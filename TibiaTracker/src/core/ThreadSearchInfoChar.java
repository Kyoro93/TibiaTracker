package core;

import java.io.IOException;

class ThreadSearchInfoChar implements Runnable {
	private Thread t;
	private String strCharName;

	ThreadSearchInfoChar(String strCharName) {
		this.strCharName = strCharName;
		// System.out.println("Creating: " + strCharName);
	}

	public void run() {
		GetInfos gi = new GetInfos();
		System.out.println(strCharName + ": " + gi.getFullCharacterInfo(strCharName));
		gi = null;
		// System.out.println("Thread " + strCharName + " exiting.");
	}

	public void start() {
		// System.out.println("Starting " + strCharName);
		if (t == null) {
			t = new Thread(this, strCharName);
			t.start();
		}
	}
}
