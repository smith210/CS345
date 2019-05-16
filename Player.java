import java.util.*;

public class Player{

	private String playerName;
	private int actorLevel;
	private Location currLocation;
	private Wallet myWallet;
	private boolean activeActor;
	private Work jobDescription;
	private Roll dice; 

	Player(){
		playerName = "";//haven't thought about default name yet
		actorLevel = 1;
		currLocation = new Location();
		myWallet = new Wallet();
		jobDescription = new Work();
		dice = new Roll();
	}
	public void setLocation(Location location){ currLocation = location; }

	public void setPlayerName(String name){ playerName = name; }
	
	public int getLevel(){ return actorLevel; }

	public Location getLocation(){ return currLocation; }

	public void takeTurn(){
		System.out.println(playerName+ " level: " + actorLevel);
		System.out.println(jobDescription.getWorkType() + " " + activeActor);
		System.out.println("location: " + currLocation.getLocationName());
			
		boolean finishTurn = false;
		boolean hasWorked = false;
		boolean hasMoved = false;
		while(!finishTurn){

			String userInput = getUserInput("Enter your move: ");
			switch(userInput){
				case "MOVE":
					if(!activeActor && !hasMoved){
						LinkedList<Location> neighborhood = currLocation.getNeighbors();
						for(int ID = 0; ID < neighborhood.size(); ID++){
							Location nextDoor = neighborhood.get(ID);						
							System.out.println(ID + ": " + nextDoor.getLocationName());
						}
						String moveInput = "";
						int desiredMove = getIntInput("Where do you want to move to?",
														"move", neighborhood.size());
						if(desiredMove != -1){
							setLocation(neighborhood.get(desiredMove));
							System.out.println("move successful! You are now in " + currLocation.getLocationName());
							hasMoved = true;
						}
		
					}else{
						System.out.println("you are not allowed to move");
					}
					break;
				case "UPGRADE":

					if(!activeActor && currLocation.getID() == 8){
						System.out.println("in the right area to level up");
						int levelDesired = getIntInput("What level do you want to level up to?", "upgrade", 6);
					}else{
						System.out.println("you cannot Upgrade in the " + currLocation.getLocationName());
					}
					break;
				case "WORK":
					if(currLocation.getSet().getShotCounter() != 0 && !hasWorked){
						if(!hasMoved){ 
							work();
						}else{ 
							setRole(); 
						}
						
						if(activeActor){ hasWorked = true; }
					}else{
						if(hasWorked){
							System.out.println("you already worked");
						}else{
							System.out.println("you are not allowed to work here");
						}						
					}
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
	
	private int getIntInput(String prompt, String action, int listSize){
		int intInput = -1;
		boolean validInput = false;
		while(!validInput){
			String userInput = getUserInput(prompt + " (if you don't want to " + action + ", type \"no\"): ");
			try{
				if(Integer.parseInt(userInput) < 0 || Integer.parseInt(userInput) < listSize){
					intInput = Integer.parseInt(userInput);
					validInput = true;
				}else{
					System.out.println("The ID does not correspond with one of the listed IDs.");
				}
			}catch(Exception e){
				if(userInput.equals("NO")){
					validInput = true;
				}else{
					System.out.println("You must input the ID associated with your choice in order to " + action + ".");
				}
			}
		}
		return intInput;
	}	

	private void setRole(){
		Set actingSpace = currLocation.getSet();
		LinkedList<Work> availableWork = actingSpace.findAvailWork(actorLevel);
		System.out.println("Set being filmed: " + actingSpace.getSceneDesc() );

		if(availableWork.size() != 0){
			System.out.println("Available Jobs within your Acting Level: ");

			for(int jobNum = 0; jobNum < availableWork.size(); jobNum++){
				System.out.print(jobNum + ": ");
				availableWork.get(jobNum).display();
			}
			boolean validInput = false;
			int roleID = getIntInput("Which Role do You Want?", "work", availableWork.size());
			if(roleID != -1){
				jobDescription = availableWork.get(roleID);
				jobDescription.bufWork();
				activeActor = true;
				System.out.println("Congrats, you are now working on " + jobDescription.getJobTitle());
			}
				
		}else{
			System.out.println("There are no available jobs within your experience to work on");
		}
	}

	public void work(){
		if(!activeActor){
			setRole();
		}
		if(activeActor){
			boolean validInput = false;
			while(!validInput){
				String userInput = getUserInput("What do you want to work on: ");

				switch(userInput){
					case "REHEARSE":
						validInput = true;
						dice.increaseAC();
						break;
					case "ACT":
						validInput = true;
						dice.roll();
						int actingEffort = dice.actRoll();
						boolean isSucc = currLocation.getSet().isActSuccess(actingEffort);
						/*if(isSucc){

						}else{

						}*/
						break;
					default:
						System.out.print("INVALID WORK. ");	
				}
			}

				//	--->send actingEffort to currLocation to retreive true or false
				//		,will check if budget is less than or equal to actingEffort
				//	if(successAct){
				//		get paid (invoke myWallet)
				//		currLocation.removeShotCounter();
				//		if(currLocation.isSceneWrap()){
				//			
				//		}
				//	}else{
				//		if acting_type == extra --> pay
				//		
		}
	}
	
	private String getUserInput(String query){
			System.out.print(query);
			Scanner scn = new Scanner(System.in);
			String userInput = scn.nextLine();
			userInput = userInput.toUpperCase();
			return userInput;
	}
	
	public void upgrade(int upgradeLevel){
		//do check to see if valid level, and if near or in CastingOffice
		//if check succeeds
			//myWallet.addDollars(-amount) or myWallet.addCredits(-amount)
			//actorLevel = upgradeLevel;
		//if not located in the CastingOffice
			//currLocation = CastingOffice;
	}

	public Wallet evalWalletContent(){ return myWallet; }


}
