import java.util.*;

public class Board{
	private GameTiles tiles;
	private SceneDeck deck;
	private int round;
	
	public Board(){
		tiles = new GameTiles();//where the locations and extra actors generate
		deck = new SceneDeck();//where the scenes and main actors generate
		round = 3;
	}
	
	public Board(int playerNum){
		tiles = new GameTiles();
		round = 3;
	}

	public void setRound(int round){ this.round = round; }

	public int getRound(){
		return round;
	}

	public void nextRound(){
		round = round - 1;
	}

	public Location getTrailer(){ return tiles.get(4); }

	public boolean hasFinishedRound(){ return tiles.isBoardWrapped(); }

	public boolean hasFinishedGame(){ return round == 0; }

	public void prepBoard(){
		tiles.resetBoard();
		tiles.drawScenes(deck);
	}


/*	
	public userInput getQueries(){ return query; }

	private void returnToTrailer(){
		for(int i = 0; i < users.size(); i++){
			users.get(i).setLocation(tiles.get(4));
		}
	}

	private void createPlayers(int playerNum){
		ColorTemplates picture = new ColorTemplates();
		for(int i = 0; i < playerNum; i++){
			Player temp = new Player();
			temp.setColors(picture.get(i));
			//temp.setQuery(query);
			temp.setPlayerName("Player " + (i+1));
			temp.setLocation(tiles.get(4));
			users.add(temp);
		}
	}

	private void displayOtherPlayers(int asker){
		for(int i = 0; i < users.size(); i++){
			if(i != asker){
				users.get(i).playerLocation(users.get(i).getPlayerName() + " is");
			}
		}
	}


	private void role(Player p){
		cont.fixView("ROLE");			
		//cont.createJobButtons();
		while(query.getIntInput() == 0){
			cont.getCommand();	
		}
		if(query.getIntInput() != -1){
			int ID = query.getIntInput() - 1;
			Work newHire = p.getLocation().getSet().getAllActors().get(ID);
			p.setJob(newHire);
			newHire.bufWork();
			p.buffActingStatus(true);
			cont.finishedAction("Work");
		}
		//cont.wrapup();

	}

	private void act(Player p, boolean succAct){
		if(succAct){
			System.out.println("You acted succesfully!" );
			switch(p.getJob().getWorkType()){
				case "MAIN":
					p.evalWalletContent().addCredits(2, "You");
					break;
				case "EXTRA":
					p.evalWalletContent().addDollars(1, "You");
					p.evalWalletContent().addCredits(1, "You");
					break;
				default:
			}
			
			p.getLocation().getSet().decrementShotCounter();
			int shotsLeft = p.getLocation().getSet().getShotCounter();	
			System.out.print("There is " + shotsLeft + " shots left on ");
			System.out.println(p.getLocation().getLocationName() +".");						

		}else{
			System.out.println("You suck at acting. " );
			switch(p.getJob().getWorkType()){
				case "MAIN":
					System.out.println("You do not recieve any currency.");
					break;
				case "EXTRA":
					p.evalWalletContent().addDollars(1, "You");
					break;
				default:
			}
		}
	}

	private void work(Player p){
		//cont.fixView("WORK");	
		if(!p.isWorking()){
			role(p);
		}
		if(playerMoved){
			System.out.println("finished moving");
			cont.finishedAction("Work");
		}	
	
		if(p.isWorking() && !playerMoved){
			//set buttons for act and rehearsal
			cont.fixView("WORK");
			while(query.getIntInput() == 0){
				cont.getCommand();	
			}
			if(query.getIntInput() != -1){
				switch(query.getUserInput()){
					case "REHEARSE":
						p.grabDice().increaseAC();
						
						break;
					case "ACT":
						p.grabDice().roll();
						int actingEffort = p.grabDice().actRoll();
						boolean isSucc = p.getLocation().getSet().isActSuccess(actingEffort);
						break;
					default:

				}
				cont.finishedAction("Work");				
			}			
		}
		cont.wrapup();
	}

	private void move(Player p){
		LinkedList<String> hotSpots = p.neighborNames();
		query.addNeighborNames(hotSpots);
		cont.fixView("MOVE");
		while(query.getIntInput() == 0){
			cont.getCommand();	
		}

		if(query.getIntInput() != -1){
			p.setLocation(tiles.get(query.getIntInput()));
			playerMoved = true;
			cont.finishedAction("Move");	
			//cont.redisplayLoc(p.getLocation());
		}
		cont.wrapup();
		//return hasMoved;
	}

*/
	/*public void getPlayerInput(Player p, int ID){

		boolean hasWorked = false;
		boolean hasMoved = false;
		while(!query.getUserInput().equals("END")){
			cont.setPlayer(p);
			String userInput = query.getUserInput();
			//String userInput = query.getUserInput();
			query.resetValue();
			if(query.getUserInput().equals("END")){
				System.out.println("END");				
				System.exit(0);
			}
			switch(userInput){
				case "MOVE":
					//if(!hasMoved){
					move(p);
					//}
					break;
				case "UPGRADE":
					p.upgrade();
					break;
				case "WORK":
					//hasWorked = p.initializeWork(hasWorked, hasMoved);
					work(p);
					break;
				case "AMOUNT":
					p.evalWalletContent().displayContent();
					break;
				case "WHO":
					p.playerInformation();
					break;
				case "WHERE":
					p.playerLocation("You are");
					displayOtherPlayers(ID);
					break;
				case "RENAME":
					String newName = query.getUserInput("Enter your new name: ");
					p.setPlayerName(newName);
					break;
				default:
					//System.out.print("INVALID MOVE. ");	
			}
			cont.getCommand();
			cont.redisplayImage();

		}			
		//cont.fixView("END");
		playerMoved = false;
		query.resetValue();
		cont.fixView("END");
		
		System.out.println(" ");
	}*/
/*
	public int iterateRound(int start){
		tiles.drawScenes();
		System.out.println("BEGINNING OF ROUND " + display);
		System.out.println(" ");
		returnToTrailer();

		int num = start;
		while(!hasFinishedRound()){
			Player p = users.get(num);
			//p.allowTurn(true);
			cont.redisplayImage();
			getPlayerInput(p, num);
			Set pSet = p.getLocation().getSet();
			LinkedList<Player> onScene = getPlayers(p.getLocation());
			if(pSet.getShotCounter() == 0){
				if(pSet.getScene().hasMainActors()){
					LinkedList<Integer> bonusRolls = p.grabDice().getBonusRoll(pSet.getScene().getBudget());
					for(int i = 0; i < onScene.size(); i++){
						Player actor = onScene.get(i);
						String actorType = actor.getJob().getWorkType();
						Wallet myWallet = actor.evalWalletContent();
						switch(actorType){			
							case "MAIN":
								actor.bonusPayout(bonusRolls);
								break;
							case "EXTRA":
								myWallet.addDollars(onScene.get(i).getJob().getWorkLevel(), actor.getPlayerName());
								break;
							default:
						}
					}
				}
				for(int j = 0; j < onScene.size(); j++){
					Player actor = onScene.get(j);
					actor.removeWork();
				}					
	
			}
	
			num = num + 1;
			//p.allowTurn(false);
			if(num == users.size()){ 
				num = 0; 
			}	
		}
		System.out.println("END OF ROUND " + getRound());

		nextRound();
		return num;

	}

	public LinkedList<Player> getPlayers(Location loc){
		LinkedList<Player> onLoc = new LinkedList<Player>();		
		for(int i = 0; i < users.size(); i++){
			Location l = users.get(i).getLocation();
			if(l.getID() == loc.getID() && users.get(i).isWorking()){
				onLoc.add(users.get(i));
			}
		}
		return onLoc;
	}

	public boolean hasFinishedRound(){ return tiles.isBoardWrapped(); }

	public LinkedList<Player> getPlayers(){
		return users;
	}

	public int displayRound(){
		return display;
	}*/

}
