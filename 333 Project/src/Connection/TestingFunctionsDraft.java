package Connection;

import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClassTypes.CharacterSpells;
import ClassTypes.Character;

public class TestingFunctionsDraft {
	private DatabaseConnectionService dbService = null;
	
	public TestingFunctionsDraft(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	//There is no error checking, just to make sure the connection works
	
	public ArrayList<ArrayList<String>> readCharacterSpells(String characterID, String username, String password, String campaignID) {
		ArrayList<ArrayList<String>> characterSpells = new ArrayList<ArrayList<String>>();
		ResultSet rs = null;
		Connection con = this.dbService.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("EXEC read_character_spells @characterid = ?, @username = ?,"
					+ "@campaignid = ?, @password = ?");
			
			ps.setString(1, characterID);
			ps.setString(2, username);
			ps.setString(3, campaignID);
			ps.setString(4, password);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				characterSpells.add(new CharacterSpells(rs.getString("ClassName"), rs.getString("RaceName"),
						rs.getString("Name"), rs.getString("Description"), rs.getString("Cast Level")).getItems());
			}
		} catch (SQLException e) {
			System.out.println("oops :(");
//			e.printStackTrace();
		}
		
		return characterSpells;
	}
	
	public ArrayList<ArrayList<Object>> readCharacter(String characterID, String username, String password, String campaignID) {
		ArrayList<ArrayList<Object>> character = new ArrayList<ArrayList<Object>>();
		ResultSet rs = null;
		Connection con = this.dbService.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("EXEC read_player_character @characterid = ?, @username = ?,"
					+ "@password = ?, @campaignid = ?");
			
			
//			Statement statement = this.dbService.getConnection().createStatement();
//			String sql = "SELECT * FROM read_player_character('" + pusername + "', '" + charname + "')";
//			set = statement.executeQuery(sql);
			while (rs.next()) {
				character.add(new Character(set.getString("CharName"), set.getString("Class_ClassName"),
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
	
	
	
	
	
	
	
	
	public ArrayList<String> getFunction() {
		ArrayList<String> test = new ArrayList<String>();
		ResultSet set = null;
		try {
			Statement statement = this.dbService.getConnection().createStatement();
			String sql = "SELECT ID, Name, RaceName FROM Character";
			set = statement.executeQuery(sql);
			while (set.next()) {
				test.add(set.getString("Name"));
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return test;
	}
	
	public boolean addFunction(int id, String name, String raceName) {
		Connection con = this.dbService.getConnection();
		CallableStatement cs = null;
		
		try {
			cs = con.prepareCall("INSERT INTO Character (ID, Name, RaceName) VALUES (?, ?, ?)");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.setString(3, raceName);
			cs.execute();
			cs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteFunction(int id) {
		Connection con = this.dbService.getConnection();
		CallableStatement cs = null;
		
		try {
			cs = con.prepareCall("DELETE FROM Character WHERE ID = ?");
			cs.setInt(1, id);
			cs.execute();
			cs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
