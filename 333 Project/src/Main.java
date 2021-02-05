import java.util.ArrayList;

import Connection.Backend;
import Connection.DatabaseConnectionService;
import Connection.TestingFunctionsDraft;
import UserInterface.UIMain;

public class Main {

	public static void main(String[] args) {
		//Start Connection
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "DnDatabase30");
		db.connect("jurgenkr", "MyNewPassword13");
		
		TestingFunctionsDraft test = new TestingFunctionsDraft(db);
		Backend back = new Backend(db);
		UIMain ui = new UIMain(back);
		
		
//		System.out.println(test.getFunction());
//		test.addFunction(9, "Remi", "Tiefling");
//		System.out.println(test.getFunction());
//		test.deleteFunction(9);
//		System.out.println(test.getFunction());
//		System.out.println("Character can Cast");
//		ArrayList<ArrayList<String>> characterSpells = test.readCharacterSpells("2", "ansarij", "abcd", "1");
//		for(int i = 0; i < characterSpells.size(); i++) {
//			System.out.println(characterSpells.get(i));
//		}
//		System.out.println();
//		
		System.out.println("Getting Character Information");
		ArrayList<ArrayList<String>> checkCharacter = back.readFunctions().readPlayerCharacterInformation("quicks", -1, -1);
		System.out.println(checkCharacter);
		System.out.println();
		
		System.out.println("Getting Character Spells");
		ArrayList<ArrayList<String>> characterSpells = back.readFunctions().readCharacterSpells("quicks", 6);
		System.out.println(characterSpells);
		System.out.println();
		
		System.out.println("Getting Campaign Character Information");
		ArrayList<ArrayList<String>> characters = back.readFunctions().readCampaignCharacterInformation("ansarij", -1);
		System.out.println(characters);
		System.out.println();
		
		System.out.println("Getting Campaign Notes");
		ArrayList<ArrayList<String>> notes = back.readFunctions().readCampaignNotes("ansarij", -1);
		System.out.println(notes);
		System.out.println();
		
		System.out.println("Getting Campaign Locations");
		ArrayList<ArrayList<String>> locations = back.readFunctions().readCampaignLocations("ansarij", -1, -1, 1);
		System.out.println(locations);
		System.out.println();
//		for(int i = 0; i < character.size(); i++) {
//			System.out.println(character.get(i));
//		}
	}
	
	

}
