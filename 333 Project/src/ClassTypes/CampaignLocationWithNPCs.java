package ClassTypes;

import java.util.ArrayList;

public class CampaignLocationWithNPCs {

	private String locationName, description, npcName, npcId, locationId, raceName, occupation = null;
	
	public CampaignLocationWithNPCs(int locId, String description, String npcName, int NPCID, String raceName, String occupation, String locationName) {
		this.locationName = locationName;
		this.locationId = Integer.toString(locId);
		this.description = description;
		this.npcName = npcName;
		this.raceName = raceName;
		this.occupation = occupation;
		this.npcId = Integer.toString(NPCID);
	}
	
	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(locationId);
		list.add(this.locationName);
		list.add(this.description);
		list.add(this.npcId);
		list.add(this.npcName);
		list.add(this.raceName);
		list.add(this.occupation);
		return list;
	}
}
