package com.webMethods.io.Integration.PrivateWebhookTests;

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

public class PrivateWebhook 
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

	@Test(priority = 0,groups = {"PrivateWebhook"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"PrivateWebhook"},description="Verify search project")
	public void ProjectLogin() 
	{
		//Search project
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2, webDriver, "Click on project search box");

		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName1"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Search workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2,"Privatewebhook",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Privatewebhook,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.editWorkflow1,webDriver,"Editing Workflow ");
	}

	@Test(priority = 2,dependsOnMethods = {"ProjectLogin"},groups = {"PrivateWebhook"},description="Verify private webhook test")
	public void WebhookTest() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.activitiesPanel, webDriver, "Wait for activities panel on canvas");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.webhookURLon,webDriver,"Verify webhook URL");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.privatewebhook,webDriver,"Verify webhook toggle");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.privatetoggleoff,webDriver,"click on toggle");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.webhookURLoff,webDriver,"Verify webhook URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.privatetoggleon,webDriver,"click on toggle");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.webhookURLon,webDriver,"Verify webhook URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Done,webDriver,"click on done");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Save,webDriver,"click on Save");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.workflowPlayButton,webDriver,"Click on play button to execute workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.workflowExecutionStartMessage,PropertiesData.readAssertionData("workflowExecutionStartMessage"), webDriver,"Assert workflow execution start message",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.workflowExecutionCompletedMessage,PropertiesData.readAssertionData("workflowExecutionCompleteMessage"), webDriver,"Assert workflow execution complete message",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.leaveCanvas,webDriver,"Leave canvas page");
	}

	@Test(priority = 3,dependsOnMethods = {"WebhookTest"},groups = {"PrivateWebhook"},description="Verify private webhook publish test")
	public void WebhookpublishTest() 	
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Project,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2, webDriver, "Click on project search box");

		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName1"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.myUIautomationproject1, webDriver, "Click on searched project");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.elopsis,webDriver,"Click on VarshithaUIAutomation elopsis ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Publishproject,webDriver,"Click on Publish project");

		//Select Assets 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Assetworkflows,webDriver,"check for element workflow visible");

		//click(com.projectParameterTestPageObjects.Locators.Assetworkflows,driver,"check box"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Next,webDriver,"click on next"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Next,webDriver,"click on next"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.InputName,"TestParameter",webDriver,"Input name to publish workflow");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.InputDescription,"TestParameter testing",webDriver,"Input name to publish workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Dropdown,webDriver,"click on Dropdown");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Publish, webDriver, "Wait till publish button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Publish,webDriver,"click on publish");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectPublishMessage,PropertiesData.readAssertionData("projectPublishMessage"),webDriver,"Assert Project Publish message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectPublishMessage, webDriver, "Wait till message disappear");

	}
	/*	@Test(priority = 4,dependsOnMethods = {"WebhookpublishTest"},groups = {"PrivateWebhook"},description="Verify private webhook deploy test")
	public void WebhookdeployTest() 	
	{
		webDriver.navigate().to(PropertiesData.readLoginTenantURL("loginDestinationTenantURL"));
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.ssoLoginLink, webDriver, "Click on SSO login Link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.addNewProjectButton, webDriver,"Verify user logged in");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName1"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyBoardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Privatewebhook,webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.Privatewebhook,webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.deployprojectnamemodal,webDriver, "Wait for project name modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PrivateWebhookTests.PrivateWebhookLocators.saveandcontinueButton,webDriver, "click on save and continue option");
    }
	 */	

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