import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException, invalidPlayerException {
		Deadwood game = new Deadwood();		
		SwingPaint GUI = new SwingPaint(game);	
		GUI.createAndShowGUI();
		game.run();	

	}
}
