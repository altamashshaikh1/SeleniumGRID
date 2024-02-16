package com.webMethods.io.Integration.SyncWorkFlowXAccelTests;

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

public class SyncWorkflowXaccel 
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

	@Test(priority = 0,groups = {"SyncWorkFlowXaccel : DailyPV TestSuite"},description = "Login user : SyncWorkFlowXaccel")
	public void userLogin() 
	{		
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.SyncWorkFlowXAccelFeature.SyncWorkflowXaccelLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"SyncWorkFlowXaccel : DailyPV TestSuite"},description = "Verify sync v2 url in edit webhook page")
	public void EditWebhook()  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.projectSearchBox,"ContextID", webDriver,"Enter WF_MESSAGING_BACKLOG project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.createdWF, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.mouseHoverWF, webDriver, "Mousehover WF1");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.EditWF, webDriver, "Click on EditWF1");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WFPencil, webDriver, "Wait WF pencil to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WebhookTrigger, webDriver, "Mousehover webhook trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WebhookSetting, webDriver, "Click on Webhook connector setting");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WebhookURL, webDriver, "Wait for webhook URL to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.Syncv2Url, webDriver,"Verify sync v2 url is present or not");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.Syncv2UpdatedURLNote, webDriver,"Verify Updated Url note is present or not");		
	}

	@Test(priority = 2,dependsOnMethods = {"userLogin"},groups = {"SyncWorkFlowXaccel : DailyPV TestSuite"},description = "Verify sync v2 url in events page")
	public void EventsPageWebhook()  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.CloseButton, webDriver, "Click on Close Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.BackButton, webDriver, "Click on Back button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.createdWF, webDriver, "Wait recipes import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.EventsPage, webDriver, "Click on Events Page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WebhookTabEventsPage, webDriver, "Wait for Webhhok field to be visible in events page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WebhookToggleEventsPage, webDriver, "Expand webhook tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.CreatedWFInEventsPage, webDriver, "Wait for X accel workflow to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.WebhookURLInfoTipEventsPage, webDriver, "Mousehover info tip on webhook section in Events Page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SyncWorkFlowXAccelTests.SyncWorkflowXaccelLocators.URLInfoEventsPage, webDriver,"Verify Updated Url note is present or not");
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