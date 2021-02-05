package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import ClassTypes.PlayerCharacter;
import Connection.Backend;

public class UIMain{
	private Backend backEnd;
	private String playerUsername;
	
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
       new CreateTabs(createTabs, this);
       
       JTabbedPane readTabs = new JTabbedPane();
       new ReadTabs(readTabs, this);
       
       JTabbedPane updateTabs = new JTabbedPane();
       new UpdateTabs(updateTabs);
       
       JTabbedPane deleteTabs = new JTabbedPane();
       new DeleteTabs(deleteTabs, this);
       
       JTabbedPane CRUDTabs = new JTabbedPane();
       CRUDTabs.addTab("CREATE", createTabs);
       CRUDTabs.addTab("READ", readTabs);
       CRUDTabs.addTab("UPDATE", updateTabs);
       CRUDTabs.addTab("DELETE", deleteTabs);
       frame.add(CRUDTabs);
	}
	
	String[] getCampaignLocationViewHeaders(boolean showPCs) {
		if (!showPCs) {
			return new String[] {"Location Name", "Location Description"};
		}
		return new String[] {"Location Name", "Location Description", "NPC Name", "NPC Race", "NPC Occupation"};
 	}
	
	String[] getPossibleAlignments() {
		return new String[] {"",
							 "Lawful Good", "Lawful Neutral", "Lawful Evil",
							 "Neutral Good", "True Neutral", "Neutral Evil",
							 "Chaotic Good", "Chaotic Neutral", "Chaotic Evil"};
 	}
     
	Object[] getCharacterIdsForPlayer() {
		ArrayList<String> character = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_CharacterIdsForPlayer @username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				character.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Character Ids For Player");
			System.out.println();
			e.printStackTrace();
			
		}
		character.add(0, "");
		return character.toArray();
 	}
	
	Object[] getCampaignIdsForPlayer() {
		ArrayList<String> campaign = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_CampaignIdsForPlayer @username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				campaign.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Campaign Ids For Player");
			System.out.println();
			e.printStackTrace();
			
		}
		campaign.add(0, "");
		return campaign.toArray();
 	}
	
	Object[] getCampaignIdsForDM() {
		ArrayList<String> campaign = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_CampaignIdsForDM @username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				campaign.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Campaign Ids For DM");
			System.out.println();
			e.printStackTrace();
			
		}
		campaign.add(0, "");
		return campaign.toArray();
 	}
	
	Object[] getLocationIdsForDM() {
		ArrayList<String> location = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_LocationIdsForDM @username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				location.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Location Ids For DM");
			System.out.println();
			e.printStackTrace();
			
		}
		location.add(0, "");
		return location.toArray();
 	}
	
	Object[] getNPCIdsForDM() {
		ArrayList<String> npc = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_NPCIdsForDM @username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				npc.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get NPC Ids For DM");
			System.out.println();
			e.printStackTrace();
			
		}
		npc.add(0, "");
		return npc.toArray();
	}

	Object[] getNotesForDM() {
		ArrayList<String> note = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_NoteIdsForDM @username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				note.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Note Ids For DM");
			System.out.println();
			e.printStackTrace();
			
		}
		note.add(0, "");
		return note.toArray();
	}
	
	Object[] getPossibleRaces() {
		ArrayList<String> races = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_RaceNames";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next()) {
				races.add(set.getString(1));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Race Names");
			System.out.println();
			e.printStackTrace();
			
		}
		races.add(0, "");
		return races.toArray();
 	}
	
	Object[] getPossibleClasses() {
		ArrayList<String> classes = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_ClassNames";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next()) {
				classes.add(set.getString(1));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get Class Names");
			System.out.println();
			e.printStackTrace();
			
		}
		classes.add(0, "");
		return classes.toArray();
 	}

	String getPlayerUsername() {
		return playerUsername;
	}


	Backend getBackEnd() {
		return backEnd;
	}
}