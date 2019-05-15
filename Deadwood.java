import java.util.*;

public class Deadwood{
	public void run(int playerNum) throws invalidPlayerException{
		if(playerNum != 2 && playerNum != 3){
			throw new invalidPlayerException();
		}
		/*Roll die = new Roll();
		System.out.println(die.getAC());
		die.increaseAC();
		System.out.println(die.getAC());*/
		
		//will contain game logic;
		Board currGame = new Board(playerNum);
		boolean isPlaying = true;
		while(isPlaying){
			System.out.println("ROUND " + currGame.displayRound());
			currGame.iterateRound();
			//might contain a while loop to keep playing the game until player quits.
			//if user wants to quit, exit loop
			/*if(currGame.checkFinishRound()){
				currGame.nextRound();
			}*/
			currGame.nextRound();
			if(currGame.getRound() == 0){
				isPlaying = false;
				//Player winner = currGame.evaluateWinner()
			}
		}
	}
}
