package com.webMethods.io.Integration.SubscriberLogTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class SubscriberWorkflowExecutionLogDisableOption
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

	@Test(priority = 0,groups={"ExecutionLogsEnableDisabled Messaging"},description = "Login user : WorkflowExecutionLogs Subscriber")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups={"ExecutionLogsEnableDisabled Messaging"},description = "Create subscriber workflow test")
	public void subscriberWorkflowLogsDisableNew()
	{
		//Create New workflow from Subscriber page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.projectSearchTextBox,PropertiesData.readInputData("subscriberWorkflowDebuggerProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.executionlogsautomationproject,webDriver,"Click on default project");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.messagingtab,webDriver, "Wait till messaging tab is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.messagingtab,webDriver, "Click on Messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.addqueueopt,webDriver, "Click on Add Queue option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.newquewindow,webDriver, "Wait for new queue window");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.queuenameinput,"Queue1",webDriver,"Input Queue Name");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.msgsavebutton,webDriver, "Wait till Save button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.msgsavebutton,webDriver, "Click on Save Button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.QueueCreatedMsg,"Queue created successfully." ,webDriver, "Assert Queue Created Message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.QueueCreatedMsg,webDriver, "Wait for message invisible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.subscriberopt,webDriver, "Click on Subscriber Optios");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.addsubopt,webDriver, "Click on Add Subscriber Option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.subscriberform,webDriver, "Wait for Subscriber Form");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.subnameinput, "Sub1",webDriver, "Provide Suabscriber Name");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.destinameinput, "Queue1",webDriver, "Provider Destination Name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.invocationtype,webDriver, "Click on Invocatio type DropDown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowfromdropdown,webDriver, "Select Workflow Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.addnewworkflowinsubscriber,webDriver, "Click on Add new option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.subNewworkflowmodal_str,webDriver, "Wait for modal to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflownameinput, "Workflow from Subscriber",webDriver, "Provide workflow name");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.msgsavebutton1,webDriver, "Wait till Save button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.msgsavebutton1,webDriver, "Click on Save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.modal_overlay_str,webDriver, "Wait for loader to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.cancelbutton,webDriver, "Wait till Cancel button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.cancelbutton,webDriver, "Click on Cancel Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.unsavechange_str,webDriver, "Wait for Unsaved changes modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.cancelcnf,webDriver, "Click on Cancel option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.nosubmsg_str,webDriver, "Wait for No Subscriber message");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.integrationtab,webDriver, "Wait till Integrations tab is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.integrationtab,webDriver, "Click on Integrations tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowfromSubscriber,webDriver, "Verify workflow created from subscriber page get display in list");

		//Visit canvas and check state of Maintain execution logs option
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowfromSubscriber,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		//To verify check box status
		if(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.maintainActitivylogcheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is OFF");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.maintainActitivylogcheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.maintainActitivylogcheckbox+" Option is ON");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.visualizationlinecheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Visualization line options is OFF");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.visualizationlinecheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.visualizationlinecheckbox+" Option is ON");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.action2Searched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.trelloConnectorActionId,webDriver,"Assert logger action visible on canvas");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.firstActionConnector,com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.stopAction,webDriver,"Connect Logger connector with stop action");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowSave,webDriver, "Wait till save button is Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"),webDriver,"Assert workflow save message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowSaveMessage,webDriver, "Wait till workflow save message disappear");
	}

	@Test(priority = 2,dependsOnMethods = {"subscriberWorkflowLogsDisableNew"},groups={"ExecutionLogsEnableDisabled Messaging"},description = "Delete Workflow")
	public void subscriberWorkflowLogsDisableDataDelete() 
	{
		//To Delete Workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowfromSubscriber,webDriver, "Verify workflow created from subscriber get display in list");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.deleteWorkflow,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.deleteOption,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"),webDriver,"Assert workflow delete message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.workflowDeleteMessage,webDriver, "Wait till workflow delete message disappear");

		//To Delete messaging Queue
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.messagingtab,webDriver, "Wait till messaging tab is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.messagingtab,webDriver, "Click on Messaging Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.Queue_1_str,webDriver, "Wait till record load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.deletequeueoption,webDriver, "Click on Delete Queue Option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.unsavechange_str,webDriver, "Wait for Deletion confirmation modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.acc_delete_btn,webDriver, "Click on Delete Option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.SubscriberLogTests.SubscriberLogDisableTestLocators.QueueDeletedMsg, "Queue deleted successfully.",webDriver, "Verify Queue Deleted message",softAssert);
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