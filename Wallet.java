public class Wallet{
	private int dollars;
	private int credits;

	public int calculatePoints(){
		int points = dollars + credits;
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
