package Connection;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class DataImport {

	private Connection con;

	public DataImport(Connection con) {
		this.con = con;
	}

	// 1st
	public void importSpells(String filePath) {

		int batchSize = 20;

		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "INSERT INTO SpellTemp (Name, Description, [Cast Level]) VALUES (?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);

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

	// 2nd
	public void importRaces(String filePath) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "INSERT INTO RaceTemp (Name, Description) VALUES (?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);

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

			String sql = "INSERT INTO ClassTemp (Name, Description) VALUES (?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);

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

	// 4th
	public void importClassCanCast(String filePath, int i) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(i);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "INSERT INTO ClassCanCastTemp (ClassName, SpellName) VALUES (?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					switch(i) {
					case 0:
//						System.out.print("0 " + i + " ");
						statement.setString(1, "Bard");
						break;
					case 1:
//						System.out.print("1 " + i + " ");
						statement.setString(1, "Cleric");
						break;
					case 2:
//						System.out.print("2 " + i + " ");
						statement.setString(1, "Druid");
						break;
					case 3:
//						System.out.print("3 " + i + " ");
						statement.setString(1, "Paladin");
						break;
					case 4:
//						System.out.print("4 " + i + " ");
						statement.setString(1, "Ranger");
						break;
					case 5:
//						System.out.print(i + " ");
						statement.setString(1, "Sorcerer");
						break;
					case 6:
//						System.out.print(i + " ");
						statement.setString(1, "Warlock");
						break;
					case 7:
//						System.out.print(i + " ");
						statement.setString(1, "Wizard");
						break;
					}
					
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						String className = nextCell.getStringCellValue();
//						System.out.print(className + "\n");
						statement.setString(2, className);
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
	public void importCampaign(String filePath) {
		int batchSize = 20;
		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			con.setAutoCommit(false);

			String sql = "EXEC create_campaign @name = ?, @dm_username = ?";
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
						String dm_username = nextCell.getStringCellValue();
						statement.setString(2, dm_username);
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
}
