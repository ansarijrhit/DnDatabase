package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClassTypes.CampaignCharacter;
import ClassTypes.CampaignLocation;
import ClassTypes.CampaignLocationWithNPCs;
import ClassTypes.CampaignNotes;
import ClassTypes.CharacterSpells;
import ClassTypes.PlayerCharacter;

public class ReadFunctions {

	private Connection con = null;

	public ReadFunctions(Connection con) {
		this.con = con;
	}

	public ArrayList<ArrayList<String>> readPlayerCharacterInformation(String pusername, int id, int campaignID) {
		ArrayList<ArrayList<String>> character = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_player_character @username = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			if (id < 0 && campaignID < 0) {
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
			} else if (id < 0) {
				sql += ", @campaignid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setInt(2, campaignID);
			} else if (campaignID < 0) {
				sql += ", @characterid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setInt(2, id);
			} else {
				sql += ", @characterid = ?, @campaignid = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, pusername);
				statement.setInt(2, id);
				statement.setInt(3, campaignID);
			}
			set = statement.executeQuery();
			while (set.next()) {
				character.add(new PlayerCharacter(set.getString("CharName"), set.getString("Class_ClassName"),
						set.getInt("Character_CharacterID"), set.getInt("HasClass_Level"), set.getInt("Hitpoints"),
						set.getString("Alignment"), set.getString("BackGround")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Player Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();

		}
		return character;
	}

	public ArrayList<ArrayList<String>> readCharacterSpells(String pusername, int id, int showAllPossible) {
		ArrayList<ArrayList<String>> spells = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_character_spells @username = ?, @characterid = ?, @showAll = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			statement.setString(1, pusername);
			statement.setInt(2, id);
			statement.setInt(3, showAllPossible);
			set = statement.executeQuery();
			while (set.next()) {
				spells.add(new CharacterSpells(set.getString("ClassName"), set.getString("RaceName"),
						set.getString("Name"), set.getString("Description"), set.getInt("Cast Level")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Character Spells Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();

		}
		return spells;
	}

	public ArrayList<ArrayList<String>> readCampaignCharacterInformation(String dmUsername, int campaignID) {
		ArrayList<ArrayList<String>> character = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_campaign_characters @DM_Username = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			if (campaignID < 0) {
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
			} else {
				sql += ", @CampaignID = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
				statement.setInt(2, campaignID);
			}
			set = statement.executeQuery();
			while (set.next()) {
				character.add(new CampaignCharacter(set.getInt("CampaignID"), set.getString("PlayerUsername"),
						set.getInt("CharacterID"), set.getString("CharacterName"), set.getString("RaceName"),
						set.getString("Alignment"), set.getInt("Hitpoints"), set.getString("BackGround")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Campaign Character Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();

		}
		return character;
	}

	public ArrayList<ArrayList<String>> readCampaignLocations(String dmUsername, int campaignID, int locationID,
			int viewNPCs) {
		ArrayList<ArrayList<String>> locations = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_campaign_locations_npcs @DM_Username = ?, @viewNPCs = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			if (campaignID < 0 && locationID < 0) {
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
				statement.setInt(2, viewNPCs);
			} else if (campaignID < 0) {
				sql += ", @LocationID = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
				statement.setInt(2, viewNPCs);
				statement.setInt(3, locationID);
			} else if (locationID < 0) {
				sql += ", @CampaignID = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
				statement.setInt(2, viewNPCs);
				statement.setInt(3, campaignID);
			} else {
				sql += ", @CampaignID = ?, @LocationID = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
				statement.setInt(2, viewNPCs);
				statement.setInt(3, campaignID);
				statement.setInt(4, locationID);
			}
			set = statement.executeQuery();

			if (viewNPCs == 0) {
				while (set.next()) {
					locations.add(new CampaignLocation(set.getInt("Locationid"), set.getString("LocationName"),
							set.getString(2)).getItems());
				}
			} else {
				while (set.next()) {
					locations.add(new CampaignLocationWithNPCs(set.getInt("Locationid"), set.getString("LocationName"),
							set.getString(2), set.getInt("NPCID"), set.getString("NPCName"), set.getString("RaceName"),
							set.getString("Occupation")).getItems());
				}
			}

			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Campaign Location Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();

		}
		return locations;
	}

	public ArrayList<ArrayList<String>> readCampaignNotes(String dmUsername, int campaignID) {
		ArrayList<ArrayList<String>> notes = new ArrayList<ArrayList<String>>();
		ResultSet set = null;
		try {
			String sql = "EXEC read_campaign_notes @DM_Username = ?";
			PreparedStatement statement = this.con.prepareStatement(sql);
			if (campaignID < 0) {
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
			} else {
				sql += ", @CampaignID = ?";
				statement = this.con.prepareStatement(sql);
				statement.setString(1, dmUsername);
				statement.setInt(2, campaignID);
			}
			set = statement.executeQuery();
			while (set.next()) {
				notes.add(new CampaignNotes(set.getInt("CampaignID"), set.getInt("NotesID"), set.getString("NoteName"),
						set.getString("Description")).getItems());
			}
			set.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println();
			System.out.println("----------Error in fetching data-------------");
			System.out.println("Read Campaign Notes Function");
			System.out.println("Check login information, access level, and existence of character");
			System.out.println();
			e.printStackTrace();

		}
		return notes;
	}
}
