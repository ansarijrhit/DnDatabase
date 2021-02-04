package UserInterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ReadTabs {

	public ReadTabs(JTabbedPane tabs) {
		
		JPanel character = new JPanel(new GridBagLayout());
		initilizeCharacterView(character, new GridBagConstraints());
		character.setVisible(true);
		tabs.addTab("Character", character);
		
		JPanel spells = new JPanel(new GridBagLayout());
		initilizeSpellsView(spells, new GridBagConstraints());
		spells.setVisible(false);
		tabs.addTab("Spells", spells);
		
		JPanel campaignNotes = new JPanel(new GridBagLayout());
		initilizeCampaignNotesView(campaignNotes, new GridBagConstraints());
		campaignNotes.setVisible(false);
		tabs.addTab("Campaign Notes", campaignNotes);
		
		JPanel campaignCharacter = new JPanel(new GridBagLayout());
		initilizeCampaignCharacterView(campaignCharacter, new GridBagConstraints());
		campaignCharacter.setVisible(false);
		tabs.addTab("Campaign Characters", campaignCharacter);
		
		JPanel campaignLocations = new JPanel(new GridBagLayout());
		initilizeCampaignLocationsView(campaignLocations, new GridBagConstraints());
		campaignCharacter.setVisible(false);
		tabs.addTab("Campaign Locations/NPCs", campaignLocations);
	}

	private void initilizeCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<String> characterIds = new JComboBox<String>(UIMain.getCharacterIdsForPlayer());
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
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
		JTable results = new JTable(getCharacterViewData(), new String[] {"Character Name", "Attribute 1", "Attribute 2", "Attribute 3"});
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
	}
	
	private void initilizeSpellsView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JCheckBox checkBox = new JCheckBox();
		c.insets = new Insets(0,0,0,5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Filter to currently available spells"), c);
		c.gridx = 1;
		panel.add(checkBox, c);
		c.insets = new Insets(0,100,0,0);
		c.gridx = 4;
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				initilizeSpellsView(panel, new GridBagConstraints());	
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 5;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getSpellsViewData(), new String[] {"Spell Name", "Description", "Cast Level", "Class", "Race"}) {
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);	
	}
	
	private void initilizeCampaignNotesView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
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
				initilizeCampaignNotesView(panel, new GridBagConstraints());	
			}
		});
		panel.add(search, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 10;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCampaignNotesData(), new String[] {"CampaignID", "Note Name", "Description"}){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		results.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+3)));
		panel.add(scroll, c);
	}
	
	private void initilizeCampaignCharacterView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
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
				initilizeCampaignCharacterView(panel, new GridBagConstraints());	
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 5;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCampaignCharacterViewData(), new String[] {"Character Name", "Attribute 1", "Attribute 2", "Attribute 3"});
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
	}
	
	private void initilizeCampaignLocationsView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.revalidate();
		JComboBox<String> campaignIds = new JComboBox<String>(UIMain.getCampaignIdsForPlayer());
		JComboBox<String> locationIds = new JComboBox<String>(UIMain.getLocationIdsForDM());
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
				initilizeCampaignLocationsView(panel, new GridBagConstraints());	
			}
		});
		panel.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth= 7;
		c.insets = new Insets(10,0,0,0);
		JTable results = new JTable(getCampaignLocationViewData(), UIMain.getCampaignLocationViewHeaders());
		JScrollPane scroll = new JScrollPane(results);
		Dimension d = results.getPreferredSize();
		scroll.setPreferredSize(new Dimension(d.width, (results.getRowHeight())*Math.min(25, results.getRowCount()+2)));
		panel.add(scroll, c);
	}

	private Object[][] getCampaignNotesData() {
		return new String[][] {{"Campaign ID", "Name", "This is a descripiton field"}};
	}

	private String[][] getSpellsViewData() {
		return new String[][] {{"Name", "fact", "fact","fact","fact"}};
	}
	
	private String[][] getCampaignLocationViewData() {
		return new String[][] 
			  {{"Name", "fact", "fact","fact","fact"}};
	}

	private String[][] getCharacterViewData() {
		return new String[][] {{"Name", "fact", "fact","fact"}};
	}
	
	private String[][] getCampaignCharacterViewData() {
		return new String[][] {{"Name", "fact", "fact","fact"}};
	}
}
