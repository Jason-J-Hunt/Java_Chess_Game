package chess;

public class Knight implements ChessPiece{
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
	 * Constructor for knight.
	 * @param team a char indicating team piece belongs to
	 * @param pos string of piece's current position
	 */  
	public Knight(char team, String pos) {
		this.team=team;
		this.pos=pos;
		this.id=""+team+"N";
	}
	/**{@inheritDoc}*/
	public boolean validmove(int srsrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		//System.out.println("Called knight valid move");
		if(check_capture(destrank, destfile, chessboard)==true || chessboard[destrank][destfile].piece_at==null) { //destination is an empty space or theres an opponents piece there
			if(srcfile +1 == destfile && srsrank + 2 == destrank ) {//up 2 to the right 1
				return true;
			}
			else if(srcfile - 1 == destfile && srsrank + 2 == destrank ) {//up 2 to the left 1
				return true;
			}
			else if(srcfile + 2 == destfile && srsrank + 1 == destrank ) {//up 1 to the right 2
				return true;
			}
			else if(srcfile - 2 == destfile && srsrank + 1 == destrank ) {//up 1 to the left 2
				return true;
			}
			else if(srcfile + 1 == destfile && srsrank - 2 == destrank ) {//down 2 to the right 1
				return true;
			}
			else if(srcfile - 1 == destfile && srsrank - 2 == destrank ) {//down 2 to the left 1
				return true;
			}
			else if(srcfile - 2 == destfile && srsrank - 1 == destrank ) {//down 1 to the left 2
				return true;
			}
			else if(srcfile + 2 == destfile && srsrank - 1 == destrank ) {//down 1 to the right 2
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
