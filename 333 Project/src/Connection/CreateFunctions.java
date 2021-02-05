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
	
	
	public boolean createLocation(String dmUsername, String locationID, String locationName, String locationDescription, String campaignID) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC create_location @dm_username = ?, @locationid = ?,"
					+ "@name = ?, @description = ?, @campaignid = ?");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, dmUsername);
			cs.setInt(2, Integer.parseInt(locationID));
			cs.setString(3, locationName);
			cs.setString(4, locationDescription);
			cs.setInt(5, Integer.parseInt(campaignID));
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
	
	public boolean createCharacter(String dmUsername, String campaignID, String characterID, String characterName, String raceName) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC create_character @dm_username = ?, @campaign_id = ?, @id = ?, "
					+ "@name = ?, @racename = ?");

			
			cs.setString(1, dmUsername);
			cs.setInt(2, Integer.parseInt(campaignID));
			cs.setInt(3, Integer.parseInt(characterID));
			cs.setString(4, characterName);
			cs.setString(5, raceName);
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean createMajorCharacter(String characterID, String characterClass, String level, String hitpoints, String alignment,
			String background, String playerUsername, String name, String raceName, String dmUsername, String campaignID) {
		CallableStatement cs = null;
		try {
			boolean campaign = false;
			String call = "EXEC create_major_character @id = ?, @class = ?, @level = ?, "
					+ "@hitpoints = ?, @alignment = ?, @background = ?, @username = ?, "
					+ "@name = ?, @racename = ?, @dm_username = ?, @campaign_id = ?";
			
			if(dmUsername != null && Integer.parseInt(campaignID) > 0) {
				call += ", @dm_username = ?, @campaign_id = ?";
				campaign = true;
			}
			
			cs = con.prepareCall(call);

			
			cs.setInt(1, Integer.parseInt(characterID));
			cs.setString(2, characterClass);
			cs.setInt(3, Integer.parseInt(level));
			cs.setInt(4, Integer.parseInt(hitpoints));
			cs.setString(5, alignment);
			cs.setString(6, background);
			cs.setString(7, playerUsername);
			cs.setString(8, name);
			cs.setString(9, raceName);
			if(campaign) {
				cs.setString(10, dmUsername);
				cs.setInt(11, Integer.parseInt(campaignID));
			}
			
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean createNotes(String dmUsername, String campaignID, String id, String name, String description) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC create_notes @dm_username = ?, @id = ?, @name = ?, @description = ?, @campaignid = ?");

			
			cs.setString(1, dmUsername);
			cs.setInt(2, Integer.parseInt(id));
			cs.setString(3, name);
			cs.setString(4, description);
			cs.setInt(5, Integer.parseInt(campaignID));
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean createNPC(String dmUsername, String locationID, String id, String name, String raceName, String occupation) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC create_npc @dm_username = ?, @locationid = ?, @id = ?, "
					+ "@name = ?, @racename = ?, @occupation = ?");

			
			cs.setString(1, dmUsername);
			cs.setInt(2, Integer.parseInt(locationID));
			cs.setInt(3, Integer.parseInt(id));
			cs.setString(4, name);
			cs.setString(5, raceName);
			cs.setString(6, occupation);
			cs.execute();


			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean createPlayer(String dmUsername, String campaignID, String playerUsername, String name, String password) {
		CallableStatement cs = null;
		try {
			boolean campaign = false;
			String call = "EXEC create_player @playerusername = ?, "
					+ "@name = ?, @password = ?";
			
			if(dmUsername != null && Integer.parseInt(campaignID) > 0) {
				call += ", @dm_username = ?, @campaign_id = ?"; 
				campaign = true;
			}
			
			cs = con.prepareCall(call);

			
			cs.setString(1, playerUsername);
			cs.setString(2, name);
			cs.setString(3, password);
			
			if(campaign) {
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
	
}
