import java.util.*;

public class Scene{
	private LinkedList<MainRole> actors;
	private int movieBudget;
	private boolean isDisplayed;

	public void displayScene(){

		isDisplayed = true;

	}
	
	public boolean ifActSucceeded(int roll){

		if(movieBudget <= roll){
			return true;
		}else{
			return false;
		}
 	}

	public LinkedList<MainRole> findMainActor(int playerLevel){

		LinkedList<MainRole> availActors = new LinkedList<MainRole>;
		for(int i = 0; i < actors.size(); i++ ){
			MainRole currActor = actors.get(i);

			if(!currActor.getWorkStatus() && currActor.getWorkLevel() <= playerLevel ){
			
				availActors.add(currActor);
			}
		}
		return availActors; //returns a list of avail actors player can choose from 

	}
}

