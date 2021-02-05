package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DeleteTabs {
	private UIMain UI;
	
	public DeleteTabs(JTabbedPane tabs, UIMain ui) {
		this.UI = ui;
		
		// Delete Player
		
		JPanel location = new JPanel(new GridBagLayout());
		initilizeLocationView(location, new GridBagConstraints());
		location.setVisible(false);
		tabs.addTab("Location", location);
		
		JPanel majorCharacter = new JPanel(new GridBagLayout());
		initilizeMajorCharacterView(majorCharacter, new GridBagConstraints());
		majorCharacter.setVisible(false);
		tabs.addTab("Major Character", majorCharacter);
		
		JPanel note = new JPanel(new GridBagLayout());
		initilizeNoteView(note, new GridBagConstraints());
		note.setVisible(false);
		tabs.addTab("Note", note);
		
		JPanel npc = new JPanel(new GridBagLayout());
		initilizeNPCView(npc, new GridBagConstraints());
		npc.setVisible(false);
		tabs.addTab("NPC", npc);
		
		JPanel campaign = new JPanel(new GridBagLayout());
		initilizeCampaignView(campaign, new GridBagConstraints());
		campaign.setVisible(false);
		tabs.addTab("Campaign", campaign);
	}

	private void initilizeMajorCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> characterIds = new JComboBox<Object>(UI.getCharacterIdsForPlayer());

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Major Character to Delete: "), c);
		c.gridx = 1;
		panel.add(characterIds, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String toDelete = (String) characterIds.getSelectedItem();
				if (toDelete.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please Select a Major Character to Delete.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete: " + toDelete, "Confirm Deletion", optionType);
					if (result == JOptionPane.OK_OPTION) {
						// TODO: Call deletion method
						JOptionPane.showMessageDialog(panel, toDelete + " was removed from database.");
					}
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(delete, c);
	}

	private void initilizeCampaignView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForDM());

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Campaign to Delete: "), c);
		c.gridx = 1;
		panel.add(campaignIds, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String toDelete = (String) campaignIds.getSelectedItem();
				if (toDelete.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please Select a Campaign to Delete.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete: " + toDelete, "Confirm Deletion", optionType);
					if (result == JOptionPane.OK_OPTION) {
						// TODO: Call deletion method
						JOptionPane.showMessageDialog(panel, toDelete + " was removed from database.");
					}
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(delete, c);
	}

	private void initilizeNPCView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> npcs = new JComboBox<Object>(UI.getNPCIdsForDM());

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select NPC to Delete: "), c);
		c.gridx = 1;
		panel.add(npcs, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String toDelete = (String) npcs.getSelectedItem();
				if (toDelete.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please Select a NPC to Delete.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete: " + toDelete, "Confirm Deletion", optionType);
					if (result == JOptionPane.OK_OPTION) {
						// TODO: Call deletion method
						JOptionPane.showMessageDialog(panel, toDelete + " was removed from database.");
					}
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());		
			}
		});
		panel.add(delete, c);
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> notes = new JComboBox<Object>(UI.getNotesForDM());

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Note to Delete: "), c);
		c.gridx = 1;
		panel.add(notes, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String toDelete = (String) notes.getSelectedItem();
				if (toDelete.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please Select a Note to Delete.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete: " + toDelete, "Confirm Deletion", optionType);
					if (result == JOptionPane.OK_OPTION) {
						// TODO: Call deletion method
						JOptionPane.showMessageDialog(panel, toDelete + " was removed from database.");
					}
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());
			}
		});
		panel.add(delete, c);		
	}

	private void initilizeLocationView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<Object> locations = new JComboBox<Object>(UI.getLocationIdsForDM());

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Location to Delete: "), c);
		c.gridx = 1;
		panel.add(locations, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String toDelete = (String) locations.getSelectedItem();
				if (toDelete.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please Select a Major Character to Delete.");
				} else {
					int optionType = JOptionPane.OK_CANCEL_OPTION;
					int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete: " + toDelete, "Confirm Deletion", optionType);
					if (result == JOptionPane.OK_OPTION) {
						// TODO: Call deletion method
						JOptionPane.showMessageDialog(panel, toDelete + " was removed from database.");
					}
				}
				initilizeMajorCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(delete, c);	
	}
}
