package com.webMethods.io.Integration.EnableDisableWorkflowsTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumUploadDownloadFilesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class WorkflowsEnableDisableBackLogTests
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

	@Test(priority = 0,groups = {"WorkflowsEnableDisableBackLogTests"},description = "Verify login user : WorkflowsEnableDisableBackLogTests")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver, "Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"WorkflowsEnableDisableBackLogTests"},description = "Verify Check Disable Action functionality")
	public void disableActionTest() 
	{
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.searchProjectTextBox,"BACKLOG_TEST_DATA", webDriver, "Enter BACKLOG_TEST_DATA project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.backlogTestDataProjectName, webDriver, "Click on BACKLOG_TEST_DATA project card");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.searchWorkflowTextBox,"WorkflowEnableDisableTestData", webDriver, "Enter WorkflowEnableDisableTestData name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.workflowCard, webDriver, "Mouseover on workflow card");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.workflowCardEditButtonOption, webDriver, "Click on edit button to edit workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.startAction, webDriver, "Wait for canvas to load");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.loggerAction, webDriver, "Mouse over on logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.moreOption, webDriver,"Click on more options");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.actionDisable, webDriver, "Disable action");
	}

	@Test(priority = 2,dependsOnMethods = {"disableActionTest"},groups = {"WorkflowsEnableDisableBackLogTests"},description = "Verify Check Enable Action functionality")
	public void enableActionTest() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.actionEnable, webDriver, "Enable action");
	}

	@Test(priority = 3,dependsOnMethods = {"enableActionTest"},groups = {"WorkflowsEnableDisableBackLogTests"},description = "Verify Switch and Global Error Handler should not have disable action option")
	public void switchGlobalErrorActionDisableOption() 
	{
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.loggerSwitchAction, webDriver, "Mouse over on logger action used in switch action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.moreOptionLoggerSwitch, webDriver,"Click on more options");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.actionDisable, webDriver, "Disable action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.actionEnable, webDriver, "Enable action");
	}

	@Test(priority = 4,dependsOnMethods = {"switchGlobalErrorActionDisableOption"},groups = {"WorkflowsEnableDisableBackLogTests"},description = "Verify Disabled action log should not appear in Execution Logs")
	public void disableActionExecutionLog() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.playButton, webDriver, "Click on play icon to execute workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.testCompleteMessage, webDriver,"Wait for test complete message");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "D", "Open debug panel");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.executionSourceLocator, webDriver, "Click on workflow execution source to open execution logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.startActionLog, webDriver, "Start action logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.loggerActionLog, webDriver, "Logs action logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.switchActionLog, webDriver, "Switch action logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.stopActionLog, webDriver, "Stop action logs");
	}

	@Test(priority = 5,dependsOnMethods = {"disableActionExecutionLog"},groups = {"WorkflowsEnableDisableBackLogTests"},description = "Verify Check Import/Export with disabled action and FlowService")
	public void importExportDisabledAction() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.leaveCanvas, webDriver, "Click on leave icon to go back workflow dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.addNewWorkflow, webDriver, "Wait for workflow dashbaord to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.projectLink, webDriver, "Click on projectLink to visit dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.createNewProjectButton, webDriver,"Wait for dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.searchProjectTextBox,"Default", webDriver, "Enter default project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.defaultProjectName, webDriver, "Click on Default project card");
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"ActionEnabledDisabled.zip",com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.importWorkflowButton,"Upload .zip file to import workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.importButton, webDriver, "Wait for import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.importButton, webDriver, "Wait for import button to be visible");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.workflowImported, webDriver, "Wait for workflow to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisableWorkflowsTests.WorkflowsEnableDisableBackLogTestsLocators.workflowImported, webDriver, "Verify workflowImported displayed");
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