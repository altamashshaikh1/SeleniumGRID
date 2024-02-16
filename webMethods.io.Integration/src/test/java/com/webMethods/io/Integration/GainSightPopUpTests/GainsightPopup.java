package com.webMethods.io.Integration.GainSightPopUpTests;

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

public class GainsightPopup 
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

	@Test(priority = 0,groups = {"GainSightPopUp"},description = "Login user : GainSight PopUp")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"GainSightPopUp"},description = "Gain sight modal")
	public void gainSightModal() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.modalOverlayString,webDriver,"Wait for modal overlay modal to dissappear");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.helpIocn,webDriver,"Click on help icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.gettingStartedOption,webDriver,"Wait for getting started link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.gettingStartedOption,webDriver,"Click on getting started option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.gainSigtModalPopUp,webDriver,"Assert gain sight modal visible");
	}

	@Test(priority = 2,dependsOnMethods = {"gainSightModal"},groups = {"GainSightPopUp"},description = "Gain sight modal welcome wrapper")
	public void gainSightModalWelcomeWrapper() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.gainSigtModalPopUpWrapper,webDriver,"Assert gain sigt wrapper content visible");
	}

	@Test(priority = 3,dependsOnMethods = {"gainSightModalWelcomeWrapper"},groups = {"GainSightPopUp"},description = "Gain sight modal slide content")
	public void gainSightModalSlideContent()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.gainSigtModalSlideContent,webDriver,"Assert gain sight modal slight content visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.flowServiceContentSlide,webDriver,"Slide content for flowservices details");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.GainSightPopUpTests.GainsightPopupTestLocators.connectorsContentSlide,webDriver,"Slide content for connectors details");
	}

	@Test(priority = 4,dependsOnMethods = {"gainSightModalSlideContent"},groups = {"GainSightPopUp"},description = "Gain sight modal resources")
	public void gainSightModalResources() 
	{
		SeleniumLinksImagesUtils.verifyLinkWithBasicAuth(PropertiesData.readInputData("documentationURL"),PropertiesData.readInputData("docsUserName"),PropertiesData.readInputData("docsUserPassword"),"Assert documentation URL in gain sight modal");
		SeleniumLinksImagesUtils.verifyLinkWithBasicAuth(PropertiesData.readInputData("tutorialsURL"),PropertiesData.readInputData("docsUserName"),PropertiesData.readInputData("docsUserPassword"),"Assert tutorials URL in gain sight modal");
		SeleniumLinksImagesUtils.verifyLinkWithBasicAuth(PropertiesData.readInputData("webinarURL"),PropertiesData.readInputData("docsUserName"),PropertiesData.readInputData("docsUserPassword"),"Assert webinar URL in gain sight modal");
	}

	@Test(priority = 5,dependsOnMethods = {"gainSightModalResources"},groups = {"GainSightPopUp"},description = "Gain sight modal guided tutorials")
	public void gainSightModalGuidedTutorials()
	{
		SeleniumLinksImagesUtils.verifyLinkWithBasicAuth(PropertiesData.readInputData("guidedTutotrialURL"),PropertiesData.readInputData("docsUserName"),PropertiesData.readInputData("docsUserPassword"),"Assert webinar URL in gain sight modal");
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