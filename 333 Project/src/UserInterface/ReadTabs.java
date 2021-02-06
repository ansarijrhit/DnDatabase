package UserInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ReadTabs extends Tabs {
	private UIMain UI;
	private boolean enablePlayer;
	private boolean enableDM;

	public ReadTabs(UIMain ui, boolean enablePlayer, boolean enableDM) {
		this.UI = ui;
		this.enablePlayer = enablePlayer;
		this.enableDM = enableDM;
		createTabs();
	}
	
	protected void createTabs() {
		if (enableDM) {
			JPanel campaignCharacter = new JPanel(new GridBagLayout());
			initilizeCampaignCharacterView(campaignCharacter, new GridBagConstraints());
			campaignCharacter.setVisible(false);
			this.addTab("Campaign Characters", campaignCharacter);
			
			JPanel campaignNotes = new JPanel(new GridBagLayout());
			initilizeCampaignNotesView(campaignNotes, new GridBagConstraints());
			campaignNotes.setVisible(true);
			this.addTab("Campaign Notes", campaignNotes);
			
			JPanel campaignLocations = new JPanel(new GridBagLayout());
			initilizeCampaignLocationsView(campaignLocations, new GridBagConstraints());
			campaignCharacter.setVisible(true);
			this.addTab("Campaign Locations/NPCs", campaignLocations);
		}	
		
		if (enablePlayer) {
			JPanel character = new JPanel(new GridBagLayout());
			initilizeCharacterView(character, new GridBagConstraints());
			character.setVisible(true);
			this.addTab("Character", character);
			
			JPanel spells = new JPanel(new GridBagLayout());
			initilizeSpellsView(spells, new GridBagConstraints());
			spells.setVisible(true);
			this.addTab("Spells", spells);
		}	
		
	}

	private void initilizeCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<Object> characterIds = new JComboBox<Object>(UI.getCharacterIdsForPlayer());
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForPlayer());
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
				String charId = (String) characterIds.getSelectedItem();
				String campId = (String) campaignIds.getSelectedItem();
				panel.remove(5);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth= 5;
				c.insets = new Insets(10,0,0,0);
				JTable results = new JTable(getCharacterViewData(charId, campId), new String[] {"Character Name", "Class", "Class Level", "Hitpoints", "Alignment", "Background"});
				JScrollPane scroll = new JScrollPane(results);
				Dimension d = results.getPreferredSize();
				scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
				panel.add(scroll, c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(search, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 5;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCharacterViewData("",""), new String[] {"Character Name", "Class", "Class Level", "Hitpoints", "Alignment", "Background"});
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
	}
	
	private void initilizeSpellsView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<Object> characters = new JComboBox<Object>(UI.getCharacterIdsForPlayer());
		JCheckBox checkBox = new JCheckBox();
		c.insets = new Insets(0,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Select a Character: "));
		c.gridx = 1;
		panel.add(characters);
		c.gridx = 2;
		panel.add(new JLabel("Filter to currently available spells"), c);
		c.gridx = 3;
		panel.add(checkBox, c);
		c.insets = new Insets(0,25,0,0);
		c.gridx = 4;
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String charId = (String) characters.getSelectedItem();
				boolean showAll = checkBox.isSelected();
				panel.remove(5);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth= 5;
				c.insets = new Insets(10,0,0,0);
				JTable results = new JTable(getSpellsViewData(charId, showAll), new String[] {"ClassName", "Race", "Spell Name", "Description", "Cast Level"});
				JScrollPane scroll = new JScrollPane(results);
				Dimension d = results.getPreferredSize();
				scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
				panel.add(scroll, c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 5;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getSpellsViewData("", false), new String[] {"ClassName", "Race", "Spell Name", "Description", "Cast Level"});
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);	
	}
	
	private void initilizeCampaignNotesView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForDM());
		c.insets = new Insets(0,0,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Filter by CampaignID: "), c);
		c.gridx = 1;
		c.insets = new Insets(0,5,0,5);
		panel.add(campaignIds, c);
		c.gridx = 2;
		c.insets = new Insets(0,15,0,0);
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String campId = (String) campaignIds.getSelectedItem();
				panel.remove(3);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth= 3;
				c.insets = new Insets(10,0,0,0);
				JTable results = new JTable(getCampaignNotesData(campId), new String[] {"CampaignID", "Note Name", "Description"});
				JScrollPane scroll = new JScrollPane(results);
				Dimension d = results.getPreferredSize();
				scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
				panel.add(scroll, c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCampaignNotesData(""), new String[] {"CampaignID", "Note Name", "Description"});

		JScrollPane scroll = new JScrollPane(results);
		results.setPreferredScrollableViewportSize(scroll.getPreferredSize());
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+3)));
		panel.add(scroll, c);
	}
	
	private void initilizeCampaignCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForDM());
		c.insets = new Insets(0,0,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Filter by CampaignID: "), c);
		c.gridx = 1;
		c.insets = new Insets(0,5,0,5);
		panel.add(campaignIds, c);
		c.gridx = 2;
		c.insets = new Insets(0,15,0,0);
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String campId = (String) campaignIds.getSelectedItem();
				panel.remove(3);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth= 3;
				c.insets = new Insets(10,0,0,0);
				JTable results = new JTable(getCampaignCharacterViewData(campId), new String[] {"Campaign ID", "Player Username", "Character Name", "Race", "Alignment", "Hitpoints", "Background"});
				JScrollPane scroll = new JScrollPane(results);
				Dimension d = results.getPreferredSize();
				scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
				panel.add(scroll, c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 3;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCampaignCharacterViewData(""), new String[] {"Campaign ID", "Player Username", "Character Name", "Race", "Alignment", "Hitpoints", "Background"});
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
	}
	
	private void initilizeCampaignLocationsView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		JComboBox<Object> campaignIds = new JComboBox<Object>(UI.getCampaignIdsForDM());
		JComboBox<Object> locationIds = new JComboBox<Object>(UI.getLocationIdsForDM());
		JCheckBox checkBox = new JCheckBox();
		c.insets = new Insets(0,0,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Filter by CampaignID: "), c);
		c.gridx = 1;
		c.insets = new Insets(0,5,0,5);
		panel.add(campaignIds, c);
		c.gridx = 2;
		panel.add(new JLabel("Filter by LocationID: "), c);
		c.gridx = 3;
		panel.add(locationIds, c);
		c.gridx = 4;
		panel.add(new JLabel("Enable View of PCs"), c);
		c.gridx = 5;
		panel.add(checkBox, c);
		c.gridx = 6;
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				String locId = (String) locationIds.getSelectedItem();
				String campId = (String) campaignIds.getSelectedItem();
				boolean enablePCs = checkBox.isSelected();
				panel.remove(7);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth= 7;
				c.insets = new Insets(10,0,0,0);
				JTable results = new JTable(getCampaignLocationViewData(campId, locId, enablePCs), UI.getCampaignLocationViewHeaders(checkBox.isSelected()));
				JScrollPane scroll = new JScrollPane(results);
				Dimension d = results.getPreferredSize();
				scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
				panel.add(scroll, c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 7;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCampaignLocationViewData("","",false), UI.getCampaignLocationViewHeaders(false));
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
	}

	private String[][] getCampaignNotesData(String campaign) {
		int campId = -1;
		if (!campaign.isEmpty()) {
			campId = Integer.parseInt(campaign);
		}
		ArrayList<ArrayList<String>> results = UI.getBackEnd().getReadFunctions().readCampaignNotes(UI.getPlayerUsername(), campId);
		String[][] stringArray = results.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return stringArray;
	}

	private String[][] getSpellsViewData(String character, boolean showAllPossible) {
		int charId = -1;
		if (!character.isEmpty()) {
			charId = Integer.parseInt(character);
		}
		int showAll = 0;
		if (showAllPossible) {
			showAll = 1;
		}
		ArrayList<ArrayList<String>> results = UI.getBackEnd().getReadFunctions().readCharacterSpells(UI.getPlayerUsername(), charId, showAll);
		String[][] stringArray = results.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return stringArray;
	}
	
	private String[][] getCampaignLocationViewData(String campaign, String location, boolean enableNPCs) {
		int campId = -1;
		if (!campaign.isEmpty()) {
			campId = Integer.parseInt(campaign);
		}
		int locId = -1;
		if (!location.isEmpty()) {
			locId = Integer.parseInt(location);
		}
		int viewNPCs = 0;
		if (enableNPCs) {
			viewNPCs = 1;
		}
		ArrayList<ArrayList<String>> results = UI.getBackEnd().getReadFunctions().readCampaignLocations(UI.getPlayerUsername(), campId, locId, viewNPCs);
		String[][] stringArray = results.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return stringArray;
	}

	private String[][] getCharacterViewData(String character, String campaign) {
		int charId = -1;
		if (!character.isEmpty()) {
			charId = Integer.parseInt(character);
		}
		int campId = -1;
		if (!campaign.isEmpty()) {
			campId = Integer.parseInt(campaign);
		}
		ArrayList<ArrayList<String>> results = UI.getBackEnd().getReadFunctions().readPlayerCharacterInformation(UI.getPlayerUsername(), charId, campId);
		String[][] stringArray = results.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return stringArray;
	}
	
	private String[][] getCampaignCharacterViewData(String campaign) {
		int campId = -1;
		if (!campaign.isEmpty()) {
			campId = Integer.parseInt(campaign);
		}
		ArrayList<ArrayList<String>> results = UI.getBackEnd().getReadFunctions().readCampaignCharacterInformation(UI.getPlayerUsername(), campId);
		String[][] stringArray = results.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return stringArray;
	}

}
