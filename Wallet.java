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
		displayStatus(paycheck, "Dollar");
		dollars = dollars + paycheck;
	}

	public void addCredits(int paycheck){
		displayStatus(paycheck, "Credit");
		credits = credits + paycheck;
	}

	private void displayStatus(int paycheck, String type){
		if(paycheck >= 0){
			System.out.print("Recieved payment of ");
		}else{
			System.out.print("Paying ");
		}
		System.out.println(paycheck + " " + type);
	}

	public void displayContent(){
		System.out.print("You currently have: ");
		System.out.print(dollars + " Dollars, ");
		System.out.println(credits + " Credits.");
	}
}
