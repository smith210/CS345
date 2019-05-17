import java.util.*;

public class userInput{

	public int getIntInput(String prompt, String action, int min, int max){
		int intInput = -1;
		boolean validInput = false;
		while(!validInput){
			String userInput = getUserInput(prompt + " (if you don't want to " + action + ", type \"no\"): ");
			try{
				if(Integer.parseInt(userInput) >= min && Integer.parseInt(userInput) < max){
					intInput = Integer.parseInt(userInput);
					validInput = true;
				}else{
					System.out.println("The ID does not correspond with one of the listed IDs.");
				}
			}catch(Exception e){
				if(userInput.equals("NO")){
					validInput = true;
				}else{
					System.out.println("You must input the ID associated with your choice in order to " + action + ".");
				}
			}
		}
		return intInput;
	}	

	public String getUserInput(String query){
		System.out.print(query);
		Scanner scn = new Scanner(System.in);
		String userInput = scn.nextLine();
		userInput = userInput.toUpperCase();
		return userInput;
	}

}
