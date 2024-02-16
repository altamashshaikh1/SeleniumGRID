package com.webMethods.io.Integration.EmptyNullValuesTests;

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

public class EmptyNullValues  
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

	@Test(priority = 0,groups = {"EmptyNull Values"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"EmptyNull Values"},description="Ability to pass null and empty values to Node.js connectors via UI")
	public void passNullAndEmptyValues() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Search workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectSearchTextBox2,"Okta_Null",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Okta_Null,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.editWorkflow1,webDriver,"Editing project ");

		//Search Okta connector
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Oktaconnector,webDriver,"Assert connector visible on canvas");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Oktaconnector,webDriver,"Double click on  connector");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.UpdateUser,webDriver,"Update user page");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.accnamevalidatorcircle,webDriver,"Wait for action list modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Nextclick,webDriver,"click on next "); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.AllowEmptyValue,webDriver,"Update user page");

		//Check box
		webDriver.findElement(By.xpath("//input[@state='unchecked']")).isSelected();	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.AllowEmptyValue,webDriver,"check box");
		webDriver.findElement(By.xpath("//input[@state='checked']")).isSelected();		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.AllowEmptyValue,webDriver,"check box"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Nextclick,webDriver,"click on next");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Done,webDriver,"click on done");

		//Save Workflow
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.canvasAssets1,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.workSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.workflowSaveMessagedisplay,PropertiesData.readAssertionData("workflowSaveMessage"),webDriver,"Assert project deployment message",softAssert);
		//SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.workflowSaveMessagedisplay,getAssertionData("workflowSaveMessage"), webDriver,"Assert workflow save message");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.workflowSaveMessagedisplay, webDriver, "Wait till workflow save message disappear");

		//Leave canvas
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Copyworkflow, webDriver, "Verify Copyworkflow workflow get display in list");

		//check clone workflow
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectSearchTextBox1,"Copyworkflow",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Copyworkflow,webDriver,"Clicking on Workflow tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.editWorkflow1,webDriver,"Editing Workflow ");

		//Check for connector
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Oktaconnector2,webDriver,"Assert connector visible on canvas");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Oktaconnector2,webDriver,"Double click on  connector");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.UpdateUser2,webDriver,"Update user page");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.accnamevalidatorcircle,webDriver,"Wait for action list modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Nextclick1,webDriver,"Update user tab "); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.AllowEmptyValue1,webDriver,"Update user page");

		//check box
		webDriver.findElement(By.xpath("//input[@state='checked']")).isSelected();
		Thread.sleep(2000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.Cancel,webDriver,"click on next");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.leaveCanvas1,webDriver,"Leave canvas page"); 

	}
	/*	@Test(priority = 2,dependsOnMethods = {"passNullAndEmptyValues"},groups = {"EmptyNull Values"},description="Verifing messaging service promotion deployment")
	public void deploytodestinationTest() 
	{
		webDriver.navigate().to(PropertiesData.readLoginTenantURL("loginDestinationTenantURL"));
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.ssoLoginLink,webDriver,"Click on SSO login link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectSearchTextBox,PropertiesData.readInputData("ProjectName2"),webDriver,"Input published project name");       
		SeleniumWebElementsUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EmptyNullValuesTests.EmptyNullValuesLocators.projectDeployedName,webDriver,"Assert project deployed is shown");
	}
	 */	

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