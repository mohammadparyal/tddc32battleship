import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FlottaGUI extends JPanel {
	
	static final long serialVersionUID = 4L;
	
	private JButton boat;
	private JButton cruiser;
	private JButton battleship;
	private JButton ship;
	
	private GridGUI grid;
	public FlottaGUI(GridGUI g){
		grid = g;
		
		setLayout(new FlowLayout());
		boat = new JButton("Boat");
		ship = new JButton("Ship");
		cruiser = new JButton("Cruiser");
		battleship = new JButton("Battleship");

		boat.addActionListener(new BoatListener());
		ship.addActionListener(new ShipListener());
		cruiser.addActionListener(new CruiserListener());
		battleship.addActionListener(new BattleshipListener());

		add(boat);
		add(ship);
		add(cruiser);
		add(battleship);
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
