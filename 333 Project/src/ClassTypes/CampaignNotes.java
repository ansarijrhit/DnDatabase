package ClassTypes;

import java.util.ArrayList;

public class CampaignNotes {

	private String noteName, noteId, description = null;
	private int campaignID = -1;

	public CampaignNotes(int campaignID, int noteId, String noteName, String description) {
		this.campaignID = campaignID;
		this.noteName = noteName;
		this.description = description;
		this.noteId = Integer.toString(noteId);
	}

	public ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(String.valueOf(this.campaignID));
		list.add(this.noteId);
		list.add(this.noteName);
		list.add(this.description);
		return list;
	}
}
