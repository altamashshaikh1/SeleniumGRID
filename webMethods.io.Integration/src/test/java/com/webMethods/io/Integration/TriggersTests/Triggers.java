package com.webMethods.io.Integration.TriggersTests;

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

public class Triggers
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

	@Test(priority = 0,groups = {"Triggers"},description = "Login user : Triggers CRUD")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.TriggersTests.TriggerTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Triggers"},description = "Create and apply trigger to workflow")
	public void createNewTriggerTest() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.projectSearchTextBox,PropertiesData.readInputData("triggersDefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.defaultProjectCard,webDriver,"Click on Default project card");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.activitiesPanelString,webDriver,"Wait for canvas page to load");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startAction,webDriver,"Double click on start action");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.webhookIconString,webDriver,"Wait for webhook icon to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerSearchTextBox,PropertiesData.readInputData("trelloTriggerName"),webDriver,"Search trello trigger");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.trelloTriggerObject,webDriver,"Select Trello trigger after search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerNextButton,webDriver,"Click on Next button after selecting trello trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerAuthDropdown,webDriver,"Select trello auth");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.trelloAuthName,webDriver,"Select trello auth");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerNextButton,webDriver,"Click on Done button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerSaveMessage,PropertiesData.readAssertionData("triggerSaveMessage"),webDriver,"Assert trigger saved message",softAssert);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.trelloMessageString,webDriver,"Wait for trello test message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerSkip,webDriver,"Click on Skip button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerNextButton,webDriver,"Click on Done button to add trello trigger");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.stopActionString,webDriver,"Wait for canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerAppliedObject,webDriver,"Assert trello trigger applied to workflow");
	}

	@Test(priority = 2,dependsOnMethods = {"createNewTriggerTest"},groups = {"Triggers"},description = "Upsert trigger to workflow")
	public void upsertTriggerTest() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startAction,webDriver,"Mouse hover on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startSettingsIcon,webDriver,"Click on settings icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerName,webDriver,"Click on trigger object to upsert");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerNextButton,webDriver,"Click on Done button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.stopActionString,webDriver,"Wait for canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerAppliedObject,webDriver,"Assert trello trigger upsert to workflow");
	}

	@Test(priority = 3,dependsOnMethods = {"upsertTriggerTest"},groups = {"Triggers"},description = "Edit trigger to workflow")
	public void editTriggerTest() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startAction,webDriver,"Mouse hover on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startSettingsIcon,webDriver,"Click on settings icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerEditButton,webDriver,"Click on pencil icon to edit trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerNextButton,webDriver,"Click on Save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerSkip,webDriver,"Click on skip button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerDone,webDriver,"Click on done button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.stopActionString,webDriver,"Wait for canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerAppliedObject,webDriver,"Assert trello trigger applied to workflow");
	}

	@Test(priority = 4,dependsOnMethods = {"editTriggerTest"},groups = {"Triggers"},description = "Delete trigger to workflow")
	public void deleteTriggerTest() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startAction,webDriver,"Mouse hover on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.startSettingsIcon,webDriver,"Click on settings icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerDeleteButton,webDriver,"Click on delete button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerDeleteYesButton,webDriver,"Click on Yes button to delete trigger");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.triggerDeleteMessage,PropertiesData.readAssertionData("triggerDeleteMessage"),webDriver,"Assert trigger delete message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TriggersTests.TriggerTestLocators.webhookOption,webDriver,"Assert trigger deleted");
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