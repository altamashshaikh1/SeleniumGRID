package com.webMethods.io.Integration.SupportLinkTrialTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumLinksImagesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class SupportLinkTrialCustomer
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

	@Test(priority = 0,groups = {"SupportLinkTrialUser"},description = "Login user : SupportLinkTrialCustomer")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"SupportLinkTrialUser"},description = "Help Menu")
	public void supportLinkTrialHelpIcon() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.helpIconVisible, webDriver, "Wait till help option is visiable");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.appSwticher,webDriver,"Assert App switcher icon visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.helpIconVisible, webDriver, "Assert Help icon visible");
	}

	@Test(priority = 2,dependsOnMethods = {"supportLinkTrialHelpIcon"},groups = {"SupportLinkTrialUser"},description = "Help Menu")
	public void supportLinkTrialOption() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.helpIconclick, webDriver, "Wait till help option is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.helpIconclick, webDriver, "Click on Help icon");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.supportString,webDriver,"Assert App switcher icon visible");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.SupportLinkTrialTests.SupportLinkTrialTestLocators.supportLinkTrial, "Support", webDriver, "Assert Support text",softAssert);
		SeleniumLinksImagesUtils.verifyLinkWithOutBasicAuth(PropertiesData.readAssertionData("trialCustomerUrl"),"https://tech.forums.softwareag.com/tag/webMethods-io-Integration link for paid user");
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