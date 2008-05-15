package ships;

public class Ship {
	private int length;
	private String name;
	private int health;
	
	public Ship(int l, String n){
		length = l;
		name = n;
		health = length;
	}
	
	public String getName(){
		return name;
	}
	
	public int getLength(){
		return length;
	}
	
	public void hit()
	{
		health--;
	}
	
	public boolean isSunk()
	{
		return (health == 0);
	}
}
