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
				System.out.println(wrk.getWorkType() + " " + p.getWorkStatus());
				
				boolean finishTurn = false;
				boolean hasMoved = false;
				while(!finishTurn){
					System.out.print("Enter your move: ");
					Scanner scn = new Scanner(System.in);
					String userInput = scn.nextLine();
					userInput = userInput.toUpperCase();
					switch(userInput){
						case "MOVE":
							if(!p.getWorkStatus() && !hasMoved){
								System.out.println("move successful!");
								hasMoved = true;
							}else{
								System.out.println("you are not allowed to move");
							}
							break;
						case "UPGRADE":
							break;
						case "WORK":
							break;
						case "END":
							finishTurn = true;
							break;
						default:
							System.out.print("INVALID MOVE. ");		
					}
				}
				System.out.println(" ");
				
			}
			//might contain a while loop to keep playing the game until player quits.
			//if user wants to quit, exit loop
			//if(currGame.checkFinishRound()){			
				currGame.nextRound();
			//}
			if(currGame.getRound() == 0){
				isPlaying = false;
			}
		}
	}
}
