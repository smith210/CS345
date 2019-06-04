import java.util.*;

public class userInput{

	private String userInput;
	private LinkedList<String> validNeighbors;


	userInput(){
		userInput = new String();
		validNeighbors = new LinkedList<String>();
	}

	public void setUserInput(String s){ userInput = s; }

	public String getUserInput(){ return userInput; }


	public void resetValue(){ 	userInput = new String();}

	/*public int getIntInput(String prompt, String action)//, int min, int max){
		int intInput = -1;
		boolean validInput = false;
		while(!validInput){
			userInput = getCommand(prompt + " (if you don't want to " + action + ", type \"no\"): ");
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
	}*/

	public int getIntInput(){
		int intInput = 0;
		try{
			intInput = Integer.parseInt(userInput);
		}catch(Exception e){
			if(userInput.equals("NO")){
				intInput = -1;
			}
		}
		return intInput;
	}
	

	public void addNeighborNames(LinkedList<String> neighbors){
		validNeighbors = neighbors;
	}

	public LinkedList<String> getNeighbors(){ return validNeighbors; }

	public String getUserInput(String query){
		//System.out.print(query);
		//Scanner scn = new Scanner(System.in);
		//System.out.println("mailman has view: " + mailman.hasView());
		//while(!mailman.hasView()){}
		//userInput = mailman.sendCommand();//scn.nextLine();
		//System.out.println("MAILMAN DELIVERED: " + userInput);
		//mailman.rest();
		//while(!retrieved){}
		//System.out.println(userInput);
		return userInput;
	}

	public String getCommand(String query){
		userInput = getUserInput(query);
		return userInput.toUpperCase();
	}

}
