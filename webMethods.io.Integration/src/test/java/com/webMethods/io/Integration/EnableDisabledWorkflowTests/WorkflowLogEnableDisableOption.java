package com.webMethods.io.Integration.EnableDisabledWorkflowTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumUploadDownloadFilesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class WorkflowLogEnableDisableOption
{
	public static DesiredCapabilities capabilities;

	public static String responseBody;

	public static String authtoken;

	public static String cookie;

	public static String csrftoken;

	public static boolean paidstatus;

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

	@Test(priority = 0,groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Login user")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Create new workflow test")
	public void workflowLogEnableDisableNew() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.projectSearchTextBox,PropertiesData.readInputData("DebuggerProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.executionlogsautomationproject,webDriver,"Click on default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflowButton,webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
	}

	@Test(priority = 2,dependsOnMethods = {"workflowLogEnableDisableNew"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Assert Maintain Execution log status")
	public void workflowLogEnableDisablPanelVisible() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//To verify Maintain execution logs status
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is OFF");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is ON");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Visualization line options is OFF");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox+" Option is ON");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
	}

	@Test(priority = 3,dependsOnMethods = {"workflowLogEnableDisablPanelVisible"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Assert canvas assets visible")
	public void workflowLogEnableDisableCanvas() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 4,dependsOnMethods = {"workflowLogEnableDisableCanvas"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Drag drop actions on canvas")
	public void workflowLogEnableDisableDragDrop() 
	{
		//Test Steps
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.actionSearchTextBox,PropertiesData.readInputData("SearchTrelloConnector"),webDriver,"Search Trello connector");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.action1Searched,webDriver,"Drag and drop Trello connector on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloConnectorActionId,webDriver,"Assert Trello connector visible on canvas");
	}

	@Test(priority = 5,dependsOnMethods = {"workflowLogEnableDisableDragDrop"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Connect actions on canvas")
	public void workflowLogEnableDisableConnect() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloConnectorActionId,webDriver,"Mouseover on Trello connector");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.firstActionConnector,com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.stopAction,webDriver,"Connect Trello connector with stop action");
	}

	@Test(priority = 6,dependsOnMethods = {"workflowLogEnableDisableConnect"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Configure actions")
	public void workflowLogEnableDisableTrello() 
	{
		//To visit operation modal
		String webhookURL;
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloConnectorActionId,webDriver,"Double click on Trello connector");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.selectactionmodal_str,webDriver, "Wait for select action modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.selectactionmodal,webDriver, "Click on Select action modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.actionsearchfield,"get all boards",webDriver,"Search Get All Boards action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to select action");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.add_acc,webDriver, "Wait for add account icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.add_acc,webDriver, "Click on Add Account icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.addAccountModal_str,webDriver, "Wait for Add account modal");
		String name = com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accountName.getAttribute("value");
		String handle= webDriver.getWindowHandle();
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.addAuthButton,webDriver, "Wait for add button to Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.addButton,webDriver,"Click on Add button");

		for (String handle1 : webDriver.getWindowHandles()) 
		{
			webDriver.switchTo().window(handle1);
		}

		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Page scroll down"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloLogin,webDriver,"Wait for Login visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloLogin,webDriver,"Click on Login button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.loginPage,webDriver,"Wait for Login Page visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloEmail,PropertiesData.readInputData("trelloEmail")+Keys.TAB,webDriver,"Input user email");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloLogInButton,webDriver,"Click on Login button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloLogInButton2,webDriver,"Wait for Login Continue Page visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloPassword,PropertiesData.readInputData("trelloPassword"),webDriver,"Input user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloLogInButton2,webDriver,"Click on Login button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accessPage,webDriver,"Wait for Permission Page to load");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.allowAccess,webDriver,"Wait for allow to visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.allowAccess,webDriver,"Wait for allow to Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.allowButton,webDriver,"Click on Allow button");
		webDriver.switchTo().window(handle);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.notification,webDriver, "Wait for notification");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.successMessage, "Account saved successfully.",webDriver,"Verify Auth Creation",softAssert);	   
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.notification,webDriver, "Wait for notification");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.authVerify,name,webDriver,"Verify Auth Selection",softAssert);	
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accselectmodalnextbutton,webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.actionformsection_str,webDriver, "Wait for action configuration modal to laod");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accselectmodalnextbutton,webDriver, "Click on Next Option");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.testactionmodaldonebutton,webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.testactionmodaldonebutton,webDriver, "Click on Done button");

		//To rename Workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.editPencilIcon,webDriver, "Click on pencil icon to rename workflow");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowRenameTextbox,webDriver,"Clear test area");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowRenameTextbox,PropertiesData.readInputData("renameWorkflowName1"),webDriver,"Rename workflow");	   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowRenameDoneButton,webDriver,"Click on done button");

		//Save Workflow
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"),webDriver,"Assert workflow save message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSaveMessage,webDriver, "Wait till workflow save message disappear");

		//Add Webhook to Workflow
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookOption,webDriver,"Select webhook option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookNextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookNextButton,webDriver,"Click on Next button");

		//To Check tenant is paid or not and depends on that perform operation on private key

		//Login user first and extract authtoken,cookie and csrf token
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.SOURCE_TENANT_NAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, 
				"Login account to get authtoken, cookie and CSRF token");

		authtoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken");
		cookie = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie");
		csrftoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf");


		//Send user call and check status of Paid tenant
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user",
				authtoken,
				cookie,
				csrftoken,
				"Make /user api call");

		paidstatus =  HttpURLConnHelperFuntions.getJsonKeyBooleanValue1(responseBody, "output","tenant","subscription_details","paid");

		if(paidstatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.isprivateswitch,webDriver,"Wait for is private option");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.isprivateswitch,webDriver,"Disable is Private option");
		}


		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.noneOption,webDriver,"Select none option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookNextButton,webDriver,"Click on Next button");
		webhookURL = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookURLTextBox,"value",webDriver,"Copy webhook URL",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookNextButton,webDriver,"Click on Done button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.webhookattachloder,webDriver, "Wait For loader to disapper");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloActionString,webDriver,"Wait for return action on canvas page");

		//Save Workflow
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"),webDriver,"Assert workflow save message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSaveMessage,webDriver, "Wait till workflow save message disappear");

		//Execute Workflow via webhook
		HttpURLConnHelperFuntions.executeWebhookURL(webhookURL,"Execute workflow via webhook");

		//Leave canvas
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.Trelloworkflow,webDriver, "Verify Trello workflow get display in list");
	}

	@Test(priority = 7,dependsOnMethods = {"workflowLogEnableDisableTrello"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Cloned Workflow")
	public void workflowLogEnableDisableClone() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowClone,webDriver,"Click on clone option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.cloneButton,webDriver,"Click on clone button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowCloneMessage,PropertiesData.readAssertionData("executionLogsWorkflowCloneMessage"),webDriver,"Assert workflow clone message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.clonedTrelloworkflow,webDriver, "Verify cloned Trello workflow get display in list");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.clonedTrelloworkflow,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//To verify Maintain execution logs status
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is OFF in Cloned workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is ON in Cloned workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is ON in Cloned workflow");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Visualization line options is OFF in Cloned workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox+" Option is ON in Cloned workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox+" Option is ON in Cloned workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.Trelloworkflow,webDriver, "Verify Trello workflow get display in list");

		//Delete Cloned Workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on workflow ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.menu,webDriver,"Wait for menu load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.deleteWorkflow,webDriver,"Click on Delete");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.confirmDelete,webDriver,"Wait for confirmation window");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.deleteOption,webDriver,"Click on Delete Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"),webDriver,"Assert workflow delete message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowDeleteMessage,webDriver, "Wait till workflow delete message disappear");
	}

	@Test(priority = 8,dependsOnMethods = {"workflowLogEnableDisableClone"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Execute Workflow")
	public void workflowLogEnableDisableExecute()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.Trelloworkflow,webDriver, "Verify Trello workflow get display in list");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.Trelloworkflow,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//TO check Execution Logs

		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.debugPanel,webDriver,"Click on debug panel");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "D","Open debug panel");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.executelogsentry_str,webDriver, "Wait for logs entry to get display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.executionlogstime,webDriver, "Click on execution logs to see detail logs");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.detailExecutionlogsmodal,webDriver, "Wait for detail execution logs to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.stopactionindebugger,webDriver,"Assert Disable action visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettinglink,webDriver, "Click on Settings option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingmodal_str,webDriver, "Wait for workflow setting modal to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualization_line_opt_str,webDriver, "Wait till Visualization line option get display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsoption,webDriver, "Click on Maintain Execution logs");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is ON");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is OFF");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is OFF");
		}
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckbox,webDriver, "Click on Visualization line option");

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Visualization line options is ON");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is OFF");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is OFF");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("executionLogsWorkflowSaveMessage"),webDriver,"Assert workflow save message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSaveMessage,webDriver, "Wait till workflow save message disappear");

		//Leave canvas
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.Trelloworkflow,webDriver, "Verify Trello workflow get display in list");
	}

	@Test(priority = 9,dependsOnMethods = {"workflowLogEnableDisableExecute"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Clone Workflow")
	public void workflowLogEnableDisableCloneEnable() 
	{
		//Clone workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowClone,webDriver,"Click on clone option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.cloneButton,webDriver,"Click on clone button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowCloneMessage,PropertiesData.readAssertionData("executionLogsWorkflowCloneMessage"),webDriver,"Assert workflow clone message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.clonedTrelloworkflow,webDriver, "Verify cloned Trello workflow get display in list");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.clonedTrelloworkflow,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//To verify Maintain execution logs status
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is ON in Cloned workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is OFF in Cloned workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is OFF in Cloned workflow");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Visualization line options is ON in Cloned workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is OFF in Cloned workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is OFF in Cloned workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.Trelloworkflow,webDriver, "Verify Trello workflow get display in list");
	}

	@Test(priority = 10,dependsOnMethods = {"workflowLogEnableDisableCloneEnable"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "WorkflowExecutionLogs Logs Enable/Disable : Delete Workflow")
	public void workflowLogEnableDisableDataDelete() 
	{
		//To delete Workflows
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowslist_str,webDriver,"Wait for Workflow dashboard to load");

		try
		{
			java.util.List<WebElement> workflows = webDriver.findElements(By.xpath("//span[contains(@class,'truncate title-flow-name left ')]"));

			for(int i=0;i<workflows.size();i++)
			{
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on workflow ellipsis");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.menu,webDriver,"Wait for menu load");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.deleteWorkflow,webDriver,"Click on Delete");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.confirmDelete,webDriver,"Wait for confirmation window");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.deleteOption,webDriver,"Click on Delete Button");
				SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"),webDriver,"Assert workflow delete message",softAssert);
				SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowDeleteMessage,webDriver, "Wait till workflow delete message disappear");
			}
		}
		catch(WebDriverException webdriverexception)
		{
			ExtentTestManager.getTest().fail("Exception on deleting workflow. "+webdriverexception.getMessage());	
			Assert.fail("Exception on deleting workflow. "+webdriverexception.getMessage());	
		}
	}

	@Test(priority = 11,dependsOnMethods = {"workflowLogEnableDisableTrello"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "Import new workflow test with execution logs option as disabled")
	public void importWorkflowExecutionLogDisableTest() 
	{
		//Test Steps to import workflow

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.importInputBoxString,webDriver,"Wait for import button visible");
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"Maintain Execution log OFF.zip", com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.uploadFileButton, "Upload .zip file");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.selectaccountdropdownicon, webDriver, "Wait till select account is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.selectaccountdropdownicon, webDriver, "Click on select account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloaccfromlist, webDriver, "Select Default account");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.importButtonToProject,webDriver,"Click on import button");

		//To verify imported workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowimportMessage,PropertiesData.readAssertionData("workflowimportmessage"), webDriver,"Assert workflow import successfully message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsOFFworkflow, webDriver, "Verify imported workflow get display in list");

		//Visit canvas and check state of Maintain execution logs option
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsOFFworkflow, webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workfloweditoption, webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingsopt, webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingmodal, webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowExecutionSettings, webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintain_exe_log_opt_str, webDriver, "Wait till options load");

		//TO verify Maintain Execution logs check box status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is OFF in Imported Workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is ON in Imported Workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is ON in Imported Workflow");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Visualization line options is OFF in Imported Workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is ON in Imported Workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is ON in Imported Workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSettingsclosedopt, webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//Leave Canvas
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsOFFworkflow, webDriver, "Verify Trello workflow get display in list");
	}

	@Test(priority = 12,dependsOnMethods = {"workflowLogEnableDisableTrello"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "Import new workflow test with execution logs option as enabled")
	public void importWorkflowExecutionLogEnableTest() 
	{
		//Test Steps to import workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.importInputBoxString,webDriver,"Wait for import button visible");
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"Maintain Execution log ON.zip", com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.uploadFileButton, "Upload .zip file");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.selectaccountdropdownicon, webDriver, "Wait till select account is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.selectaccountdropdownicon, webDriver, "Click on select account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloaccfromlist, webDriver, "Select Default account");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.importButtonToProject,webDriver,"Click on import button");

		//To verify imported workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowimportMessage,PropertiesData.readAssertionData("workflowimportmessage"), webDriver,"Assert workflow import successfully message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsONworkflow, webDriver, "Verify imported workflow get display in list");

		//Visit canvas and check state of Maintain execution logs option
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsONworkflow, webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workfloweditoption, webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingsopt, webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowsettingmodal, webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowExecutionSettings, webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintain_exe_log_opt_str, webDriver, "Wait till options load");

		//TO verify Maintain Execution logs check box status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is ON in Imported Workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is OFF in Imported Workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainActitivylogcheckbox+" Option is OFF in Imported Workflow");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Visualization line options is ON in Imported Workflow");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is OFF in Imported Workflow");
			Assert.fail(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.visualizationlinecheckboxstate+" Option is OFF in Imported Workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowSettingsclosedopt, webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 13,dependsOnMethods = {"importWorkflowExecutionLogEnableTest"},groups={"ExecutionLogsEnableDisabled Workflows"},description = "Delete Workflow")
	public void importWorkflowExecutionLogDeleteData() 
	{
		//To Delete Workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createNewWorkflow,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.maintainexelogsONworkflow, webDriver, "Verify Trello workflow get display in list");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowslist,webDriver,"Wait for Workflow dashboard to load");
		try
		{
			java.util.List<WebElement> workflows = webDriver.findElements(By.xpath("//span[contains(@class,'truncate title-flow-name left ')]"));

			for(int i=0;i<workflows.size();i++)
			{
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on workflow ellipsis");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.menu,webDriver,"Wait for menu load");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.deleteWorkflow,webDriver,"Click on Delete");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.confirmDelete,webDriver,"Wait for confirmation window");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.deleteOption,webDriver,"Click on Delete Button");
				SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"), webDriver,"Assert workflow delete message",softAssert);
				SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowDeleteMessage, webDriver, "Wait till workflow delete message disappear");
			}
		}
		catch(WebDriverException e)
		{
			ExtentTestManager.getTest().fail("Delete Workflows"+". "+"Please check below for errors");
			Assert.fail("Delete Workflows"+". "+"Please check below for errors");
		}

		//TO delete Trello auth
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.configtab,webDriver , "Click on Configuration tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.workflowOption,webDriver,"Select workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.connectionOption,webDriver,"Select Connections option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.connectorarea_str,webDriver, "Wait till data load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.connectionlistloaddropdownarrow, webDriver, "Click on account dropdown to expand");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloacc, webDriver, "Wait till account load");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.trelloacc, webDriver, "Mouse Hover on Trello Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.acc_delete_icon, webDriver, "Click on Delete icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.acc_delete_cnf_modal, webDriver, "Wait till confirmation modal get display");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.acc_delete_btn, webDriver, "Wait till delete option is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.acc_delete_btn, webDriver, "Click on Delete option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accDeleteMessage, webDriver, "Wait till account delete message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.EnableDisabledWorkflowTests.WorkflowLogsEnableDisableTestLocators.accDeleteMessage,PropertiesData.readAssertionData("accountDeleteMessage"), webDriver,"Assert Account delete message",softAssert);
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