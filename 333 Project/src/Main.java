import Connection.DatabaseConnectionService;

public class Main {

	public static void main(String[] args) {
		//Start Connection
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase30");
		db.connect("jurgenkr", "MyNewPassword13");
	}

}
