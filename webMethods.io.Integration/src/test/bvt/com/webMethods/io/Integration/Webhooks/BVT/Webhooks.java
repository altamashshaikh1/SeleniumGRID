package com.webMethods.io.Integration.Webhooks.BVT;

import org.openqa.selenium.JavascriptExecutor;
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
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class Webhooks 
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static SoftAssertWrapper softAssert;

	private String webhookURLTwo;

	private String webhookURL;

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

	@Test(priority = 0,groups = {"Webhooks"},description = "Login user : WebHooks CRUD")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Webhooks"},description = "Apply webhook to workflow")
	public void applyWebhookWorkflow() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.projectSearchTextBox,PropertiesData.readInputData("webHookDefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.defaultProjectCard,webDriver,"Click on Default project card");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.activitiesPanelString,webDriver,"Wait for canvas page to load");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookOption,webDriver,"Select webhook option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookNextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookNextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.TenantCredRadio,webDriver,"Check for Tenant cred is selected by default ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.noneOption,webDriver,"Select none option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookNextButton,webDriver,"Click on Next button");
		webhookURL = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookURLTextBox,"value",webDriver,"Copy webhook URL",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookNextButton,webDriver,"Click on Done button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookAppliedActionStart,webDriver,"Wait for return action on canvas page");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.actionSearchTextBox,"Logger",webDriver,"Enter logger action name");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.loggerActionId,webDriver,"Assert logger action visible on canvas");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.loggerActionId,webDriver,"Mouseover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.firstActionConnector,com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.loggerActionId,webDriver,"Double click on logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.actionNextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.loggerActionTextArea,PropertiesData.readInputData("loggerActionInputData"),webDriver,"Input data in logger action");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.actionNextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookAppliedActionStart,webDriver,"Wait for return action on canvas page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.workflowSave,webDriver,"Save workflow");
	}

	@Test(priority = 2,dependsOnMethods = {"applyWebhookWorkflow"},groups = {"Webhooks"},description = "Execute webhook workflow")
	public void executeWebhookWorkflow() 
	{
		//Test Steps
		HttpURLConnHelperFuntions.executeWebhookURL(webhookURL,"Execute workflow via webhook");
	}

	@Test(priority = 3,dependsOnMethods = {"executeWebhookWorkflow"},groups = {"Webhooks"},description = "Reset webhook workflow")
	public void resetWebhookWorkflow() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.refreshWebhookSid,webDriver,"Refresh webhook SID");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookIDMessage,PropertiesData.readAssertionData("webhookRefreshSIDMessage"),webDriver,"Assert webhook SID refresh message",softAssert);
		webhookURLTwo = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookURLTextBox2,"value",webDriver,"Copy webhook URL after reset",softAssert);
		SeleniumWebElementsUtils.assertAttributeValues(webhookURL, webhookURLTwo, "Assert webhook SID reset and new created",webDriver);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.copyWebhookSid,webDriver,"copy webhook SID");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.WebhookSidcopied,webDriver,"verify webhook sid copied");
	}

	@Test(priority = 4,dependsOnMethods = {"resetWebhookWorkflow"},groups = {"Webhooks"},description = "Edit webhook payload")
	public void editwebhookpayload() 
	{
		//Test Steps headers(invalid)
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookpayload,webDriver,"search for webhook playload");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.editwebhookpayload,webDriver,"click on edit webhook payload");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Headers,webDriver,"click on edit webhook payload");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to tab");
		SeleniumKeyBoardActionUtils.keyboardBackSpaceAndSendKeys(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webHookPayloadElement, webDriver, "abcd", "Clear data from payload and enter abcd as new payload");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Headers,webDriver,"click on edit webhook header payload");
		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Scroll down"); 

		//Test Steps body(invalid)
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Body,webDriver,"click on edit webhook header payload");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndDelete(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Bodyclick, webDriver, "abcd", "Clear payload and add new data");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Body,webDriver,"click on edit webhook body payload");

		//Test steps Query(invalid)
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Query,webDriver,"click on edit webhook Query payload");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndDelete(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.BodyQueryclick, webDriver, "abcd", "Clear data from payload and enter abcd as new payload");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Query,webDriver,"click on edit webhook Query payload");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookSave,webDriver,"click on save");

		//Assert invalid message
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.workflowinputString,"Please enter a valid JSON object in the header field.",webDriver,"Assert webhook SID refresh message",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.previous,webDriver,"click on previous");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.cancel,webDriver,"click on cancel");

		//Test Steps headers(valid)
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookpayload,webDriver,"search for webhook playload");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.editwebhookpayload,webDriver,"click on edit webhook payload");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Headers,webDriver,"click on headers payload");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to tab");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndDelete(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webHookPayloadElement, webDriver, "{\"name\":\"application\"}", "Clear data from payload and enter new data");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Headers,webDriver,"click on edit webhook header payload");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll down"); 

		//Test Steps body(invalid)
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Body,webDriver,"click on edit webhook header payload");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndDelete(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Bodyclick, webDriver, "{ \"raw\": {\"name\":\"json\"}}", "Clear data from payload and enter new data");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Body,webDriver,"click on edit webhook body payload");

		//Test steps Query(invalid)		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Query,webDriver,"click on edit webhook Query payload");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndDelete(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.BodyQueryclick, webDriver,"{\"version\":5}", "Clear data from payload and enter new data");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.Save,webDriver,"click on save");
	}

	@Test(priority = 5,dependsOnMethods = {"editwebhookpayload"},groups = {"Webhooks"},description = "Delete webhook workflow")
	public void deleteWebhookWorkflow() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.deleteWebhookIcon,webDriver,"Click on delete button to delete webhook");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Webhooks.BVT.WebhookLocators.webhookOption,webDriver,"Assert webhook deleted");
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