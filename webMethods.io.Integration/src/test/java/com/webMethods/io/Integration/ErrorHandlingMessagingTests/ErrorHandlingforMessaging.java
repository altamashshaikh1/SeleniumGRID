package com.webMethods.io.Integration.ErrorHandlingMessagingTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class ErrorHandlingforMessaging 
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

	@Test(priority = 0,groups = {"StagPromotion"},description = "Login user : StagPromotion (Projects)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,groups = {"StagPromotion"}, dependsOnMethods={"userLogin"},testName = "Error Handling Scenarios",description="Edit subscriber and Add routing rules")
	public void fatalError() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.projectSearchTextBox,"Clone_MaaS",webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.clonedprojectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Subscriberedit, webDriver, "Clicking on Edit icon of subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EH_settingicon, webDriver, "Clicking on settings icon ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EHSTab, webDriver, "Clicking on Error Handling Tab ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.fatalerrorhandler, webDriver, "Clicking on enable fatal error handler toggle button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Okbutton, webDriver, "Clicking on Ok button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Addroutingrule, webDriver, "Clicking on Add Routing rule");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Routingruletext, "%body/string% == 'EHS'", webDriver, "Providing Filter Value");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.addnewflowbutton,webDriver, "Clicking on addnew flowservice button");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Scroll page down");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Scroll page down");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Scroll page down");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Flownameinsubscriber, "ErrorHandlerCons", webDriver, "Providing a name to the Consumer Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Flowservice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.closeicon, webDriver, "Close Success message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Addrulebttn,webDriver, "Saving the Routing Rule");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.yesEditbutton,webDriver, "Proceed to Update and save");
	}

	@Test(priority = 2,groups = {"StagPromotion"}, dependsOnMethods={"fatalError"},testName = "Error Handling Scenarios",description="Configure Consumer")
	public void Configureconsumer() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.ErrorConsFlow, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.errorConsFlow,webDriver, "Click on Error Flowservice Link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowStepLoaded, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowfirststep,webDriver,"click firststep");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.allservices,webDriver, "Click on All Dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Controlsdropdown,webDriver, "Click on CONTROLS dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.exitFunction,webDriver, "selecting Exit Function");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Exitdropdown,webDriver, "Clicking on the dropdown ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Failurestatus,webDriver, "selecting Failure as status");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.failuremessage, "SuspendingSubscriber", webDriver, "Providing an Input for the service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.saveflow,webDriver, "saving the Flow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Flowsuccessmessage, webDriver, "Validating success message",softAssert);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}

	@Test(priority = 3,groups = {"StagPromotion"}, dependsOnMethods={"Configureconsumer"},testName = "Error Handling Scenarios",description="Publish Message")
	public void Publishmessage() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.projectSearchTextBox,"Clone_MaaS",webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.clonedprojectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.queuepublisherflowlabel,webDriver, "Clicking on Queue Publisher flow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.firstStep,webDriver, "Mouse hover on First Step");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.mappingimage,webDriver, "Open mapping page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.treeplusicon,webDriver, "Expand the JMS Signature");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.bodyplusicon,webDriver, "Expand the body");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.stringinput, webDriver, "Double click on the string input");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Setvalue, webDriver, "EHS", "Update the Inputs");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.savesetvalue,webDriver, "Save the Input");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.runFlowbutton,webDriver, "Executing the Publisher Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowserviceresult, webDriver, "Wait for execution result");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}

	@Test(priority = 4,groups = {"StagPromotion"},dependsOnMethods={"Publishmessage"},testName = "Error Handling Scenarios",description="Validate subscriber suspension")
	public void suspendsubscriberverification() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.SubscriberforQueue_Flowlink, webDriver, "Clicking on Subscriber");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Suspended, "Suspended", webDriver, "Verifying if the subscriber is suspended",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EnableSubscriber, webDriver, "Enabling the subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.successmesage, webDriver, "Validating success message after enabling subscriber");
	}

	@Test(priority = 5,groups = {"StagPromotion"}, dependsOnMethods={"suspendsubscriberverification"},testName = "Error Handling Scenarios",description="Updating subscriber to Transient error to throw exception")
	public void updateTransienterrorThrowException() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Subscriberedit, webDriver, "Clicking on Edit icon of subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EH_settingicon, webDriver, "Clicking on Error Handling section");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EHSTab, webDriver, "Clicking on Error Handling Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.fatalerrorhandler, webDriver, "Disabling error handler");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.retryAttempts, webDriver, "3", "Update the retryAttempts Inputs");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.retryInterval, webDriver, "4", "Update the retryInterval Inputs");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Okbutton, webDriver, "Clicking on messaging Tab");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.yesEditbutton,webDriver, "Proceed to Update and save");
	}

	@Test(priority = 6,groups = {"StagPromotion"}, testName = "Error Handling Scenarios",dependsOnMethods={"updateTransienterrorThrowException"},description="Configure consumer with throw exception service")
	public void updateConsumer()
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.ErrorConsFlow, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.errorConsFlow,webDriver, "Click on Error Flowservice Link");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.firstStep,webDriver, "Mouse hover on First Step");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Moreoptions,webDriver, "Click on the elipsis of first step");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.deletebutton, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Deleteoption,webDriver, "Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Okdeletebttn,webDriver, "Click on Ok button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowStepLoaded, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowfirststep,webDriver,"click firststep");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.allservices,webDriver, "Click on All Dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.servicesdropdown,webDriver, "Click on services dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.FlowFunction,webDriver, "selecting Flow Function service");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.selectservice,"throwExceptionForRetry",webDriver,"Search for throwExceptionForRetry service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.errorexceptionservice,webDriver, "selecting Flow Function service");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.saveflow,webDriver, "saving the Flow");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Flowloader, webDriver, "Wait for Loader");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Flowsuccessmessage, webDriver, "Validating success message",softAssert);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}	

	@Test(priority = 7,groups = {"StagPromotion"}, dependsOnMethods={"updateConsumer"},testName = "Error Handling Scenarios",description="Updating subscriber to Transient error to throw exception")
	public void publishandVerifyRetrys() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.queuepublisherflowlabel,webDriver, "Clicking on Queue Publisher flow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.runFlowbutton,webDriver, "Executing the Publisher Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowserviceresult, webDriver, "Wait for execution result");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}

	@Test(priority = 8,groups = {"StagPromotion"},testName = "Error Handling Scenarios",description="Validting Error handler flow in monitor page")
	public void Validatemonitorpage() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.ProjectsPage, webDriver, "Click on Projects page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.MonitorPage, webDriver, "Click on Monitor page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.FlowExecutionpage, webDriver, "Click on Flowservice execution page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Filterdropdown, webDriver, "Click on Filter dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.projectdropdown, webDriver, "Click on project dropdown");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.wfsearchbox, "Clone_MaaS",webDriver,"Input Project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.selectmonitoringvalue, webDriver, "Select the project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowDropdown, webDriver, "Click on Flowservice filter");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.wfsearchbox, "ErrorHandlerCons",webDriver,"Input Flow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.selectmonitoringvalue, webDriver, "Select the Flow");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.wfsearchbox, "Queue_Publisher",webDriver,"Input Flow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.selectmonitoringvalue, webDriver, "Select the Flow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.executiondropdown, webDriver, "Click on execution dropdown");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.wfsearchbox, "Successful",webDriver,"Input Success Value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.selectmonitoringvalue, webDriver, "Select success status");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.wfsearchbox, webDriver, "Clearing the Search box" );
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.wfsearchbox, "Fail",webDriver,"Input Fail Value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.selectmonitoringvalue, webDriver, "Select Fail Status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.contextID, webDriver, "Click on context id");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.applybutton, webDriver, "Click on Apply button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Flowexecutionlink, webDriver, "Click on Flow execution");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Transactioncount_Value, webDriver, "1",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.ProjectsPage, webDriver, "Click on Projects page");
	}

	@Test(priority = 9, groups = {"StagPromotion"}, dependsOnMethods={"Validatemonitorpage"},testName = "Error Handling Scenarios",description="Enabling the suspendAndRetrylater option")
	public void suspendAndRetrylater() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.projectSearchTextBox,"Clone_MaaS",webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.clonedprojectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Subscriberedit, webDriver, "Clicking on Edit icon of subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EH_settingicon, webDriver, "Clicking on Error Handling section");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EHSTab, webDriver, "Clicking on Error Handling Tab");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.retryAttempts, webDriver, "1", "Update the retryAttempts Inputs");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.retryInterval, webDriver, "4", "Update the retryInterval Inputs");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.retryfailuredropdown, webDriver, "Clicking on retry on failure dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.suspendandretrylater, webDriver, "Selecing the suspendandretrylater option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Okbutton, webDriver, "Clicking on Ok button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.yesEditbutton,webDriver, "Proceed to Update and save");
	}

	@Test(priority = 10,groups = {"StagPromotion"}, dependsOnMethods={"suspendAndRetrylater"},testName = "Error Handling Scenarios",description="Publishing a Message")
	public void publishandVerifySuspended()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowTab,webDriver, "Clicking on Flow tab name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.queuepublisherflowlabel,webDriver, "Clicking on Queue Publisher flow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.runFlowbutton,webDriver, "Executing the Publisher Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowserviceresult, webDriver, "Wait for execution result");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton, webDriver, "wait for back button to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.flowbackbutton,webDriver, "Clicking on Back button");
	}

	@Test(priority = 11,groups = {"StagPromotion"}, dependsOnMethods={"publishandVerifySuspended"},testName = "Error Handling Scenarios",description="Validation of Suspended subscriber and pending message")
	public void pendingMessage() throws InterruptedException
	{
		Thread.sleep(5000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.SubscriberforQueue_Flowlink, webDriver, "Clicking on Subscriber");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Suspended, "Suspended", webDriver, "Verifying if the subscriber is suspended",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.QueueFlowmetrics, webDriver, "Clicking on Queue name");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.pendingValue, "1", webDriver, "Verifying pending count after execution",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.SubscriberforQueue_Flowlink, webDriver, "Clicking on Subscriber");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Suspended, "Suspended", webDriver, "Verifying if the subscriber is suspended",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.EnableSubscriber, webDriver, "Enabling the subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.successmesage, webDriver, "Validating success message after enabling subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.QueueFlowmetrics, webDriver, "Clicking on Queue name");
		//	SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.pendingValue0, webDriver, "Waiting for pending value to be 0");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.pendingValue, "1", webDriver, "Verifying pending count after execution",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.subscribersTab,webDriver, "Clicking on Subscriber Tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.SubscriberforQueue_Flowlink, webDriver, "Clicking on Subscriber");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.Suspended, "Suspended", webDriver, "Verifying if the subscriber is suspended",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.messagingTab, webDriver, "Clicking on messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.QueueFlowmetrics, webDriver, "Clicking on Queue name");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ErrorHandlingMessagingTests.ErrorHandlingforMessagingLocators.pendingValue, "1", webDriver, "Verifying pending count after execution",softAssert);
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