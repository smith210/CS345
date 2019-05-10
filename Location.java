public class Location{
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
