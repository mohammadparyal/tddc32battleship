package gui;

import javax.swing.*;
import java.awt.*;

public class Grid extends JComponent {

	static final long serialVersionUID = 3L;
	private int xSize;
	private int ySize;
	private Cell[][] cellTable;

	public Grid(GUI gui, int cols, int rows)
	{
		xSize = cols;
		ySize = rows;
		this.setLayout(new GridLayout(cols, rows));
		cellTable = new Cell[cols][rows];

		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				cellTable[col][row] = new Cell(gui, this, 20, 20, col, row);

		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				this.add(cellTable[col][row]);
	}

	public Cell getCell(int x, int y)
	{
		return cellTable[x][y];
	}

	public int getXSize()
	{
		return xSize;
	}

	public int getYSize()
	{
		return ySize;
	}
}
