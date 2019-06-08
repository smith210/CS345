import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MoveButtons{
	//RedSquare redSquare = new RedSquare();
	private LinkedList<ButtonCreator> locationButtons;
	public MoveButtons(){
		locationButtons = new LinkedList<ButtonCreator>();
	}

	public void clearButtons(){ 
			locationButtons.clear(); 
	}

	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> JMove = new LinkedList<JButton>();
			
		for(int i = 0; i < locationButtons.size(); i++){
			JMove.add(locationButtons.get(i).getJButton());
		}

		return JMove;

	}

	public void addButtons(LinkedList<Location> neighbors){
		for(int i = 0; i < neighbors.size(); i++){
			createRoleButton(neighbors.get(i));
		}
	}


	private void createRoleButton(Location opt){
		ButtonCreator neighbor = new ButtonCreator(opt.getLocationName());
		if(opt.getSet().getShotCounter() != 0){
			String plural = "";			
			if(opt.getSet().getShotCounter() != 1){
				plural = "s";
			}
			neighbor.createOneLineButton(opt.getSet().getShotCounter() + " Shot" + plural + " Remaining", "UP");//shotCounterDisplay(opt.getSet().getShotCounter()));
		}
		neighbor.setVisibility(false);
		neighbor.setCommand(Integer.toString(opt.getID()));
		neighbor.setStatus(true);

		/*if(w.getWorkType().equals("MAIN")){
			//add celebrity icon next to button
		}*/
		//actor.changeSize(200,100);
		locationButtons.add(neighbor);
	}

	public int getID(String name){
		int curr = 0;		
		while(curr != locationButtons.size() && !name.equals(locationButtons.get(curr).getName())){
			curr++;
		}
		if(curr == locationButtons.size()){
			return -1;
		}else{
			return curr;
		}
	}



	public void hide(int ID){
		locationButtons.get(ID).setVisibility(false);
	}

	public void show(int ID){
		locationButtons.get(ID).setVisibility(true);
	}

    public void actionPerformed(ActionEvent e) {
        
    }

}
