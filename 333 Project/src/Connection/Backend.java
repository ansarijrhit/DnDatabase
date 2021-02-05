package Connection;

import java.sql.Connection;

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
	
}