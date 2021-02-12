package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateFunctions {
	private Connection con = null;

	public UpdateFunctions(Connection con) {
		this.con = con;
	}

	public boolean updateNPCLocation(String campaignID, String npcID, String locationID) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall(
					"EXEC update_npc_location @CampaignID = ?, @NPC_ID = ?, @LocationID = ?");;

			cs.setInt(1, Integer.parseInt(campaignID));
			cs.setInt(2, Integer.parseInt(npcID));
			cs.setInt(3, Integer.parseInt(locationID));
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateNPCOccupation(String npcID, String occupation) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall(
					"EXEC update_npc_occupation @id = ?, @occupation = ?");;

			cs.setInt(1, Integer.parseInt(npcID));
			cs.setString(2, occupation);
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateNotes(String dmUsername, String id, String campaignID, String description) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC update_notes @dm_username = ?, @id = ?, @description = ?, @campaignid = ?");

			cs.setString(1, dmUsername);
			cs.setInt(2, Integer.parseInt(id));
			cs.setString(3, description);
			cs.setInt(4, Integer.parseInt(campaignID));
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean updateLocation(String id, String name, String description) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall(
					"EXEC update_location @id = ?, @name = ?, @description = ?");;

			cs.setInt(1, Integer.parseInt(id));
			cs.setString(2, name);
			cs.setString(3, description);
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateMajorCharacter(String id, String newClass, String alignment, String hitpoints, String username, String campaignID) {
		CallableStatement cs = null;
		try {
			String call = "EXEC update_major_character_best @id = ?, @username = ?";
			int alIndex = 3;
			int hpIndex = 3;
			int cIDIndex = 3;
			if(newClass != null) {
				call += ", @class = ?";
				alIndex++;
				hpIndex++;
				cIDIndex++;
			}
			if(alignment != null) {
				call += ", @alignment = ?";
				hpIndex++;
				cIDIndex++;
			}
			if(hitpoints != null) {
				call += ", @hitpoints = ?";
			}
			if(campaignID != null) {
				call += ", @campaign_id = ?";
			}

			cs = con.prepareCall(call);

			cs.setInt(1, Integer.parseInt(id));
			cs.setString(2, username);
			
			if(newClass != null) {
				cs.setString(3, newClass);
			}
			if(alignment != null) {
				cs.setString(alIndex, alignment);
			}
			if(hitpoints != null) {
				cs.setInt(hpIndex, Integer.parseInt(hitpoints));
			}
			if(campaignID != null) {
				cs.setInt(cIDIndex, Integer.parseInt(campaignID));
			}

			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
