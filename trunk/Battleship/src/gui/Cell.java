package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import ships.Ship;

public class Cell extends JPanel {
	static final long serialVersionUID = 1L;

	private GUI gui;
	private Grid grid;

	private Color color;
	private Color water;
	private Color mouseColor;

	private int xID;
	private int yID;

	private JLabel cellNumberLabel;

	public Cell(GUI gui, Grid grid, int x, int y, int xID, int yID)
	{
		this.gui = gui;
		this.grid = grid;
		this.setPreferredSize(new Dimension(x, y));
		this.xID = xID;
		this.yID = yID;
		water = new Color(0, 130, 180);
		mouseColor = Color.darkGray;
		color = water;

		String ID = Integer.toString(xID) + ',' + Integer.toString(yID);
		cellNumberLabel = new JLabel(ID);
		this.add(cellNumberLabel);

		addMouseListener(new MyMouseHandler());
	}

	public void swapColor()
	{
		if (color == water)
			color = mouseColor;
		repaint();
	}

	public void resetColor()
	{
		if (color == mouseColor)
			color = water;
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		Dimension d = getSize();

		g.setColor(color);
		g.fillRect(0, 0, d.width, d.height);

		g.setColor(Color.black);
		g.drawRect(0, 0, d.width, d.height);
	}

	public int getXID()
	{
		return xID;
	}

	public int getYID()
	{
		return yID;
	}

	public Grid getGrid()
	{
		return grid;
	}

	public void mouseOn()
	{
		if (gui.getShip() != null)
		{
			Ship ship = gui.getShip();
			int xSize = grid.getXSize();
			int ySize = grid.getYSize();
			int length = ship.getLength();

			if (ship.isVertical())
			{
				for (int i = 0; i < length; i++)
				{
					if (yID + i < ySize)
						grid.getCell(xID, yID + i).swapColor();
				}
			}
			else
			{
				for (int i = 0; i < length; i++)
				{
					if (xID + i < xSize)
						grid.getCell(xID + i, yID).swapColor();
				}
			}
		}
		else
			swapColor();
	}

	public void mouseOff()
	{
		if (gui.getShip() != null)
		{
			Ship ship = gui.getShip();
			int xSize = grid.getXSize();
			int ySize = grid.getYSize();
			int length = ship.getLength();

			if (ship.isVertical())
			{
				for (int i = 0; i < length; i++)
				{
					if (yID + i < ySize)
						grid.getCell(xID, yID + i).resetColor();
				}
			}
			else
			{
				for (int i = 0; i < length; i++)
				{
					if (xID + i < xSize)
						grid.getCell(xID + i, yID).resetColor();
				}
			}
		}
		else
			resetColor();
	}

	class MyMouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e)
		{
			mouseOn();
		}

		public void mouseExited(MouseEvent e)
		{
			mouseOff();
		}

		public void mouseClicked(MouseEvent e)
		{
			if (gui.getShip() != null)
			{
				gui.getGame().getBoard().placeShip(gui.getShip(), xID, yID);
				gui.emptyShip();
			}
			gui.statusUpdate("klick på " + xID + ',' + yID);
			color = Color.red;
			repaint();
		}
	}
}