import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Painting{

    private int xPos = 0;
    private int yPos = 532;
    private int width = 400;
    private int height = 400;

    private int MxPos = 400;
    private int MyPos = 532;
    private int Mwidth = 400;
    private int Mheight = 400;

	private Color navyBlue = new Color(4,166,91);
	private	Color purplish = new Color(0,94,57);
	private Color tan = new Color(222,220,231);
	private Color sunflower = new Color(200, 141, 37);

	private Image background;
	private LinkedList<Image> players;
	private Image credits = Toolkit.getDefaultToolkit().createImage("images/credits.png");;
	private Image dollars = Toolkit.getDefaultToolkit().createImage("images/dollars.png");;

	public Painting(){
		background = Toolkit.getDefaultToolkit().createImage("images/cf.jpg");
		String[] playerString = {"images/deneke.jpg", "images/tanzima.jpg","images/jagodzinski.jpg"};
		players = new LinkedList<Image>();
		createPlayers(playerString);
		
	}

	private void createPlayers(String[] filePaths){
		for(int i = 0; i < filePaths.length; i++){
			Image currPlayer = Toolkit.getDefaultToolkit().createImage(filePaths[i]);
			players.add(currPlayer);
		}
	}

	public void paintLocDetails(Graphics g, String desc){
		int xCoord = 430;
		int yCoord = 575;
		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		//g.drawImage(players.get(index), 30, 555, null);

		g.setColor(Color.WHITE);
		g.drawString("Player 1 is thinking in the " + desc +"...", xCoord, yCoord);
		/*g.drawString("Player 1 has traveled to Main Street!", xCoord, yCoord+30);
		g.drawString("Player 1 decided to act on Main Street!", xCoord, yCoord+60);
		g.drawString("Player 1 decided to rest for the moment...", xCoord, yCoord+90);*/


	}

	
	private void moveOptions(Graphics g, int x, int y, int w, int h){
		x = w/2; //margin
		y = h/3; //margin		
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 28));
		g.setColor(Color.WHITE);
		g.drawString("Main Street", x, y + 40);
		g.drawString("Hotel", x, y + 80);  
		g.drawString("Saloon", x, y + 120);
	}

	public void paintOptionsBox(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		
		int smallXCoord = 100;
		int smallYCoord = 65;
		int smallWidth = 600;
		int smallHeight = 425;

		int offSet = 12;
		int offSize = offSet * 2;
		int offXCoord = smallXCoord - offSet;
		int offYCoord = smallYCoord - offSet;
		int offWidth = smallWidth + offSize;
		int offHeight = smallHeight + offSize;


		/*g2D.setStroke(new BasicStroke(5));
		g2D.setColor(sunflower);
        g2D.fillRect(offXCoord, offYCoord, offWidth, offHeight);
        g2D.setColor(navyBlue);
        g2D.fillRect(smallXCoord,smallYCoord,smallWidth,smallHeight);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(offXCoord, offYCoord, offWidth, offHeight);
		
		moveOptions(g, smallXCoord, smallYCoord, smallWidth, smallHeight);*/

	}


	public void paintPlayer(int index, Graphics g, Player p){
		int xCoord = 210;
		int yCoord = 575;
		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		g.drawImage(players.get(index), 30, 555, null);

		g.setColor(Color.WHITE);
		g.drawString("Name:", xCoord, yCoord);
		g.drawString(p.getPlayerName(), xCoord + 25, yCoord+20);
		g.drawString("Occupation:", xCoord, yCoord + 50);  
		g.drawString(p.getJob().getJobTitle(), xCoord + 25, yCoord+70);
		g.drawString("Experience:", xCoord, yCoord + 100);  
		g.drawString("Level " + p.getLevel(), xCoord + 25, yCoord + 120);  
		g.drawString("Currently Carrying:", xCoord, yCoord + 150);  

		Wallet w = p.evalWalletContent();
		g.drawImage(credits,xCoord, yCoord+155,null);
		g.drawImage(dollars,xCoord, yCoord+175,null);
		g.drawString(w.getCredits() + " Credits", xCoord + 25, yCoord + 170);
		g.drawString(w.getDollars() + " Dollars", xCoord + 25, yCoord + 190);  
	}
	public void paintBackground(Graphics g){

		g.drawImage(background, 0, 0, null);

	}

    public void paintSquare(Graphics g){
		Graphics2D g2D = (Graphics2D) g;

		g2D.setStroke(new BasicStroke(4));
        g2D.setColor(navyBlue);
        g2D.fillRect(xPos,yPos,width,height);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(xPos,yPos,width,height);

        g2D.setColor(purplish);
        g2D.fillRect(MxPos,MyPos,Mwidth,Mheight);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(MxPos,MyPos,Mwidth,Mheight);
    }
}

