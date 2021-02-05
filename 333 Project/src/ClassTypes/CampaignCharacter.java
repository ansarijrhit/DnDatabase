package ClassTypes;

import java.util.ArrayList;

public class CampaignCharacter {

	private String username, charName, raceName, alignment, background = null;
	private int campaignID, hitpoints = 0;
	
	public CampaignCharacter(int campaignID, String username, String charName, String raceName, String alignment, int hitpoints, String background) {
		this.campaignID = campaignID;
		this.username = username;
		this.charName = charName;
		this.raceName = raceName;
		this.hitpoints = hitpoints;
		this.alignment = alignment;
		this.background = background;
	}
	
	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(String.valueOf(this.campaignID));
		list.add(this.username);
		list.add(this.charName);
		list.add(this.raceName);
		list.add(this.alignment);
		list.add(String.valueOf(this.hitpoints));
		list.add(this.background);
		return list;
	}
}
