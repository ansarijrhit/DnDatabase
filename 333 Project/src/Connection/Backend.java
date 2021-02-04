package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import ClassTypes.PlayerCharacter;

public class Backend {

	private DatabaseConnectionService dbService = null;
	
	public Backend(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public ArrayList<ArrayList<Object>> readPlayerCharacter(String pusername, String password, int id) {
		ArrayList<ArrayList<Object>> character = new ArrayList<ArrayList<Object>>();
		ResultSet set = null;
		Connection con = this.dbService.getConnection();
		try {
			String sql = "EXEC read_player_character @characterid = ?, @username = ?, @password = ?";
			PreparedStatement statement = this.dbService.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, pusername);
			statement.setString(3, password);
			set = statement.executeQuery();
			while (set.next()) {
				character.add(new PlayerCharacter(set.getString("CharName"), set.getString("Class_ClassName"),
						set.getInt("HasClass_Level"), set.getInt("Hitpoints"), set.getString("Alignment"), 
						set.getString("BackGround")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return character;
	}
}
