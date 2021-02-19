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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ClassTypes.PlayerCharacter;
import Connection.Backend;
import Connection.UserService;

public class UIMain {
	private Backend backEnd;
	private String playerUsername;
	private UserService us;

	public UIMain(Backend b, UserService us) {
		backEnd = b;
		this.us = us;
		JFrame frame = new JFrame("DnDatabase");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);

		JPanel loginPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		loginPanel.add(new JLabel("Login Username: "), c);
		c.gridx = 1;
		JTextField username = new JTextField(10);
		loginPanel.add(username, c);
		c.gridx = 0;
		c.gridy = 1;
		loginPanel.add(new JLabel("Login Password: "), c);
		c.gridx = 1;
//		JTextField password = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		loginPanel.add(password, c);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 20, 10, 20);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerUsername = username.getText();
				String playerPassword = String.valueOf(password.getPassword());//password.getText();
				if (playerUsername.isBlank() || playerPassword.isBlank()) {
					JOptionPane.showMessageDialog(loginPanel, "Please fill in all of the registration fields");
				} else {
					// TODO: Validate username and password
//					us = new UserService(b.getConnection());
					boolean success = us.login(playerUsername, playerPassword);
					// boolean success =
					// backEnd.getCreateFunctions().validatePlayerPass(playerUsername,
					// playerPassword);
					if (success) {
						loginPanel.setVisible(false);
						displayUserTabs(frame, backEnd.enablePlayerView(playerUsername),
								backEnd.enableDMView(playerUsername));
						frame.revalidate();
						frame.repaint();
					} else {
						JOptionPane.showMessageDialog(loginPanel,
								"Error: Unable to complete Registration. Please try again.");
					}
				}
			}
		});
		loginPanel.add(loginButton, c);

		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 0;
		loginPanel.add(new JLabel("Register Username: "), c);
		c.gridx = 3;
		JTextField Rusername = new JTextField(10);
		loginPanel.add(Rusername, c);
		c.gridx = 2;
		c.gridy = 1;
		loginPanel.add(new JLabel("Register Password: "), c);
		c.gridx = 3;
//		JTextField Rpassword = new JTextField(10);
		JPasswordField Rpassword = new JPasswordField(10);
		loginPanel.add(Rpassword, c);
		c.gridx = 2;
		c.gridy = 2;
		loginPanel.add(new JLabel("Register Name: "), c);
		c.gridx = 3;
		JTextField Rname = new JTextField(10);
		loginPanel.add(Rname, c);
		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(10, 35, 10, 20);
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerUsername = Rusername.getText();
				String playerPassword = String.valueOf(Rpassword.getPassword());//Rpassword.getText();
				String playerName = Rname.getText();
				if (playerUsername.isBlank() || playerPassword.isBlank() || playerName.isBlank()) {
					JOptionPane.showMessageDialog(loginPanel, "Please fill in all of the registration fields");
				} else {
//					boolean success = backEnd.getCreateFunctions().createPlayer(playerUsername, playerName,
//							playerPassword);
//					UserService us = new UserService(b.getConnection());
					boolean success = us.register(playerUsername, playerName, playerPassword);
					if (success) {
						JOptionPane.showMessageDialog(loginPanel, "Registration Complete.");
						loginPanel.setVisible(false);
						displayUserTabs(frame, backEnd.enablePlayerView(playerUsername),
								backEnd.enableDMView(playerUsername));
						frame.revalidate();
						frame.repaint();
					} else {
						JOptionPane.showMessageDialog(loginPanel,
								"Error: Unable to complete Registration. Please try a different username.");
					}
				}
			}
		});
		loginPanel.add(registerButton, c);

		frame.add(loginPanel);
		frame.setVisible(true);

	}

	private void displayUserTabs(JFrame frame, boolean enablePlayer, boolean enableDM) {

		Tabs createTabs = new CreateTabs(this, enablePlayer, enableDM);
		Tabs readTabs = new ReadTabs(this, enablePlayer, enableDM);
		Tabs updateTabs = new UpdateTabs(this, enablePlayer, enableDM);
		Tabs deleteTabs = new DeleteTabs(this, enablePlayer, enableDM);
		Tabs accountTabs = new AccountTabs(this, us);

		JTabbedPane CRUDTabs = new JTabbedPane();
		CRUDTabs.addTab("CREATE", createTabs);
		CRUDTabs.addTab("READ", readTabs);
		CRUDTabs.addTab("UPDATE", updateTabs);
		CRUDTabs.addTab("DELETE", deleteTabs);
		CRUDTabs.addTab("ACCOUNT", accountTabs);
		frame.add(CRUDTabs);
		CRUDTabs.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int index = ((JTabbedPane) e.getSource()).getSelectedIndex();
				if (index == 0) {
					createTabs.reInitilize();
				} else if (index == 1) {
					readTabs.reInitilize();
				} else if (index == 2) {
					updateTabs.reInitilize();
				} else if (index == 3) {
					deleteTabs.reInitilize();
				} else {
					accountTabs.reInitilize();
				}
			}

		});
	}

	String[] getCampaignLocationViewHeaders(boolean showPCs) {
		if (!showPCs) {
			return new String[] { "Location ID", "Location Name", "Location Description" };
		}
		return new String[] { "Location ID", "Location Name", "Location Description", "NPC ID", "NPC Name", "NPC Race",
				"NPC Occupation" };
	}

	String[] getPossibleAlignments() {
		return new String[] { "", "Lawful Good", "Lawful Neutral", "Lawful Evil", "Neutral Good", "True Neutral",
				"Neutral Evil", "Chaotic Good", "Chaotic Neutral", "Chaotic Evil" };
	}

	Object[] getCharacterIdsForPlayer() {
		ArrayList<String> character = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_CharacterIdsForPlayer @Username = ?";
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
			String sql = "EXEC get_CampaignIdsForPlayer @Username = ?";
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

	public Object[] getCharacterIdsForUser() {
		ArrayList<String> campaign = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_CharacterIdsForUser @Username = ?";
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

	public Object[] getAllCharacterIds() {
		ArrayList<String> characters = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_AllCharacterIds @Username = ?";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			statement.setString(1, playerUsername);
			set = statement.executeQuery();
			while (set.next()) {
				characters.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get all character Ids");
			System.out.println();
			e.printStackTrace();

		}
		characters.add(0, "");
		return characters.toArray();
	}

	Object[] getAllCampaignIds() {
		ArrayList<String> campaign = new ArrayList<String>();
		ResultSet set = null;
		try {
			String sql = "EXEC get_AllCampaignIds";
			PreparedStatement statement = backEnd.getConnection().prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next()) {
				campaign.add(String.valueOf(set.getInt(1)));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Get All Campaign Ids");
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

	public String getNote(String noteID) {
		return backEnd.getReadFunctions().getNoteDescription(Integer.parseInt(noteID));
	}

	public String getLocationDesription(String cLocId) {
		return backEnd.getReadFunctions().getLocationDescription(Integer.parseInt(cLocId));
	}
}