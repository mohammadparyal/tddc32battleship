package Network;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GUI extends JFrame {
	static final long serialVersionUID = 1L;
	
	private JTextArea textarea;
	private JTextField textfield;
	
	private JPanel panel;
	private JPanel buttonpanel;
	private JButton create;
	private JButton connect;
	private JTextField addressfield;
	
	private Game game;
	
	public GUI(Game g)
	{
		game = g;
		
		textarea = new JTextArea();
		textfield = new JTextField();
		textfield.addActionListener(new keyListener());
		
		panel = new JPanel(new BorderLayout());
		panel.add(textarea, BorderLayout.CENTER);
		panel.add(textfield, BorderLayout.SOUTH);
		
		buttonpanel = new JPanel();
		create = new JButton("Create");
		create.addActionListener(new CreateListener());
		connect = new JButton("Connect");
		connect.addActionListener(new ConnectListener());
		addressfield = new JTextField("127.0.0.1");
		
		buttonpanel.add(addressfield);
		buttonpanel.add(create);
		buttonpanel.add(connect);
		
		panel.add(buttonpanel, BorderLayout.NORTH);
		
		add(panel);
		
		setTitle("Testwindow");
		setPreferredSize(new Dimension(300,300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();		
	}
		
	public void append(String s)
	{
		textarea.append(s + '\n');
	}
	
	class keyListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev)
		{
			try
			{
				game.send(textfield.getText());
				textfield.selectAll();
				textfield.replaceSelection("");
			}
			catch (Exception e)
			{
				e.printStackTrace();			
			}			
		}		
	}

	class CreateListener implements ActionListener {

		public void actionPerformed(ActionEvent ev)
		{
			try
			{
				game.create(31415);
			}
			catch (Exception e)
			{
				e.printStackTrace();			
			}
		}
	}
	
	class ConnectListener implements ActionListener {

		public void actionPerformed(ActionEvent ev)
		{
			try
			{
				String address = addressfield.getText();
				game.connect(address, 31415);				
			}
			catch (Exception e)
			{
				e.printStackTrace();			
			}
		}
	}
}
