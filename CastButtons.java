import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;

public class CastButtons{

	private int playerLevel;
	private int cash;
	private LinkedList<ButtonCreator> levels;
	private ButtonCreator dollar, credit;

	private String currency;

	public CastButtons(){
		levels = new LinkedList<ButtonCreator>();
		cash = 0;
		playerLevel = 2;
		currency = "DOLLAR";//default

		dollar = new ButtonCreator("Switch to Dollar");
		dollar.changeSize(150, 50);
		dollar.setCommand("DOLLAR");
		
		credit = new ButtonCreator("Switch to Credit");
		credit.changeSize(150, 50);
		credit.setCommand("CREDIT");

		toggleCurrencyType();
	}

	public boolean hasMoreLevels(){
		if(levels.size() != 0){
			return true;
		}else{
			return false;
		}
	} 

	public int calculatePrice(int levelID){//get the cost of the level
		if(currency.equals("DOLLAR")){
			return (int) Math.pow(2, levelID);
		}else if(currency.equals("CREDIT")){
			return 5 * (levelID - 1);
		}else{//error
			return -1;
		}
	}

	private boolean canPay(int chosenLevel){
		if(cash >= calculatePrice(chosenLevel)){
			return true;
		}else{
			return false;
		}
	}

	public void setLevel(int playerLevel){ this.playerLevel = playerLevel + 1; }

	public void setCash(int cash){ this.cash = cash; }

	public void setCurrency(String currency){ 
		this.currency = currency; 
		toggleCurrencyType();
	}

	private void toggleCurrencyType(){
		switch(currency){
			case "DOLLAR":
				dollar.setStatus(false);
				credit.setStatus(true);
				break;
			case "CREDIT":
				dollar.setStatus(true);
				credit.setStatus(false);
				break;
			default:
		}
	}

	public void removeButtons(){ levels.clear(); }

	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> levelButtons = new LinkedList<JButton>();
		for(int i = 0; i < levels.size(); i++){
			levelButtons.add(levels.get(i).getJButton());
		}
		levelButtons.add(dollar.getJButton());
		levelButtons.add(credit.getJButton());
		return levelButtons;
	}
	
	public void getAvailableLevels(int playerLevel){ //get level above player
		this.playerLevel = playerLevel + 1;

	}

	public void validateCanPay(){
		for(int i = playerLevel; i <= 6 ; i++){
			if(canPay(i)){
				levels.get(i - playerLevel).setStatus(true);
			}else{
				levels.get(i - playerLevel).setStatus(false);
			}
		}
	}

    public void generateCastButtons(){
		for(int i = playerLevel; i <= 6; i++){
			ButtonCreator upgrade = new ButtonCreator("Level " + i);
			//upgrade.createOneLineButton(Integer.toString(calculatePrice(i)), "DOWN");
			upgrade.setVisibility(false);
			upgrade.setCommand(Integer.toString(i));
			upgrade.changeSize(135,50);
			levels.add(upgrade);
		}
		validateCanPay();
	}

	
}
