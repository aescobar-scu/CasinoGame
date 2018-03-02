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
	private static Map<String, Player> playerList;
	private static List<Wager> wagerList;
	private String gameName;

	// game is not initialized until the PLAY button is pressed in the UI
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
		playerList = new HashMap<String, Player>();
		wagerList = new ArrayList<Wager>();
		
	}

	public void addPlayer(String name, String screenName, double accountValue) {
		Player newPlayer = new Player(name, screenName, accountValue);
		playerList.put(screenName, newPlayer);
		System.out.println("addPlayer: " + name + " to playerList, now " + playerList.size() + " players");
	}

	public void deletePlayer(String screenName) {
		playerList.remove(screenName);
		System.out.println("deletePlayer: " + screenName + " from playerList, now " + playerList.size() + " players");
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
	public void payWagers() {
		for (Wager wager : wagerList) {
			// get screenName and payout result from the wager
			String playerName = wager.getWagerId();
			double payout = game.calculatePayout(wager);
			
			// update the player account
			Player p = playerList.get(playerName);
			p.setAccount(p.getAccount() + payout); 
			playerList.put(playerName, p);
		}
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
