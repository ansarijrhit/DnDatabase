package ClassTypes;

import java.util.ArrayList;

public class CampaignNotes {

	private String noteName, description = null;
	private int campaignID = -1;
	
	public CampaignNotes(int campaignID, String noteName, String description) {
		this.campaignID = campaignID;
		this.noteName = noteName;
		this.description = description;
	}
	
	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(String.valueOf(this.campaignID));
		list.add(this.noteName);
		list.add(this.description);
		return list;
	}
}
