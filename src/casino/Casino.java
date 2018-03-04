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
	private static Map<String, SessionResult> sessionResultList;
	
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
		sessionResultList = new HashMap<String, SessionResult>();
	}

	public class SessionResult {
		public String screenName;
		public double lastWinLoss;
		public double accountBalance;
		
		SessionResult(String screenName, double accountBalance) {
			this.lastWinLoss = 0;
			this.screenName = screenName;
			this.accountBalance = accountBalance;
		}
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
		newWager = new Wager(screenName, wagerType, wagerAmount, wagerContent);
		wagerList.add(newWager);
	}

	public void addWager(String screenName, double wagerAmount, String wagerType) {
		Wager newWager;
		newWager = new Wager(screenName, wagerType, wagerAmount);
		wagerList.add(newWager);
	}
	public void deleteWager(String screenName, double wagerAmount, String wagerType, List<Integer> wagerContent) {
		// we need to search the wager list for the first matching wager, and remove it
		// if the wager is not found then we should fail

		for ( Wager currentWager : wagerList) {
			if (currentWager.getPlayerName() 		== screenName &&
				currentWager.getWagerAmount() 	== wagerAmount &&
				currentWager.getWagerType() 	== wagerType &&
				currentWager.getBetNumber().equals(wagerContent)
				)

				wagerList.remove(currentWager);
				break;
			}
	}

	// This method will pay based on last game result, and return a summation
	// of player winnings or losses
	public void payWagers() {

		// clear lastWinResults
		for (SessionResult sessionResult : sessionResultList.values()) {
			sessionResult.lastWinLoss = 0;
			sessionResultList.put(sessionResult.screenName, sessionResult);
		}
		
		
		for (Wager wager : wagerList) {
			// get screenName and payout result from the wager
			String screenName = wager.getPlayerName();
			Player p = playerList.get(screenName);
			double payout = game.calculatePayout(wager);
			
			//if (!sessionResultList.containsKey(screenName)) {
			//	SessionResult sessionResult = new SessionResult(screenName, p.getAccount());
			//	sessionResultList.put(screenName, sessionResult);
			//}
			// update the player account
			p.setAccount(p.getAccount() + payout);
			playerList.put(screenName, p);
			
			// update the session list running total and account
			SessionResult sessionResult = sessionResultList.get(screenName);
			sessionResult.lastWinLoss += payout;
			sessionResult.accountBalance = p.getAccount();
			sessionResultList.put(screenName, sessionResult);
		}		
	}

	public boolean createGame(String gameName) {
		if (gameName.equals("Roulette")) {
			game = new RouletteGame();
			
			// initialize the sessionList for display
			for (Player p : playerList.values()) {
				SessionResult s = new SessionResult(p.getNickName(), p.getAccount());
				sessionResultList.put(p.getNickName(), s);
			}
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

	public List<String> getPlayerScreenNames() {
		List<String> playerScreenNameList = new ArrayList<String>();
		for (Player player : playerList.values()) {
			playerScreenNameList.add(player.getNickName());
		}
		return playerScreenNameList;
	}
	
	// true if player already exists in the playerList
	public boolean hasPlayer(String screenName){
		if (playerList.containsKey(screenName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clearWagerList() {
		wagerList.clear();
	}
	
	
	public void printPlayerList(){
		for (Player player : playerList.values()) {
			System.out.println("DEBUG: printPlayerList: screenName " + player.getNickName());
			System.out.println("		name " + player.getName());
			System.out.println("		account " + player.getAccount());
			
		}
	}
	
	public void printWagerList(){
		for (Wager wager : wagerList) {
			System.out.println("DEBUG: printWagerList: screenName " + wager.getPlayerName());
			System.out.println("		wagerAmount " + wager.getWagerAmount());
			System.out.println("		wagerType " + wager.getWagerType());
			System.out.println("		wagerContent " + wager.getBetNumber());
		}
	}
	
	public void printSessionResultList() {
		for (SessionResult sessionResult : sessionResultList.values()) {
			System.out.println("DEBUG: sessionResultList: screenName " + sessionResult.screenName);
			System.out.println("	   lastWinLoss " + sessionResult.lastWinLoss);
			System.out.println("	   accountBalance " + sessionResult.accountBalance);
		}
	}

}
