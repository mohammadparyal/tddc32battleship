import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Båtar.Ship;

public class CellGUI extends JComponent {
	static final long serialVersionUID = 1L;
	
	private Color color;
	private Color water;
	private Color watership;
	private Color mouseColor;
	
	private MyMouseHandler ml;
	
	private int posX;
	private int posY;
	
	private boolean ship = false;
	private boolean bomb = false;
	
	private GridGUI grid;
	
	private String dir = "South";
	
	public CellGUI(int x, int y, int a, int b, GridGUI g)
	{
		posX = a;
		posY = b;
		grid = g;
		this.setPreferredSize(new Dimension(x, y));
		water = new Color(0,130,180);
		mouseColor = Color.darkGray;
		color = water;
		
		ml = new MyMouseHandler();
		addMouseListener(ml);
	}
	
	public void setShip(){
		ship = true;
		if (grid.getMinSpelplan()){
			water = new Color(0,100,120);
			color = water;
			repaint();
		}
		System.out.println("setShip för cellen: " + posX + " och " + posY);
	}
	
	public void setBomb(){
		bomb = true;
		water = new Color(0,150,220);
		color = water;
		if (ship){
			water = new Color(0,50,120);
			color = water;
		}
		repaint();
		System.out.println("setBomb för cellen: " + posX + " och " + posY);
	}
	
	public void mouseClick(MouseEvent e)
	{
		
		if (grid.getMinSpelplan()){
			if (!grid.getGame().getStart())
				grid.setShip(posX, posY, grid.getDir());}
		else{
			if (grid.getGame().getStart())
				grid.bomb(posX, posY);}
	}
	
	public void swapColor()
	{
		if(color == water)
			color = mouseColor;
		else
			color = water;		
	}
	
	public void paintComponent(Graphics g)
	{
		Dimension d = getSize();
		
		g.setColor(color);
		g.fillRect(0, 0, d.width, d.height);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, d.width, d.height);		
	
	}

	class MyMouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e)
		{
			//mouseOn();
		}
		public void mouseExited(MouseEvent e)
		{
			//mouseOff();
		}
		public void mouseClicked(MouseEvent e)
		{
			if (SwingUtilities.isLeftMouseButton(e))
				mouseClick(e);
			else if (SwingUtilities.isRightMouseButton(e))
				grid.changeDir();
		}	
	}
}


//public void mouseOn()
//{
//	color = mouseColor;
//	repaint();
//}

//public void mouseOff()
//{
//	color = water;
//	repaint();
//}
