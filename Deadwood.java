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
		canWork = false;
		canUpgrade = false;
		toggleDefault = false;
		dice = new Roll();
		userInput = new String();
		mainInput = new String();
		paymentMethod = "DOLLAR";
		playerID = 0;
		cash = 0;
	}


	public void setPaymentMethod(String paymentMethod){ this.paymentMethod = paymentMethod; }
	public void getScenes(){ currGame.prepBoard(); }
	public int getLastRoll(){ return dice.getRoll(); }
	public void setPlayerNum(int playerNum){ this.playerNum = playerNum; } 

	public boolean moveToggle(){ return canMove;}
	public boolean workToggle(){ return canWork;}
	public boolean upgradeToggle(){ return canUpgrade;}
	public boolean menuToggled(){ return toggleDefault; }

	public void setUserInput(String userInput){ this.userInput = userInput; }
	public String getUserInput(){ return userInput; }

	public String getCurrentCommand(){ return userInput; }
	public String getBaseCommand(){ return mainInput; }
	public String getCurrencyType(){ return paymentMethod; }
	public LinkedList<Player> allPlayers(){ return users; }
	public Player currentPlayer(){ return users.get(playerID); }
	public int getCash(){ return cash;}

	public Wallet currentWallet(){ return currentPlayer().evalWalletContent(); }
	public int currentLevel(){ return currentPlayer().getLevel(); }
	public Location currentLocation(){ return currentPlayer().getLocation(); }
	public boolean currentlyWorking(){ return currentPlayer().isWorking(); }

	public int numberShotCounters(){ return currentLocation().getSet().getShotCounter();}


	public LinkedList<Location> currentNeighbors(){ return currentLocation().getNeighbors(); }
	public LinkedList<Work> currentJobs(){ return currentLocation().getJobs(); }

	public Location meetNeighbor(int index){ return currentNeighbors().get(index); }//returns the job at current index

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
			temp.setID(i);
			temp.setColors(picture.get(i));
			temp.setPlayerName("Player " + (i+1));
			temp.setLocation(currGame.getTrailer());
			friends.add(temp);
		}
		//System.out.println("Created players");
		return friends;
	}

	public void checkOnPlayer(){//detoggle options based on player's circumstances
		if(currentlyWorking()){
			canMove = false;
		}
		
		if(currentLocation().getID() != 8){
			canUpgrade = false;
		}else{
			canUpgrade = true;
		}
		
		if(finishedFilming()){
			canWork = false;
		}else{
			canWork = true;
		}
	}

	public int validLocationID(int locID){//valid location
		int i = 0;		
		while(i != currentNeighbors().size() &&  meetNeighbor(i).getID() != locID){
			//System.out.println(meetNeighbor(i).getID());
			i++;
		}
		if(i == currentNeighbors().size()){
			return -1;
		}else{
			return i;
		}
	}


	

	public boolean validWorkID(int jobID){//valid work
		if(jobID >= 0 && jobID < currentJobs().size()){
			//System.out.println("valid job in list");
			if(currentPlayer().getLevel() >= selectedJob(jobID).getWorkLevel() && !selectedJob(jobID).getWorkStatus()){
				//System.out.println("valid level for the posting");
				return true;
			}else{
				//System.out.println("not able to be worked on");
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


	private void actorsLastHurrah(){
		//System.out.println("FINISHED FILMING");
		for(int j = 0; j < users.size(); j++){
			Player applicant = users.get(j);
			//System.out.println(applicant.getPlayerName() + " is on " + applicant.getLocation().getLocationName());
			if(sameLocation(applicant.getLocation())){
				//System.out.println("guess I'm on the lcoation");
				if(currentLocation().hasMainActors()){//get bonus payout if there were celebrities	
					applicant.bonusPayout(dice.getBonusRoll(currentLocation().getSet().getScene().getBudget()));
				}
				applicant.removeWork();
				//System.out.println("REMOVE FROM WORK");
			}
		}
	}

	private void evaluateConsequences(){//check to see if any outside factors happened
		if(finishedFilming() && (currentLocation().getID() != 8 && currentLocation().getID() != 4)){
			actorsLastHurrah();
		}
		if(endofRound()){//finished a round
			if(!endofGame()){//get new scenes, return players to Trailer, decrement round
				for(int i = 0; i > users.size(); i++){//return all players to Trailer
					users.get(i).setLocation(currGame.getTrailer());
				}
				currGame.prepBoard();//get new scenes
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
		playerID = playerID + 1;
		if(playerID == playerNum){
			playerID = 0;
		}
	}

	private void parseAction(){//parse the moves within moves
		//System.out.println(currentPlayer().getPlayerName() + " is trying to " + userInput + " with " + mainInput);
		switch(mainInput){
			case "MOVE"://change current player location
				try{
					int LocID = Integer.parseInt(userInput);
					//System.out.println("Guess the user inputted a Location ID of some kind...");
					int neighborAddress = validLocationID(LocID);
					if(neighborAddress != -1){//exists as a neighbor
						currentPlayer().setLocation(meetNeighbor(neighborAddress));
						checkOnPlayer();
						canMove = false;
						toggleDefault = false;
						//System.out.println("Player now resides on: " + currentLocation().getLocationName());
					}else{
						//System.out.println("Not a valid Location identification");
					}
				}catch(Exception e){//error
					//System.out.println("Not a valid input for moving");
				}
				break;
			case "WORK"://set role, act, or rehearse
					if(!currentlyWorking()){ // If NOT working, then you are unemployed
						System.out.println("Looks like " + currentPlayer().getPlayerName() + " is unemployed...");
						try{
						int workID = (Integer.parseInt(userInput));
						System.out.println("trying to obtain job number " + workID);
						if(validWorkID(workID)){ // If the work ID is valid, then they work on this job!
							selectedJob(workID).bufWork();							
							currentPlayer().setJob(selectedJob(workID));
							
							System.out.println(currentPlayer().getPlayerName() + " is now working on " + currentPlayer().getJob().getJobTitle());
							if(!canMove){ //remove player from work status if have moved already 
								System.out.println("Can't work at the moment, check back in later");
								toggleDefault = false;
								canWork = false;
								break; 
							}
						}
						}catch(Exception e){ // a catch and not an else?
							System.out.println("Can't work with that command...");
						}
					}

					if(currentlyWorking()){

						//System.out.println("Looks like the player " + currentPlayer().getPlayerName() + "has been doing something at the " + currentLocation().getLocationName() + "...");
						//System.out.println("seems like the player is trying to " + userInput);
						if(userInput.equals("REHEARSE")){
							int rehearsals = currentPlayer().rehearse();
							//System.out.println("you've rehearsed " + rehearsals + " times");
							if(rehearsals != -1){
								toggleDefault = false;
								canWork = false;
							}else{
								//System.out.println("YOu can't rehaeESE anyMorea");
							}
						}else if(userInput.equals("ACT")){
							dice.roll();
							currentPlayer().act(dice.getRoll());
							toggleDefault = false;
							canWork = false;
						}else{ //error
							//System.out.println("guess the player is not really doing anything rn...");
						}
						canMove = false;
					}


				break;
			case "UPGRADE"://change current player level
				if(userInput.equals("CREDIT") || userInput.equals("DOLLAR")){
					exchangeCurrency();
				}else{ //selected a level
					int level = Integer.parseInt(userInput);
					if(validLevel(level)){
						paythePrice();
						currentPlayer().setActorLevel(level);
						canUpgrade = false;
					}
				}
				break;
			default://not a recognized userInput
				//System.out.println("Not a command recognized for " + mainInput);
		}

	}


	public void switchCommand(){//main commands
		if(!toggleDefault){
			//System.out.println("");
			switch(userInput){
				case "RENAME":
					break;
				case "DISPLAY":
					break;
				case "WHO":
					System.out.println("WHO YOU ARE --------");
					System.out.println("USER NAME: " + currentPlayer().getPlayerName());
					System.out.println("WHERE: " + currentLocation().getLocationName());
					System.out.println("WORK TITLE: " + currentPlayer().getJob().getJobTitle());

					System.out.println("Allowed to move -> " + canMove);
					System.out.println("Allowed to work -> " + canWork);
					System.out.println("Allowed to upgrade -> " + canUpgrade);
					break;
				case "WHERE":
					for(int i = 0; i < users.size(); i++){
						System.out.println(users.get(i).getPlayerName() + " on " + users.get(i).getLocation().getLocationName());
						System.out.println("WORK STATUS: " + users.get(i).isWorking());
					}
					break;
				case "END"://USER ENDS THEIR TURN, NEXT PLAYER STARTS
					//System.out.println("User " + currentPlayer().getPlayerName() + " has ended their turn");
					if(!canMove){
						canMove = true;
					}
					evaluateConsequences();//check for outside happenings
					getNextPlayer();
					checkOnPlayer();
					//System.out.println("User " + currentPlayer().getPlayerName() + " has started their turn");
					break;
				case "MOVE"://USER WANTS TO MOVE
					//System.out.println("User " + currentPlayer().getPlayerName() + " wants to move it.");
					if(canMove){
						//System.out.println(currentPlayer().getPlayerName() + " is permitted to move.");
						mainInput = userInput;
						//System.out.println("Locations in your area (enter ID): ");
						for(int m = 0; m < currentNeighbors().size(); m++){
							//System.out.println(meetNeighbor(m).getID() + " " + meetNeighbor(m).getLocationName());
						}
						toggleDefault = true;
					}else{
						//System.out.println("User " + currentPlayer().getPlayerName() + " is not allowed to move.");
					}
					break;
				case "UPGRADE":
					if(canUpgrade){
						mainInput = userInput;
						toggleDefault = true;
					}else{
						//System.out.println(currentPlayer().getPlayerName() + " is not allowed to upgrade");
					}
					break;
				case "WORK":
					//System.out.println(currentLocation().getLocationName() + " currently has " + numberShotCounters() + "shots.");
					if(canWork){
						currentLocation().displayContent();
						mainInput = userInput;
						toggleDefault = true;
						//System.out.println("player work status: " + currentPlayer().isWorking());
						if(!currentPlayer().isWorking()){
							//System.out.println("seems like you're not working, here's the jobs");
							for(int w = 0; w < currentJobs().size(); w++){
								System.out.print(w + ": ");
								System.out.print(selectedJob(w).getJobTitle() + " ");
								System.out.print(selectedJob(w).getWorkLevel() + " ");
								System.out.print(selectedJob(w).getWorkType() + " ");
								System.out.print(selectedJob(w).getWorkStatus());
								System.out.println(" ");
							}
						}
					}else{
						//System.out.println("PLAYER IS NOT ALLOWED TO WORK");
					}


					break;
				default:
					//System.out.println("Not a command");
			}
		}else{

			if(userInput.equals("NO")){			
				toggleDefault = false;
				//System.out.println("going back to the default commands");	
			}else{
				parseAction();
			}
		}
		//System.out.println("");
	}

	public void run(){//int playerNum) throws invalidPlayerException{
		/*if(playerNum != 2 && playerNum != 3){
			throw new invalidPlayerException();
		}*/

		boolean playGame = true;
		System.out.println("start run");
		currGame.prepBoard();
		checkOnPlayer();
		while(playGame){

			if(endofGame()){
				if(userInput.equals("YES")){//refresh program
				
				}
				if(userInput.equals("NO")){//quit program
					playGame = false;
				}
			}else{
				switchCommand();
			}
		}
	}
}
