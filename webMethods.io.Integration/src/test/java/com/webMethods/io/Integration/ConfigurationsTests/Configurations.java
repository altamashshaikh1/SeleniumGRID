package com.webMethods.io.Integration.ConfigurationsTests;

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

public class Configurations  
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

	@Test(priority = 0,groups = {"ConfigurationsDashboard"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ConfigurationsDashboard"},description = "Visit configuration page")
	public void configurationPageVisit() 
	{
		//Visit Configuration page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.projectSearchTextBox,PropertiesData.readInputData("configurationWorkflowName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectorsConfigurationsProject,webDriver,"Click on ConnectorsConfigurationProject");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.workflow1,webDriver,"Wait for Workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.configurationsLinkTab,webDriver,"Click on configurations link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.noCert,webDriver,"Wait for configuration tab to gets visible");  
	}

	@Test(priority = 2,dependsOnMethods = {"configurationPageVisit"},groups = {"ConfigurationsDashboard"},description = "Add new connections/auth")
	public void configurationPageAddNewAccount() 
	{	   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.workflowOption,webDriver,"Select workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionOption,webDriver,"Select Connections option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhooksOption,webDriver,"Click on webhook option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionOption,webDriver,"Select Connections option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.configurationAccount,webDriver,"Wait for connections tab to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionDropdown,webDriver,"Click on connection dropdown to expand");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.jotformActionName,webDriver,"Wait for added connection displaye");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addNewConnectionIcon,webDriver,"Click on + icon to add new connection");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.jotFormKeyTextBox,PropertiesData.readInputData("jotformAPIKey"),webDriver,"Enter jotform API key");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addNewConnectionDoneButton,webDriver,"Cick on done button to add new connection");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotFormConnection,webDriver,"Wait for connection added");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotFormConnection,webDriver,"Assert new jotform connection added");
	}

	@Test(priority = 3,dependsOnMethods = {"configurationPageAddNewAccount"},groups = {"ConfigurationsDashboard"},description = "Edit connections/auth")
	public void configurationPageEditNewAccount()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotFormConnection,webDriver,"Click on dropdown to edit connection");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedConnectionEditButton,webDriver,"Click on pencil icon");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addNewConnectionDoneButton,webDriver,"Cick on done button to edit connection");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.miniLoader,webDriver,"Wait for miniloader to dissapper");	   
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotFormConnection,webDriver,"Wait for connection added");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotFormConnection,webDriver,"Assert new jotform connection updated");
	}

	@Test(priority = 4,dependsOnMethods = {"configurationPageEditNewAccount"},groups = {"ConfigurationsDashboard"},description = "Update configuration ata")
	public void configurationPageUpdateConfigData()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedConnectionDropDown,webDriver,"Click on dropdown to edit connection");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotfotm2ConnectionName,webDriver,"Select account 1 to update config data");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.updateConfigDataMessage,webDriver,"Wait for message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.updateConfigDataMessage,PropertiesData.readAssertionData("updateConfigDataMessage"),webDriver,"Assert config data update message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedJotForm1Connection,webDriver,"Assert config data updated");
	}

	@Test(priority = 5,dependsOnMethods = {"configurationPageUpdateConfigData"},groups = {"ConfigurationsDashboard"},description = "Delete connections/auth")
	public void configurationPageDeleteConnections() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedConnectionDropDown1,webDriver,"Click on dropdown to edit connection");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionDeleteIcon,webDriver,"Click on delete icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionDeleteButton,webDriver,"Click on Delete button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.configurationAccount,webDriver,"Wait for connections tab to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.configurationAccount,webDriver,"Assert account deleted");
	}

	@Test(priority = 6,dependsOnMethods = {"configurationPageDeleteConnections"},groups = {"ConfigurationsDashboard"},description = "Connection/auth usages")
	public void configurationPagesConnUsages() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionDropdown,webDriver,"Click on connection dropdown to expand");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedConnectionDropDown1,webDriver,"Click on dropdown to edit connection");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.connectionDeleteIcon2,webDriver,"Click on delete icon");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.usedWorkflowName,webDriver,"Assert used workflow name shown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.closeUsageModal,webDriver,"Close usages modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedConnectionDropDown1,webDriver,"Wait for added connection displaye");
	}

	@Test(priority = 7,dependsOnMethods = {"configurationPagesConnUsages"},groups = {"ConfigurationsDashboard"},description = "Copy webhook URL")
	public void configurationpageCopyWebhook() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhooksOption,webDriver,"Select Webhook option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookCopyIcon,webDriver,"Click on Copy webhook icon");
	}

	@Test(priority = 8,dependsOnMethods = {"configurationpageCopyWebhook"},groups = {"ConfigurationsDashboard"},description = "Refresh webhook URL")
	public void configurationpageRefreshWebhook() 
	{
		String webhookURL;
		String webhookURLUpdated;

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookOptions,webDriver,"Click on webhook ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookRefreshOption,webDriver,"Wait for refresh URL option");
		webhookURL = com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookURLTextArea.getText();
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookRefreshOption,webDriver,"Select Refresh webhook URL option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookUpdateMessage,webDriver,"Wait for update message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookUpdateMessage,PropertiesData.readAssertionData("webhookURLRefreshedMessage"),webDriver,"Assert webhook URL update message",softAssert);
		webhookURLUpdated = com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookURLTextArea.getText();
		SeleniumWebElementsUtils.assertAttributeValuesInverse(webhookURL, webhookURLUpdated, "Verify webhook URL refreshed",webDriver);
	}

	@Test(priority = 9,dependsOnMethods = {"configurationpageRefreshWebhook"},groups = {"ConfigurationsDashboard"},description = "Webhook authentication")
	public void configurationpageWebhookAuth() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookOptions,webDriver,"Click on webhook ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookAuthenticationOption,webDriver,"Wait for webhook authentication option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookAuthenticationOption,webDriver,"Select authentication option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookSettingsModal,webDriver,"Assert authentication modal shown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookSettingsModalDoneButton,webDriver,"Close authentication modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookURLTextArea,webDriver,"Wait for modal closed");
	}

	@Test(priority = 10,dependsOnMethods = {"configurationpageWebhookAuth"},groups = {"ConfigurationsDashboard"},description = "Webhook payload")
	public void configurationPageWebhookPayload() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.overlayModal,webDriver,"Wait for overlay modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookOptions,webDriver,"Click on webhook ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookViewWebhookPayload,webDriver,"Wait for webhook payload option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookViewWebhookPayload,webDriver,"Select payload option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookSettingsModal,webDriver,"Assert payload modal shown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookSettingsModalDoneButton2,webDriver,"Close payload modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.webhookURLTextArea,webDriver,"Wait for modal closed");
	}

	@Test(priority = 11,dependsOnMethods = {"configurationPageWebhookPayload"},groups = {"ConfigurationsDashboard"},description = "Edit triggers")
	public void configurationPageEditTriggers() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.triggersOption,webDriver,"Select triggers option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedTrigger,webDriver,"Wait for trigger modal to load");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedTrigger,webDriver,"Mouseover on added trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.triggerPencilIcon,webDriver,"Click on pencil icon to edit trigger");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedTriggerNameStringModal,webDriver,"Wait for edit modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.triggerEditDoneButton,webDriver,"Click on done button to edit trigger");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedTrigger,webDriver,"Wait for trigger modal to load");
	}

	@Test(priority = 12,dependsOnMethods = {"configurationPageEditTriggers"},groups = {"ConfigurationsDashboard"},description = "Trigger usages")
	public void configurationPageTriggerUsages() 
	{
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedTrigger,webDriver,"Mouseover on added trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.triggerDeleteIcon,webDriver,"Click on delete icon");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.usedWorkflowName,webDriver,"Assert used workflow name shown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.closeUsageModalButton,webDriver,"Close usage modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedTrigger,webDriver,"Wait for trigger modal to load");
	}

	@Test(priority = 13,dependsOnMethods = {"configurationPageTriggerUsages"},groups = {"ConfigurationsDashboard"},description = "Project parameters usages")
	public void configurationPageParamUsages() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.parameterOption,webDriver,"Select parameter option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedParam,webDriver,"Wait for params modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.firstParamsEllipsis,webDriver,"Click on ellipsis");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.usedWorkflowName,webDriver,"Assert used workflow is shown");
	}

	@Test(priority = 14,dependsOnMethods = {"configurationPageParamUsages"},groups = {"ConfigurationsDashboard"},description = "Add project parameters")
	public void configurationPageAddParams() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.closeParamsUsageModal,webDriver, "Close params usages modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.paramsDescription,webDriver,"Wait for params modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addNewParameterOption,webDriver,"Click on New Parameter option");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.paramsInputBox,PropertiesData.readInputData("newParameterName"),webDriver,"Enter new parameter name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.createParamsButton,webDriver,"Click on Create button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedParam2,webDriver,"Wait for new params added");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedParam2,webDriver,"Assert new parameter added");
	}

	@Test(priority = 15,dependsOnMethods = {"configurationPageAddParams"},groups = {"ConfigurationsDashboard"},description = "Edit project parameters")
	public void configurationPageEditParams()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.firstParamsEllipsis,webDriver,"Click on ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.paramsEditOption,webDriver,"Wait for edit option to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.paramsEditOption,webDriver,"Select edit option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.updateParamsButton,webDriver,"Click on Update button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedParam2,webDriver,"Wait for params updated");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.addedParam2,webDriver,"Assert parameter updated");
	}

	@Test(priority = 16,dependsOnMethods = {"configurationPageEditParams"},groups = {"ConfigurationsDashboard"},description = "Delete project parameters")
	public void configurationPageDeleteParams() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.overlayModalProjectParam,webDriver,"Wait for overlay modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.firstParamsEllipsis,webDriver,"Click on ellipsis to delete parameter");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConfigurationsTests.ConfigurationsTestLocators.paramsDeleteButton,webDriver,"Click on delete button");
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