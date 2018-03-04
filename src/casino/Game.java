package casino;

public abstract class Game {


	abstract void runGame();
	abstract double getGameResult(Wager objWager);
	abstract void calculatePayout(Wager objWager);
	
}
