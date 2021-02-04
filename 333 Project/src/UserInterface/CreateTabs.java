package UserInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateTabs {
	public CreateTabs(JTabbedPane tabs) {
		// Create player?
		
		JPanel character = new JPanel(new GridBagLayout());
		initilizeCharacterView(character, new GridBagConstraints());
		character.setVisible(true);
		tabs.addTab("Character", character);
		
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

	private void initilizeCampaignView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		// name, description, campaignID
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		JTextArea description = new JTextArea();
		description.setColumns(30);
		description.setRows(5);
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
		c.insets = new Insets(10,5,0,5);
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
		submit.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeCharacterView(panel, new GridBagConstraints());	
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
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter New Location Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Enter New Location Description: "), c);
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
		submit.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(submit, c);
	}

	private void initilizeCharacterView(JPanel panel, GridBagConstraints c) {
		// campaignID, name, raceName
			// Major: class, level, hitpoints, alignment, background
			// NPC: Ocupation, locationID
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		JTextArea description = new JTextArea("Location Descrption");
		description.setColumns(30);
		description.setRows(5);
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter New Location Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Enter New Location Description: "), c);
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
		submit.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(submit, c);		
	}
}
