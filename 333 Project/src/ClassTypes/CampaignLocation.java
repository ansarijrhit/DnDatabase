package ClassTypes;

import java.util.ArrayList;

public class CampaignLocation {

	private String locationName, description = null;
	
	public CampaignLocation(String locationName, String description) {
		this.locationName = locationName;
		this.description = description;
	}
	
	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.locationName);
		list.add(this.description);
		return list;
	}
}
