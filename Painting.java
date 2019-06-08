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
	private LinkedList<Image> moveView;
	//private LinkedList<Image> players;
	private Image background;
	private Image movingOpt;
	private Image credits = Toolkit.getDefaultToolkit().createImage("images/credits.png");
	private Image dollars = Toolkit.getDefaultToolkit().createImage("images/dollars.png");

	public Painting(){
		String[] backgroundStrings = {"train.JPG", "jail.JPG", "mainsqure.JPG", "trailer.JPG",
										"hotel.JPG", "church.JPG", "secret.JPG", "casting.JPG",
										"grocery.JPG", "saloon.JPG", "bank.JPG", "ranch.JPG"};
		String[] moveviewStrings = {"trainstation-move.png", "jail-move.png", "mainstreet-move.png",
									"trailer-move.png", "hotel-move.png", "church-move.png", 
									"secret-move.png", "casting-move.png", "general-move.png", 
									"saloon-move.png", "bank-move.png", "ranch-move.png"};
		//String[] playerString = {"deneke.jpg", "tanzima.jpg","jagodzinski.jpg"};
		backgrounds = new LinkedList<Image>();
		moveView = new LinkedList<Image>();
		//players = new LinkedList<Image>();
		//createImages(playerString, players);
		createImages(backgroundStrings, backgrounds);
		createImages(moveviewStrings, moveView);
		background = backgrounds.get(3);
		
	}

	public void setBackground(int ID){
		try{
			background = backgrounds.get(ID - 1); 
			movingOpt = moveView.get(ID - 1);
		}catch(Exception e){
			background = backgrounds.get(3);
			movingOpt = moveView.get(ID - 1);
		}
	}

	private void createImages(String[] filePaths, LinkedList<Image> photos){
		for(int i = 0; i < filePaths.length; i++){
			Image currPlayer = Toolkit.getDefaultToolkit().createImage("images/" + filePaths[i]);
			photos.add(currPlayer);
		}
	}

	private void printLine(Graphics g, String printable, int xCoord, int yCoord){
		g.drawString(printable, xCoord, yCoord);
	}

	private int yCoord(int spacing, int numLine){
		int yCoord = 575;
		return (yCoord + (spacing * numLine));
	}

	public void paintMoveDirection(Graphics g){
		g.drawImage(movingOpt, 0, 0, null);
	}

	public void paintLocDetails(Graphics g, Player p){
		int xCoordLoc = 430;
		int xCoordList = xCoordLoc + 25;
 		int spacing = 25;
		int spacingList = 22;
		int newLines = 0;

		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		//g.drawImage(players.get(index), 30, 555, null);

		Location loc = p.getLocation();
		g.setColor(Color.BLACK);
		
		printLine(g, p.getPlayerName() + " resides at the " + loc.getLocationName() + ".", xCoordLoc, yCoord(spacing, newLines));
		if(loc.getSet().getShotCounter() != 0){
		/*g.drawString("Player 1 has traveled to Main Street!", xCoord, yCoord+30);
		g.drawString("Player 1 decided to act on Main Street!", xCoord, yCoord+60);
		g.drawString("Player 1 decided to rest for the moment...", xCoord, yCoord+90);*/
			printLine(g, "Number of Counters: " + loc.getSet().getShotCounter(), xCoordLoc, yCoord(spacing, 4));
			printLine(g, "Scene of the Day: ", xCoordLoc, yCoord(spacing, 1));
			printLine(g, loc.getSet().getScene().sceneDesc(), xCoordList, yCoord(spacingList, 2));
			printLine(g, "Scene Budget: $" + loc.getSet().getScene().getBudget() + " million", xCoordLoc, yCoord(spacing, 3));

			if(p.isWorking()){
				printLine(g, "Have rehearsed " + p.getRehearsals() + " times on this scene", xCoordLoc, yCoord(spacing, 5));
			}
			
		}else{
			if(loc.getID() == 4){
				printLine(g, "Welcome to Deadwood, young actor!", xCoordLoc, yCoord(spacing, 1));
				printLine(g,"Travel around to get experience and monies!", xCoordLoc, yCoord(spacingList, 2));
			}else if(loc.getID() == 8){
				printLine(g,"If you have enough monies,", xCoordLoc, yCoord(spacing, 1));
				printLine(g,"you can upgrade your Acting Status" , xCoordLoc, yCoord(spacingList, 2));
			}else{
				printLine(g,"Seems like the scene at the " + loc.getLocationName() + " has wrapped for today.", xCoordLoc, yCoord(spacing, 1));
			}
		}


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

	private void drawOtherPlayer(Graphics g, LinkedList<Image> faces, Work job, int margin){
		int x = 600 - margin;
		int y = 205;
		switch(job.getWorkType()){
			case "MAIN":
				g.drawImage(faces.get(2), x, y, null);
				break;
			case "EXTRA":
				g.drawImage(faces.get(1), x, y, null);
				break;
			default:
				g.drawImage(faces.get(0), x, y, null);
				break;

		}
	}

	private void drawCurrentPlayer(Graphics g, LinkedList<Image> faces, Work job){
		int x = 120;
		int y = 205;
		switch(job.getWorkType()){
			case "MAIN":
				g.drawImage(faces.get(2), x, y, null);
				break;
			case "EXTRA":
				g.drawImage(faces.get(1), x, y, null);
				break;
			default:
				g.drawImage(faces.get(0), x, y, null);
				break;

		}
	}

	public void drawGamePieces(Graphics g, Player p, LinkedList<Player> spectators){
		int margin = 50;		
		Colors c = p.getSurroundings();		


		drawCurrentPlayer(g, c.getFaces(), p.getJob());
		for(int i = 0; i < spectators.size(); i++){
			Player observer = spectators.get(i);
			Colors observerC = observer.getSurroundings();
			drawOtherPlayer(g, observerC.getFaces(), observer.getJob() , margin * (i + 1));
		}
		
	}

	//public void 


	public void paintPlayer(Graphics g, Player p){
		g.drawImage(p.getSurroundings().getIcon(), 30, 555, null);
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

		g2D.setColor(new Color(234,232,228));
        g2D.fillRect(800,0,200,800);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(800,0,200,800);

		//g2D.drawImage(c.getIcon(), 30, 555, null);
    }
}

