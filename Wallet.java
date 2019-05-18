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

	public void addDollars(int paycheck, String user){
		displayStatus(paycheck, "Dollar", user);
		dollars = dollars + paycheck;
	}

	public void addCredits(int paycheck, String user){
		displayStatus(paycheck, "Credit", user);
		credits = credits + paycheck;
	}

	private void displayStatus(int paycheck, String type, String userName){
		if(paycheck >= 0){
			System.out.print(userName + " recieved payment of ");
		}else{
			paycheck = paycheck * -1;
			System.out.print(userName + " paid ");
		}
		System.out.println(paycheck + " " + type);
	}

	public void displayContent(){
		System.out.print("You currently have: ");
		System.out.print(dollars + " Dollars, ");
		System.out.println(credits + " Credits.");
	}
}
