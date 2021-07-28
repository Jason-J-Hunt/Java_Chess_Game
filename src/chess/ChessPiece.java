package chess;
/**
* ChessPiece interface to implement various functions
*for each chess piece
* @author  Jason Hunt, Timothy Kinsey
* @version 1.0
* @since   2019-03-01 
*/
public interface ChessPiece {
	/**
	   * This method is used to ensure legal move for piece
	   * @param srcrank This is the starting rank
	   * @param srcfile This is the starting file
	   * @param destrank This is the destination rank
	   * @param destfile This is the destination file
	   * @param chessboard instance of a chessboard
	   * @return true or false depending if move is legal or not
	   */
	public boolean validmove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard);// check if given move is valid
	/**
	   * This method is used to determine capture of a piece
	   * @param rankdest This is the destination rank of the piece
	   * @param filedest This is the destination file of the piece
	   * @param chessboard Takes an instance of chessboard
	   * @return true or false depending if a piece is at the given position or not
	   */
	public boolean check_capture(int rankdest, int filedest, Square[][] chessboard);//check if captured
	/**
	   * Returns the team a piece is on
	   * @return Letter of team B for Black, W for White
	   */
	public char ret_team();
	/**
	   * Returns the grid coordinate a piece is on
	   * @return A character for column and number for the row together ex. a1
	   */
	public String ret_pos();
	/**
	   * Returns the id for a piece
	   * @return The team name concatenated with a letter for the piece ex. Black Knight id = BK
	   */
	public String ret_id();
	
}
