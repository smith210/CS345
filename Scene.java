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
	
	public boolean evaluateActing(int roll){

		if(movieBudget <= roll){
			return true;
		}else{
			return false;
		}
 	}

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
}

