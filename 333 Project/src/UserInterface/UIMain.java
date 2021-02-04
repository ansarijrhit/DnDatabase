package UserInterface;

import javax.swing.*;

class UIMain{
	
	public UIMain() {
       JFrame frame = new JFrame("DnDatabase");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1000,600);
       
       JTabbedPane createTabs = new JTabbedPane();
       new CreateTabs(createTabs);
       
       JTabbedPane readTabs = new JTabbedPane();
       new ReadTabs(readTabs);
       
       JTabbedPane updateTabs = new JTabbedPane();
       new UpdateTabs(updateTabs);
       
       JTabbedPane deleteTabs = new JTabbedPane();
       new DeleteTabs(deleteTabs);
       
       JTabbedPane CRUDTabs = new JTabbedPane();
       CRUDTabs.addTab("CREATE", createTabs);
       CRUDTabs.addTab("READ", readTabs);
       CRUDTabs.addTab("UPDATE", updateTabs);
       CRUDTabs.addTab("DELETE", deleteTabs);
       frame.add(CRUDTabs);

       frame.setVisible(true);
     }
     
	static String[] getCharacterIdsForPlayer() {
		return new String[] {"", "Character 1", "Character 2", "Character 3"};
 	}
	
	static String[] getCampaignIdsForPlayer() {
		return new String[] {"", "Campaign 1", "Campaign 2", "Campaign 3"};
 	}
	
	static String[] getLocationIdsForDM() {
		return new String[] {"", "Location 1", "Location 2", "Location 3"};
 	}
	
	static String[] getCampaignLocationViewHeaders() {
		return new String[] {"", "LocationID", "LocationName", "Description", "NPCName", "NPC Job"};
 	}
	
	static String[] getPossibleAlignments() {
		return new String[] {"",
							 "Lawful Good", "Lawful Neutral", "Lawful Evil",
							 "Neutral Good", "True Neutral", "Neutral Evil",
							 "Chaotic Good", "Chaotic Neutral", "Chaotic Evil"};
 	}
	
	static String[] getPossibleRaces() {
		return new String[] {"", "Half-Elf", "Human", "Orc", "Dwarf"};
 	}
	
	static String[] getPossibleClasses() {
		return new String[] {"", "Wizard", "Warrior", "Ranger", "Bard"};
 	}
}