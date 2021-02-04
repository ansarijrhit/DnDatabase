package UserInterface;
import java.awt.BorderLayout;
import javax.swing.*;

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
		
		JPanel character = new JPanel();
		character.add(new JLabel("This is the character view"), BorderLayout.CENTER);
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
}