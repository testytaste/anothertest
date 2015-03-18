

import java.util.ArrayList;

public class KnightsTour3 implements KnightsTour {

	private int row, col;
	private int[][] boardState = new int[8][8];
	
	/**
	 * Sets up the board and does nothing else
	 */
	public KnightsTour3(){
		setUpBoard();
	}

	@Override
	public void setUpBoard() {
		for (int i = 0; i < boardState.length; i++)
			for (int j = 0; j < boardState[i].length; j++)
				boardState[i][j] = KnightsTour.SQUARE_UNVISITED;
	}

	@Override
	public void startTour() {
		int row = (int)(Math.random()*8);
		int col = (int)(Math.random()*8);
		startTour(row, col);
	}

	@Override
	public void startTour(int row, int col) {
		this.row = row;
		this.col = col;
		markBoard();
	}

	@Override
	public int[] makeMove(int[] delta) {
		if (delta == null) return null;
		if (getBoardState()[row+delta[0]][col+delta[1]] != KnightsTour.SQUARE_UNVISITED)
			return null;
		col += delta[1];
		row += delta[0];
		markBoard();
		return new int[]{delta[0]+row, delta[1]+col};
	}

	@Override
	public int[] makeMove() {
		int[] move = getBestMove();
		makeMove(move);
		return move;
	}

	@Override
	public int getCurrentRow() {
		return row;
	}

	@Override
	public int getCurrentCol() {
		return col;
	}

	@Override
	public int getNumVisited() {
		int numVisited = 0;
		for (int[] row : boardState)
			for (int val : row){	// parse through each position in board
				if (val != KnightsTour.SQUARE_UNVISITED)	// if square has been visited...
					numVisited++;	// increment the sum
			}
		return numVisited;
	}

	@Override
	public int[][] getPossibleMoves() {
		return getPossibleMoves(this.row, this.col);
	}
	
	/**
	 * Get the possible number of moves from the position specified
	 * @param row row where the knight is located
	 * @param col column where the knight is located
	 * @return possible moves from knight's current position in the form {(dy,dx), ...}
	 */
	public int[][] getPossibleMoves(int row, int col) {
		ArrayList<int[]> arrList = new ArrayList<int[]>();
		for (int i = 0; i <= 7; i++){
			/*
			 * Turns the iterator to a three digit binary number as follows
			 * 1st digit : i/4					significance: left or right
			 * 2nd digit : i/2 % 2				significance: up or down
			 * 3rd digit : i%2 || (i+1) % 2		significance: horizontally 2 spaces?
			 * Based on these three digits and their significances it generates a move
			 */
			int dy = ((i/4) * 2 - 1) * ((i%2) + 1);
			int dx = ((i/2 % 2) * 2 - 1) * (((i+1) % 2) + 1);
			if ((col + dx < 8 && row + dy < 8) && (col + dx > -1 && row + dy > -1))	// if move doesn't go off the board
				if (getBoardState()[row+dy][col+dx] == KnightsTour.SQUARE_UNVISITED)
					arrList.add(new int[]{dy, dx});
		}
		return arrList.toArray(new int[arrList.size()][]);
	}

	@Override
	public int[][] getBoardState() {
		return boardState;
	}

	@Override
	public String getBoardDisplay() {
		String s = "";
		for (int[] row : boardState){
			for (int i : row){
				// Make a row of values separated by tabs
				if (i != KnightsTour.SQUARE_UNVISITED)
					s += i + "\t";
				else
					s += "-\t";
			}
			// Insert a newline after every row
			s += "\n";
		}
		return s;
	}
	
	public int[] getBestMove(){
		int[][] moves = getPossibleMoves();
		int[] bestMove = null;
		if (moves.length == 0) return null;
		int min = 9;
		int bestFutureMin = 9;
		for (int[] move : moves){
			int numPossible = getPossibleMoves(move[0]+row, move[1]+col).length;
			
			int futureMin = 9;
			for (int[] futureMoves : getPossibleMoves(move[0]+row, move[1]+col)){
				int futurePossible = getPossibleMoves(move[0]+row+futureMoves[0], move[1]+col+futureMoves[1]).length;
				if (futurePossible < futureMin)
					futureMin = futurePossible;
			}
			
			if (numPossible < min){
				min = numPossible;
				bestMove = move;
				bestFutureMin = futureMin;
			}
			else if (numPossible == min){
				if (futureMin < bestFutureMin){
					bestMove = move;
					bestFutureMin = futureMin;
				}
			}
		}
		return bestMove;
	}
	
	public void markBoard() {
		boardState[row][col] = getNumVisited();
	}

}
