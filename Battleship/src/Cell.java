
public class Cell {
	private boolean bomb = false;
	private boolean skepp = false;
	private String skeppID;
	private CellGUI gui;
	
	
	
	//metoder
	//Metoder som bearbetar cellen
	public void setBomb(){
		bomb = true;
		gui.setBomb();
	}
	
	public void setShip(String ID){
		skepp = true;
		skeppID = ID;
		gui.setShip();
	}
	
	public void addGUI(CellGUI c){
		gui = c;
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
		return gui;
	}
}
