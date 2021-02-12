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
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> npcIds = new JComboBox<Object>(UI.getNPCIdsForDM());
		JComboBox<Object> locations = new JComboBox<Object>(UI.getLocationIdsForDM());
		JTextField occupation = new JTextField(10);
		
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select an NPC to update: "), c);
		c.gridx = 1;
		panel.add(npcIds, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Select a location to move the NPC to: "), c);
		c.gridx = 1;
		panel.add(locations, c);
		c.gridx = 2;
		JButton changeLocation = new JButton("Change Location");
		changeLocation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String npc = (String) npcIds.getSelectedItem();
				String location = (String) locations.getSelectedItem();
				if (location.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a location.");
					return;
				} else if (npc.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a NPC to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to move NPC :" + npc + "\n "
						+ "to location " + location;
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					 boolean success = UI.getBackEnd().getUpdateFunctions().updateNPCLocation(npc, location);
					if (success) {
						JOptionPane.showMessageDialog(panel, "NPC's location was updated.");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update NPC's location.");
					}
				} else {
					return;
				}
				initilizeNPCView(panel, new GridBagConstraints());
			}
		});
		panel.add(changeLocation, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Enter the NPC's new Occupation: "), c);
		c.gridx = 1;
		panel.add(occupation, c);
		c.gridx = 2;
		JButton changeOccupation = new JButton("Change Occupation");
		changeOccupation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String npc = (String) npcIds.getSelectedItem();
				String newOcc = (String) occupation.getText();
				if (newOcc.isBlank()) {
					JOptionPane.showMessageDialog(panel, "Please enter an occupation.");
					return;
				} else if (npc.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a NPC to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to change NPC :" + npc + "'s \n "
						+ "occupation to " + newOcc;
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					boolean success = UI.getBackEnd().getUpdateFunctions().updateNPCOccupation(npc, newOcc);
					if (success) {
						JOptionPane.showMessageDialog(panel, "NPC's occupation was updated.");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update NPC's occupation.");
					}
				} else {
					return;
				}
				initilizeNPCView(panel, new GridBagConstraints());				
			}
		});
		panel.add(changeOccupation, c);
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		
		c.insets = new Insets(10, 25, 10, 25);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select a note to update: "), c);
		c.gridx = 1;
		JComboBox<Object> noteIds = new JComboBox<Object>(UI.getNotesForDM());
		panel.add(noteIds);
		c.gridx = 3;
		JButton getData = new JButton("Get Current Note");
		getData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cNoteId = (String) noteIds.getSelectedItem();
				if (cNoteId.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a Note to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Note:" + cNoteId;
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					updateNoteView(panel, new GridBagConstraints(), cNoteId, UI.getNote());
				} 
				return;
			}
			
		});
		panel.add(getData, c);
	}
	
	private void updateNoteView(JPanel panel, GridBagConstraints c, String noteId, String noteData) {
		panel.removeAll();
		panel.repaint();
		
		c.insets = new Insets(10, 25, 0, 25);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Edit Note: "), c);
		c.gridx = 1;
		JTextArea note = new JTextArea(noteData);
		note.setColumns(30);
		note.setRows(5);
		panel.add(note);
		c.gridx = 2;
		JButton update = new JButton("Update Note");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newNote = note.getText();
				if (newNote.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please enter text to the note field.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Note:" + noteId;
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					 boolean success = UI.getBackEnd().getUpdateFunctions().updateNotes(noteId, noteData);
					if (success) {
						JOptionPane.showMessageDialog(panel, "Note was updated.");
						initilizeNoteView(panel, new GridBagConstraints());
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update note.");
					}
				} else {
					return;
				}
			}
			
		});
		panel.add(update, c);
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
					boolean success = UI.getBackEnd().getUpdateFunctions().updateMajorCharacter(charUpdate, newClass, null, null, UI.getPlayerUsername());
					if (success) {
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
					 boolean success = UI.getBackEnd().getUpdateFunctions().updateMajorCharacter(charUpdate, null, newAlignment, null, UI.getPlayerUsername());
					if (success) {
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
					 boolean success = UI.getBackEnd().getUpdateFunctions().updateMajorCharacter(charUpdate, null, null, newHitpoints, UI.getPlayerUsername());
					if (success) {
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
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> locIds = new JComboBox<Object>(UI.getLocationIdsForDM());
		JComboBox<Object> campIds = new JComboBox<Object>(UI.getCampaignIdsForDM());
		
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Location to update: "), c);
		c.gridx = 1;
		panel.add(locIds, c);
		
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Would you like to update location description: "), c);
		c.gridx = 2;
		JButton updateDescrption = new JButton("Update Description");
		updateDescrption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cLocId = (String) locIds.getSelectedItem();
				if (cLocId.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a Note to update.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Location:" + cLocId;
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					updateLocationDescriptionView(panel, new GridBagConstraints(), cLocId, UI.getNote());
				} 
				return;
			}
			
		});
		panel.add(updateDescrption, c);
		
		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Select campaign to add location to: "), c);
		c.gridx = 1;
		panel.add(campIds, c);
		c.gridx = 2;
		JButton addTo = new JButton("Add to Campaign");
		addTo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String locId = (String) locIds.getSelectedItem();
				String campId = (String) campIds.getSelectedItem();
				if (campId.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a campaign.");
					return;
				} else if (locId.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please select a Location.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to add Location:" + locId + "\n "
						+ "to Campaign " + campId + "?";
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					boolean success = UI.getBackEnd().getUpdateFunctions().updateLocation(locId, "", campId);
					if (success) {
						JOptionPane.showMessageDialog(panel, "Location was added to campaign.");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to add location to campaign.");
					}
				} else {
					return;
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());
			}
			
		});
		panel.add(addTo, c);
	}
	
	private void updateLocationDescriptionView(JPanel panel, GridBagConstraints c, String locId, String locData) {
		panel.removeAll();
		panel.repaint();
		
		c.insets = new Insets(10, 25, 0, 25);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Edit Description: "), c);
		c.gridx = 1;
		JTextArea note = new JTextArea(locData);
		note.setColumns(30);
		note.setRows(5);
		panel.add(note);
		c.gridx = 2;
		JButton update = new JButton("Update Description");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newDescription = note.getText();
				if (newDescription.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please enter text to the note field.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to update Location:" + locId;
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Update", optionType);
				if (result == JOptionPane.OK_OPTION) {
					boolean success = UI.getBackEnd().getUpdateFunctions().updateLocation(locId, newDescription, "");
					if (success) {
						JOptionPane.showMessageDialog(panel, "Location was updated.");
						initilizeLocationView(panel, new GridBagConstraints());
					} else {
						JOptionPane.showMessageDialog(panel,
								"Error: Unable to update location.");
					}
				} else {
					return;
				}
			}
			
		});
		panel.add(update, c);
	}

}
