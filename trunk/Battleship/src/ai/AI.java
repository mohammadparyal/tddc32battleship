package ai;

import java.util.*;

import ships.*;

import battleship.*;

public class AI {
	
	private Random rand;
	
	private int boardSizeX = 10;
	private int boardSizeY = 10;
	
	private ArrayList<Ship> boats;
	private ArrayList<String> bombs;
	
	private Board board;
	
	public AI()
	{
		rand = new Random();
		
		board = new Board(boardSizeX,boardSizeY);

		boats = new ArrayList<Ship>();
		bombs = new ArrayList<String>();
				
		boats.add(new Ship(5, "Aircraft Carrier"));
		boats.add(new Ship(4, "Battleship"));
		boats.add(new Ship(3, "Destroyer"));
		boats.add(new Ship(2, "Patrol Boat"));
		boats.add(new Ship(3, "Submarine"));
//		boats.add(new Ship(1, "bomb"));

		//skicka klartecken till spelaren, dvs "a"
	}

	/**
	 * Spelaren bombar datorn
	 * @param x x-koordinat
	 * @param y y-koordinat
	 */
	public void bomb(int x, int y)
	{
		Boolean hit;
		//kolla spelbrädan om någon båt är i fara
		if(board.isFree(x, y))
		{
			board.placeBomb(x, y);
			hit = false;
		}		
		else
		{
			board.placeBomb(x, y);
			hit = true;
		}	

	}
	
	/**
	 * Bombar slumpmässiga koordinater på spelbrädan
	 * Kollar så att koordinaten inte redan är bombad
	 * @return strängen med koordinater att bomba "c:x,y"
	 */
	public String randBomb()
	{
		int x = rand.nextInt(boardSizeX);
		int y = rand.nextInt(boardSizeY);
		String coords = "c:" + x + "," + y;
		
		if(bombs.indexOf(coords) != -1)
			coords = randBomb();
		
		return coords;
	}			
	
	/**
	 * Placerar ett skepp slumpmässigt på spelplanen
	 * @param boat
	 */
	public void placeBoat(Ship boat)
	{
		int yStart = 0;
		int yEnd = 0;
		int xStart = 0;
		int xEnd = 0;
		//direction
		int direction = rand.nextInt(2);
		
		//direction == 0 => horisontellt läge
		if(direction == 0)
		{
			yStart = rand.nextInt(boardSizeY); // y c [0,10[			
			xStart = rand.nextInt(boardSizeX - boat.getLength()+1);
			xEnd = xStart + boat.getLength();
			
			//kolla så att alla koordinater är lediga
			
			boolean free = true;
			for(int i=xStart; i<xEnd; i++)
			{
				if(free)
					free = board.isFree(i, yStart);
			}
			
			if(free)
				//lägg skeppet till spelbrädan
				board.placeShip(boat, xStart, yStart, false);
			else
				//gör om
				placeBoat(boat);
		}
		else //vertikal
		{
			xStart = rand.nextInt(boardSizeX);			
			yStart = rand.nextInt(boardSizeY - boat.getLength()+1);
			yEnd = yStart + boat.getLength();
			
			
			boolean free = true;
			for(int i=yStart; i<yEnd; i++)
			{
				if(free)
					free = board.isFree(xStart, i);
			}
			
			if(free)
				//lägg skeppet till spelbrädan
				board.placeShip(boat, xStart, yStart, true);
			else
				//gör om
				placeBoat(boat);
		}
	}
	
	/**
	 * Placerar ut samtliga skepp i listan
	 * @param boats
	 */
	public void placeBoats()
	{
		for(Ship ship: boats)
			placeBoat(ship);
	}
	
	public void printBoard()
	{
		System.out.println(board.toString());
	}
}
