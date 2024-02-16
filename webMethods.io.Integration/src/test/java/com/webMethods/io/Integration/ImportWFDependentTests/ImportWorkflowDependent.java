package com.webMethods.io.Integration.ImportWFDependentTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumUploadDownloadFilesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class ImportWorkflowDependent
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

	@Test(priority = 0,groups = {"AssetDependenciesImportWorkflows"},description = "Login account - Asset dependendies (ImportWorkfow)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"AssetDependenciesImportWorkflows"},description = "Import workflow with dependent data")
	public void dependenciesWfImportWithDependentData() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.projectSearchTextBox,PropertiesData.readInputData("assetsDependentimportWFProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.defaultProject,webDriver,"Click on default project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.inputBox,webDriver,"Wait for import button visible");
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"ImportData.zip", com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.uploadFileTextBox,"Upload .zip file");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.importButtonToProject,webDriver,"Click on import button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.addProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.projectSearchTextBox,PropertiesData.readInputData("assetDepenendentWFWorkflowName"),webDriver,"Input imported workflow name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.workflowWithDependentData,webDriver,"Wait for workflow dashboard to load");
	}

	@Test(priority = 2,dependsOnMethods = {"dependenciesWfImportWithDependentData"},groups = {"AssetDependenciesImportWorkflows"},description = "Assert dependent data after workflow import")
	public void dependenciesWfVerifyDependentData()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.uploadedWorkflow,webDriver,"Assert workflow with dependent data imported");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.flowServicesLInk,webDriver,"Click on flowservices link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ImportWFDependentTests.ImportWorkflowDependentTestLocators.copiedFlowServices,webDriver,"Assert flowservices with dependent data imported");
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