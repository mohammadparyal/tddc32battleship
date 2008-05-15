package battleship;

import gui.*;
import Network.NetworkPlayer;
import ai.AI;
import ships.Ship;


/**
 * This is the main class that starts the game.
 * @author David
 *
 */
public class Game {	

	private NetworkPlayer net;
	private AI ai;
	private GUI gui;
	private int boardSizeX;
	private int boardSizeY;
	private Board board;
	
	public Game()
	{	
		boardSizeX = 10;
		boardSizeY = 10;
		gui = new GUI(this, boardSizeX, boardSizeY);		
		board = new Board(boardSizeX,boardSizeY);
	}
	
	public void makeAI()
	{
		ai = new AI();
	}
	
	/**
	 * Metod för att skapa ny instans av nätverksspelare. Ligger inte
	 * i konstruktorn ifall spelaren inte ska använda nätverket
	 */
	public void makeNetwork()
	{
		net = new NetworkPlayer(this);
	}
	
	/**
	 * sänd sträng till nätverksspelare
	 * @param s sträng att sända
	 */
	public void send(String s)
	{
		net.send(s);
	}
	
	/**
	 * Skriver ut en sträng till GUI:ts statusfält
	 * @param s
	 */
	public void print(String s)
	{
		gui.statusUpdate(s);
	}
	
	/**
	 * Gör spelserver
	 * @param port port motståndaren ska ansluta till
	 */
	public void create(int port)
	{
		net.create(port);
	}
	
	/**
	 * Ansluter till motståndarens server
	 * @param address ip-adress
	 * @param port port hos motståndaren
	 */
	public void connect(String address, int port)
	{
		net.connect(address, port);
		send("connected");
	}
	
	/**
	 * Stänger uppkopplingen
	 */
	public void close()
	{
		net.close();
		print("Other player left, you win!");
	}
	
	/**
	 * koordinaterna ska redan vara kollade innan metoden används
	 * @param ship
	 */
	public void placeShip(Ship ship,int x,int y,Boolean vertical)
	{
		board.placeShip(ship, x, y, vertical);
	}
	
	public void printBoard()
	{
		System.out.println(board.toString());
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		Game game = new Game();
	}	
}
