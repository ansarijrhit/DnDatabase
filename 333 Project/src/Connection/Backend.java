package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ClassTypes.PlayerCharacter;

public class Backend {

	private DatabaseConnectionService dbService;
	private Connection con;
	private ReadFunctions readFunctions;
	private CreateFunctions createFunctions;
//	private UpdateFunctions updateFunctions;
	private DeleteFunctions deleteFunctions;
	
	public Backend(DatabaseConnectionService dbService) {
		this.dbService = dbService;
		this.con = this.dbService.getConnection();
		this.readFunctions =  new ReadFunctions(this.con);
		this.createFunctions = new CreateFunctions(this.con);
//		this.updateFunctions = new UpdateFunctions(this.con);
		this.deleteFunctions = new DeleteFunctions(this.con);
	}
	
	public ReadFunctions getReadFunctions() {
		return this.readFunctions;
	}
	
	public CreateFunctions getCreateFunctions() {
		return this.createFunctions;
	}
	
//	public UpdateFunctions getUpdateFunctions() {
//		return this.updateFunctions;
//	}
	
	public DeleteFunctions getDeleteFunctions() {
		return this.deleteFunctions;
	}
	
	public Connection getConnection() {
		return this.con;
	}

	public boolean enablePlayerView(String playerUsername) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("SELECT dbo.enablePlayerView(?)");
			
			cs.setString(1, playerUsername);
			ResultSet returnSet = cs.executeQuery();

			int returnValue = -1;
			while (returnSet.next()) {
				returnValue = returnSet.getInt(1);
			}
			returnSet.close();
						
			if(returnValue == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Enable Player View");
			System.out.println();
			e.printStackTrace();
			
		}
		return false;
	}
	
	public boolean enableDMView(String playerUsername) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("SELECT dbo.enableDMView(?)");
			
			cs.setString(1, playerUsername);
			ResultSet returnSet = cs.executeQuery();

			int returnValue = -1;
			while (returnSet.next()) {
				returnValue = returnSet.getInt(1);
			}
			returnSet.close();
						
			if(returnValue == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Enable DM View");
			System.out.println();
			e.printStackTrace();
			
		}
		return false;
	}
	
}