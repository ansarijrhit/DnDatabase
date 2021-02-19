package Connection;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class DataImport {

	private Connection con;
	private UserService us;

	public DataImport(Connection con, UserService us) {
		this.con = con;
		this.us = us;
	}

	// 1st, works
	public void importSpells(String filePath) {

		int batchSize = 20;

		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC insert_spell @name = ?, @description = ?, @castLevel = ?";
			CallableStatement statement = con.prepareCall(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
//                        System.out.println("Name: " + name + " ");
						statement.setString(1, name);
						break;
					case 1:
						String description = nextCell.getStringCellValue();
//                    	System.out.println("Description: " + description + " ");
						statement.setString(2, description);
					case 2:
						CellType type = nextCell.getCellType();
						if (type.equals(CellType.NUMERIC)) {
							int level = (int) nextCell.getNumericCellValue();
//                            System.out.print("Level: " + level + "\n");
							statement.setInt(3, level);
						}
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			workbook.close();

			// execute the remaining queries
			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}

	}

	// 2nd, works
	public void importRaces(String filePath) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC insert_race @name = ?, @description = ?";
			CallableStatement statement = con.prepareCall(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
						statement.setString(1, name);
						break;
					case 1:
						String description = nextCell.getStringCellValue();
						statement.setString(2, description);
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			workbook.close();

			// execute the remaining queries
			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}

	}

	// 3rd
	public void importClasses(String filePath) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC insert_class @className = ?, @description = ?";
			CallableStatement statement = con.prepareCall(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
						statement.setString(1, name);
						break;
					case 1:
						String description = nextCell.getStringCellValue();
						statement.setString(2, description);
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			workbook.close();

			// execute the remaining queries
			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}

	// 4th, works
	public void importClassCanCast(String filePath, int i) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(i);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC insert_classCanCast @classname = ?, @spellName = ?";
			CallableStatement statement = con.prepareCall(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					switch (i) {
					case 0:
						System.out.print("Bard ");
//						statement.setString(1, "Bard");
						break;
					case 1:
						System.out.print("Cleric ");
//						statement.setString(1, "Cleric");
						break;
					case 2:
						System.out.print("Druid ");
//						statement.setString(1, "Druid");
						break;
					case 3:
						System.out.print("Paladin ");
//						statement.setString(1, "Paladin");
						break;
					case 4:
						System.out.print("Ranger ");
//						statement.setString(1, "Ranger");
						break;
					case 5:
						System.out.print("Sorcerer ");
//						statement.setString(1, "Sorcerer");
						break;
					case 6:
						System.out.print("Warlock ");
//						statement.setString(1, "Warlock");
						break;
					case 7:
						System.out.print("Wizard ");
//						statement.setString(1, "Wizard");
						break;
					}

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String spellName = nextCell.getStringCellValue();
//						System.out.print(spellName + "\n");
						statement.setString(2, spellName);
						break;
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			workbook.close();

			// execute the remaining queries
//			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}

	}

	// 5th
	public void importRaceCanCast(String filePath) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC insert_raceCanCast @raceName = ?, @spellname = ?";
			CallableStatement statement = con.prepareCall(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String raceName = nextCell.getStringCellValue();
//						System.out.print(raceName + " ");
						statement.setString(1, raceName);
						break;
					case 1:
						String spellName = nextCell.getStringCellValue();
//						System.out.print(spellName + "\n");
						statement.setString(2, spellName);
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			workbook.close();

			// execute the remaining queries
//			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}

	}

	// 6th
	// Excel file should include: Campaign name/description/DM Username.
	// Locations (Name, Description)
	// Characters (Name, Race): Major (Hitpoints, Alignment, Background,
	// PlayerUsername, Class, Level),
	// NPC (Occupation, LocationID)
	// Notes (Name, Description)
	public void importCampaign(String filePath, String name) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet playerSheet = workbook.getSheetAt(0);
			Sheet locationSheet = workbook.getSheetAt(1);
			Sheet majorCharacterSheet = workbook.getSheetAt(2);
			Sheet NPCSheet = workbook.getSheetAt(3);
			Sheet notesSheet = workbook.getSheetAt(4);
			
			importPlayers(playerSheet);
			
			Iterator<Row> rowIterator = playerSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC create_campaign @name = ?, @dm_username = ?";
			CallableStatement statement = con.prepareCall(sql);

			statement.setString(1, name);
			
			rowIterator.next(); // skip the header row

			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			Cell nextCell = cellIterator.next();

			int columnIndex = nextCell.getColumnIndex();
			String dmUsername = "";
			int campaignID = 0;

			switch (columnIndex) {
			case 0:
				dmUsername = nextCell.getStringCellValue();
				System.out.println("dmUsername: " + dmUsername);
				statement.setString(2, dmUsername);

			}
			statement.execute();

			CallableStatement cs = con.prepareCall("{ ? = call mostRecentCampaignID(?)}");
			cs.setString(2, dmUsername);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			campaignID = cs.getInt(1);
			
			System.out.println("campaignID: " + campaignID);
			
			System.out.println("Importing Locations");
			importLocations(locationSheet, dmUsername, campaignID);
			System.out.println("Importing Major Characters");
			importMajorCharacters(majorCharacterSheet, dmUsername, campaignID);
			System.out.println("Importing NPCs");
			importNPCs(NPCSheet, dmUsername, campaignID);
			System.out.println("Importing Notes");
			importNotes(notesSheet, dmUsername, campaignID);
			
			workbook.close();

			// execute the remaining queries

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}

	public void importPlayers(Sheet sheet) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			Iterator<Row> rowIterator = sheet.iterator();

			con.setAutoCommit(false);

			
			
//			String sql = "EXEC create_campaign @name = ?, @dm_username = ?";
//			CallableStatement statement = con.prepareCall(sql);

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				CallableStatement cs = con.prepareCall("{? = call doesPlayerExist(?)}");
				
				cs.registerOutParameter(1, Types.INTEGER);
				boolean exists = false;
				String username = "";
				String name = "";

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						username = nextCell.getStringCellValue();
						cs.setString(2, username);
						cs.execute();
						int returnValue = cs.getInt(1);
						if(returnValue == 0) {
							exists = true;
						}
						break;
					case 1:
						name = nextCell.getStringCellValue();
						break;
					case 2:
						String password = nextCell.getStringCellValue();
//						statement.setString(2, password);
						if(!exists) {
							us.register(username, name, password);
						}
					}

				}

//				statement.addBatch();
//
//				if (count % batchSize == 0) {
//					statement.executeBatch();
//				}

			}

			// execute the remaining queries
//			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}

	public void importLocations(Sheet sheet, String dmUsername, int campaignID) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();
			
			Iterator<Row> rowIterator = sheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC create_location @dm_username = ?, @name = ?, @description = ?, @campaignid = ?";
			CallableStatement call = con.prepareCall(sql);

			int count = 0;
			
			call.setString(1, dmUsername);
			call.setInt(4, campaignID);
			
			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				boolean doIt = false;

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
						if(!name.isEmpty()) {
							call.setString(2, name);
							doIt = true;
						}
						break;
					case 1:
						String description = nextCell.getStringCellValue();
						call.setString(3, description);
					}

				}
				if(doIt) {
					call.addBatch();
				}
				if (count % batchSize == 0) {
					call.executeBatch();
				}

			}


			// execute the remaining queries
			call.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		}  catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}
	
	public void importMajorCharacters(Sheet sheet, String dmUsername, int campaignID) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			Iterator<Row> rowIterator = sheet.iterator();

			con.setAutoCommit(false);

			
			

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				String sql = "EXEC create_major_character @name = ?, @racename = ?, @hitpoints = ?, @alignment = ?, @background = ?, @class = ?, @level = ?, @campaign_id = ?";
				
				String name = "";
				String raceName = "";
				int hitpoints = 0;
				String alignment = "";
				String background = "";
				String username = "";
				String pClass = "";
				int level = 0;
				boolean fail = false;
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						name = nextCell.getStringCellValue();
//						System.out.println(name.isBlank());
						if(name.isBlank() || name.equals("")) {
							System.out.println("failure");
							fail = true;
						}
//						statement.setString(1, name);
						break;
					case 1:
						raceName = nextCell.getStringCellValue();
//						System.out.println("Racename: " + raceName + " aaa");
//						statement.setString(2, raceName);
						break;
					case 2:
						hitpoints = (int) nextCell.getNumericCellValue();
//						statement.setInt(3, hitpoints);
						break;
					case 3:
						alignment = nextCell.getStringCellValue();
//						statement.setString(4, alignment);
						break;
					case 4:
//						System.out.println(4);
						background = nextCell.getStringCellValue();
//						statement.setString(5, background);
						break;
					case 5:
						username = nextCell.getStringCellValue();
//						System.out.print(5 + " dmUsername ");
						if(username.equals("abcdefghijklmnopqrstuvwxyz")) {
							sql += ", @dm_username = ?";
							username = dmUsername;
						}
						else {
							sql += ", @playerusername = ?";
//							System.out.print(5 + " playerUsername ");
						}
						break;
					case 6:
						pClass = nextCell.getStringCellValue();
						break;
					case 7:
						level = (int) nextCell.getNumericCellValue();
					}
				}
				if(fail) {
					break;
				}
//				System.out.println(sql);
				
				CallableStatement statement = con.prepareCall(sql);
				statement.setString(1, name);
				statement.setString(2, raceName);
				statement.setInt(3, hitpoints);
				statement.setString(4, alignment);
				statement.setString(5, background);
				statement.setString(6, pClass);
				statement.setInt(7, level);
				statement.setInt(8, campaignID);
				statement.setString(9, username);
				
				statement.execute();
//				statement.addBatch();

//				if (count % batchSize == 0) {
//					statement.executeBatch();
//				}

			}

			// execute the remaining queries
//			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}

	public void importNPCs(Sheet sheet, String dmUsername, int campaignID) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			Iterator<Row> rowIterator = sheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC create_npc @dm_username = ?, @locationid = ?, "
					+ "@name = ?, @racename = ?, @occupation = ?";
			CallableStatement statement = con.prepareCall(sql);
			
			statement.setString(1, dmUsername);

			int count = 0;

			rowIterator.next(); // skip the header row


			
			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
						statement.setString(3, name);
						break;
					case 1:
						String race = nextCell.getStringCellValue();
						statement.setString(4, race);
						break;
					case 2:
						String occupation = nextCell.getStringCellValue();
						statement.setString(5, occupation);
						break;
					case 3:
						String locationName = nextCell.getStringCellValue();
						
						CallableStatement cs = con.prepareCall("{ ? = call get_LocationId(?, ?)}");
//						System.out.println("? = call get_LocationID(" + locationName + " " + campaignID + ")");
						cs.setString(2, locationName);
						cs.setInt(3, campaignID);
						cs.registerOutParameter(1, Types.INTEGER);
						cs.execute();
//						campaignID = cs.getInt(1);
						statement.setInt(2, cs.getInt(1));
						
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			// execute the remaining queries
			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		}  catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}

	public void importNotes(Sheet sheet, String dmUsername, int campaignID) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			Iterator<Row> rowIterator = sheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC create_notes @dm_username = ?, @name = ?, @description = ?, @campaignid = ?";
			CallableStatement statement = con.prepareCall(sql);
			
			statement.setString(1, dmUsername);
			statement.setInt(4, campaignID);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
						statement.setString(2, name);
						break;
					case 1:
						String description = nextCell.getStringCellValue();
						statement.setString(3, description);
						break;
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			// execute the remaining queries
			statement.executeBatch();

			con.commit();
//			con.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		}  catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
		
	}
}
