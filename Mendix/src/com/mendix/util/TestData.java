package com.mendix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	public static String testDataFile = "";
	static String sheetName = "";
	static int testCaseIdColNo = 0;
	public static String testCaseName= null;

	public static ArrayList<String[]> getTestCaseData(String sheetName) throws IOException {
		TestData.sheetName = sheetName;
		// String matchedData = "";
		// int matchedCol = -1;
		FileInputStream file = new FileInputStream(new File(TestData.testDataFile));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(TestData.sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		Row firstRow = rowIterator.next();
		int colCount = firstRow.getLastCellNum();
		ArrayList<String[]> data = new ArrayList<String[]>();
		while (rowIterator.hasNext()) {
			int i = 0;
			String[] currentRowData = new String[colCount];
			Row currentRow = rowIterator.next();
			Cell testCaseStatusCell = currentRow.getCell(testCaseIdColNo+3);
			// Cell testStepIdCell = currentRow.getCell(testCaseIdColNo);
			// System.out.println("Row testCaseIdCell:"+testCaseIdCell+
			// "  testStepIdCell:"+testStepIdCell);
			if(testCaseStatusCell.getStringCellValue().equalsIgnoreCase("Y")){
				// &&
				// testStepIdCell.getStringCellValue().equalsIgnoreCase(stepId)){
				Iterator<Cell> cellIterator = currentRow.cellIterator();
				while (cellIterator.hasNext()) {
					// matchedCol = matchedCol +1;
					// System.out.println("Row:"+currentRow.getRowNum()+"  Col:"+matchedCol);
					Cell currentCell = cellIterator.next();
					String currentCellValue = "";
					if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						currentCellValue = String.valueOf(currentCell.getNumericCellValue());
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
						currentCellValue = currentCell.getStringCellValue();
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
						currentCellValue = String.valueOf(currentCell.getBooleanCellValue());
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
						currentCellValue = "";
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
						currentCellValue = String.valueOf(currentCell.getCellFormula());
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_ERROR) {
						currentCellValue = "Error reading data";
					}

					// System.out.println("Colval:"+currentCellValue);
					// if(currentCellValue.equalsIgnoreCase(fieldName)){
					// matchedData = currentCell.getStringCellValue();
					// break; // Break reading columns
					// }

					currentRowData[i] = currentCellValue;
					i = i + 1;
				}
				data.add(currentRowData);
				// break; // Break reading rows
			}
		}
		return data;
	}

	/*public static String getTestCaseData(String testCaseId, String stepId, String fieldName)
			throws IOException {
		String matchedData = "";
		int matchedCol = -1;
		FileInputStream file = new FileInputStream(new File(TestData.testDataFile));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(TestData.sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			Cell testCaseIdCell = currentRow.getCell(testCaseIdColNo - 1);
			Cell testStepIdCell = currentRow.getCell(testCaseIdColNo);
			// System.out.println("Row testCaseIdCell:"+testCaseIdCell+
			// "  testStepIdCell:"+testStepIdCell);
			if (testCaseIdCell.getStringCellValue().equalsIgnoreCase(testCaseId)
					&& testStepIdCell.getStringCellValue().equalsIgnoreCase(stepId)) {
				Iterator<Cell> cellIterator = currentRow.cellIterator();
				while (cellIterator.hasNext()) {
					matchedCol = matchedCol + 1;
					// System.out.println("Row:"+currentRow.getRowNum()+"  Col:"+matchedCol);
					Cell currentCell = cellIterator.next();
					String currentCellValue = "";
					if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						currentCellValue = String.valueOf(currentCell.getNumericCellValue());
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
						currentCellValue = currentCell.getStringCellValue();
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
						currentCellValue = String.valueOf(currentCell.getBooleanCellValue());
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
						currentCellValue = "";
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
						currentCellValue = String.valueOf(currentCell.getCellFormula());
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_ERROR) {
						currentCellValue = "Error reading data";
					}

					// System.out.println("Colval:"+currentCellValue);
					if (currentCellValue.equalsIgnoreCase(fieldName)) {
						matchedData = currentCell.getStringCellValue();
						break; // Break reading columns
					}
				}

				break; // Break reading rows
			}
		}

		if (matchedCol != -1) {
			Row nextRow = rowIterator.next();
			Cell matchedCell = nextRow.getCell(matchedCol);
			if (matchedCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				matchedData = String.valueOf(matchedCell.getNumericCellValue());
			} else if (matchedCell.getCellType() == Cell.CELL_TYPE_STRING) {
				matchedData = matchedCell.getStringCellValue();
			} else if (matchedCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				matchedData = String.valueOf(matchedCell.getBooleanCellValue());
			} else if (matchedCell.getCellType() == Cell.CELL_TYPE_BLANK) {
				matchedData = "";
			} else if (matchedCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				matchedData = String.valueOf(matchedCell.getCellFormula());
			} else if (matchedCell.getCellType() == Cell.CELL_TYPE_ERROR) {
				matchedData = "Error reading data";
			}
		}
		return matchedData;
	}*/

	/*public static void loadTestCaseData(String testDataFile, String sheetName, int testCaseIdColNo) {
		TestData.testDataFile = testDataFile;
		TestData.sheetName = sheetName;
		TestData.testCaseIdColNo = testCaseIdColNo;
	}

	public static void setDataFrom(String testDataFile, String sheetName) {
		TestData.testDataFile = testDataFile;
		TestData.sheetName = sheetName;
		TestData.testCaseIdColNo = 1;
	}*/

	/*public static String getTestCaseData(String testDataFile, String sheetName,
			int testCaseIdColNo, String testCaseId, String stepId, String fieldName)
			throws IOException {
		TestData.testDataFile = testDataFile;
		TestData.sheetName = sheetName;
//		TestData.testCaseIdColNo = testCaseIdColNo;
		return getTestCaseData(testCaseId, stepId, fieldName);
	}*/

	public static String getTestCaseName(String testDataFile, String sheetName)
					throws IOException {
		TestData.testDataFile = "C:\\Users\\SatishKumarSundaramo\\git\\Mendix_New\\Mendix\\input\\Mendix_TestPlan.xlsx";
		ArrayList<String[]> data = TestData.getTestCaseData("TestPlan");
		String testCaseName=null;
		for (int i = 0; i < data.size(); i++) {
			String[] s = data.get(i);
			for (int j = 0; j < s.length; j++) {
				System.out.print(s[j] + " , ");
				testCaseName=s[2].toString();

			}
			System.out.println("");
			//			movedata(testCaseName);
			System.out.println(testCaseName);
		}
		;
		System.out.println(testCaseName);
		//TestData.testCaseIdColNo = testCaseIdColNo;
		return testCaseName;
	}

	public static int getRow(String testDataFile, String sheetName) throws IOException {
		return TestCase.getRow(testDataFile, sheetName);
	}

	public static String getRow_New(String testDataFile, String sheetName) throws IOException {
		return TestCase.getRow_New(testDataFile, sheetName);
	}

	/*public static String[] getData(String fileName, String sheet, String testCaseId) throws IOException{
		return ExcelMethods.getData(fileName, sheet, testCaseId);
	}*/


	public String movedata(String testCaseName){
		return this.testCaseName;
	}


	public static void main(String args[]) throws IOException {
		TestData.testDataFile = "C:\\Users\\SatishKumarSundaramo\\git\\Mendix_New\\Mendix\\input\\Mendix_TestPlan.xlsx";
		ArrayList<String[]> data = TestData.getTestCaseData("TestPlan");
		String testCaseName=null;
		for (int i = 0; i < data.size(); i++) {
			String[] s = data.get(i);
			for (int j = 0; j < s.length; j++) {
				System.out.print(s[j] + " , ");
				testCaseName=s[2].toString();

			}
			System.out.println("");
			//			movedata(testCaseName);
			System.out.println(testCaseName);
		}
		;
		System.out.println(testCaseName);
		// System.out.println("Data:"+TestData.getTestCaseData("TC1", "TS1",
		// "CustomerName"));
		// System.out.println("Data:"+TestData.getTestCaseData("TC2", "TS1",
		// "Phone"));
	}


}
