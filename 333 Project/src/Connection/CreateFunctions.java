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
			cs = con.prepareCall("? = EXEC create_location @dm_username = ?, @locationid = ?,"
					+ "@name = ?, @description = ?, @campaignid = ?");

			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(2, dmUsername);
			cs.setInt(3, Integer.parseInt(locationID));
			cs.setString(4, locationName);
			cs.setString(5, locationDescription);
			cs.setInt(6, Integer.parseInt(campaignID));
			cs.execute();

			int returnValue = cs.getInt(1);

			
//			while (rs.next()) {
//				character.add(new PlayerCharacter(rs.getString("CharName"), rs.getString("Class_ClassName"), 
//						Integer.parseInt(rs.getString("HasClass_Level")), Integer.parseInt(rs.getString("Hitpoints")), 
//						rs.getString("Alignment"), rs.getString("Background")).getItems());
//			}
			if(returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: This is not your campaign");
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
