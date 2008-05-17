package battleship;
import network.NetworkPlayer;
import ships.Ship;
//import Network.*;
import ai.*;
import battleship.*;

public class Game {
	
	//2 rutnät (skapar två rutnät)
	
	
	private int träffräknare;
	
	private GUI gui;
//	private GUI2 gui2;
		
//	private Network net;
	
	private Grid spelplan1;
	private Grid spelplan2;
	
	private boolean start = false;
	
	private Flotta fleet;
	
	private Player player;
	
	private boolean myTurn = true;
	
	
	//konstruktor
	public Game(){
		int rows = 10;
		int cols = 10;
		
		
		
		GUI gui = new GUI(rows, cols, this);
		
		spelplan1 = gui.getGrid1().getGrid();
		spelplan2 = gui.getGrid2().getGrid();
		
		fleet = gui.getGrid1().getFleet();
		
		
//		Ship ship = new Ship(5, "bbb");
	//	gui.getGrid2().setShip(0, 1, "south");
 
/*		try {
			spelplan2.setShip(ship, 0, 4, 0 + ship.getLength() - 1, 4);
		} catch (BoatException e) {
			System.out.println(e);
		}
		Ship ship2 = new Ship(5, "bbb"); 
		try {
			spelplan2.setShip(ship2, 3, 2, 3 + ship2.getLength() - 1, 2);
		} catch (BoatException e) {
			System.out.println(e);
		}*/
	}
		
	
	//Metoder
	
	public void makeAI()
	{
		player = new AI(this);
	}
	
	public void makeNetwork()
	{
		player = new NetworkPlayer(this);
	}
	
	public void sättSkepp(){}
	
	public void sättBomb(int x, int y){
		spelplan1.bomba(x, y);
	}
	
	public void setStart(){
		start = true;
	}
	
	public void print(String s)
	{
		gui.updateStatus(s);
	}
	
	public boolean getStart(){
		return start;
	}
	
	public Flotta getFleet(){
		return fleet;
	}
	
	public Grid getMotståndarplan(){
		return spelplan2;
	}

	public Grid getMinplan(){
		return spelplan1;
	}
	
	public void changeTurn(){
		myTurn = !myTurn; 
		System.out.println(myTurn);
		if(!myTurn){
			player.goAhead();
			System.out.println(myTurn);
		}
	}
	
	public boolean isMyTurn(){
		return myTurn;
	}
	
	
	public static void main(String[] args) {

		new Game();
	}
	

}
