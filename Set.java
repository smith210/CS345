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

	public void createExtras(LinkedList<ExtraRole> actors){ this.actors = actors; }
	public void setScene(Scene film){ this.film = film; }
	public Scene getScene(){ return film; }

	public void setShotCounter(int shots){ shotCounter = shots; }

	public void decrementShotCounter(){
		if(shotCounter != 0){
			shotCounter = shotCounter - 1;
		}	
	}

	public String getSceneDesc(){ return film.getName() + " scene no. " + film.getSceneNum(); }

	public int getShotCounter(){ return shotCounter; }	

	public LinkedList<Work> listAllWork(){
		LinkedList<Work> actingJobs = new LinkedList<Work>();
		LinkedList<MainRole> main = film.getMainActors();
		for(int i = 0; i < main.size(); i ++){
			actingJobs.add(main.get(i));
		}
		for(int j = 0; j < actors.size(); j++){
			actingJobs.add(actors.get(j));
		}
		return actingJobs;
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











