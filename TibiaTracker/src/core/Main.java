package core;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		/*
		GetInfos gi = new GetInfos();
		
		ArrayList<String> allWorlds = new ArrayList<String>();
		ArrayList<String> players = new ArrayList<String>();
		
		allWorlds = gi.getWorldsName();
		
		for(String w : allWorlds) {
			players = gi.getPlayersByWorld(w);
			int quantPlayers = players.size();
			int i = 0;
			for(String c : players) {
				System.out.println(++i+"/"+quantPlayers);
				System.out.println(gi.getFullCharacterInfo(c));
			}
		}
		*/
		
		ExecutorService executor = Executors.newFixedThreadPool(6);
        ArrayList<String> players = new ArrayList<String>();
        ArrayList<String> worlds = new ArrayList<String>();
        GetInfos gi = new GetInfos();
        
        worlds = gi.getWorldsName();
        for(String w : worlds) {
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
