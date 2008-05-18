package battleship;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/**
 * CellGUI is the visual representation of the cell class. Every CellGUI
 * corresponds to one instance of the cell class. It listens to mouse 
 * calls and translates them into operations. It also recieves calls 
 * by its corresponding cells. 
 * @author Lars Öberg and David Gunnarsson
 *
 */
public class CellGUI extends JComponent {
	static final long serialVersionUID = 1L;
	
	private Color color;
	private Color water;
	private Color mouseColor;
	
	private MyMouseHandler mouseHandler;
	
	private int posX;
	private int posY;
	
	private boolean ship = false;
	
	private GridGUI gridGUI;
		
	/**
	 * Creates a CellGUI with two integers, X and Y, corresponding to its 
	 * place in the GridGUI. It also gets a reference to the parent GridGUI.
	 * 
	 * @param x
	 * @param y
	 * @param a
	 * @param b
	 * @param g
	 */
	public CellGUI(int x, int y, int a, int b, GridGUI g)
	{
		posX = a;
		posY = b;
		gridGUI = g;
		this.setPreferredSize(new Dimension(x, y));
		water = new Color(0,130,180);
		mouseColor = Color.darkGray;
		color = water;
		
		mouseHandler = new MyMouseHandler();
		addMouseListener(mouseHandler);
	}
	
	/**
	 * Changes the boolean ship to true and if the CellGUI is myBoard the
	 * Color of the cellGUI changes.
	 */
	public void setShip(){
		ship = true;
		if (gridGUI.isMyBoard()){
			water = new Color(0,100,120);
			color = water;
			repaint();
		}
	}
	
	/**
	 * Changes the boolean bomb to true. If the ship boolean is true the water
	 * gets one color and else it gets another color. 
	 */
	public void setBomb(){
		water = new Color(0,150,220);
		color = water;
		if (ship){
			water = new Color(0,50,120);
			color = water;
		}
		repaint();
	}
	
	/**
	 * Depending on the value of certain booleans in game and griGUI different
	 * actions are called by the method mouseClick. For example if the cell
	 * belongs to MyBoard then the setShip method is called, and otherwise
	 * the setBomb method is called.
	 */
	public void mouseClick()
	{		
		if (gridGUI.isMyBoard()){
			if (!gridGUI.getGame().bothPlayersReady())
				gridGUI.setShip(posX, posY);}
		else{
			if (gridGUI.getGame().bothPlayersReady()){
				if (!gridGUI.isBombed(posX, posY)){
					gridGUI.bomb(posX, posY);
					gridGUI.getGame().changeTurn();
				}
				else
					gridGUI.getGame().printToStatusField("Platsen är redan bombad");
			}
		}
	}
	
	/**
	 * If the color is water color it changes to mouseColor.
	 */
	public void swapColor()
	{
		if (color == water)
			color = mouseColor;
		repaint();
	}

	/**
	 * f the color is mouseColor it changes the color back to water color.
	 */
	public void resetColor()
	{
		if (color == mouseColor)
			color = water;
		repaint();
	}
	
	/**
	 * Handles the mouseover effect.
	 */
	public void mouseOn()
	{
		int length = gridGUI.getShip(gridGUI.getShipIndex()).getLength();
		boolean vertical = gridGUI.isVertical();
		int xSize = gridGUI.getXSize();
		int ySize = gridGUI.getYSize();
		
		if (!vertical)
		{
			for (int i = 0; i < length; i++)
			{
				if (posY + i < ySize)
					gridGUI.getCellGUI(posX, posY + i).swapColor();
			}
		}
		else
		{
			for (int i = 0; i < length; i++)
			{
				if (posX + i < xSize)
					gridGUI.getCellGUI(posX + i, posY).swapColor();
			}
		}
	}

	/**
	 * Handles the mouseover effect.
	 */
	public void mouseOff()
	{
		int length = gridGUI.getShip(gridGUI.getShipIndex()).getLength();
		boolean vertical = gridGUI.isVertical();
		int xSize = gridGUI.getXSize();
		int ySize = gridGUI.getYSize();
		
		if (!vertical)
		{
			for (int i = 0; i < length; i++)
			{
				if (posY + i < ySize)
					gridGUI.getCellGUI(posX, posY + i).resetColor();
			}
		}
		else
		{
			for (int i = 0; i < length; i++)
			{
				if (posX + i < xSize)
					gridGUI.getCellGUI(posX + i, posY).resetColor();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Dimension d = getSize();
		
		g.setColor(color);
		g.fillRect(0, 0, d.width, d.height);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, d.width, d.height);		
	
	}

	/**
	 * Handles the mouse, mouseover and mouseclicked. 
	 *
	 */
	class MyMouseHandler extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e)
		{
			if (gridGUI.isMyBoard() && !gridGUI.getCurrentShip().isPlaced())
				mouseOn();
			else
				swapColor();
		}
		@Override
		public void mouseExited(MouseEvent e)
		{
			if (gridGUI.isMyBoard())
				mouseOff();
			else
				resetColor();
		}
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (SwingUtilities.isLeftMouseButton(e)){
				if (gridGUI.getGame().isMyTurn())
					mouseClick();
				else
					gridGUI.getGame().printToStatusField("Inte din tur");
			}

			else if (SwingUtilities.isRightMouseButton(e)){
				if (gridGUI.isMyBoard()){
					mouseOff();
					gridGUI.changeDir();
					mouseOn();}}
		}	
	}
}
