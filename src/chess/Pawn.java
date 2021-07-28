package chess;
/**
* Pawn implementation of a chess piece
* @author  Timothy Kinsey
* @version 1.0
* @since   2019-03-01 
*/
public class Pawn implements ChessPiece{
	/**
	 * char of team piece belongs to
	 */
	private char team;
	/**
	 * String of team + piece letter
	 */
	private String id;
	/**
	 * String of pieces current position
	 */
	private String pos;
	/**
	 * boolean if piece has moved yet
	 */
	public boolean hasmoved=false;
	/**
	 * boolean of valid enpassant
	 */
	public boolean valid_enpassant=false;
	/**
	 * Constructor to create pawn
	 * @param team letter of which team piece belongs to
	 * @param pos string of piece's current position
	 */
	public Pawn(char team, String pos) {
		this.team=team;
		this.pos=pos;
		this.id=""+team+"p";
	}
	/**{@inheritDoc}*/
	public boolean validmove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(this.team=='b') {
			if(check_capture(destrank, destfile, chessboard)==false && destrank>srcrank && (chessboard[destrank-1][destfile].piece_at==null ||(chessboard[destrank-1][destfile].piece_at!=null && chessboard[destrank-1][destfile].piece_at instanceof Pawn && ((Pawn)chessboard[destrank-1][destfile].piece_at).valid_enpassant==false))) { //no capture by pawn
				if((this.hasmoved==false || this.hasmoved==true) && srcrank+1==destrank && srcfile==destfile && chessboard[destrank][destfile].piece_at==null) { //player wishes to move the piece by one spot and no piece is on space
					return true;
				}
				else if(this.hasmoved==false && srcrank+2==destrank && srcfile==destfile && chessboard[destrank-1][destfile].piece_at==null && chessboard[destrank][destfile].piece_at==null) { //player wishes to move pawn by two spots
					return true;
				}
			}
			else if(check_capture(destrank, destfile, chessboard)==true || destrank>srcrank && (chessboard[destrank-1][destfile].piece_at!=null && chessboard[destrank-1][destfile].piece_at instanceof Pawn && ((Pawn)chessboard[destrank-1][destfile].piece_at).valid_enpassant==true)) { //player is trying to capture a piece with a pawn
				if(srcfile==0 && srcrank+1==destrank && srcfile+1==destfile) { //edge case, player is on left column of board
					return true;
				}
				else if(srcfile==7 && srcrank+1==destrank && srcfile-1==destfile) {
					return true;
				}
				else if(srcrank+1==destrank && srcfile+1==destfile || (srcrank+1==destrank && srcfile-1==destfile)) {
					return true;
				}
			}
		}
		else if(this.team=='w') {
			if(check_capture(destrank, destfile, chessboard)==false && destrank<srcrank && (chessboard[destrank+1][destfile].piece_at==null || (chessboard[destrank+1][destfile].piece_at!=null && chessboard[destrank+1][destfile].piece_at instanceof Pawn && ((Pawn)chessboard[destrank+1][destfile].piece_at).valid_enpassant==false))) { //no capture by pawn
				if((this.hasmoved==false || this.hasmoved==true) && srcrank-1==destrank && srcfile==destfile && chessboard[destrank][destfile].piece_at==null) { //player wishes to move the piece by one spot and no piece is on space
					return true;
				}
				else if(this.hasmoved==false && srcrank-2==destrank && srcfile==destfile && chessboard[destrank+1][destfile].piece_at==null && chessboard[destrank][destfile].piece_at==null) { //player wishes to move pawn by two spots
					return true;
				}
			}
			else if(check_capture(destrank, destfile, chessboard)==true || destrank<srcrank && (chessboard[destrank+1][destfile].piece_at!=null && chessboard[destrank+1][destfile].piece_at instanceof Pawn && ((Pawn)chessboard[destrank+1][destfile].piece_at).valid_enpassant==true)) { //player is trying to capture a piece with a pawn
				if(srcfile==0 && srcrank-1==destrank && srcfile+1==destfile) { //edge case, player is on left column of board
					return true;
				}
				else if(srcfile==7 && srcrank-1==destrank && srcfile-1==destfile) {
					return true;
				}
				else if(srcrank-1==destrank && srcfile+1==destfile || (srcrank-1==destrank && srcfile-1==destfile)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean validpromotion(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(this.team=='b' && destrank !=7) {
			return false;
		}
		else if(this.team=='w' && destrank !=0) {
			return false;
		}
		return true;
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
		return this.hasmoved;
	}
}
