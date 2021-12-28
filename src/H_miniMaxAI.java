
public class H_miniMaxAI extends miniMaxAI {
	int depth;
	
	public H_miniMaxAI(int d) {
		depth = d;
	}
	
	public boolean cutoff_test(gameBoard state, Player p) {
		int balanceEvenOdd = 0; // used to balance the depth of check when AI goes first v.s. second
		int numOfMoves = 0;
		for (int i = 0; i < state.getWidth(); i++) {
			for (int j = 0; j < state.getHeight(); j++) {
				if(!state.board[i][j].equals(" ")) {
					numOfMoves++;
				}
			}
		}
		
		if (p.pieces.equals("X")) {
			balanceEvenOdd = 1;
		}
		// the depth limit, cutoff after that many moves
		if(numOfMoves > depth + p.getMovesPlayed()*2 - balanceEvenOdd) {
			return true;
		}
		return false;
	}
	
	
	@Override
	// returns a utility for a goal state
	public int Utility(gameBoard state, Player p) {
		if (state.checkDiagonal("X") || state.checkHorizontal("X") || state.checkVertical("X")) {
			if (p.pieces.equals("X")) {
				return -1000;
			} else {
				return 1000;
			}
		} else if (state.checkDiagonal("O") || state.checkHorizontal("O") || state.checkVertical("O")) {
			if (p.pieces.equals("X")) {
				return 1000;
			} else {
				return -1000;
			}
		} else {
			return 0;
		}
	}
	
	
	// check for how many there are of a certain number in a row Vertically
	public int checkVert(gameBoard state, String piece, int inARow) {
		int counter;
		int numOfInARows = 0; // number of how many 3s or 2s in a rows
		
		for (int i = 0; i < state.getWidth(); i++) {
			counter = 0;
			for (int j = 0; j < state.getHeight(); j++) {
				if (state.board[i][j].equals(piece)) {
					counter ++;
				} else {
					counter = 0;
				}
				
				if (counter >= inARow) {
					numOfInARows++;
					counter = 0;
				}
			}
		}	
		return numOfInARows;
	}
	
	
	// check for how many there are of a certain number in a row Horizontally
	public int checkHori(gameBoard state, String piece, int inARow) {
		int counter;
		int numOfInARows = 0;
		
		for (int j = 0; j < state.getHeight(); j++) {
			counter = 0;
			for (int i = 0; i < state.getWidth(); i++) {
				if (state.board[i][j].equals(piece)) {
					counter ++;
				} else {
					counter = 0;
				}
				
				if (counter >= inARow) {
					numOfInARows++;
					counter = 0;
				}
			}
		}
		return numOfInARows;
	}
	
	
	// check for how many there are of a certain number in a row diagonally
	public int checkDiag(gameBoard state, String piece, int inARow) {
		int numOfInARows = 0;
		
		// check diagonally from bottom right to top left starting at pieces on bottom edge
		for (int i = inARow-1; i < state.getWidth(); i++) { // loop through each new diagonal line
			int counter = 0;
			int x = i;
			int y = 0;
			while (x >= 0 && y < state.getHeight()) { // loop through each piece in one diagonal line
				if (state.board[x][y].equals(piece)) {
					counter ++;
				} else {
					counter = 0;
				}
				x--;
				y++;
				if (counter >= inARow) {
					numOfInARows++;
					counter = 0;
				}
			}	
		}
		// check diagonally from bottom right to top left starting at pieces on right edge
		for (int j = 1; j <= state.getHeight() - inARow; j++) {
			int counter = 0;
			int x = state.getWidth()-1;
			int y = j;
			while (x >= 0 && y < state.getHeight()) {
				if (state.board[x][y].equals(piece)) {
					counter++;
				} else {
					counter = 0;
				}
				x--;
				y++;
				if (counter >= inARow) {
					numOfInARows++;
					counter = 0;
				}
			}
		}
		// check diagonally from bottom left to top right starting at pieces on left edge
		for (int j = state.getHeight()-inARow; j >= 0; j--) {
			int counter = 0;
			int x = 0;
			int y = j;
			while (x < state.getWidth() && y < state.getHeight()) {
				if (state.board[x][y].equals(piece)) {
					counter++;
				} else {
					counter = 0;
				}
				x++;
				y++;
				if (counter >= inARow) {
					numOfInARows++;
					counter = 0;
				}
			}
		}
		// check diagonally from bottom left to top right starting at pieces on bottom edge
		for (int i = 1; i <= state.getWidth() - inARow; i++) {
			int counter = 0;
			int x = i;
			int y = 0;
			while (x < state.getWidth() && y < state.getHeight()) {
				if (state.board[x][y].equals(piece)) {
					counter++;
				} else {
					counter = 0;
				}
				x++;
				y++;
				if (counter >= inARow) {
					numOfInARows++;
					counter = 0;
				}
			}	
		}
		return numOfInARows;
	}
	
	
	
	// returns the utility of a non-terminal state using heuristics
	public int heuristicVal(gameBoard state, Player p) {
		int H_Val = 0;
		
		// the H_Val if AI is playing "O"
		H_Val += checkVert(state, "O", 3)*10;
		H_Val += checkHori(state, "O", 3)*10;
		H_Val += checkDiag(state, "O", 3)*10;
			
		H_Val += checkVert(state, "O", 2)*5;
		H_Val += checkHori(state, "O", 2)*5;
		H_Val += checkDiag(state, "O", 2)*5;
			
		H_Val -= checkVert(state, "X", 3)*10;
		H_Val -= checkHori(state, "X", 3)*10;
		H_Val -= checkDiag(state, "X", 3)*10;
			
		H_Val -= checkVert(state, "X", 2)*5;
		H_Val -= checkHori(state, "X", 2)*5;
		H_Val -= checkDiag(state, "X", 2)*5;
		
		// if AI is playing "X", player is playing "O"
		if (p.pieces.equals("O")) {
			H_Val = - H_Val;
		}	
		return H_Val;
	}
	
	
	@Override
	// returns the best move represented by an integer of the column
	public int miniMax_decision(gameBoard state, Player p) {
		int maxUtil = -1000000;
		int bestMove = 0;
		for (int a : state.actions()) {
			statesVisited++;
			gameBoard temp = state.clone();
			temp.drop(a);
			int currentUtil = min_value(temp, p);
			if ( currentUtil > maxUtil) {
				maxUtil = currentUtil;
				bestMove = a;
			}	
		}
		return bestMove;
	}
	
	
	@Override
	// returns a utility value
	public int min_value(gameBoard state, Player p) {
		int minVal = 1000000;
		
		if (isGoalState(state)) {
			return Utility(state, p);
		}
		if (cutoff_test(state, p)) {
			return heuristicVal(state, p);
		}
		for (int a: state.actions()) {
			statesVisited++;
			gameBoard temp = state.clone();
			temp.drop(a);
			int currentVal = max_value(temp, p);
			if (currentVal < minVal) {
				minVal = currentVal;
			}
		}
		return minVal;
	}
	
	
	@Override
	// returns a utility value
	public int max_value(gameBoard state, Player p) {
		int maxVal = -1000000;
		
		if (isGoalState(state)) {
			return Utility(state, p);
		}
		if (cutoff_test(state, p)) {
			return heuristicVal(state, p);
		}
		for (int a: state.actions()) {
			statesVisited++;
			gameBoard temp = state.clone();
			temp.drop(a);
			int currentVal = min_value(temp, p);
			if (currentVal > maxVal) {
				maxVal = currentVal;
			}	
		}
		return maxVal;
	}
}
