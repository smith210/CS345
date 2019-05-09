import java.util.*;

public class Player{

	private String playerName;
	private int actorLevel;
	private Set currLocation;
	private Wallet myWallet;
	private boolean activeActor;
	private Work jobDescription;
	private Roll dice; 

	Player(){
		playerName = "";//haven't thought about default name yet
		actorLevel = 1;
		//currLocation = trailer; We don't know how to implement yet
		myWallet = new Wallet();
		jobDescription = new Work();
		dice = new Roll();
	}

	public void setPlayerName(String name){
		playerName = name;
	}

	public String getPlayerName(){
		return playerName;
	}
	
	public void move(Set newLocation){
	//check if new location is adjacent to current location

		//Set currLocation = newLocation; 

	}
	
	public int getActorLevel(){
		return actorLevel;
	
	}
	
	public void setRole(){
		//if there is available work within the player's level
			//return available work
			//have user pick work
			//activeActor = true;
	}
	
	public Work getWork(){
		return jobDescription;
	}

	public void work(String workType){
		if(!activeActor){
			setRole();
		}
		if(activeActor){
			//ask user for rehearse or act
				if(workType.equals("rehearse")){
					dice.increaseAC();
				}
				
				if (workType.equals("act")){
					dice.roll();
					int actingEffort = dice.actRoll();
					boolean isSucc = currLocation.isActSuccess(actingEffort);
					if(isSucc){

					}else{

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
	
	public void upgrade(int upgradeLevel){
		//do check to see if valid level, and if near or in CastingOffice
		//if check succeeds
			//myWallet.addDollars(-amount) or myWallet.addCredits(-amount)
			//actorLevel = upgradeLevel;
		//if not located in the CastingOffice
			//currLocation = CastingOffice;
	}

	public Wallet evalWalletContent(){
		return myWallet;
	}


}
