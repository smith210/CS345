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

	private Player p;	
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

	public String getCommand(){ return command; }
	public boolean hasSelected(){ return selected; }
	public void retrieved(){ selected = false; }

	public void retrieveNeighbors(LinkedList<String> neighbors){ this.neighbors = neighbors;}

	private void displayAll(LinkedList<JButton> buttons, boolean v){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setVisible(v);
			//System.out.println(buttons.get(i).getText());
		}
	}

	public void disableHome(String name){
		int curr = 0;		
		while(curr != Home.size() && !name.equals(Home.get(curr).getText())){
			curr++;
		}
		if(curr != Home.size()){
			Home.get(curr).setEnabled(false);
		}
	}

	private void display(JButton j, boolean v){
		j.setVisible(v);
	}

	private void searchMove(String name, boolean view){
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

	private void addButtons(LinkedList<JButton> buttons){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).addActionListener(this);
			add(buttons.get(i));

		}
	}

	private void createEscape(){
		ButtonCreator escapeCreate = new ButtonCreator("Go Back");
		escapeCreate.setCommand("NO");
		escapeCreate.setVisibility(false);
		escapeCreate.changeSize(100,50);
		escape = escapeCreate.getJButton();		
		escape.addActionListener(this);
		add(escape);

	}

	public MyPanelTwo() {

		//p = new Player();
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
		//Jobs = JOBS.getButtons();
		//addButtons(Jobs);

		CAST = new CastButtons();
		//Cast = CAST.getButtons();
		//addButtons(Cast);

		MOVE = new MoveButtons();
		Move = MOVE.getButtons();
		addButtons(Move);

		createEscape();
		
	}
    public Dimension getPreferredSize() {
        return new Dimension(300,800);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);      
		//redSquare.paintSquare(g);
    }  

	public void movePanel(){
		displayAll(Home, false);
		
		display(escape, true);
		for(int i = 0; i < neighbors.size(); i++){
			searchMove(neighbors.get(i), true);
		}
	}

	private void closeDisplay(){
		switch(baseCommand){
			case "MOVE":
				for(int i = 0; i < neighbors.size(); i++){

					searchMove(neighbors.get(i), false);
				}
				break;
			case "WORK":
				break;
			case "UPGRADE":
				break;
			default:

		}
	}

	public void setHomeScreen(){
		closeDisplay();
		display(escape, false);	
		displayAll(Home, true);
		command = new String();
	}

	public void actionPerformed(ActionEvent e) {
		selected  = true;
		command = e.getActionCommand();
		try{
			Integer.parseInt(command);
		}catch(Exception c){
			if(!command.equals("NO") || !command.equals("END")){
				baseCommand = command;
			}
		}
		//System.out.println("action committed: " + e.getActionCommand());
        /*if ("MOVE".equals(e.getActionCommand())) {
			command = "MOVE";
			//movePanel();
			//display(Move, true);
        } else if("WORK".equals(e.getActionCommand())) {
			command = "WORK";			
			displayAll(Home, false);
			display(escape, true);
			//get the roles or work
		} else if("UPGRADE".equals(e.getActionCommand())){
			command = "UPGRADE";	
			displayAll(Home, false);
			display(escape, true);
			//display casting office 
		} else if("END".equals(e.getActionCommand())){
			//displayAll(Home, true);
		} else if("ACT".equals(e.getActionCommand())){
			//displayAll(Home, true);
		} else if("REHEARSE".equals(e.getActionCommand())){
			//displayAll(Home, true);
		} else if("NO".equals(e.getActionCommand())){
			closeDisplay();
			command = "NO";
			display(escape, false);	
			displayAll(Home, true);
			command = new String();
		} else {
			command = get*/
    }



}
