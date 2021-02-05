
import Connection.Backend;
import Connection.DatabaseConnectionService;
import UserInterface.UIMain;

public class Main {

	public static void main(String[] args) {
		//Start Connection
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase30");
		db.connect("jurgenkr", "MyNewPassword13");
		
		Backend back = new Backend(db);
		UIMain ui = new UIMain(back);
	}
	
}
