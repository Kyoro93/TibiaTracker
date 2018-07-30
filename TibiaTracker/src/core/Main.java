package core;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		GetInfos gi = new GetInfos();
		
		ArrayList<String> worlds = new ArrayList<String>();
		ArrayList<String> players = new ArrayList<String>();
		
		worlds = gi.getWorldsName();
		
		for(String w : worlds) {
			System.out.println("Getting players for: "+w);
			ArrayList<String> playersWorld = new ArrayList<String>();
			playersWorld = gi.getPlayersByWorld(w);
			System.out.println(playersWorld.size() +" players found");
			players.addAll(playersWorld);
		}
		
		System.out.println(players.size());
		
		/*
		ArrayList<String> CharNames = new ArrayList<String>();
		
		CharNames.add("Kharsek");
		CharNames.add("Lyh");
		CharNames.add("Kyoro");
		CharNames.add("Kyorinho");
		CharNames.add("COnefi");
		for(String c : CharNames) {
			new ControllerBuscaInfoChar(c).start();
		}
		*/
		
	}

}
