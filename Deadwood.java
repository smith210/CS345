import java.util.*;

public class Deadwood{
	private Board currGame;

	private int playerNum;

	Deadwood(){
		//currGame = new Board();

		//cont.setView(GUI.getPanel());
		//cont.setModel(currGame.getQueries());
		playerNum = 2;// new Integer();
	}

	public void run(){//int playerNum) throws invalidPlayerException{
		/*if(playerNum != 2 && playerNum != 3){
			throw new invalidPlayerException();
		}*/

		//GUI.createAndShowGUI();

		//cont.setView(GUI.getPanel());
		//will contain game logic;
		currGame = new Board(playerNum);

		boolean isPlaying = true;
		int starter = 0;
		while(isPlaying){
			//cont.getCommand();
			starter = currGame.iterateRound(starter);
			if(currGame.getRound() == 0){
				Player winner = currGame.evaluateWinner();
				System.out.println(winner.getPlayerName() + " Wins!");
				isPlaying = false;
			}
		}
	}
}
