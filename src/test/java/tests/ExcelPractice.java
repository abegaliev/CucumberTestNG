package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPractice {

	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/test/resources/testData/Workbook2.xlsx");
		
		XSSFWorkbook wBook = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell;
		

			FileInputStream input = new FileInputStream(file);
			wBook = new XSSFWorkbook(input);
			sheet = wBook.getSheet("sheet1");
			row = sheet.getRow(9);

		cell = row.getCell(0);
		
		System.out.println(cell);
		cell.setCellValue("111222333444555");
		System.out.println("After: "+ cell );
		
		FileOutputStream outPut = new FileOutputStream(file);
		wBook.write(outPut);
		outPut.close();
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
