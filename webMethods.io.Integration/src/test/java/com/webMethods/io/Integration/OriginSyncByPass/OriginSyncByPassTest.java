package com.webMethods.io.Integration.OriginSyncByPass;

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
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class OriginSyncByPassTest
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
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal,"http://localhost:4444", capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"OriginSyncByPass"},description = "Login user : OriginSyncByPass")
	public void userLogin() 
	{		
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"OriginSyncByPass"},description = "Verify Sync of Workflow")
	public void OriginSync()  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.projectSearchBox,"DO_NOT_DELETE", webDriver,"Enter DO_NOT_DELETE project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.createdWF, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.mouseHoverWF, webDriver, "Mousehover WF1");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.EditWF, webDriver, "Click on EditWF1");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.WFPencil, webDriver, "Wait WF pencil to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.OriginFlowConnector, webDriver, "Mousehover Origin Flow Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.OriginFlowConnectorSetting, webDriver, "Click on Origin Flow connector setting");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.OriginFlowConnectorInfo, webDriver, "Wait for Origin Flow Connector to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.NextButton, webDriver, "Click on Next Button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.ActionConfigure, webDriver, "Wait for Action Configure to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.NextButton, webDriver, "Click on Next Button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.TestThisAction, webDriver, "Wait for Test this Action to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.SyncButton, webDriver, "Click on Sync Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.OriginSyncByPass.OriginSyncByPassLocators.SuccessfullySynced, webDriver, "Wait for Successfully synced to be visible");	
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