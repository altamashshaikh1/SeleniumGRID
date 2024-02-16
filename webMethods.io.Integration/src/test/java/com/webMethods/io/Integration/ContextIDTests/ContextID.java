package com.webMethods.io.Integration.ContextIDTests;

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

public class ContextID 
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
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal,BaseTestUtils.getGridURL(), capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"ContextID"},description = "Login user : ContextID")
	public void userLogin() 
	{		
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.ContextIDTests.ContextIDLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}	

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execute WF1 with Context ID = Context1")
	public void ExecuteWF1() throws InterruptedException 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.projectSearchBox,"ContextID", webDriver,"Enter WF_MESSAGING_BACKLOG project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createdWF1, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.mouseHoverWF1, webDriver, "Mousehover WF1");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.EditWF1, webDriver, "Click on EditWF1");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF1Pencil, webDriver, "Wait WF1 pencil to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowPlayButton, webDriver, "Click on Play Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionStartMessage,"Workflow testing started.", webDriver,"Assert workflow execution start message", softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionCompletedMessage,"Workflow testing completed.", webDriver,"Assert workflow execution complete message", softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.BackIcon, webDriver, "Click on back button to exit from WF1");
	}	

	@Test(priority = 2,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execute WF2 with Context ID = Context2")
	public void ExecuteWF2() throws InterruptedException 	
	{	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createdWF2, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.mouseHoverWF2, webDriver, "Mousehover WF2");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.EditWF2, webDriver, "Click on EditWF2");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF2Pencil, webDriver, "Wait WF2 pencil to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowPlayButton, webDriver, "Click on Play Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionStartMessage,"Workflow testing started.", webDriver,"Assert workflow execution start message", softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionCompletedMessage,"Workflow testing completed.", webDriver,"Assert workflow execution complete message", softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.BackIcon, webDriver, "Click on back button to exit from WF2");
	}	

	@Test(priority = 2,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execute WF3 with Context ID = Context3")
	public void ExecuteWF3() throws InterruptedException	
	{	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createdWF3, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.mouseHoverWF3, webDriver, "Mousehover WF2");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.EditWF3, webDriver, "Click on EditWF2");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF3Pencil, webDriver, "Wait WF2 pencil to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowPlayButton, webDriver, "Click on Play Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionStartMessage,"Workflow testing started.", webDriver,"Assert workflow execution start message", softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionCompletedMessage,"Workflow testing completed.", webDriver,"Assert workflow execution complete message", softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.BackIcon, webDriver, "Click on back button to exit from WF3");
	}	

	@Test(priority = 3,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execute WF4 with Context ID = Context4")
	public void ExecuteWF4() throws InterruptedException
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createdWF4, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.mouseHoverWF4, webDriver, "Mousehover WF4");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.EditWF4, webDriver, "Click on EditWF4");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF4Pencil, webDriver, "Wait WF4 pencil to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowPlayButton, webDriver, "Click on Play Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionStartMessage,"Workflow testing started.", webDriver,"Assert workflow execution start message", softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionCompletedMessage,"Workflow testing completed.", webDriver,"Assert workflow execution complete message", softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.BackIcon, webDriver, "Click on back button to exit from WF4");
	}	

	@Test(priority = 4,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execute WF5 with Context ID = Context1")
	public void ExecuteWF5() throws InterruptedException
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createdWF5, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.mouseHoverWF5, webDriver, "Mousehover WF5");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.EditWF5, webDriver, "Click on EditWF5");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF5Pencil, webDriver, "Wait WF5 pencil to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowPlayButton, webDriver, "Click on Play Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionStartMessage,"Workflow testing started.", webDriver,"Assert workflow execution start message", softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionCompletedMessage,"Workflow testing completed.", webDriver,"Assert workflow execution complete message", softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.BackIcon, webDriver, "Click on back button to exit from WF5");
	}

	@Test(priority = 5,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execute WF6 with Context ID = Context12")
	public void ExecuteWF6() throws InterruptedException
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.createdWF6, webDriver, "Wait recipes import button to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.mouseHoverWF6, webDriver, "Mousehover WF6");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.EditWF6, webDriver, "Click on EditWF6");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF6Pencil, webDriver, "Wait WF6 pencil to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowPlayButton, webDriver, "Click on Play Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionStartMessage,"Workflow testing started.", webDriver,"Assert workflow execution start message", softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.workflowExecutionCompletedMessage,"Workflow testing completed.", webDriver,"Assert workflow execution complete message", softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.BackIcon, webDriver, "Click on back button to exit from WF6");
	}

	@Test(priority = 6,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Workflow Execution Page")
	public void WorkflowExecutionPage() throws InterruptedException
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.MonitorPage, webDriver, "Click on Monitor Page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WorkflowExecution, webDriver, "Click on Workflow Executions Page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ExecutionsTab, webDriver, "Wait for executions tab to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.SettingsIcon, webDriver, "Click on Settings icon in executions tab");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ContextIDCheckBox, webDriver, "Verify Context ID checkbox is present");
	}

	@Test(priority = 7,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Filter workflows with given Context ID")
	public void FilterWorkflow() throws InterruptedException
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ExpandFilterIcon, webDriver, "Click on Expand Filter");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ContextIDSearchBox, webDriver, "Verify Context ID searchbox is present");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ContextIDSearchBox,"Context12", webDriver,"Enter Context12 to be searched");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ApplyButton, webDriver, "Click on Apply Button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ListSearchedContextID, webDriver, "Verify WF6 with Context ID Context12 is listing");
	}

	@Test(priority = 8,dependsOnMethods = {"userLogin"},groups = {"ContextID"},description = "Execution logs of WF6 with Context ID Context12")
	public void WF6ExecutionLogs() throws InterruptedException
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF6HavingContextIDContext12, webDriver, "Click on WF6 in executions page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF6ExecutionLogs, webDriver, "Wait for WF6 to be visible in WF6 execution logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.ContextIDExecutionLogs, webDriver, "Verify Context ID is present in WF6 execution logs page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ContextIDTests.ContextIDLocators.WF6ContextIDInExecutionLogs, webDriver, "Verify WF6 Context ID Context12 is present for WF6 in execution logs page");
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