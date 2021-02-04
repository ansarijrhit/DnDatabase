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
	private Connection con = null;
	
	public Backend(DatabaseConnectionService dbService) {
		this.dbService = dbService;
		this.con = this.dbService.getConnection();
	}
	
	public ArrayList<ArrayList<Object>> readPlayerCharacterInformation(String pusername, String password, 
			int id, int campaignID) {
		ArrayList<ArrayList<Object>> character = new ArrayList<ArrayList<Object>>();
		ResultSet set = null;
		ArrayList<ArrayList<Object>> error = null;
		try {
			String sql = "EXEC read_player_character @username = ?, @password = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			if (id < 0 && campaignID < 0) {
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setString(2, password);
			} else if (id < 0) {
				sql += ", @campaignid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setString(2, password);
				statement.setInt(3, campaignID);
			} else if (campaignID < 0) {
				sql += ", @characterid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setString(2, password);
				statement.setInt(3, id);
			} else {
				sql += ", @characterid = ?, @campaignid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setString(2, password);
				statement.setInt(3, id);
				statement.setInt(4, campaignID);
			}
			set = statement.executeQuery();
			while (set.next()) {
				character.add(new PlayerCharacter(set.getString("CharName"), set.getString("Class_ClassName"),
						set.getInt("HasClass_Level"), set.getInt("Hitpoints"), set.getString("Alignment"), 
						set.getString("BackGround")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Player Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();
		}
		
		if (set == null) {
			return error;
		}
		return character;
	}
	
}