package com.mendix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCase {
	
	static int testCaseIdColNo = 1;
	
	protected static int getRow(String testDataFile, String sheetName) throws IOException{
		if(testDataFile.toLowerCase().endsWith(".xlsx")){
			return getRowXLSX(testDataFile, sheetName);
		}else if(testDataFile.toLowerCase().endsWith(".xls")){
			return getRowXLS(testDataFile, sheetName);
		} else{
			return -1;
		}
	}
	
	protected static String getRow_New(String testDataFile, String sheetName) throws IOException{
//		if(testDataFile.toLowerCase().endsWith(".xlsx")){
			return getRowXLSX_New(testDataFile, sheetName);
//		}
		/*}else if(testDataFile.toLowerCase().endsWith(".xls")){
			return getRowXLS(testDataFile, sheetName);
		} else{
			return -1;
		}*/
	}
	
	private static int getRowXLSX(String testDataFile, String sheetName) throws IOException{
		int matchedCol = 1;
		
		FileInputStream file = new FileInputStream(new File(testDataFile));	
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()){
			Row currentRow = rowIterator.next();
			Cell testCaseIdCell = currentRow.getCell(testCaseIdColNo);
			if(testCaseIdCell.getStringCellValue().equalsIgnoreCase("Y")){
				break;
			}
			
			matchedCol = matchedCol + 1;
		}

		return matchedCol;
	}
	
	private static String getRowXLSX_New(String testDataFile, String sheetName) throws IOException{
		String getTestCase=null;
		int matchedCol=1;
		
		FileInputStream file = new FileInputStream(new File(testDataFile));	
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()){
			Row currentRow = rowIterator.next();
			Cell testCaseIdCell = currentRow.getCell(testCaseIdColNo);
			if(testCaseIdCell.getStringCellValue().equalsIgnoreCase("Y")){
				getTestCase=testCaseIdCell.getStringCellValue();
				break;
			}
			
			matchedCol = matchedCol + 1;
		}

		return getTestCase;
	}
	private static int getRowXLS(String testDataFile, String sheetName) throws IOException{
		int matchedCol = 1;
		
		FileInputStream file = new FileInputStream(new File(testDataFile));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()){
			Row currentRow = rowIterator.next();
			Cell testCaseIdCell = currentRow.getCell(testCaseIdColNo-1);
			/*if(testCaseIdCell.getStringCellValue().equalsIgnoreCase(testCaseId)){
				break;
			}*/
			
			matchedCol = matchedCol + 1;
		}

		return matchedCol;
	}


	public static void main(String[] args) throws IOException {
//		System.out.println(TestData.getRow("C:\\Users\\SatishKumarSundaramo\\git\\Mendix_New\\Mendix\\input\\Mendix_TestPlan.xlsx", "TestPlan"));
		System.out.println(TestData.getRow_New("C:\\Users\\SatishKumarSundaramo\\git\\Mendix_New\\Mendix\\input\\Mendix_TestPlan.xlsx", "TestPlan"));
	}

}
