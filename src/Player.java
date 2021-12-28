// this class is used to store which color pieces the player is using
public class Player {
	String pieces;
	private int movesPlayed;
	
	public Player(String s) {
		pieces = s;
		movesPlayed = 0;
	}
	
	public void movePlayed() {
		movesPlayed++;
	}
	
	public int getMovesPlayed() {
		return movesPlayed;
	}
	

}
