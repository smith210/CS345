import java.util.*;

public class Work{
	private String jobTitle;
	private int workLevel;
	private boolean isWorking;
	private String workType;

	Work(){
		jobTitle = "Unemployed";
		workLevel = 1;
		isWorking = false;
		workType = "none";
	}

	public void setJobTitle(String jobTitle){ this.jobTitle = jobTitle; }
	public void setWorkLevel(int workLevel){ this.workLevel = workLevel; }
	public void setWorkType(String workType){ this.workType = workType; }

	public String getJobTitle(){ return jobTitle; }

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

		System.out.println(workType + " ACTOR: " + jobTitle + ", Actor Level " + workLevel);
	}
	//public abstract int payout(boolean isActSuccess);


}
