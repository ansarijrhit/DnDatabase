package ClassTypes;

import java.util.ArrayList;

public class CharacterSpells {

	private String className, raceName, name, description, castLevel = null;
	
	
	public CharacterSpells(String className, String raceName, String name, String description, String castLevel) {
		this.className = className;
		this.raceName = raceName;
		this.name = name;
		this.description = description;
		this.castLevel = castLevel;
	}
	
	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.className);
		list.add(this.raceName);
		list.add(this.name);
		list.add(this.description);
		list.add(this.castLevel);
		return list;
	}
	
	
}
