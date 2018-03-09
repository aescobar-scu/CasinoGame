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
	double finalPayout = 0; //the final amount to be added or deducted from the player account
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
		
		if(spinColor.contains("Red")) {
			spinColor = "Red";
		}
		else if(spinColor.contains("Black")) {
			spinColor = "Black";
		}
		else {
			spinColor = "Green";
		}
		
	}

	// this method returns the integer result of the last spin
	public int getGameResult() {
		return spinValue;
	}
	
	// this method returns a user string for the current game result
	// the string will be displayed exactly like this in the UI
	public String getGameResultString() {
		
		String strResult = spinColor + " " + Integer.toString(spinValue);
		return strResult;
	}
	
	/*
	 * Checks if the given wager has won or lost
	 * Calculates the amount after the game result to update the player and casino account
	 * @see casino.Game#calculatePayout(casino.Wager)
	 */

	public double calculatePayout(Wager objWager) {
		
		try {
		betPayout = 0; betAmount = 0; finalPayout = 0;
		List<Integer> lstBet = objWager.getBetNumber();
		betPayout = objWager.getPayoutOdds();
		betAmount = objWager.getWagerAmount();
		
		switch(objWager.getWagerType()) {
				
				case("Straight"):
				case("Split"):
					if (lstBet.contains(spinValue)) {
                        finalPayout = betAmount + (betAmount * betPayout);
                    }
                    else {
                        finalPayout = (0 - betAmount);
                    }
                    break;

				case("Street"):
                    if (lstBet.contains(spinValue)) {
                        finalPayout = betAmount + (betAmount * betPayout);
                    }
                    else {
                        finalPayout = (0 - betAmount);
                    }
                    break;

				case("Square"):
                    if (lstBet.contains(spinValue)) {
                        finalPayout = betAmount + (betAmount * betPayout);
                    }
                    else {
                        finalPayout = (0 - betAmount);
                    }
                    break;

				case("Six Line"):
                    if (lstBet.contains(spinValue)) {
                        finalPayout = betAmount + (betAmount * betPayout);
                    }
                    else {
                        finalPayout = (0 - betAmount);
                    }
					break;
				case("Columns"):

				case("Dozens"):
                    for (int i : lstBet) {
                        if (spinValue > (i-1)*12 && spinValue <= i*12) {
                            finalPayout = betAmount + (betAmount * betPayout);
                        }
                        else {
                            finalPayout = (0 - betAmount);
                        }
                    }
                    break;

				case("Red"):
					
					if(spinColor.equals("Red")) {
						finalPayout = betAmount + (betAmount * betPayout);
					}
					else {
						finalPayout = (0 - betAmount);
					}
					break;
				
				case("Black"):
					
					if(spinColor.equals("Black")) {
						finalPayout = betAmount + (betAmount * betPayout);
					}
					else {
						finalPayout = (0 - betAmount);
					}
					break;
				
				case("Highs"):
					
					if(spinValue >= 19 && spinValue <= 36) {
						finalPayout = betAmount + (betAmount * betPayout);
					}
					else {
						finalPayout = (0 - betAmount);
					}
					break;
				
				case("Lows"):
					
					if(spinValue >= 1 && spinValue <= 18) {
						finalPayout = betAmount + (betAmount * betPayout);
					}
					else {
						finalPayout = (0 - betAmount);
					}
					break;
				
				case("Odds"):
					
					if(spinValue % 2 != 0) {
						finalPayout = betAmount + (betAmount * betPayout);
					}
					else {
						finalPayout = (0 - betAmount);
					}
					System.out.println("Final Payout at Odds:" + finalPayout);
					break;
				
				case("Evens"):
					
					if(spinValue % 2 == 0) {
						finalPayout = betAmount + (betAmount * betPayout);
					}
					else {
						finalPayout = (0 - betAmount);
					}
					break;
				}
		return finalPayout;
		
		}catch(NullPointerException Ex) {
			
			System.out.println("Error Message: Wager Object not found.");
			return 0;
		}

	}
}

