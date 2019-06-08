import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SwingPaint extends JFrame{
   
	private Color background;
   	private Window newContentPane;
	private Dimension size;
	private Deadwood game;


	SwingPaint(Deadwood game){
		background = new Color(222,220,231);
		newContentPane = new Window(game);
		size = new Dimension(1400,850);
		this.game = game;

	}

	public void createAndShowGUI() {
        String[] options = new String[]{"2","3"};
		int response = JOptionPane.showOptionDialog(null, "How Many Players?", "Deadwood",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
		game.setPlayerNum(Integer.parseInt(options[response]));

		

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
//		game.run();
    }

}
