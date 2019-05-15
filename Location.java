public class Location{

	private String name;
	private Set workSpace;
	private int ID;
	private int[] neighbors;

	Location(){
		name = "";
		workSpace = new Set();
		ID = 0;
		neighbors = new int[4];
	}

	public void setID(int ID){ this.ID = ID; }

	public void setName(String setName){ name = setName; }

	public void setNeighbors(int[] neighbors){ this.neighbors = neighbors; }

	public String getLocationName(){ return name; }

	public Set getSet(){ return workSpace; }
	
	public int getID(){ return ID; }

	public boolean isPlayableSet(){
		boolean workableSet = true;
		if(name.equals("trailer") || name.equals("casting office")){
			workableSet = false;
		}
		return workableSet;

	}

	

}
