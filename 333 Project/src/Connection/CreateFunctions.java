package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ClassTypes.PlayerCharacter;

public class CreateFunctions {

	private Connection con = null;

	public CreateFunctions(Connection con) {
		this.con = con;
	}

	public boolean createLocation(String dmUsername, String locationName, String locationDescription,
			String campaignID) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall(
					"EXEC create_location @dm_username = ?," + "@name = ?, @description = ?, @campaignid = ?");

//			cs.registerOutParameter(1, Types.INTEGER);

			cs.setString(1, dmUsername);
			cs.setString(2, locationName);
			cs.setString(3, locationDescription);
			cs.setInt(4, Integer.parseInt(campaignID));
			cs.execute();

//			int returnValue = cs.getInt(1);

//			while (rs.next()) {
//				character.add(new PlayerCharacter(rs.getString("CharName"), rs.getString("Class_ClassName"), 
//						Integer.parseInt(rs.getString("HasClass_Level")), Integer.parseInt(rs.getString("Hitpoints")), 
//						rs.getString("Alignment"), rs.getString("Background")).getItems());
//			}
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean createMajorCharacter(String characterClass, String level, String hitpoints, String alignment,
			String background, String playerUsername, String name, String raceName, String dmUsername,
			String campaignID) {
		CallableStatement cs = null;
		try {
			String call = "EXEC create_major_character @class = ?, @level = ?, @hitpoints = ?, @alignment = ?, @background = ?, @name = ?, @racename = ?, @campaign_id = ?";

			if (dmUsername.isEmpty()) {
				call += ", @playerusername = ?";
				cs = con.prepareCall(call);
				cs.setString(9, playerUsername);
			} else {
				call += ", @dm_username = ?";
				cs = con.prepareCall(call);
				cs.setString(9, dmUsername);
			}
			System.out.println(call);
			cs.setString(1, characterClass);
			cs.setInt(2, Integer.parseInt(level));
			cs.setInt(3, Integer.parseInt(hitpoints));
			cs.setString(4, alignment);
			cs.setString(5, background);
			cs.setString(6, name);
			cs.setString(7, raceName);
			cs.setInt(8, Integer.parseInt(campaignID));

			cs.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Issue with createMajorCharacter");
			e.printStackTrace();
		}

		return false;
	}

	public boolean createNotes(String dmUsername, String campaignID, String name, String description) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC create_notes @dm_username = ?, @name = ?, @description = ?, @campaignid = ?");

			cs.setString(1, dmUsername);
			cs.setString(2, name);
			cs.setString(3, description);
			cs.setInt(4, Integer.parseInt(campaignID));
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean createNPC(String dmUsername, String locationID, String name, String raceName, String occupation) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC create_npc @dm_username = ?, @locationid = ?, "
					+ "@name = ?, @racename = ?, @occupation = ?");

			cs.setString(1, dmUsername);
			cs.setInt(2, Integer.parseInt(locationID));
			cs.setString(3, name);
			cs.setString(4, raceName);
			cs.setString(5, occupation);
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean createPlayer(String dmUsername, String campaignID, String playerUsername, String name,
			String password) {
		CallableStatement cs = null;
		try {
			boolean campaign = false;
			String call = "EXEC create_player @playerusername = ?, " + "@name = ?, @password = ?";

			if (dmUsername != null && Integer.parseInt(campaignID) > 0) {
				call += ", @dm_username = ?, @campaign_id = ?";
				campaign = true;
			}

			cs = con.prepareCall(call);

			cs.setString(1, playerUsername);
			cs.setString(2, name);
			cs.setString(3, password);

			if (campaign) {
				cs.setString(4, dmUsername);
				cs.setInt(5, Integer.parseInt(campaignID));
			}

			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean createCampaign(String dmUsername, String CampaignName) {
		CallableStatement cs = null;
		try {
			String call = "EXEC create_campaign @dm_username = ?, " + "@description = ?";

			cs = con.prepareCall(call);

			cs.setString(1, dmUsername);
			cs.setString(2, CampaignName);

			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
