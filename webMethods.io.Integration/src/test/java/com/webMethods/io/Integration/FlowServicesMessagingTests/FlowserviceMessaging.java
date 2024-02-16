package com.webMethods.io.Integration.FlowServicesMessagingTests;

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

public class FlowserviceMessaging 
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

	@Test(priority = 0,groups = {"Messaging Flowservices"},description = "Login user : StagPromotion (Projects)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1, dependsOnMethods = {"userLogin"} ,groups = {"Messaging Flowservices"},testName = "Creating Messaging assets",description="Creating queues and Topics")
	public void createdestinationassets() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.projectSearchTextBox,PropertiesData.readInputData("projectNameMaas"),webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.projectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newQueuebutton,webDriver, "Clicking on Add Queue Button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.queuetext, "Queue_Flow", webDriver, "Providing a name to the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.savebutton,webDriver, "Saving the Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.queuelist,webDriver, " Returning to the Queue listing page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closeicon,webDriver, "Clicking to close popup");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newQueuebutton, webDriver, "Wait" );
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.topicsTab,webDriver, "Clicking on Topics tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newTopicbutton,webDriver, "Clicking on Add Topic Button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.topictext, "Topic_Flow", webDriver, "Providing a name to the Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.savebutton,webDriver, "Saving the Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closeicon,webDriver, "Clicking to close popup");
	}

	@Test(priority = 2,groups = {"Messaging Flowservices"},dependsOnMethods={"createdestinationassets"},testName = "Creating Subscribers using Flowservices",description="Creating Subscribers")
	public void createSubscribers() 
	{	    	      
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newSubscriberbutton,webDriver, "Adding new subscriber");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribertext, "SubscriberforQueue_Flow", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinationtypedropdown,webDriver, "Selecting the destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinationtypevaluequeue,webDriver, "Selecting the destination type value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.selectdestinationdropdown,webDriver, "Cicking on select Destination dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.selectDestinationqueue,webDriver, "Selecting the Queue");   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.addnewflowbutton,webDriver, "Clicking on addnew flowservice button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Flownameinsubscriber, "Queue_ConsumerFlow", webDriver, "Providing name to the consumer Flowervice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closeicon, webDriver, "Wait");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newSubscriberbutton,webDriver, "Adding new subscriber");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribertext, "SubscriberforTopic_Flow", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinationtypedropdown,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinationtypevaluetopic,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.selectdestinationdropdown,webDriver, "Cicking on select Destination dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.selectDestinationtopic,webDriver, "Selecting the Topic");   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.addnewflowbutton,webDriver, "Clicking on addnew flowservice button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Flownameinsubscriber, "Topic_ConsumerFlow", webDriver, "Providing a name to the Consumer Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closeicon, webDriver, "Close Success message");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newFlowLabel, webDriver, "Wait for Flowservice page to disappear");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.saveandexitbutton,webDriver, "Saving the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.subscribersTab, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closeicon,webDriver, "Clicking to close popup");
	}

	@Test(priority = 4,groups = {"Messaging Flowservices"},testName = "Creating Messaging assets",description="Creating Publisher Flowservices")
	public void createPublisherFlows() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newflowserviceicon,webDriver, "Clickig on Plus icon of flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowStepLoaded, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowfirststep,webDriver,"click on first step");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.allservices,webDriver, "Click on All Dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.connectorsinflow,webDriver, "Click on Connectors dropdwon");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowfirststep,PropertiesData.readInputData("searchConnector"),webDriver,"Search messaging connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.messagingconnector,webDriver, "select Messaging connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.searchoperation,webDriver, "search Operation");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.publishoperation,webDriver, "select publish operation");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.searchdestinationtype,webDriver, "search destination type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinantionqueue,webDriver, "select destinantion type as Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.searchdestinationname,webDriver, "search destination name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinantionqueuename,webDriver, "Select the Queue as destination");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.account,webDriver, "Select account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.defaultaccount,webDriver, "Select account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mappingimage,webDriver, "Open mapping page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.treeplusicon,webDriver, "Expand the JMS Signature");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.bodyplusicon,webDriver, "Expand the body");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.stringinput, webDriver, "Double click on the string input");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Setvalue,PropertiesData.readInputData("QueueData"),webDriver,"Providing value to the input");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.savesetvalue,webDriver, "Save the Input");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.blocker, webDriver, "Wait for blocker element to disappear");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.StringLabel, webDriver, "Wait for string element to appear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closepipeline,webDriver,"Closing mapping page");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowname, webDriver, PropertiesData.readInputData("FlowNameforQueuePublisher"), "Name the Publisher Flow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.runFlowbutton,webDriver, "Executing the Publisher Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowserviceresult, webDriver, "Wait for execution result");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");

		// Creating the Topic Publisher
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.newflowserviceicon,webDriver, "Clickig on Plus icon of flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowStepLoaded, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowfirststep,webDriver,"click on firs step");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.allservices,webDriver, "Click on All Dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.connectorsinflow,webDriver, "Click on Connectors action");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowfirststep,PropertiesData.readInputData("searchConnector"),webDriver,"Search messaging connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.messagingconnector,webDriver, "select Messaging connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.searchoperation,webDriver, "search Operation");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.publishoperation,webDriver, "select publish operation  ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.searchdestinationtype,webDriver, "search destination type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinantiontopic,webDriver, "select destinantion type as Queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.searchdestinationname,webDriver, "search destination name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.destinantiontopicname,webDriver, "Select the Queue as destination");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.account,webDriver, "Select account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.defaultaccount,webDriver, "Select account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mappingimage,webDriver, "Open mapping page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.treeplusicon,webDriver, "Expand the JMS Signature");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.bodyplusicon,webDriver, "Expand the body");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.stringinput, webDriver, "Double click on the string input");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Setvalue,PropertiesData.readInputData("QueueData"),webDriver,"Providing value to the input");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.savesetvalue,webDriver, "Save the Input");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.blocker, webDriver, "Wait for blocker element to disappear");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.StringLabel, webDriver, "Wait for string element to appear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closepipeline,webDriver,"Closing mapping page");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowname, webDriver, "Topic_Publisher", "Name the Publisher Flow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.runFlowbutton,webDriver, "Executing the Publisher Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowserviceresult, webDriver, "Wait for execution result");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}

	@Test(priority = 3,groups = {"Messaging Flowservices"},testName = "Creating Subscribers using Flowservices",description="Configuring the Consumer flowservices")
	public void configureconsumerflowservices() 
	{	    
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.topicconsumerflowlabel,webDriver, "Clicking on Flow tab name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowStepLoaded, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowfirststep,webDriver,"click firststep");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.allservices,webDriver, "Click on All Dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.servicesdropdown,webDriver, "Click on services dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.FlowFunction,webDriver, "selecting Flow Function service");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.selectservice,"logCustomMessage",webDriver,"Search for logCustomMessage service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.logcustommessageservice,webDriver, "selecting logcustommessage service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mappingicon,webDriver, "Click on mapping icon");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.treeplusicon,webDriver, "Clicking to expand the JMS signature");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.bodyplusicon,webDriver, "Clicking to expand the JMS signature of body");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.stringinput, webDriver, "Select string Value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.logcustommessageinput, webDriver, "Select message input");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mapdisabled, webDriver, "Wait for element to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mapicon, webDriver, "Select Map icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closepipeline,webDriver, "Closing Mapping page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.saveflow,webDriver, "saving the Flow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Flowloader, webDriver, "Wait for Loader");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Flowsuccessmessage, webDriver, "Validating success message",softAssert);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");

		//Configuring the second flow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.queueconsumerflowlabel,webDriver, "Clicking on Flow tab name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowStepLoaded, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowfirststep,webDriver,"click first step");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.allservices,webDriver, "Click on All Dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.servicesdropdown,webDriver, "Click on services dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.FlowFunction,webDriver, "selecting Flow Function service");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.selectservice,"logCustomMessage",webDriver,"Search for logCustomMessage service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.logcustommessageservice,webDriver, "selecting logcustommessage service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mappingicon,webDriver, "Click on mapping icon");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.treeplusicon,webDriver, "Clicking to expand the JMS signature");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.bodyplusicon,webDriver, "Clicking to expand the JMS signature of body");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.stringinput, webDriver, "Select string Value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.logcustommessageinput, webDriver, "Select message input");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mapicon, webDriver, "Wait for map element to be enabled");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mapdisabled, webDriver, "Wait for element to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.mapicon, webDriver, "Select message input");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.closepipeline,webDriver, "Closing Mapping page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.saveflow,webDriver, "saving the Flow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Flowloader, webDriver, "Wait for Loader");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Flowsuccessmessage, webDriver, "Validating success message",softAssert);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}	

	@Test(priority = 5,groups = {"Messaging Flowservices"},dependsOnMethods={"createPublisherFlows"},testName = "Creating Subscribers using Flowservices",description="Destination asset count verification")
	public void Executionverifications() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.QueueFlowmetrics, webDriver, "Clicking on Queue name");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.publishedvalue, "1", webDriver, "Verifying published count after execution",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.consumedvalue, "1", webDriver, "Verifying consumed count after execution",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.topicstab, webDriver, "Clicking on subscribers Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.TopicFlowmetrics, webDriver, "Clicking on Topic name");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.publishedvalue, "1", webDriver, "Verifying published count after execution",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.consumedvalue, "1", webDriver, "Verifying consumed count after execution",softAssert);
	}

	//@Test(priority = 6,groups = {"Messaging Flowservices"},dependsOnMethods={"createSubscribers"},testName = "Creating Subscribers using Flowservices",description="Verification of dependent assertions")
	public void Dependentassertions() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.projectSearchTextBox,"Topic_ConsumerFlow",webDriver,"Search for the Flow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.moreoptions, webDriver, "wait for elipsis element to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.moreoptions,webDriver, "Clicking on elipsis icon");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.moreoptions,webDriver, "Clicking on elipsis icon"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.deleteaction,webDriver, "Clicking on First delete  button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.dependentsubscribertopic, webDriver, "Verifying the dependent subscriber name",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Cancelbutton,webDriver, "Clicking on Cancel button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.projectSearchTextBox,"Topic_ConsumerFlow",webDriver,"Search for the Flow");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.projectSearchTextBox, webDriver, "Queue_ConsumerFlow", "Search for the Flow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.moreoptions,webDriver, "Clicking on elipsis icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.moreoptions,webDriver, "Clicking on elipsis icon"); 
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.deleteaction, webDriver, "Wait for delete button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.deleteaction,webDriver, "Clicking on First delete  button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.dependentsubscriberqueue, webDriver, "Verifying the dependent subscriber name",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FlowServicesMessagingTests.FlowServiceMessagingLocators.Cancelbutton,webDriver, "Clicking on Cancel button");
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