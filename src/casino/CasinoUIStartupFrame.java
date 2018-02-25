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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private Font defaultFont = new Font("Ariel", Font.PLAIN, 20);
	private Font titleFont = new Font("Ariel", Font.BOLD, 30);
	private Font headingFont = new Font("Ariel", Font.BOLD, 25);
	
	private DefaultListModel<String> currentPlayerListModel = new DefaultListModel<String>();
	
	private int hGap = 10;
	private int vGap = 10;
	
	CasinoUIStartupFrame() {
		super("WELCOME TO THE COEN 275 CASINO!");
		//this.setSize(900, 800);
		
		// main start panel layout
		JPanel startPanel = new JPanel();
		
		//BoxLayout startPanelLayout = new BoxLayout(startPanel, BoxLayout.Y_AXIS);
		GridLayout startPanelLayout = new GridLayout(0,1);
		startPanel.setLayout(startPanelLayout);
		
		// welcome label at the top
		JLabel welcomeLabel = new JLabel("Welcome to the COEN 275 Casino!");
		welcomeLabel.setFont(titleFont);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startPanel.add(welcomeLabel);
		
		// create and add the sub-panels
		JPanel gameSelectPanel = createGameSelectPanel();
		JPanel addNewPlayerPanel = createNewPlayerPanel();
		JPanel currentPlayersPanel = createCurrentPlayersPanel(); 
		
		startPanel.add(gameSelectPanel);
		startPanel.add(addNewPlayerPanel);
		startPanel.add(currentPlayersPanel);
		
		// add the main start panel to the frame
		this.add(startPanel);
		this.pack();
	}
	
	private JPanel createNewPlayerPanel() {
		
		int columnSize = 20;

		JPanel newPlayerPanel = new JPanel();
		BoxLayout newPlayerPanelLayout = new BoxLayout(newPlayerPanel, BoxLayout.Y_AXIS);
		//GridLayout newPlayerPanelLayout = new GridLayout(0,1);
		//BorderLayout newPlayerPanelLayout = new BorderLayout();
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
		
		JLabel accountLabel = new JLabel("Starting Account Balance in $$");
		screenNameLabel.setFont(defaultFont);
		screenNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// labels 
		JPanel labelPanel = new JPanel(new GridLayout(0,1));
		labelPanel.add(nameLabel);
		labelPanel.add(screenNameLabel);
		labelPanel.add(accountLabel);
		
		// TODO: these text fields should be formatted
		JFormattedTextField nameField = new JFormattedTextField();
		nameField.setColumns(columnSize);
		nameField.setFont(defaultFont);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JFormattedTextField screenNameField = new JFormattedTextField();
		screenNameField.setColumns(columnSize);
		screenNameField.setFont(defaultFont);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JFormattedTextField accountField = new JFormattedTextField();
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
		JButton addPlayerButton = new JButton("ADD");
		addPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addPlayerButton.setFont(defaultFont);
		
		newPlayerPanel.add(headingLabel);
		newPlayerPanel.add(tablePanel);
		newPlayerPanel.add(addPlayerButton);
		
		return newPlayerPanel;
	}
		

	private JPanel createCurrentPlayersPanel() {
		JPanel currentPlayersPanel = new JPanel();
		BoxLayout currentPlayersPanelLayout = new BoxLayout(currentPlayersPanel, BoxLayout.Y_AXIS);
		currentPlayersPanel.setLayout(currentPlayersPanelLayout);
			
		JLabel headingLabel = new JLabel("CURRENT PLAYERS");
		headingLabel.setFont(headingFont);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// JList of current players, with single selection and delete button
		JList<String> currentPlayersList = new JList<>(currentPlayerListModel);
		currentPlayersList.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentPlayersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentPlayersList.setLayoutOrientation(JList.VERTICAL);
		currentPlayersList.setFont(defaultFont);
		currentPlayersList.setSelectedIndex(0);
	
		// initialize the list for testing only ...
		// tbd how we want to format and label the list
		currentPlayerListModel.addElement("Anthony Escobar, aka Tony, $10000.00");
		currentPlayerListModel.addElement("Hinal Pinchal, aka Hinal, $20000.00");
		currentPlayerListModel.addElement("Andrew Chen, aka Andrew, $30000.00");
		currentPlayerListModel.addElement("Juan Wang, aka Juan, $40000.00");
		
		// Remove Player Button
		JButton removePlayerButton = new JButton("REMOVE");
		removePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removePlayerButton.setFont(defaultFont);
		
		currentPlayersPanel.add(headingLabel);
		currentPlayersPanel.add(currentPlayersList);
		currentPlayersPanel.add(removePlayerButton);
		
		return currentPlayersPanel;
	}
	
	private JPanel createGameSelectPanel() {
		JPanel gameSelectPanel = new JPanel();
		
		JLabel gameLabel = new JLabel("Select Your Game:");
		gameLabel.setFont(defaultFont);
		gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		String[] gameNames = new String[] {"Roulette", "Coming Soon!"};
		JComboBox<String> selectGame = new JComboBox<>(gameNames);
		selectGame.setFont(defaultFont);
		selectGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		gameSelectPanel.add(gameLabel);
		gameSelectPanel.add(selectGame);
		
		
		return gameSelectPanel;
	}
	// Button Listener
}
