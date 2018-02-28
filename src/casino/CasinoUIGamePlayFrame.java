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
	private DefaultListModel<String> currentBetsListModel = new DefaultListModel<String>();
	
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
		Box betPanel = createBetPanel(buttonHandler);
		Box currentBetsPanel = createCurrentBetsPanel(buttonHandler);
		Box playerResultsPanel = createPlayerResultsPanel(buttonHandler);
		Box playGamePanel = createPlayGamePanel(buttonHandler);
		
		// create the exit button
		String buttonName = new String("EXIT");
		JButton exitButton = new JButton(buttonName);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setFont(defaultFont);
		exitButton.setActionCommand(buttonName);
		exitButton.addActionListener(buttonHandler);
		
		// add the sub-panels
		mainPanel.add(betPanel);
		mainPanel.add(playGamePanel);
		mainPanel.add(currentBetsPanel);
		mainPanel.add(playerResultsPanel);
		mainPanel.add(exitButton);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		this.add(mainPanel);
		this.pack();
	}
	
	private Box createBetPanel(ActionListener handler) {
		int columnSize = 10;
		
		Box betPanel = new Box(BoxLayout.Y_AXIS);
		
		JLabel betLabel = new JLabel("Place Your Bet:");
		betLabel.setFont(defaultFont);
		betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// The player placing the bet
		// use public method String[] Casino.getPlayers();
		String [] playerScreenNames = {"Tony",
								 "Hinal",
								 "Andrew",
								 "Juan"};
		
		JComboBox<String> betPlayerSelect = new JComboBox<>(playerScreenNames);
		betPlayerSelect.setFont(defaultFont);
		betPlayerSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Amount of the bet in $$
		JFormattedTextField betAmountField = new JFormattedTextField();
		betAmountField.setColumns(10);
		betAmountField.setFont(defaultFont);
		betAmountField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		// The type of Bet
		String[] betTypeNames = new String[] {"Reds", 
										  "Blacks",
										  "Evens",
										  "Odds",
										  "Lows (1-18)",
										  "Highs (19-36",
										  "Numerical Bet"};
		
		JComboBox<String> betTypeSelect = new JComboBox<>(betTypeNames);
		betTypeSelect.setFont(defaultFont);
		betTypeSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// The selected numbers for the bet (optional for some bets)
		// from 0 to 36 only, and 1 to 6 numbers.
		// TODO/tbd, maybe infer the numerical bet type from this field?
		JFormattedTextField betNumberField = new JFormattedTextField();
		betNumberField.setColumns(columnSize);
		betNumberField.setFont(defaultFont);
		betNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JPanel betNamePanel = new JPanel(new GridLayout(2,1));
		JLabel playerLabel = new JLabel("Screen Name");
		playerLabel.setFont(defaultFont);
		playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		betNamePanel.add(playerLabel);
		betNamePanel.add(betPlayerSelect);
		
		JPanel betAmountPanel = new JPanel(new GridLayout(2,1));
		JLabel betAmountLabel = new JLabel("Bet Amount $$");
		betAmountLabel.setFont(defaultFont);
		betAmountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		betAmountPanel.add(betAmountLabel);
		betAmountPanel.add(betAmountField);
		
		JPanel betSelectPanel = new JPanel(new GridLayout(2,1));
		JLabel betTypeLabel = new JLabel("Type of Bet");
		betTypeLabel.setFont(defaultFont);
		betTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		betSelectPanel.add(betTypeLabel);
		betSelectPanel.add(betTypeSelect);
		
		JPanel betNumbersPanel = new JPanel(new GridLayout(2,1));
		JLabel betNumbersLabel = new JLabel("Numerical Bet, 0-36");
		betNumbersLabel.setFont(defaultFont);
		betNumbersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		betNumbersPanel.add(betNumbersLabel);
		betNumbersPanel.add(betNumberField);
		
		JPanel betEntryPanel = new JPanel();
		betEntryPanel.add(betNamePanel);
		betEntryPanel.add(betAmountPanel);
		betEntryPanel.add(betSelectPanel);
		betEntryPanel.add(betNumbersPanel);
		
		String buttonName = new String("PLACE BET");
		JButton betButton = new JButton(buttonName);
		betButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		betButton.setFont(defaultFont);
		betButton.setActionCommand(buttonName);
		betButton.addActionListener(handler);
				
		betPanel.add(betLabel);
		betPanel.add(Box.createRigidArea(new Dimension(0,10)));
		betPanel.add(betEntryPanel);
		betPanel.add(Box.createRigidArea(new Dimension(0,10)));
		betPanel.add(betButton);
		betPanel.add(Box.createRigidArea(new Dimension(0,100)));
		
		return betPanel;
	}
	
	private Box createCurrentBetsPanel(ActionListener handler) {
		Box currentBetsPanel = new Box(BoxLayout.Y_AXIS);
			
		JLabel headingLabel = new JLabel("CURRENT BETS");
		headingLabel.setFont(headingFont);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// JList of current players, with single selection and delete button
		JList<String> currentBetsList = new JList<>(currentBetsListModel);
		currentBetsList.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentBetsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentBetsList.setLayoutOrientation(JList.VERTICAL);
		currentBetsList.setFont(defaultFont);
		currentBetsList.setSelectedIndex(0);
	
		// initialize the list for testing only ...
		currentBetsListModel.addElement("Tony		$10.00	11");
		currentBetsListModel.addElement("Tony		$100.00	Reds");
		currentBetsListModel.addElement("Hinal	$10.00	3,12,32,36");
		currentBetsListModel.addElement("Andrew	$50.00	Evens");
		currentBetsListModel.addElement("Juan		$1.00	12");
		currentBetsListModel.addElement("Juan		$100.00	High");
		
		// Remove Player Button
		String buttonName = new String("REMOVE");
		JButton removeBetButton = new JButton(buttonName);
		removeBetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeBetButton.setFont(defaultFont);
		removeBetButton.setActionCommand(buttonName);
		removeBetButton.addActionListener(handler);
		
		//currentBetsPanel.add(Box.createRigidArea(new Dimension(0,100)));
		currentBetsPanel.add(headingLabel);
		currentBetsPanel.add(Box.createRigidArea(new Dimension(0,10)));
		currentBetsPanel.add(currentBetsList);
		currentBetsPanel.add(Box.createRigidArea(new Dimension(0,10)));
		currentBetsPanel.add(removeBetButton);
		
		return currentBetsPanel;
	}
	
	private Box createPlayerResultsPanel(ActionListener handler) {
		
		int columnSize = 20;

		Box playerPanel = new Box(BoxLayout.Y_AXIS);
		// create all the labels and add to a new label panel
		JLabel headingLabel = new JLabel("PLAYER RESULTS");
		headingLabel.setFont(headingFont);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// the table labels 
		JLabel screenNameLabel = new JLabel("Screen Name");
		screenNameLabel.setFont(defaultFont);
		screenNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel winLossLabel = new JLabel("Last Win/Loss");
		winLossLabel.setFont(defaultFont);
		winLossLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel accountBalanceLabel = new JLabel("Account Balance");
		accountBalanceLabel.setFont(defaultFont);
		accountBalanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// add table labels to the playerDataPanel in 1 row
		JPanel playerDataPanel = new JPanel(new GridLayout(0,3));
		playerDataPanel.add(screenNameLabel);
		playerDataPanel.add(winLossLabel);
		playerDataPanel.add(accountBalanceLabel);
		
		// Now add the player data to the panel 1 row at a time
		// 	Create using a for loop and current players list at initialization
		// 	TBD - how will I name and reference each users account and winLoss?
		// screen name
		
		JFormattedTextField screenNameField = new JFormattedTextField();
		screenNameField.setColumns(columnSize);
		screenNameField.setFont(defaultFont);
		screenNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		// win/loss amount
		JFormattedTextField winLossField = new JFormattedTextField();
		winLossField.setColumns(columnSize);
		winLossField.setFont(defaultFont);
		winLossField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JFormattedTextField accountBalanceField = new JFormattedTextField();
		accountBalanceField.setColumns(columnSize);
		accountBalanceField.setFont(defaultFont);
		accountBalanceField.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		// add each row to the playerDatapanel
		playerDataPanel.add(screenNameField);
		playerDataPanel.add(winLossField);
		playerDataPanel.add(accountBalanceField);

		// Grid layouts always need to be encapsulated into a panel to 
		// prevent stretching
		JPanel combinedDataPanel = new JPanel();
		combinedDataPanel.add(playerDataPanel);
		
		playerPanel.add(Box.createRigidArea(new Dimension(0,100)));
		playerPanel.add(headingLabel);
		playerPanel.add(Box.createRigidArea(new Dimension(0,10)));
		playerPanel.add(combinedDataPanel);
		playerPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		return playerPanel;
	}

	private Box createPlayGamePanel(ActionListener handler) {
		int columnSize = 10;
		
		Box playGamePanel = new Box(BoxLayout.Y_AXIS);
		
		// spin button
		String buttonName = new String("SPIN");
		JButton spinButton = new JButton(buttonName);
		spinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		spinButton.setFont(defaultFont);
		spinButton.setActionCommand(buttonName);
		spinButton.addActionListener(handler);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(spinButton);
		
		// result label
		JLabel headingLabel = new JLabel("PLAY ROULETTE");
		headingLabel.setFont(headingFont);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// result label
		JLabel resultLabel = new JLabel("Spin Result");
		resultLabel.setFont(defaultFont);
		resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JFormattedTextField spinResultField = new JFormattedTextField();
		spinResultField.setColumns(columnSize);
		spinResultField.setFont(defaultFont);
		spinResultField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel resultPanel = new JPanel();
		resultPanel.add(spinResultField);
		
		playGamePanel.add(headingLabel);
		playGamePanel.add(resultLabel);
		playGamePanel.add(resultPanel);
		playGamePanel.add(buttonPanel);
		
		return playGamePanel;
	}
	
	private class ButtonHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String keyId = event.getActionCommand();
			
			switch (keyId) {
				case "PLACE BET":
					System.out.println("Info: game " + keyId);
					break;
				case "REMOVE":
					System.out.println("Info: game " + keyId);
					break;
				case "SPIN":
					System.out.println("Info: game " + keyId);
					break;
				case "EXIT":
					System.out.println("Info: game " + keyId);
					Casino.selectGameFrame(false);
					break;		
					
			}
		}
	}
}
