package com.webMethods.io.Integration.HelpMenu.BVT;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
//import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumLinksImagesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test; 

public class HelpMenu 
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
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal,BaseTestUtils.getGridURL(), capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"HelpMenu"},description = "Login user : HelpMenu")
	public void appSwitcherHelpLoginUser() 
	{		
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"appSwitcherHelpLoginUser"},groups = {"HelpMenu"},description = "App switcher")
	public void appSwitcherHelpAppSwitcher() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.appSwticher,webDriver,"Assert App switcher icon visible");
	}

	@Test(priority = 2,dependsOnMethods = {"appSwitcherHelpAppSwitcher"},groups = {"HelpMenu"},description = "About page")
	public void appSwitcherHelpAboutPage()  
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.helpIcon,webDriver,"Click on help icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.aboutLink,webDriver,"Wait for about link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.aboutLink,webDriver,"Click on About link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.aboutContent,webDriver,"Assert about page content");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.aboutContent,webDriver,"Assert Product version is available on page");
		//String documentURLonVersion = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.documentsLinkonVersion,"href",webDriver,"Fetch Release document link on version", softAssert);
		//SeleniumLinksImagesUtils.verifyLinkWithBasicAuth(documentURLonVersion,PropertiesData.readInputData("docsUserName"),PropertiesData.readInputData("docsUserPassword"),"Verify release documents link on version");
	}

	@Test(priority = 3,dependsOnMethods = {"appSwitcherHelpAboutPage"},groups = {"HelpMenu"},description = "Documentations")
	public void appSwitcherDocPage() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.helpIcon,webDriver,"Click on help icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.documentString,webDriver,"Wait for documentation link");
		//String documentURL = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.documentsLink,"href",webDriver,"Fetch document link", softAssert);
		//SeleniumLinksImagesUtils.verifyLinkWithBasicAuth(documentURL,PropertiesData.readInputData("docsUserName"),PropertiesData.readInputData("docsUserPassword"),"Verify documents page");
	}

	@Test(priority = 4,dependsOnMethods = {"appSwitcherDocPage"},groups = {"HelpMenu"},description = "Getting started")
	public void appSwitcherGetStarted()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.gettingStarted,webDriver,"Wait for getting started link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.gettingStarted,webDriver,"Click on getting started link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.gettingStartedContent,webDriver,"Assert getting started contents");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.closeGettingStarted,webDriver,"Close setting started message");
	}

	@Test(priority = 5,dependsOnMethods = {"appSwitcherGetStarted"},groups = {"HelpMenu"},description = "Assert Impressum and Privacy Policy links")
	public void appSwitcherVerifyFoooterLinks() 
	{
		//Test Steps
		SeleniumLinksImagesUtils.verifyLinkWithOutBasicAuth("https://www.softwareag.com/impressum","Assert impressum link in footer");
		SeleniumLinksImagesUtils.verifyLinkWithOutBasicAuth("https://www.softwareag.com/corporate/cloud_privacy_policy","Assert privacy policy link in footer");
	}

	@Test(priority = 6,dependsOnMethods = {"appSwitcherVerifyFoooterLinks"},groups = {"HelpMenu"},description = "Support form")
	public void appSwitcherSupportLinks() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.helpIcon,webDriver,"Click on help icon");
		SeleniumLinksImagesUtils.verifyLinkWithOutBasicAuth("https://empower.softwareag.com/","Verify https://empower.softwareag.com/ link");
	}

	@Test(priority = 7,dependsOnMethods = {"appSwitcherSupportLinks"},groups = {"HelpMenu"},description = "Logout user")
	public void appSwitcherLogout()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.overlayModalProfileString,webDriver,"Wait for modal to be invisible");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.profileIcon,webDriver,"Wait for profile icon to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.profileIcon,webDriver,"Click on profile icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.logOutOption,webDriver,"Wait for Logout option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.logOutOption,webDriver,"Click on Logout option");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.HelpMenu.BVT.HelpMenuLocators.loginButton,webDriver,"Assert account logout");
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