package battleship;
import ships.*;
import ai.*;

public class BattleTest {
	
//	private Board board;
//	private Board board2;
	
	private AI ai;
	
	public BattleTest()
	{
//		board = new Board(7,7);
//		board2 = new Board(7,7);
//		
//		board.placeShip(new Ship(6,"testbåt"), 0, 0, false);		
//		board.placeShip(new Ship(4,"annanbåt"), 4, 2, true);
//		board.placeBomb(2, 2);
//		board.placeBomb(3, 6);
//		board.placeBomb(6, 6);
//		board.placeBomb(4, 1);
//
//		board2.placeShip(new Ship(3,"testbåt"), 2, 5, false);
//		board2.placeShip(new Ship(4,"patrullbåt"), 2, 1, true);
//		board2.placeBomb(2, 2);
//		board2.placeBomb(3, 6);
//		board2.placeBomb(6, 6);
//		board2.placeBomb(4, 1);
//		System.out.println(board.toString());
//		System.out.println("\n"+board2);
		
//		for(int i=0; i<30; i++)
		{
			ai = new AI();		
			ai.printBoard();
			ai.placeBoats();
		}
		
		for(int x=0; x<10; x+=3)
			for(int y=0; y<10; y+=2)
				ai.bomb(x, y);
		
		ai.printBoard();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BattleTest battle = new BattleTest();

	}

}
