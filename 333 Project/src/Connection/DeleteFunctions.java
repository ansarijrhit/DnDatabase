package Connection;

import java.sql.Connection;

public class DeleteFunctions {
	
	private Connection con = null;
	
	public DeleteFunctions(Connection con) {
		this.con = con;
	}
	
	public boolean deleteLocation(String username, int locId) {
		return true;
	}
	
	public boolean deleteMajorCharacter(String username, int charId) {
		return true;
	}
	
	public boolean deleteNote(String username, int noteId) {
		return true;
	}
	
	public boolean deleteNPC(String username, int NPCId) {
		return true;
	}
	
	public boolean deleteCampagin(String username, int campId) {
		return true;
	}
	
	public boolean deleltePlayer(String username) {
		return true;
	}
}
