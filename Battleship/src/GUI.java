import java.awt.GridLayout;

import javax.swing.*;
public class GUI extends JFrame {

	static final long serialVersionUID = 2L;
	
	private FlottaGUI flotta;
	private GridGUI grid;
	private Spelkontroll spelkontroll;
	
	
	public GUI(int rows, int cols){
		spelkontroll = new Spelkontroll(this);
				
		setLayout(new GridLayout(1, 2));
		
	//	mo = new MouseOver();
		
		grid = new GridGUI(rows, cols);

		
		flotta = new FlottaGUI(grid);
		
	//	mo.setVisible(true);
		
	//	add(mo);
		add(grid);
		add(flotta);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		
	}
	
	
	public GridGUI getGrid(){
		return grid;
	}
}
