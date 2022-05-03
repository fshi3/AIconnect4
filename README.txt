{\rtf1\ansi\ansicpg1252\cocoartf1561\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww10800\viewh9300\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0


\b abstract elements based on the formal model of the game.\

\b0 The states are stored in the class gameBoard.java in a 2D array.\
The all possible actions in a particular state is return by the method actions() in class gameBoard.java\
The result function is the method drop() in gameBoard.java. It takes in a int represent a column and plays that move and alters the state of the board.\
The cost is constant, and it is ignored in this project.\
Goal states are evaluated by the method isGoalState() in class miniMaxAI.java, which uses methods checkHorizontal(), checkVertical(), and checkDiagonal() in the class gameBoard.java to check for 4 in a row in any direction.\
The initial state is an empty board and is the default when class gameBoard.java is initialized.\
\

\b Specific implementations of those elements for m x n x k Connect-Four\

\b0 States are stored in class gameBoard.java, as the variable board[][].\
Applicable actions are return as integer values in an ArrayList by actions() method in class gameBoard.java.\
Result is the drop() method in the class gameBoard.java. it takes an action (an integer) and changes the state of the game.\
Goal states are evaluated by the method isGoalState() in class miniMaxAI.java, which uses methods checkHorizontal(), checkVertical(), and checkDiagonal() in the class gameBoard.java to check for 4 in a row in any direction. After each new move is made the three methods  checkHorizontal(), checkVertical(), and checkDiagonal() are run on the entire board to check for any 4 in a row, to return a win.\
The initial state is an empty board and is the default when class gameBoard.java is initialized. The variable board[][] in gameBoard.java is set very value in the 2D array to \'93 \'93 or  empty String.\
\

\b MINIMAX algorithm
\b0 \
minimax algorithm is in miniMaxAI.java. The correct move is returned by the methods miniMax_decision().\
\

\b How to run a 3x3x3 game?\

\b0 Run the file Game.java, in the main method, it will create an instance of class Game.java and it will prompt you to choose which game to play. choose 3x3x3 and it will play the 3x3x3 board and it will use miniMaxAI.java as the AI to play for that board size.\
\


\b0 Heuristic-miniMax algorithm is in H_miniMax.java. The correct move is returned by miniMax_decision().\


\b How to run a 6x7x4 game?\

\b0 Run the file Game.java, in the main method, it will create an instance of Game.java and it will prompt the user to choose which game. Choose the 6x7x4 and it will generate that board and use the H_miniMaxAI.java file to run the AI for that game.\
\


\b0 My game has two depth options - 5 moves or 6 moves ahead. The 5 moves plays reasonably well, but it will blunder in certain situations, however the first move is relatively fast, a second or two, and each successive move gets faster. The 6 moves option plays much better, and will beat me most of the times, however the first move can take up to 5 or 6 seconds, but each successive move gets faster.\
\

\b OTHER - HERE ARE ALL OF MY FILES AND WHAT THEY DO
\b0 \
My Connect-4 AI Game consists of five total classes: gameBoard.java, Game.java, miniMaxAI.java, H_miniMaxAI.java, and Player.java\
\

\b Game.java
\b0 \
contains the main game loop that takes turns asking the user for input for the next move and then playing a move from the AI. The class also contains the loop to prompt the user to choose the type of game to play (3 by 3 Connect-3 or 6 by 7 Connect-4) and the depth of the heuristic minimax AI when playing the connect-4 game.\
\

\b gameBoard.java\

\b0 This class represents the connect-4 board and is used to store the states of the game. It uses a 2-D array to store String values of either \'93 \'93 for empty square, \'93X\'94 for red, and \'93O\'94 for yellow. It also keeps track of how many it in a row is needed to win, and who\'92s turn is it to move next. drop() is the method that takes in a integer parameter and plays a move in that column. It also has methods that checks if s move in a certain column is valid\
\

\b miniMaxAI.java\

\b0 This class is the AI that uses mini-max to decide moves using the utility of terminal states. This is the class used to play the 3x3 connect-3.\
\

\b H_miniMaxAI.java\

\b0 The class is the AI that uses heuristic mini-max to decide moves with a limited depth using a heuristic function that evaluates cut-off states based on the heuristic that 3 in row is better than 2 in a row, 2 in a row is better 1 piece or no pieces at all, and the vice versa is bad.\
\

\b Player
\b0 \
This class represents the human player, stores information about which pieces (RED or YELLOW) they play, and how many moves they have made.\
\

\b \
}
