package com.webMethods.io.Integration.NotificationsTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class Notifications
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

	@Test(priority = 0,groups = {"Notifications"},description = "Login user : Workflow Notifications")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Notifications"},description = "Notificaiton count")
	public void notificationCount() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.notificationCount,webDriver,"Notification count is shown after workflow executions");
	}

	@Test(priority = 2,dependsOnMethods = {"notificationCount"},groups = {"Notifications"},description = "Click on notification bell icon")
	public void notificationDropDownData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.notificationBellIcon,webDriver,"Click on Notification bell icon");
	}

	@Test(priority = 3,dependsOnMethods = {"notificationDropDownData"},groups = {"Notifications"},description = "Latest workflow execution data")
	public void latestNotification() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.allNotifications,webDriver,"Assert all notifications visible");
	}

	@Test(priority = 4,dependsOnMethods = {"latestNotification"},groups = {"Notifications"},description = "Latest workflow execution data")
	public void viewExecutionNotificationLogs() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.latestNotification,webDriver,"CLick on latest workflow execution logs from notification dropdown");
	}

	@Test(priority = 5,dependsOnMethods = {"viewExecutionNotificationLogs"},groups = {"Notifications"},description = "Export execution logs")
	public void exportLogsNotificationLogs() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.exportLogsString,webDriver,"Wait for logs modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NotificationsTests.NotificationsTestLocators.exportLogs,webDriver,"Click on Export logs");
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