package core;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		ExecutorService executor = Executors.newFixedThreadPool(3);
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<String> worlds = new ArrayList<String>();
		GetInfos gi = new GetInfos();

		worlds = gi.getWorldsName();
		int total = worlds.size();
		int i = 0;
		for (String w : worlds) {
			ArrayList<String> j = gi.getPlayersByWorld(w);
			players.addAll(j);
			System.out.println(w+": "+ ++i +"/"+total+" - "+(players.size()-j.size())+"+"+j.size()+" = "+players.size());
		}
		
		players.sort(String::compareToIgnoreCase);
		
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
