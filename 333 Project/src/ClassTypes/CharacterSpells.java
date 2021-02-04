package ClassTypes;

import java.util.ArrayList;

public class CharacterSpells {

	private String className, raceName, name, description = null;
	private int castLevel = -1;
	
	
	public CharacterSpells(String className, String raceName, String name, String description, int castLevel) {
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
		list.add(String.valueOf(this.castLevel));
		return list;
	}
	
	
}
