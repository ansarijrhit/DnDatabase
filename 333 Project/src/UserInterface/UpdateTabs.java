package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		if (enablePlayer) {
			JPanel majorCharacterPlayer = new JPanel(new GridBagLayout());
			initilizeMajorCharacterPlayerView(majorCharacterPlayer, new GridBagConstraints());
			majorCharacterPlayer.setVisible(false);
			this.addTab("Major Character - PC", majorCharacterPlayer);
		}
		
		if (enableDM) {
			JPanel majorCharacterNPC = new JPanel(new GridBagLayout());
			initilizeMajorCharacterNPCView(majorCharacterNPC, new GridBagConstraints());
			majorCharacterNPC.setVisible(false);
			this.addTab("Major Character - NPC", majorCharacterNPC);
			
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
		



		JPanel note = new JPanel(new GridBagLayout());
		initilizeNoteView(note, new GridBagConstraints());
		note.setVisible(false);
		this.addTab("Note", note);

		JPanel npc = new JPanel(new GridBagLayout());
		initilizeNPCView(npc, new GridBagConstraints());
		npc.setVisible(false);
		this.addTab("NPC", npc);
	}

	private void initilizeNPCView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// location tied-to, occupation
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// Description
	}

	private void initilizeMajorCharacterPlayerView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// hitpoints, levelUp, alignment

	}
	
	private void initilizeMajorCharacterNPCView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// hitpoints, levelUp, alignment

	}

	private void initilizeLocationView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// Name description campaign
	}

}
