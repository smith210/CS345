import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyPanel extends JPanel{

	private Painting easel;
	private LinkedList<Player> persons;

	private Player perspective;
	private LinkedList<Player> spectators;

	private boolean lookingForDirect;

	public MyPanel(LinkedList<Player> persons) {
        setBorder(BorderFactory.createLineBorder(Color.black, 5));
		easel = new Painting();
		perspective = new Player();
		this.persons = persons;
		spectators = new LinkedList<Player>();
		lookingForDirect = false;
		/*addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });*/
    }

	private boolean onSameLocation(Player p){
		if(p.getLocation().getID() == perspective.getLocation().getID() && p.getID() != perspective.getID()){
			return true;
		}else{
			return false;
		}
	}

	private void otherPlayers(){
		spectators.clear();
		for(int i = 0; i < persons.size(); i++){
			if(onSameLocation(persons.get(i))){
				spectators.add(persons.get(i));
			}	
		}
	}	

	public void passPlayerDetails(Player perspective){
		this.perspective = perspective;
		otherPlayers();
		repaint();
	}

	public void passCommand(String command){
		if(command.equals("MOVE")){
			lookingForDirect = true;
		}
		repaint();
	}

	//public void removePlayer(){ perspective = new Player(); }

	
   /* private void moveSquare(int x, int y) {
         // Current square state, stored as final variables 
        // to avoid repeat invocations of the same methods.
        final int CURR_X = redSquare.getX();
        final int CURR_Y = redSquare.getY();
        final int CURR_W = redSquare.getWidth();
        final int CURR_H = redSquare.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background 
            // over the old square location. 
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);
				
            // Update coordinates.
            redSquare.setX(x);
            redSquare.setY(y);

            // Repaint the square at the new location.
            repaint(redSquare.getX(), redSquare.getY(), 
                    redSquare.getWidth()+OFFSET, 
                    redSquare.getHeight()+OFFSET);
        }
    }*/

    public Dimension getPreferredSize() {
        return new Dimension(1000,800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
		Colors splash = perspective.getSurroundings();
		easel.setBackground(perspective.getLocation().getID());
		easel.paintSquare(splash, g);
		easel.paintBackground(g);
		easel.drawGamePieces(g, perspective, spectators);
		if(lookingForDirect){
			easel.paintMoveDirection(g);
			lookingForDirect = false;
		}
		easel.paintPlayer(g, perspective);
		easel.paintLocDetails(g, perspective);
		//easel.paintOptionsBox(g);
    }  
}
