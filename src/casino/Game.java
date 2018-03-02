package casino;

public abstract class Game {


	abstract void runGame();
	abstract void getGameResult();
	abstract double calculatePayout(Wager wager);
	
}
