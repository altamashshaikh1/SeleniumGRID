package com.webMethods.io.Integration.ImportWFTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumUploadDownloadFilesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class ImportWorkflow 
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

	@Test(priority = 0,groups = {"CustomNodeJSCLIConnector-ImportWorkflow"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"CustomNodeJSCLIConnector-ImportWorkflow"},description = "Import workflow having NodeJS CLI Connector")
	public void importWorkflow() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.projectSearchBox,"Default", webDriver,"Enter Backlog NodeJS CLI project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.recipesInputButton, webDriver, "Wait recipes import button to be visible");
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"CLINodeJSImportFile.zip", com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.recipesInputBox, "Upload .zip file to import workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.importModalTitle, webDriver, "Wait import modal to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.addAccount, webDriver, "Click on addAccount");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.userName,"TEST", webDriver,"Enter Node JS CLI user name");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.userPassword,"TEST", webDriver,"Enter Node JS CLI user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.addButton, webDriver, "Click on add button to add user details");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.accountSavedMessage, webDriver, "Wait account saved message to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.importButton, webDriver, "Click on import button to import workflow in default project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.workflowImportMessage, webDriver, "Wait workflow Import message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.workflowW1, webDriver, "Verify workflow with run flow action is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ImportWFTests.ImportWorkflowLocators.workflowW2, webDriver, "Verify workflow with NodeJS CLI connectors is visible");
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