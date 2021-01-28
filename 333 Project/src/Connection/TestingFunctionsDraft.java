package Connection;

import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClassTypes.CharacterSpells;

public class TestingFunctionsDraft {
	private DatabaseConnectionService dbService = null;
	
	public TestingFunctionsDraft(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	//There is no error checking, just to make sure the connection works
	
	public ArrayList<ArrayList<String>> readCharacterSpells(String characterClass, int classLevel, String race) {
		ArrayList<ArrayList<String>> characterSpells = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		Connection con = this.dbService.getConnection();
		CallableStatement cs = null;
		try {
			Statement statement = this.dbService.getConnection().createStatement();
			String sql = "SELECT * FROM read_character_spells('" + characterClass + "', " + classLevel + 
							", '" + race + "')";
			set = statement.executeQuery(sql);
			while (set.next()) {
				characterSpells.add(new CharacterSpells(set.getString("ClassName"), set.getString("RaceName"),
						set.getString("Name"), set.getString("Description"), set.getString("Cast Level")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return characterSpells;
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
