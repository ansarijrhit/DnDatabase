package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Connection.Backend;

public class UIMain{
	private static Backend backEnd;
	private static String playerUsername;
	
	public UIMain(Backend b) {
	   backEnd = b;	
       JFrame frame = new JFrame("DnDatabase");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1000,600);
       
       JPanel loginPanel = new JPanel(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
       c.insets = new Insets(5,5,5,5);
       c.gridx = 0;
       c.gridy = 0;
       loginPanel.add(new JLabel("Username: "), c);
       c.gridx = 1;
       JTextField username = new JTextField(10);
       loginPanel.add(username,c);
       c.gridx = 0;
       c.gridy = 1;
       loginPanel.add(new JLabel("Password: "), c);
       c.gridx = 1;
       JTextField password = new JTextField(10);
       loginPanel.add(password,c);
       c.gridx = 0;
       c.gridy = 2;
       c.insets = new Insets(10,20,10,20);
       JButton loginButton = new JButton("Login");
       loginButton.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   playerUsername = username.getText();
    		   String playerPassword = password.getText();
    		   // TODO: Validate username and password
    		   loginPanel.setVisible(false);
    		   displayUserTabs(frame);
    		   frame.revalidate();
    		   frame.repaint();
    	   }
       });
       loginPanel.add(loginButton,c);
       
       c.insets = new Insets(10,35,10,20);
       c.gridx = 1;
       JButton registerButton = new JButton("Register");
       registerButton.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   playerUsername = username.getText();
    		   String playerPassword = password.getText();
    		   // TODO: Validate username and password
    		   loginPanel.setVisible(false);
    		   displayUserTabs(frame);
    		   frame.revalidate();
    		   frame.repaint();
    	   }
       });
       loginPanel.add(registerButton,c);
       
       frame.add(loginPanel);
       frame.setVisible(true);
       
     }

	private void displayUserTabs(JFrame frame) {
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
	}
     
	static String[] getCharacterIdsForPlayer() {
		// TODO: Call procedure
		return new String[] {"", "2", "3", "5", "6"};
 	}
	
	static String[] getCampaignIdsForPlayer() {
		// TODO: Call procedure
		return new String[] {"", "1", "2"};
 	}
	
	static String[] getCampaignIdsForDM() {
		// TODO: Call procedure
		return new String[] {"", "1", "2", "4"};
 	}
	
	static String[] getLocationIdsForDM() {
		// TODO: Call procedure
		return new String[] {"", "1", "3", "4", "21"};
 	}
	
	static String[] getCampaignLocationViewHeaders(boolean showPCs) {
		if (!showPCs) {
			return new String[] {"Location Name", "Location Description"};
		}
		return new String[] {"Location Name", "Location Description", "NPC Name", "NPC Race", "NPC Occupation"};
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
		return new String[] {"", "Wizard", "Bard"};
 	}

	public static String[] getNPCIdsForDM() {
		// TODO: Call procedure
		return new String[] {"", "8", "18", "72"};
	}

	public static String[] getNotesForDM() {
		// TODO: Call procedure
		return new String[] {"", "3", "15", "16"};
	}

	public static String getPlayerUsername() {
		return playerUsername;
	}


	public static Backend getBackEnd() {
		return backEnd;
	}
}