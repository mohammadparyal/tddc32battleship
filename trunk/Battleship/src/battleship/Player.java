package battleship;

/**
 * Interface for opponent. Is implemented by either network or AI.
 * @author Lars Öberg and David Gunnarsson
 *
 */
public interface Player {
	void bomb(int x, int y);
	void goAhead();
	void setOpponentReady();
	void sendMessage(String s);
	void exit();
}
