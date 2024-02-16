package com.webMethods.io.Integration.WhiteLabelTests;

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

public class WhiteLabel 
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

	@Test(priority = 0,groups = {"WhiteLabel"},description = "Login user : White Label")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"WhiteLabel"},description = "Login account")
	public void visitWhiteLabelPage()  
	{
		//Visit white label page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.projectSearchTextBox,PropertiesData.readInputData("whiteLabelDefaultProject"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.defaultProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.profileIcon,webDriver,"Click on profile icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.profileSettingsOption,webDriver,"Wait for settings option to visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.profileSettingsOption,webDriver,"Select settings option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.whiteLabelOption,webDriver,"Select White label option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.noSettingstext,webDriver,"Wait for white label modal to load");
	}

	@Test(priority = 2,dependsOnMethods = {"visitWhiteLabelPage"},groups = {"WhiteLabel"},description = "Create new white label settings")
	public void createNewWhiteLabelSettings()  
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.newThemeButton,webDriver,"Click on New theme button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.themeNameInputBox,PropertiesData.readInputData("newThemeName"), webDriver,"Enter new theme name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.themeSaveButton,webDriver,"Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.successMessage,webDriver,"Assert create new role message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.addedTheme,webDriver,"Assert theme created");
	}

	@Test(priority = 3,dependsOnMethods = {"createNewWhiteLabelSettings"},groups = {"WhiteLabel"},description = "Edit white label settings")
	public void editWhiteLabelSettings() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.themeEditPencilIcon,webDriver,"Click on pencil icon to edit theme");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.themeUpdateButton,webDriver,"Click on Update button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.successMessage,webDriver,"Assert create new role message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.addedTheme,webDriver,"Assert theme created");
	}

	@Test(priority = 4,dependsOnMethods = {"editWhiteLabelSettings"},groups = {"WhiteLabel"},description = "Apply white label settings")
	public void applyWhiteLabelSettings()  
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.whiteLabelEllipsis,webDriver,"Click on ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.applyTheme,webDriver,"Wait for apply option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.applyTheme,webDriver,"Click on Apply option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.addedTheme,webDriver,"Assert theme applied");
	}

	@Test(priority = 5,dependsOnMethods = {"applyWhiteLabelSettings"},groups = {"WhiteLabel"},description = "Delete white label settings")
	public void deleteWhiteLabelSettings() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.whiteLabelEllipsis,webDriver,"Click on ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.deleteThemeString,webDriver,"Wait for delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.deleteTheme,webDriver,"Click on Delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.deletThemeButton,webDriver,"Click on delete button to delete theme");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WhiteLabelTests.WhiteLabelLocators.noSettingstext,webDriver,"Assert theme deleted");
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