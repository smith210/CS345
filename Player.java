import java.util.*;
import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.accessibility.*;

public class Player{

	private String playerName;
	private int playerID;
	private int actorLevel, rehearsals;
	private Location currLocation;
	private Wallet myWallet;
	private boolean activeActor;
	private Work jobDescription;
	private Colors surroundings;

	public Player(){
		playerName = "";
		playerID = -1;
		actorLevel = 1;
		rehearsals = 0;
		activeActor = false;
		currLocation = new Location();
		myWallet = new Wallet();
		jobDescription = new Work();
		surroundings = new Colors();
	}

	public void setID(int playerID){ this.playerID = playerID;}
	public int getID(){ return playerID;}

	public void setColors(Colors surroundings){ this.surroundings = surroundings; }

	public void setLocation(Location location){ currLocation = location; }

	public void setPlayerName(String name){ playerName = name; }

	public void setActorLevel(int actorLevel){this.actorLevel = actorLevel;}

	public void setJob(Work jobDescription){ 
		this.jobDescription = jobDescription; 
		activeActor = true;
	}
	
	public void unbuffActingStatus(boolean activeActor){ this.activeActor = activeActor; }

	public Colors getSurroundings(){ return surroundings; }

	public String getPlayerName(){ return playerName; }
	
	public int getLevel(){ return actorLevel; }

	public Location getLocation(){ return currLocation; }

	public Work getJob(){ return jobDescription; }

	public boolean isWorking(){ return activeActor; }

	public Wallet evalWalletContent(){ return myWallet; }

	public int getRehearsals(){ return rehearsals; }


	public void pay(int cash, String type){
		cash = cash * -1;		
		if(type.equals("DOLLAR")){
			myWallet.addDollars(cash);
		}else if(type.equals("CREDIT")){
			myWallet.addCredits(cash);
		}else{//error
		
		}
	}

	public void bonusPayout(LinkedList<Integer> rolls){
		switch(jobDescription.getWorkType()){
			case "MAIN":
				int numMain = currLocation.getSet().numMainRoles();
				int mainIdx = currLocation.getSet().getScene().getActingHierarchy((MainRole)jobDescription);
				int currHandout = 0;				
				for(int i = 0; i > rolls.size(); i++){
					if(mainIdx == currHandout){
						myWallet.addDollars(rolls.get(i));
					}
					currHandout++;
					if(currHandout == numMain){
						currHandout = 0;
					}
				}
			case "EXTRA":
				myWallet.addDollars(jobDescription.getWorkLevel());
			default://might of been on, but not acting
		}
	}

	public int rehearse(){
		if(rehearsals + 1  <= currLocation.getSet().getScene().getBudget()){
			rehearsals++;
			return rehearsals;
		}else{
			return -1;
		}
	}

	public boolean validActing(int roll){ return currLocation.getSet().isActSuccess(roll + rehearsals);}
	public int totalRoll(int roll){ return roll + rehearsals; }

	public void act(int roll){
		String actorType = jobDescription.getWorkType();
		boolean isSucc = currLocation.getSet().isActSuccess(roll + rehearsals);
		
		if(isSucc){
			System.out.println("You acted succesfully!" );
			switch(actorType){
				case "MAIN":
					myWallet.addCredits(2);
					break;
				case "EXTRA":
					myWallet.addDollars(1);
					myWallet.addCredits(1);
					break;
				default:
			}
			
			currLocation.getSet().decrementShotCounter();					

		}else{
			System.out.println("You suck at acting. " );
			switch(actorType){
				case "MAIN":
					System.out.println("You do not recieve any currency.");
					break;
				case "EXTRA":
					myWallet.addDollars(1);
					break;
				default:
			}
		}
	}

	public void removeWork(){
		System.out.println(playerName + " has finished working at " + currLocation.getLocationName() + ".");
		activeActor = false;
		rehearsals = 0;
		jobDescription = new Work();
	}

	public void playerInformation(){
		System.out.println("You are " + playerName + ", a Level " + actorLevel + " actor.");
		System.out.println("You are at the " + currLocation.getLocationName() + ".");
		if(!activeActor){
			System.out.println("You are currently not working.");
		}else{
			System.out.println("You are currently working on " + jobDescription.getJobTitle());
		}
	}

	public void playerLocation(String userView){
		System.out.print(userView + " on the " + currLocation.getLocationName());
		if(activeActor){
			System.out.print(", working");
		}
		System.out.println(".");
	}
}
