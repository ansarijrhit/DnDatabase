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
		System.out.println("Character can Cast");
		ArrayList<ArrayList<String>> characterSpells = test.readCharacterSpells("2", "ansarij", "abcd", "1");
		for(int i = 0; i < characterSpells.size(); i++) {
			System.out.println(characterSpells.get(i));
		}
		System.out.println();
		
		System.out.println("Character");
		ArrayList<ArrayList<Object>> character = test.readCharacter("parkhurca", "Dalkhaust");
		for(int i = 0; i < character.size(); i++) {
			System.out.println(character.get(i));
		}
	}
	
	

}
