import java.util.*;

public abstract class Work{
	private int workLevel;
	private boolean isWorking;

	public int getWorkLevel(){
		return workLevel;
	}

	public boolean getWorkStatus(){
		return isWorking;
	}

	public boolean canWork(int playerLevel){
		if(workLevel <= playerLevel){
			return true;
		}else{
			return false;
		}
	}
	
	public void bufWork(){
		isWorking = true;
	}

	public abstract int payout(boolean isActSuccess);
	public abstract String getActorType();

}
