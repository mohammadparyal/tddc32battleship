package ships;
import battleship.*;

/**
 * Contains length, name and orientation of the ship and also what fleet it
 * belongs to. 
 * @author Lars Öberg and David Gunnarsson
 *
 */
public class Ship {
	private int length;
	private String name;
	private int health;
	private boolean vertical;
	private boolean isPlaced;
	private Fleet fleet;

	public Ship(int l, String n, Fleet f)
	{
		fleet = f;
		length = l;
		name = n;
		health = length;
		vertical = false;
	}

	public String getName()
	{
		return name;
	}

	public int getLength()
	{
		return length;
	}

	public void setVertical()
	{
		vertical = true;
	}

	public void setHorizontal()
	{
		vertical = false;
	}

	public boolean isVertical()
	{
		return vertical;
	}

	public void hit()
	{
		health--;
		if (isSunk()){
			fleet.isSunk();
		}
	}

	public boolean isSunk()
	{
		return (health == 0);
	}

	public void rotate()
	{
		if (vertical)
			vertical = false;
		else
			vertical = true;
	}
	
	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced() {
		isPlaced = true;
	}
}
