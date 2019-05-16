import java.util.*;

public class Board{
	private LinkedList<Player> users;
	private GameTiles tiles;
	private int round;
	private int display;
	
	Board(int playerNum){
		users = new LinkedList<Player>();
		tiles = new GameTiles();
		createPlayers(playerNum);
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
			temp.setLocation(tiles.get(4));
			users.add(temp);
		}
	}

	public void iterateRound(){
		tiles.drawScenes();
		System.out.println("BEGINNING OF ROUND " + display);
		System.out.println(" ");
		int i = 0;
		while(!hasFinishedRound()){
			Player p = users.get(i);
			p.takeTurn();
			i = i + 1;
			if(i == users.size()){ 
				i = 0; 
			}
		}
		System.out.println("END OF ROUND " + getRound());
		if(getRound() == 0){
			//Player winner = currGame.evaluateWinner()
		}else{
			nextRound();
		}

	}

	public boolean hasFinishedRound(){ return tiles.isBoardWrapped(); }

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
