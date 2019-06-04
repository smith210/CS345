import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WorkButtons implements ActionListener{

	private ButtonCreator REHEARSE, ACT, FINISH;

	public WorkButtons(){
		REHEARSE = new ButtonCreator("Rehearse");
		ACT = new ButtonCreator("Act");
		FINISH = new ButtonCreator("Exit the Set");
		setup();
	}

	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> JWork = new LinkedList<JButton>();
			
		JButton rehearse = REHEARSE.getJButton();
		JWork.add(rehearse);

		JButton act = ACT.getJButton();
		JWork.add(act);

		JButton finish = FINISH.getJButton();
		JWork.add(finish);

		return JWork;

	}

	private void setup(){
		REHEARSE.setVisibility(false);
		ACT.setVisibility(false);
		FINISH.setVisibility(false);

		REHEARSE.setStatus(true);
		ACT.setStatus(true);
		FINISH.setStatus(true);

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
		FINISH.setVisibility(true);
	}

    public void actionPerformed(ActionEvent e) {
      	if ("REHEARSE".equals(e.getActionCommand())) {
			hide();
        } else if("ACT".equals(e.getActionCommand())) {
			hide();
		} else {
			FINISH.setVisibility(false);
		}
    }


}
