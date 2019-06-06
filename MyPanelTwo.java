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

	//private Player p;	
	private boolean selected;
	private String baseCommand;
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
		baseCommand = new String();
		command = new String();
		selected = false;

		neighbors = new LinkedList<String>();

		Dimension buttonSize = new Dimension(200,125);
		HOME = new HomeButtons();
		Home = HOME.getButtons();
		addButtons(Home);

		WORK = new WorkButtons();
		Work = WORK.getButtons();
		addButtons(Work);

		JOBS = new RoleButtons();
		Jobs = new LinkedList<JButton>();

		CAST = new CastButtons();
		//Cast = CAST.getButtons();
		//addButtons(Cast);

		MOVE = new MoveButtons();
		Move = MOVE.getButtons();
		addButtons(Move);

		createEscape();
		initializeButtons();
		
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
		add(escape);

	}

    public Dimension getPreferredSize() {
        return new Dimension(300,800);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);      
		//redSquare.paintSquare(g);
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

	public void toggleHomeButton(String name, boolean view){//toggle desired home button
		int curr = 0;		
		while(curr != Home.size() && !name.equals(Home.get(curr).getText())){
			curr++;
		}
		if(curr != Home.size()){
			Home.get(curr).setEnabled(view);
		}
	}

	private void displayAll(LinkedList<JButton> buttons, boolean v){//display all buttons
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setVisible(v);
			//System.out.println(buttons.get(i).getText());
		}
	}

	public void initializeButtons(){
		model.checkOnPlayer();
		toggleHomeButton("Move", model.moveToggle());
		toggleHomeButton("Work", model.workToggle());
		toggleHomeButton("Upgrade", model.upgradeToggle());
		view.passPlayerDetails(model.currentPlayer());

	}

	public void beginAction(){
		switch(command){
			case "MOVE":
				for(int i = 0; i < model.currentNeighbors().size(); i++){
					searchMove(model.currentNeighbors().get(i).getLocationName(), true);
				}
			case "UPGRADE":

			case "WORK":
	
			default:

		}
	}

	public void toggleSwitch(){
		displayAll(Home, model.menuToggled());
		if(!model.menuToggled()){
			beginAction();
			escape.setVisible(true);
		}else{
			escape.setVisible(false);
		}
	}

	/*public String getCommand(){ return command; }
	public boolean hasSelected(){ return selected; }
	public void resetCommand(){ command = new String(); }
	public void retrieved(){ selected = false; }
	public void setRoleButtons(LinkedList<Work> jobs, int level){//create the role buttons
		JOBS.setLevel(level);		
		JOBS.addButtons(jobs);
		Jobs = JOBS.getButtons();
		addButtons(Jobs);
	}
	public boolean rolesEmpty(){
		if(Jobs.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	public void retrieveNeighbors(LinkedList<String> neighbors){ this.neighbors = neighbors;}

	private void setBaseCommand(String baseCommand){this.baseCommand = baseCommand; }

	private void displayAll(LinkedList<JButton> buttons, boolean v){//display all buttons
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setVisible(v);
			//System.out.println(buttons.get(i).getText());
		}
	}

	private void toggleFinishWork(boolean view){
		JButton finish = Work.get(2);
		finish.setVisible(view);
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

	private void display(JButton j, boolean v){
		j.setVisible(v);
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
			display(Move.get(curr), view);
		}
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
		add(escape);

	}

    public Dimension getPreferredSize() {
        return new Dimension(300,800);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);      
		//redSquare.paintSquare(g);
    }  

	public void differentPanel(){//make home buttons invisible
		displayAll(Home, false);
		display(escape, true);
	}

	public void movePanel(){//set neighbor buttons visible
		differentPanel();
		for(int i = 0; i < neighbors.size(); i++){
			searchMove(neighbors.get(i), true);
		}
	}

	public void workPanel(){//set work buttons visible
		differentPanel();
		displayAll(Work, true);
		toggleFinishWork(false);
	}

	public void rolePanel(){//set role buttons visible
		differentPanel();
		displayAll(Jobs, true);
	}

	private void closeDisplay(){//make displayed buttons invisible
		switch(baseCommand){
			case "MOVE":
				for(int i = 0; i < neighbors.size(); i++){

					searchMove(neighbors.get(i), false);
				}
				break;
			case "WORK":
				displayAll(Jobs, false);
				displayAll(Work, false);
				break;
			case "UPGRADE":
				break;
			case "ACT":
			case "REHEARSE":
				displayAll(Work, false);
				break;
			default:

		}
	}
	
	public void setHomeScreen(){//remove current buttons, display home buttons
		System.out.println(baseCommand);
		closeDisplay();
		display(escape, false);	
		displayAll(Home, true);
		command = new String();
	}*/

	public void actionPerformed(ActionEvent e) {//set command
		//selected  = true;
		initializeButtons();
		System.out.println(e.getActionCommand());
		command = e.getActionCommand();
		model.setUserInput(command);
		toggleSwitch();
		view.repaint();
    }



}
