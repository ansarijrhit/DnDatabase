package ClassTypes;

import java.util.ArrayList;

public class PlayerCharacter {

	private String charName, class_ClassName, alignment, background = null;
	private int hasClass_Level, hitpoints = 0;
	
	public PlayerCharacter(String charName, String class_ClassName, int hasClass_Level, int hitpoints, 
			String alignment, String background) {
		this.charName = charName;
		this.class_ClassName = class_ClassName;
		this.hasClass_Level = hasClass_Level;
		this.hitpoints = hitpoints;
		this.alignment = alignment;
		this.background = background;
	}
	
	public ArrayList<Object> getItems() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(this.charName);
		list.add(this.class_ClassName);
		list.add(this.hasClass_Level);
		list.add(this.hitpoints);
		list.add(this.alignment);
		list.add(this.background);
		return list;
	}
}
