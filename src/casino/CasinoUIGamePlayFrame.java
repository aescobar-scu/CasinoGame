package casino;

import java.awt.*;
import javax.swing.*;

/**
 * Casino game play screen.
 * 
 * Displays the players name (or nickname), current bet selection (pulldown),
 * current bet amount in $$, and account balance (in $$). 
 * 
 * TBD, also display the last bet, bet $$, and payoff amount? 
 * Will see what fits well on the screen.
 * 
 * Allow maximum of 6 players at any time (tbd, only limited by screen layout
 * constraints)
 * 
 * Displays the game "spin" button, plus last spin result.
 * Each "spin" will invoke the RouletteGame, locking in current bets, and will
 * return the new game spin result. 
 * 
 * The players accounts are updated automatically (up/down) after the "spin", and current 
 * bets are reset to "pass" and bet amount $ set to 0. The last payout field for 
 * each player is updated (if we have this field)
 * 
 * Exit button will return to the CasinoUIStartup frame.
 * 
 * SAMPLE SCREEN:
 * 
 * 
 * @author tony
 *
 */

public class CasinoUIGamePlayFrame extends JFrame {
	
	CasinoUIGamePlayFrame() {
		super("Casino Game Play");
		this.setSize(900, 800);
		
		
	}

}
