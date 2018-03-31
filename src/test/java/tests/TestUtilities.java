package tests;

import utilities.ExcelUtils;

public class TestUtilities {

	public static void main(String[] args) {
		
		ExcelUtils.openExcelFile("./src/test/resources/testData/contacts.xlsx", "Sheet1");

		ExcelUtils.setCellData("Hello ", 3, 9);
		System.out.println(ExcelUtils.getUsedRowsCount());
	}
	
	
}
