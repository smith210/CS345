import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyPanelTwo extends JPanel implements ActionListener{
	private HomeButtons HOME;
	private WorkButtons WORK;
	private RoleButtons JOBS;
	private CastButtons CAST;
	private MoveButtons MOVE;
	private MyPanel view;
	private Deadwood model;

	boolean toggleMove, toggleRole, toggleWork, toggleUpgrade;

	//private Player p;	
	private String command;
	private LinkedList<String> neighbors;

	protected LinkedList<JButton> Home;
	protected LinkedList<JButton> Work;
	protected LinkedList<JButton> Jobs;
	protected LinkedList<JButton> Cast;
	protected LinkedList<JButton> Move;
	protected JButton escape;

	public MyPanelTwo(Deadwood model, MyPanel view) {
		this.model = model;
		this.view = view;
		command = new String();

		model.getScenes();


		neighbors = new LinkedList<String>();

		Dimension buttonSize = new Dimension(200,125);
		HOME = new HomeButtons();
		Home = HOME.getButtons();
		addButtons(Home);

		WORK = new WorkButtons();
		Work = new LinkedList<JButton>();

		JOBS = new RoleButtons();
		Jobs = new LinkedList<JButton>();

		CAST = new CastButtons();
		Cast = new LinkedList<JButton>();

		MOVE = new MoveButtons();
		Move = MOVE.getButtons();
		addButtons(Move);

		createEscape();
		view.passPlayerDetails(model.currentPlayer());

		//initializeButtons();
		
	}
	private void addButtons(LinkedList<JButton> buttons){//add listener
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).addActionListener(this);
			add(buttons.get(i));
		}
	}
	private void createEscape(){//go back button
		ButtonCreator escapeCreate = new ButtonCreator("Go Back");
		escapeCreate.setCommand("NO");
		escapeCreate.setVisibility(false);
		escapeCreate.changeSize(100,50);
		escape = escapeCreate.getJButton();		
		escape.addActionListener(this);

		escape.setBorder(null);
		add(escape);
	}

    public Dimension getPreferredSize() {
        return new Dimension(300,800);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);      
		g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
		g.setColor(Color.BLACK);
		if(command.equals("ACT")){
			g.drawString(Integer.toString(model.currentPlayer().totalRoll(model.getLastRoll())), 100, 700);
			if(model.currentPlayer().validActing(model.getLastRoll())){
				g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
				g.drawString("You're not doing so well...", 75,900);
			}
		}
    }  

	private void displayAll(LinkedList<JButton> buttons, boolean v){//display all buttons
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setVisible(v);
			//System.out.println(buttons.get(i).getText());
		}
	}

	public void toggleHomeButton(String name, boolean view){//toggle desired home button
		int curr = 0;		
		while(curr != Home.size() && !name.equals(Home.get(curr).getText())){
			curr++;
		}
		if(curr != Home.size()){
			Home.get(curr).setEnabled(view);
		}
	}


	private void searchMove(String name, boolean view){//search for location, set visibility
		int curr = 0;		
		while(curr != Move.size() && !name.equals(Move.get(curr).getText())){
			curr++;
		}
		if(curr == Move.size()){
			return;
		}else{
			System.out.println(name + " found");
			Move.get(curr).setVisible(view);
		}
	}

	public void revealHome(){
		displayAll(Home, true);
		escape.setVisible(false);
	}


	public void hideHome(){
		displayAll(Home, false);
		escape.setVisible(true);
	}

	public void clean(){
		switch(model.getBaseCommand()){
			case "MOVE":
				displayAll(Move, false);
				revealHome();
				break;
			case "WORK":
				if(!model.finishedFilming()){
					if(!model.currentlyWorking()){				
						displayAll(Jobs, false);
						if(model.moveToggle() && !command.equals("NO")){
							displayAll(Work, true);
							escape.setVisible(false);
							toggleHomeButton("Move", false);
						}else{
							revealHome();
						}
					}
				}
				displayAll(Work, false);
				Work.clear();
				revealHome();

				break;
			case "UPGRADE":
				displayAll(Cast, false);
				CAST.removeButtons();
				revealHome();
				break;
			default:
		}

	}

	public void setButtons(){
		toggleHomeButton("Move", model.moveToggle());
		toggleHomeButton("Work", model.workToggle());	
		toggleHomeButton("Upgrade", model.upgradeToggle());
	}

	public void toggleSwitch(){
		hideHome();	
		switch(command){
			case "MOVE":	
				MOVE.addButtons(model.currentNeighbors());
				Move = MOVE.getButtons();
				addButtons(Move);
				MOVE.clearButtons();
				displayAll(Move, true);
				break;
			case "WORK":

				if(!model.currentlyWorking()){
					JOBS.setLevel(model.currentLevel());		
					JOBS.addButtons(model.currentJobs());
					Jobs = JOBS.getButtons();
					addButtons(Jobs);
					JOBS.clearButtons();
					displayAll(Jobs, true);
				}else{
					int currRehearsals = model.currentPlayer().getRehearsals();
					int budget = model.currentLocation().getSet().getScene().getBudget();
					WORK.hitMaxRehearsals(currRehearsals, budget);
					Work = WORK.getButtons();
					addButtons(Work);
					displayAll(Work, true);
				}
				break;
			case "UPGRADE":
				CAST.setLevel(model.currentLevel());
				CAST.setCurrency(model.getCurrencyType());
				CAST.generateCastButtons();
				Cast = CAST.getButtons();
				addButtons(Cast);
				displayAll(Cast, true);
				break;
			case "DOLLAR":
			case "CREDIT":
				model.setPaymentMethod(command);
				CAST.setCash(model.getCash());
				CAST.setCurrency(command);
				CAST.validateCanPay();
				Cast = CAST.getButtons();
				//addButtons(Cast);
				displayAll(Cast, true);
				break;
			default:
				clean();
				
				//revealHome();
				

		}

	}

	public void actionPerformed(ActionEvent e) {//set command
		//selected  = true;
		System.out.println(e.getActionCommand());
		repaint();
		command = e.getActionCommand();	
		if(!command.equals("END")){
			toggleSwitch();
		}
		view.passCommand(command);
//		toggleSwitch();
		model.setUserInput(command);
		model.switchCommand();
		//toggleSwitch();
		view.passPlayerDetails(model.currentPlayer());
		setButtons();
		repaint();
    }




}
