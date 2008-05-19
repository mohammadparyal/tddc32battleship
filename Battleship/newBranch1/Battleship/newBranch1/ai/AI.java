package ai;

import java.util.*;

import ships.*;

import battleship.*;

/**
 * Handles a computer controlled opponent. 
 * @author Lars Öberg and David Gunnarsson
 *
 */
public class AI implements Player{

	private Random rand;
	private Game game;
	private int boardSizeX;
	private int boardSizeY;

	private Grid board;
	private Grid playerBoard;
	
	private Fleet aiFleet;

	public AI(Game game)
	{
		rand = new Random();
		this.game = game;
		board = game.getOpponentBoard();
		playerBoard = game.getPlayerBoard();
		boardSizeX = board.getXSize();
		boardSizeY = boardSizeX;
		aiFleet = board.getGridGUI().getFleet();
		for (int index = 0; index < 5; index++){
			placeBoat(aiFleet.getShip(index));
		}
		game.setOpponentStart();
	}

	/**
	 * Places a bomb on the ai board.
	 * @param x
	 * @param y
	 */
	public void bomb(int x, int y)
	{
		boolean hasShip = board.hasShip(x, y);
		Ship sunkShip = board.getShip(x, y);

		board.setBomb(x, y);
		if (!hasShip)
		{
			game.printToStatusField("Miss!");
		}
		else	
		{
			game.printToStatusField("Hit!");
			
			if (sunkShip.isSunk())
			{
				game.printToStatusField("You sunk my " + board.getShip(x, y).getName());
			}
		}
	}

	/**
	 * Chooses random coordinates on the board to bomb. 
	 */
	public void randBomb()
	{
		int x;
		int y;
		do
		{
			x = rand.nextInt(boardSizeX);
			y = rand.nextInt(boardSizeY);
		}
		while (playerBoard.isBombed(x, y)); 	
		game.placeBomb(x, y);		
	}

	/**
	 * Places a ship on the board.
	 * 
	 * @param boat
	 */
	public void placeBoat(Ship boat)
	{
		int yStart = 0;
		int yEnd = 0;
		int xStart = 0;
		int xEnd = 0;
		int direction = rand.nextInt(2);

		// direction == 0 => horizontal
		if (direction == 0)
		{
			yStart = rand.nextInt(boardSizeY); 
			xStart = rand.nextInt(boardSizeX - boat.getLength());
			xEnd = xStart + boat.getLength();
			
			boolean free = true;
			for (int i = xStart; i < xEnd; i++)
			{
				if (free)
					free = !board.hasShip(i, yStart);
			}
			if (free)
			{
				boat.setHorizontal();
				try {
					board.setShip(boat, xStart, yStart, xStart+boat.getLength()-1, yStart);
				} catch (BoatException e) {
					e.printStackTrace();
				}
			}
			else
				placeBoat(boat);
		}
		else
		{
			xStart = rand.nextInt(boardSizeX);
			yStart = rand.nextInt(boardSizeY - boat.getLength());
			yEnd = yStart + boat.getLength();
			
			boolean free = true;
			for (int i = yStart; i < yEnd; i++)
			{
				if (free)
					free = !board.hasShip(xStart, i);
			}

			if (free)
			{
				boat.setVertical();
				try {
					board.setShip(boat, xStart, yStart, xStart, yStart+boat.getLength()-1);
				} catch (BoatException e) {
					e.printStackTrace();
				}
			}
			else
				placeBoat(boat);
		}
	}
	
	public void goAhead()
	{
		randBomb();
		game.changeTurn();
	}

	public void sendMessage(String s)
	{
		game.printToChat(s);
	}
	public void setOpponentReady() {}
	
	public void exit(){}
	
}
