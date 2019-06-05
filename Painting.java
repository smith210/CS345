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

	private LinkedList<Image> backgrounds;
	//private LinkedList<Image> players;
	private Image background;
	private Image credits = Toolkit.getDefaultToolkit().createImage("images/credits.png");;
	private Image dollars = Toolkit.getDefaultToolkit().createImage("images/dollars.png");;

	public Painting(){
		String[] backgroundStrings = {"train.JPG", "jail.JPG", "mainsqure.JPG", "trailer.JPG",
										"hotel.JPG", "church.JPG", "secret.JPG", "casting.JPG",
										"grocery.JPG", "saloon.JPG", "bank.JPG", "ranch.JPG"};
		//String[] playerString = {"deneke.jpg", "tanzima.jpg","jagodzinski.jpg"};
		backgrounds = new LinkedList<Image>();
		//players = new LinkedList<Image>();
		//createImages(playerString, players);
		createImages(backgroundStrings, backgrounds);

		background = backgrounds.get(3);
		
	}

	public void setBackground(int ID){
		try{
			background = backgrounds.get(ID - 1); 
		}catch(Exception e){
			background = backgrounds.get(3);
		}
	}

	private void createImages(String[] filePaths, LinkedList<Image> photos){
		for(int i = 0; i < filePaths.length; i++){
			Image currPlayer = Toolkit.getDefaultToolkit().createImage("images/" + filePaths[i]);
			photos.add(currPlayer);
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


	public void paintPlayer(Graphics g, Player p){
		int xCoord = 210;
		int yCoord = 575;
		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		//g.drawImage(players.get(currPlayer), 30, 555, null);

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
		g.drawImage(background, 0, 0, 800, 532, null);
	}

    public void paintSquare(Colors c, Graphics g){
		Graphics2D g2D = (Graphics2D) g;

		g2D.setStroke(new BasicStroke(4));
        g2D.setColor(c.getPrimary());
        g2D.fillRect(xPos,yPos,width,height);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(xPos,yPos,width,height);

        g2D.setColor(c.getSecondary());
        g2D.fillRect(MxPos,MyPos,Mwidth,Mheight);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(MxPos,MyPos,Mwidth,Mheight);

		g2D.drawImage(c.getIcon(), 30, 555, null);
    }
}

