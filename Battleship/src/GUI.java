import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import ships.Ship;



public class GUI extends JFrame {

	static final long serialVersionUID = 2L;

	private GridGUI gridGUI;
	private GridGUI gridGUI2;
	private Game game;
	
	private JPanel opponentPanel;
//	private JButton networkButton;
	private JButton aiButton;
	private JTextField addressfield;
	private JButton createButton;
	private JButton connectButton;
	
///////////////////////////////////////////////
	private JPanel ram;

	//KnappPanel
	private JPanel knappPanel;
	
	private JButton start;
	private JButton avsluta;

	private JLabel chatt;
	private JTextField chatText;
	private JButton chattKnapp;
	
	private JPanel j1;
	private JPanel j2;
	//KnappPanel: SLut
	
	//Kommunikation
	private JPanel messagePanel;
	private JLabel datorMessage;
	private JLabel personMessage;
	private JTextArea datorT;
	private JTextArea personT;
	//Kommunikation: Slut
	
	// FlottaGUI
	private JPanel flottaGUI;
	
	private JButton boat;
	private JButton cruiser;
	private JButton battleship;
	private JButton ship;

	private JLabel n1;
	private JLabel n2;
	private JLabel n3;
	private JLabel n4;

	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;

	private GridGUI grid;

	private JTextField t;
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
		opponentPanel.add(aiButton = new JButton("AI"));
		opponentPanel.add(createButton = new JButton("Create"));
		opponentPanel.add(addressfield = new JTextField("127.0.0.1"));
		opponentPanel.add(connectButton = new JButton("Connect"));

		aiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				game.makeAI();
			}
		});
		//FlottaGUI
		flottaGUI = new JPanel();
		flottaGUI.setBorder(new TitledBorder("Fleet"));
		
		setLayout(new FlowLayout());
		boat = new JButton("Boat");
		ship = new JButton("Ship");
		cruiser = new JButton("Cruiser");
		battleship = new JButton("Battleship");

		n1 = new JLabel(gridGUI.getFleet().boatLeft(), JLabel.CENTER);
		n2 = new JLabel(gridGUI.getFleet().shipLeft(), JLabel.CENTER);
		n3 = new JLabel(gridGUI.getFleet().cruiserLeft(), JLabel.CENTER);
		n4 = new JLabel(gridGUI.getFleet().battleshipLeft(), JLabel.CENTER);

		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		p2 = new JPanel();
		p2.setLayout(new GridLayout(2,1));
		p3 = new JPanel();
		p3.setLayout(new GridLayout(2,1));
		p4 = new JPanel();
		p4.setLayout(new GridLayout(2,1));

		boat.addActionListener(new BoatListener());
		ship.addActionListener(new ShipListener());
		cruiser.addActionListener(new CruiserListener());
		battleship.addActionListener(new BattleshipListener());

		p1.add(n1);
		p1.add(boat);
		p2.add(n2);
		p2.add(ship);
		p3.add(n3);
		p3.add(cruiser);
		p4.add(n4);
		p4.add(battleship);

		flottaGUI.add(p1);
		flottaGUI.add(p2);
		flottaGUI.add(p3);
		flottaGUI.add(p4);
		// FlottaGUI: Slut
		
		// KnappPanel
		knappPanel = new JPanel();
		
		start = new JButton("Start");
		avsluta = new JButton("Close");

		chatt = new JLabel("Chat");
		chatText = new JTextField();
		chatText.setPreferredSize(new Dimension(120,25));
		chattKnapp = new JButton("Send");

		start.addActionListener(new StartListener());
		avsluta.addActionListener(new AvslutaListener());
		chattKnapp.addActionListener(new ChattListener());

		j1 = new JPanel();
		j1.add(start);
		j1.add(avsluta);

		j2 = new JPanel();
		j2.add(chatt);
		j2.add(chatText);
		j2.add(chattKnapp);

		setLayout(new GridLayout(2,1));
		knappPanel.add(j2);
		knappPanel.add(j1);
		//KnappPanel: SLut
		
		
				
		messagePanel = new JPanel();
		//messagePanel.setBorder(new TitledBorder(""));
		messagePanel.setLayout(new GridLayout(2,2));
		datorMessage = new JLabel("Computer message:");
		personMessage = new JLabel("Chat:");
		datorT = new JTextArea();
		datorT.setBorder(new TitledBorder(""));
		personT = new JTextArea();
		personT.setBorder(new TitledBorder(""));
		messagePanel.add(datorMessage);
		messagePanel.add(datorT);
		messagePanel.add(personMessage);
		messagePanel.add(personT);
		
		
		
		ram = new JPanel();
		ram.setLayout(new GridLayout(4, 1));
		ram.add(opponentPanel);
		ram.add(flottaGUI);
		ram.add(messagePanel);
		ram.add(knappPanel);
		
		setLayout(new GridLayout(1, 3));

		add(gridGUI);
		add(ram);
		add(gridGUI2);

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

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
	
	
	
	
	//FlottaGUI: knappar	
	class BoatListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			gridGUI.setShipsize(2);}}

	class CruiserListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			gridGUI.setShipsize(4);}}

	class BattleshipListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			gridGUI.setShipsize(5);}}

	class ShipListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			gridGUI.setShipsize(3);}}
	
	//KnappPanel: knappar
	class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent E){
			if (game.getFleet().isPlaced())
				game.setStart();
			else
				System.out.println("Det finns skepp kvar att placera ut.");
		}
	}
	
	class AvslutaListener implements ActionListener{
		public void actionPerformed(ActionEvent E){

		}
	}
	
	class ChattListener implements ActionListener{
		public void actionPerformed(ActionEvent E){
			String temp = chatText.getText();
			personT.setText(temp);
		}
	}
}
