package Connection;

import java.sql.Connection;

public class Backend {

	private DatabaseConnectionService dbService = null;
	private Connection con = null;
	
	public Backend(DatabaseConnectionService dbService) {
		this.dbService = dbService;
		this.con = this.dbService.getConnection();
	}
	
	public ReadFunctions readFunctions() {
		return new ReadFunctions(this.con);
	}
	
	public CreateFunctions createFunctions() {
		return new CreateFunctions(this.con);
	}
	
}