package chess;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Player {
	/**
	 * team the player is on
	 */
	char team; //b or w
	/**
	 * Constructor for player
	 * @param team takes char for team player is on b Black w White
	 */
	public Player(char team) {
		this.team=team;
	}
	/**
	 * Method to take a players input for movement
	 * @return string of players desired movement
	 * @throws IOException blah blah not used
	 */
	public String askMove() throws IOException {
		BufferedReader player_input = new BufferedReader(new InputStreamReader(System.in));
		String s_input;
		s_input=player_input.readLine();
		System.out.println();
		return s_input;
		
	}
	/**
	 * Method to ask player for piece desired upon promotion
	 * @param user_input piece user requests
	 * @return returns char of letter or piece user wants
	 */
	public char type_of_Promotion(String user_input) {
		return user_input.charAt(6);
	}
}
