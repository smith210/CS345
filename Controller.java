import java.util.*;

public class Controller{
	private String command;
	private LinkedList<String> neighborLocations;
	private Player player;
	private userInput model;
	private LinkedList<Work> jobsinArea;
	//private MyPanelTwo view;
	//private MyPanel design;
	private Window view;


	public Controller(userInput model, Window view){
		neighborLocations = new LinkedList<String>();
		command = new String();
		jobsinArea = new LinkedList<Work>();		

		this.model = model;
		this.view = view;
		
	}

	public void setPlayer(Player player){ 
		this.player = player;
		command = new String();
		view.passPlayerDetails(player);
		disableMove(player.isWorking());
		disableWork(player.getLocation().getSet());
		disableUpgrade(player.getLocation());
	}

	public void setJobsInArea(LinkedList<Work> jobs){ jobsinArea = jobs; }
	public void addNeighborName(String name){ neighborLocations.add(name); }
	public LinkedList<String> getNeighbors(){ return neighborLocations; }
	public void clearNeighbors(){ neighborLocations = new LinkedList<String>(); }


	public void redisplayImage(){ 
		view.passPlayerDetails(player);
	}
	public void disableUpgrade(Location loc){
		if(loc.getID() != 8){
			showHomeButton("Upgrade", false);
		}else{
			showHomeButton("Upgrade", true);
		}
	}
	public void disableMove(boolean isWorking){
		if(isWorking){
			showHomeButton("Move", false);
		}
	}
	public void finishedAction(String name){
		System.out.println("name false: "  + name);		
		showHomeButton(name, false);
	}

	private void disableWork(Set set){
		if(set.getShotCounter() == 0){
			showHomeButton("Work", false);
		}else{
			showHomeButton("Work", true);
		}
	}

	public void fixView(String command){//
		switch(command){
			case "MOVE":
				LinkedList<String> neighbors = model.getNeighbors();
				view.retrieveNeighbors(neighbors);
				view.movePanel();
				break;
			case "WORK":
				view.workPanel();
				break;
			case "ROLE":
				if(jobsinArea.size() == 0 && !player.isWorking()){
					setJobsInArea(player.getLocation().getSet().getAllActors());
					view.createJobButtons(jobsinArea, player.getLevel());
				}
				view.rolePanel();
				break;
			case "UPGRADE":
				break;
			case "NO":
				view.setDefaultScreen();
				break;
			case "END"://refresh move for the next player
				showHomeButton("Move", true);
				jobsinArea.clear();
			default:

		}
	}

	public void wrapup(){ view.setDefaultScreen(); }

	private void showHomeButton(String name, boolean visible){ view.setHomeButton(name, visible); }
	

	public void getCommand(){
		//System.out.println("WAITING");

		//view.retrieved();
		command = view.getCommand();
		model.setUserInput(command);
		//view.resetCommand();


	}
}
