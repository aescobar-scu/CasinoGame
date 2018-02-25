/**
 * 
 */
package casino;
import java.util.*;
import javax.swing.JFrame;


/**
 * Main class for running the Casino game
 * @author tony
 *
 */

public class Casino {

	/**
	 * @param args
	 */
	// casino game ui screens
	CasinoUIStartupFrame casinoMainFrame;
	CasinoUIGamePlayFrame casinoPlayFrame;
	
	// For the player and wager lists, we use the player selected nickname
	// as the unique player id. This nickname/id acts as the reference point 
	// linking players to the wager. // No duplicate players or wagers allowed.
	private Map<String, Player> playerList;
	private Map<String, Wager> wagerList;
	// TODO: We probably want to move wagerList to Game, but keeping it here for now
	// But I think that playerList belongs in theCasino itself, not in the game 
	// (people reside in the casino). The game should deal with game logic, wagers, and payouts,
	
	Casino() {
		casinoMainFrame = new CasinoUIStartupFrame();
		casinoPlayFrame = new CasinoUIGamePlayFrame();	
		casinoMainFrame.setVisible(true);
		casinoPlayFrame.setVisible(true); // for test only
	}

	public static void main(String[] args) {
		
		Casino casinoPlay = new Casino();	
		casinoPlay.casinoMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this is necessary to prevent closing the screen from exiting the game
		casinoPlay.casinoPlayFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
	
	public void addPlayer() {
		
	}
	
	public void deletePlayer() {
		
	}
	
	public void selectGame() {
		
	}
	
	// Add a new wager. If playerId exists, the wager is overwritten
	public void addWager(Wager wager) {
		
	}
	
	// This method will pay based on last game result,
	// and also CLEAR the existing wagerList!
	public void payWagers() {
		
	}

}
