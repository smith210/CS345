public class Location{
	private final int rowMax = 7;
	private final int colMax = 5;

	private String name;
	private Set workSpace;
	private int rowNum;
	private int colNum;

	Location(){
		name = "";
		workSpace = new Set();
		rowNum = 0;
		colNum = 0;
	}
	
	//will remove function later for functionality
	public void getTrailer(){
		setName("Trailer");
		rowNum = 2;
		colNum = 3;
	}

	public void setName(String setName){
		name = setName;
	}

	public String getLocationName(){
		return name;
	}

	public Set getSet(){
		return workSpace;
	}

	public boolean isPlayableSet(){
		boolean workableSet = true;
		if(name.equals("trailer") || name.equals("casting office")){
			workableSet = false;
		}
		return workableSet;

	}

	

}
