package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DeleteTabs {

public DeleteTabs(JTabbedPane tabs) {
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
	// TODO Auto-generated method stub
	
	}

	private void initilizeCampaignView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		
	}

	private void initilizeNPCView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		
	}

	private void initilizeLocationView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		
	}
}
