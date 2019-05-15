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

	public void takeTurn(){
		System.out.println(playerName+ " level: " + actorLevel);
		System.out.println(jobDescription.getWorkType() + " " + activeActor);
		System.out.println("location: " + currLocation.getID());
			
		boolean finishTurn = false;
		boolean hasMoved = false;
		while(!finishTurn){
			if(activeActor){
				work();
				finishTurn = true;
			}else{
				String userInput = getUserInput("Enter your move: ");
				switch(userInput){
					case "MOVE":
						if(!activeActor && !hasMoved){
							System.out.println("move successful!");
							hasMoved = true;
						}else{
							System.out.println("you are not allowed to move");
						}
						break;
					case "UPGRADE":
						break;
					case "WORK":
						work();
						finishTurn = true;
						break;
					case "END":
						finishTurn = true;
						break;
					default:
						System.out.print("INVALID MOVE. ");		
				}
			}
		}
		System.out.println(" ");
	}

	public void move(Location newLocation){
	//check if new location is adjacent to current location

		//Set currLocation = newLocation; 

	}
	
	
	private void setRole(){
		/*Set actingSpace = currLocation.getSet();
		LinkedList<Work> availableWork = actingSpace.findAvailWork(actorLevel);
		if(availableWork.size() != 0){
			for(int jobNum = 0; jobNum < availableWork.size(); jobNum++){
				availableWork.get(jobNum).display();
			}
			boolean validInput = false;
			while(!validInput){
				String userInput = getUserInput("Which Role do You Want? If you want no Role, type \'no\'");
				try{
					if(Integer.parseInt(userInput) < availableWork.size()){
						jobDescription = availableWork.get(Integer.parseInt(userInput));
						activeActor = true;
						validInput = true;
					}
				}catch(Exception e){
					if(userInput.equals("no"){
						validInput = true;
					}else{
						System.out.print("INVALID COMMAND. ");
					}
				}
			}
				
			*/
			//activeActor = true;
		//}
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
						if(isSucc){

						}else{

						}
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
