import Båtar.Ship;
//import Network.*;

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
	
	
	//konstruktor
	public Game(){
		int rows = 10;
		int cols = 10;
		GUI gui = new GUI(rows, cols, this);
		
		spelplan1 = gui.getGrid1().getGrid();
		spelplan2 = gui.getGrid2().getGrid();
		
		fleet = gui.getGrid1().getFleet();
		
		
		Ship ship = new Ship(5, "bbb"); 
		try {
			spelplan2.setShip(ship, 0, 4, 0 + ship.getLength() - 1, 4);
		} catch (BoatException e) {
			System.out.println(e);
		}
		Ship ship2 = new Ship(5, "bbb"); 
		try {
			spelplan2.setShip(ship2, 3, 2, 3 + ship2.getLength() - 1, 2);
		} catch (BoatException e) {
			System.out.println(e);
		}
	}
		
	
	//Metoder
	public void sättSkepp(){}
	
	public void sättBomb(){}
	
	public void setStart(){
		start = true;
	}
	
	public boolean getStart(){
		return start;
	}
	
	public Flotta getFleet(){
		return fleet;
	}
	
	public static void main(String[] args) {

		Game g = new Game();
	}

}
