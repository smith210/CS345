import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JPanel{
 	//protected JButton b1, b2, b3, b4;
	private MyPanel panel;
	private MyPanelTwo buttonPanel;
   
	public Window() {
		panel = new MyPanel();
		buttonPanel = new MyPanelTwo();

		add(panel);  
		add(buttonPanel);

    }

    public Dimension getPreferredSize() {
        return new Dimension(200,900);
    }

	public boolean containsNoJobs(){ return buttonPanel.rolesEmpty(); }

	public String getCommand(){ return buttonPanel.getCommand(); }
	public void resetCommand(){ buttonPanel.resetCommand(); }

	public void movePanel(){ buttonPanel.movePanel();}
	public void rolePanel(){ buttonPanel.rolePanel();}
	public void workPanel(){ buttonPanel.workPanel();}
	public void retrieveNeighbors(LinkedList<String> neighbors){ buttonPanel.retrieveNeighbors(neighbors); }
	public void createJobButtons(LinkedList<Work> jobs, int level){ buttonPanel.setRoleButtons(jobs, level); }	

	public void passPlayerDetails(Player p){
		panel.passPlayerDetails(p);
	}
	public void setHomeButton(String name, boolean view){ buttonPanel.toggleHomeButton(name, view); }
	public void setDefaultScreen(){ buttonPanel.setHomeScreen(); }

	public MyPanelTwo retrievePanel(){ return buttonPanel; }
	public MyPanel retrieveArt(){ return panel; }


}
