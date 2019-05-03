import java.util.*;

public class Board{
	private LinkedList<Player> users;
	private LinkedList<Set> tiles;
	private int round;
	
	Board(int playerNum){
		users = new LinkedList<Player>();
		//figure out how to initialize tiles		
		round = 4;

		if(playerNum == 2){
			round = 3;
		}
	}
	
	public void createPlayers(int playerNum){
		for(int i = 0; i < playerNum; i++){
			Player temp = new Player();
			users.add(temp);
		}
	}

	public LinkedList<Player> getPlayers(){
		return users;
	}

	public int displayRound(){
		int currRound = (round - round - 1) * (-1);
		return currRound;
	}

	public void nextRound(){
		round = round - 1;
	}

	public void takeTurn(Player p){
		//based on user input, initiate the player's turn (work, move, upgrade)
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
