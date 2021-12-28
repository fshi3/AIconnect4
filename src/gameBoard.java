import java.util.ArrayList;

public class gameBoard {
	private int sizeX;
	private int sizeY;
	protected int toWin; // how many in a row to win
	
	protected String[][] board;			
	protected String nextToMove; // indicate the next player to move

	public gameBoard(int x, int y, int n) {
		// input the x and y dimensions of the board
		sizeX = x;
		sizeY = y;
		toWin = n;
		board = new String[sizeX][sizeY];
		
		for (int i = 0; i < sizeX; i++ ) {
			for (int j = 0; j < sizeY; j++) {
				board[i][j] = " ";
			}
		}

		// default is RED goes first
		nextToMove = "RED";
	}
	
	public int getWidth() {
		return sizeX;
	}
	
	public int getHeight() {
		return sizeY;
	}
		

	// check to see if a move is valid
	public boolean isMoveValid(int col) {
		boolean inputValid = false;
		// check to see if the input is a number between 0 and the number of columns-1
		for (int i = 0; i < sizeX; i++) {
			if (col == i) {
				inputValid = true;
				break;
			}
		}
		
		if (!inputValid) {
			return false;
		}
		
		// check for any empty slots in a column
		for (int i = 0; i < sizeY; i++) {
			if (board[col][i].equals(" ")) {
				return true;
			}
		}	
		return false;
	}
	

	// makes a move by dropping a piece on a column
	public void drop(int col) {
		int row = 0; // the row of the piece that is dropped
		
		// find the next empty slot in a column to stack on top
		for (int i = 0; i < sizeY; i++) {
			if (board[col][i].equals(" ")) {
				row = i;
				break;
			}
		}
		
		if (nextToMove == "RED") {
			board[col][row] = "X";
			nextToMove = "YELLOW";
		} else {
			board[col][row] = "O";
			nextToMove = "RED";
		}
	}
	
	
	// check for a win for vertically that matches a String indicator for "X" or "O"
	public boolean checkVertical(String piece) {
		int counter;
		
		for (int i = 0; i < sizeX; i++) {
			counter = 0;
			for (int j = 0; j < sizeY; j++) {
				if (board[i][j].equals(piece)) {
					counter ++;
				} else {
					counter = 0;
				}
				
				if (counter >= toWin) {
					return true;
				}
			}
		}	
		return false;
	}
	
	
	// check for a win for horizontally
	public boolean checkHorizontal(String piece) {
		int counter;
		
		for (int j = 0; j < sizeY; j++) {
			counter = 0;
			for (int i = 0; i < sizeX; i++) {
				if (board[i][j].equals(piece)) {
					counter ++;
				} else {
					counter = 0;
				}
				
				if (counter >= toWin) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	// check for a win for diagonally
	public boolean checkDiagonal(String piece) {
		// check diagonally from bottom right to top left starting at pieces on bottom edge
		for (int i = toWin-1; i < sizeX; i++) { // loop through each new diagonal line
			int counter = 0;
			int x = i;
			int y = 0;
			while (x >= 0 && y < sizeY) { // loop through each piece in one diagonal line
				if (board[x][y].equals(piece)) {
					counter ++;
				} else {
					counter = 0;
				}
				x--;
				y++;
				if (counter >= toWin) {
					return true;
				}
			}	
		}
		// check diagonally from bottom right to top left starting at pieces on right edge
		for (int j = 1; j <= sizeY - toWin; j++) {
			int counter = 0;
			int x = sizeX-1;
			int y = j;
			while (x >= 0 && y < sizeY) {
				if (board[x][y].equals(piece)) {
					counter++;
				} else {
					counter = 0;
				}
				x--;
				y++;
				if (counter >= toWin) {
					return true;
				}
			}
		}
		// check diagonally from bottom left to top right starting at pieces on left edge
		for (int j = sizeY-toWin; j >= 0; j--) {
			int counter = 0;
			int x = 0;
			int y = j;
			while (x < sizeX && y < sizeY) {
				if (board[x][y].equals(piece)) {
					counter++;
				} else {
					counter = 0;
				}
				x++;
				y++;
				if (counter >= toWin) {
					return true;
				}
			}
		}
		// check diagonally from bottom left to top right starting at pieces on bottom edge
		for (int i = 1; i <= sizeX - toWin; i++) {
			int counter = 0;
			int x = i;
			int y = 0;
			while (x < sizeX && y < sizeY) {
				if (board[x][y].equals(piece)) {
					counter++;
				} else {
					counter = 0;
				}
				x++;
				y++;
				if (counter >= toWin) {
					return true;
				}
			}	
		}
		return false;
	}
	
	
	// gives you all of the valid moves at a current state
	public ArrayList<Integer> actions() {
		ArrayList<Integer> actions = new ArrayList<Integer>();
		for (int i = 0; i < this.getWidth(); i++) {
			if (this.isMoveValid(i)) {
				actions.add(i);
			}
		}	
		return actions;
	}
	
	
	// duplicates a class of gameBoard
	public gameBoard clone() {
		gameBoard clone = new gameBoard(sizeX, sizeY, toWin);
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				clone.board[i][j] = this.board[i][j];
			}
		}
		clone.nextToMove = this.nextToMove;
		return clone;
	}
	
	
	public void printBoard() {
		String result = "\n" + "  ";

		for (int i = 0; i < sizeX; i++) {
			result += i + " ";
		}

		result += "\n";

		for (int i = sizeY-1; i >= 0; i--) {
			result += i + " ";
			for (int j = 0; j < sizeX; j++) {
				result += board[j][i] + " ";
			}
			result += i + "\n";
		}

		result += "  ";

		for (int i = 0; i < sizeX; i++) {
			result += i + " ";
		}
		
		result += "\n" + "Next to move: " + nextToMove + "\n";

		System.out.println(result);
	}

}
