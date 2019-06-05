import java.util.*;
import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.accessibility.*;

public class Player{

	private String playerName;
	private int actorLevel;
	private Location currLocation;
	private Wallet myWallet;
	private boolean activeActor;
	private Work jobDescription;
	private Roll dice; 
	private CastingOffice co;
	private Colors surroundings;
	private Image playerIcon;

	public Player(){
		playerName = "";
		actorLevel = 1;
		currLocation = new Location();
		myWallet = new Wallet();
		jobDescription = new Work();
		dice = new Roll();
		co = new CastingOffice();
		//query = new userInput();
		surroundings = new Colors();
	}

	//public void setQuery(userInput query){ this.query = query;}

	public void setColors(Colors surroundings){ this.surroundings = surroundings; }

	public void setLocation(Location location){ currLocation = location; }

	public void setPlayerName(String name){ playerName = name; }

	public void setActorLevel(int actorLevel){this.actorLevel = actorLevel;}

	public void setJob(Work jobDescription){ this.jobDescription = jobDescription; }
	
	public void buffActingStatus(boolean activeActor){ this.activeActor = activeActor; }

	public Colors getSurroundings(){ return surroundings; }

	public String getPlayerName(){ return playerName; }
	
	public int getLevel(){ return actorLevel; }

	public Location getLocation(){ return currLocation; }

	public Work getJob(){ return jobDescription; }

	public boolean isWorking(){ return activeActor; }

	public Roll grabDice(){return dice;}

	public void removeWork(){
		System.out.println(playerName + " has finished working at " + currLocation.getLocationName() + ".");
		activeActor = false;
		jobDescription = new Work();
		dice = new Roll();
	}

	public void playerInformation(){
		System.out.println("You are " + playerName + ", a Level " + actorLevel + " actor.");
		System.out.println("You are at the " + currLocation.getLocationName() + ".");
		if(!activeActor){
			System.out.println("You are currently not working.");
		}else{
			System.out.println("You are currently working on " + jobDescription.getJobTitle());
		}
	}

	public void bonusPayout(LinkedList<Integer> bonusDice){
		int mainID = currLocation.getSet().getScene().mainActorID(jobDescription);
		int numMain = currLocation.getSet().getScene().numMainActors();

		int curr = 0;
		int payment = 0;
		while(curr != bonusDice.size()){
			if(payment == mainID){
				myWallet.addDollars(bonusDice.get(curr), playerName);
			}			

			if(payment != numMain){
				payment++;
			}else{
				payment = 0;
			}
			curr++;
		}
	}

	public void playerLocation(String userView){
		System.out.print(userView + " on the " + currLocation.getLocationName());
		if(activeActor){
			System.out.print(", working");
		}
		System.out.println(".");
	}


	public LinkedList<String> neighborNames(){
		LinkedList<Location> neighborhood = currLocation.getNeighbors();
		LinkedList<String> neighbornames = new LinkedList<String>();
		for(int ID = 0; ID < neighborhood.size(); ID++){
			Location nextDoor = neighborhood.get(ID);						
			neighbornames.add(nextDoor.getLocationName());
		}
		return neighbornames;
	}

	/*public boolean move(boolean hasMoved){
		if(!activeActor && !hasMoved){
			LinkedList<Location> neighborhood = currLocation.getNeighbors();
			LinkedList<String> neighbornames = new LinkedList<String>();
			for(int ID = 0; ID < neighborhood.size(); ID++){
				Location nextDoor = neighborhood.get(ID);						
				System.out.println(ID + ": " + nextDoor.getLocationName());
				neighbornames.add(nextDoor.getLocationName());
			}
			query.addNeighborNames(neighbornames);

			String moveInput = "";
			int desiredMove = query.getIntInput("Where do you want to move to?",
											"move", 0, neighborhood.size());
			if(desiredMove != -1){
				System.out.print("Move successful! You have moved from the " + currLocation.getLocationName());
				setLocation(neighborhood.get(desiredMove));
				System.out.println(" to the " + currLocation.getLocationName() + ".");
				hasMoved = true;
			}

		}else{
			System.out.println("You are not allowed to move");
		}
		return hasMoved;
	}*/

	public void upgrade(){
		if(actorLevel != 6){
			if(!activeActor && currLocation.getID() == 8){
				co.updateWallet(myWallet);
				co.displayPrice();
				myWallet.displayContent();
				int levelDesired = -1;
				if(levelDesired != -1){						
					boolean hasPayment = co.pay(levelDesired);

					if(hasPayment){
						setActorLevel(levelDesired);
					}
				}
			}else{
				System.out.println("You cannot upgrade in the " + currLocation.getLocationName() + ".");
			}
		}else{
			System.out.println("You are upgraded to the maximimum.");
		}
	}

	public boolean initializeWork(boolean hasWorked, boolean hasMoved){
		if(currLocation.getSet().getShotCounter() != 0 && !hasWorked){
			Set actingSpace = currLocation.getSet();
			actingSpace.getSceneDesc();
			if(!hasMoved){ 
				work();
			}else{ 
				setRole(); 
			}
			
			if(activeActor){ hasWorked = true; }
		}else{
			if(hasWorked){
				System.out.println("You already worked this turn.");
			}else{
				System.out.println("You cannot work at the " + currLocation.getLocationName() + ".");
			}						
		}
		return hasWorked;
	}

	private void setRole(){
		Set actingSpace = currLocation.getSet();
		LinkedList<Work> availableWork = actingSpace.findAvailWork(actorLevel);

		if(availableWork.size() != 0){
			System.out.println("Available Jobs within your Acting Level: ");

			for(int jobNum = 0; jobNum < availableWork.size(); jobNum++){
				System.out.print(jobNum + ": ");
				availableWork.get(jobNum).display();
			}
			boolean validInput = false;
			int roleID = -1;
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
			dice.displayRehearsals();
			while(!validInput){
				String userInput = "ACT";

				switch(userInput){
					case "REHEARSE":
						if(!dice.isMaxRehearsals(currLocation.getSet().getScene().getBudget())){
							int priorAdd = dice.getAC();
							dice.increaseAC();
							int afterAdd = dice.getAC();
							System.out.println("Increased acting counters from " + priorAdd + " to " + afterAdd);

							validInput = true;
						}else{
							System.out.println("You rehearsed enough! Just act already!");
						}
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
									myWallet.addCredits(2, "You");
									break;
								case "EXTRA":
									myWallet.addDollars(1, "You");
									myWallet.addCredits(1, "You");
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
									System.out.println("You do not recieve any currency.");
									break;
								case "EXTRA":
									myWallet.addDollars(1, "You");
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
