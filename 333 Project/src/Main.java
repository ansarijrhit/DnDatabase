
import Connection.Backend;
import Connection.DataImport;
import Connection.DatabaseConnectionService;
import Connection.UserService;
import UserInterface.UIMain;

public class Main {

	public static void main(String[] args) {
		//Start Connection
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase30");
		db.connect("DnDatabaseUser30", "KatiJordanOlivia30");
		
		Backend back = new Backend(db);
		UserService us = new UserService(back.getConnection());
		UIMain ui = new UIMain(back, us);
	}
	
}