public class CastingOffice{
	private final int[] levelTwo = {4, 5};
	
	private final int[] levelThree = {10, 10};
	
	private final int[] levelFour = {18, 15};
	
	private final int[] levelFive = {28, 20};
	
	private final int[] levelSix = {40, 25};

	private Wallet purse;
	private userInput query;

	CastingOffice(){
		purse = new Wallet();
		query = new userInput();
	}

	public void updateWallet(Wallet purse){ this.purse = purse; }

	private int canPay(int pocketChange, int level, int type){

		int payment = -1;
		
		switch(level){
			case 2:
				if(levelTwo[type] <= pocketChange){	
					payment = levelTwo[type];
					break;
				}
			case 3:
				if(levelThree[type] <= pocketChange){	
					payment = levelThree[type];
					break;
				}
			case 4:
				if(levelFour[type] <= pocketChange){	
					payment = levelFour[type];
					break;
				}
			case 5:
				if(levelFive[type] <= pocketChange){	
					payment = levelFive[type];
					break;
				}
			case 6:
				if(levelSix[type] <= pocketChange){	
					payment = levelSix[type];
					break;
				}
			default:
		}
		return payment;
	}

	private void payDollars(int payment){
		System.out.println("Paying in dollars...");
		purse.addDollars(payment * -1);
	}

	private void payCredits(int payment){
		System.out.println("Paying in credits...");
		purse.addCredits(payment * -1);
	}

	

	public Wallet pay(int level){		
		int dollars = purse.getDollars();
		int credits = purse.getCredits();
	
		int payDollars = canPay(dollars, level, 0);
		int payCredits = canPay(credits, level, 1);

		if(payDollars != -1 && payCredits != -1){
			String payType = query.getUserInput("Do you want to pay in Dollars or Credits: ");
			boolean validInput = false;
			while(!validInput){
				switch(payType){
					case "DOLLARS":
						payDollars(payDollars);
						validInput = true;
						break;	
					case "CREDITS":
						payCredits(payCredits);
						validInput = true;
						break;
					default:
						System.out.println("Not a valid payment method");
				}
			}
		}else if(payDollars != -1){
			payDollars(payDollars);
		}else if(payCredits != -1){
			payCredits(payCredits);
		}else{
			System.out.println("You don't have the appropriate currency to level up.");
		}
		return purse;

	}


}
