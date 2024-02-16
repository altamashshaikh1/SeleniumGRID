package com.webMethods.io.Integration.ProfileDataTests;

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

public class ProfileData
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

	@Test(priority = 0,groups = {"ProfileData"},description = "Login user : ProfileData")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ProfileData"},description = "Login account")
	public void visitProfileDataTest() 
	{
		//Visit profile page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.projectSearchTextBox,PropertiesData.readInputData("profileDatadefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.defaultProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.profileIcon,webDriver,"Click on profile icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.profileOption,webDriver,"Wait for profile option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.profileOption,webDriver,"Click on profile option");
	}

	@Test(priority = 2,dependsOnMethods = {"visitProfileDataTest"},groups = {"ProfileData"},description = "Profile data")
	public void viewProfileData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.profileData,webDriver,"Profile data visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.userData,webDriver,"User data visible");
	}

	@Test(priority = 3,dependsOnMethods = {"viewProfileData"},groups = {"ProfileData"},description = "Generate new developer token")
	public void developerTokenGenerateProfile()
	{
		//Test Steps
		String tempKey = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.developerKeyTextBox,"value", webDriver,"Fetch and store developer key",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.genereateKey,webDriver,"Click on generate key button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.yesButton,webDriver,"Click on Yes button to generate key");
		String tempKeyTwo = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.ProfileDataTests.ProfileDataTestLocators.developerKeyTextBox,"value", webDriver,"Fetch and store new developer key",softAssert);
		SeleniumWebElementsUtils.assertAttributeValues(tempKey, tempKeyTwo, "Assert developer key generated",webDriver);
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