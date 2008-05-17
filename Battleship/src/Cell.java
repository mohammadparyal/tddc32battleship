import ships.Ship;


public class Cell {
	private boolean bomb = false;
	private boolean skepp = false;
	private String skeppID;
	private Ship ship;
	private CellGUI cellGUI;
	
	
	
	//metoder
	//Metoder som bearbetar cellen
	public void setBomb(){
		bomb = true;
		cellGUI.setBomb();
		if (skepp){
			System.out.println(ship.getName());
		}
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
//		skepp = true;
//		skeppID = ID;
		cellGUI.setShip();
	}
	
	public void addGUI(CellGUI c){
		cellGUI = c;
	}
	
	//Metoder som ger information om cellen
	public boolean hasShip(){
		return skepp;
	}
	
	public boolean hasBomb(){
		return bomb;
	}
	
	public String getShipID(){
		return skeppID;
	}
	
	public CellGUI getGUI(){
		return cellGUI;
	}
}
