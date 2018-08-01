package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		ExecutorService executor = Executors.newFixedThreadPool(6);
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<String> worlds = new ArrayList<String>();
		GetInfos gi = new GetInfos();

		worlds = gi.getWorldsName();
		for (String w : worlds) {
			players.addAll(gi.getPlayersByWorld(w));
			System.out.println(players.size());
		}
		for (String p : players) {
			Runnable worker = new ThreadSearchInfoChar(p);
			executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);

	}

}
