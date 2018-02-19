/*
 * This implements the FactoryMethod design pattern to
 * select a game
 *  
 */

package casino;

public class GameFactory {
	public static Game getGame(String gameName) {
		if ("Roulette".equalsIgnoreCase(gameName)) {
			return new RouletteGame();
		}
		else {
			System.out.println("ERROR: Unknown gameName: " + gameName + " returning null");
			return null;
		}
	}
}
