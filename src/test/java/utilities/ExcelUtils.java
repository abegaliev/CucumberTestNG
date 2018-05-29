package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Custom class, which helps to work with Excel files.
 * Can read and write an Excel file.
 * 
 * ====> Open Excel File ( ExcelUtils.openExcelFile(filePath, sheetName) )
 * before using methods of this class.
 * file.
 */
public class ExcelUtils {

	private static XSSFWorkbook excelWBook;
	private static XSSFSheet excelWSheet;
	private static String excelFilePath;


	/**
	 * Opens Excel file,
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * 
	 */
	public static void  openExcelFile(String filePath, String sheetName) {
		excelFilePath = filePath;
		try {

			File file = new File(filePath);
			FileInputStream ExcelFile = new FileInputStream(file);

			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println("Exception from Constructor of ExcelUtils: " + e);
		}
	}

	/**
	 * This method is to read the data from the Excel cell. Accepts as a parameter
	 * Row number and Column number
	 * 
	 * @param rowNum
	 * @param colNum
	 * @return String
	 */
	public static String getCellData(int rowNum, int colNum) {
		try {
			XSSFCell cell = excelWSheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.toString();
			return cellData;
		} catch (Exception e) {
			System.out.println("No existing cell in: " + excelWSheet.getSheetName() + " : cell: " + colNum);
			return "";
		}
	}

	/**
	 * Writes data in the Excel cell.
	 * 
	 * @param value
	 * @param rowNum
	 * @param colNum
	 */
	public static void setCellData(String value, int rowNum, int colNum) {
		try {
			XSSFCell cell;
			XSSFRow row;

			if (excelWSheet.getPhysicalNumberOfRows() < rowNum) {
				row = excelWSheet.createRow(rowNum);
			} else {
				row = excelWSheet.getRow(rowNum);
			}

			cell = row.getCell(colNum);

			if (cell == null) {
				cell = row.createCell(colNum);
			}
			cell.setCellValue(value);

			// To save changes in ExcelFile.
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);
			excelWBook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Failed to write data to: " + excelWSheet.getSheetName());
		}
	}

	/**
	 * 
	 *
	 * @param tableName
	 * @return
	 */
	public static String[][] getTestData(String tableName) {
		String[][] testData = null;

		try {
			// Handles numbers and strings
			DataFormatter formatter = new DataFormatter();
			// BoundaryCells are the first and the last column
			// We need to find first and last column, so that we know which rows to read for
			// the data
			XSSFCell[] boundaryCells = findCells(tableName);
			// First cell to start with
			XSSFCell startCell = boundaryCells[0];
			// Last cell where data reading should stop
			XSSFCell endCell = boundaryCells[1];

			// Find the start row based on the start cell
			int startRow = startCell.getRowIndex() + 1;
			// Find the end row based on end cell
			int endRow = endCell.getRowIndex() - 1;
			// Find the start column based on the start cell
			int startCol = startCell.getColumnIndex() + 1;
			// Find the end column based on end cell
			int endCol = endCell.getColumnIndex() - 1;

			// Declare multi-dimensional array to capture the data from the table
			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					// testData[i-startRow][j-startCol] =
					// ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					// For every column in every row, fetch the value of the cell
					Cell cell = excelWSheet.getRow(i).getCell(j);
					// Capture the value of the cell in the multi-dimensional array
					testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the multi-dimensional array
		return testData;
	}

	public static XSSFCell[] findCells(String tableName) {
		DataFormatter formatter = new DataFormatter();
		// Declare begin position
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];

		for (Row row : excelWSheet) {
			for (Cell cell : row) {
				// if (tableName.equals(cell.getStringCellValue())) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						// Find the begin cell, this is used for boundary cells
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						// Find the end cell, this is used for boundary cells
						cells[1] = (XSSFCell) cell;
					}
				}
			}
		}
		// Return the cells array
		return cells;
	}

	
	/**
	 * Returns count of used rows in the given ExcelSheet
	 * 
	 * @return count of used rows
	 */
	public static int getUsedRowsCount() {
		try {
			int rowCount = excelWSheet.getPhysicalNumberOfRows();
			return rowCount;
		} catch (Exception e) {
			return 0;
		}
	}
	
	

}