package helper;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public ExcelHelper(String filePath) {
		
	}

	public static ArrayList<Map<String, Object>> extractAsListOfDictionary(String excelFilePath, Integer worksheetInd) {
		File excelFile = new File(excelFilePath);
		List<String> keys = null;
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			FileInputStream file = new FileInputStream(excelFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			// Get desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(worksheetInd);
			Iterator<Row> rowIterator = sheet.iterator();
			int maxColumn = 0;
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Get list of keys in the first row
				if (row.getRowNum() == 0) {
					maxColumn = row.getLastCellNum();
					keys = extractRowAsListOfString(row, maxColumn);
				}
				
				if (isRowEmpty(row, maxColumn)) {
					break;
				}
				
				// Extract this row into dictionary
				Map<String, Object> singleRows = extractRowAsDictionary(row, maxColumn, keys);
				// And this row into the list
				list.add(singleRows);			
			}
			
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private static List<String> extractRowAsListOfString(Row row, Integer maxCol) {
		ArrayList<String> list = new ArrayList<String>();
		for (int col = 0; col < maxCol; col++) {
			Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			list.add(cell.getStringCellValue());
		}
		return list;
	}

	private static Map<String, Object> extractRowAsDictionary(Row row, Integer maxCol, List<String> keys) {
		Map<String, Object> singleRows = new HashMap<String, Object>();
		for (int colInd = 0; colInd < maxCol; colInd++) {

			Cell cell = row.getCell(colInd, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

			switch (cell.getCellTypeEnum()) {
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					singleRows.put(keys.get(colInd),
							new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()));
				} else
					singleRows.put(keys.get(colInd),
							new SimpleDateFormat("yyyy-MM-dd").format(cell.getNumericCellValue()));
				break;

			case STRING:
				singleRows.put(keys.get(colInd), new SimpleDateFormat("yyyy-MM-dd").format(cell.getStringCellValue()));
				break;

			case BLANK:
				singleRows.put(keys.get(colInd), null);
				break;

			default:
				singleRows.put(keys.get(colInd), new SimpleDateFormat("yyyy-MM-dd").format(cell.getStringCellValue()));
			}

		}
		return null;
	}

	public static boolean isRowEmpty(Row row, int lastcellno) {
		for (int c = row.getFirstCellNum(); c < lastcellno; c++) {
			Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (cell != null && cell.getCellTypeEnum() != CellType.BLANK)
				return false;
		}
		return true;
	}
}