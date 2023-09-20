package excelUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class ReadFromExcel {
	public static int totalRow;

	public List<String> getData(String excelFilePath, String sheetName)
			throws InvalidFormatException, IOException {	
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		return readSheet(sheet);
		
	}

	private List<String> readSheet(Sheet sheet) {

		Row row;
		//Cell cell;
		totalRow = sheet.getLastRowNum();

		List<String> excelRows = new ArrayList<String>();

		for (int currentRow = 0; currentRow <= totalRow; currentRow++) {

			row = sheet.getRow(currentRow);

			int totalColumn = row.getLastCellNum();	

			String columnVal = row.getCell(0).getStringCellValue();
			
			excelRows.add(columnVal);
			
		}

		return excelRows;
	}

	public int countRow() {

		return totalRow;
	}

}
