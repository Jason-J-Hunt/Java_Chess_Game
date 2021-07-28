package chess;
/**
*Class to help print out the Chess Board
* @author  Timothy Kinsey
* @version 1.0
* @since   2019-03-01 
*/
public class Square {
	/**
	 * Color of team b Black w White
	 */
	public char color;
	/**
	 * Piece at current position
	 */
	public ChessPiece piece_at;
	/**
	 * Constructor for Square
	 * @param color team Color piece belongs to at this square
	 * @param piece the actual piece that belongs to this square
	 */
	public Square(char color, ChessPiece piece) {
		this.color=color;
		this.piece_at=piece;
	}
}
