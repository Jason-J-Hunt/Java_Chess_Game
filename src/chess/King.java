package chess;
/**
* King implementation of a chess piece
* @author  Jason Hunt
* @version 1.0
* @since   2019-03-01 
*/
public class King extends Hor_Ver_Diag implements ChessPiece{
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
	 * constructor to create king
	 * @param team letter of team king belongs to
	 * @param pos String of kings current position
	 */
	public King(char team, String pos) {
		this.team=team;
		this.pos=pos;
		this.id=""+team+"K";
	}
	/**{@inheritDoc}*/
	public boolean validmove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(!this.hasmoved && (srcrank == destrank)) {//check if king is castling
			if(check_castling(destrank, destfile, chessboard)) {
				return true;
			}
		}
		else if((destrank - srcrank)>1 || (destfile - srcfile) > 1) { //player is trying to move piece more then one space
				return false; //only returns false in case that it has been move because of castling
		}
		else {//use Hor_Ver_Diag methods to check if valid move
			if(check_capture(destrank, destfile, chessboard)==true || chessboard[destrank][destfile].piece_at==null) {
				if(validHorizontalMove(srcrank, srcfile, destrank, destfile, chessboard)) {//check if valid horizontal move
					return true;
				}
				else if(validVerticalMove(srcrank, srcfile, destrank, destfile, chessboard)) {//check if valid vertical move
					return true;
				}
				else if(validDiagonalMove(srcrank, srcfile, destrank, destfile, chessboard)) {//check if valid vertical move
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean check_castling(int rankdest, int filedest, Square[][] chessboard) {//check for check and check mate before calling
		if(filedest!=2 && filedest!=6) {  //king is not trying to go to castle position
			return false;
		}
		
		if(this.team=='w' && filedest==2 && !(chessboard[7][0].piece_at instanceof Rook) || //check to make sure there is a rook at the ends of the board based on what color of king is moving
				this.team=='w' && filedest==6 && !(chessboard[7][7].piece_at instanceof Rook) ||
				this.team=='b' && filedest==2 && !(chessboard[0][0].piece_at instanceof Rook) || 
				this.team=='b' && filedest==6 && !(chessboard[0][7].piece_at instanceof Rook)) {
			return false;
		}
		
		if(chessboard[rankdest][filedest].piece_at != null) {//make sure king is going to an empty space
			return false;
		}
		
		if(filedest - 4 > 0) { //right rook
			for(int i = 5; i<=filedest; i++) {
				if(chessboard[rankdest][i].piece_at!=null) {
					return false; //piece in the way not valid castle
				}
			}
		}
		else if(filedest - 4 < 0) {//left rook
			for(int i = 3; i>=filedest; i--) {
				if(chessboard[rankdest][i].piece_at!=null) {
					return false; //piece in the way not valid castle
				}
			}
		}
			
		//no pieces between rook and king check to ensure rook hasnt moved if it hasnt its valid castle
		if(rankdest==7 && filedest==2 && chessboard[7][0].piece_at!=null) { //white trying to castle to the left
			Rook r = (Rook)chessboard[7][0].piece_at;
			if(!r.ret_hasmoved()) {
				return true;
			}
		}
		if(rankdest==7 && filedest==6 && chessboard[7][7].piece_at!=null) { //white trying to castle to the right
			Rook r = (Rook)chessboard[7][7].piece_at;
			if(!r.ret_hasmoved()) {
				return true;
			}
		}
		if(rankdest==0 && filedest==2 && chessboard[0][0].piece_at!=null) { //black trying to castle to the left
			Rook r = (Rook)chessboard[0][0].piece_at;
			if(!r.ret_hasmoved()) {
				return true;
			}
		}
		if(rankdest==0 && filedest==6 && chessboard[0][7].piece_at!=null) { //black trying to castle to the right
			Rook r = (Rook)chessboard[0][7].piece_at;
			if(!r.ret_hasmoved()) {
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

}
