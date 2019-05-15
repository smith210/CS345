import java.util.*;

public class Work{
	private int workLevel;
	private boolean isWorking;
	private String workType;

	Work(){
		workLevel = 1;
		isWorking = false;
		workType = "none";
	}

	public int getWorkLevel(){ return workLevel; }

	public boolean getWorkStatus(){	return isWorking; }

	public boolean canWork(int playerLevel){
		if(workLevel <= playerLevel){
			return true;
		}else{
			return false;
		}
	}
	
	public void bufWork(){ isWorking = true; }
	
	public String getWorkType(){ return workType; }
	
	public void display(){
		System.out.print(workType);
		System.out.print("  " + workLevel);
	}
	//public abstract int payout(boolean isActSuccess);


}
