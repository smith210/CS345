import javax.swing.*;
import java.awt.*;

public class SwingPaint{
   
	private Color background;
    private Window newContentPane;
	private Dimension size;

	SwingPaint(){
		background = new Color(222,220,231);
		newContentPane = new Window();
		size = new Dimension(1150,850);
	}

	public Window getDisplay(){return newContentPane;}

    public void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Deadwood");
		//f.add(new MyPanel());
		//newContentPane.setOpaque(true);
		f.setContentPane(newContentPane);
		f.getContentPane().setBackground(background);

		f.setPreferredSize(size);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}
