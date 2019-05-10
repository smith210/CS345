import java.util.*;

public class Set{
	private LinkedList<ExtraRole> actors;
	private int shotCounter;
	private Scene film;
	private boolean finishedShoot;
	
	Set(){
		shotCounter = 3;
		finishedShoot = false;
		//figure out how to initialize actors and scene

	}
	public void DecrementShotCounter(){
		shotCounter = shotCounter--;
		if(shotCounter== 0){
			finishedShoot = true;
			// check scene to see if there were any main actors
			// if so, do a bonus payout!
		}			
			
	}

	public int getShotCounter(){
		return shotCounter;
	}	

	private void scenePayout(){
		// we'll get there :)
	}

	public LinkedList<Work> findAvailWork(int playerLevel){

		LinkedList<Work> availActors = new LinkedList<Work>();
		
		LinkedList<MainRole> availMain = film.findMainActor(playerLevel); 
		
		for(int j = 0; j < availMain.size(); j++){
			MainRole currActor = availMain.get(j);
			availActors.add(currActor);
		}		
	
		for(int i = 0; i < actors.size(); i++ ){
			ExtraRole currActor = actors.get(i);

			if(!currActor.getWorkStatus() && currActor.getWorkLevel() <= playerLevel ){		
				availActors.add(currActor);
			}
		}

		return availActors; //returns a list of avail actors player can choose from 

	}

	public boolean isActSuccess(int roll){
		boolean actingAttempt = film.evaluateActing(roll);
		return actingAttempt;

	}
}











