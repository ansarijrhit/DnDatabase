import java.util.ArrayList;

import Connection.DatabaseConnectionService;
import Connection.TestingFunctionsDraft;

public class Main {

	public static void main(String[] args) {
		//Start Connection
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase30");
		db.connect("jurgenkr", "MyNewPassword13");
		
		TestingFunctionsDraft test = new TestingFunctionsDraft(db);
//		System.out.println(test.getFunction());
//		test.addFunction(9, "Remi", "Tiefling");
//		System.out.println(test.getFunction());
//		test.deleteFunction(9);
//		System.out.println(test.getFunction());
		
		ArrayList<ArrayList<String>> characterSpells = test.readCharacterSpells("Wizard", 10, "Tiefling");
		for(int i = 0; i < characterSpells.size(); i++) {
			System.out.println(characterSpells.get(i));
		}
	}
	
	

}
