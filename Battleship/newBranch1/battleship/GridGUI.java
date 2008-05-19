package battleship;

import javax.swing.*;

import ships.Ship;

//import Cell.MyMouseHandler;

import java.awt.*;

/**
 * This class is a central information holder and binds the other classes
 * together.
 * 
 * @author Lars Öberg and David Gunnarsson
 * 
 */
public class GridGUI extends JComponent {

	static final long serialVersionUID = 3L;
	private CellGUI[][] cellTable;

	private int rows;
	private int cols;
	private int shipIndex = 0;

	private boolean vertical = true;
	private boolean isMyBoard;
	
	private Fleet fleet;
	private Game game;
	private GUI gui;
	private Grid grid;

	public GridGUI(int rows, int cols, Game g, boolean isMyBoard, GUI gu) {
		setLayout(new GridLayout(rows, cols));
		this.rows = rows;
		this.cols = cols;
		
		gui = gu;
		game = g;
		this.isMyBoard = isMyBoard;
		fleet = new Fleet(game, isMyBoard);
		cellTable = new CellGUI[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				cellTable[row][col] = new CellGUI(20, 20, row, col, this);
				this.add(cellTable[row][col]);
			}
		}
		grid = new Grid(rows, cols, this);
	}

	public void changeDir() {
		vertical = !vertical;
	}

	public boolean getDir() {
		return vertical;
	}

	public void setShip(int x, int y) {

		if (fleet.shipLeft(shipIndex)) {
			if (vertical) {
				try {
					grid.setShip(fleet.getShip(shipIndex), x, y, x
							+ fleet.getShip(shipIndex).getLength() - 1, y);
					gui.updateFleetGUI();
				} catch (BoatException e) {
					game.printToStatusField(e.getMessage());
				}
			}

			else {
				try {
					grid.setShip(fleet.getShip(shipIndex), x, y, x, y
							+ fleet.getShip(shipIndex).getLength() - 1);
					gui.updateFleetGUI();
				} catch (BoatException e) {
					game.printToStatusField(e.getMessage());
				}
			}
		} else
			game.printToStatusField("Too many ships");
		gui.disableShipButton(shipIndex);
	}

	public void bomb(int x, int y) {
		game.bombOpponent(x, y);
	}

	public boolean isBombed(int x, int y) {
		return grid.isBombed(x, y);
	}

	public int getYSize() {
		return rows;
	}

	public int getXSize() {
		return cols;
	}

	public void setShipIndex(int i) {
		shipIndex = i;
	}

	public int getShipIndex() {
		return shipIndex;
	}

	public Ship getShip(int index) {
		return fleet.getShip(index);
	}

	public Ship getCurrentShip() {
		return fleet.getShip(shipIndex);
	}

	public CellGUI getCellGUI(int X, int Y) {
		return cellTable[X][Y];
	}

	public boolean isMyBoard() {
		return isMyBoard;
	}

	public Grid getGrid() {
		return grid;
	}

	public boolean isVertical() {
		return vertical;
	}

	public Game getGame() {
		return game;
	}

	public Fleet getFleet() {
		return fleet;
	}
}
