package battleship;
import java.util.*;

import ships.Ship;

/**
 * Creates the ships and holds them in a list. It also contains methods for
 * access to the ships and information about whether the fleet is placed or not 
 * and sunk or not.
 * 
 * @author Lars Öberg and David Gunnarsson
 *
 */
public class Fleet {

	private ArrayList<Ship> ships;
	private Game game;
	private boolean isMyFleet;
	
	//Constructor	
	public Fleet(Game game, boolean isMyFleet){
		this.game = game;
		this.isMyFleet = isMyFleet;
		ships = new ArrayList<Ship>();
		ships.add(new Ship(5, "Aircraft Carrier", this));
		ships.add(new Ship(4, "Battleship", this));
		ships.add(new Ship(3, "Destroyer", this));
		ships.add(new Ship(3, "Submarine", this));
		ships.add(new Ship(2, "Patrol Boat", this));						
		
	}
	
	//Methods
	public boolean shipLeft(int index){
		
		return !ships.get(index).isPlaced();
	}
	
	/**
	 * Checks to see if all ships has been placed
	 * @return true if all ships are placed
	 */
	public boolean isPlaced(){
		boolean isPlaced = true;
		for(Ship s:ships)
		{
			if (isPlaced){
				isPlaced = s.isPlaced();
			}
		}
		return isPlaced;
	}
	
	public Ship getShip(int index) {
		return ships.get(index);
	}

	public void isSunk(){
		boolean isSunk = true;
		for (Ship s: ships){
			if (isSunk){
				isSunk = s.isSunk();
			}
		}
		if (isSunk && isMyFleet)
		{
			game.sendMessage("---===You win!===---");
			game.setGameOver();
		}
	}
}
