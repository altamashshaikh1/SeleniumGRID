package com.webMethods.io.Integration.CloneWFDependentTests;

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

public class CloneWorkflowDependent
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

	@Test(priority = 0,groups = {"AssetDependenciesCloneWorkflows"},description = "Login account Asset dependencies - Clone workflow (Login account)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"AssetDependenciesCloneWorkflows"},description = "Clone workflow with dependent data")
	public void dependenciesWfCloneWithDepnData()
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.projectSearchTextBox,PropertiesData.readInputData("assetsDependentCloneWorkflowName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.cloneProjectName,webDriver,"Open project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.workflowWithDependentData,webDriver,"Wait for workflow dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.workflowEllipsis,webDriver,"Click on ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.workflowClone,webDriver,"Click on clone option");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.cloneInputBox,PropertiesData.readInputData("assetsDependentCloneProjectName"), webDriver, "Provide Project Name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.cloneButton,webDriver,"Click on clone button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.workflowCloneMessage,PropertiesData.readAssertionData("workflowAssetDepWFCloneMessage"), webDriver,"Assert workflow clone message",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.projectLink,webDriver,"Click on project link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.defaultProject,webDriver,"Open default project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.copiedWorkflow,webDriver,"Wait for workflow dashboard to load");
	}

	@Test(priority = 2,dependsOnMethods = {"dependenciesWfCloneWithDepnData"},groups = {"AssetDependenciesCloneWorkflows"},description = "Assert dependent data after workflow clone")
	public void dependenciesWfAssertDepenData()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.copiedWorkflow,webDriver,"Assert workflow with dependent data cloned");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.flowServicesLInk,webDriver,"Click on flowservices link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CloneWFDependentTests.CloneWorkflowDependentTestLocators.copiedFlowServices,webDriver,"Assert flowservices with dependent data cloned");
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