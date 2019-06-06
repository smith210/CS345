import java.util.*;
import java.lang.Math;

public class Deadwood{
	private Board currGame;
	private Roll dice;
	private int playerNum, playerID, cash;
	private boolean canMove, canWork, canUpgrade, toggleDefault;
	private LinkedList<Player> users;
	private String userInput, mainInput, paymentMethod;

	Deadwood(){
		playerNum = 3;
		currGame = new Board();
		users = createPlayers(playerNum);//generate players
		canMove = true;
		canWork = true;
		canUpgrade = true;
		toggleDefault = false;
		userInput = new String();
		mainInput = new String();
		playerID = 0;
		cash = 0;
	}

	public boolean moveToggle(){ return canMove;}
	public boolean workToggle(){ return canWork;}
	public boolean upgradeToggle(){ return canUpgrade;}
	public boolean menuToggled(){ return toggleDefault; }

	public void setUserInput(String userInput){ this.userInput = userInput; }

	public String getBaseCommand(){ return userInput; }
	public Player currentPlayer(){ return users.get(playerID); }

	public Wallet currentWallet(){ return currentPlayer().evalWalletContent(); }
	public int currentLevel(){ return currentPlayer().getLevel(); }
	public Location currentLocation(){ return currentPlayer().getLocation(); }
	public boolean currentlyWorking(){ return currentPlayer().isWorking(); }

	public LinkedList<Location> currentNeighbors(){ return currentLocation().getNeighbors(); }
	public LinkedList<Work> currentJobs(){ return currentLocation().getJobs(); }

	public Location meetNeighbor(int index){ return currentNeighbors().get(index); }

	public Work selectedJob(int ID){ return currentJobs().get(ID); }
	
	public boolean endofRound(){ return currGame.hasFinishedRound(); }
	public boolean finishedFilming(){ return currentLocation().doneFilming(); }
	public boolean endofGame(){ return currGame.hasFinishedGame(); }

	private LinkedList<Player> evaluateWinner(){//evaluate winner(s)
		LinkedList<Player> winner = new LinkedList<Player>();		
		Player p1 = users.get(0);
		Wallet w1 = p1.evalWalletContent();
		for(int i = 0; i < users.size(); i++){
			Player p2 = users.get(i);
			Wallet w2 = p2.evalWalletContent();

			int p1Points = w1.calculatePoints(p1.getLevel());
			int p2Points = w2.calculatePoints(p2.getLevel());
			if(p2Points > p1Points){
				winner.clear();
				p1 = p2;
				w1 = w2;
			}if(p2Points == p1Points){
				winner.add(p2);
			}
		}
		winner.add(p1);
		return winner;
	}

	private LinkedList<Player> createPlayers(int playerNum){//generates players
		LinkedList<Player> friends = new LinkedList<Player>();
		ColorTemplates picture = new ColorTemplates();
		for(int i = 0; i < playerNum; i++){
			Player temp = new Player();
			temp.setColors(picture.get(i));
			temp.setPlayerName("Player " + (i+1));
			temp.setLocation(currGame.getTrailer());
			friends.add(temp);
		}
		System.out.println("Created players");
		return friends;
	}

	public void checkOnPlayer(){//detoggle options based on player's circumstances
		if(currentlyWorking()){
			canMove = false;
		}
		if(currentLocation().getID() != 8){
			canUpgrade = false;
		}
		if(finishedFilming()){
			canWork = false;
		}
	}

	public boolean validLocationID(int locID){//valid location
		int i = 0;		
		while(i != currentNeighbors().size() &&  meetNeighbor(i).getID() != locID){
			i++;
		}
		if(i == currentNeighbors().size()){
			return false;
		}else{
			return true;
		}
	}

	public boolean validWorkID(int jobID){//valid work
		if(jobID >= 0 && jobID < currentJobs().size()){
			if(validLevel(currentPlayer().getLevel())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		} 
	}


	private boolean sameLocation(Location check){// check if location is same as the current
		if(currentLocation().getID() == check.getID()){
			return true;
		}else{
			return false;
		}
	}

	private void evaluateConsequences(){//check to see if any outside factors happened
		if(finishedFilming() && currentLocation().hasMainActors()){//get bonus payout if there were celebrities
			for(int j = 0; j > users.size(); j++){
				Player applicant = users.get(j);
				if(sameLocation(applicant.getLocation())){
					applicant.bonusPayout();
				}
			}
		}
		if(endofRound()){
			if(!endofGame()){//get new scenes, return players to Trailer, decrement round
				for(int i = 0; i > users.size(); i++){//return all players to Trailer
					users.get(i).setLocation(currGame.getTrailer());
				}
				currGame.nextRound();//decrement round
			}else{//end game, figure out winner.
				LinkedList<Player> winner = evaluateWinner();
			}
		}
	}

	private void exchangeCurrency(){//get currency current player is using
		if(paymentMethod.equals("DOLLAR")){
			cash = currentWallet().getDollars();
		}else if(paymentMethod.equals("CREDIT")){
			cash = currentWallet().getCredits();
		}else{//error
			cash = -1;
		}
	}

	public int calculatePrice(int levelID){//get the cost of the level
		if(paymentMethod.equals("DOLLAR")){
			return (int) Math.pow(2, levelID);
		}else if(paymentMethod.equals("CREDIT")){
			return 5 * (levelID - 1);
		}else{//error
			return -1;
		}
	}

	public boolean validLevel(int levelID){//valid upgradablelevel
		int myPrice = 0;		
		if(levelID <= 6 && levelID > currentLevel()){//player at valid level
			if(paymentMethod.equals("DOLLAR") || paymentMethod.equals("CREDIT")){
				myPrice = calculatePrice(levelID);
				if(canPay(myPrice, cash)){
					return true;
				}else{
					return false;
				}
			}else{//error
				return false;
			}
		}else{
			return false;
		}
	}
	
	private void paythePrice(){ currentPlayer().pay(cash, paymentMethod);}//decrement cash

	private boolean canPay(int price, int amount){//determine if has enough cash to pay
		if(price > amount){
			return false;
		}else{
			return true;
		}
	}

	private void getNextPlayer(){//next player
		playerID++;
		if(playerID == playerNum){
			playerID = 0;
		}
	}

	private void parseAction(String selection){//parse the moves within moves
		if(!selection.equals("NO")){
			switch(userInput){
				case "MOVE"://change current player location
					try{
						int LocID = Integer.parseInt(selection);
						if(validLocationID(LocID)){
							currentPlayer().setLocation(meetNeighbor(LocID));
							canMove = false;
						}
					}catch(Exception e){//error

					}
					break;
				case "WORK"://set role, act, or rehearse
					if(!currentlyWorking()){
						int workID = (Integer.parseInt(selection));
						if(validWorkID(workID)){
							currentPlayer().setJob(selectedJob(workID));
						}
					}
					if(currentlyWorking() && canMove){
						if(selection.equals("REHEARSE")){
							currentPlayer().rehearse();
						}else if(selection.equals("ACT")){
							dice.roll();
							currentPlayer().act(dice.getRoll());
						
						}else{ //error
						}
					}else{
						canWork = false;
					}
					break;
				case "UPGRADE"://change current player level
					if(selection.equals("CREDIT") || selection.equals("DOLLAR")){
						exchangeCurrency();
					}else{ //selected a level
						int level = Integer.parseInt(selection);
						if(validLevel(level)){
							paythePrice();
							currentPlayer().setActorLevel(level);
							canUpgrade = false;
						}
					}
					break;
				default://not a recognized userInput
			}
		}else{//clear the userInput, toggle default options
			userInput = new String();
		}
	}

	private void switchCommand(String desireTo){//main commands
		if(!toggleDefault){
			switch(desireTo){
				case "RENAME":
					break;
				case "DISPLAY":
					break;
				case "END"://rebuff default options, get next player
					evaluateConsequences();//check for outside happenings
					getNextPlayer();
					checkOnPlayer();
					break;
				case "MOVE":
					if(!canMove){ break; }
				case "UPGRADE":
					if(!canUpgrade){ break; }
				case "WORK":
					if(!canWork){ break; }
					toggleDefault = true;//a main command has been selected
					break;
				default:
			}
		}else{
			parseAction(desireTo);			

		}
	}

	public void run(){//int playerNum) throws invalidPlayerException{
		/*if(playerNum != 2 && playerNum != 3){
			throw new invalidPlayerException();
		}*/

		boolean playGame = true;
		checkOnPlayer();
		System.out.println("start run");
		while(playGame){

			//System.out.println("userInput: " + userInput);
			if(endofGame()){
				if(userInput.equals("YES")){//refresh program
					
				}
				if(userInput.equals("NO")){//quit program
					playGame = false;
				}
			}
			
			switchCommand(userInput);
			if(toggleDefault){
				mainInput = userInput;
				System.out.println("maininPut: " + mainInput);
				toggleDefault = false;
			}

		}
	}
}
