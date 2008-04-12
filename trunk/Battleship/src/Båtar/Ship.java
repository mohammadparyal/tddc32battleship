package Båtar;

public class Ship {
	private int length;
	private String name;
	private int x;
	private int y;
	private int dir;
	
	public Ship(int l, String n){
		length = l;
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public int getLength(){
		return length;
	}
}
