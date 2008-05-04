import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FlottaGUI extends JPanel {
	
	static final long serialVersionUID = 4L;
	
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
	

	public FlottaGUI(GridGUI g){
		grid = g;
		
		setLayout(new FlowLayout());
		boat = new JButton("Boat");
		ship = new JButton("Ship");
		cruiser = new JButton("Cruiser");
		battleship = new JButton("Battleship");
		
		n1 = new JLabel("1", JLabel.CENTER);
		n2 = new JLabel("1", JLabel.CENTER);
		n3 = new JLabel("1", JLabel.CENTER);
		n4 = new JLabel("1", JLabel.CENTER);
		
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
	
		add(p1);
		add(p2);
		add(p3);
		add(p4);
	}
	
	class BoatListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			grid.setShipsize(2);
		}
	}
	
	class CruiserListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			grid.setShipsize(4);
		}
	}
	
	class BattleshipListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
			grid.setShipsize(5);
		}
	}
	
	class ShipListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
			grid.setShipsize(3);
		}
	}
}
