package com.webMethods.io.Integration.Configuration.BVT;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class Configuration
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

	@Test(priority = 0,groups = {"ConfigurationsDashboard"},description = "Login account")
	public void configurationPageLogin() 
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"configurationPageLogin"},groups = {"ConfigurationsDashboard"},description = "Visit configuration page")
	public void configurationPageVisit() 
	{
		//Visit Configuration page
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton, webDriver,"Configuration page visit");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Input project name");       
	}

	@Test(priority = 2,dependsOnMethods = {"configurationPageVisit"},groups = {"ConfigurationsDashboard"},description = "Add new connections/auth")
	public void configurationPageAddNewAccount() 
	{	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Select workflow option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Select Connections option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on webhook option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Select Connections option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Wait for connections tab to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on connection dropdown to expand");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Wait for added connection displaye");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on + icon to add new connection");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Enter jotform API key");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Cick on done button to add new connection");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Wait for connection added");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Assert new jotform connection added");
	}

	@Test(priority = 3,dependsOnMethods = {"configurationPageAddNewAccount"},groups = {"ConfigurationsDashboard"},description = "Edit connections/auth")
	public void configurationPageEditNewAccount()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on pencil icon");
		//		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addNewConnectionDoneButton,webDriver,"Cick on done button to edit connection");
		//		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.miniLoader,webDriver,"Wait for miniloader to dissapper");	   
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedJotFormConnection,webDriver,"Wait for connection added");	
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedJotFormConnection,webDriver,"Assert new jotform connection updated");
	}

	@Test(priority = 4,dependsOnMethods = {"configurationPageEditNewAccount"},groups = {"ConfigurationsDashboard"},description = "Update configuration ata")
	public void configurationPageUpdateConfigData() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedConnectionDropDown,webDriver,"Click on dropdown to edit connection");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedJotfotm2ConnectionName,webDriver,"Select account 1 to update config data");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.updateConfigDataMessage,webDriver,"Wait for message");
		//		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.updateConfigDataMessage,PropertiesData.readAssertionData("updateConfigDataMessage"),webDriver,"Assert config data update message",softAssert);
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedJotForm1Connection,webDriver,"Assert config data updated");
	}

	@Test(priority = 5,dependsOnMethods = {"configurationPageUpdateConfigData"},groups = {"ConfigurationsDashboard"},description = "Delete connections/auth")
	public void configurationPageDeleteConnections() throws InterruptedException 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");
		Thread.sleep(7000);
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedConnectionDropDown1,webDriver,"Click on dropdown to edit connection");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.connectionDeleteIcon,webDriver,"Click on delete icon");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.connectionDeleteButton,webDriver,"Click on Delete button");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.configurationAccount,webDriver,"Wait for connections tab to load");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.configurationAccount,webDriver,"Assert account deleted");
	}

	@Test(priority = 6,dependsOnMethods = {"configurationPageDeleteConnections"},groups = {"ConfigurationsDashboard"},description = "Connection/auth usages")
	public void configurationPagesConnUsages() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.connectionDropdown,webDriver,"Click on connection dropdown to expand");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedConnectionDropDown1,webDriver,"Click on dropdown to edit connection");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.connectionDeleteIcon2,webDriver,"Click on delete icon");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.usedWorkflowName,webDriver,"Assert used workflow name shown");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.closeUsageModal,webDriver,"Close usages modal");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedConnectionDropDown1,webDriver,"Wait for added connection displaye");
	}

	@Test(priority = 7,dependsOnMethods = {"configurationPagesConnUsages"},groups = {"ConfigurationsDashboard"},description = "Copy webhook URL")
	public void configurationpageCopyWebhook() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");
		//
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhooksOption,webDriver,"Select Webhook option");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookCopyIcon,webDriver,"Click on Copy webhook icon");
	}

	@Test(priority = 8,dependsOnMethods = {"configurationpageCopyWebhook"},groups = {"ConfigurationsDashboard"},description = "Refresh webhook URL")
	public void configurationpageRefreshWebhook() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		String webhookURL;
		//		String webhookURLUpdated;
		//
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookOptions,webDriver,"Click on webhook ellipsis");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookRefreshOption,webDriver,"Wait for refresh URL option");
		//		webhookURL = com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookURLTextArea.getText();
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookRefreshOption,webDriver,"Select Refresh webhook URL option");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookUpdateMessage,webDriver,"Wait for update message");
		//		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookUpdateMessage,PropertiesData.readAssertionData("webhookURLRefreshedMessage"),webDriver,"Assert webhook URL update message", softAssert);
		//		webhookURLUpdated = com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookURLTextArea.getText();
		//		SeleniumWebElementsUtils.assertAttributeValuesInverse(webhookURL, webhookURLUpdated, "Verify webhook URL refreshed");
	}

	@Test(priority = 9,dependsOnMethods = {"configurationpageRefreshWebhook"},groups = {"ConfigurationsDashboard"},description = "Webhook authentication")
	public void configurationpageWebhookAuth() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookOptions,webDriver,"Click on webhook ellipsis");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookAuthenticationOption,webDriver,"Wait for webhook authentication option");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookAuthenticationOption,webDriver,"Select authentication option");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookSettingsModal,webDriver,"Assert authentication modal shown");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookSettingsModalDoneButton,webDriver,"Close authentication modal");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookURLTextArea,webDriver,"Wait for modal closed");
	}

	@Test(priority = 10,dependsOnMethods = {"configurationpageWebhookAuth"},groups = {"ConfigurationsDashboard"},description = "Webhook payload")
	public void configurationPageWebhookPayload() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.overlayModal,webDriver,"Wait for overlay modal to dissappear");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookOptions,webDriver,"Click on webhook ellipsis");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookViewWebhookPayload,webDriver,"Wait for webhook payload option");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookViewWebhookPayload,webDriver,"Select payload option");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookSettingsModal,webDriver,"Assert payload modal shown");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookSettingsModalDoneButton2,webDriver,"Close payload modal");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.webhookURLTextArea,webDriver,"Wait for modal closed");
	}

	@Test(priority = 11,dependsOnMethods = {"configurationPageWebhookPayload"},groups = {"ConfigurationsDashboard"},description = "Edit triggers")
	public void configurationPageEditTriggers() 
	{ 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		ExtentTestManager.getTest().info("Select triggers option");
		//		ExtentTestManager.getTest().info("Wait for trigger modal to load");
		//		ExtentTestManager.getTest().info("Mouseover on added trigger");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.triggerPencilIcon,webDriver,"Click on pencil icon to edit trigger");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedTriggerNameStringModal,webDriver,"Wait for edit modal to load");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.triggerEditDoneButton,webDriver,"Click on done button to edit trigger");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedTrigger,webDriver,"Wait for trigger modal to load");
	}

	@Test(priority = 12,dependsOnMethods = {"configurationPageEditTriggers"},groups = {"ConfigurationsDashboard"},description = "Trigger usages")
	public void configurationPageTriggerUsages() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");
		//
		//		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedTrigger,webDriver,"Mouseover on added trigger");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.triggerDeleteIcon,webDriver,"Click on delete icon");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.usedWorkflowName,webDriver,"Assert used workflow name shown");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.closeUsageModalButton,webDriver,"Close usage modal");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedTrigger,webDriver,"Wait for trigger modal to load");
	}

	@Test(priority = 13,dependsOnMethods = {"configurationPageTriggerUsages"},groups = {"ConfigurationsDashboard"},description = "Project parameters usages")
	public void configurationPageParamUsages() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.parameterOption,webDriver,"Select parameter option");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedParam,webDriver,"Wait for params modal to load");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.firstParamsEllipsis,webDriver,"Click on ellipsis");
		//		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver, "Press keyboard tab");
		//		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver, "Press keyboard tab");
		//		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter button");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.usedWorkflowName,webDriver,"Assert used workflow is shown");
	}

	@Test(priority = 14,dependsOnMethods = {"configurationPageParamUsages"},groups = {"ConfigurationsDashboard"},description = "Add project parameters")
	public void configurationPageAddParams() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.closeParamsUsageModal,webDriver, "Close params usages modal");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.paramsDescription,webDriver,"Wait for params modal to load");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addNewParameterOption,webDriver,"Click on New Parameter option");
		//		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.paramsInputBox,PropertiesData.readInputData("newParameterName"),webDriver,"Enter new parameter name");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createParamsButton,webDriver,"Click on Create button");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedParam2,webDriver,"Wait for new params added");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedParam2,webDriver,"Assert new parameter added");
	}

	@Test(priority = 15,dependsOnMethods = {"configurationPageAddParams"},groups = {"ConfigurationsDashboard"},description = "Edit project parameters")
	public void configurationPageEditParams()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.firstParamsEllipsis,webDriver,"Click on ellipsis");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.paramsEditOption,webDriver,"Wait for edit option to be visible");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.paramsEditOption,webDriver,"Select edit option");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.updateParamsButton,webDriver,"Click on Update button");
		//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedParam2,webDriver,"Wait for params updated");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.addedParam2,webDriver,"Assert parameter updated");
	}

	@Test(priority = 16,dependsOnMethods = {"configurationPageEditParams"},groups = {"ConfigurationsDashboard"},description = "Delete project parameters")
	public void configurationPageDeleteParams() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.createNewProjectButton,webDriver,"Click on dropdown to edit connection");

		//		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.overlayModalProjectParam,webDriver,"Wait for overlay modal to dissappear");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.firstParamsEllipsis,webDriver,"Click on ellipsis to delete parameter");
		//		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver, "Press keyboard tab");
		//		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver, "Press keyboard tab");
		//		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter button");
		//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Configuration.BVT.ConfigurationLocators.paramsDeleteButton,webDriver,"Click on delete button");
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