package Connection;

import java.sql.Connection;

public class Backend {

	private DatabaseConnectionService dbService;
	private Connection con;
	private ReadFunctions readFunctions;
	private CreateFunctions createFunctions;
	
	public Backend(DatabaseConnectionService dbService) {
		this.dbService = dbService;
		this.con = this.dbService.getConnection();
		this.readFunctions =  new ReadFunctions(this.con);
		this.createFunctions = new CreateFunctions(this.con);
	}
	
	public ReadFunctions getReadFunctions() {
		return this.readFunctions;
	}
	
	public CreateFunctions getCreateFunctions() {
		return this.createFunctions;
	}
	
}