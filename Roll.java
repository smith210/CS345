import java.util.*;

public class Roll{
	private int diceRoll;
	private int actingCounters;

	Roll(){
		Random r = new Random();
		diceRoll = r.nextInt(6) + 1;
		actingCounters = 0;
	}

	public void roll(){
		Random r = new Random();
		diceRoll = r.nextInt(6) + 1;
	}

	public int actRoll(){
		return diceRoll + actingCounters;
	}

	public void increaseAC(){		
		actingCounters = actingCounters + 1;
	}

	public int getRoll(){
		return diceRoll;
	}

	public boolean isMaxRehearsals(int budget){
		if(budget <= (actingCounters + 1)){
			return true;
		}else{
			return false;
		}
	}

	public void displayRehearsals(){
		System.out.println("You have completed " + actingCounters + " rehearsals.");
	}
		
	public int getAC(){
		return actingCounters;
	}

	private int getIndexID(LinkedList<Integer> rolls, int currRoll){
		int index = 0;
		while(index != rolls.size() && rolls.get(index) > currRoll){
			index++;
		}
		return index;
	}

	public LinkedList<Integer> getBonusRoll(int budget){
		LinkedList<Integer> allRolls = new LinkedList<Integer>();		
		for(int i = 0; i < budget; i++){	
			roll();
			int currRoll = diceRoll;
			int index = getIndexID(allRolls, currRoll);
			if(index != allRolls.size()){
				allRolls.add(index, currRoll);
			}else{
				allRolls.add(currRoll);
			}
		}
		return allRolls;
	}


}
