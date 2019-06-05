import java.util.*;

public class Scene{
	private String sceneName;
	private int sceneNum;
	private LinkedList<MainRole> actors;
	private int movieBudget;
	private boolean isDisplayed;

	

	public void displayScene(){ isDisplayed = true; }
	public void setName(String sceneName){ this.sceneName = sceneName; }
	public void setID(int sceneID){ sceneNum = sceneID; }
	public void setBudget(int movieBudget){ this.movieBudget = movieBudget; }
	public void createMains(LinkedList<MainRole> actors){ this.actors = actors; } 
	public boolean getDisplayScene(){return isDisplayed;}
	public String getName(){ return sceneName; }
	public int getSceneNum(){ return sceneNum; }
	public int getBudget(){ return movieBudget; }

	public int numMainActors(){ return actors.size(); }

	public int mainActorID(Work person){
		int ID = 0;		
		while(ID != actors.size() && person.getJobTitle().equals(actors.get(ID).getJobTitle())){
			ID++;
		}
		if(ID == actors.size()){
			ID = -1;
		}
		return ID;
	}

	public int getActingHierarchy(MainRole actor){
		int position = 0;
		while(position != actors.size() && actors.get(position).getWorkLevel() != actor.getWorkLevel()){
			position++;
		}
		return position;
	}
	
	public boolean evaluateActing(int roll){

		if(movieBudget <= roll){
			return true;
		}else{
			return false;
		}
 	}

	public boolean hasMainActors(){
		int curr = 0;		
		while(curr != actors.size() && !actors.get(curr).getWorkStatus()){
			curr++;
		}
		if(curr != actors.size()){	
			return true;
		}else{
			return false;
		}
	}

	public String sceneDesc(){ return sceneName + ", Scene no. " + sceneNum; }

	public LinkedList<MainRole> getMainActors(){ return actors; }	

	public LinkedList<MainRole> findMainActor(int playerLevel){

		LinkedList<MainRole> availActors = new LinkedList<MainRole>();
		for(int i = 0; i < actors.size(); i++ ){
			MainRole currActor = actors.get(i);

			if(!currActor.getWorkStatus() && currActor.getWorkLevel() <= playerLevel ){
			
				availActors.add(currActor);
			}
		}
		return availActors; //returns a list of avail actors player can choose from 

	}

	public void displayContent(){
		System.out.println("Scene being filmed: " + sceneDesc());
		System.out.println("Budget of the film: $" + movieBudget + " million");

	}
}

