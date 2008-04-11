package Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class Receiver implements Runnable {
	
	private DataInputStream in = null;
	private Game game;
	private Thread receiverThread;
	private Socket socket;
	
	public Receiver(Socket socket, Game g)
	{
		try
		{
			in = new DataInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		this.socket = socket;
		game = g;
		receiverThread = new Thread(this);
	}
	
	public void start()
	{
		if(!receiverThread.isAlive())
			receiverThread.start();
	}	
		
	public void run()
	{		
		String input;
		while(socket.isConnected()) //while(true)
		{
			try
			{
				while((input = in.readUTF()) != null)
				{
					//check to see what kind of string this is
					switch (input.charAt(0))
					{
					//the other player has finished placing his ships
					case 'a':
//						game.startGame();					//not yet implemented
						break;
					
					//Coordinates, string should be formatted as "c:x_coord,y_coord"	
					case 'c':
						int x_begin = input.indexOf(':') + 1;
						int y_begin = input.indexOf(',') + 1;
						
						
						int x_coord;
						int y_coord;
						
						x_coord = Integer.parseInt(input.substring(x_begin, y_begin - 1));
						y_coord = Integer.parseInt(input.substring(y_begin));
						
						game.print("x= " + x_coord + " y= " + y_coord);
						
//						game.bomb(x_coord, y_coord);		//not yet implemented						
						break;
						
					//Result of a bombing, either hit (h) or miss (m)
					case 'r':
//						game.getResult(input.charAt(2));	//not yet implemented
						game.print("Resultatet blev :" + input.substring(2));
						break;
						
					//some ship was sunk
					case 's':	
//						game.youSankMy(input.substring(2));	//not yet implemented
						break;
						
					//Message, formatted as "m:This is my message."
					case 'm':	
						game.print(input.substring(2));			//not yet implemented
						break;
						
					//other player left the game	
					case 'x':	
//						game.playerHasLeft();			//not yet implemented
						break;
						
					default:
						game.print("unrecognized network input");
						break;
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}			
		}
	}
}