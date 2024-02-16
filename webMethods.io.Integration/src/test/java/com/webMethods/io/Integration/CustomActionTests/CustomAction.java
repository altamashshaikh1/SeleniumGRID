package com.webMethods.io.Integration.CustomActionTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class CustomAction 
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static SoftAssertWrapper softAssert;

	@BeforeTest
	public void startRemoteWebDriverSession()
	{
		//Set desired capabilities
		capabilities = ThreadLocalDriverFactory.getCapabilities();

		//Initiate ThreadSafe Session
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal, BaseTestUtils.getGridURL(), capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"CustomAction"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"CustomAction"},description="Verify Custom Action configuration test")
	public void ConfigurationTest() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Search workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.projectSearchTextBox2,"ActionConfiguration",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.ActionConfiguration,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.editWorkflow1,webDriver,"Editing project ");

		//Search connector
		//SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.accnamevalidatorcircle, webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Cloudstream,webDriver,"Assert connector visible on canvas");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Cloudstream,webDriver,"Double click on  connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.actionListdropdown,webDriver,"click on the drop down to vissible "); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.actionsearchfield,"test1", webDriver,"Input project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.customOperEditIcon, webDriver, "Click on pencil icon to rename workflow");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.EditCustomOperation,webDriver,"Assert connector visible on canvas");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.YES,webDriver, "Click on yes to continue edit");

		//Add custom action 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.AddCustomAction,webDriver,"Assert custom action page visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Next,webDriver, "Click on next");

		//Operations
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.CompositeRequest,webDriver,"Check composit Request is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.CompositeRequest,webDriver, "Click on next");
		webDriver.findElement(By.xpath("//input[@id='compositeRequest']")).isSelected();
		//com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.compositeRequest.isSelected();
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.CompositeRequestlable,webDriver,"Check composit Request is visible");
		//SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.accnamevalidatorcircle, webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Next,webDriver, "Click on next");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Edit,webDriver, "Click on edit");
		//click(com.actionConfigurationTestPageObjects.Locators.Next,driver, "Click on next");

		//Interactions 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.create,webDriver,"Check for create is visible");
		webDriver.findElement(By.xpath("//input[@id='create']")).isSelected();
		//elementvisible(com.actionConfigurationTestPageObjects.Locators.Createlable,driver,"Check Create Request is visible",softAssertion);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Next,webDriver, "Click on next");

		//Edit Interactions
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.verifyaccount,webDriver,"Check for concerned account");
		webDriver.findElement(By.xpath("//input[@id='AccountPartnerAccount Partner']")).isSelected();
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.BusinessObjectlable,webDriver,"Check Business object Request is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Next,webDriver, "Click on next");

		//Select Fields
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.AccountFromId,webDriver,"Check for AccountFromId Fields ");
		webDriver.findElement(By.xpath("//input[@state='checked']")).isSelected();
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.AccountToId,webDriver,"Check for AccountToId1 Fields");
		webDriver.findElement(By.xpath("//input[@state='checked']")).isSelected();
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.OpportunityId,webDriver,"Check for OpportunityId Fields");
		webDriver.findElement(By.xpath("//input[@state='checked']")).isSelected();
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Next,webDriver, "Click on next");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Next,webDriver, "Click on next");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomActionTests.CustomActionTestLocators.Cancel,webDriver, "Click on Cancel");
	}

	@AfterMethod
	public void testStatusCapture(ITestResult result)
	{
		//Continue dependsOnMethod on soft assertion failures
		if (softAssert.shouldContinueOnFailure() || !result.isSuccess())
		{
			softAssert.assertAll();
		}

		//Collect test status and record as logs
		switch (result.getStatus()) 
		{
		case ITestResult.SUCCESS:
			LoggerUtil.INFO(result.getMethod().getMethodName()+" SUCCESS");
			break;

		case ITestResult.FAILURE:
			LoggerUtil.ERROR(result.getMethod().getMethodName()+" FAILURE. " + result.getThrowable());
			break;

		case ITestResult.SKIP:
			LoggerUtil.DEBUG(result.getMethod().getMethodName()+" SKIP.");
			break;

		default:
			LoggerUtil.DEBUG(result.getMethod().getMethodName()+" Status UNKNOWN.");
			break;
		}
	}

	@AfterTest
	public void endRemoteWebDriverSession()
	{
		ThreadLocalDriverFactory.closeWebBrowser(webDriver);
		ThreadLocalDriverFactory.quitDriver(webDriver);
		ThreadLocalDriverFactory.closeThreadSafeSession(webDriverThreadLocal);
	}
}