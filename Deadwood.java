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
		//might contain a while loop to keep playing the game until player quits.
	}
}
