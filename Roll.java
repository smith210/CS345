import java.util.*;

public class Roll{
	private int diceRoll;

	Roll(){
		Random r = new Random();
		diceRoll = r.nextInt(6) + 1;
	}

	public void roll(){
		Random r = new Random();
		diceRoll = r.nextInt(6) + 1;
	}


	public int getRoll(){
		return diceRoll;
	}


	private int getIndexID(LinkedList<Integer> rolls, int currRoll){//helper function, returns index where roll is added
		int index = 0;
		while(index != rolls.size() && rolls.get(index) > currRoll){
			index++;
		}
		return index;
	}

	public LinkedList<Integer> getBonusRoll(int budget){//set of rolls for bonus payout
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
