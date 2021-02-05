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

public class CreateTabs {
	private UIMain UI;
	public CreateTabs(JTabbedPane tabs, UIMain ui) {
		this.UI = ui;
		// Create player?

		JPanel majorCharacter = new JPanel(new GridBagLayout());
		initilizeMajorCharacterView(majorCharacter, new GridBagConstraints());
		majorCharacter.setVisible(true);
		tabs.addTab("Major Character", majorCharacter);

		JPanel npc = new JPanel(new GridBagLayout());
		initilizeNPCView(npc, new GridBagConstraints());
		npc.setVisible(true);
		tabs.addTab("NPC", npc);

		JPanel location = new JPanel(new GridBagLayout());
		initilizeLocationView(location, new GridBagConstraints());
		location.setVisible(false);
		tabs.addTab("Location", location);

		JPanel note = new JPanel(new GridBagLayout());
		initilizeNoteView(note, new GridBagConstraints());
		note.setVisible(false);
		tabs.addTab("Note", note);

		JPanel campaign = new JPanel(new GridBagLayout());
		initilizeCampaignView(campaign, new GridBagConstraints());
		campaign.setVisible(false);
		tabs.addTab("Campaign", campaign);
	}

	private void initilizeNPCView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		JComboBox<Object> races = new JComboBox<Object>(UI.getPossibleRaces());
		JTextField occupation = new JTextField(30);
		JComboBox<Object> locations = new JComboBox<Object>(UI.getLocationIdsForDM());

		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter NPC Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Select NPC's Race: "), c);
		c.gridx = 1;
		panel.add(races, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Enter NPC's Occupation: "), c);
		c.gridx = 1;
		panel.add(occupation, c);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(new JLabel("Select NPC's Location: "), c);
		c.gridx = 1;
		panel.add(locations, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = name.getText();
				String cRace = (String) races.getSelectedItem();
				String cOccupation = occupation.getText();
				String cLocation = (String) locations.getSelectedItem();
				if (cName.isBlank() || cRace.isEmpty() || cOccupation.isBlank() || cLocation.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					String confirmString = "Are you sure you want to create NPC:\n" + cName + ", " + cRace + ", "
							+ cOccupation + "\n in Location " + cLocation;
					int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Creation", optionType);
					if (result == JOptionPane.OK_OPTION) {
						boolean success = UI.getBackEnd().getCreateFunctions().createNPC(UI.getPlayerUsername(),
								cLocation, cName, cRace, cOccupation);
						if (success) {
							JOptionPane.showMessageDialog(panel, "NPC was added to the database.");

						} else {
							JOptionPane.showMessageDialog(panel, "Error: Unable to add NPC to the database.");
						}
					} else {
						return;
					}
				}
				initilizeNPCView(panel, new GridBagConstraints());
			}
		});
		panel.add(submit, c);
	}

	private void initilizeMajorCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForPlayer());
		JTextField name = new JTextField(30);
		JComboBox<Object> races = new JComboBox<Object>(UI.getPossibleRaces());
		JComboBox<Object> classes = new JComboBox<Object>(UI.getPossibleClasses());

		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter levelFormat = new NumberFormatter(longFormat);
		levelFormat.setValueClass(Long.class); // ensures you will always get a long value
		levelFormat.setAllowsInvalid(false);
		JFormattedTextField level = new JFormattedTextField(levelFormat);
		level.setColumns(30);
		NumberFormatter hitpointsFormat = new NumberFormatter(longFormat);
		hitpointsFormat.setValueClass(Long.class); // ensures you will always get a long value
		hitpointsFormat.setAllowsInvalid(false);
		JFormattedTextField hitpoints = new JFormattedTextField(hitpointsFormat);
		hitpoints.setColumns(30);

		JComboBox<Object> alignment = new JComboBox<Object>(UI.getPossibleAlignments());
		JTextArea background = new JTextArea();
		background.setColumns(30);
		background.setRows(5);

		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Campaign to Join: "), c);
		c.gridx = 1;
		panel.add(campaignIds, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Enter Major Character Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Select Major Character's Race: "), c);
		c.gridx = 1;
		panel.add(races, c);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(new JLabel("Select Major Character's Class: "), c);
		c.gridx = 1;
		panel.add(classes, c);
		c.gridx = 0;
		c.gridy = 4;
		panel.add(new JLabel("Enter Major Character's Level: "), c);
		c.gridx = 1;
		panel.add(level, c);
		c.gridx = 0;
		c.gridy = 5;
		panel.add(new JLabel("Enter Major Character's HitPoints: "), c);
		c.gridx = 1;
		panel.add(hitpoints, c);
		c.gridx = 0;
		c.gridy = 6;
		panel.add(new JLabel("Select Major Character's Alignment: "), c);
		c.gridx = 1;
		panel.add(alignment, c);
		c.gridx = 0;
		c.gridy = 7;
		panel.add(new JLabel("Enter Major Character's Background: "), c);
		c.gridx = 1;
		panel.add(background, c);
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = name.getText();
				String cRace = (String) races.getSelectedItem();
				String cClass = (String) classes.getSelectedItem();
				String cLevel = level.getText();
				String cHitpoints = hitpoints.getText();
				String cAlignment = (String) alignment.getSelectedItem();
				String cBackground = background.getText();
				String cCampaignID = (String) campaignIds.getSelectedItem();
				if (cCampaignID.isBlank() || cName.isBlank() || cRace.isEmpty() || cClass.isEmpty() || cLevel.isBlank()
						|| cHitpoints.isBlank() || cAlignment.isEmpty() || cBackground.isBlank()) {
					JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					String confirmString = "Are you sure you want to create Major Character:\n" + cName + ", " + cRace
							+ ", " + cClass + ", " + cLevel + ", " + cHitpoints + ", " + cAlignment + ",\n "
							+ cBackground + "\n in Campaign " + cCampaignID;
					int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Creation", optionType);
					if (result == JOptionPane.OK_OPTION) {
						boolean success = UI.getBackEnd().getCreateFunctions().createMajorCharacter(cClass, cLevel,
								cHitpoints, cAlignment, cBackground, UI.getPlayerUsername(), cName, cRace, null,
								cCampaignID);
						if (success) {
							JOptionPane.showMessageDialog(panel, "Major Character was added to the database.");

						} else {
							JOptionPane.showMessageDialog(panel,
									"Error: Unable to add Major Character to the database.");
						}
					} else {
						return;
					}
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());
			}
		});
		panel.add(submit, c);
	}

	private void initilizeCampaignView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter Campaign Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = name.getText();
				if (cName.isBlank()) {
					JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					String confirmString = "Are you sure you want to create campaign:\n " + cName;
					int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Creation", optionType);
					if (result == JOptionPane.OK_OPTION) {
						boolean success = UI.getBackEnd().getCreateFunctions().createCampaign(UI.getPlayerUsername(), cName);
						if (success) {
							JOptionPane.showMessageDialog(panel, "Campaign was added to the database.");

						} else {
							JOptionPane.showMessageDialog(panel,
									"Error: Unable to add Campaign to the database.");
						}
					} else {
						return;
					}
				}
				initilizeCampaignView(panel, new GridBagConstraints());
			}
		});
		panel.add(submit, c);
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		JTextArea description = new JTextArea();
		description.setColumns(30);
		description.setRows(5);
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForDM());
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter Note Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Enter Note: "), c);
		c.gridx = 1;
		panel.add(description, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Select Campaign for Note: "), c);
		c.gridx = 1;
		panel.add(campaignIds, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = name.getText();
				String cDescription = description.getText();
				String cCampaignId = (String) campaignIds.getSelectedItem();
				if (cName.isBlank() || cDescription.isBlank() || cCampaignId.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					String confirmString = "Are you sure you want to create the Note... \n " + cName + ": "
							+ cDescription + "\n on " + cCampaignId + "?";
					int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Creation", optionType);
					if (result == JOptionPane.OK_OPTION) {
						boolean success = UI.getBackEnd().getCreateFunctions().createNotes(UI.getPlayerUsername(), cCampaignId, cName, cDescription);
						if (success) {
							JOptionPane.showMessageDialog(panel, "Note was added to the database.");

						} else {
							JOptionPane.showMessageDialog(panel,
									"Error: Unable to add Note to the database.");
						}
					} else {
						return;
					}
				}
				initilizeNoteView(panel, new GridBagConstraints());
			}
		});
		panel.add(submit, c);
	}

	private void initilizeLocationView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		JTextArea description = new JTextArea();
		description.setColumns(30);
		description.setRows(5);
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForDM());
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter Location Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Enter Location Description: "), c);
		c.gridx = 1;
		panel.add(description, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Select Campaign of Location: "), c);
		c.gridx = 1;
		panel.add(campaignIds, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = name.getText();
				String cDescription = description.getText();
				String cCampaignId = (String) campaignIds.getSelectedItem();
				if (cName.isBlank() || cDescription.isBlank() || cCampaignId.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					String confirmString = "Are you sure you want to create the location... \n " + cName + ": "
							+ cDescription + "\n tied to " + cCampaignId + "?";
					int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Creation", optionType);
					if (result == JOptionPane.OK_OPTION) {
						boolean success = UI.getBackEnd().getCreateFunctions().createLocation(UI.getPlayerUsername(), cName, cDescription, cCampaignId);
						if (success) {
							JOptionPane.showMessageDialog(panel, "Location was added to the database.");

						} else {
							JOptionPane.showMessageDialog(panel,
									"Error: Unable to add Location to the database.");
						}
					} else {
						return;
					}
				}
				initilizeLocationView(panel, new GridBagConstraints());
			}
		});
		panel.add(submit, c);
	}
}
