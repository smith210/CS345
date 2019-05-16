import java.util.*;
import java.io.*;

public class GameTiles{
	private LinkedList<Location> tiles;
	private SceneDeck deck;
	
	GameTiles(){
		tiles = new LinkedList<Location>();
		deck = new SceneDeck();
		initializeTiles();
	}

	public Location get(int ID){
		int curr = 0;
		while(curr != tiles.size() && tiles.get(curr).getID() != ID){
			curr++;
		}
		return tiles.get(curr);
	}
	
	public int size(){ return tiles.size(); }

	public boolean isBoardWrapped(){
		boolean finishedRound = true;
		for(int i = 0; i < tiles.size(); i++){
			int currCounters = tiles.get(i).getSet().getShotCounter();
			if(currCounters != 0){ finishedRound = false; }
		}
		return finishedRound;
	}

	public void drawScenes(){
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).getSet().setScene(deck.drawCard());
		}
	}

	public boolean checkFinishRound(){
		boolean finishRound = true;		
		for(int i = 0; i < tiles.size(); i++){
			Location currTile = tiles.get(i);
			Set film = currTile.getSet();
			if(currTile.isPlayableSet()){
				if(film.getShotCounter() != 0){
					finishRound = false;
				}
			}
		}
		return finishRound;
	
	}

	private int grabID(int ID, int boundary){
		if(ID == boundary){
			if(boundary == 8){
				ID = 1;
			}else{
				ID = 9;
			}
		}else{
			ID = ID + 1;
		}
		return ID;
	}

	private void initializeNeighbors(){		
		int neighborLeft = 2;
		int neighborRight = 8;
		int neighborDown = 9;
		int neighborUp = 0;
		for(int ID = 1; ID <= 12; ID++){
			LinkedList<Location> neighbors = new LinkedList<Location>();
			Location spot = get(ID);
			
			neighbors.add(get(neighborLeft));
			neighbors.add(get(neighborRight));
			neighbors.add(get(neighborDown));
			if(neighborUp != 0){
				neighbors.add(get(neighborUp));
			}
			spot.setNeighbors(neighbors);	

			if(ID < 8){
				neighborLeft = grabID(neighborLeft, 8);
				neighborRight = grabID(neighborRight, 8);
				if(ID % 2 == 0){
					neighborDown = neighborDown + 1;
				}
			}else if (ID == 8){
				neighborLeft = 1;
				neighborRight = 2;
				neighborDown = 12;
				neighborUp = 10;
			}else{
				neighborLeft = neighborLeft + 2;
				neighborRight = neighborRight + 2;
				neighborDown = grabID(neighborDown, 12);
				neighborUp = grabID(neighborUp, 12);
			}
		}
	}
	
	private void initializeTiles(){

		File tileFile = new File("DeadwoodTiles.txt");
		try{
			Scanner scn = new Scanner(tileFile);
			while(scn.hasNextLine()){
				Location loc = new Location();
				String curr = scn.nextLine();
				String[] spliced = curr.split("\\|");
				loc.setID(Integer.parseInt(spliced[0]));
				loc.setName(spliced[1]);
				loc.getSet().setShotCounter(Integer.parseInt(spliced[2]));
				if(Integer.parseInt(spliced[2]) != 0){
					ExtraRole extra = new ExtraRole();
					LinkedList<ExtraRole> listExtras = new LinkedList<ExtraRole>();
					for(int i = 3; i < spliced.length; i++){						
						if(i % 2 != 0){
							extra.setJobTitle(spliced[i]);
						}else{
							extra.setWorkLevel(Integer.parseInt(spliced[i]));
							listExtras.add(extra);
							extra = new ExtraRole();
						}
					}
					loc.getSet().createExtras(listExtras);
				}
				tiles.add(loc);
			}
			initializeNeighbors();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
