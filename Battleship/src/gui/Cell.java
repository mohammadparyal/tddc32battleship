package gui;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

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
	
	private MyMouseHandler ml;
	
	public Cell(GUI gui, Grid grid, int x, int y, int xID, int yID)
	{
		this.gui = gui;
		this.grid = grid;
		this.setPreferredSize(new Dimension(x, y));
		this.xID = xID;
		this.yID = yID;
		water = new Color(0,130,180);
		mouseColor = Color.darkGray;
		color = water;

		String ID = Integer.toString(xID)+','+Integer.toString(yID);
		cellNumberLabel = new JLabel(ID);
		this.add(cellNumberLabel);
		
		ml = new MyMouseHandler();
		addMouseListener(ml);
	}
			
	public void swapColor()
	{
		if(color == water)
			color = mouseColor;
//		else if(color == mouseColor)
//			color = water;
	}
	
	public void resetColor()
	{
		if(color == mouseColor)
			color = water;
	}
	
	public void updateCellID()
	{
		gui.mouseOver(this);
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

	class MyMouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e)
		{
			grid.swapColor(1,xID,yID);
			repaint();
			updateCellID();
			gui.placeShip();
		}
		public void mouseExited(MouseEvent e)
		{
//			grid.swapColor(1,xID,yID);
			resetColor();
			repaint();
		}
		public void mouseClicked(MouseEvent e)
		{
			gui.statusUpdate("klick på " + xID + ',' + yID);
			color = Color.red;
			repaint();
			gui.getGame().placeShip(gui.getShip(), xID, yID, false);
		}
	}
}