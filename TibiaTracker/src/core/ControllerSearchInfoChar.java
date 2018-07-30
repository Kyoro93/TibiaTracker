package core;

class ControllerSearchInfoChar implements Runnable {
	private Thread t;
	private String strCharName;

	ControllerSearchInfoChar(String strCharName) {
		this.strCharName = strCharName;
		System.out.println("Creating: " + strCharName);
	}

	public void run() {
		System.out.println("Running " + strCharName);
		GetInfos gi = new GetInfos();
		System.out.println(gi.getFullCharacterInfo(strCharName));
		System.out.println("Thread " + strCharName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + strCharName);
		if (t == null) {
			t = new Thread(this, strCharName);
			t.start();
		}
	}
}
