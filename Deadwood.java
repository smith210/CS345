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
			for(int i = 0; i < currGame.getPlayers().size(); i++){
				Player p = currGame.getPlayers().get(i);
				System.out.println(p.getPlayerName()+ " level: " + p.getActorLevel());
				Work wrk = p.getWork();
				System.out.println(wrk.getWorkType());
				
			}
			//might contain a while loop to keep playing the game until player quits.
			//if user wants to quit, exit loop
			if(currGame.getRound() == 0){
				isPlaying = false;
			}
			currGame.nextRound();
		}
	}
}
