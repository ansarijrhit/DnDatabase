package UserInterface;

import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class CreateTabs {
	public CreateTabs(JTabbedPane tabs) {
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
	
	// name, raceName
		// Major: class, level, hitpoints, alignment, background
		// NPC: Ocupation, locationID	

	private void initilizeNPCView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
		JTextField name = new JTextField(30);
		JComboBox<String> races = new JComboBox<String>(UIMain.getPossibleRaces());
		JTextField occupation = new JTextField(30);
		JComboBox<String> locations = new JComboBox<String>(UIMain.getLocationIdsForDM());

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter Character Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Select Character's Race: "), c);
		c.gridx = 1;
		panel.add(races, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Enter Character's Occupation: "), c);
		c.gridx = 1;
		panel.add(occupation, c);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(new JLabel("Select Character's Location: "), c);
		c.gridx = 1;
		panel.add(locations, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeNPCView(panel, new GridBagConstraints());	
			}
		});
		panel.add(submit, c);
		
		
	}

	private void initilizeMajorCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
		JTextField name = new JTextField(30);
		JComboBox<String> races = new JComboBox<String>(UIMain.getPossibleRaces());
		JComboBox<String> classes = new JComboBox<String>(UIMain.getPossibleClasses());
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); // ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); 
		JFormattedTextField level = new JFormattedTextField(numberFormatter);
		level.setColumns(30);
		JFormattedTextField hitpoints = new JFormattedTextField(numberFormatter);
		hitpoints.setColumns(30);
		
		JComboBox<String> alignment = new JComboBox<String>(UIMain.getPossibleAlignments());
		JTextArea background = new JTextArea();
		background.setColumns(30);
		background.setRows(5);

		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select Campaign to Join: "), c);
		c.gridx = 1;
		panel.add(campaignIds, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Enter Character Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Select Character's Race: "), c);
		c.gridx = 1;
		panel.add(races, c);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(new JLabel("Enter Character's Level: "), c);
		c.gridx = 1;
		panel.add(level, c);
		c.gridx = 0;
		c.gridy = 4;
		panel.add(new JLabel("Enter Character's HitPoints: "), c);
		c.gridx = 1;
		panel.add(hitpoints, c);
		c.gridx = 0;
		c.gridy = 5;
		panel.add(new JLabel("Select Character's Alignment: "), c);
		c.gridx = 1;
		panel.add(alignment, c);
		c.gridx = 0;
		c.gridy = 6;
		panel.add(new JLabel("Enter Character's Background: "), c);
		c.gridx = 1;
		panel.add(background, c);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeMajorCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(submit, c);
		
	}

	private void initilizeCampaignView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();
		JTextField name = new JTextField(30);
		c.insets = new Insets(10,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter Campaign Name: "), c);
		c.gridx = 1;
		panel.add(name, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				initilizeLocationView(panel, new GridBagConstraints());	
			}
		});
		panel.add(submit, c);
	}
}
