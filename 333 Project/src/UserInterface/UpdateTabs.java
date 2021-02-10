package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class UpdateTabs extends Tabs {
	private UIMain UI;
	private boolean enablePlayer;
	private boolean enableDM;

	public UpdateTabs(UIMain ui, boolean enablePlayer, boolean enableDM) {
		this.UI = ui;
		this.enablePlayer = enablePlayer;
		this.enableDM = enableDM;
		createTabs();
	}

	@Override
	protected void createTabs() {
		JPanel majorCharacter = new JPanel(new GridBagLayout());
		initilizeMajorCharacterView(majorCharacter, new GridBagConstraints());
		majorCharacter.setVisible(false);
		this.addTab("Major Character", majorCharacter);

		if (enableDM) {
			JPanel location = new JPanel(new GridBagLayout());
			initilizeLocationView(location, new GridBagConstraints());
			location.setVisible(false);
			this.addTab("Location", location);

			JPanel note = new JPanel(new GridBagLayout());
			initilizeNoteView(note, new GridBagConstraints());
			note.setVisible(false);
			this.addTab("Note", note);

			JPanel npc = new JPanel(new GridBagLayout());
			initilizeNPCView(npc, new GridBagConstraints());
			npc.setVisible(false);
			this.addTab("NPC", npc);
		}
	}

	private void initilizeNPCView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// location tied-to, occupation
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// Description
	}

	private void initilizeMajorCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> charIds = new JComboBox<Object>(UI.getAllCharacterIds());
		JComboBox<Object> classes = new JComboBox<Object>(UI.getPossibleClasses());
		JComboBox<Object> alignments = new JComboBox<Object>(UI.getPossibleAlignments());

		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter hitpointsFormat = new NumberFormatter(longFormat);
		hitpointsFormat.setValueClass(Long.class); // ensures you will always get a long value
		hitpointsFormat.setAllowsInvalid(false);
		JFormattedTextField hitpoints = new JFormattedTextField(hitpointsFormat);
		hitpoints.setColumns(5);

		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Major Character to update: "), c);
		c.gridx = 1;
		panel.add(charIds, c);

		// Level Up
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Select Class to Level Up: "), c);
		c.gridx = 1;
		panel.add(classes, c);
		c.gridx = 2;
		JButton levelUp = new JButton("Level Up");
		levelUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String charUpdate = (String) charIds.getSelectedItem();
				String newClass = (String) classes.getSelectedItem();
				if (newClass.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select an Class up level up.");
					return;
				} else if (charUpdate.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a character to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Major Character :" + charUpdate + "\n "
						+ "on Class " + newClass + "'s level?";
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					// boolean success =
					// UI.getBackEnd().getUpdateFunctions().updateMajorCharacter(Integer.getInteger(charUpdate),
					// newClass, null, null, UI.getPlayerUsername());
					if (true) {
						JOptionPane.showMessageDialog(panel, "Major Character's Class level was updated.");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update the Major Character's Class level.");
					}
				} else {
					return;
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());
			}
		});
		panel.add(levelUp, c);

		// Change Alignment
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Select New Alignment: "), c);
		c.gridx = 1;
		panel.add(alignments, c);
		c.gridx = 2;
		JButton updateAlignment = new JButton("Update Alignment");
		updateAlignment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String charUpdate = (String) charIds.getSelectedItem();
				String newAlignment = (String) alignments.getSelectedItem();
				if (newAlignment.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select an Alignment.");
					return;
				} else if (charUpdate.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a character to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Major Character:" + charUpdate + "\n "
						+ "to have " + newAlignment + " alignment?";
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					// boolean success =
					// UI.getBackEnd().getUpdateFunctions().updateMajorCharacter(Integer.getInteger(charUpdate),
					// null, newAlignment, null, UI.getPlayerUsername());
					if (true) {
						JOptionPane.showMessageDialog(panel, "Major Character's Alignment was updated.");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update the Major Character's Alignment.");
					}
				} else {
					return;
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());
			}
		});
		panel.add(updateAlignment, c);

		// update hitpoints
		c.gridx = 0;
		c.gridy = 3;
		panel.add(new JLabel("Input New Hit Point Amount: "), c);
		c.gridx = 1;
		panel.add(hitpoints, c);
		c.gridx = 2;
		JButton updateHitpoints = new JButton("Update Hitpoints");
		updateHitpoints.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String charUpdate = (String) charIds.getSelectedItem();
				String newHitpoints = hitpoints.getText();
				if (newHitpoints.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please enter a hitpoint amount.");
					return;
				} else if (charUpdate.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a character to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Major Character :" + charUpdate + "\n "
						+ "on have " + newHitpoints + " hitpoints?";
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					// boolean success =
					// UI.getBackEnd().getUpdateFunctions().updateMajorCharacter(Integer.getInteger(charUpdate),
					// null, null, Integer.getInteger(newHitpoints), UI.getPlayerUsername());
					if (true) {
						JOptionPane.showMessageDialog(panel, "Major Character's hitpoint amount was updated.");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update the Major Character's hitpoints.");
					}
				} else {
					return;
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());
			}
		});
		panel.add(updateHitpoints, c);
	}

	private void initilizeLocationView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// Name description campaign
	}

}
