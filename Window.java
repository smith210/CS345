import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JPanel{
 	//protected JButton b1, b2, b3, b4;
	private MyPanel panel;
	private MyPanelTwo buttonPanel;
	private Deadwood game;
   
	public Window(Deadwood game) {
		this.game = game;
		panel = new MyPanel();
		buttonPanel = new MyPanelTwo(game, panel);

		add(panel);  
		add(buttonPanel);

    }

    /*public Dimension getPreferredSize() {
        return new Dimension(200,900);
    }


	public void passPlayerDetails(Player p){
		panel.passPlayerDetails(game.currentPlayer());
	}

	public MyPanelTwo retrievePanel(){ return buttonPanel; }
	public MyPanel retrieveArt(){ return panel; }*/


}
