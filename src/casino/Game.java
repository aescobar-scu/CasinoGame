package casino;

public abstract class Game {


	abstract void runGame();
	abstract int getGameResult();
	abstract double calculatePayout(Wager wager);
	abstract String getGameResultString();
}
