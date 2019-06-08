import java.util.*;

public class Set{
	private LinkedList<ExtraRole> actors;
	private int shotCounter;
	private Scene film;
	
	Set(){
		shotCounter = 0;
		film = new Scene();
		actors = new LinkedList<ExtraRole>();
	}

	public boolean hasMains(){ return film.hasMainActors(); }
	public int numMainRoles(){ return film.getMainActors().size(); }

	public void createExtras(LinkedList<ExtraRole> actors){ this.actors = actors; }
	public void setScene(Scene film){ this.film = film; }
	public Scene getScene(){ return film; }

	public void setShotCounter(int shots){ shotCounter = shots; }

	public void decrementShotCounter(){
		if(shotCounter != 0){
			shotCounter = shotCounter - 1;
		}	
	}

	public void getSceneDesc(){ film.displayContent(); }

	public int getShotCounter(){ return shotCounter; }	

	public LinkedList<Work> getAllActors(){
		LinkedList<Work> allActors = new LinkedList<Work>();
		allActors.addAll(film.getMainActors());		
		allActors.addAll(actors);
		return allActors;
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











