package ai;

import java.util.*;

import ships.*;

import battleship.*;

public class AI {

	private Random rand;
	private Game game;
	private int boardSizeX = 10;
	private int boardSizeY = 10;

	private Board board;

	public AI(Game game)
	{
		rand = new Random();
		this.game = game;
		board = new Board(boardSizeX, boardSizeY);

		placeBoat(new Ship(5, "Aircraft Carrier"));
		placeBoat(new Ship(4, "Battleship"));
		placeBoat(new Ship(3, "Destroyer"));
		placeBoat(new Ship(2, "Patrol Boat"));
		placeBoat(new Ship(3, "Submarine"));

		// skicka klartecken till spelaren, dvs "a"
	}

	public AI() // ta bort, enbart för att BattleTest ska fungera!!
	{
		rand = new Random();
		board = new Board(boardSizeX, boardSizeY);

		placeBoat(new Ship(5, "Aircraft Carrier"));
		placeBoat(new Ship(4, "Battleship"));
		placeBoat(new Ship(3, "Destroyer"));
		placeBoat(new Ship(2, "Patrol Boat"));
		placeBoat(new Ship(3, "Submarine"));
	}

	/**
	 * Spelaren bombar datorn
	 * 
	 * @param x
	 *            x-koordinat
	 * @param y
	 *            y-koordinat
	 */
	public void bomb(int x, int y)
	{
		if (board.isFree(x, y)) // ej träff
		{
			board.placeBomb(x, y);
			game.print("Miss!");
		}
		else
		// träff
		{
			board.getShip(x, y).hit();
			if (board.getShip(x, y).isSunk())
			{
				game.print("You sunk my " + board.getShip(x, y).getName());
				// uppdatera
			}
			board.placeBomb(x, y);
			game.print("Hit!");
		}
	}

	/**
	 * Datorn väljer slumpmässiga koordinater på spelbrädan att bomba. Kollar
	 * först så att koordinaten inte redan är bombad
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
		while (!game.getBoard().isBombed(x, y)); // om koordinaten är bombad
													// gör ny

		game.getBoard().placeBomb(x, y);
	}

	/**
	 * Placerar ett skepp slumpmässigt på spelplanen
	 * 
	 * @param boat
	 */
	public void placeBoat(Ship boat)
	{
		int yStart = 0;
		int yEnd = 0;
		int xStart = 0;
		int xEnd = 0;
		// direction
		int direction = rand.nextInt(2);

		// direction == 0 => horisontellt läge
		if (direction == 0)
		{
			yStart = rand.nextInt(boardSizeY); // y c [0,10[
			xStart = rand.nextInt(boardSizeX - boat.getLength() + 1);
			xEnd = xStart + boat.getLength();

			// kolla så att alla koordinater är lediga

			boolean free = true;
			for (int i = xStart; i < xEnd; i++)
			{
				if (free)
					free = board.isFree(i, yStart);
			}

			if (free)
			{
				// lägg skeppet till spelbrädan
				boat.setHorizontal();
				board.placeShip(boat, xStart, yStart);
			}
			else
				// gör om
				placeBoat(boat);
		}
		else
		// vertikal
		{
			xStart = rand.nextInt(boardSizeX);
			yStart = rand.nextInt(boardSizeY - boat.getLength() + 1);
			yEnd = yStart + boat.getLength();

			boolean free = true;
			for (int i = yStart; i < yEnd; i++)
			{
				if (free)
					free = board.isFree(xStart, i);
			}

			if (free)
			{
				// lägg skeppet till spelbrädan
				boat.setVertical();
				board.placeShip(boat, xStart, yStart);
			}
			else
				// gör om
				placeBoat(boat);
		}
	}

	public void printBoard()
	{
		System.out.println(board.toString());
	}
}
