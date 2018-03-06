package casino;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

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

	private int hGap = 10;
	private int vGap = 10;
	private Casino my_casino;
	private Game my_game;

	// UI Elements

	// bet fields
	private JComboBox<String> betPlayerSelect;
	private JFormattedTextField betAmountField;
	private JComboBox<String> betTypeSelect;
	private JFormattedTextField betNumberField;

	// current bets
	private JList<String> currentBetsList;
	private DefaultListModel<String> currentBetsListModel = new DefaultListModel<String>();

	// spin result and player results
	JFormattedTextField spinResultField;
	
	// The player results panel which is refreshed after spin
	JPanel combinedDataPanel;
	JLabel screenNameLabel;
	JLabel winLossLabel;
	JLabel accountBalanceLabel;
	
	CasinoUIGamePlayFrame(Game game, Casino casino) {
		super("Casino Game Play");
		my_casino = casino;
		my_game = game;

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

		// The current session players, placing the bet
		List<String> playerList = my_casino.getPlayerScreenNames();
		String[] playerScreenNames = playerList.toArray(new String[playerList.size()]);
		betPlayerSelect = new JComboBox<>(playerScreenNames);
		
		betPlayerSelect.setFont(defaultFont);
		betPlayerSelect.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Amount of the bet in $$
		betAmountField = new JFormattedTextField();
		betAmountField.setColumns(10);
		betAmountField.setFont(defaultFont);
		betAmountField.setAlignmentX(Component.CENTER_ALIGNMENT);


		// The type of Bet
		String[] betTypeNames = new String[] {"Red",
										  "Black",
										  "Evens",
										  "Odds",
										  "Lows",
										  "Highs",
										  "Straight", 	// 1 number
										  "Split",		// 2 numbers
										  "Street",		// 3 numbers
										  "Square",		// 4 numbers
										  "Six Line",	// 6 numbers
										  "Columns",	// set of 12 numbers in a column, 1, 2, or 3
										  "Dozens"};	// set of 12 numbers, 1-12, 13-24, 25-36. Enter 1, 2, or 3

		betTypeSelect = new JComboBox<>(betTypeNames);
		betTypeSelect.setFont(defaultFont);
		betTypeSelect.setAlignmentX(Component.CENTER_ALIGNMENT);

		// The selected numbers for the bet (optional for some bets)
		// from 0 to 36 only, and 1 to 6 numbers.
		// TODO/tbd, maybe infer the numerical bet type from this field?
		betNumberField = new JFormattedTextField();
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
		currentBetsList = new JList<>(currentBetsListModel);
		currentBetsList.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentBetsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentBetsList.setLayoutOrientation(JList.VERTICAL);
		currentBetsList.setFont(defaultFont);
		currentBetsList.setSelectedIndex(0);

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
		screenNameLabel = new JLabel("Screen Name");
		screenNameLabel.setFont(defaultFont);
		screenNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		winLossLabel = new JLabel("Last Win/Loss");
		winLossLabel.setFont(defaultFont);
		winLossLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		accountBalanceLabel = new JLabel("Account Balance");
		accountBalanceLabel.setFont(defaultFont);
		accountBalanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// add table labels to the playerDataPanel in 1 row
		//JPanel playerDataPanel = new JPanel(new GridLayout(0,3));
		JPanel playerDataPanel = createPlayerDataPanel(screenNameLabel, winLossLabel, accountBalanceLabel);
		// Grid layouts always need to be encapsulated into a panel to
		// prevent stretching
		combinedDataPanel = new JPanel();
		combinedDataPanel.add(playerDataPanel);

		playerPanel.add(Box.createRigidArea(new Dimension(0,100)));
		playerPanel.add(headingLabel);
		playerPanel.add(Box.createRigidArea(new Dimension(0,10)));
		playerPanel.add(combinedDataPanel);
		playerPanel.add(Box.createRigidArea(new Dimension(0,10)));

		return playerPanel;
	}

	private JPanel createPlayerDataPanel(JLabel screenNameLabel, JLabel winLossLabel, JLabel accountBalanceLabel) {
		JPanel playerDataPanel = new JPanel(new GridLayout(0,3));
		
		playerDataPanel.add(screenNameLabel);
		playerDataPanel.add(winLossLabel);
		playerDataPanel.add(accountBalanceLabel);
		
		int columnSize = 20;
		List<String> playerDataList = my_casino.getPlayerResultsData();
		for(String playerData : playerDataList) {
			String[] playerDataSplit = playerData.split(",");
			playerDataPanel.add(createPlayerField(playerDataSplit[0], columnSize)); 
			playerDataPanel.add(createPlayerField(playerDataSplit[1], columnSize)); 
			playerDataPanel.add(createPlayerField(playerDataSplit[2], columnSize)); 
		}
		return playerDataPanel;
	}
	
	private void refreshPlayerDataPanel() {
		combinedDataPanel.removeAll();
		JPanel newDataPanel = createPlayerDataPanel(screenNameLabel, winLossLabel, accountBalanceLabel);
		combinedDataPanel.add(newDataPanel);
		combinedDataPanel.revalidate();
		combinedDataPanel.repaint();
	}
	
	private JFormattedTextField createPlayerField(String text, int columnSize) {
		JFormattedTextField field = new JFormattedTextField();
		field.setColumns(columnSize);
		field.setFont(defaultFont);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		field.setText(text);
		
		return field;
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

		spinResultField = new JFormattedTextField();
		spinResultField.setColumns(columnSize);
		spinResultField.setFont(defaultFont);
		spinResultField.setEditable(false);
		spinResultField.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel resultPanel = new JPanel();
		resultPanel.add(spinResultField);

		playGamePanel.add(headingLabel);
		playGamePanel.add(resultLabel);
		playGamePanel.add(resultPanel);
		playGamePanel.add(buttonPanel);

		return playGamePanel;
	}

	// helper to create the wagerString for display
	private String createWagerListString(String screenName, 
								   String wagerType, 
								   double wagerAmount, 
								   List<Integer> wagerContent){
		
		String wagerString = new String();
		wagerString = screenName + ", " + wagerType + ", " + wagerAmount;
		if (wagerContent.size() > 0) {
			String betNumbers = wagerContentListToString(wagerContent);
			wagerString += betNumbers; // betNumbers has the leading comma already
		}
		return wagerString;
	}
	
	// returns a list of bet numbers comma seperated, from a wager list
	private String wagerContentListToString(List<Integer> wagerContent) {
		String betNumbers = new String("");
		for (int number : wagerContent) {
			betNumbers = betNumbers + ", " + number;
		}
		System.out.println("wagerContentListToString: betNumbers result:" + betNumbers);
		return betNumbers;
	}


	// convert comma separated bet numbers string to a wagerContent list
	// and validate the content to the bet type
	private boolean betNumbersToList(String betNumbers, List<Integer> wagerContent) {
		if (!betNumbers.matches(".*\\d+.*")) {
			System.out.println("betNumbersToList: no valid digits:  " + betNumbers);
			return false;
		}
		String[] splitNumbers = betNumbers.split(",");	
		System.out.println("betNumbersToList: betNumbers: "
		+ betNumbers
		+ " split size is: " 
		+ splitNumbers.length);
		
		if (splitNumbers.length == 0) {
			System.out.println("ERROR: betNumbersToList: no valid bet numbers in " + betNumbers);
			return false;
		}
	
		for ( String numberString : splitNumbers ) {
			try {
				int betNumber = Integer.parseInt(numberString.trim());
				System.out.println("betNumbersToList: betNumbers: " + betNumbers + " betNumber: " + betNumber);
				if (betNumber < 0 || betNumber > 36) {
					System.out.println("ERROR: betNumbersToList: invalid bet number" + betNumber);
					return false;
				}
				wagerContent.add(betNumber);
				System.out.println("betNumbersToList: betNumbers: " + betNumbers + " betNumber: " + betNumber);
			} catch (NumberFormatException e) {
				System.out.println("ERROR: betNumbersToList: invalid number format" + numberString);
				return false;
			}

		}
		// the wager content is properly formed and non-zero
		System.out.println("betNumbersToList: created List wagerContent: "
				+ wagerContent
				+ " of length: "
				+ wagerContent.size());
		return true;
	}

	

	private boolean checkWagerContentToWagerType(String wagerType, List<Integer> wagerContent) {
		boolean validation = false;
		
		switch(wagerType) {
		case "Red":
		case "Black":
		case "Evens":
		case "Odds":
		case "Lows":
		case "Highs":
			if (wagerContent.size() == 0)
				validation = true;
			break;
		case "Straight":
			if (wagerContent.size() == 1)
				validation = true;
			break;
		case "Split":
			if (wagerContent.size() == 2)
				validation = true;
			break;
		case "Street":
			if (wagerContent.size() == 3)
				validation = true;
			break;
		case "Square":
			if (wagerContent.size() == 4)
				validation = true;
			break;
		case "Six Line":
			if (wagerContent.size() == 6)
				validation = true;
			break;
		// dozens and columns select a single group, and must be in the range 1-3
		case "Columns":
		case "Dozens":
			if (wagerContent.size() == 1) {
				if (wagerContent.contains(1) ||
					wagerContent.contains(2) ||
					wagerContent.contains(3)) {
					validation = true;
				}
			}
			break;
		}
		return validation;
	}
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String keyId = event.getActionCommand();
			String screenName;
			String wagerType;
			double wagerAmount;
			List<Integer> wagerContent;
			
			switch (keyId) {
				case "PLACE BET":
					System.out.println("Info: game " + keyId);
					// bet type
					wagerType = betTypeSelect.getItemAt(betTypeSelect.getSelectedIndex());
					screenName = betPlayerSelect.getItemAt(betPlayerSelect.getSelectedIndex());
					String betAmount = betAmountField.getText();
					
					if(betAmount.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Invalid bet amount.");
						break;
					}
					
					try {
						wagerAmount = Double.parseDouble(betAmount);
						if(Double.compare(wagerAmount, my_casino.getAccountAmount(screenName)) > 0) {
							JOptionPane.showMessageDialog(null, "Not Enough Money for this bet.");
							break;
						}
						
					} catch (NumberFormatException e) {
						System.out.println("ERROR: wagerAmount: invalid number" + betAmount);
						break;
					}
					
					// The wager content can be null, or holds comma separated integers
					wagerContent = new ArrayList<Integer>();
					String betNumbers = betNumberField.getText();
					
					// true if the bet content is valid
					boolean isValidBetNumbers = betNumbersToList(betNumbers, wagerContent);
					boolean isValidBet = checkWagerContentToWagerType(wagerType, wagerContent);
					
					// create a valid bet with content
					if (isValidBetNumbers && isValidBet) {
						my_casino.addWager(screenName, wagerAmount, wagerType, wagerContent);
					// valid bet with no content
					} else if (isValidBet){
						my_casino.addWager(screenName, wagerAmount, wagerType);
					}
					else {
						System.out.println("ERROR: invalid bet, skipping");
						JOptionPane.showMessageDialog(null, "Invalid bet numbers, try again");
						break;
					}
					// since the bet is OK, we can also add it to the list
					String wagerString = createWagerListString(screenName, wagerType, wagerAmount, wagerContent);
					currentBetsListModel.addElement(wagerString);
					my_casino.printWagerList();
					break;
				case "REMOVE":
					System.out.println("Info: game " + keyId);
					
					if(currentBetsList.isSelectionEmpty()) {
						JOptionPane.showMessageDialog(null, "Please select a bet to remove.");
						break;
					}

					// remove selection from JList
					System.out.println(" currentBetsList selected index: " + currentBetsList.getSelectedIndex());
					System.out.println(" currentBetsList selected value: " + currentBetsList.getSelectedValue());
					int[] selectedIndex = currentBetsList.getSelectedIndices();
					String selectedString = new String(currentBetsList.getSelectedValue());
					
					if (selectedIndex.length == 1) {
						System.out.println("Info: REMOVE " + selectedString + " from list at index: " + selectedIndex[0]);
						currentBetsListModel.remove(selectedIndex[0]);
					}
					// now parse the string and remove from the wagerList
					wagerContent = new ArrayList<Integer>();
					screenName = "";
					wagerAmount = 0;
					wagerType = "";

					String[] wagerArgs = selectedString.split(",");
					for (int i=0; i < wagerArgs.length; i++) {
						switch (i) {
							case 0:
								screenName = wagerArgs[0].trim();
								break;
							case 1:
								wagerType = wagerArgs[1].trim();
								break;
							case 2:
								wagerAmount = Double.parseDouble(wagerArgs[2].trim());
								break;
							default:
								int betNumber = Integer.parseInt(wagerArgs[i].trim());
								wagerContent.add(betNumber);
						}
					}
					my_casino.deleteWager(screenName, wagerAmount, wagerType, wagerContent);
					my_casino.printWagerList();
					break;
				case "SPIN":
					System.out.println("Info: game " + keyId);
					
					if(currentBetsListModel.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No bets on the list to play on.");
						break;
					}
					// run the spin (as a thread if possible)
					my_game.runGame();
					spinResultField.setText(my_game.getGameResultString());
					
					// pay results to player accounts
					my_casino.payWagers();
						
					// clear the wager list
					my_casino.clearWagerList();
					currentBetsListModel.removeAllElements();
					
					// display new player results
					my_casino.printPlayerList();
					my_casino.printSessionResultList();
					refreshPlayerDataPanel();
					break;
				case "EXIT":
					System.out.println("Info: game " + keyId);
					my_casino.selectGameFrame(false);
					break;

			}
		}
	}
}
