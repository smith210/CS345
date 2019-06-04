import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MoveButtons{
	//RedSquare redSquare = new RedSquare();
	private LinkedList<ButtonCreator> locationButtons;
	public MoveButtons(){
		locationButtons = new LinkedList<ButtonCreator>();

		ButtonCreator TRAIN = new ButtonCreator("Train");
		locationButtons.add(TRAIN);

		ButtonCreator JAIL = new ButtonCreator("Jail");
		locationButtons.add(JAIL);

		ButtonCreator MAINSTREET = new ButtonCreator("Main Street");
		locationButtons.add(MAINSTREET);

		ButtonCreator TRAILER = new ButtonCreator("Trailer");
		locationButtons.add(TRAILER);

		ButtonCreator HOTEL = new ButtonCreator("Hotel");
		locationButtons.add(HOTEL);

		ButtonCreator CHURCH = new ButtonCreator("Church");
		locationButtons.add(CHURCH);

		ButtonCreator SECRETHIDE = new ButtonCreator("Secret Hideout");
		locationButtons.add(SECRETHIDE);

		ButtonCreator CASTING = new ButtonCreator("Casting Office");
		locationButtons.add(CASTING);

		ButtonCreator GENSTORE = new ButtonCreator("General Store");
		locationButtons.add(GENSTORE);

		ButtonCreator SALOON = new ButtonCreator("Saloon");
		locationButtons.add(SALOON);

		ButtonCreator BANK = new ButtonCreator("Bank");
		locationButtons.add(BANK);

		ButtonCreator RANCH = new ButtonCreator("Ranch");
		locationButtons.add(RANCH);
		
		setup();
	}

	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> JMove = new LinkedList<JButton>();
			
		for(int i = 0; i < locationButtons.size(); i++){
			JMove.add(locationButtons.get(i).getJButton());
		}

		return JMove;

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

	private void setup(){
		for(int i = 1; i < locationButtons.size() + 1; i++){
			locationButtons.get(i-1).setVisibility(false);
			locationButtons.get(i-1).setCommand(Integer.toString(i));
			locationButtons.get(i-1).setStatus(true);
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
