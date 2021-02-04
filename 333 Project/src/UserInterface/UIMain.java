package UserInterface;

import javax.swing.*;

import Connection.Backend;

public class UIMain{
	Backend backEnd;
	
	public UIMain(Backend b) {
	   this.backEnd = b;	
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
		// TODO: Call procedure
		return new String[] {"", "Character 1", "Character 2", "Character 3"};
 	}
	
	static String[] getCampaignIdsForPlayer() {
		// TODO: Call procedure
		return new String[] {"", "Campaign 1", "Campaign 2", "Campaign 3"};
 	}
	
	static String[] getCampaignIdsForDM() {
		// TODO: Call procedure
		return new String[] {"", "Campaign 1", "Campaign 2", "Campaign 3"};
 	}
	
	static String[] getLocationIdsForDM() {
		// TODO: Call procedure
		return new String[] {"", "Location 1", "Location 2", "Location 3"};
 	}
	
	static String[] getCampaignLocationViewHeaders(boolean showPCs) {
		// TODO: column names
		if (showPCs) {
			return new String[] {"LocationID", "LocationName", "Description", "NPCName", "NPC Job"};
		}
		return new String[] {"LocationID", "LocationName", "Description", "NPCName", "NPC Job"};
 	}
	
	static String[] getPossibleAlignments() {
		return new String[] {"",
							 "Lawful Good", "Lawful Neutral", "Lawful Evil",
							 "Neutral Good", "True Neutral", "Neutral Evil",
							 "Chaotic Good", "Chaotic Neutral", "Chaotic Evil"};
 	}
	
	static String[] getPossibleRaces() {
		// TODO: Call procedure
		return new String[] {"", "Half-Elf", "Human", "Orc", "Dwarf"};
 	}
	
	static String[] getPossibleClasses() {
		// TODO: Call procedure
		return new String[] {"", "Wizard", "Warrior", "Ranger", "Bard"};
 	}

	public static String[] getNPCIdsForDM() {
		// TODO: Call procedure
		return new String[] {"", "NPC 1", "NPC 2", "NPC 3"};
	}

	public static String[] getNotesForDM() {
		// TODO: Call procedure
		return new String[] {"", "Note 1", "Note 2", "Note 3"};
	}
}