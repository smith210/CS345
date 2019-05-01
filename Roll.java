import java.util.*;

public class Roll{
	private int diceRoll;
	private int actingCounters;

	Roll(){
		Random r = new Random();
		diceRoll = r.nextInt(6) + 1;
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
		
	public int getAC(){
		return actingCounters;
	}


}
