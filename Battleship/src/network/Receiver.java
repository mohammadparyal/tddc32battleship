package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

import battleship.*;

public class Receiver implements Runnable {
	
	private DataInputStream in = null;
	private Game game;
	private Thread receiverThread;
	private boolean stopped;
	
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

		game = g;
		receiverThread = new Thread(this);
	}
	
	public void start()
	{
		if(!receiverThread.isAlive())
			receiverThread.start();
		
		stopped = false;
	}
	
	public void stop()
	{
		stopped = true;
	}
		
	public void run()
	{		
		String input;
		while(!stopped) //while(true)
		{
			try
			{
				while((input = in.readUTF()) != null)
				{
					switch (input.charAt(0))
					{		
					case 'a':	//the other player has finished placing his ships
						break;

					case 'c':	//koordinater, "c:x,y"
						int x_begin = input.indexOf(':') + 1;
						int y_begin = input.indexOf(',') + 1;

						int x_coord = Integer.parseInt(input.substring(x_begin,	y_begin - 1));
						int y_coord = Integer.parseInt(input.substring(y_begin));

						game.print("Opponent bombed: "+x_coord+y_coord);		//not yet implemented						
						break;

					case 'r':	//Result of a bombing, either hit (h) or miss (m)
//						game.updateResult(hit);
						break;


					case 's':	//some ship was sunk
						break;

					case 'm':	//Message, "m:This is my message."
//						game.print(input.substring(2));	
						break;

					case 'x':	//other player left the game
//						game.close();
						break;

					default:
//						game.print(">>" + input);
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
