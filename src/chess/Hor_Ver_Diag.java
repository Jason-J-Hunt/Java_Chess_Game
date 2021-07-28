package chess;
/**
* Class to check valid moves for Queen, Rook, Knight
* Methods for checking on diagonals, Vertical, and Horizontal Moves
* @author  Jason Hunt
* @version 1.0
* @since   2019-03-01 
*/
public class Hor_Ver_Diag {
	/**
	   * This method is used to ensure legal move for piece horizontally
	   * @param srcrank start rank of piece
	   * @param srcfile start file of piece
	   * @param destrank destination rank of piece
	   * @param destfile destination file of piece
	   * @param chessboard instance of chess board
	   * @return true or false depending if move is legal or not
	   */
	public boolean validHorizontalMove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(srcrank != destrank) {//if move not in same row invalid move
			return false;
		}
		else {
			if(destfile - srcfile < 0) {//piece is moving left
				for(int i = srcfile - 1; i>destfile; i--) {//subtract 1 so it starts at space left of piece
					if(chessboard[srcrank][i].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
			else if(destfile - srcfile > 0) {//piece is moving right
				for(int i = srcfile + 1; i<destfile; i++) {//add one so it starts space to right of piece
					if(chessboard[srcrank][i].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
		}
		return true;
	}
	/**
	   * This method is used to ensure legal move for piece vertically
	   * @param srcrank start rank of piece
	   * @param srcfile start file of piece
	   * @param destrank destination rank of piece
	   * @param destfile destination file of piece
	   * @param chessboard instance of chess board
	   * @return true or false depending if move is legal or not
	   */
	public boolean validVerticalMove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		if(srcfile != destfile) {//if not in same column return false not valid vertical move
			return false;
		}
		else {
			if(destrank - srcrank < 0) {//piece is moving up
				for(int i = srcrank - 1; i>destrank; i--) {//subtract 1 so it starts at space one up
					if(chessboard[i][srcfile].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
			else if(destrank - srcrank > 0) {//piece is moving down
				for(int i = srcrank + 1; i<destrank; i++) {//add one so it starts space one down
					if(chessboard[i][srcfile].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
		}
		return true;
	}
	/**
	   * This method is used to ensure legal move for piece diagonally
	   * @param srcrank start rank of piece
	   * @param srcfile start file of piece
	   * @param destrank destination rank of piece
	   * @param destfile destination file of piece
	   * @param chessboard instance of chess board
	   * @return true or false depending if move is legal or not
	   */
	public boolean validDiagonalMove(int srcrank, int srcfile, int destrank, int destfile, Square[][] chessboard) {
		int rankchange, filechange, changingfile;
		changingfile=srcfile;
		rankchange = destrank - srcrank;
		filechange = destfile - srcfile;
		if(Math.abs(rankchange) != Math.abs(filechange)) {//if changes arent equal its not a diagonal move
			return false;
		}
		else {
			if(rankchange < 0 && filechange < 0) {//piece is moving up to the left
				for(int i = srcrank-1; i > destrank; i--) {//check that path to destination is clear
					changingfile=changingfile-1;
					if(chessboard[i][changingfile].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
			else if(rankchange < 0 && filechange > 0) {//piece is moving up and to the right
				for(int i = srcrank-1; i > destrank; i--) {//check that path to destination is clear
					changingfile=changingfile+1;
					if(chessboard[i][changingfile].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
			else if(rankchange > 0 && filechange > 0){//piece is moving down and to the right
				for(int i = srcrank+1; i < destrank; i++) {//check that path to destination is clear
					changingfile=changingfile+1;
					if(chessboard[i][changingfile].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
			else if(rankchange > 0 && filechange < 0){//piece is down up and to the left
				for(int i = srcrank+1; i < destrank; i++) {//check that path to destination is clear
					changingfile=changingfile-1;
					if(chessboard[i][changingfile].piece_at!=null) {
						return false;//if piece is blocking path not a valid move
					}
				}
			}
		}
		return true;// if it succeeds path is valid
	}
}
