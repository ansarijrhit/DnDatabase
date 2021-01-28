import Connection.DatabaseConnectionService;
import Connection.TestingFunctionsDraft;

public class Main {

	public static void main(String[] args) {
		//Start Connection
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase30");
		db.connect("jurgenkr", "MyNewPassword13");
		
		TestingFunctionsDraft test = new TestingFunctionsDraft(db);
		System.out.println(test.getFunction());
		test.addFunction(9, "Remi", "Tiefling");
		System.out.println(test.getFunction());
	}

}
