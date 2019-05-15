import java.util.*;

public class Board{
	private LinkedList<Player> users;
	private LinkedList<Location> tiles;
	private int round;
	private int display;
	
	Board(int playerNum){
		users = new LinkedList<Player>();
		createPlayers(playerNum);
		tiles = initializeTiles();	
		display = 1;	
		round = 4;

		if(playerNum == 2){
			round = 3;
		}
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

	private LinkedList<Location> initializeTiles(){
		int[] neighbors = new int[4];		
		int neighborLeft = 2;
		int neighborRight = 8;
		int neighborDown = 9;
		int neighborUp = 0;

		LinkedList<Location> gameTiles = new LinkedList<Location>();
		
		for(int ID = 1; ID <= 12; ID++){
			Location spot = new Location();
			spot.setID(ID);
			neighbors[0] = neighborLeft;
			neighbors[1] = neighborRight;
			neighbors[2] = neighborDown;
			neighbors[3] = neighborUp;
			spot.setNeighbors(neighbors);
			gameTiles.add(spot);	

			if(ID < 8){
				neighborLeft = grabID(ID, 8);
				neighborRight = grabID(ID, 8);
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
				neighborDown = grabID(ID, 12);
				neighborUp = grabID(ID, 12);
			}
		}
		return gameTiles;
	}
	
	private void createPlayers(int playerNum){
		for(int i = 0; i < playerNum; i++){
			Player temp = new Player();
			temp.setPlayerName("Player " + (i+1));
			users.add(temp);
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

	public void iterateRound(){
		for(int i = 0; i < users.size(); i++){
			Player p = users.get(i);
			p.takeTurn();
			//evaluate if reached end of round
			//currGame.nextRound();
			
		}
	}

	public LinkedList<Player> getPlayers(){
		return users;
	}

	public int displayRound(){
		return display;
	}

	public int getRound(){
		return round;
	}

	public void nextRound(){
		round = round - 1;
		display = display + 1;
	}

	public Player evaluateWinner(){
		boolean evalWinner = false;
		Player winner = new Player();
		Wallet winnerCash = new Wallet();		
		for(int i = 0; i < users.size(); i++){
			Player p = users.get(i);
			Wallet w = p.evalWalletContent();

			if(!evalWinner){
				evalWinner = true;
				winner = p;
				winnerCash = w;
			}else{
				int winnerPoints = winnerCash.calculatePoints(winner.getLevel());
				int playerPoints = w.calculatePoints(p.getLevel());
				if(playerPoints > winnerPoints){
					winner = p;
					winnerCash = w;
				}
			}
			
		}
		return winner;
	}

}
