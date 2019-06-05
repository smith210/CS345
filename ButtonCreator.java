import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonCreator{// implements ActionListener{
	private Dimension buttonSize;
	private String buttonName;
	private Icon i;	
	protected JButton button;

	public ButtonCreator(String name){

		//i = new ImageIcon("images/ticket.png");
 		button = new JButton(name);
		changeSize(200, 125);
		buttonName = name;
		System.out.println("adding button " + name);
		//add(button);
		//button.addActionListener(this);
	}

	public void changeSize(int w, int h){
		button.setPreferredSize(new Dimension(w, h));
	}

	public void createTwoLineButton(String one, String two){
		button.setLayout(new BorderLayout());
		JLabel topLabel = new JLabel(one);
		JLabel bottomLabel = new JLabel(two);
		button.add(BorderLayout.NORTH, topLabel);
		button.add(BorderLayout.SOUTH, bottomLabel);
	}

	public JButton getJButton(){ return button; }

	public String getName(){ return buttonName; }

	public void setStatus(boolean active){
		if(active){
			button.setEnabled(true);
		}else{
			button.setEnabled(false);
		}
	}

	public void setVisibility(boolean visible){
		if(visible){
			button.setVisible(true);
		}else{
			button.setVisible(false);
		}
	}

	public void setCommand(String command){
		System.out.println("COMMAND: " + command);
		button.setActionCommand(command);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Window.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}
