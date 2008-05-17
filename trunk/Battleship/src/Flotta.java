import java.util.*;

import Båtar.*;

public class Flotta {
	//private List<Ship> flotta;
	private boolean utplacerad = false;

	private int allShips = 0;
	private int maxAllShips;
	
	private int nrBoats = 0;
	private int nrShips = 0;
	private int nrCruisers = 0;
	public int nrBattleships = 0;
	
	private int maxBoats;
	private int maxShips;
	private int maxCruisers;
	public int maxBattleships;
	
	private List<Ship> boats;
	private List<Ship> ships;
	private List<Ship> cruisers;
	private List<Ship> battleships;
	
	
	//konstruktor
	public Flotta(int bo, int sh, int cr, int ba){
		maxBoats = bo;
		maxShips = sh;
		maxCruisers = cr;
		maxBattleships = ba;
		
		maxAllShips = maxBoats + maxShips + maxCruisers + maxBattleships;
		
		boats = new ArrayList<Ship>(bo);
		ships = new ArrayList<Ship>(sh);
		cruisers = new ArrayList<Ship>(cr);
		battleships = new ArrayList<Ship>(ba);
	}
	
	//Metoder som ändrar flottan
	public void add(Ship s) throws ToManyShipsException{
		if (s.getLength() == 2){
//			if (nrBoats == maxBoats)
//				throw new ToManyShipsException("Max antal Båtar uppnått");
			boats.add(s);
			nrBoats++;
			allShips++;
		}	
		if (s.getLength() == 3){
//			if (nrShips == maxShips)
//				throw new ToManyShipsException("Max antal Skepp uppnått");
			ships.add(s);
			nrShips++;
			allShips++;
		}
		if (s.getLength() == 4){
//			if (nrCruisers == maxCruisers)
//				throw new ToManyShipsException("Max antal Kryssare uppnått");
			cruisers.add(s);
			nrCruisers++;
			allShips++;
		}
		if (s.getLength() == 5){
//			if (nrBattleships == maxBattleships)
//				throw new ToManyShipsException("Max antal Slagskepp uppnått");
			battleships.add(s);
			nrBattleships++;
			allShips++;
		}	
	}

	public boolean shipLeft(int n){
		if (n == 2)
			if (nrBoats == maxBoats)
				return false;
		
		if (n == 3)
			if (nrShips == maxShips)
				return false;
		
		if (n == 4)
			if (nrCruisers == maxCruisers)
				return false;
					
		if (n == 5)
			if (nrBattleships == maxBattleships)
				return false;
		
		return true;
	}
	
	//Metoder som ändrar skeppen
	
	
	//Metoder som ger information om flottan
	public boolean isPlaced(){
		if (nrBoats == maxBoats & nrBoats == maxBoats & nrCruisers == maxCruisers & nrBattleships == maxBattleships){
			return true;
		}
		else
			return false;
	}
	
	public String boatLeft(){
		int left = maxBoats - nrBoats;
		String temp = String.valueOf(left);
		return temp;
	}

	public String shipLeft(){
		int left = maxShips - nrShips;
		String temp = String.valueOf(left);
		return temp;
	}
	
	public String cruiserLeft(){
		int left = maxCruisers - nrCruisers;
		String temp = String.valueOf(left);
		return temp;
	}
	
	public String battleshipLeft(){
		int left = maxBattleships - nrBattleships;
		String temp = String.valueOf(left);
		return temp;
	}
/*	public void printFleet(){
		System.out.println("Flottan består av " + antalSkepp + " skepp, varav ");
		System.out.println(antalBoats + " Båtar");
		System.out.println(antalShips + " Skepp");
		System.out.println(antalCruisers + " Kryssare");
		System.out.println(antalBattleships + " Slagskepp");
		
		if (utplacerad = true)
			System.out.println("Skeppen är utplacerade");
		else
			System.out.println("Skeppen är inte utplacerade");
	}*/
}
