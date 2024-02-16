package com.webMethods.io.Integration.SwitchTests;

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

public class SwitchCase
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

	@Test(priority = 0,groups = {"SwitchCase"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"SwitchCase"},description="Verify switch case configuration")
	public void ActionTest() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName1"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Search workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.projectSearchTextBox2,"SwitchConfiguration",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.SwitchConfiguration,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.editWorkflow1,webDriver,"Editing Workflow ");

		//Switch configurtion 
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.loadingindicator, webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Label,webDriver,"click on condition");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.setting,webDriver,"click on settings");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.defaultselect,webDriver,"Assert if delault is selected in drop down");

		//Select case1
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Dropdown,webDriver,"click on dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Case1select,webDriver,"select case 1");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Done,webDriver,"click on done");

		//verify Case1lable
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Label,webDriver,"click on condition");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.setting,webDriver,"click on settings");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Case1select,webDriver,"Assert if delault is selected in drop down");

		//select Default
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Dropdown,webDriver,"click on dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.defaultselect,webDriver,"select Default");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Done,webDriver,"click on done");

		//Verify Defaultlable
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.Label,webDriver,"click on condition");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.setting,webDriver,"click on settings");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SwitchTests.SwitchCaseLocators.defaultselect,webDriver,"Assert if delault is selected in drop down");
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