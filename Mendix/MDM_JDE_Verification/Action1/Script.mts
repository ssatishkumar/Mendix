StrExcelSheetName = "TestPlan"
StrSheetName = StrExcelSheetName

'Provide TestData excel sheet path
strFilePath = "C:\Users\SatishKumarSundaramo\git\Mendix_New\Mendix\input\Mendix_TestPlan.xlsx"

StrURL_JDE = "http://145.47.230.78:8084/jde/E1Menu.maf"
StrUserName_JDE = "ReddyG02"
StrPassword_JDE = "Welcome@12"

DataTable.AddSheet("TestPlan")
DataTable.ImportSheet strFilePath, StrExcelSheetName, StrSheetName

RowCount =DataTable.GetSheet(StrSheetName).GetRowCount

'RowCount =1

For i = 0 To RowCount		
	DataTable.SetCurrentRow(i)
	
	StrGlobalVendorID =  DataTable.Value("Global_ID", StrSheetName)
	StrExecute = DataTable.Value("Execute", StrSheetName)
	StrTestCase = DataTable.Value("Test_Case", StrSheetName)
	
	If (UCase(StrExecute) = "Y") And (StrTestCase ="Create_Vendor_with_Questionnaire_with_Global_and_Local_and_Bank_JDE") Then
		Call fn_LaunchApplication("iexplore.exe",StrURL_JDE)
		Call Login_JDE_Portal(StrUserName_JDE,StrPassword_JDE)
		Call VerifyMDMGlobalVendorIDInJDE(StrGlobalVendorID)
		Exit For
	Else
		DataTable.Value("Status_JDE", StrSheetName) = "No Run"
	End If
Next

'DataTable.ExportSheet strFilePath, StrSheetName, StrExcelSheetName

'-------------------------------------Fuctions--------------------------------

Public Function VerifyMDMGlobalVendorIDInJDE(StrGlobalVendorID)
	On Error Resume Next
	'Open application 'Create Address Book application'
	Set oJDEHomePage = Browser("name:=JD Edwards").Page("title:=JD Edwards")
	Wait(2)
	oJDEHomePage.Sync
	oJDEHomePage.WebMenu("html id:=menuContainer").WebElement("html id:=drop_mainmenu").Click
	oJDEHomePage.Sync
	oJDEHomePage.WebMenu("html id:=menuContainer").WebTable("text:= B A S E DocumentationTask Profile ","index:=0").Click
	oJDEHomePage.Sync
	oJDEHomePage.WebTable("text:=Manage Master Data.*.","index:=0").Click
	oJDEHomePage.Sync
	oJDEHomePage.WebTable("text:=Manage Suppliers.*.","index:=0").Click
	oJDEHomePage.Sync
	oJDEHomePage.Link("text:=Create Address Book","index:=0").Click
	oJDEHomePage.Sync
	Wait(2)
	
	Set oCreateAddressBookFrame = Browser("name:=Create Address Book.*.").Page("title:=Create Address Book.*.").Frame("html id:=e1menuAppIframe")
	Call WaitForProgressIndicator(oCreateAddressBookFrame)
	
	oCreateAddressBookFrame.WebEdit("title:=Global Vendor ID").Set StrGlobalVendorID
	oCreateAddressBookFrame.WebButton("html id:=hc_Find").Click
	Call WaitForProgressIndicator(oCreateAddressBookFrame)
	If oCreateAddressBookFrame.WebTable("class:=gridrowEven","index:=0").WebCheckBox("name:=grs0_1").Exist(5) Then
		oCreateAddressBookFrame.WebTable("class:=gridrowEven","index:=0").WebCheckBox("name:=grs0_1").Set "ON"
		Wait 1
		ResultReport "PASS", "Search & Select Record","Record has been Selected","Yes"
		oCreateAddressBookFrame.WebButton("html id:=hc_Select").Click
		Call WaitForProgressIndicator(oCreateAddressBookFrame)
		ExpGlobalVendorID = oCreateAddressBookFrame.WebEdit("title:=Global Vendor ID","index:=0").GetROProperty("value")
		If Trim(ExpGlobalVendorID) = StrGlobalVendorID Then
			ResultReport "PASS", "Verify Global Vendor ID","Global Vendor ID found successfully","Yes"
			DataTable.Value("Status_JDE", StrSheetName) = "PASS"
		Else
			ResultReport "FAIL", "Verify Global Vendor ID","Global Vendor ID not matched.","Yes"
			DataTable.Value("Status_JDE", StrSheetName) = "FAIL"
		End If
		oCreateAddressBookFrame.WebButton("html id:=hc_Cancel").Click
		Wait 2
		Call WaitForProgressIndicator(oCreateAddressBookFrame)
		oCreateAddressBookFrame.WebButton("html id:=hc_Close").Click
		Call WaitForProgressIndicator(oJDEHomePage)
	Else
		ResultReport "FAIL", "Search & Select Record","No Record Selected","Yes"
		DataTable.Value("Status_JDE", StrSheetName) = "FAIL"
	End If
End Function

Public Function Login_JDE_Portal(StrUserName,StrPassword)
	On Error Resume Next
	Wait 2
	Set oJDEHomePage = Browser("name:=JD Edwards").Page("title:=JD Edwards")
	oJDEHomePage.WebEdit("html id:=User").Set StrUserName
	oJDEHomePage.WebEdit("html id:=Password").SetSecure StrPassword
	oJDEHomePage.WebButton("value:=Sign In").Click
	wait(5)
	oJDEHomePage.Frame("html id:=RIPaneIFRAME1","Index:=0").Sync
	wait(5)
	If oJDEHomePage.WebMenu("html id:=menuContainer","Index:=0").Exist(60) Then
		ResultReport "PASS","Login to the JDE Application","Successfully logged in to Base JDE application with User : "&StrUserName,"Yes"
	Else
		ResultReport "FAIL","Login to the JDE Application","Not logged in to Base JDE application (Refer the screenshot) with User : "&StrUserName,"Yes"
	End If
	Wait 3
	oJDEHomePage.Sync
End Function

Function fn_LaunchApplication(strBrowserType,strURL)
	On Error Resume Next
	fn_CloseBrowsers()
	Wait 3
	SystemUtil.Run strBrowserType,strURL,,,3
	set oBrowser = Browser("name:=JD Edwards")	
	If oBrowser.Exist Then
		ResultReport "PASS","Launch the application","Application has been launched","Yes"
	Else
		ResultReport "FAIL","Launch the application","Application failed to launch","Yes"
	End If
End Function

Function fn_CloseBrowsers()
	On Error Resume Next
	Reporter.Filter = rfDisableAll
		Set oBrowser = Description.Create
		oBrowser.Add "micClass","Browser"
	Set oBrowserCol = Desktop.ChildObjects(oBrowser)
	For i = 0 to oBrowserCol.Count-1
	  strBrowserName = oBrowserCol(i).GetROProperty("Name")
	  strBrowserTitle = oBrowserCol(i).GetROProperty("title")
	  If instr(1,strBrowserName,"Quality Center",1) = 0   Then
	  	oBrowserCol(i).Close	
	  End If
'	  If instr(1,strBrowserTitle,"JD Edwards",1) = 1   Then
'	  	oBrowserCol(i).Close	
'	  End If
	Next
	Reporter.Filter = rfEnableAll	
End Function


Public Function ResultReport(strStatus,strStep,StrMessage,strScreenshot)
   On Error Resume Next
   strFilepath = CaptureScreenshot("","","")
   If Ucase(Trim(strScreenshot))= "YES" or Ucase(Trim(strScreenshot))= "" Then
   	Call CaptureScreenshot(strStep,strStatus,"")
   End If
   If UCase(Trim(strstatus))="PASS" Then
   	Reporter.ReportEvent micPass,strStep,StrMessage,strFilepath
   ElseIf UCase(Trim(strstatus))="FAIL" Then	   
	Reporter.ReportEvent micFail,strStep,StrMessage,strFilepath
	'ExitTest()
   Else
   	Reporter.ReportEvent micDone,strStep,StrMessage,strFilepath
   End If
End Function

Public Function CaptureScreenshot(strStep,strStatus,strFilepath)
    On error Resume Next
	''Define TimeStamp
	strMonth = month(Date)
	If len(strMonth) = 1 Then
		strMonth = "0" & strMonth
	End If
	
	strDate = day(Date)
	If len(strDate) = 1 Then
		strDate = "0" & strDate
	End If
	
	strHour = hour(Time)
	If len(strHour) = 1 Then
		strHour = "0" & strHour
	End If
	
	strMin = minute(Time)
	If len(strMin) = 1 Then
		strMin = "0" & strMin
	End If
	
	strSec = Second(Time)
	If len(strSec) = 1 Then
		strSec = "0" & strSec
	End If
	
	strTimeStamp = strMonth & strDate &  year(Date) & "_" & strHour & "h" & strMin & "m"&strSec&"s"		
    
	Set oWsh = CreateObject("WScript.Shell")
	strTempFolder = oWsh.ExpandEnvironmentStrings("%Temp%")
	strTempFolderPath = strTempFolder&"\"
	Sheetname=DataTable.LocalSheet.Name
	
	If ucase(strStatus)="PASS" Then
		strScreenShotName= "Pass_"&strStep&"_"&strTimeStamp&".png"
	Elseif ucase(strStatus)="FAIL" then
		strScreenShotName="Fail_"&strStep&"_"&strTimeStamp&".png"
	Else
		strScreenShotName=strTimeStamp&".png"
	End If
	
	strScreenShotFileName=Sheetname&"_"&strScreenShotName
	strFilepath=strTempFolderPath&strScreenShotFileName
      Desktop.CaptureBitmap strFilepath
      CaptureScreenshot = strFilepath
    	err.clear
	On error goto 0
End Function

Function WaitForProgressIndicator(oBrowserPageFrame)
	On Error Resume Next
	Wait 5
	If oBrowserPageFrame.WebTable("innerhtml:=.*af_progressIndicator_indeterminate.*","index:=0").Exist(5) Then
		Do While oBrowserPageFrame.WebTable("innerhtml:=.*af_progressIndicator_indeterminate.*","index:=0").WebElement("class:=af_progressIndicator_indeterminate","visible:=True","index:=0").Exist(3) = True
			Wait(1)
			Counter = Counter+1
			If Counter > 30 Then
	    			Exit Do
	  		End If
		Loop	
	End If
End Function
