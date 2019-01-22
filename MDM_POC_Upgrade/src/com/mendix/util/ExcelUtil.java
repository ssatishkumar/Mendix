package com.mendix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelUtil.
 */
public class ExcelUtil {

private static XSSFWorkbook workbook;

/**
 * Gets the test data.
 *
 * @param strWorkbookPath the str workbook path
 * @param strWorksheetName the str worksheet name
 * @return the test data
 */
public static Iterator<Object[]> getTestData(String strWorkbookPath,String strWorksheetName){
	List<Object[]> data = new ArrayList<Object[]>();
	
	try{
		FileInputStream file = new FileInputStream(new File(strWorkbookPath));
    
		workbook = new XSSFWorkbook(file);
		
		//Get first sheet from the workbook
		XSSFSheet sheet = workbook.getSheet(strWorksheetName);
	
		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		Row firstRow=rowIterator.next();
	
		Map<String,String> columnNamesMap=getColumnNames(firstRow);
		
		while(rowIterator.hasNext()){
			Iterator<Cell> cellIterator=rowIterator.next().cellIterator();
			Map<String,String> rowMap=new LinkedHashMap<String, String>();
			for(Entry<?, ?> entry:columnNamesMap.entrySet()){
				String strColumnName=entry.getKey().toString();
				String strValue="";
			    try{
			     Cell cell=cellIterator.next();
			     if(cell!=null){strValue=cell.toString();}
			    }catch(Exception e){}
				rowMap.put(strColumnName, strValue.trim());
			}
			
			if(rowMap.get("Execute").equalsIgnoreCase("Y")){	
				//rowMap.put("Iteration", ""+inRowCounter);
				data.add(new Object[]{rowMap});
				//inRowCounter++;
			}
		}
		
		file.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return data.iterator();
}

/**
 * Gets the column names.
 *
 * @param row the row
 * @return the column names
 */
private static Map<String,String> getColumnNames(Row row){
	Map<String,String> columnNamesMap=new LinkedHashMap<String, String>();
	
	Iterator<Cell> cells=row.cellIterator();
	
	while(cells.hasNext()){
		String strColumnName=cells.next().toString();
		columnNamesMap.put(strColumnName, strColumnName);
	}
	
	return columnNamesMap;
}

public static void excelWrite(String globalId)
		throws FileNotFoundException, IOException {
	try {
		FileInputStream fis = new FileInputStream("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//call the getSheet() method of Workbook and pass the Sheet Name here. 
		//In this case I have given the sheet name as “TestData” 
		//or if you use the method getSheetAt(), you can pass sheet number starting from 0. Index starts with 0.
		XSSFSheet sheet = workbook.getSheet("MaterialPage");
		//XSSFSheet sheet = workbook.getSheetAt(0);
		//Now create a row number and a cell where we want to enter a value. 
		//Here im about to write my test data in the cell B2. It reads Column B as 1 and Row 2 as 1. Column and Row values start from 0.
		//The below line of code will search for row number 2 and column number 2 (i.e., B) and will create a space. 
		//The createCell() method is present inside Row class.
		//		Row row = sheet.c
		//		Cell cell = row.createCell(1);
		Cell cell = sheet.getRow(1).getCell(3);
		//Now we need to find out the type of the value we want to enter. 
		//If it is a string, we need to set the cell type as string 
		//if it is numeric, we need to set the cell type as number
//		cell.setCellType(cell.CELL_TYPE_STRING);
		cell.setCellValue(globalId);
		FileOutputStream fos = new FileOutputStream("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm");
		workbook.write(fos);
		fos.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void excelWriteState(String globalId)
		throws FileNotFoundException, IOException {
	try {
		FileInputStream fis = new FileInputStream("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\Mendix_DataSheet.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//call the getSheet() method of Workbook and pass the Sheet Name here. 
		//In this case I have given the sheet name as “TestData” 
		//or if you use the method getSheetAt(), you can pass sheet number starting from 0. Index starts with 0.
		XSSFSheet sheet = workbook.getSheet("MDM");
		//XSSFSheet sheet = workbook.getSheetAt(0);
		//Now create a row number and a cell where we want to enter a value. 
		//Here im about to write my test data in the cell B2. It reads Column B as 1 and Row 2 as 1. Column and Row values start from 0.
		//The below line of code will search for row number 2 and column number 2 (i.e., B) and will create a space. 
		//The createCell() method is present inside Row class.
		//		Row row = sheet.c
		//		Cell cell = row.createCell(1);
		Cell cell = sheet.getRow(1).getCell(3);
		//Now we need to find out the type of the value we want to enter. 
		//If it is a string, we need to set the cell type as string 
		//if it is numeric, we need to set the cell type as number
//		cell.setCellType(cell.CELL_TYPE_STRING);
		cell.setCellValue(globalId);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\Mendix_DataSheet.xlsx");
		workbook.write(fos);
		fos.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public static void setCellData(String sheetName, String colName, int rowNum, String value)
    {
        try
        {
        	FileInputStream fis = new FileInputStream("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\Mendix_DataSheet.xlsx");
    		XSSFWorkbook workbook = new XSSFWorkbook(fis);
        	int col_Num = -1;
            XSSFSheet sheet = workbook.getSheet(sheetName);

            XSSFRow row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                {
                    col_Num = i;
                }
            }

            sheet.autoSizeColumn(col_Num);
            row = sheet.getRow(rowNum - 1);
            if(row==null)
                row = sheet.createRow(rowNum - 1);

            XSSFCell cell = row.getCell(col_Num);
            if(cell == null)
                cell = row.createCell(col_Num);

            cell.setCellValue(value);

            FileOutputStream fos = new FileOutputStream("C:\\Users\\IBM_ADMIN\\Documents\\Documents\\Mendix_UFT\\Mendix_DataSheet.xlsx");
            workbook.write(fos);
            fos.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
//            return  false;
        }
//        return true;
    }
	
	public static void excelWriteGlobalId(String globalId)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\input\\Mendix-MDM.xlsm");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("MaterialPage");
			Cell cell = sheet.getRow(1).getCell(4);
			cell.setCellValue(globalId);
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\input\\Mendix-MDM.xlsm");
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		/*System.setProperty("webdriver.gecko.driver", "C:\\MDM_Workspace\\MDM_POC_Upgrade\\drivers\\geckodriver.exe");

		DesiredCapabilities capabilities=DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);*/
		
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		DesiredCapabilities ieCap=new DesiredCapabilities();
    	ieCap.setCapability("ignoreZoomSetting", true);
    	ieCap.setCapability("requireWindowFocus", true);
    	ieCap.setCapability("enablePersistentHover", false);
        WebDriver driver = new InternetExplorerDriver(ieCap);
		driver.get("https://w3.ibm.com/");
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(-2000, 0));
        
        driver.manage().window().maximize();
		
	}

}
