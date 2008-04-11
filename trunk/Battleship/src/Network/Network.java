package Network;
/**
 * Network class
 */
import java.net.*;
import java.io.*;

public class Network {

	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	private DataInputStream in;
	private DataOutputStream out;
	
	private Game game;
	private Receiver receiver;
	
	/**
	 * Constructor
	 */
	public Network(Game g)
	{
		game = g;
	}
		
	/**
	 * Makes a ServerSocket listening to <code>port</code> and after accepting a connection
	 * makes a new Socket.
	 * @param port int of what port to listen to.
	 */
	public void create(int port)
	{
		try	
		{
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			receiver = new Receiver(socket, game);
			receiver.start();
			
		}
		catch (Exception e)	{System.err.println("unable to create: " + e.getMessage());}
	}
	
	/**
	 * Connects to a server at <code>address</code>, <code>port</code> and makes
	 * a new PrintWriter and a BufferedReader
	 * @param address String. 
	 * @param port int.
	 */
	public void connect(String address, int port)
	{		
		try	
		{
			socket = new Socket(address, port);
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			receiver = new Receiver(socket, game);
			receiver.start();
		}
		catch (Exception e)	{System.err.println("unable to connect: " + e.getMessage());}
	}
	
	/**
	 * Puts a sting in the output stream, connected to the remote socket
	 * @param s String to send.
	 */
	public void send(String s)
	{
		try
		{
			out.writeUTF(s);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a line from the input stream from the socket.
	 * @return the read String.
	 */
	public String read()
	{
		try
		{
			return in.readUTF();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void close()
	{
		try
		{
			serverSocket.close();
			socket.close();
			in.close();
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	public DataInputStream getIn()
	{
		return in;
	}

	public DataOutputStream getOut()
	{
		return out;
	}	
}
