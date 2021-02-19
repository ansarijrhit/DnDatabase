import com.sun.jdi.connect.spi.Connection;

import Connection.Backend;
import Connection.DataImport;
import Connection.DatabaseConnectionService;
import Connection.UserService; 

public class ImportAll {
	public static void main(String[] args) {
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase_test30");
		db.connect("DnDatabaseUser30", "KatiJordanOlivia30");
		
		Backend back = new Backend(db);
		UserService us = new UserService(back.getConnection());
		
		DataImport importer = new DataImport(back.getConnection(), us);
		
		importer.importSpells("Spreadsheets\\5eSpells.xlsx");
		importer.importClasses("Spreadsheets\\5eClasses.xlsx");
		for(int i = 0; i < 8; i++) {
			importer.importClassCanCast("Spreadsheets\\5eClassSpells.xlsx", i);
		}
		importer.importRaces("Spreadsheets\\5eRaces.xlsx");
		importer.importRaceCanCast("Spreadsheets\\5eRaceSpells.xlsx");
		importer.importCampaign("Spreadsheets\\Sample Campaign.xlsx", "Imported Campaign");
	}
}
