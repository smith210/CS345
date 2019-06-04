import java.util.*;

public class Board{
	private Controller cont;
	private SwingPaint GUI;
	private LinkedList<Player> users;
	private GameTiles tiles;
	private int round;
	private int display;
	private userInput query;
	
	public Board(){
		GUI = new SwingPaint();
		GUI.createAndShowGUI();
		users = new LinkedList<Player>();
		tiles = new GameTiles();
		display = 1;	
		round = 3;
		query = new userInput();
		cont = new Controller(query, GUI.getPanel(), GUI.getArt());
	}
	
	public Board(int playerNum){
		GUI = new SwingPaint();
		GUI.createAndShowGUI();
		users = new LinkedList<Player>();
		tiles = new GameTiles();
		createPlayers(playerNum);
		display = 1;	
		round = 3;
		query = new userInput();
		cont = new Controller(query, GUI.getPanel(), GUI.getArt());

	}
	
	public userInput getQueries(){ return query; }

	private void returnToTrailer(){
		for(int i = 0; i < users.size(); i++){
			users.get(i).setLocation(tiles.get(4));
		}
	}

	private void createPlayers(int playerNum){
		for(int i = 0; i < playerNum; i++){
			Player temp = new Player();
			temp.setQuery(query);
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

	private boolean move(Player p, boolean hasMoved){
		LinkedList<String> hotSpots = p.neighborNames();
		query.addNeighborNames(hotSpots);
		cont.fixView("MOVE");
		while(query.getIntInput() == 0){
			cont.getCommand();	
		}

		if(query.getIntInput() != -1){
			p.setLocation(tiles.get(query.getIntInput()));
			hasMoved = true;
			cont.disableMove();	
			//cont.redisplayLoc(p.getLocation());
		}
		cont.wrapup();
		return hasMoved;
	}

	public void getPlayerInput(Player p, int ID){

		boolean finishTurn = false;
		boolean hasWorked = false;
		boolean hasMoved = false;
		while(!finishTurn){
			cont.redisplayImage(p);
			String userInput = query.getCommand("Enter your move: ");
			//String userInput = query.getUserInput();

			switch(userInput){
				case "MOVE":
					if(!hasMoved){
						move(p, hasMoved);
					}
					break;
				case "UPGRADE":
					p.upgrade();
					break;
				case "WORK":
					hasWorked = p.initializeWork(hasWorked, hasMoved);
					break;
				case "END":
					finishTurn = true;
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

		}
		System.out.println(" ");
	}

	public int iterateRound(int start){
		tiles.drawScenes();
		System.out.println("BEGINNING OF ROUND " + display);
		System.out.println(" ");
		returnToTrailer();

		int num = start;
		while(!hasFinishedRound()){
			Player p = users.get(num);
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
	}

	public int getRound(){
		return round;
	}

	public void nextRound(){
		round = round - 1;
		display = display + 1;
	}

	public Player evaluateWinner(){
		boolean evalWinner = false;
		Player winner = new Player();
		Wallet winnerCash = new Wallet();		
		for(int i = 0; i < users.size(); i++){
			Player p = users.get(i);
			Wallet w = p.evalWalletContent();

			if(!evalWinner){
				evalWinner = true;
				winner = p;
				winnerCash = w;
			}else{
				int winnerPoints = winnerCash.calculatePoints(winner.getLevel());
				int playerPoints = w.calculatePoints(p.getLevel());
				if(playerPoints > winnerPoints){
					winner = p;
					winnerCash = w;
				}
			}
			
		}
		return winner;
	}

}
