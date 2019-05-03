public class MainRole extends Work {
	private final String ACTOR_TYPE = "MAIN";
	
	public MainRole(){
		super.setWorkType(ACTOR_TYPE);
	}

	public int payout(boolean isActSuccess){
		return 0;
	}
}
