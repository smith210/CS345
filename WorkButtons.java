import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WorkButtons implements ActionListener{

	private ButtonCreator REHEARSE, ACT, FINISH;

	public WorkButtons(){
		REHEARSE = new ButtonCreator("Rehearse");
		ACT = new ButtonCreator("Act");
		setup();
	}

	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> JWork = new LinkedList<JButton>();
			
		JButton rehearse = REHEARSE.getJButton();
		JWork.add(rehearse);

		JButton act = ACT.getJButton();
		JWork.add(act);

		return JWork;

	}

	public void hitMaxRehearsals(int rehearsals, int budget){
		if(rehearsals >= budget){
			REHEARSE.setStatus(false);
			REHEARSE.createOneLineButton("Stop rehearsing, and start acting!", "DOWN");
		}else{
			REHEARSE.setStatus(true);
		}
	}

	private void setup(){
		REHEARSE.setVisibility(false);
		ACT.setVisibility(false);

		REHEARSE.setStatus(true);
		ACT.setStatus(true);

		REHEARSE.setCommand("REHEARSE");
		ACT.setCommand("ACT");
	}

	public void disableRehearse(){ REHEARSE.setStatus(false); }
	public void enableRehearse(){ REHEARSE.setStatus(true); }
	
	public void start(){
		REHEARSE.setVisibility(true);
		ACT.setVisibility(true);
	}

	private void hide(){
		REHEARSE.setVisibility(false);
		ACT.setVisibility(false);
	}

    public void actionPerformed(ActionEvent e) {
      	if ("REHEARSE".equals(e.getActionCommand())) {
			hide();
        } else if("ACT".equals(e.getActionCommand())) {
			hide();
		} else {

		}
    }


}
