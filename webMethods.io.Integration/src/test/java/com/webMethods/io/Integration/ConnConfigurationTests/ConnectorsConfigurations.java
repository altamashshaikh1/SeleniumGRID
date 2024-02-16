package com.webMethods.io.Integration.ConnConfigurationTests;

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

public class ConnectorsConfigurations
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

	@Test(priority = 0,groups = {"ConnectorsEnableDisable"},description = "Login acount")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ConnectorsEnableDisable"},description = "Disable connector")
	public void connConfigurationDisableConn()
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.projectSearchTextBox,PropertiesData.readInputData("connConfigDefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.defaultProject,webDriver,"Open default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.activitesPanel,webDriver,"Wait for activites visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.actionSearchTextBox,PropertiesData.readInputData("connConfigSearchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.loggerAction,webDriver,"Mouseover on logger action");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.moreOptions,webDriver,"Wait for more options to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.moreOptions,webDriver,"Click on more options");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.disableConnector,webDriver,"Disable connector");
	}

	@Test(priority = 2,dependsOnMethods = {"connConfigurationDisableConn"},groups = {"ConnectorsEnableDisable"},description = "Enable connector")
	public void connConfigurationEnableConn()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.enableConnector,webDriver,"Enable connector");
	}

	@Test(priority = 3,dependsOnMethods = {"connConfigurationEnableConn"},groups = {"ConnectorsEnableDisable"},description = "Copy connector")
	public void connConfigurationCopyConn()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.copyConnector,webDriver,"Copy connectors");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.copiedLoggerAction,webDriver,"Assert logger action copied");
	}

	@Test(priority = 4,dependsOnMethods = {"connConfigurationCopyConn"},groups = {"ConnectorsEnableDisable"},description = "Keyboard Shourtcuts")
	public void connConfigurationKeyboardShortcuts()
	{
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver,"k","Open keyboard shortcut panel");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape key");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver,"i","Show activity id's");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape key");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver,"s","Show workflow settings panel");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape key");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver,"d","Show debugger panel");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape key");
	}

	@Test(priority = 5,dependsOnMethods = {"connConfigurationKeyboardShortcuts"},groups = {"ConnectorsEnableDisable"},description = "Transformer visual indication")
	public void connConfigurationVisualIndications()
	{
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.loggerAction,webDriver,"Double click on logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.loggerNextButton,webDriver,"Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.transFormArrowButton,webDriver,"Click on transform arrow button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.transFormAddButton,webDriver,"Click on Add new button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.transformInputBox,"newFunction",webDriver,"Name transform function");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.transformInputSelect2,webDriver,"Click on select2 dropdown");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver, "Get Current Date String", "Search date function");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Select searched function");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.transFormDoneButton,webDriver,"Click on Done button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.loggerNextButton,webDriver,"Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.transFormDoneButton,webDriver,"Click on Done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnConfigurationTests.ConnectorsConfigurationsTestLocators.newFunction,webDriver,"Assert transform function added");
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