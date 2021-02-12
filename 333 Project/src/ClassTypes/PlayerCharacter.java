package ClassTypes;

import java.util.ArrayList;

public class PlayerCharacter {

	private String charName, class_ClassName, alignment, charID, background = null;
	private int hasClass_Level, hitpoints = 0;

	public PlayerCharacter(String charName, String class_ClassName, int charID, int hasClass_Level, int hitpoints,
			String alignment, String background) {
		this.charName = charName;
		this.charID = Integer.toString(charID);
		this.class_ClassName = class_ClassName;
		this.hasClass_Level = hasClass_Level;
		this.hitpoints = hitpoints;
		this.alignment = alignment;
		this.background = background;
	}

	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.charName);
		list.add(this.charID);
		list.add(this.class_ClassName);
		list.add(String.valueOf(this.hasClass_Level));
		list.add(String.valueOf(this.hitpoints));
		list.add(this.alignment);
		list.add(this.background);
		return list;
	}
}
