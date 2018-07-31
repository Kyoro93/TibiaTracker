package core;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
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
		
		
		
		
	}

}
