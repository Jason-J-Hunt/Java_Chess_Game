package chess;

import java.util.ArrayList;
/**
* Board class file for creating a new board and printing after every move
* @author  Jason Hunt, Timothy Kinsey
* @version 1.0
* @since   2019-03-01 
*/
public class Board {
	/**
	 * instance of a chessboard array to put the pieces into and track movements
	 */
	Square chessboard[][];
	/**
	 * Not used
	 */
	ArrayList<ChessPiece> black_pieces=new ArrayList<ChessPiece>();
	/**
	 * not used
	 */
	ArrayList<ChessPiece> white_pieces=new ArrayList<ChessPiece>();
	/**
	 * boolean for if the game is over or not
	 */
	private boolean game_over=false;
	
	/**
	   * Constructor takes no arguments and creates a new square instance
	   * and populates the boards with all the pieces
	   */
	public Board() {
		chessboard=new Square[8][8];
		char color='w';
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				chessboard[i][j]=new Square(color, null);
				if(j!=7) {
					if(color=='w') {
						color='b';
					}
					else {
						color='w';
					}
				}
			}
		}
		
		chessboard[0][0].piece_at=new Rook('b', "a8");
		chessboard[0][1].piece_at=new Knight('b', "b8");
		chessboard[0][2].piece_at=new Bishop('b', "c8");
		chessboard[0][3].piece_at=new Queen('b', "d8");
		chessboard[0][4].piece_at=new King('b', "e8");
		chessboard[0][5].piece_at=new Bishop('b', "f8");
		chessboard[0][6].piece_at=new Knight('b', "g8");
		chessboard[0][7].piece_at=new Rook('b', "h8");
		
		chessboard[1][0].piece_at=new Pawn('b', "a7");
		chessboard[1][1].piece_at=new Pawn('b', "b7");
		chessboard[1][2].piece_at=new Pawn('b', "c7");
		chessboard[1][3].piece_at=new Pawn('b', "d7");
		chessboard[1][4].piece_at=new Pawn('b', "e7");
		chessboard[1][5].piece_at=new Pawn('b', "f7");
		chessboard[1][6].piece_at=new Pawn('b', "g7");
		chessboard[1][7].piece_at=new Pawn('b', "h7");
		
		chessboard[7][0].piece_at=new Rook('w', "a1");
		chessboard[7][1].piece_at=new Knight('w', "b1");
		chessboard[7][2].piece_at=new Bishop('w', "c1");
		chessboard[7][3].piece_at=new Queen('w', "d1");
		chessboard[7][4].piece_at=new King('w', "e1");
		chessboard[7][5].piece_at=new Bishop('w', "f1");
		chessboard[7][6].piece_at=new Knight('w', "g1");
		chessboard[7][7].piece_at=new Rook('w', "h1");
		
		chessboard[6][0].piece_at=new Pawn('w', "a6");
		chessboard[6][1].piece_at=new Pawn('w', "b6");
		chessboard[6][2].piece_at=new Pawn('w', "c6");
		chessboard[6][3].piece_at=new Pawn('w', "d6");
		chessboard[6][4].piece_at=new Pawn('w', "e6");
		chessboard[6][5].piece_at=new Pawn('w', "f6");
		chessboard[6][6].piece_at=new Pawn('w', "g6");
		chessboard[6][7].piece_at=new Pawn('w', "h6");
		
		
		
		
		
	}
	/**
	   * This method is used for
	   *ending the game upon a trigger ex. CheckMate
	   * @return true or false if the game is over
	   */
	public boolean gameOver() {
		return this.game_over;
	}
	/**
	   * This method is used to print the board
	   * after every move from both players
	   */
	public void printBoard() {
		int i;
		int j;
		for(i=0; i<8; i++) {
			for(j=0; j<8; j++) {
				if(chessboard[i][j].piece_at!=null) {
					System.out.print(chessboard[i][j].piece_at.ret_id()+" ");
				}
				else if(chessboard[i][j].piece_at==null && chessboard[i][j].color=='b') {
					System.out.print("## ");
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.println(j-i);
		}
		i=0;
		char file='a';
		System.out.print(" ");
		while(i<8) {
			System.out.print(file+"  ");
			file++;
			i++;
		}
		System.out.println();
		System.out.println();
	}
	
	//get piece at a certain position on board.  If no piece on square, then return is null.
	/**
	   * This method is used to get a piece from the board
	   * @param rank this is the rank of the piece
	   * @param file this is the file of the piece
	   * @return returns the ChessPiece at the given grid or null if there isn't one there
	   */
	public ChessPiece getpiece(int rank, int file) {
		return chessboard[rank][file].piece_at;
	}
	/**
	   * This method is used to move pieces around the board
	   * @param srcrank starting rank of piece
	   * @param srcfile starting file of piece
	   * @param destrank destination rank of piece
	   * @param destfile destination file of piece
	   */
	public void makemove(int srcrank, int srcfile, int destrank, int destfile) {
		ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
		
		if(piece_to_move instanceof Pawn && piece_to_move.ret_team()=='w' && destrank==0) { //promotion for pawn with no promotion given.  Thus promote to Queen
			int[] findstringvalue= {destrank, destfile};
			chessboard[destrank][destfile].piece_at=new Queen('w', intToStringPostion(findstringvalue));
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(piece_to_move instanceof Pawn && piece_to_move.ret_team()=='b' && destrank==7) { //promotion for pawn with no promotion given.  Thus promote to Queen
			int[] findstringvalue= {destrank, destfile};
			chessboard[destrank][destfile].piece_at=new Queen('b', intToStringPostion(findstringvalue));
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(piece_to_move instanceof King && piece_to_move.ret_team()=='b' && destfile-srcfile==2) { //trying to castle black king to the right
			chessboard[0][5].piece_at=chessboard[0][7].piece_at; //move rook to correct place
			chessboard[0][7].piece_at=null; //make rooks old spot null
			chessboard[destrank][destfile].piece_at=piece_to_move; //move the king
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(piece_to_move instanceof King && piece_to_move.ret_team()=='b' && destfile-srcfile==-2) { //trying to castle black king to the left
			chessboard[0][3].piece_at=chessboard[0][0].piece_at; //move rook to correct place
			chessboard[0][0].piece_at=null; //make rooks old spot null
			chessboard[destrank][destfile].piece_at=piece_to_move; //move the king
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(piece_to_move instanceof King && piece_to_move.ret_team()=='w' && destfile-srcfile==2) { //trying to castle white king to the right
			chessboard[7][5].piece_at=chessboard[7][7].piece_at; //move rook to correct place
			chessboard[7][7].piece_at=null; //make rooks old spot null
			chessboard[destrank][destfile].piece_at=piece_to_move; //move the king
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(piece_to_move instanceof King && piece_to_move.ret_team()=='w' && destfile-srcfile==-2) { //trying to castle white king to the left
			chessboard[7][3].piece_at=chessboard[7][0].piece_at; //move rook to correct place
			chessboard[7][0].piece_at=null; //make rooks old spot null
			chessboard[destrank][destfile].piece_at=piece_to_move; //move the king
			chessboard[srcrank][srcfile].piece_at=null;
		}
		
		else {
		chessboard[destrank][destfile].piece_at=piece_to_move;
		chessboard[srcrank][srcfile].piece_at=null;
		}
		
		if(piece_to_move instanceof Pawn) { //update has moved for Pawn only after the move has occured
			((Pawn) piece_to_move).hasmoved=true;
		}
		if(piece_to_move instanceof King) { //update has moved for King only after the move has occured
			((King) piece_to_move).hasmoved=true;
		}
		if(piece_to_move instanceof Pawn && piece_to_move.ret_team()=='w' && (destrank-srcrank)==-2) { //white pawn moved two spaces forward
			((Pawn) piece_to_move).valid_enpassant=true;
		}
		if(piece_to_move instanceof Pawn && piece_to_move.ret_team()=='b' && (destrank-srcrank)==2) { //black pawn moved two spaces forward
			((Pawn) piece_to_move).valid_enpassant=true;
		}
	}
	/**
	   * This method is used promote a pawn upon making it across the board
	   * @param srcrank starting rank of piece
	   * @param srcfile starting file of piece
	   * @param destrank destination rank of piece
	   * @param destfile destination file of piece
	   * @param Promotion char indicating what to piece promote pawn to
	   * @param team char indicating the team the piece belongs to
	   */
	public void makePromotionMove(int srcrank, int srcfile, int destrank, int destfile, char Promotion, char team) {
		int[] findstringvalue= {destrank, destfile};
		if(Promotion=='R') {
			ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
			piece_to_move=new Rook(team, intToStringPostion(findstringvalue));
			chessboard[destrank][destfile].piece_at=piece_to_move;
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(Promotion=='N') {
			ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
			piece_to_move=new Knight(team, intToStringPostion(findstringvalue));
			chessboard[destrank][destfile].piece_at=piece_to_move;
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(Promotion=='B') {
			ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
			piece_to_move=new Bishop(team, intToStringPostion(findstringvalue));
			chessboard[destrank][destfile].piece_at=piece_to_move;
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else if(Promotion=='Q') {
			ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
			piece_to_move=new Queen(team, intToStringPostion(findstringvalue));
			chessboard[destrank][destfile].piece_at=piece_to_move;
			chessboard[srcrank][srcfile].piece_at=null;
		}
	}
	/**
	   * This method is used to allow Enpassant of a pawn
	   * @param srcrank starting rank of piece
	   * @param srcfile starting file of piece
	   * @param destrank destination rank of piece
	   * @param destfile destination file of piece
	   */
	public void makeEnpassantmove(int srcrank, int srcfile, int destrank, int destfile) {
		if((destrank-srcrank)<0) { //white moving pawn
			ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
			chessboard[destrank][destfile].piece_at=piece_to_move;
			chessboard[destrank+1][destfile].piece_at=null;
			chessboard[srcrank][srcfile].piece_at=null;
		}
		else { //black moving pawn
			ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
			chessboard[destrank][destfile].piece_at=piece_to_move;
			chessboard[destrank-1][destfile].piece_at=null;
			chessboard[srcrank][srcfile].piece_at=null;
		}
	}
	/**
	   * This method is used to move pieces around the board
	   * @param src takes a size 2 array of rank and file indices
	   * @return returns string form of rank and file
	   */
	public String intToStringPostion(int[] src) {
		String toreturn="";
		if(src[1]==7) {
			toreturn+="h";
		}
		else if(src[1]==6) {
			toreturn+="g";
		}
		else if(src[1]==5) {
			toreturn+="f";
		}
		else if(src[1]==4) {
			toreturn+="e";
		}
		else if(src[1]==3) {
			toreturn+="d";
		}
		else if(src[1]==2) {
			toreturn+="c";
		}
		else if(src[1]==1) {
			toreturn+="b";
		}
		else if(src[1]==0) {
			toreturn+="a";
		}
		if(src[0]==7) {
			toreturn+="1";
		}
		else if(src[0]==6) {
			toreturn+="2";
		}
		else if(src[0]==5) {
			toreturn+="3";
		}
		else if(src[0]==4) {
			toreturn+="4";
		}
		else if(src[0]==3) {
			toreturn+="5";
		}
		else if(src[0]==2) {
			toreturn+="6";
		}
		else if(src[0]==1) {
			toreturn+="7";
		}
		else if(src[0]==0) {
			toreturn+="8";
		}
		return toreturn;
	}
	/**
	   * This method is used to set boolean value of game_over field
	   * @param value boolean value of if the game is over
	   */
	public void setGameOver(boolean value) {
		game_over=value;
	}
	/**
	   * This method is used to check if a king is in check
	   * @param kingrank rank of where the king is at
	   * @param kingfile file of where the king is at
	   * @param team char of what team the king is on
	   * @return returns true or false if the king is in check or not
	   */
	public boolean is_check(int kingrank, int kingfile, char team) {
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j<8; j++) {
				if(chessboard[i][j].piece_at != null) {//piece in spot
					if(chessboard[i][j].piece_at.ret_team() != team) {//check opponents pieces if they have a valid move
						if(chessboard[i][j].piece_at.validmove(i, j, kingrank, kingfile, chessboard)) {
						return true; //king is in check
						}
					}
				}
			}
		}
		
		return false; //no valid moves to king not in check
	}
	/**
	   * This method is used to check if a king is in checkmate
	   * @param kingrank rank of where the king is at
	   * @param kingfile file of where the king is at
	   * @param team char of what team the king is on
	   * @return returns true or false if the king is in checkmate or not
	   */
	public boolean is_checkmate(int kingrank, int kingfile, char team) {//only call if king is in check
		boolean chk_king_tomove=true;
		boolean chk_otherpieces_tomove=true;
		King new_king = (King)chessboard[kingrank][kingfile].piece_at;
		ChessPiece piece_beingchecked;
		ChessPiece piece_goingto;
		
		if(!is_check(kingrank, kingfile, team)) {//king isnt in check
			return false;
		}
		for(int i = 0; i < 8; i++) {//king is in check look for possible move that gets out of check
			for(int j = 0; j<8; j++) {
				if(new_king.validmove(kingrank, kingfile, i, j, chessboard)) { //check for move that doesnt result in check
					piece_goingto=chessboard[i][j].piece_at;
					chessboard[i][j].piece_at=new_king;
					chessboard[kingrank][kingfile].piece_at=null;
					if(!is_check(i, j, team)) {
						chk_king_tomove=false;	//if there is a move that doesnt result in check king is not in check mate
					}
					chessboard[kingrank][kingfile].piece_at=new_king;
					chessboard[i][j].piece_at=piece_goingto;
				}
			}
		}
		
		//check to see if any piece can block a check
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(chessboard[i][j].piece_at!=null && chessboard[i][j].piece_at.ret_team()==team && !(chessboard[i][j].piece_at instanceof King)) {
					for(int i1=0; i1<8; i1++) {
						for(int j1=0; j1<8; j1++) {
							if(chessboard[i][j].piece_at.validmove(i, j, i1, j1, chessboard)) {
								piece_goingto=chessboard[i1][j1].piece_at;
								piece_beingchecked=chessboard[i][j].piece_at;
								chessboard[i][j].piece_at=null;
								chessboard[i1][j1].piece_at=piece_beingchecked;
								if(!is_check(kingrank, kingfile, team)) {
									chk_otherpieces_tomove=false;
								}
								chessboard[i][j].piece_at=piece_beingchecked;
								chessboard[i1][j1].piece_at=piece_goingto;
							}
						}
					}
				}
			}
		}
		if(chk_king_tomove==true && chk_otherpieces_tomove==true) {
			return true;
		}
		return false;
	}
	/**
	   * This method is used to check if a king castling attempt will result in check
	   * @param rankdest rank of where the king is castling to
	   * @param filedest file of where the king is castling to
	   * @return returns true or false if the attempt to castle while result in check
	   */
	public boolean castle_into_check(int rankdest, int filedest) {
		boolean to_check=false;
		ChessPiece oldKing;
		
		if(rankdest==7) { //white king castling
			if(is_check(7,4,'w')) {
				return true;
			}
			else if(filedest==2) { //to the left
				for(int i=3; i>=filedest; i--) {
					oldKing=this.chessboard[7][4].piece_at;
					this.chessboard[7][i].piece_at=oldKing;
					this.chessboard[7][4].piece_at=null;
					if(this.is_check(7, i, 'w')) {
						to_check=true;
					}
					this.chessboard[7][i].piece_at=null;
					this.chessboard[7][4].piece_at=oldKing;
				}
			}
			else if(filedest==6) { //to the right
				for(int i=5; i<=filedest; i++) {
					oldKing=this.chessboard[7][4].piece_at;
					this.chessboard[7][i].piece_at=oldKing;
					this.chessboard[7][4].piece_at=null;
					if(this.is_check(7, i, 'w')) {
						to_check=true;
					}
					this.chessboard[7][i].piece_at=null;
					this.chessboard[7][4].piece_at=oldKing;
				}
			}
		}
		else if(rankdest==0) { //black king trying to castle
			if(is_check(0,4,'b')) { 
				return true;
			}
			else if(filedest==2) { //to the left
				for(int i=3; i>=filedest; i--) {
					oldKing=this.chessboard[0][4].piece_at;
					this.chessboard[0][i].piece_at=oldKing;
					this.chessboard[0][4].piece_at=null;
					if(this.is_check(0, i, 'b')) {
						to_check=true;
					}
					this.chessboard[0][i].piece_at=null;
					this.chessboard[0][4].piece_at=oldKing;
				}
			}
			else if(filedest==6) { //to the right
				for(int i=5; i<=filedest; i++) {
					oldKing=this.chessboard[0][4].piece_at;
					this.chessboard[0][i].piece_at=oldKing;
					this.chessboard[0][4].piece_at=null;
					if(this.is_check(0, i, 'b')) {
						to_check=true;
					}
					this.chessboard[0][i].piece_at=null;
					this.chessboard[0][4].piece_at=oldKing;
				}
			}
		}
		return to_check;
	}
	/**
	   * This method is used to check if a move results in the king being placed in check
	   * @param srcrank rank of where the piece is at
	   * @param srcfile file of where the  piece is at
	   * @param destrank rank of where the piece wants to move to
	   * @param destfile file of where the  piece wants to move to
	   * @param team char of what team the piece belongs
	   * @return returns true or false if the move results in the king becoming checked
	   */
	public boolean check_move_intoCheck(int srcrank, int srcfile, int destrank, int destfile, char team) {
		boolean is_check_aftermove=false;
		int[] king_ind=getkinglocation(team);
		ChessPiece piece_to_move=chessboard[srcrank][srcfile].piece_at;
		ChessPiece piece_goingto=chessboard[destrank][destfile].piece_at;
		chessboard[destrank][destfile].piece_at=piece_to_move;
		chessboard[srcrank][srcfile].piece_at=null;
		if(is_check(king_ind[0], king_ind[1], team)) {
			is_check_aftermove=true;
		}
		chessboard[srcrank][srcfile].piece_at=piece_to_move;
		chessboard[destrank][destfile].piece_at=piece_goingto;
		
		return is_check_aftermove;
	}
	
	//method to get king location for that team given
	/**
	   * This method is used to check if a king location
	   * @param team char of team of the king youre trying to find
	   * @return returns int array of size 2 with rank at index 0 and file at index 1
	   */
	public int[] getkinglocation(char team) {
		int[] kings_indicies=new int[2];
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(chessboard[i][j].piece_at instanceof King && chessboard[i][j].piece_at.ret_team()==team) {
					kings_indicies[0]=i;
					kings_indicies[1]=j;
				}
			}
		}
		
		return kings_indicies;
	}
	/**
	   * This method is used to assist with enpassant
	   * @param team char team of piece
	   */
	public void clear_enpassant(char team) {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(chessboard[i][j].piece_at!=null && chessboard[i][j].piece_at instanceof Pawn && chessboard[i][j].piece_at.ret_team()==team) {
					((Pawn)chessboard[i][j].piece_at).valid_enpassant=false;
				}
			}
		}
	}
	
}
