package com.webMethods.io.Integration.MessagingRoutingRuleTests;

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

public class WorkflowRoutingRules 
{
	public static DesiredCapabilities capabilities;

	public static WebDriver webDriver;

	public static String responseBody;

	public static String authtoken;

	public static String cookie;

	public static String csrftoken;

	public static boolean skipandoverridestatus;

	public static SoftAssertWrapper softAssert;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal= new ThreadLocal<>();

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

	@Test(priority = 0,groups = {"Messaging Workflows Routing Rules"},description = "Login user : StagPromotion (Projects)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,groups = {"Messaging Workflows Routing Rules"},dependsOnMethods = {"userLogin"},testName = "Validation of Subscriber Error Message",description="Clear default Invocation")
	public void clearInvocation() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.projectSearchTextBox,PropertiesData.readInputData("projectNameMaas"),webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.projectName,webDriver, "Clicking on Project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.WorkflowSubscriberedit, webDriver, "Clicking on Edit icon of subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.destinationtypevaluetopic, webDriver, "Clicking on the Topic selected as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.destinationtypevaluequeue, webDriver, "Clicking on the Queue as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.selectdestinationdropdown,webDriver, "Selecting destination name dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.selectqueueDestinationName,webDriver, "Selecting the destination name");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.savebutton,webDriver, "Click on save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.yesEditbutton,webDriver, "Click on yes button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.QueueError,webDriver,"Wait for error to display for queue validation");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.closeicon,webDriver, "Clicking to close popup");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.destinationtypevaluequeue, webDriver, "Clicking on the Queue selected as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.destinationtypevaluetopic, webDriver, "Clicking on the Topic as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.selectdestinationdropdown,webDriver, "Selecting destination asset Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.selectTopicDestinationName,webDriver, "Selecting the old topic as destination asset");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.clearinvocation, webDriver, "Clicking on Edit icon of subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.savebutton,webDriver, "Click on save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.yesEditbutton,webDriver, "Click on yes button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.declarationpage,webDriver,"Wait for yes button to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.yesEditbutton,webDriver, "Click on yes button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.closeicon,webDriver, "Clicking to close popup");
	}

	@Test(priority = 2,groups = {"Messaging Workflows Routing Rules"},dependsOnMethods = {"clearInvocation"},testName = "Validation of Routing Rule with Workflows",description="Validation of Routing Rule with Workflows")
	public void routingRule() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.editSubscriberButton, webDriver, "Clicking on Edit button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Addroutingrule, webDriver, "Clicking on Add Routing rule");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.RoutingRulename, webDriver, "Default", "Name the Routing rule as default");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.defaultrulename,webDriver,"Wait for error to display");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.RoutingRulename, webDriver, "WorkflowRule", "Name the Rotuing rule for workflow");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Routingruletext, "%body/string% == 'ROUTE'", webDriver, "Providing Filter Value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.FlowRuleInvocation,webDriver, "Click on Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.R_Ruleworkflowdropdown,webDriver, "Click on workflow from dropdown");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to click on add button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Workflownameinsubscriber, "Messaging_Filter", webDriver, "Providing a name to the Consumer Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.saveconsumerflowbutton,webDriver, "Saving the Filter workflow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.newWF, webDriver, "Wait for Flowservice page to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Addrulebttn,webDriver, "Saving the Routing Rule");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.savebutton,webDriver, "Click on save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.yesEditbutton,webDriver, "Click on yes button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.editSubscriberButton,webDriver,"Wait for Edit button to appear on screen");
	}

	@Test(priority = 3,groups = {"Messaging Workflows Routing Rules"},description="Delete the project messaging assets")
	public void Clearingtargetmessagingassets() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.workflowTab,webDriver, "Clicking on Workflow tab");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.miniloader, webDriver, "Wait for loader to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.projectSearchTextBox, webDriver, "Wait for element to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.searchboxInput,"Consumer Workflow for Queue" , webDriver, "Input name and search for workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.qconsumerWF,webDriver, "Clicking on Workflow tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.editWorkflow,webDriver, "Editing  Workflow ");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.workflowstartaction, webDriver, "click on start action");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.deletetrigger,webDriver, "Click on delete button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.confirmtriggerdelete,webDriver, "Confirm by clicking yes");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.closeexecution,webDriver, "Close the Trigger scetion");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.exitworkflow, webDriver, "exit workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.saveandexitbutton, webDriver, "Click on save and exit");	
	}

	@Test(priority = 4,dependsOnMethods = {"Clearingtargetmessagingassets"},groups = {"Messaging Workflows Routing Rules"},description="Validating subscriber state after deletion")
	public void unConfiguredSubscriber() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.workflowTab,webDriver, "Clicking on Workflow Tab Name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.unConfiguredSubscriber, webDriver, "Verifying the subscriber state after trigger deletion ",softAssert);		
	}

	@Test(priority = 5,groups = {"Messaging Workflows Routing Rules"},dependsOnMethods = {"routingRule"},testName = "Adding Filter inside messaging trigger",description="Adding Filter inside messaging trigger")
	public void AddFilter() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.workflowTab,webDriver, "Clicking on Workflow tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.searchBox, webDriver, "Wait for search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.searchboxInput,"Messaging_Filter" ,webDriver, "Searching for the workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow"); 
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.RoutingruleWF,webDriver, "Clicking on Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.editWorkflow,webDriver, "Editing  Workflow ");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loggerActionId,webDriver,"Mousehover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.firstActionConnector,com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loggerActionId,webDriver,"Mousehover on logger action"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.OpenloggerAction, webDriver, "Logger Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.nextButton, webDriver, "Logger Action");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loggertextfield, PropertiesData.readInputData("JMSMapData"), webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.nextButton, webDriver, "Click on the next Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.doneButton, webDriver, "Click on the Done Button");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.messagingtriggericon, webDriver, "Click on the Messaging icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Edittrigger_btn, webDriver, "Click on the Edit trigger button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Triggerlabel,webDriver,"Wait for Trigger label to appear");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.AddFilter_btn, webDriver, "Click on the Add Filter Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Secondfilterlabel, webDriver, "Click on the Filter 2 Label");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Filtername, "Filter_WF", webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.FilterExpression, "%body/string% == 'WFRule'", webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.savebutton, webDriver, "Save Action");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Circle, webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.skipbutton, webDriver, "skip Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.doneButton, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.closeicon,webDriver, "Clicking on close icon");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.Circle, webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.saveworkflowbutton2, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.exitworkflow, webDriver, "exitworkflow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.miniloader, webDriver, "Wait for mini loader to disappear");
	}

	@Test(priority = 6,groups = {"Messaging Workflows Routing Rules"},dependsOnMethods = {"AddFilter"},testName = "Updating the Invocation of Routing rule",description="Updating the invocation of Routing rule")
	public void updateInvocation() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.WorkflowSubscriberedit, webDriver, "Clicking on Edit icon of subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.EditWFRule,webDriver, "Clicking on edit icon of routing rule");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.WFRuleInvocation,webDriver, "Clicking on workflow label from dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingRoutingRuleTests.WorkflowRoutingRuleLocators.R_RuleFlowdropdown,webDriver, "Clicking on FlowService label from dropdown");
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