package UserInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableModel;

class UIMain{
	
	public UIMain() {
       JFrame frame = new JFrame("My First GUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1000,600);
       
       JTabbedPane tabs = new JTabbedPane();
       getAllowedViews(tabs, frame);
       
       frame.add(tabs);

       frame.setVisible(true);
     }
     
	private void getAllowedViews(JTabbedPane tabs, JFrame frame) {
		
		JPanel character = new JPanel(new GridBagLayout());
		initilizeCharacterView(character, new GridBagConstraints());
		character.setVisible(true);
		tabs.addTab("Character", character);
		
		JPanel spells = new JPanel();
		spells.add(new JLabel("This is the spells view"), BorderLayout.CENTER);
		spells.setVisible(false);
		tabs.addTab("Spells", spells);
		
		JPanel campaignNotes = new JPanel();
		campaignNotes.add(new JLabel("This is the campaignNotes view"), BorderLayout.CENTER);
		campaignNotes.setVisible(false);
		tabs.addTab("Campaign Notes", campaignNotes);
		
		JPanel campaignCharacter = new JPanel();
		campaignCharacter.add(new JLabel("This is the campaignCharacter view"), BorderLayout.CENTER);
		campaignCharacter.setVisible(false);
		tabs.addTab("Campaign Characters", campaignCharacter);
		
		JPanel campaignLocations = new JPanel();
		campaignLocations.add(new JLabel("This is the campaignLocations view"), BorderLayout.CENTER);
		campaignCharacter.setVisible(false);
		tabs.addTab("Campaign Locations", campaignLocations);
	}
	
	private void initilizeCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<String> characterIds = new JComboBox<String>(getCharacterIdsForPlayer());
		JComboBox<String> campaignIds = new JComboBox<String>(getCampaignIdsForPlayer());
		c.insets = new Insets(0,0,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Filter by CharacterID: "), c);
		c.gridx = 1;
		panel.add(characterIds, c);
		c.insets = new Insets(0,10,0,5);
		c.gridx = 2;
		panel.add(new JLabel("Filter by CampaignID: "), c);
		c.gridx = 3;
		c.insets = new Insets(0,5,0,5);
		panel.add(campaignIds, c);
		c.gridx = 4;
		c.insets = new Insets(0,15,0,0);
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 5;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCharacterViewData(), getCharacterViewColumns());
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
		
	}

	private String[] getCharacterViewColumns() {
		return new String[] {"Character Name", "Attribute 1", "Attribute 2", "Attribute 3"};
	}

	private String[][] getCharacterViewData() {
		return new String[][] {{"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"},
							   {"Name", "fact", "fact","fact"}, 
							   {"Name2", "fact", "fact","fact"}};
	}

	private String[] getCharacterIdsForPlayer() {
		return new String[] {"", "Char1", "Char2", "Char3"};
 	}
	
	private String[] getCampaignIdsForPlayer() {
		return new String[] {"", "Camp1", "Camp2", "Camp3"};
 	}
}