package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClassTypes.CharacterSpells;
import ClassTypes.PlayerCharacter;

public class ReadFunctions{

	
	private Connection con = null;
	
	public ReadFunctions(Connection con) {
		this.con = con;
	}

	public ArrayList<ArrayList<String>> readPlayerCharacterInformation(String pusername, int id, int campaignID) {
		ArrayList<ArrayList<String>> character = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_player_character @username = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			if (id < 0 && campaignID < 0) {
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
			} else if (id < 0) {
				sql += ", @campaignid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setInt(2, campaignID);
			} else if (campaignID < 0) {
				sql += ", @characterid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setInt(2, id);
			} else {
				sql += ", @characterid = ?, @campaignid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setInt(2, id);
				statement.setInt(3, campaignID);
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
		return character;
	}
	
	public ArrayList<ArrayList<String>> readCharacterSpells(String pusername, int id) {
		ArrayList<ArrayList<String>> character = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_character_spells @username = ?, @characterid = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			statement.setString(1, pusername);
			statement.setInt(2, id);
			set = statement.executeQuery();
			while (set.next()) {
				character.add(new CharacterSpells(set.getString("ClassName"), set.getString("RaceName"),
						set.getString("Name"), set.getString("Description"), set.getInt("Cast Level")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Character Spells Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();
			
		}
		return character;
	}
}
