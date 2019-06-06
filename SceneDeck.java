import java.util.*;
import java.io.*;

public class SceneDeck{
	private LinkedList<Scene> cardDeck;
	private LinkedList<Scene> drawnCards;
	private LinkedList<Scene> discardCards;

	SceneDeck(){
		cardDeck = new LinkedList<Scene>();
		drawnCards = new LinkedList<Scene>();
		discardCards = new LinkedList<Scene>();
		initializeDeck();//generate
	}

	public void tossCards(){//put drawn cards into discard
		discardCards.addAll(drawnCards);
		drawnCards.clear();
	}

	public Scene drawCard(){//draw a card at random, and return
		int cardID = (int) (Math.random() * cardDeck.size());
		
		if(cardDeck.size() == 0){//shuffle discarded cards back into deck
			cardDeck = discardCards;
		}

		//draw from deck
		Scene chosenScene = cardDeck.get(cardID);
		drawnCards.add(chosenScene);
		cardDeck.remove(cardID);
			
		return chosenScene;
	}
	
	private void initializeDeck(){//generate scenes and main actors
		File tileFile = new File("setInfo.txt");
		try{
			Scanner scn = new Scanner(tileFile);
			while(scn.hasNextLine()){
				Scene scene = new Scene();
				String curr = scn.nextLine();
				String[] spliced = curr.split("\\|");
				scene.setName(spliced[0]);
				scene.setID(Integer.parseInt(spliced[1]));
				scene.setBudget(Integer.parseInt(spliced[2]));
				MainRole main = new MainRole();
				LinkedList<MainRole> mainCast = new LinkedList<MainRole>();
				for(int i = 3; i < spliced.length; i++){						
					if(i % 2 != 0){
						main.setJobTitle(spliced[i]);
					}else{
						main.setWorkLevel(Integer.parseInt(spliced[i]));
						mainCast.add(main);
						main = new MainRole();
					}
				}
				scene.createMains(mainCast);
				cardDeck.add(scene);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
