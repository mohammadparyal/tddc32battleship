import javax.swing.*;

import Båtar.Ship;

//import Cell.MyMouseHandler;

import java.awt.*;

public class GridGUI extends JComponent{
	
	static final long serialVersionUID = 3L;
	public CellGUI[][] cellTable;
	
	private int rows;
	private int cols;
	
	private int shipsize = 5;
	
	private Grid grid;

	private String dir = "South";
	
	private Flotta fleet; 
	
	public GridGUI(int rows, int cols)
	{		
		fleet = new Flotta(1, 1, 1, 1); 
		
		this.rows = rows;
		this.cols = cols;
		setLayout(new GridLayout(rows,cols));		
		cellTable = new CellGUI[rows][cols];

		for(int row = 0; row<rows; row++)
			for(int col = 0; col<cols; col++)
				cellTable[row][col] = new CellGUI(20,20, row, col, this);
		
		for(int row = 0; row<rows; row++)
			for(int col = 0; col<cols; col++)
				this.add(cellTable[row][col]);	
		
		grid = new Grid(rows, cols, this);
		
	}
	
	public void changeDir(){
		if (dir == "South")
			dir = "East";
		else if (dir == "East")
			dir = "South";
	}

	public String getDir(){
		return dir;
	}
	
	public void setShip(int x, int y, String dir) {
		
		Ship ship = new Ship(shipsize, "bbb");
		try {
			System.out.println("Nu läggs ett skepp till");
			fleet.add(ship);
			if (dir == "South"){
				try {
					grid.setShip(ship, x, y, x + ship.getLength()-1, y);
				} catch (BoatException e) {
					System.out.println(e);
				}
			}
			
			if (dir == "East"){
				try {
					grid.setShip(ship, x, y, x, y + ship.getLength()-1);
				} catch (BoatException e) {
					System.out.println(e);
				}
			}
		} 
		catch (ToManyShipsException e) {
			System.out.println(e);
		}
		

	}
	
	public void setShipsize(int i){
		shipsize = i;
	}
	
	public int getShipsize(){
		return shipsize;
	}
	
	public CellGUI getCellGUI(int X, int Y){
		return cellTable[X][Y];
	}
}
