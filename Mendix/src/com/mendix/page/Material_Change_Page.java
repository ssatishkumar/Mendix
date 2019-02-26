package com.mendix.page;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mendix.tool.Button;
import com.mendix.tool.Constants;
import com.mendix.tool.Sync;
import com.mendix.util.ExcelUtil;


public class Material_Change_Page {

	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new home page changes
	 *
	 * @param driver the driver
	 */
	public Material_Change_Page(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	@FindBy(how=How.XPATH, using="//*[text()='Edit Global Data']/../div/input")
	WebElement checkBoxEdit;
	
	@FindBy(how=How.XPATH, using="//button[text()='Edit']")
	WebElement btnEdit;
	
	@FindBy(how=How.XPATH, using="//button[text()='Extend']")
	WebElement btnExtend;
	
	@FindBy(how=How.XPATH, using="//button[text()='Flag For Deletion']")
	WebElement btnDelete;
	
	
	@FindBy(how=How.XPATH, using="//button[text()='Create With Reference']")
	WebElement btnCreateRef; 
	
	@FindBy(how=How.XPATH, using=".//span[@class='glyphicon glyphicon-flag']")
	WebElement btnFlag; 
	@FindBy(how=How.XPATH, using=".//span[@class='glyphicon glyphicon-ok']")
	WebElement btnExtension; 
	@FindBy(how=How.CSS, using=".btn.btn-primary")
	WebElement btnOkay; 
	public void clickEditCheckBox() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", checkBoxEdit);
		Button.click("Click Materials Menu", checkBoxEdit);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
	}
	
	public void clickEditbutton() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", btnEdit);
		Button.click("Click Edit Button", btnEdit);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
	}

	public void clickExtendbutton() {
		Sync.waitForSeconds(Constants.WAIT_5);
		//Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", btnEdit);
		Button.click("Click Extend Button", btnExtend);
		Sync.waitForSeconds(Constants.WAIT_10);
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMinutes(4))
		        .pollingEvery(Duration.ofSeconds(600))
		        .ignoring(NoSuchElementException.class)
		        .ignoring(TimeoutException.class);
		//Sync.waitForSeconds(Constants.WAIT_5);
	}

	public void clickReferencebutton() {
		WebDriverWait wait = new WebDriverWait(driver,100);
		Sync.waitForSeconds(Constants.WAIT_5);
		//Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		
		Sync.waitForObject(driver, "Wait until the Material appears", btnCreateRef);
		Sync.waitForSeconds(Constants.WAIT_5);
		
		
		Button.click("Click Materials Menu", btnCreateRef);
		Sync.waitForSeconds(Constants.WAIT_2);
		
  } 
	/*public void clickReferencebutton() {
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitUntilObjectDisappears(driver, "Wait for Materials", By.xpath((".//*[@id='mxui_widget_Progress_0']/div[2]")));
		Sync.waitForObject(driver, "Wait until the Material appears", btnCreateRef);
		Button.click("Click Materials Menu", btnCreateRef);
		Sync.waitForSeconds(Constants.WAIT_5);
		Sync.waitForSeconds(Constants.WAIT_5);
		} 
	
	
    public void clickFlagForDeletionButton(){
    	
    	Sync.waitForSeconds(Constants.WAIT_5);
    	Sync.waitForSeconds(Constants.WAIT_5);
    	Sync.waitForObject(driver, "Wait until the Material appears", btnDelete);
		Button.click("Delete Material", btnDelete);
		
	}
    public void clickFlagForDeletion() {
    	Sync.waitForSeconds(Constants.WAIT_5);
    	Sync.waitForSeconds(Constants.WAIT_5);
    	//Sync.waitForSeconds(Constants.WAIT_5);
    	
    	JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("$(\".mx-layoutcontainer-wrapper.mx-scrollcontainer-wrapper\").animate({ scrollTop: \"60px\" })");
		Sync.waitForSeconds(Constants.WAIT_5);
    	Sync.waitForObject(driver, "Wait until the Material appears", btnFlag);
		Button.click("Delete Material", btnFlag);
		
    }
    public void clickConfirmExtension() {
    	Sync.waitForSeconds(Constants.WAIT_5);
    	//Sync.waitForSeconds(Constants.WAIT_1);
    	//Sync.waitForSeconds(Constants.WAIT_5);
    	Sync.waitForObject(driver, "Wait until the Material appears", btnFlag);
		Button.click("Confirm Extension", btnExtension);
    }*/
    
    public  String getGlobalIdNew() throws FileNotFoundException, IOException {
		Sync.waitForSeconds(Constants.WAIT_3);
		
//		Sync.waitForSeconds(Constants.WAIT_2);
	WebElement waitElement = null;
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofMinutes(3))
		        .pollingEvery(Duration.ofSeconds(600))
		        .ignoring(NoSuchElementException.class)
		        .ignoring(TimeoutException.class);
		 
		//First checking to see if the loading indicator is found
		// we catch and throw no exception here in case they aren't ignored
		try {
		  waitElement = fwait.until(new Function<WebDriver, WebElement>() {
		   public WebElement apply(WebDriver driver) {
		      return driver.findElement(By.xpath(".//*[@id='mxui_widget_Progress_0']"));
		   }
		 });
		    } catch (Exception e) {
		   }
		 
		//checking if loading indicator was found and if so we wait for it to
		//disappear
		  if (waitElement != null) {
		      WebDriverWait wait = new WebDriverWait(driver, 30);
		      wait.until(ExpectedConditions.visibilityOfElementLocated(
		    		  By.cssSelector("tr > td.mx-name-column2.mx-right-aligned > div"))
		    		           
		            );
		        }
//		Sync.waitForObject(driver, "Wait for Global Material Id", driver.findElement(By.cssSelector("tr > td.mx-name-column2.mx-right-aligned > div")));
		//String globalLock=driver.findElement(By.cssSelector("tr > td.mx-name-column17.mx-left-aligned > div")).getText();
		String globalLockState=driver.findElement(By.xpath("//*[text()='Global Lock']/../../../../../../table[2]/tbody[1]/tr[1]/td[1]/div")).getText();
		System.out.println(globalLockState);
		if(globalLockState.equalsIgnoreCase("NO")){
	     clickEditCheckBox();
	     clickEditbutton();    
	     
		}
		
		else if(globalLockState.equalsIgnoreCase("Yes")){
		
		//String globalLockStateNew=driver.findElement(By.xpath("//*[text()='Global Lock']/../../../../../../table[2]/tbody[1]/tr[1]/td[1]/div")).getText();
			System.out.println(globalLockState);
			
		}
		
		
		String globalId=driver.findElement(By.cssSelector("tr > td.mx-name-column2.mx-right-aligned > div")).getText();
		System.out.println(globalId);
		ExcelUtil.excelWriteGlobalId(globalId);
		return globalId;
	}



	

}
	
	
