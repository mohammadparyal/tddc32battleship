import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {

	static final long serialVersionUID = 2L;

	private FlottaGUI flottaGUI;
	private GridGUI gridGUI;
	private GridGUI gridGUI2;
	private Game spelkontroll;
	
	private KnappPanel knappPanel;
	
	private JPanel ram;

	private JPanel messagePanel;
	private JLabel datorMessage;
	private JLabel personMessage;
	private JTextArea datorT;
	private JTextArea personT;
	
	public GUI(int rows, int cols, Game g) {

		spelkontroll = g;
		
		gridGUI = new GridGUI(rows, cols, spelkontroll, true);
		gridGUI2 = new GridGUI(rows, cols, spelkontroll, false);
		gridGUI.setBorder(new TitledBorder("Din Spelplan"));
		gridGUI2.setBorder(new TitledBorder("Motståndarens Spelplan"));
		flottaGUI = new FlottaGUI(gridGUI);
		flottaGUI.setBorder(new TitledBorder("Fleet"));
//		spelkontroll = new Game(this);

		knappPanel = new KnappPanel(spelkontroll);
		//knappPanel.setBorder(new TitledBorder(""));
		
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
		ram.setLayout(new GridLayout(3, 1));
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
	

}
