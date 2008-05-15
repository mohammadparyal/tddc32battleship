package battleship;
import ships.*;;

public class Board {
	
	private Ship[][] board;
	private Ship bomb;
	
	public Board(int x, int y)
	{
		board = new Ship[x][y];
		bomb = new Ship(1, "bomb");
	}
	
	/**
	 * Metod för att placera skepp på spelplanen
	 * När metoden får koordinaterna har annan metod kollat så att plats finns
	 * @param ship skepp att placera ut
	 * @param xStart x-koordinat för startpunkt
	 * @param yStart y-koordinat för startpunkt
	 * @param vertical <code>true</code> om skeppet placeras vertikalt
	 */
	public void placeShip(Ship ship, int xStart, int yStart, boolean vertical)
	{		
		for(int i=0; i<ship.getLength(); i++)
		{
			if(vertical)
				board[xStart][yStart+i] = ship;
			else
				board[xStart+i][yStart] = ship;
		}
	}
	
	/**
	 * Annan variant av föregående
	 * @param ship
	 * @param xStart
	 * @param yStart
	 * @param xEnd
	 * @param yEnd
	 */
	public void placeShip(Ship ship, int xStart, int yStart, int xEnd, int yEnd)
	{
		for(int i=0; i<ship.getLength(); i++)
		{
			if(xStart==xEnd)
				board[xStart][yStart+i] = ship;
			if(yStart==yEnd)
				board[xStart+i][yStart] = ship;
			else
				System.err.println("error!");
		}
	}
	
	
	/**
	 * Används för att markera en ruta som bombad
	 * @param x
	 * @param y
	 */
	public void placeBomb(int x, int y)
	{
		board[x][y] = bomb;
	}
	
	/**
	 * Returnerar brädan i form av en sträng
	 */
	public String toString()
	{
		String boardString = "";
		String row;

		for(int y=0; y<board.length; y++)
		{
			row = "|";
			for(int x=0; x<board.length; x++)
			{
				if(!(board[x][y]==null))
					row = row + board[x][y].getName().charAt(0) + "|";
				else
					row = row + "_|";
			}
			boardString = boardString + row + "\n";
		}
		return boardString;
	}
	
	/**
	 * Kollar om en koordinat är ledig
	 * @param x mellan 0 och x-1
	 * @param y mellan 0 och y-1
	 * @return <code>true</code> om platsen är ledig
	 */
	public boolean isFree(int x, int y)
	{
		return board[x][y] == null;	
	}
}
