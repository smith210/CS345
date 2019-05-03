import java.util.*;

public class Player{

	private int actorLevel;
	private Set currLocation;
	private Wallet myWallet;
	private boolean activeActor;
	private Work jobDescription;
	private Roll dice; 

	Player(){
	
		actorLevel = 1;
		//currLocation = trailer; We don't know how to implement yet
		myWallet = new Wallet();
		jobDescription = new Work();
		dice = new Roll();
	}

	public void move(Set newLocation){
	//check if new location is adjacent to current location

		//Set currLocation = newLocation; 

	}

	public void setRole(){
		//if there is available work within the player's level
			//return available work
			//have user pick work
			//activeActor = true;
	}

	public void work(){
		if(!activeActor){
			setRole();
		}
		if(activeActor){
			//ask user for rehearse or act
				//if rehearse
				//	dice.increaseAC();
				//if act
				//	dice.roll();
				//	int actingEffort = dice.actRoll();
				//	--->send actingEffort to currLocation to retreive true or false
				//		,will check if budget is less than or equal to actingEffort
				//	if(successAct){
				//		get paid
				//		currLocation.removeShotCounter();
				//		if(currLocation.isSceneWrap()){
				//			
				//		}
				//	}else{
				//		if acting_type == extra --> pay
				//		
		}
	}


}
