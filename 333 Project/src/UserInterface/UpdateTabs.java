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

public class UpdateTabs {

	public UpdateTabs(JTabbedPane tabs) {
		// TODO: Player Password????
		
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

	}

	private void initilizeNPCView(JPanel npc, GridBagConstraints gridBagConstraints) {
		// TODO Auto-generated method stub
		// location tied-to, occupation
	}

	private void initilizeNoteView(JPanel panel, GridBagConstraints c) {
		// TODO Auto-generated method stub
		// Description
	}

	private void initilizeMajorCharacterView(JPanel majorCharacter, GridBagConstraints gridBagConstraints) {
		// TODO Auto-generated method stub
		// hitpoints, levelUp, alignment
		
	}

	private void initilizeLocationView(JPanel location, GridBagConstraints gridBagConstraints) {
		// TODO Auto-generated method stub
		// Name description campaign
	}
}
