package com.webMethods.io.Integration.WFMessagingsTests;

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

public class WorkflowMessagingTestcases 
{
	public static DesiredCapabilities capabilities;

	public static WebDriver webDriver;

	public static String responseBody;

	public static String authtoken;

	public static String cookie;

	public static String csrftoken;

	public static boolean skipandoverridestatus;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal= new ThreadLocal<>();

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

	@Test(priority = 0,groups = {"Messaging Workflows"},description = "Login user : StagPromotion (Projects)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,groups = {"Messaging Workflows"},dependsOnMethods = {"userLogin"},testName = "Messaging With Workflow",description="Creating Queue")
	public void queueCreation() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.projectSearchTextBox,PropertiesData.readInputData("projectNameMaas"),webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.projectName,webDriver, "Clicking on Project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsTab, webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingDestinationsButton, webDriver, "Wait for Messaging Destination Button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingDestinationsButton, webDriver, "Click on Messaging Destination button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newQueuebutton,webDriver, "Clicking on Add Queue Button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queuetext, PropertiesData.readInputData("queueName"), webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.savebutton,webDriver, "Saving the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.closeicon,webDriver, "clicking on close icon");
	}

	@Test(priority = 1,groups = {"Messaging Workflows"},dependsOnMethods = {"queueCreation"},testName = "Messaging With Workflow",description="Creating Topic")
	public void TopicCreation() 
	{

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queuelist,webDriver, " Returning to the Queue listing page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.topicsTab,webDriver, "Clicking on Topics tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newTopicbutton,webDriver, "Clicking on Add Topic Button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.topictext, PropertiesData.readInputData("topicName"), webDriver, "Providing a name to the Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.savebutton,webDriver, "Saving the Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.closeicon,webDriver, "clicking on close icon");
	}

	@Test(priority = 1,groups = {"Messaging Workflows"},dependsOnMethods = {"TopicCreation"},testName = "Messaging With Workflow",description="Creating Queue Subscribers")
	public void createQueueSubscribers() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newSubscriberbutton,webDriver, "Adding new subscriber");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.subscribertext, "Subscriber_QueueWorkflow", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.destinationtypedropdown,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.destinationtypevaluequeue,webDriver, "Selecting destination asset Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectdestinationdropdown,webDriver, "Selecting destination name dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectqueueDestinationName,webDriver, "Selecting the destination name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectinvocationType, webDriver, "select invocation Type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.invocationsdropdown, webDriver, "select invocation Type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.addnewWFbutton,webDriver, "Clicking on addnew workflow button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.Workflownameinsubscriber,"Consumer Workflow for Queue", webDriver, "Providing a name to the Consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Workflow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newWF, webDriver, "Wait for Flowservice page to disappear");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll down page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveandexitbutton,webDriver, "Saving the Subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsHeader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.closeicon,webDriver, "Clicking to close popup");
	}

	@Test(priority = 2,groups = {"Messaging Workflows"},dependsOnMethods = {"createQueueSubscribers"},testName = "Messaging With Workflow",description="Configuring the Consumer created for queue")
	public void configureQueueconsumer() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queuesubscriberwf,webDriver, "Clicking on Queue subscriber for workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queueconsumerexecution,webDriver, "Clicking on Workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggerActionId,webDriver,"Mousehover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.firstActionConnector,com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggerActionId,webDriver,"Mousehover on logger action"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.OpenloggerAction, webDriver, "Logger Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Logger Action");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggertextfield, PropertiesData.readInputData("JMSMapData"), webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Click on the next Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.doneButton, webDriver, "Click on the Done Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveworkflowbutton, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.exitworkflow, webDriver, "exitworkflow");
	}

	@Test(priority = 3,groups = {"Messaging Workflows"},dependsOnMethods = {"configureQueueconsumer"},testName = "Messaging With Workflow",description="Create subscriber using topic")
	public void createTopicSubscribers() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsTab, webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newSubscriberbutton,webDriver, "Adding new subscriber");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.subscribertext, "Subscriber_TopicWorkflow", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.destinationtypedropdown,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.destinationtypevaluetopic,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectdestinationdropdown,webDriver, "Selecting destination asset Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectTopicDestinationName,webDriver, "Selecting destination asset");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectinvocationType, webDriver, "select invocation Type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.invocationsdropdown, webDriver, "select invocation Type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.addnewWFbutton,webDriver, "Clicking on addnew workflow button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.Workflownameinsubscriber, "Consumer Workflow for Topic", webDriver, "Providing a name to the Consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Workflow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newWF, webDriver, "Wait for Flowservice page to disappear");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll down page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveandexitbutton,webDriver, "Saving the Subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsHeader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.closeicon,webDriver, "Clicking to close popup");
	}

	@Test(priority = 4,groups = {"Messaging Workflows"},dependsOnMethods = {"createTopicSubscribers"},testName = "Messaging With Workflow",description="Configuring the Consumer created for Topic")
	public void configuretopicconsumer() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowTab,webDriver, "Clicking on Workflow tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.newWorkflow, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.searchboxInput,"Consumer Workflow for Topic" ,webDriver, "Searching for the workflow topic");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow" ); 
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.TopicconsumerWF,webDriver, "Clicking on Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.editWorkflow,webDriver, "Editing  Workflow ");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggerActionId,webDriver,"Mousehover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.firstActionConnector,com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggerActionId,webDriver,"Mousehover on logger action"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.OpenloggerAction, webDriver, "Logger Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Logger Action");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggertextfield, PropertiesData.readInputData("JMSMapData"), webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Click on the next Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.doneButton, webDriver, "Click on the Done Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveworkflowbutton, webDriver, "Click on the save Button");
//		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowbackbutton, webDriver, "Wait for back button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.exitworkflow, webDriver, "exitworkflow");
	}

	@Test(priority = 5,groups = {"Messaging Workflows"},testName = "Messaging With Workflow",description="Creating the Queue and Publisher workflows")
	public void createPublisherworkflows() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowTab,webDriver, "Clicking on Workflow Tab Name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.miniloader, webDriver, "Wait for mini loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfplusicon, webDriver, "Logger Action");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.createnewwfbtn, webDriver, "Logger Action"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.actionSearchTextBox,PropertiesData.readInputData("searchConnector"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.connectorSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggerActionId,webDriver,"Mouseover on logger action"); 
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.firstActionConnector,com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumWebElementsUtils.openconnector(webDriver,"Messaging");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectdestination,webDriver, "Selecting destination asset dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectqueueDestinationName,webDriver, "Selecting destination asset queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.defaultaccountdropdown,webDriver, "selecting the default account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.defaultaccount,webDriver, "selecting the default account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Next Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.StringLabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Key board tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("QueueData"), "Queue Textbox" );
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Next2 Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.doneButton, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.editWFicon, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowfName, webDriver, "name  Action");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowfName, webDriver, "Queue_PublisherWorkflow", "Name the consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowdoneButton, webDriver, "Done for workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveworkflowbutton, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.runworkflowbutton, webDriver, "Clicking on the Run Button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.exitworkflow, webDriver, "Waiting for back button to be vsisible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.exitworkflow, webDriver, "Clicking on the back Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.miniloader, webDriver, "Wait for mini loader to disappear");

		//Creating the topic publisher workflow from here
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowTab,webDriver, "Clicking on Workflow Tab Name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfplusicon, webDriver, "Click on new Plus icon of workflow");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.createnewwfbtn, webDriver, "Click create new workflow button"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.actionSearchTextBox,PropertiesData.readInputData("searchConnector"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.connectorSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.loggerActionId,webDriver,"Mouseover on logger action"); 
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.firstActionConnector,com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumWebElementsUtils.openconnector(webDriver,"Messaging");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.destinationtypevaluequeue,webDriver, "Selecting destination type value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.destinationtypevaluetopic,webDriver, "Selecting value for destination type ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectdestination,webDriver, "Selecting destination asset");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectTopicDestinationName,webDriver, "select Topic as Destination Name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.defaultaccountdropdown,webDriver, "selecting the default account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.defaultaccount,webDriver, "selecting the default account ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Next Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.StringLabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Key board tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("TopicData"), "Textbox" );
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.nextButton, webDriver, "Next2 Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.doneButton, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.editWFicon, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowfName, webDriver, "name  Action");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowfName, webDriver, "Topic_PublisherWorkflow", "Name the consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowdoneButton, webDriver, "Done for workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.saveworkflowbutton, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.runworkflowbutton, webDriver, "Clicking on the Run Button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.exitworkflow, webDriver, "Click on the back Button");
	}

	@Test(priority = 6,groups = {"Messaging Workflows"},dependsOnMethods = {"createPublisherworkflows"},testName = "Count Verification",description="Verifying the Published and consumed count")
	public void Executionverifications() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingDestinationsButton, webDriver, "Wait for Messaging Destination Button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MessagingDestinationsButton, webDriver, "Click on Messaging Destination button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.WorkflowQueuemetrics, webDriver, "Clicking on Queue name");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.publishedvalue, "1", webDriver, "Verifying published count after execution",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.consumedvalue, "1", webDriver, "Verifying consumed count after execution",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.topicstab, webDriver, "Clicking on Topics Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.WorkflowTopicmetrics, webDriver, "Clicking on Topic name");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.publishedvalue, "1", webDriver, "Verifying published count after execution",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.consumedvalue, "1", webDriver, "Verifying consumed count after execution",softAssert);
	}

	@Test(priority = 7,groups = {"Messaging Workflows"},testName = "Dependent subscriber assertions",description="Dependent subscriber assertions")
	public void Dependentsubscriberassertions() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowTab,webDriver, "Clicking on Project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.miniloader, webDriver, "Wait for mini loader to disappear");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.projectSearchTextBox,"Consumer Workflow for Topic",webDriver,"Input Workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow" );
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.moreoptions,webDriver, "Clicking on elipsis icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.deleteaction,webDriver, "Clicking on First delete  button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.topicsubscriberwf, webDriver, "Verifying the dependent subscriber name",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.Cancelbutton,webDriver, "Clicking on Cancel button");

		//Deleting the second Workflow
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.projectSearchTextBox, webDriver, "Clearing the Search box" );
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.projectSearchTextBox,"Consumer Workflow for Queue",webDriver,"Input Workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.moreoptions, webDriver, "Waiting for elipsis icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.moreoptions,webDriver, "Clicking on elipsis icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.deleteaction,webDriver, "Clicking on First delete  button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queuesubscriberwf, webDriver, "Verifying the dependent subscriber name",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.Cancelbutton,webDriver, "Clicking on Cancel button");
	} 

	@Test(priority = 8,groups = {"Messaging Workflows"},dependsOnMethods = {"createTopicSubscribers"},testName = "Execution verification in MONITOR Page",description="Publisher Execution verification in MONITOR Page")
	public void Executionverification() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.ProjectsPage, webDriver, "Click on Projects page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.MonitorPage, webDriver, "Click on Monitor page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.Workflowexecutionpage, webDriver, "Click on Workflow execution page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.Filterdropdown, webDriver, "Click on Filter dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.projectdropdown, webDriver, "Click on Projects Dropdown");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.FilterSpace, webDriver, "Wait for projects to be listed in dropdown");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, "MaaS_E2ETest",webDriver,"Input Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectmonitoringvalue, webDriver, "Select the Project - click on check box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.workflowdropdown, webDriver, "Click on workflow dropdown");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, "Consumer Workflow for Topic",webDriver,"Input Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectmonitoringvalue, webDriver, "Click on Workflow name - click on check box");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, webDriver, "Clearing the Search box" );
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, "Consumer Workflow for Queue",webDriver,"Input Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectmonitoringvalue, webDriver, "Click on Workflow name");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, webDriver, "Clearing the Search box" );
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, "Queue_PublisherWorkflow",webDriver,"Input Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectmonitoringvalue, webDriver, "Click on Workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.executiondropdown, webDriver, "Click on Projects page");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.wfsearchbox, "Success",webDriver,"Input Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.selectmonitoringvalue, webDriver, "Click on Projects page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.applybutton, webDriver, "Click on Apply button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll down page");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queuePublisherexecution, webDriver, "Verifying the execution of Consumer Workflow for Topic ",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queuePublisherexecution, webDriver, "Clicking on Queue Publisher workflow execution");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.closeexecution, webDriver, "Closing the execution");
	}

	@Test(priority = 9,groups = {"Messaging Workflows"},dependsOnMethods = {"Executionverification"},testName = "Execution verification in MONITOR Page",description="Execution verification in MONITOR Page")
	public void consumerExecutionverification() 
	{
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.topicconsumerexecution, webDriver, "Verifying the execution of Consumer Workflow for Topic.",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.queueconsumerexecution, webDriver, "Verifying the execution of Consumer Workflow for Topic.",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.topicconsumerexecution, webDriver, "Clicking on topic consumer workflow execution");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WFMessagingsTests.WorkflowMessagingLocators.closeexecution, webDriver, "Closing the execution");
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