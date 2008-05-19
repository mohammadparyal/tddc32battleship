package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

import battleship.*;
import ships.Ship;

/**
 * Makes a thread that runs constantly to check for incoming Strings
 * from the other player
 * @author Lars Öberg and David Gunnarsson
 *
 */
public class Receiver implements Runnable {

	private DataInputStream in = null;
	private Game game;
	private NetworkPlayer net;
	private Thread receiverThread;
	private boolean stopped;
	
	//constructor
	public Receiver(NetworkPlayer net, Socket socket, Game g)
	{
		this.net = net;
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
		if (!receiverThread.isAlive())
			receiverThread.start();
		game.printToStatusField("Connected");
		stopped = false;
	}

	public void stop()
	{
		stopped = true;
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run()
	{
		String input;
		while (!stopped) // while(true)
		{
			try
			{
				while (!stopped && (input = in.readUTF()) != null)
				{
					switch (input.charAt(0))
					{
					case 'a': // the other player has finished placing his
						// ships
						game.setOpponentStart();
						break;

					case 'c': //coordinates, "c:x,y"
						int x_begin = input.indexOf(':') + 1;
						int y_begin = input.indexOf(',') + 1;

						int x_coord = Integer.parseInt(input.substring(x_begin,
								y_begin - 1));
						int y_coord = Integer
								.parseInt(input.substring(y_begin));
						
						Ship sunkShip = game.getPlayerBoard().getShip(x_coord, y_coord);
						boolean hit = game.placeBomb(x_coord, y_coord);
						
						if(hit)
						{
							net.send("r:h");
							if(sunkShip.isSunk())
								net.send("s:"+sunkShip.getName());
						}
						else
							net.send("r:m");						
						game.changeTurn();
						
						break;

					case 'r': // Result of a bombing, either hit (h) or miss
						boolean hit2 = (input.charAt(2) == 'h');	
						game.setHit(hit2);
						if(hit2)
							game.printToStatusField("Hit");
						else
							game.printToStatusField("Miss");
						break;

					case 's': // some ship was sunk
						game.printToStatusField("You sunk my "+input.substring(2));
						break;

					case 'm': // Message, "m:This is my message."
						 game.printToChat(">> "+input.substring(2));
						break;
					case 'x': // other player left the game
						 game.printToStatusField("Other player left the game");
						 net.close();
						break;

					default:
						game.printToStatusField("unknown input: "+input);
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
