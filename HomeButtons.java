import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HomeButtons implements ActionListener{
	//RedSquare redSquare = new RedSquare();
	private ButtonCreator MOVE, WORK, UPGRADE, END;

	public HomeButtons(){
		MOVE = new ButtonCreator("Move");
		WORK = new ButtonCreator("Work");
		UPGRADE = new ButtonCreator("Upgrade");
		END = new ButtonCreator("End");
		setup();
	}

	private void setup(){
		display(true);

		MOVE.setCommand("MOVE");
		WORK.setCommand("WORK");
		UPGRADE.setCommand("UPGRADE");
		END.setCommand("END");

		MOVE.setStatus(true);
		WORK.setStatus(false);
		UPGRADE.setStatus(false);
		END.setStatus(true);
	}

	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> JHome = new LinkedList<JButton>();
			
		JButton move = MOVE.getJButton();
		JHome.add(move);

		JButton work = WORK.getJButton();
		JHome.add(work);

		JButton upgrade = UPGRADE.getJButton();
		JHome.add(upgrade);

		JButton end = END.getJButton();
		JHome.add(end);

		return JHome;

	}

	public void disableMove(){ MOVE.setStatus(false); }
	public void disableUpgrade(){UPGRADE.setStatus(false); }
	public void disableWork(){WORK.setStatus(false); }
	public void enableMove(){ MOVE.setStatus(true); }
	public void enableUpgrade(){UPGRADE.setStatus(true); }
	public void enableWork(){WORK.setStatus(true); }

	public void display(boolean wantShow){
			MOVE.setVisibility(wantShow);
			WORK.setVisibility(wantShow);
			UPGRADE.setVisibility(wantShow);
			END.setVisibility(wantShow);
	}

    public void actionPerformed(ActionEvent e) {
        if ("MOVE".equals(e.getActionCommand())) {
			display(false);
        } else if("WORK".equals(e.getActionCommand())) {
			display(false);
		} else if("UPGRADE".equals(e.getActionCommand())){
			display(false);
		} else {
			//update for next player
		}
    }

}
