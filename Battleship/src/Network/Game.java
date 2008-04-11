package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * This is the main class that starts the game.
 * @author David
 *
 */
public class Game {	

	private Network net;
	private GUI gui;
	
	
	public Game()
	{	
		net = new Network(this);
		
		gui = new GUI(this);
		gui.setVisible(true);
	}
	
	public DataInputStream getIn()
	{
		return net.getIn();
	}
	
	public DataOutputStream getOut()
	{
		return net.getOut();
	}
	
	public GUI getGui()
	{
		return gui;
	}
	
	public void send(String s)
	{
		net.send(s);
	}
	
	public void print(String s)
	{
		gui.append(s);
	}
	
	public void create(int port)
	{
		net.create(port);
	}
	
	public void connect(String address, int port)
	{
		net.connect(address, port);
		send("connected");
	}
	
	
	public static void main(String[] args)
	{
		Game game = new Game();
	}
	
}
