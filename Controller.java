import java.util.*;

public class Controller{
	private String command;
	private LinkedList<String> neighborLocations;
	private boolean packageModel;
	private boolean packageView;
	private userInput model;
	private MyPanelTwo view;
	private MyPanel design;


	public Controller(userInput model, MyPanelTwo view, MyPanel design){
		neighborLocations = new LinkedList<String>();
		command = new String();
		packageModel = false;
		packageView = false;
		this.model = model;
		this.view = view;
		this.design = design;
		//waitForEvent();
	}

	public void setModel(userInput model){ this.model = model; }
	public void setView(MyPanelTwo vies){   this.view = view;  }
	public boolean hasModel(){return packageModel;}
	public boolean hasView(){return packageView;}
	public void bufModel(){packageModel = true;}
	public void bufView(){packageView = true;}
	public void rest(){
		packageModel = false;
		packageView = false;
	}

	public void addNeighborName(String name){ neighborLocations.add(name); }
	public LinkedList<String> getNeighbors(){ return neighborLocations; }
	public void clearNeighbors(){ neighborLocations = new LinkedList<String>(); }

	public void redisplayImage(Player p){ design.passPlayerDetails(p);
											design.repaint(); }

	public void fixView(String command){
		switch(command){
			case "MOVE":
				LinkedList<String> neighbors = model.getNeighbors();
				view.retrieveNeighbors(neighbors);
				view.movePanel();
				break;
			case "WORK":
				break;
			case "UPGRADE":
				break;
			case "NO":
				break;			
			default:

		}
	}

	public void wrapup(){ view.setHomeScreen(); }

	public void disableMove(){ view.disableHome("Move"); }

	/*public void setCommand(String command){ this.command = command;}
	public String sendCommand(){ return command; }*/
	public void getCommand(){
		//System.out.println("WAITING");
		if(view.hasSelected()){
			view.retrieved();
			command = view.getCommand();
			model.setUserInput(command);
		}
	}
}
