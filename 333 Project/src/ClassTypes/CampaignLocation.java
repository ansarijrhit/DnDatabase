package ClassTypes;

import java.util.ArrayList;

public class CampaignLocation {

	private String locationName, locationId, description = null;
	
	public CampaignLocation(int locationid, String name, String description) {
		this.locationName = name;
		this.locationId = Integer.toString(locationid);
		this.description = description;
	}

	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.locationId);
		list.add(this.locationName);
		list.add(this.description);
		return list;
	}
}
