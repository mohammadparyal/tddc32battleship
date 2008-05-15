import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class KnappPanel extends JPanel{

	static final long serialVersionUID = 5L;
	
	private JButton start;
	private JButton avsluta;

	private JLabel chatt;
	private JTextField chatText;
	private JButton chattKnapp;
	
	private JPanel j1;
	private JPanel j2;

	private Game game;
	
	public KnappPanel(Game g){
		game = g;
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
		add(j2);
		add(j1);
		
		
		
	
	}
	
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
			
		}
	}
	
}
