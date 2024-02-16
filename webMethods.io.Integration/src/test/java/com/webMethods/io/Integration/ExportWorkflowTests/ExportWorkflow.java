package com.webMethods.io.Integration.ExportWorkflowTests;

import org.openqa.selenium.JavascriptExecutor;
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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class ExportWorkflow 
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

	@Test(priority = 0,groups = {"CustomNodeJSCLIConnector ExportWorkflow"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"CustomNodeJSCLIConnector ExportWorkflow"},description = "Verify workflow export with NodeJS CLI connector")
	public void exportWorkflow() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.projectSearchBox,"Backlog NodeJS CLI", webDriver,"Enter Backlog NodeJS CLI project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.workflowW2Card, webDriver, "Wait for workflow to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.workflowW2CardEllipsis, webDriver, "Click on W2 workflow ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.publishExportOption, webDriver, "Wait workflow export option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.publishExportOption, webDriver, "Click on workflow export option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.publishExportDescriptionOption, webDriver, "Wait workflow export description to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Page scroll down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.exportButton, webDriver, "Click on export button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.exportMessage, webDriver, "Wait workflow export message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.exportMessage, webDriver, "Workflow exported");
	}

	@Test(priority = 2,dependsOnMethods = {"exportWorkflow"},groups = {"CustomNodeJSCLIConnector ExportWorkflow"},description = "Verify .zip file downloaded in folder")
	public void verifyFileDownloaded() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.exportMessage, webDriver, "Wait workflow export message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExportWorkflowTests.ExportWorkflowLocators.exportMessage, webDriver, "Workflow exported");
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