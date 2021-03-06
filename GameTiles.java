import java.util.*;
import java.io.*;

public class GameTiles{
	private LinkedList<Location> tiles;
	
	GameTiles(){
		tiles = new LinkedList<Location>();
		initializeTiles();//generate the locations and extra actors
	}

	public void resetBoard(){ 
		for(int i = 0; i < tiles.size(); i++){
			Location curr = tiles.get(i);
			curr.getSet().setScene(new Scene());
		}		
	 }

	public Location get(int ID){//get the location at certain ID
		int curr = 0;
		while(curr != tiles.size() && tiles.get(curr).getID() != ID){
			curr++;
		}
		return tiles.get(curr);
	}
	
	public int size(){ return tiles.size(); }

	public boolean isBoardWrapped(){ //check if all locations have no shot counters
		int curr = 0;		
		while(curr != tiles.size() && tiles.get(curr).getSet().getShotCounter() != 0){
			curr++;
		}
		if(curr == tiles.size()){
			return true;
		}else{
			return false;
		}
	}

	public void drawScenes(SceneDeck deck){ //set scene to a location
		for(int i = 0; i < tiles.size(); i++){
			int ID = tiles.get(i).getID();
			if(ID != 4 && ID != 8){				
				tiles.get(i).getSet().setScene(deck.drawCard());
			}
		}
	}

	public boolean checkFinishRound(){ //check if round has finished
		boolean finishRound = true;		
		for(int i = 0; i < tiles.size(); i++){
			Location currTile = tiles.get(i);
			Set film = currTile.getSet();
			if(film.getShotCounter() != 0){
				finishRound = false;
			}
		}
		return finishRound;
	
	}

	private int grabID(int ID, int boundary){ //initializeNeighbors helper
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

	private void initializeNeighbors(){	//creates the neighbor relation
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
	
	private void initializeTiles(){//parse through locations

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
