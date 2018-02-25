package casino;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private Font defaultFont = new Font("Ariel", Font.PLAIN, 20);
	private Font titleFont = new Font("Ariel", Font.BOLD, 30);
	private Font headingFont = new Font("Ariel", Font.BOLD, 25);
	
	private DefaultListModel<String> currentPlayerListModel = new DefaultListModel<String>();
	
	private int hGap = 10;
	private int vGap = 10;
	
	CasinoUIGamePlayFrame() {
		super("Casino Game Play");
		
		// main start panel layout
		Box mainPanel = new Box(BoxLayout.Y_AXIS);
		
		// ButtonHandler for PLAY, ADD, REMOVE buttons
		ButtonHandler buttonHandler = new ButtonHandler();
		
		// welcome label at the top
		JLabel title = new JLabel("Roulette Game Play");
		title.setFont(titleFont);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainPanel.add(title);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
	
		// create the sub-panels
		JPanel betPanel = createBetPanel();
		
		// add the sub-panels
		mainPanel.add(betPanel);
		
		this.add(mainPanel);
		this.pack();
	}
	
	private JPanel createBetPanel() {
		JPanel betPanel = new JPanel();
		
		JLabel betLabel = new JLabel("Place Your Bet:");
		betLabel.setFont(defaultFont);
		betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		String[] betNames = new String[] {"Reds", 
										  "Blacks",
										  "Evens",
										  "Odds",
										  "Lows (1-18)",
										  "Highs (19-36",
										  "1 Number",
										  "2 Number",
										  "3 Number",
										  "4 Number",
										  "6 Number"};
		
		JComboBox<String> betSelect = new JComboBox<>(betNames);
		betSelect.setFont(defaultFont);
		betSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		betPanel.add(betLabel);
		betPanel.add(betSelect);
		
		
		return betPanel;
	}
	
	private class ButtonHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String keyId = event.getActionCommand();
			
			switch (keyId) {
				case "BET":
					System.out.println("Info: game " + keyId);
					break;
				case "REMOVE":
					System.out.println("Info: game " + keyId);
					break;
				case "SPIN":
					System.out.println("Info: game " + keyId);
					break;
				case "QUIT":
					System.out.println("Info: game " + keyId);
					break;		
					
			}
		}
	}

}
