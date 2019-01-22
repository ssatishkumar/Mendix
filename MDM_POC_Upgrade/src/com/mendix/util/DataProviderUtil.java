package com.mendix.util;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.mendix.util.*;
import com.mendix.tool.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class DataProviderUtil.
 */
public class DataProviderUtil {
	
	/**
	 * The Class staticProviderClass.
	 */
	public static class staticProviderClass{
		
		/**
		 * Material creation.
		 *
		 * @return the iterator
		 */
		@DataProvider(name="HeiPort_Login",parallel=false)
		public static Iterator<Object[]> HeiPort_Login(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("input/Mendix-"+Constants.ENV+Constants.EXCEL_FORMAT, "MaterialPage");
			return testData;
		}
		
		
		
		/**
		 * Material creation Fill in.
		 *
		 * @return the iterator
		 */
		
		@DataProvider(name="CreateMaterial_Fill_In_draft",parallel=false)
		public static Iterator<Object[]> MaterialPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\data\\Global_Material_Data.xlsx", "YROH");
			return testData;
		}
		
		@DataProvider(name="CreateMaterial_Fill_In_Rejection",parallel=false)
		public static Iterator<Object[]> MaterialPageRejection(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\data\\Global_Material_Data.xlsx", "YROH");
			return testData;
		}
		
		@DataProvider(name="Vendor_Create_Global_Disable_Bank_and_LocalData",parallel=false)
		public static Iterator<Object[]> VendorPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\data\\Global_Vendor_Data.xlsx", "Y001");
			return testData;
		}
		/**
		 * Change material.
		 *
		 * @return the iterator
		 */
		@DataProvider(name="Process_Information_Check",parallel=false)
		public static Iterator<Object[]> ProcessInfoPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm", "MaterialPage");
			return testData;
		}
		
		@DataProvider(name="Process_Information_Check_Reject",parallel=false)
		public static Iterator<Object[]> ProcessInfoPageReject(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm", "MaterialPage");
			return testData;
		}
		
		@DataProvider(name="Process_Information_Check_Reject_LDR",parallel=false)
		public static Iterator<Object[]> ProcessInfoPageRejectLDR(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm", "MaterialPage");
			return testData;
		}
		
		@DataProvider(name="Process_Information_Check_Material_Reject",parallel=false)
		public static Iterator<Object[]> ProcessInfoPageRejectMaterial(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\Mendix-MDM.xlsm", "MaterialPage");
			return testData;
		}
		
		/*@DataProvider(name="CreateMaterial_Fill_In",parallel=false)
		public static Iterator<Object[]> MaterialApprovalPage(){
			Iterator<Object[]> testData=ExcelUtil.getTestData("C:\\MDM_Workspace\\MDM_POC_Upgrade\\input\\data\\Global_Material_Data.xlsx", "YROH");
			return testData;
		}*/
		
		
		
	}
	
	
	
}
