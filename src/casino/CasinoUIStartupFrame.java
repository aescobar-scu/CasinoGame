/**
 * Main Casino startup screen.
 * 
 * Allows the player names and account balance to be entered for a new player,
 * or an entered plater to be deleted.
 * 
 * Players are created or deleted as entered, with player Id's created 
 * automatically (and which are internal ref only). 
 * 
 * As players are added or deleted,they are added to the Player list in the Casino
 * class.
 * 
 * At any point, select StartGame, to start the game play screen.
 * 
 * TBD:
 * 1. Game Select option (only Roulette right now) to plan for future expansion?
 * 2.Player Id: 
 * Do we let the players select a nickname and use this as a player Id?
 * 
 * 
 * @author tony
 *
 * SAMPLE SCREEN:
 * 
 */

package casino;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;



public class CasinoUIStartupFrame extends JFrame {	
	private Font defaultFont = new Font("Ariel", Font.PLAIN, 20);
	private Font titleFont = new Font("Ariel", Font.BOLD, 30);
	private Font headingFont = new Font("Ariel", Font.BOLD, 25);
		
	private int hGap = 10;
	private int vGap = 10;
	
	private Casino casino;
	
	/** UI text fields used in the game operation **/
	private JComboBox<String> selectGame;
	private JList<String> currentPlayersList;
	private DefaultListModel<String> currentPlayerListModel = new DefaultListModel<String>();

	// new player text fields
	private JFormattedTextField nameField;
	private JFormattedTextField screenNameField;
	private JFormattedTextField accountField;
	
	CasinoUIStartupFrame(Casino casino) {
		super("WELCOME TO THE COEN 275 CASINO!");	
		this.casino = casino;
		
		// main start panel layout
		Box startPanel = new Box(BoxLayout.Y_AXIS);
		
		// ButtonHandler for PLAY, ADD, REMOVE buttons
		ButtonHandler buttonHandler = new ButtonHandler();
		
		// welcome label at the top
		JLabel welcomeLabel = new JLabel("Welcome to the COEN 275 Casino!");
		welcomeLabel.setFont(titleFont);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startPanel.add(Box.createRigidArea(new Dimension(0,10)));
		startPanel.add(welcomeLabel);
		startPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		// create and add the sub-panels
		JPanel gameSelectPanel = createGameSelectPanel();
		JPanel addNewPlayerPanel = createNewPlayerPanel(buttonHandler);
		Box currentPlayersPanel = createCurrentPlayersPanel(buttonHandler); 
		
		// create the PLAY button
		String buttonName = new String("PLAY");
		JButton playButton = new JButton(buttonName);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.setFont(defaultFont);
		playButton.setActionCommand(buttonName);
		playButton.addActionListener(buttonHandler);
		
		startPanel.add(gameSelectPanel);
		startPanel.add(Box.createRigidArea(new Dimension(0,10)));
		startPanel.add(playButton);
		startPanel.add(Box.createRigidArea(new Dimension(0,100)));
		startPanel.add(addNewPlayerPanel);
		startPanel.add(Box.createRigidArea(new Dimension(0,100)));
		startPanel.add(currentPlayersPanel);
		startPanel.add(Box.createRigidArea(new Dimension(0,100)));
				
		// add the main start panel to the frame
		this.add(startPanel);
		this.pack();
	}
	
	private JPanel createNewPlayerPanel(ActionListener handler) {
		
		int columnSize = 20;

		JPanel newPlayerPanel = new JPanel();
		BoxLayout newPlayerPanelLayout = new BoxLayout(newPlayerPanel, BoxLayout.Y_AXIS);
		newPlayerPanel.setLayout(newPlayerPanelLayout);

		// create all the labels and add to a new label panel
		JLabel headingLabel = new JLabel("NEW PLAYERS");
		headingLabel.setFont(headingFont);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel nameLabel = new JLabel("Full Name");
		nameLabel.setFont(defaultFont);
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel screenNameLabel = new JLabel("Screen Name");
		screenNameLabel.setFont(defaultFont);
		screenNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel accountLabel = new JLabel("Starting Account Balance in $");
		accountLabel.setFont(defaultFont);
		accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// labels 
		JPanel labelPanel = new JPanel(new GridLayout(0,1));
		labelPanel.add(nameLabel);
		labelPanel.add(screenNameLabel);
		labelPanel.add(accountLabel);
		
		// TODO: these text fields should be formatted
		nameField = new JFormattedTextField();
		nameField.setColumns(columnSize);
		nameField.setFont(defaultFont);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		screenNameField = new JFormattedTextField();
		screenNameField.setColumns(columnSize);
		screenNameField.setFont(defaultFont);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		accountField = new JFormattedTextField();
		accountField.setColumns(columnSize);
		accountField.setFont(defaultFont);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// text field vertical grid
		JPanel fieldPanel = new JPanel(new GridLayout(0,1));
		fieldPanel.add(nameField);
		fieldPanel.add(screenNameField);
		fieldPanel.add(accountField);
		
		// side by side panel for the table
		JPanel tablePanel = new JPanel();
		tablePanel.add(labelPanel);
		tablePanel.add(fieldPanel);
		
		// Add Player Button
		String buttonName = new String("ADD");
		JButton addPlayerButton = new JButton(buttonName);
		addPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addPlayerButton.setFont(defaultFont);
		addPlayerButton.setActionCommand(buttonName);
		addPlayerButton.addActionListener(handler);
		
		newPlayerPanel.add(headingLabel);
		newPlayerPanel.add(tablePanel);
		
		newPlayerPanel.add(addPlayerButton);
		newPlayerPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		return newPlayerPanel;
	}
		

	private Box createCurrentPlayersPanel(ActionListener handler) {
		Box currentPlayersPanel = new Box(BoxLayout.Y_AXIS);
		//BoxLayout currentPlayersPanelLayout = new BoxLayout(currentPlayersPanel, BoxLayout.Y_AXIS);
		//currentPlayersPanel.setLayout(currentPlayersPanelLayout);
			
		JLabel headingLabel = new JLabel("CURRENT PLAYERS");
		headingLabel.setFont(headingFont);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// JList of current players, with single selection and delete button
		currentPlayersList = new JList<>(currentPlayerListModel);
		currentPlayersList.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentPlayersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentPlayersList.setLayoutOrientation(JList.VERTICAL);
		currentPlayersList.setFont(defaultFont);
		currentPlayersList.setSelectedIndex(0);
	
		// initialize the list for testing only ...
		// tbd how we want to format and label the list
		//currentPlayerListModel.addElement("Anthony Escobar, aka Tony, $10000.00");
		//currentPlayerListModel.addElement("Hinal Pinchal, aka Hinal, $20000.00");
		//currentPlayerListModel.addElement("Andrew Chen, aka Andrew, $30000.00");
		//currentPlayerListModel.addElement("Juan Wang, aka Juan, $40000.00");
		
		// Remove Player Button
		String buttonName = new String("REMOVE");
		JButton removePlayerButton = new JButton(buttonName);
		removePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removePlayerButton.setFont(defaultFont);
		removePlayerButton.setActionCommand(buttonName);
		removePlayerButton.addActionListener(handler);
		
		currentPlayersPanel.add(headingLabel);
		currentPlayersPanel.add(Box.createRigidArea(new Dimension(0,10)));
		currentPlayersPanel.add(currentPlayersList);
		currentPlayersPanel.add(Box.createRigidArea(new Dimension(0,10)));
		currentPlayersPanel.add(removePlayerButton);
		
		return currentPlayersPanel;
	}
	
	private JPanel createGameSelectPanel() {
		JPanel gameSelectPanel = new JPanel();
		
		JLabel gameLabel = new JLabel("Select Your Game:");
		gameLabel.setFont(defaultFont);
		gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		String[] gameNames = new String[] {"Roulette", "Coming Soon!"};
		selectGame = new JComboBox<>(gameNames);
		selectGame.setFont(defaultFont);
		selectGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		gameSelectPanel.add(gameLabel);
		gameSelectPanel.add(selectGame);
		
		
		return gameSelectPanel;
	}
	
	// Button Listener
	private class ButtonHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String keyId = event.getActionCommand();
			System.out.println("Info: startup key: " + keyId);
			
			switch (keyId) {
				case "PLAY":
					System.out.println("Info: PLAY: ");
					String gameName = selectGame.getItemAt(selectGame.getSelectedIndex());
					boolean validGame = casino.createGame(gameName);
					if (validGame) {
						casino.selectGameFrame(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid game, try again");
					}	
					break;
				case "REMOVE":
					System.out.println("Info: REMOVE");
					// get selected Jlist item and remove it
					String selectedString = currentPlayersList.getSelectedValue();
					int[] selectedIndex = currentPlayersList.getSelectedIndices();
					if (selectedIndex.length == 1) {
						System.out.println("Info: REMOVE " + selectedString + " from list at index: " + selectedIndex[0]);
						currentPlayerListModel.remove(selectedIndex[0]);
						String screenName = selectedString.split(", ")[1];
						System.out.println("Info: REMOVE " + screenName + " from playerList");
						casino.deletePlayer(screenName);
					}
					casino.printPlayerList();
					break;
				case "ADD":
					String name = nameField.getText();
					String screenName = screenNameField.getText();
					if (casino.hasPlayer(screenName)) {
						JOptionPane.showMessageDialog(null, "Player exists, Please remove existing player first");
						break;
					}
					double accountValue = Double.parseDouble(accountField.getText());
					casino.addPlayer(name, screenName, accountValue);
					System.out.println("Info: ADD");
					currentPlayerListModel.addElement(name + ", " + screenName + ", " + accountValue);
					casino.printPlayerList();
					break;
			}
		}
	}
}
