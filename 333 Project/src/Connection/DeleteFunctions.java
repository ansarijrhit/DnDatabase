package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DeleteFunctions {
	
	private Connection con = null;
	
	public DeleteFunctions(Connection con) {
		this.con = con;
	}
	
	public boolean deleteLocation(String username, int locId) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC delete_location @dm_username = ?, @locationid = ?");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, username);
			cs.setInt(2, locId);
			cs.execute();

//			int returnValue = cs.getInt(1);
			
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteMajorCharacter(String username, int charId) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC delete_MajorCharacter @Username = ?, @CharacterID = ?");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, username);
			cs.setInt(2, charId);
			cs.execute();

//			int returnValue = cs.getInt(1);
			
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteNote(String username, int noteId) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC delete_notes @dm_username = ?, @noteid = ?");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, username);
			cs.setInt(2, noteId);
			cs.execute();

//			int returnValue = cs.getInt(1);
			
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteNPC(String username, int NPCId) {
		// CharacterID, CampaignID, Username
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC delete_npc @Username = ?, @Characterid = ?");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, username);
			cs.setInt(2, NPCId);
			cs.execute();

//			int returnValue = cs.getInt(1);
			
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCampagin(String username, int campId) {
		// dm_username, campaign_id
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC delete_campaign @dm_username = ?, @campaign_id = ?");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, username);
			cs.setInt(2, campId);
			cs.execute();

//			int returnValue = cs.getInt(1);
			
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleltePlayer(String username) {
		// Username
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("EXEC delete_player @username");

//			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(1, username);
			cs.execute();

//			int returnValue = cs.getInt(1);
			
//			if(returnValue == 1) {
//				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
//			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
