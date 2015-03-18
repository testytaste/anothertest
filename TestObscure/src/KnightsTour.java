
public interface KnightsTour {
	//my changes
	public static final int SQUARE_UNVISITED = -1;
	
	/**
	 * prepare the board for the knight
	 */
	public void setUpBoard();
	
	/**
	 * start the knight's tour at a random position on an 8x8 board
	 */
	public void startTour();
	
	/**
	 * start the knight's tour at the position row, col
	 * @param row row of chess board to start at
	 * @param col column of chess board to start at
	 */
	public void startTour(int row, int col);
	
	/**
	 * step in the specified direction
	 * @param leftOrRight true if going left from original position, false otherwise
	 * @param UpOrDown true if going up from original position, false otherwise
	 * @param verticalFirst true if moving vertically first
	 */
	
	/**
	 * step in the direction d[0] (dy), d[1] (dx)
	 * @param delta the array of changes in position (dy, dx)
	 * @return the new position, or null if no move was made
	 */
	public int[] makeMove(int[] delta);
	
	/**
	 * step in a direction
	 * @return the new position, or null if no move was made
	 */
	public int[] makeMove();
	
	/**
	 * gets the row of the knight
	 * @return row of knight
	 */
	public int getCurrentRow();
	
	/**
	 * gets the column of the knight
	 * @return column of the knight
	 */
	public int getCurrentCol();
	
	/**
	 * gets the number of visited squares
	 * @return number of visited squares
	 */
	public int getNumVisited();
	
	/**
	 * gets the possible moves from the knight's current position
	 * @return possible moves from knight's current position in the form {(dy,dx), ...}
	 */
	public int[][] getPossibleMoves();
	
	/**
	 * Gets the board's state where each position is either unvisited or the step in which the knight visited that space
	 * @return board state
	 */
	public int[][] getBoardState();
	
	/**
	 * gets the board state in a single printable String, where each row is printed on a new line
	 * @return an array of Strings describing the board state (order in which knight visited each square, dash for Unvisited spaces)
	 */
	public String getBoardDisplay();

}
