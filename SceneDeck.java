import java.util.*;
import java.io.*;

public class SceneDeck{
	private LinkedList<Scene> cardDeck;

	SceneDeck(){
		cardDeck = new LinkedList<Scene>();
		initializeDeck();
	}

	public Scene drawCard(){
		Random rand = new Random();
		int cardID = rand.nextInt(cardDeck.size());
		Scene chosenScene = cardDeck.get(cardID);
		cardDeck.remove(cardID);
		
		return chosenScene;
	}
	
	private void initializeDeck(){
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
