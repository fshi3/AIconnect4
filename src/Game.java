import java.util.Scanner;

public class Game {
	Scanner scan = new Scanner(System.in);
	
	public Game() {
		gameBoard myGame;
		Player humanPlayer;
		boolean goingFirst;
		String input;
		String whichGame = "connect-4"; // default game is connect-4
		miniMaxAI AI;
		int depth = 6; // default depth is 6;
		
		System.out.println("Connect 4 by Forest Shi");
		
		// Prompt the user for the type of game and the depth
		while(true) {
			System.out.println("Choose your game:");
			System.out.println("1. 3x3x3 Connect-Three");
			System.out.println("2. 6x7x4 Connect-Four");
			System.out.println("Your Choice?: ");
			input = scan.nextLine();
			if (!input.equals("1") && !input.equals("2")) {
				System.out.println("Input is invalid! Please try again.");
			} else {
				if (input.equals("1")) {
					myGame = new gameBoard(3, 3, 3);
					whichGame = "connect-3"; // choose miniMaxAI
				} else {
					myGame = new gameBoard(7, 6, 4);
					while(true) {
						System.out.println("Choose your opponent:");
						System.out.println("1. Easy/Fast (depth cutoff - 5)");
						System.out.println("2. Hard (depth cutoff - 6)");
						System.out.println("Your Choice?: ");
						input = scan.nextLine();
						if (!input.equals("1") && !input.equals("2")) {
							System.out.println("Input is invalid! Please try again.");
						} else {
							if (input.equals("1")) {
								depth = 5;
							} else {
								depth = 6;
							}
							break;
						}	
					}
				}
				break;
			}
		}
		
		// prompt user to play RED or YELLOW
		while(true) {
			System.out.println("Would you like to be RED(1) or YELLOW(2)?");
			input = scan.nextLine();
			if (!input.equals("1") && !input.equals("2")) {
				System.out.println("Input is invalid! Please try again.");
			} else {
				break;
			}
		}
		
		if (input.equals("1")) {
			humanPlayer = new Player("X");
			goingFirst = true;
		} else {
			humanPlayer = new Player("O");
			goingFirst = false;
		}
		
		myGame.printBoard();
		if (!goingFirst) {
			// AI's move
			System.out.println("I'm thinking...");
			if (whichGame.equals("connect-3")) {
				AI = new miniMaxAI();
			} else {
				AI= new H_miniMaxAI(depth);
			}
			
			int bestMove = AI.miniMax_decision(myGame, humanPlayer);
			System.out.println("    " + AI.statesVisited + " states visited");
			System.out.println("    The best move is @ column " + bestMove);
			myGame.drop(bestMove);
			myGame.printBoard();
		}
		
		boolean gameover = false;
		// main game loop;
		while (!gameover) {
			int nCol = myGame.getWidth()-1;
			System.out.println("Your move [column 0-" + nCol + "]?: ");
			input = scan.nextLine();
			
			if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
					&& !input.equals("5") && !input.equals("6") && !input.equals("7") && !input.equals("8")
					&& !input.equals("9") && !input.equals("0")) {
				System.out.println("Input is invalid! Please try again.");
				continue;
			}
			
			int nextMove = Integer.parseInt(input);
			if (myGame.isMoveValid(nextMove)) {
				myGame.drop(nextMove);
				humanPlayer.movePlayed();
			} else {
				System.out.println("Move is invalid, try again?");
				continue;
			}
			myGame.printBoard();	
			
			//checks for a win for RED(X)
			if (myGame.checkDiagonal("X") || myGame.checkHorizontal("X") ||myGame.checkVertical("X")) {
				gameover = true;
				System.out.println("RED WINS!");
				break;
			}
			// checks for a win for YELLOW(O)
			if (myGame.checkDiagonal("O") || myGame.checkHorizontal("O") ||myGame.checkVertical("O")) {
				gameover = true;
				System.out.println("YELLOW WINS!");
				break;
			}
			// checks for tie
			if (myGame.actions().isEmpty()) {
				gameover = true;
				System.out.println("IT'S A TIE");
				break;
			}
			
			// AI's move
			System.out.println("I'm thinking...");
			if (whichGame.equals("connect-3")) {
				AI = new miniMaxAI();
			} else {
				AI = new H_miniMaxAI(depth);
			}
			
			int bestMove = AI.miniMax_decision(myGame, humanPlayer);
			System.out.println("    " + AI.statesVisited + " states visited");
			System.out.println("    best move: @ column " + bestMove);
			myGame.drop(bestMove);
			myGame.printBoard();
			
			//checks for a win for RED(X)
			if (myGame.checkDiagonal("X") || myGame.checkHorizontal("X") ||myGame.checkVertical("X")) {
				gameover = true;
				System.out.println("RED WINS!");
				break;
			}
			// checks for a win for YELLOW(O)
			if (myGame.checkDiagonal("O") || myGame.checkHorizontal("O") ||myGame.checkVertical("O")) {
				gameover = true;
				System.out.println("YELLOW WINS!");
				break;
			}
			// checks for tie
			if (myGame.actions().isEmpty()) {
				gameover = true;
				System.out.println("IT'S A TIE");
				break;
			}
		}
	}
		
	public static void main(String[] args) {
		
		Game game = new Game();		
	}
}
