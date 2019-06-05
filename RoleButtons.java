import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RoleButtons implements ActionListener{

	private LinkedList<ButtonCreator> roles;
	private int level;

	public RoleButtons(){
		roles = new LinkedList<ButtonCreator>();
		level = -1;
	}

	public void setLevel(int level){ this.level = level; }

	public void clearButtons(){ roles.clear(); }

	public int numButtons(){ return roles.size(); }

	public void releaseRoles(){ roles = new LinkedList<ButtonCreator>(); }
	
	public LinkedList<JButton> getButtons(){ 		
		LinkedList<JButton> JRole = new LinkedList<JButton>();
			
		for(int i = 0; i < roles.size(); i++){
			JRole.add(roles.get(i).getJButton());
		}

		return JRole; 
	}

	public void addButtons(LinkedList<Work> work){
		for(int i = 0; i < work.size(); i++){
			createRoleButton(work.get(i), i);
		}
	}

	private String switchType(String type){
		switch(type){
			case "MAIN":
				return "ACTOR - Celebrity";
			case "EXTRA":
				return "ACTOR - Extra";
			default:
				return "Unemployed";
		}
	}

	private void createRoleButton(Work w, int ID){
		ButtonCreator actor = new ButtonCreator(w.getJobTitle());
		actor.createTwoLineButton(switchType(w.getWorkType()),Integer.toString(w.getWorkLevel()));
		actor.setVisibility(false);
		actor.setCommand(Integer.toString(ID + 1));
		actor.setStatus(false);
		if(!w.getWorkStatus() && level >= w.getWorkLevel()){
			actor.setStatus(true);
		}

		/*if(w.getWorkType().equals("MAIN")){
			//add celebrity icon next to button
		}*/
		actor.changeSize(200,100);
		roles.add(actor);
	}

	public int getID(String name){
		int curr = 0;		
		while(curr != roles.size() && !name.equals(roles.get(curr).getName())){
			curr++;
		}
		if(curr == roles.size()){
			return -1;
		}else{
			return curr;
		}
	}

	public void display(boolean v){
		for(int i = 0; i < roles.size(); i++){
			roles.get(i).setVisibility(v);
		}
	}

    public void actionPerformed(ActionEvent e) {
      	
    }


}
