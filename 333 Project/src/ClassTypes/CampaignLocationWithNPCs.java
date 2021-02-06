package ClassTypes;

import java.util.ArrayList;

public class CampaignLocationWithNPCs {

	private String locationName, description, npcName, raceName, occupation = null;
	
	public CampaignLocationWithNPCs(int locId, String description, String npcName, String raceName, String occupation) {
		this.locationName = Integer.toString(locId);
		this.description = description;
		this.npcName = npcName;
		this.raceName = raceName;
		this.occupation = occupation;
	}
	
	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.locationName);
		list.add(this.description);
		list.add(this.npcName);
		list.add(this.raceName);
		list.add(this.occupation);
		return list;
	}
}
