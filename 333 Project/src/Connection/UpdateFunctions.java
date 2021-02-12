package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateFunctions {
	private Connection con = null;

	public UpdateFunctions(Connection con) {
		this.con = con;
	}

	public boolean updateNPCLocation(String npcID, String locationID) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall(
					"EXEC update_npc_location @NPC_ID = ?, @LocationID = ?");;

			cs.setInt(1, Integer.parseInt(npcID));
			cs.setInt(2, Integer.parseInt(locationID));
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

	public boolean updateNotes(String id, String description) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC update_notes @id = ?, @description = ?");

			cs.setInt(1, Integer.parseInt(id));
			cs.setString(2, description);
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean updateLocation(String id, String name, String description	) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall(
					"EXEC update_location @id = ?, @name = ?, @description = ?");;

			cs.setInt(1, Integer.parseInt(id));
			cs.setString(2, name);
			cs.setString(3, description);
//			String sql = "EXEC update_location @dm_username = ?, @id = ?";
//			if (description.isEmpty()) {
//				sql += ", @campId = ?";
//				cs = con.prepareCall(sql);
//				cs.setInt(1, Integer.parseInt(id));
//				cs.setInt(3, Integer.parseInt(campaignID));
//			} else {
//				sql += ", @description = ?";
//				cs = con.prepareCall(sql);
//				cs.setInt(1, Integer.parseInt(id));
//				cs.setString(2, name);
//				cs.setString(3, description);
//			}
			
			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateMajorCharacter(String id, String newClass, String alignment, String hitpoints, String username) {
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

			cs.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
