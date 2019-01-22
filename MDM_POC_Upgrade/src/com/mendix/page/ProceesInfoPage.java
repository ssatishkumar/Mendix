package com.mendix.page;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.xalan.xsltc.compiler.sym;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Sync;
import com.mendix.tool.Textbox;
import com.mendix.util.ExcelUtil;

public class ProceesInfoPage {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new process Info page.
	 *
	 * @param driver the driver
	 */
	public ProceesInfoPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}



	@FindBy(how=How.CSS, using="div[id^='mxui_widget_NumberInput_'][class^='mx-name-textBox2'] :nth-child(1)")
	WebElement txtboxRequestId;

	@FindBy(how=How.XPATH, using=".//*[@class='glyphicon glyphicon-search']")
	WebElement btnReqIdSearch;

	@FindBy(how=How.XPATH, using="//a[contains(text(),'Process Information')]")
	WebElement menuProcessInfo;

	@FindBy(how=How.XPATH, using="(//*[starts-with(text(),' Process Search')])[1]")
	WebElement menuProcessInfoSearch;

	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnMsgReqIdOk;

	@FindBy(how=How.XPATH, using="//*[@class='glyphicon glyphicon-refresh']")
	WebElement btnReqIDClear;

	@FindBy(how=How.XPATH, using="(//*[text()='State'])[2]/../../../../../../table[2]//td[9]")
	WebElement txtStatus;
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


	public boolean reqIdSearch(String strValue) throws InterruptedException, FileNotFoundException, IOException {

		driver.switchTo().window("Application");
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, "Wait for Request Id", txtboxRequestId);
		Textbox.click("Click Request Id Text Box", txtboxRequestId);
		Textbox.clear("Clear Request Id Text Box", txtboxRequestId);
		Textbox.enterValue("Enter Request Id", txtboxRequestId, strValue);
		Sync.waitForSeconds(Constants.WAIT_5);
		return Button.click("Click Request Id Search Button", btnReqIdSearch);
	}

	public boolean processInfoSearch() {

		Sync.waitForObjectFluent(driver, menuProcessInfo);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObjectFluent(driver, menuProcessInfo);
//		Sync.waitForElementToBeClickable(driver, menuProcessInfo);
		Button.click("Click the Process Info Menu", menuProcessInfo);
		Sync.waitForElementToBeClickable(driver, menuProcessInfoSearch);
		return Button.click("Click the Procees info search menu", menuProcessInfoSearch);
	}

	public String getState(String strValue) throws InterruptedException{
		Sync.waitForSeconds(Constants.WAIT_6);
		Sync.waitForSeconds(Constants.WAIT_3);
		Sync.waitForObject(driver, "Wait for the status to display", txtStatus);
		Thread.sleep(30000);
		String state=driver.findElement(By.xpath(".//*[text()='"+strValue+"']/../../td[9]/div")).getText();
		System.out.println(state);
//		ExcelUtil.setCellData("MDM", "Level2", 2, state);
		return state;
	}
	
	
	public String ValidateStateLDR(String strValue){
		
		String state=driver.findElement(By.xpath(".//*[text()='"+strValue+"']/../../td[9]/div")).getText();
		
		Assert.assertEquals(state, "LDR");
		
		return state;
	}

	public String ValidateStateDiscarded(String strValue){
		
		String state=driver.findElement(By.xpath(".//*[text()='"+strValue+"']/../../td[9]/div")).getText();
		
		Assert.assertEquals(state, "Discarded");
		
		return state;
	}
	
	public String ValidateStateGBDA(String strValue){
		
		String state=driver.findElement(By.xpath(".//*[text()='"+strValue+"']/../../td[9]/div")).getText();
		
		Assert.assertEquals(state, "GDA");
		
		return state;
	}
	public void browserClose() {
		Sync.waitForSeconds(Constants.WAIT_2);
		Sync.waitForSeconds(Constants.SCRIPT_WAIT_TIME);
		driver.close();
		driver.quit();
		
	}

}
