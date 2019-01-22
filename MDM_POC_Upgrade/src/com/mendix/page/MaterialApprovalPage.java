package com.mendix.page;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Sync;
import com.mendix.tool.Textbox;
import com.mendix.util.ExcelUtil;

public class MaterialApprovalPage {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new process Info page.
	 *
	 * @param driver the driver
	 */
	public MaterialApprovalPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}



	@FindBy(how=How.CSS, using="div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)")
	WebElement txtboxRequestId;

	@FindBy(how=How.XPATH, using=".//*[@class='glyphicon glyphicon-search']")
	WebElement btnReqIdSearch;

	@FindBy(how=How.XPATH, using=".//*[text()='Search']")
	WebElement btnReqIdMyTaskSearch;

	@FindBy(how=How.XPATH, using="//label[text()='Request ID']/../../div[2]/input")
	WebElement txtboxReqIdSearch;

	@FindBy(how=How.XPATH, using="(.//button[text()='Search'])[1]")
	WebElement btnReqIdEnterSearch;

	@FindBy(how=How.XPATH, using=".//button[text()='Open Task']")
	WebElement btnOpenTask;

	@FindBy(how=How.XPATH, using="//a[text()=' My Tasks']")
	WebElement menuMyTask;

	@FindBy(how=How.XPATH, using="//div[@class='mx-placeholder']/button")
	WebElement btnlocalAction;

	@FindBy(how=How.XPATH, using=".//*[@class='btn mx-button mx-name-actionButton11 btn-success']")
	WebElement btnGDAApproval;

	@FindBy(how=How.XPATH, using="//*[text()='Proceed']")
	WebElement btnProceed;

	@FindBy(how=How.XPATH, using=".//button[text()='Submit Global Request']")
	WebElement btnGlobalRequest;
	/**
	 * Enter UserName.
	 * Enter Password
	 * 
	 * Click Login
	 *
	 * @param strMenuName the str menu name
	 * @return 
	 * @return true, if successful
	 */


	public void reqIdSearchMyTasks(String strValue) 

	{
		Sync.waitForSeconds(Constants.WAIT_2);
		//		Thread.sleep(2000);

		driver.switchTo().window("Application");

		Sync.waitForElementToBeClickable(driver, menuMyTask);
		/*WebElement elementt = driver3.findElement(By.xpath(".//*[text()='Search']"));
		waitForElementToBeClickable(driver3, elementt);*/
		Button.jsclick("Search My task", menuMyTask, driver);

		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitUntilObjectDisappears(driver, "Wait My taska to load", By.xpath(".//*[@id='mxui_widget_Progress_0']/div[2]"));

		Sync.waitForElementToBeClickable(driver, btnReqIdMyTaskSearch);
		/*WebElement elementt = driver3.findElement(By.xpath(".//*[text()='Search']"));
		waitForElementToBeClickable(driver3, elementt);*/
		Button.jsclick("Search My task", btnReqIdMyTaskSearch, driver);
		Sync.waitForSeconds(Constants.WAIT_1);
		/*JavascriptExecutor executorr1 = (JavascriptExecutor)driver3;
		executorr1.executeScript("arguments[0].click();", elementt);*/

		//		driver3.findElement(By.xpath("")).click();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(txtboxReqIdSearch));
		Textbox.click("Click Enter Request Id", txtboxReqIdSearch);
		Sync.waitForSeconds(Constants.WAIT_1);
		//		driver3.findElement(By.xpath("//label[text()='Request ID']/../../div[2]/input")).click();

		Textbox.enterValue("Enter Request Id textbox", txtboxReqIdSearch, strValue);
		Sync.waitForSeconds(Constants.WAIT_1);
		//		driver3.findElement(By.xpath("//label[text()='Request ID']/../../div[2]/input")).sendKeys(Id);

		Button.click("Click Search Button", btnReqIdEnterSearch);
		Sync.waitForSeconds(Constants.WAIT_1);
		//		driver3.findElement(By.xpath("(.//button[text()='Search'])[1]")).click();

		Button.click("Click Open Task", btnOpenTask);
		//		return false;
		Sync.waitForSeconds(Constants.WAIT_3);
	}

	public boolean approvalBtnClick()
	{

		Sync.waitForElementToBeClickable(driver, btnlocalAction);
		Button.click("Click Local Action button", btnlocalAction);
		return Button.click("Click Approval button", btnGDAApproval);
	}

	public boolean submitGlobalRequest()
	{

		Sync.waitForElementToBeClickable(driver, btnlocalAction);
		Button.click("Click Local Action button", btnlocalAction);
		return Button.click("Click Approval button", btnGlobalRequest);
	}
	
	public boolean RejectBtnClick()
	{

		Sync.waitForElementToBeClickable(driver, btnlocalAction);
		Button.click("Click Local Action button", btnlocalAction);
		return Button.click("Click Approval button", btnGDAApproval);
	}


	public boolean duplicateCheck() {
		try {

			Sync.waitForSeconds(Constants.SCRIPT_WAIT_TIME);
			//	((JavascriptExecutor)driver3).executeScript("return jQuery('button:(*\'Confirm and Approve\'*)')");


			WebElement element5 = driver.findElement(By.xpath("(.//*[@class='glyphicon glyphicon-ok'])[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element5);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element5);

			return Button.click("Click Proceed button", btnProceed);
			//			driver3.findElement(By.xpath("//*[text()='Proceed']")).click();
		}
		catch(Exception e) {
			return Button.click("Click Proceed button", btnProceed);
			//			System.err.println(e.getMessage());


		}
	}
}
