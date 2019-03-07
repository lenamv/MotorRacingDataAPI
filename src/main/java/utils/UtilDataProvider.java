package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UtilDataProvider {
	
	static Workbook workBook;
	static Sheet sheet;
	
	public static String TESTDATA_PATH = "./src/main/java/testData/f1_TestData.xlsx";
	
	
	public static Object [][] getTestData(String sheetName){
		FileInputStream file = null;
						
		try {
			file = new FileInputStream(TESTDATA_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Obtain a Workbook from an Excel file 
		try {
			workBook = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = workBook.getSheet(sheetName);
		
		// Create a DataFormatter to format and get each cell's value as String		
		DataFormatter dataFormater = new DataFormatter();
		
		int countRows = sheet.getLastRowNum();
		int countCells = sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object[countRows][countCells];
		
		for (int i=0; i<countRows; i++){
			for (int j=0; j<countCells; j++){
				
				String cellValue = dataFormater.formatCellValue(sheet.getRow(i+1).getCell(j));
				data[i][j] = cellValue;
				
				//System.out.println(data[i][j]);
		
			}
		}
		
		return data;
	}
	
}


