package chess;
/**
* Class to check valid moves for Queen, Rook, Knight
* Methods for checking on diagonals, Vertical, and Horizontal Moves
* @author  Jason Hunt
* @version 1.0
* @since   2019-03-01 
*/
public class Bishop extends Hor_Ver_Diag implements ChessPiece{
	/** 
	 * Team associated with piece
	 */  
	private char team;
	/** 
	 * Team + Letter associated with piece.
	 */  
	private String id;
	/** 
	 * Position of piece.
	 */  
	private String pos;
	/** 
	 * Boolean to track if piece has moved or not.
	 */  
	public boolean hasmoved=false;
	/** 
	 * Constructor for Bishop.
	 * @param team a char indicating team piece belongs to
	 * @param pos string of piece's current position
	 */  
	public Bishop(char team, String pos) {
		this.team=team;
		this.pos=pos;
		this.id=""+team+"B";
	}
	/**{@inheritDoc}*/
	public boolean validmove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(check_capture(destrank, destfile, chessboard)==true || chessboard[destrank][destfile].piece_at==null) {
			return validDiagonalMove(srcrank, srcfile, destrank, destfile, chessboard); //called from Super class Hor_Ver_Diag.java
		}
		return false;
	}
	
	public boolean check_capture(int rankdest, int filedest, Square[][] chessboard) {
		if(chessboard[rankdest][filedest].piece_at==null) {
			return false;
		}
		else if(chessboard[rankdest][filedest].piece_at.ret_team()!=this.team) {
			return true;
		}
		return false;
	}
	
	public char ret_team() {
		return team;
		
	}
	
	public String ret_pos() {
		return pos;
	}
	
	public String ret_id() {
		return id;
	}

}
