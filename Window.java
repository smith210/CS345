import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JPanel{
 	//protected JButton b1, b2, b3, b4;
	private MyPanel panel;
	private MyPanelTwo buttonPanel;
   
    public Dimension getPreferredSize() {
        return new Dimension(200,900);
    }

	public MyPanelTwo retrievePanel(){ return buttonPanel; }
	public MyPanel retrieveArt(){ return panel; }

    public Window() {
		panel = new MyPanel();
		buttonPanel = new MyPanelTwo();

		add(panel);  
		add(buttonPanel);

    }



}
