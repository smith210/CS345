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

	public void setID(int ID){ this.ID = ID; }

	public void setName(String setName){ name = setName; }

	public void setNeighbors(LinkedList<Location> neighbors){ this.neighbors = neighbors; }

	public String getLocationName(){ return name; }

	public Set getSet(){ return workSpace; }
	
	public int getID(){ return ID; }

	public LinkedList<Location> getNeighbors(){ return neighbors; }


	

}
