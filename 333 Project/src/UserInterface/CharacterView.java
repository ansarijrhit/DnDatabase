package UserInterface;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CharacterView {
	private JPanel panel;

	public CharacterView(JPanel panel) {
		this.panel = panel;
		initilizeView();
	}

	private void initilizeView() {
		JComboBox<String> characterIds = new JComboBox<String>(getCharacterIds());
		JComboBox<String> campaignIds = new JComboBox<String>(getCampaignIds());
		panel.add(campaignIds);
		panel.add(characterIds);
	}

	private String[] getCharacterIds() {
		return new String[] {"Char1", "Char2", "Char3"};
 	}
	
	private String[] getCampaignIds() {
		return new String[] {"Camp1", "Camp2", "Camp3"};
 	}

}
