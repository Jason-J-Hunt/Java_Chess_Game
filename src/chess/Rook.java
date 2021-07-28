package chess;

public class Rook extends Hor_Ver_Diag implements  ChessPiece{
	/**
	 * char of team piece belongs to
	 */
	private char team;
	/**
	 * Team + Letter of  piece to generate id
	 */
	private String id;
	/**
	 * current position of piece
	 */
	private String pos;
	/**
	 * boolean if piece has moved
	 */
	public boolean hasmoved=false;
	/**
	 * constructor to create  rook
	 * @param team letter of team rook belongs to
	 * @param pos String of rook current position
	 */
	public Rook(char team, String pos) {
		this.team=team;
		this.pos=pos;
		this.id=""+team+"R";
	}
	/**{@inheritDoc}*/
	public boolean validmove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(check_capture(destrank, destfile, chessboard)==true || chessboard[destrank][destfile].piece_at==null) {
			if(validHorizontalMove(srcrank, srcfile, destrank, destfile, chessboard)) {//check if valid horizontal move
				return true;
			}
			else if(validVerticalMove(srcrank, srcfile, destrank, destfile, chessboard)) {//check if valid vertical move
				return true;
			}
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
	
	public boolean ret_hasmoved() {
		return hasmoved;
	}
}
