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
	//static CasinoUIStartupFrame casinoMainFrame;
	//static CasinoUIGamePlayFrame casinoPlayFrame;
	private Map<String, Player> playerList;
	private List<Wager> wagerList;
	private String gameName;

	//private Casino casino;
	private Game game;

    // the UI frames
    private CasinoUIStartupFrame casinoMainFrame;
    private CasinoUIGamePlayFrame casinoPlayFrame;

	Casino() {
		casinoMainFrame = new CasinoUIStartupFrame(this);
		casinoMainFrame.setVisible(true);
		casinoMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Casino casino = new Casino();
		
	}

	public void addPlayer(String name, String screenName, int account) {
		Player newPlayer = new Player(name, screenName, account);
		playerList.put(screenName, newPlayer);
	}

	public void deletePlayer(String screenName) {
		playerList.remove(screenName);
	}

	// Add a new wager. If playerId exists, the wager is overwritten
	public void addWager(String screenName, double wagerAmount, String wagerType, List<Integer> wagerContent) {
		Wager newWager;

		if (wagerContent == null) {
			newWager = new Wager(screenName, wagerType, wagerAmount);
		}
		else {
			newWager = new Wager(screenName, wagerType, wagerAmount, wagerContent);
		}

		wagerList.add(newWager);
	}

	public void deleteWager(String screenName, double wagerAmount, String wagerType, List<Integer> wagerContent) {
		// we need to search the wager list for the first matching wager, and remove it
		// if the wager is not found then we should fail

		for ( Wager currentWager : wagerList) {
			if (currentWager.getWagerId() 		== screenName &&
				currentWager.getWagerAmount() 	== wagerAmount &&
				currentWager.getWagerType() 	== wagerType &&
				currentWager.getWagerContent().equals(wagerContent)
				)

				wagerList.remove(currentWager);
				break;
			}
	}

	// This method will pay based on last game result,
	// and also CLEAR the existing wagerList!
	public void payWagers() {

	}

	public boolean createGame(String gameName) {
		if (gameName.equals("Roulette")) {
			game = new RouletteGame();
			casinoPlayFrame = new CasinoUIGamePlayFrame(game, this);
			casinoPlayFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			return true;
		}
		else {
			System.out.println("selectCreateGame: Invalid gameName " + gameName);
			return false;
		}
	}

	public void selectGameFrame(boolean select) {
		if (select) {
			casinoMainFrame.setVisible(false);
			casinoPlayFrame.setVisible(true);
		}
		else {
			casinoMainFrame.setVisible(true);
			casinoPlayFrame.setVisible(false);
		}
	}

}
