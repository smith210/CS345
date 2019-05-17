import java.util.*;

public class Deadwood{
	public void run(int playerNum) throws invalidPlayerException{
		if(playerNum != 2 && playerNum != 3){
			throw new invalidPlayerException();
		}
		
		//will contain game logic;
		Board currGame = new Board(playerNum);
		boolean isPlaying = true;
		while(isPlaying){
			currGame.iterateRound();
			if(currGame.getRound() == 0){
				Player winner = currGame.evaluateWinner();
				isPlaying = false;
			}
		}
	}
}
