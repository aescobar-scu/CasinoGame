/*
 * Rules and implementation of the Roulette game
 * 
 */

package casino;

import java.util.List;
import java.util.Random;

public class RouletteGame extends Game {
	
	String gameName;
	int spinValue; //the value resulted from spinning the wheel
	int betPayout; //the Payout Odd for the selected Wager
	double betAmount; //the bet amount on the selected wager
	String spinColor; //the color associated with the resulted number from spinning the wheel
	double betResult = 0; //the final amount to be added or deducted from the player account
	RouletteGame() {
		gameName = new String("Roulette");
	}
	
	/* Assigning specific values to the enum constants for the spin wheel	*/
	
	public enum SpinResult{
		Green(0), Red_1(32), Black_1(15), Red_2(19), Black_2(4), Red_3(21), Black_3(2), 
		Red_4(25), Black_4(17), Red_5(34), Black_5(6), Red_6(27), Black_6(13),Red_7(36), Black_7(11), 
		Red_8(30), Black_8(8), Red_9(23), Black_9(10), Red_10(5), Black_10(24), Red_11(16), Black_11(33), 
		Red_12(1), Black_12(20),Red_13(14), Black_13(31), Red_14(9), Black_14(22), Red_15(18), Black_15(29), 
		Red_16(7), Black_16(28), Red_17(12), Black_17(35), Red_18(3), Black_18(26);
		
		
		protected int value;
		SpinResult(int value) {
			this.value = value;
		}
	}
	
	/*
	 * Spins the roulette wheel to generate a new random result,
	 * and makes that available to getGame Result
	 * @see casino.Game#runGame()
	 */
	
	// A thread is needed to start when this is called.
	// the thread will calculate the spin result
	// the casino will call runGame() as before, 
	// but the method will then perform the thread.start().
	void runGame() {	
		int random = new Random().nextInt(SpinResult.values().length);
		spinValue = SpinResult.values()[random].value;
		spinColor = SpinResult.values()[random].name().toString();
		
	}

	// this method returns the integer result of the last spin
	public int getGameResult() {
		return spinValue;
	}
	
	// this method returns a user string for the current game result
	// the string will be displayed exactly like this in the UI
	public String getGameResultString() {
		String resultString = new String("");
		resultString = "Red 17";
		return resultString;
	}

	public double calculatePayout(Wager wager) {
		double payout = 100; // temporary result for testing, real result to be implemented
		return payout;

	}
}
