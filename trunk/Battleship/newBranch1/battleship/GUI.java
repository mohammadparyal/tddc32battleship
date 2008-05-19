package battleship;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.*;
import javax.swing.border.TitledBorder;


/**
 * Grafical User Interface. 
 * @author Lars Öberg and David Gunnarsson
 *
 */
public class GUI extends JFrame {

	static final long serialVersionUID = 2L;

	private GridGUI gridGUI;
	private GridGUI gridGUI2;
	private Game game;
	
	private JPanel opponentPanel;
	private JButton aiButton;
	private JTextField addressfield;
	private JButton createButton;
	private JButton connectButton;
	
///////////////////////////////////////////////
	private JPanel ram;
	private JPanel ramGrid;

	//KnappPanel
	private JPanel buttonPanel;
	private JButton start;
	private JButton close;
	private JLabel chatLabel;
	private JTextField chatText;
	private JButton chatButton;
	private JPanel chatPanel;
	//KnappPanel: SLut
	
	//Kommunikation
	private JPanel messagePanel;
	private JTextArea statusTextArea;
	private JScrollPane statusScroll;
	private JTextArea chatTextArea; 
	private JScrollPane chatScroll;
	//Kommunikation: Slut
	
	// FlottaGUI
	private JPanel flottaGUI;
	private JButton aircraftCarrierButton;
	private JButton battleshipButton;
	private JButton destroyerButton;
	private JButton patrolBoatButton;
	private JButton submarineButton;
	private JButton[] shipButtons;
	// FlottaGUI: Slut
/////////////////////////////////////////////////////



	
	public GUI(int rows, int cols, Game g) {
		game = g;
		
		gridGUI = new GridGUI(rows, cols, game, true, this);
		gridGUI2 = new GridGUI(rows, cols, game, false, this);
		gridGUI.setBorder(new TitledBorder("Din Spelplan"));
		gridGUI2.setBorder(new TitledBorder("Motståndarens Spelplan"));		
		
		//opponentfield
		opponentPanel = new JPanel();
		opponentPanel.setBorder(new TitledBorder("Opponent"));
		opponentPanel.add(addressfield = new JTextField("127.0.0.1"));
		opponentPanel.add(createButton = new JButton("Create"));
		createButton.addActionListener(new  ActionListener()
		{
			public void actionPerformed(ActionEvent E)
			{
				game.createNetwork();
			}});

		opponentPanel.add(connectButton = new JButton("Connect"));
		connectButton.addActionListener(new  ActionListener()
		{
			public void actionPerformed(ActionEvent E)
			{
				game.connectNetwork(addressfield.getText());
			}});		

		opponentPanel.add(aiButton = new JButton("AI"));

		aiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				game.makeAI();
			}
		});
		start = new JButton("Start");
		start.addActionListener(new StartListener());
		opponentPanel.add(start);
		
		//FlottaGUI
		flottaGUI = new JPanel();
		flottaGUI.setBorder(new TitledBorder("Fleet"));
		
		setLayout(new FlowLayout());
		
		shipButtons = new JButton[5];
		shipButtons[0] = aircraftCarrierButton = new JButton("Aircraft Carrier");
		shipButtons[1] = battleshipButton = new JButton("Battleship");
		shipButtons[2] = destroyerButton = new JButton("Destroyer");
		shipButtons[3] = submarineButton = new JButton("Submarine");
		shipButtons[4] = patrolBoatButton = new JButton("Patrol Boat");

		aircraftCarrierButton.addActionListener(new ShipListener(0));
		battleshipButton.addActionListener(new ShipListener(1));
		destroyerButton.addActionListener(new ShipListener(2));
		submarineButton.addActionListener(new ShipListener(3));
		patrolBoatButton.addActionListener(new ShipListener(4));
		
		flottaGUI.add(aircraftCarrierButton);
		flottaGUI.add(patrolBoatButton);
		flottaGUI.add(battleshipButton);
		flottaGUI.add(destroyerButton);
		flottaGUI.add(submarineButton);
		// FlottaGUI: Slut
		
		// KnappPanel
		buttonPanel = new JPanel();
		
		close = new JButton("Close");

		chatLabel = new JLabel("Chat");
		chatText = new JTextField();
		chatText.setPreferredSize(new Dimension(300,25));
		chatText.addActionListener(new ChatListener());
		chatButton = new JButton("Send");

		close.addActionListener(new CloseListener());
		chatButton.addActionListener(new ChatListener());

		chatPanel = new JPanel();
		chatPanel.add(chatLabel);
		chatPanel.add(chatText);
		chatPanel.add(chatButton);

		setLayout(new GridLayout(2,1));
		buttonPanel.add(chatPanel);
		buttonPanel.add(close);
		//buttonPanel: end		
		
				
		messagePanel = new JPanel();
		messagePanel.setLayout(new GridLayout(2,1));
		statusScroll = new JScrollPane(statusTextArea = new JTextArea());
		statusTextArea.setEditable(false);
		statusScroll.setBorder(new TitledBorder("Status"));
		statusScroll.setAutoscrolls(true);
		chatScroll = new JScrollPane(chatTextArea = new JTextArea());
		chatScroll.setBorder(new TitledBorder("Chat"));
		chatScroll.setAutoscrolls(true);
		chatScroll.setPreferredSize(new Dimension(1,120));
		chatTextArea.setBorder(new TitledBorder(""));
		chatTextArea.setEditable(false);
		messagePanel.add(statusScroll);
		messagePanel.add(chatScroll);
		
		
		ramGrid = new JPanel();
		ramGrid.add(gridGUI);
		ramGrid.add(gridGUI2);
		
		ram = new JPanel();
		ram.setLayout(new BoxLayout(ram, BoxLayout.Y_AXIS));
		ram.add(opponentPanel);
		ram.add(flottaGUI);
		ram.add(messagePanel);
		ram.add(buttonPanel);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));		

		add(ramGrid);
		add(ram);
		
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		    	game.close();
		    	System.exit(0); 
		    }
		});

		setTitle("Battleship");
		pack();
		
		setVisible(true);
	}

	public GridGUI getGrid1() {
		return gridGUI;
	}
	
	public GridGUI getGrid2() {
		return gridGUI2;
	}
	

	public void updateFleetGUI(){
		repaint();
	}
	
	public void updateStatus(String s)
	{
		statusTextArea.append(s+'\n');
	}
	
	public void updateChat(String s)
	{
		chatTextArea.append(s+'\n');
	}
	
	public void disableShipButton(int index){
		shipButtons[index].setEnabled(false);
	}
	
	//FlottaGUI: knappar	
	class ShipListener implements ActionListener {
		private int index;
		public ShipListener(int index)
		{
			this.index = index;
		}
		public void actionPerformed(ActionEvent e){
			gridGUI.setShipIndex(index);}}
	
	//KnappPanel: knappar
	class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent E){
			if (gridGUI.getFleet().isPlaced())
				game.setPlayerReady();
			else
				System.out.println("Det finns skepp kvar att placera ut.");
		}
	}

	class CloseListener implements ActionListener{
		public void actionPerformed(ActionEvent E){
			game.close();
			System.exit(0); 
		}
	}
	class ChatListener implements ActionListener {

		public void actionPerformed(ActionEvent ev)
		{
			try
			{
				game.sendMessage(chatText.getText());
				updateChat("<< "+chatText.getText());
				chatText.selectAll();
				chatText.replaceSelection("");
			}
			catch (Exception e)
			{
				e.printStackTrace();			
			}			
		}		
	}
}