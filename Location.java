import java.util.*;

public class Location{

	private String name;
	private Set workSpace;
	private int ID;
	private LinkedList<Location> neighbors;

	Location(){
		name = "";
		workSpace = new Set();
		ID = 0;
		neighbors = new LinkedList<Location>();
	}

	public boolean hasMainActors(){ return workSpace.hasMains(); }

	public void setID(int ID){ this.ID = ID; }

	public void setName(String setName){ name = setName; }

	public void setNeighbors(LinkedList<Location> neighbors){ this.neighbors = neighbors; }

	public boolean doneFilming(){ return workSpace.getShotCounter() == 0; }

	public LinkedList<Work> getJobs(){ return workSpace.getAllActors(); }

	public String getLocationName(){ return name; }

	public Set getSet(){ return workSpace; }
	
	public int getID(){ return ID; }

	public LinkedList<Location> getNeighbors(){ return neighbors; }

	public void displayContent(String userName){
		System.out.println(userName + " on the " + name + ".");
		if(workSpace.getShotCounter() != 0){
			System.out.println("Currently it's shooting " + workSpace.getScene().sceneDesc());
		}
	
	}


	

}
