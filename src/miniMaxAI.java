import java.util.ArrayList;

public class miniMaxAI {
	int statesVisited = 0;
	
	public miniMaxAI() {
		
	}
	
	public boolean isGoalState(gameBoard state) {
		if (state.checkDiagonal("X") || state.checkHorizontal("X") || state.checkVertical("X")
				|| state.checkDiagonal("O") || state.checkHorizontal("O") || state.checkVertical("O")) {
			// a win for either player is a goal state
			return true;
		} else if (state.actions().isEmpty()) {
			// if there are no valid moves, it is a tie, therefore a goal state
			return true; 
		}
		return false;
	}
	
	
	// returns a utility for a goal state
	public int Utility(gameBoard state, Player p) {
		if (state.checkDiagonal("X") || state.checkHorizontal("X") || state.checkVertical("X")) {
			if (p.pieces.equals("X")) {
				return -1;
			} else {
				return 1;
			}
		} else if (state.checkDiagonal("O") || state.checkHorizontal("O") || state.checkVertical("O")) {
			if (p.pieces.equals("X")) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return 0;
		}
	}
	
	
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
	
	
	// returns a utility value
	public int min_value(gameBoard state, Player p) {
		int minVal = 1000000;
		
		if (isGoalState(state)) {
			return Utility(state, p);
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
	
	
	// returns a utility value
	public int max_value(gameBoard state, Player p) {
		int maxVal = -1000000;
		
		if (isGoalState(state)) {
			return Utility(state, p);	
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
