package com.webMethods.io.Integration.DeleteWorkflowWithRoleTests;

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

public class DeleteWorkflowWithCustomRole 
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

	@Test(priority = 0,groups ={"WorkflowDeleteWithCustomRole"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.loginButton, webDriver,"Click on login button");
	}

	//Delete Workflow with Custom Role which don't have default project access in it  https://itrac.eur.ad.sag/browse/WFL-2461
	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups ={"WorkflowDeleteWithCustomRole"},description = "Create and Delete Workflow")
	public void createAndDeleteNewWorkflowTest() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.loadingbody, webDriver, "Wait till loading boady disappear");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.welcomeText,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.projectSearchTextBox,PropertiesData.readInputData("dynamicAccountProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.myUIautomationproject,webDriver,"Click on default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.createNewWorkflowButton, webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.stopAction,webDriver,"Assert Stop action");

		//Visit dashboard and Delete workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.deleteWorkflow,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.deleteOption,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DeleteWorkflowWithRoleTests.DeleteWorkflowWithCustomRoleTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"), webDriver,"Assert workflow delete message",softAssert);		
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