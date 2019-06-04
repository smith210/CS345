import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RoleButtons implements ActionListener{

	private LinkedList<ButtonCreator> roles;

	public RoleButtons(){
		roles = new LinkedList<ButtonCreator>();
	}

	public void releaseRoles(){ roles = new LinkedList<ButtonCreator>(); }

	public void addButtons(LinkedList<Work> work){
		for(int i = 0; i < work.size(); i++){
			createRoleButton(work.get(i), i);
		}
	}

	private void createRoleButton(Work w, int ID){
		ButtonCreator actor = new ButtonCreator(w.getJobTitle());
		actor.setVisibility(false);
		actor.setCommand(Integer.toString(ID));
		if(w.getWorkStatus()){
			actor.setStatus(false);
		}else{
			actor.setStatus(true);
		}

		if(w.getWorkType().equals("MAIN")){
			//add celebrity icon next to button
		}
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
