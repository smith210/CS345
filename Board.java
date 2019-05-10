import java.util.*;

public class Board{
	private LinkedList<Player> users;
	private LinkedList<Location> tiles;
	private int round;
	private int display;
	
	Board(int playerNum){
		users = new LinkedList<Player>();
		createPlayers(playerNum);
		//figure out how to initialize tiles	
		display = 1;	
		round = 4;

		if(playerNum == 2){
			round = 3;
		}
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
				int winnerPoints = winnerCash.calculatePoints();
				int playerPoints = w.calculatePoints();
				if(playerPoints > winnerPoints){
					winner = p;
					winnerCash = w;
				}
			}
			
		}
		return winner;
	}

}
