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
	void runGame() {
		
		int random = new Random().nextInt(SpinResult.values().length);
		spinValue = SpinResult.values()[random].value;
		spinColor = SpinResult.values()[random].name().toString();
		
	}
	
	/*
	 * Checks if the given wager has won or lost
	 * Calculates the amount after the game result to update the player and casino account
	 * @see casino.Game#calculatePayout(casino.Wager)
	 */
	
	void calculatePayout(Wager objWager) {
		
		betPayout = 0; betAmount = 0; betResult = 0;
		List<Integer> lstBet = objWager.getBetNumber();
		
		switch(objWager.getWagerType()) {
				
				case("Straight"):
				case("Split"):
				case("Street"):
				case("Square"):
				case("Six Line"):
				case("Columns"):
				case("Dozens"):
					
					for(int i : lstBet) {
						if(i == spinValue) {
							betPayout = objWager.getPayoutOdds();
							betAmount = objWager.getWagerAmount();
							betResult = betAmount + (betAmount * betPayout);
						}
						else {
							betResult = (0 - betAmount);
						}
					}
				
				case("Red"):
					
					if(spinColor.contains("Red")) {
						betPayout = objWager.getPayoutOdds();
						betAmount = objWager.getWagerAmount();
						betResult = betAmount + (betAmount * betPayout);
					}
					else {
						betResult = (0 - betAmount);
					}
				
				case("Black"):
					
					if(spinColor.contains("Black")) {
						betPayout = objWager.getPayoutOdds();
						betAmount = objWager.getWagerAmount();
						betResult = betAmount + (betAmount * betPayout);
					}
					else {
						betResult = (0 - betAmount);
					}
				
				case("Highs"):
					
					if(spinValue >= 19 && spinValue <= 36) {
						betPayout = objWager.getPayoutOdds();
						betAmount = objWager.getWagerAmount();
						betResult = betAmount + (betAmount * betPayout);
					}
					else {
						betResult = (0 - betAmount);
					}
				
				case("Lows"):
					
					if(spinValue >= 1 && spinValue <= 18) {
						betPayout = objWager.getPayoutOdds();
						betAmount = objWager.getWagerAmount();
						betResult = betAmount + (betAmount * betPayout);
					}
					else {
						betResult = (0 - betAmount);
					}
				
				case("Odds"):
					
					if(spinValue % 2 != 0) {
						betPayout = objWager.getPayoutOdds();
						betAmount = objWager.getWagerAmount();
						betResult = betAmount + (betAmount * betPayout);
					}
					else {
						betResult = (0 - betAmount);
					}
				
				case("Evens"):
					
					if(spinValue % 2 == 0) {
						betPayout = objWager.getPayoutOdds();
						betAmount = objWager.getWagerAmount();
						betResult = betAmount + (betAmount * betPayout);
					}
					else {
						betResult = (0 - betAmount);
					}
				}
			}
	
	/*
	 * This method is to return the resultant amount to update the player and casino account
	 * @see casino.Game#getGameResult()
	 */
	
	double getGameResult(Wager objWager) {
		calculatePayout(objWager);
		return betResult;
	}
}
