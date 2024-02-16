package com.webMethods.io.Integration.MaxLimitTests;

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

public class MaxLimitTest 
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

	@Test(priority = 0,groups = {"MaxLimitExecutions"},description = "Login user")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"MaxLimitExecutions"},description = "Visit monitor page")
	public void overviewTest() throws InterruptedException
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.projectSearchTextBox2, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName1"),webDriver,"Input project name"); 
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.projectSearchTextBox2, webDriver, "Click on search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.loadingindicator, webDriver, "Wait for loader to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.myUIautomationproject1, webDriver, "Wait till workflow Search box is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.myUIautomationproject1, webDriver, "Click on workflow search box");

		//Search workflow
		Thread.sleep(1000);
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.projectSearchTextBox2,"MaxLimitTest",webDriver,"Input workflow name");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.MaxLimitTest,webDriver,"Assert connector visible on canvas");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.MaxLimitTest,webDriver,"Clicking on Workflow tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MaxLimitTests.MaxLimitLocators.editWorkflow1,webDriver,"Editing Workflow ");
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