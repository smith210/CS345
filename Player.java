import java.util.*;

public class Player{

	private String playerName;
	private int actorLevel;
	private Location currLocation;
	private Wallet myWallet;
	private boolean activeActor;
	private Work jobDescription;
	private Roll dice; 
	private CastingOffice co;
	private userInput query;

	Player(){
		playerName = "";//haven't thought about default name yet
		actorLevel = 1;
		currLocation = new Location();
		myWallet = new Wallet();
		jobDescription = new Work();
		dice = new Roll();
		co = new CastingOffice();
		query = new userInput();
	}
	public void setLocation(Location location){ currLocation = location; }

	public void setPlayerName(String name){ playerName = name; }
	
	public int getLevel(){ return actorLevel; }

	public Location getLocation(){ return currLocation; }

	public Work getJob(){ return jobDescription; }

	public void removeWork(){
		activeActor = false;
		jobDescription = new Work();
	}

	public void takeTurn(){
		System.out.println(playerName+ " level: " + actorLevel);
		System.out.println(jobDescription.getWorkType() + " " + activeActor);
		System.out.println("location: " + currLocation.getLocationName());
			
		boolean finishTurn = false;
		boolean hasWorked = false;
		boolean hasMoved = false;
		while(!finishTurn){

			String userInput = query.getUserInput("Enter your move: ");
			switch(userInput){
				case "MOVE":
					if(!activeActor && !hasMoved){
						LinkedList<Location> neighborhood = currLocation.getNeighbors();
						for(int ID = 0; ID < neighborhood.size(); ID++){
							Location nextDoor = neighborhood.get(ID);						
							System.out.println(ID + ": " + nextDoor.getLocationName());
						}
						String moveInput = "";
						int desiredMove = query.getIntInput("Where do you want to move to?",
														"move", 0, neighborhood.size());
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
					if(actorLevel != 6){
						if(!activeActor && currLocation.getID() == 8){
							int levelDesired = query.getIntInput("What level do you want to level up to?", "upgrade", actorLevel, 6);
							if(levelDesired != -1){
								co.updateWallet(myWallet);							
								myWallet = co.pay(levelDesired);
							}
						}else{
							System.out.println("you cannot upgrade in the " + currLocation.getLocationName());
						}
					}else{
						System.out.println("you are upgraded to the maximimum");
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
			int roleID = query.getIntInput("Which Role do You Want?", "work", 0, availableWork.size());
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
				String userInput = query.getUserInput("What do you want to work on: ");

				switch(userInput){
					case "REHEARSE":
						int priorAdd = dice.getAC();
						dice.increaseAC();
						int afterAdd = dice.getAC();
						System.out.println("Increased acting counters from " + priorAdd + " to " + afterAdd);

						validInput = true;
						break;
					case "ACT":
						String actorType = jobDescription.getWorkType();
						validInput = true;
						dice.roll();
						int actingEffort = dice.actRoll();
						boolean isSucc = currLocation.getSet().isActSuccess(actingEffort);
				
						System.out.print("You rolled a " + dice.getRoll());
						System.out.println(". With your rehearsals, that raised your roll to a " + actingEffort);
						
						if(isSucc){
							System.out.println("You acted succesfully!" );
							switch(actorType){
								case "MAIN":
									myWallet.addCredits(2);
									break;
								case "EXTRA":
									myWallet.addDollars(1);
									myWallet.addCredits(1);
									break;
								default:
							}
							
							currLocation.getSet().decrementShotCounter();
							int shotsLeft = currLocation.getSet().getShotCounter();	
							System.out.print("There is " + shotsLeft + " shots left on ");
							System.out.println(currLocation.getLocationName() +".");						

						}else{
							System.out.println("You suck at acting. " );
							switch(actorType){
								case "MAIN":
									break;
								case "EXTRA":
									myWallet.addDollars(1);
									break;
								default:
							}
						}
						break;
					default:
						System.out.print("INVALID WORK. ");	
				}
			}
		}
	}

	public Wallet evalWalletContent(){ return myWallet; }


}
