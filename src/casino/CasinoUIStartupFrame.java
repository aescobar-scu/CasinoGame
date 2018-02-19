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
	
	CasinoUIStartupFrame() {
		super("COEN 275 Casino - Player Entry");
		this.setSize(900, 800);
			
		
		JPanel startPanel = new JPanel();
		this.add(startPanel); 
		startPanel.setLayout(new BorderLayout(0, 0));
		JButton toggleFrameButton = new JButton("Toggle View");
		startPanel.add(toggleFrameButton, BorderLayout.SOUTH);
	}
		

	// Button Listener
}
