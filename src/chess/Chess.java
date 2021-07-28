package chess;

import java.io.IOException;
/**
*Driver File for chess game
* @author  Jason Hunt, Timothy Kinsey
* @version 1.0
* @since   2019-03-01 
*/
public class Chess {
	//main method to start our game and get it running
	/**
	 * Main method to run chess game
	 * @param args takes no actual arguments
	 * @throws IOException blah blah blah not used
	 */
	public static void main(String[] args) throws IOException {
		String blackmove; //black's string input for a move
		String whitemove; //white's string input for a move
		int[] user_indices=null; //array to hold matrix values of a move a player wants to make
		char promotion_to;
		ChessPiece piece_to_move=null; //piece that the player wishes to move
		int whiteKing[];
		int blckKing[];
		//create our board object to hold all chess pieces.
		Board gameboard=new Board();
		gameboard.printBoard();
		
		int first_move=0;
		
		//create player objects one for each team
		Player black_player=new Player('b');
		Player white_player=new Player('w');
		
		//while the game has not ended keep asking for moves from players
		while(gameboard.gameOver()!=true) {
			first_move=0;
			//get whites move only if valid
			do {
				if(first_move==1) {
					System.out.println("Illegal move, try again");
					System.out.println();
				}
				gameboard.clear_enpassant('w');
				whiteKing=gameboard.getkinglocation('w');
				if(gameboard.is_checkmate(whiteKing[0], whiteKing[1], 'w')) {
					System.out.println("Checkmate");
					System.out.println("Black wins");
					return;
				}
				if(gameboard.is_check(whiteKing[0], whiteKing[1], 'w')) { //if player is in check, let them know it
					System.out.println("Check");
					System.out.println();
				}
				whitemove=white_player.askMove();
				if(whitemove.equals("resign")) {//if player resigns
					gameboard.setGameOver(true);
					System.out.print("Black wins");
					break;
				}
				user_indices=convert_input(whitemove);
				if(user_indices[0]!=-1 && user_indices[1]!=-1) {
					piece_to_move=gameboard.getpiece(user_indices[0], user_indices[1]); //find out what piece is wanting to be moved by player
				}
				first_move=1;
				}while(isvalidinput(whitemove)==false || 
						(user_indices[0]==-1 || user_indices[1]==-1 || user_indices[2]==-1 || user_indices[3]==-1) || //check that the user input is on the board dimensions
						piece_to_move==null ||
						piece_to_move.ret_team()!='w' ||   //check to make sure player is moving there own piece
						piece_to_move.validmove(user_indices[0], user_indices[1], user_indices[2], user_indices[3], gameboard.chessboard)==false || //make sure that this piece can make this move
						(!(piece_to_move instanceof Pawn) && whitemove.length()==7) || //check that the piece trying to be promoted is a pawn
						(piece_to_move instanceof Pawn && whitemove.length()==7 && ((Pawn) piece_to_move).validpromotion(user_indices[0], user_indices[1], user_indices[2], user_indices[3], gameboard.chessboard)==false) || //check to make sure valid promotion move for pawn
						(!(piece_to_move instanceof King) && gameboard.check_move_intoCheck(user_indices[0], user_indices[1], user_indices[2], user_indices[3], 'w')==true) || //check to make sure move by player does not put there king into check
						(piece_to_move instanceof King && ((King) piece_to_move).check_castling(user_indices[2], user_indices[3], gameboard.chessboard) && gameboard.castle_into_check(user_indices[2], user_indices[3])==true)); //check if castling move is putting king into check along path 
			
			first_move=0;
			if(whitemove.equals("resign")) {
				continue;
			}
			if(whitemove.contains("draw?")) {
				System.out.print("draw");
				return;
			}
			else if(whitemove.length()==7) {  //is promotion move
				promotion_to=whitemove.charAt(6);
				gameboard.makePromotionMove(user_indices[0], user_indices[1], user_indices[2], user_indices[3], promotion_to, 'w');
			}
			else if(gameboard.chessboard[user_indices[0]][user_indices[1]].piece_at instanceof Pawn && gameboard.chessboard[user_indices[2]+1][user_indices[3]].piece_at!=null && gameboard.chessboard[user_indices[2]+1][user_indices[3]].piece_at instanceof Pawn && ((Pawn)gameboard.chessboard[user_indices[2]+1][user_indices[3]].piece_at).valid_enpassant==true) { //white pawn trying to en passant
				gameboard.makeEnpassantmove(user_indices[0], user_indices[1], user_indices[2], user_indices[3]);
			}
			else {
			gameboard.makemove(user_indices[0], user_indices[1], user_indices[2], user_indices[3]); //make the move on the board now
			}
			gameboard.printBoard();//reprint the board after a move
			
			//get blacks move only if valid
			do {
			if(first_move==1) {
				System.out.println("Illegal move, try again");
				System.out.println();
			}
			gameboard.clear_enpassant('b');
			blckKing=gameboard.getkinglocation('b');
			if(gameboard.is_checkmate(blckKing[0], blckKing[1], 'b')) {
				System.out.println("Checkmate");
				System.out.println("White wins");
				return;
			}
			if(gameboard.is_check(blckKing[0], blckKing[1], 'b')) { //if player is in check, let them know it
				System.out.println("Check");
				System.out.println();
			}
			blackmove=black_player.askMove();
			if(blackmove.equals("resign")) {//if black resigns or gets checkmated
				gameboard.setGameOver(true);
				System.out.print("White wins");
				break;
			}
			user_indices=convert_input(blackmove);
			if(user_indices[0]!=-1 && user_indices[1]!=-1) {
				piece_to_move=gameboard.getpiece(user_indices[0], user_indices[1]); //find out what piece is wanting to be moved by player
			}
			first_move=1;
			}while(isvalidinput(blackmove)==false ||
					(user_indices[0]==-1 || user_indices[1]==-1 || user_indices[2]==-1 || user_indices[3]==-1) ||  //check that the user input is on the board dimensions
					piece_to_move==null ||
					piece_to_move.ret_team()!='b' ||   //check to make sure player is moving there own piece
					piece_to_move.validmove(user_indices[0], user_indices[1], user_indices[2], user_indices[3], gameboard.chessboard)==false || //make sure that this piece can make this move
					(!(piece_to_move instanceof Pawn) && blackmove.length()==7)||  //check that the piece trying to be promoted is a pawn
					(piece_to_move instanceof Pawn && blackmove.length()==7 && ((Pawn) piece_to_move).validpromotion(user_indices[0], user_indices[1], user_indices[2], user_indices[3], gameboard.chessboard)==false) ||  //check to make sure valid promotion move for pawn
					gameboard.check_move_intoCheck(user_indices[0], user_indices[1], user_indices[2], user_indices[3], 'b')==true ||//check to make sure move by player does not put there king into check
					(piece_to_move instanceof King && ((King) piece_to_move).check_castling(user_indices[2], user_indices[3], gameboard.chessboard) && gameboard.castle_into_check(user_indices[2], user_indices[3])==true)); //check if castling move is putting king into check along path
			
			first_move=0;
			if(blackmove.equals("resign")) {
				continue;
			}
			if(blackmove.contains("draw?")) {
				System.out.print("draw");
				return;
			}
			else if(blackmove.length()==7) { //is promotion move
				promotion_to=blackmove.charAt(6);
				gameboard.makePromotionMove(user_indices[0], user_indices[1], user_indices[2], user_indices[3], promotion_to, 'b');
			}
			else if(gameboard.chessboard[user_indices[0]][user_indices[1]].piece_at instanceof Pawn && gameboard.chessboard[user_indices[2]-1][user_indices[3]].piece_at!=null && gameboard.chessboard[user_indices[2]-1][user_indices[3]].piece_at instanceof Pawn && ((Pawn)gameboard.chessboard[user_indices[2]-1][user_indices[3]].piece_at).valid_enpassant==true) { //black pawn trying to en passant
				gameboard.makeEnpassantmove(user_indices[0], user_indices[1], user_indices[2], user_indices[3]);
			}
			else {
			gameboard.makemove(user_indices[0], user_indices[1], user_indices[2], user_indices[3]); //make the move on the board now
			}
			gameboard.printBoard();//reprint the board after a move
			
		}
		
	}
	
	//method to convert user input to indexes in our board array.  Return value is int array with values at [0][1] being our rankfile src and [2][3] being our rankfile dest.
	/**
	 * Method to convert users input from letter and number combo to indices to use for the chessboard
	 * @param user_input string of users input
	 * @return int array of indices of users string number combo coordinates
	 */
	public static int[] convert_input(String user_input) {
		int[] indices=new int[4];
		
		char c_inranksrc=user_input.charAt(1);
		char c_infilesrc=user_input.charAt(0);
		char c_inrankdest=user_input.charAt(4);
		char c_infiledest=user_input.charAt(3);
		
		indices[0]=get_charvalues(c_inranksrc, 'r');
		indices[1]=get_charvalues(c_infilesrc, 'f');
		indices[2]=get_charvalues(c_inrankdest, 'r');
		indices[3]=get_charvalues(c_infiledest, 'f');
		
		return indices;
	}
	
	//method to convert userinput into indices of the board
	/**
	 * Helper method for convert_input
	 * @param c given character letter or number
	 * @param type rank or file
	 * @return returns int coordinating to rank or file of the board
	 */
	public static int get_charvalues(char c, char type) {
		int value;
		
		if(type=='r') {
			if(c=='8') {
				value=0;
			}
			else if(c=='7') {
				value=1;
			}
			else if(c=='6') {
				value=2;
			}
			else if(c=='5') {
				value=3;
			}
			else if(c=='4') {
				value=4;
			}
			else if(c=='3') {
				value=5;
			}
			else if(c=='2') {
				value=6;
			}
			else if(c=='1') {
				value=7;
			}
			else {
				value=-1;
			}
		}
		else {
			if(c=='a') {
				value=0;
			}
			else if(c=='b') {
				value=1;
			}
			else if(c=='c') {
				value=2;
			}
			else if(c=='d') {
				value=3;
			}
			else if(c=='e') {
				value=4;
			}
			else if(c=='f') {
				value=5;
			}
			else if(c=='g') {
				value=6;
			}
			else if(c=='h') {
				value=7;
			}
			else {
				value=-1;
			}
		}
		
		return value;
	}
	
	//method to make sure user input is valid based on length of input
	/**
	 * Ensures a user's input is valid
	 * @param userinput string of the users input
	 * @return returns true or false 
	 */
	public static boolean isvalidinput(String userinput) {
		if(userinput.length()!=5 && userinput.length()!=7 && !userinput.contains("draw?")) {
			return false;
		}
		if(userinput.length()==7 && (userinput.charAt(6)!='R' && userinput.charAt(6)!='N' && userinput.charAt(6)!='B' && userinput.charAt(6)!='Q')) { //make sure valid promotion type
			return false;
		}
		else {
			return true;
		}
	}
}
