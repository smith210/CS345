public class Wallet{
	private int dollars;
	private int credits;

	public int calculatePoints(int level){
		int points = dollars + credits + (5 * level);
		return points;
	}

	public int getDollars(){
		return dollars;
	}
	
	public int getCredits(){
		return credits;
	}

	public void addDollars(int paycheck){
		dollars = dollars + paycheck;
	}

	public void addCredits(int paycheck){
		credits = credits + paycheck;
	}
}
