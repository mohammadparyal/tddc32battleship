package battleship;
import network.NetworkPlayer;
import ai.*;

/**
 * Contains the main method for starting the game.
 * It is the class connecting the two players; all the information
 * between the players goes through this class. 
 * Most methods acts on either the Player or the Opponent.
 * Controls the players board and creates the AI and NetworkPlayer when needed.
 * 
 * @author Lars Öberg and David Gunnarsson
 */
public class Game {	
	private GUI gui;
	
	private Grid playerBoard;
	private Grid opponentBoard;
	
	private int xBombed;
	private int yBombed;
	
	private boolean playerReady = false;
	private boolean opponentStart = false;
	
	private Player opponent;
	
	private boolean myTurn = true;
	private int port;

	//Constructor
	public Game(){
		int rows = 10;
		int cols = 10;
		port = 31415;
		gui = new GUI(rows, cols, this);
		
		playerBoard = gui.getGrid1().getGrid();
		opponentBoard = gui.getGrid2().getGrid();		
	}	
	
	//Methods to use on the opponent.
		public void makeAI()
	{
		opponent = new AI(this);
	}
	
	public void createNetwork()
	{
		NetworkPlayer net = new NetworkPlayer(this);
		opponent = net;
		net.create(port);
	}
	
	public void connectNetwork(String address)
	{
		NetworkPlayer net = new NetworkPlayer(this);
		opponent = net;
		net.connect(address, port);
		myTurn = false;
		
	}
	
	public void sendMessage(String s)
	{
		opponent.sendMessage(s);
	}
	
	public void bombOpponent(int x, int y)
	{
		xBombed = x;
		yBombed = y;
		opponent.bomb(x,y);
	}
	
	public void setPlayerReady(){
		playerReady = true;
		if (opponent != null) {
			opponent.setOpponentReady();
		}
	}	
	
	
	/////////////////////////////////////////////////
	
	
	//Methods to use on the local player.
	public boolean placeBomb(int x, int y){
		return playerBoard.setBomb(x, y);
	}

	public void setHit(boolean hit)
	{
		if(hit)
			opponentBoard.setHit(xBombed, yBombed);
		else
			opponentBoard.setMiss(xBombed, yBombed);
	}
		
	/**
	 * Checks if both player have placed their ships and are ready
	 * to rumble!
	 * @return
	 */
	public boolean bothPlayersReady(){
		return playerReady && opponentStart;
	}
	
	public boolean isOpponentStart() {
		return opponentStart;
	}

	public void setOpponentStart() {
		opponentStart = true;
	}

	/**
	 * Appends a string to the status field in the GUI.
	 * @param s
	 */
	public void printToStatusField(String s)
	{
		gui.updateStatus(s);
	}
	
	public void printToChat(String s)
	{
		gui.updateChat(s);
	}
	
	public Grid getOpponentBoard(){
		return opponentBoard;
	}

	public Grid getPlayerBoard(){
		return playerBoard;
	}

	public void changeTurn(){
		myTurn = !myTurn; 
		if(!myTurn){
			opponent.goAhead();
		}
	}
	
	public boolean isMyTurn(){
		return myTurn;
	}	
	
	public void close()
	{
		if (opponent != null) {
			opponent.exit();
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
