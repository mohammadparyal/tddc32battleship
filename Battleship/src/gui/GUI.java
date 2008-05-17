package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import ships.*;
import battleship.Game;

public class GUI extends JFrame {

	static final long serialVersionUID = 2L;

	private Game game;
	private JPanel statusPanel;
	private JPanel boardPanel;
	private JPanel buttonPanel;
	private JPanel chatPanel;
	private JPanel contents;

	private JButton shipButton;
	private JButton rotateButton;
	private JButton printButton;

	private JTextArea statusTextArea;
	private JScrollPane statusScroll;
	private JTextArea chatTextArea;
	private JScrollPane chatScroll;

	private JPanel myBoard;
	private JPanel opponentBoard;

	private Ship ship;

	public GUI(Game g, int cols, int rows)
	{
		game = g;
		this.setTitle("Battleship");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		myBoard = new JPanel();
		myBoard.setBorder(new TitledBorder("Your board"));
		myBoard.add(new Grid(this, cols, rows));
		opponentBoard = new JPanel();
		opponentBoard.setBorder(new TitledBorder("Opponent's board"));
		opponentBoard.add(new Grid(this, cols, rows));

		boardPanel = new JPanel(new GridLayout(1, 2));
		boardPanel.add(myBoard);
		boardPanel.add(opponentBoard);

		statusTextArea = new JTextArea();
		statusTextArea.setEditable(false);
		statusTextArea.setBackground(Color.lightGray);
		statusScroll = new JScrollPane(statusTextArea);
		statusScroll.setBorder(new TitledBorder("Messages:"));
		statusPanel = new JPanel(new BorderLayout());
		statusPanel.setPreferredSize(new Dimension(1, 120));
		statusPanel.add(statusScroll);

		chatTextArea = new JTextArea();
		chatScroll = new JScrollPane(chatTextArea);
		chatScroll.setBorder(new TitledBorder("Chat:"));
		chatPanel = new JPanel(new BorderLayout());
		chatPanel.setPreferredSize(new Dimension(1, 50));
		chatPanel.add(chatScroll);

		buttonPanel = new JPanel();
		buttonPanel.add(shipButton = new JButton("Placera skepp"));

		shipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ship = new Ship(5, "testbåt");
			}
		});

		buttonPanel.add(rotateButton = new JButton("rotera"));

		rotateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (ship != null)
					ship.rotate();
			}
		});

		buttonPanel.add(printButton = new JButton("Skriv ut spelplan"));
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				game.printBoard();
			}
		});

		contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.add(boardPanel);
		contents.add(buttonPanel);
		contents.add(statusPanel);
		contents.add(chatPanel);
		this.add(contents);

		pack();
		setVisible(true);
	}

	public void statusUpdate(String update)
	{
		statusTextArea.append(update + '\n');
	}

	public Game getGame()
	{
		return game;
	}

	public Ship getShip()
	{
		return ship;
	}

	public void emptyShip()
	{
		ship = null;
	}
}
