package battleship;

import ships.*;

;

public class Board {

	private int boardSizeX;
	private int boardSizeY;
	private Ship[][] board;
	private Ship bomb;

	public Board(int x, int y)
	{
		boardSizeX = x;
		boardSizeY = y;
		board = new Ship[x][y];
		bomb = new Ship(1, "bomb");
	}

	/**
	 * Kollar så att skeppet får plats på spelbrädan och att inget annat skepp
	 * ligger i vägen
	 * 
	 * @param ship
	 * @param xStart
	 *            början på skeppet
	 * @param yStart
	 *            början på skeppet
	 * @param vertical
	 *            <code>true</code> om skeppet ligger vertikalt ifrån
	 *            utgångspunkten
	 * @return <code>true</code> om inget hinder föreligger
	 */
	public boolean isSpace(Ship ship, int xStart, int yStart)
	{
		boolean free;
		boolean vertical = ship.isVertical();
		int length = ship.getLength();
		if (vertical)
			free = yStart + length < boardSizeY;
		else
			free = xStart + length < boardSizeX;

		int i = 0;
		while (free && i < length)
		{
			if (vertical)
				free = isFree(xStart, yStart + i++);
			else
				free = isFree(xStart + i++, yStart);
		}
		return free;
	}

	/**
	 * Metod för att placera skepp på spelplanen När metoden får koordinaterna
	 * har annan metod kollat så att plats finns
	 * 
	 * @param ship
	 *            skepp att placera ut
	 * @param xStart
	 *            x-koordinat för startpunkt
	 * @param yStart
	 *            y-koordinat för startpunkt
	 * @param vertical
	 *            <code>true</code> om skeppet placeras vertikalt
	 */
	public void placeShip(Ship ship, int xStart, int yStart)
	{
		if (isSpace(ship, xStart, yStart))
		{
			int length = ship.getLength();
			for (int i = 0; i < length; i++)
			{
				if (ship.isVertical())
					board[xStart][yStart + i] = ship;
				else
					board[xStart + i][yStart] = ship;
			}
		}
	}

	/**
	 * Används för att markera en ruta som bombad
	 * 
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

		for (int y = 0; y < board.length; y++)
		{
			row = "|";
			for (int x = 0; x < board.length; x++)
			{
				if (!(board[x][y] == null))
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
	 * 
	 * @param x
	 *            mellan 0 och x-1
	 * @param y
	 *            mellan 0 och y-1
	 * @return <code>true</code> om platsen är ledig
	 */
	public boolean isFree(int x, int y)
	{
		return board[x][y] == null;
	}

	/**
	 * Kollar om koordinaten är bombad
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isBombed(int x, int y)
	{
		return board[x][y] == bomb;
	}

	public Ship getShip(int x, int y)
	{
		return board[x][y];
	}
}
