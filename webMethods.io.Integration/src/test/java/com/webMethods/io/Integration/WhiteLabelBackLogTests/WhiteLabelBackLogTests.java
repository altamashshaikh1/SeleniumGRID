package com.webMethods.io.Integration.WhiteLabelBackLogTests;

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

public class WhiteLabelBackLogTests 
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

	@Test(priority = 0,groups = {"WhiteLabelBackLogTests"},description = "Login user : WhiteLabelBackLogTests")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver, "Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"WhiteLabelBackLogTests"},description = "Verify error should be displayed for invalid color codes for all color code fields")
	public void whiteLabelInvalidColorCode()  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.profileButton, webDriver,"Click on profile icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.settingsProfile, webDriver, "Click on settings option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.whitelabelOption, webDriver, "Click on White label option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.whitelabelEditOption, webDriver, "Click on edit theme pencil icon");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "ERROR","Send keys");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.errorMessage, webDriver, "Verify error message for invalid color code");
	}

	@Test(priority = 2,dependsOnMethods = {"whiteLabelInvalidColorCode"},groups = {"WhiteLabelBackLogTests"},description = "Verify white labeling support for link hover")
	public void whiteLabelHover() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.projectLinks, webDriver, "Click on project link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.profileButton, webDriver,"Click on profile icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.settingsProfile, webDriver, "Click on settings option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.whitelabelOption, webDriver, "Click on White label option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.themeEyeIcon, webDriver, "Click on eye icon to apply white label");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.applyThemeYesButton, webDriver, "Click on yes button to apply theme");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.currentButtonElement,webDriver,"Verify white label is applied");
	}

	@Test(priority = 3,dependsOnMethods = {"whiteLabelHover"},groups = {"WhiteLabelBackLogTests"},description = "Verify only owner and admin users can see White Labeling option")
	public void whiteLabelAdminAcess() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.projectLinks, webDriver, "Click on project link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.profileButton, webDriver,"Click on profile icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.settingsProfile, webDriver, "Click on settings option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelBackLogTests.WhiteLabelBackLogTestsLocators.whitelabelOption, webDriver, "Verify white label option visible only for admin/owner users");
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