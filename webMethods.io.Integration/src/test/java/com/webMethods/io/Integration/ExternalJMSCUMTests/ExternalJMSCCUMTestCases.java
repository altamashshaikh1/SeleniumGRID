package com.webMethods.io.Integration.ExternalJMSCUMTests;

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

public class ExternalJMSCCUMTestCases 
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

	@Test(priority = 0,groups = {"JMS - External Messaging  service"},description = "Login user : StagPromotion (Projects)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,groups = {"JMS - External Messaging  service"},dependsOnMethods = {"userLogin"},testName = "Creating JMS Based subscribers",description="Creating JMS Subscriber using Queue and Topic")
	public void createSubscriberQueue() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.projectSearchTextBox,PropertiesData.readInputData("projectNameJMS"),webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMSprojectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.newSubscriberbutton,webDriver, "Click on add new subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.subscribertext, "JMSQueueSubscriber", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectortypedropdown,webDriver, "Selecting the Connector type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectortypeJMS,webDriver, "Selecting the Connector type as JMS");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.addJMSaccount,webDriver, "Selecting the Connector type as JMS");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.AccountnameInput, webDriver, "JMS_1", "Providing a name to the Account");
		//SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUM.ExternalJMSCCUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Clicking on the next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.providerURL, PropertiesData.readInputData("InvalidJMSProviderURL"), webDriver, "Providing the URL");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.accclientID, "QueueTestID", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connfactorylookupname, "JMSConnectionFactory", webDriver, "Providing value for connection factory");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.accusername, "Metering", webDriver, "Providing the username");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.accpassword, "C!0udC0nt@!nerQ@2o24", webDriver, "Providing the password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.maxRetryCount, "25", webDriver, "Providing the value for max retry count");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.toggleOffButton,webDriver, "Enabling the JMS Caching option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.toggleOffButton,webDriver, "Enabling the connection per subscriber option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.savejmsaccountbutton,webDriver, "Clicking on SAVE Button of Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.miniloader, webDriver, "Wait");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jmsDestinationname, "Queue_Automation", webDriver, "Providing a name to the destination");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.addflowbutton, webDriver, "Wait for the add new flowservice button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.addnewflowbutton,webDriver, "Clicking on addnew flowservice button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Flownameinsubscriber, "JMSConsumer_Queue", webDriver, "Providing name to the consumer Flowervice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Click on Close Icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Click on Close Icon");
//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EventsHeader, webDriver, "Wait for events header to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.newsubelement, webDriver, "Wait");
		// Creating subscriber for the JMS Topic based subscriber
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.newSubscriberbutton,webDriver, "Click on add new subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.subscribertext, "JMSTopicSubscriber", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectortypedropdown,webDriver, "Selecting the Connector type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectortypeJMS,webDriver, "Selecting the Connector type as JMS");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMSaccountdropdown,webDriver, "Selecting the Connector account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_1Account,webDriver, "Selecting the Account for JMS");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.destinationtypedropdown,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.destinationtypevaluetopic,webDriver, "Selecting destination type dropdown");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jmsDestinationname, "Topic_Automation", webDriver, "Providing a name to the Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.addnewflowbutton,webDriver, "Clicking on addnew flowservice button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Flownameinsubscriber, "JMSConsumer_Topic", webDriver, "Providing name to the consumer Flowervice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Flowservice");
//		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.newFlowLabel, webDriver, "Wait for Flowservice page to disappear");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Click on Close Icon");
//		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon, webDriver, "Click on Close Icon");
//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EventsHeader, webDriver, "Wait for events header to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.newsubelement, webDriver, "Wait");
//		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.closeicon,webDriver, "Clicking to close popup");
	}

	@Test(priority = 2,groups = {"JMS - External Messaging  service"}, dependsOnMethods = {"createSubscriberQueue"}, testName = "Creating Workflow using JMS Connector",description="Updating the JMS Account")
	public void UpdateAccount() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectorsTab,webDriver, "Click on the connectors tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.RESTconnectorsTab,webDriver, "Click on the REST connectors tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.PredefinedconnectorsTab,webDriver, "Click on the PredefinedconnectorsTab connectors tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMSConnector,webDriver,"Mousehover on JMS Connector");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.searchboxInput,"JMS", webDriver, "Searching for JMS connector");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jms_1Acc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.DisableAccount,webDriver, "Expanding the JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.yesButton,webDriver, "Click on Yes Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jms_1Acc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EditAccount,webDriver, "Edit the JMS Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.providerURL, webDriver, PropertiesData.readInputData("JMSProviderURL"), "Updating the account with working Provider URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.savejmsaccountbutton,webDriver, "Saving the account");
		//SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUM.ExternalJMSCCUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jms_1Acc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.EnableAccount,webDriver, "Enable the account");
	}

	@Test(priority = 3,groups = {"JMS - External Messaging  service"}, dependsOnMethods = {"createSubscriberQueue"}, testName = "Validating dependency of account",description="Validating dependency of account")
	public void Validatedependency() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jms_1Acc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.DeleteAccount,webDriver, "Delete the account");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Jmssubscriber, webDriver, "Verifying the dependent subscriber name",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Cancelbutton,webDriver, "Clicking on Cancel button");
	}

	@Test(priority = 4,groups = {"JMS - External Messaging  service"}, testName = "Creation of TwoWay SSL Account", dependsOnMethods = {"UpdateAccount"}, description="Creation of TwoWay SSL Account")
	public void twowaySSLAccount() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMSConnector,webDriver,"Mousehover on JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jmsNewAccount,webDriver, "Clicking on create new Account");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.AccountnameInput, webDriver, "JMS_2waySSL_Acc", "Providing a name to the Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.providerURL, PropertiesData.readInputData("JMS2waySSLURL"), webDriver, "Providing the 2waySSL URL");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.accclientID, "2waySSLClient", webDriver, "Providing a client ID");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connfactorylookupname, "EventFactory", webDriver, "Providing the connection factory name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.accusername, "Metering", webDriver, "Providing the username");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.accpassword, "C!0udC0nt@!nerQ@2o24", webDriver, "Providing the password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.enableSSLbutton,webDriver, "Enable 2way SSL option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Keystore_aliasdropdown,webDriver, "Click on keystore alias dropdown");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.jmskey,webDriver, "Selecting the JMS Key");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.keyalias,webDriver, "Clicking on key Alias dropdown");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.keyalias_dropdown,webDriver, "Clicking on the alias dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.maxRetryCount, "25", webDriver, "Providing the value for max retry count");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.toggleOffButton,webDriver, "Enabling the JMS Caching option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.toggleOffButton,webDriver, "enabling the option connections per subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.savejmsaccountbutton,webDriver, "Clicking on save button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.miniloader, webDriver, "Wait");
	}

	@Test(priority = 5,groups = {"JMS - External Messaging  service"}, dependsOnMethods = {"createSubscriberQueue"}, testName = "Creating Workflow using JMS Connector",description="Publishing a message to the Queue through workflow")
	public void publishtoQueue() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowTab,webDriver, "Clicking on Workflow Tab Name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.miniloader, webDriver, "Wait for mini loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.wfplusicon, webDriver, "Click on new workflow plus button");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.createnewwfbtn, webDriver, "New workflow button"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.actionSearchTextBox,PropertiesData.readInputData("searchJMSConnector"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectorJMS,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.loggerActionId,webDriver,"Mouseover on logger action"); 
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.firstActionConnector,com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumWebElementsUtils.openconnector(webDriver,"JMS");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Connect_toJMS,webDriver, "Click on account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_1Account,webDriver, "selecting the JMS_1 account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Next Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMSdestinationNamelabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("JMSQueueName"), "Queue Textbox");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Page scroll down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.StringLabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("QueueData"), "Queue Textbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Next2 Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.doneButton, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.editWFicon, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowfName, webDriver, "name  Action");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowfName, webDriver, "JMS_PublisherWorkflow", "Name the consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowdoneButton, webDriver, "Done for workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.saveworkflowbutton, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.runworkflowbutton, webDriver, "Clicking on the Run Button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.exitworkflow, webDriver, "Waiting for back button to be vsisible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowbackbutton, webDriver, "Clicking on the back Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.miniloader, webDriver, "Wait for mini loader to disappear");	
	}

	@Test(priority = 6,groups = {"JMS - External Messaging  service"}, dependsOnMethods = {"createSubscriberQueue"}, testName = "Creating Workflow using JMS Connector",description="Publishing a message to the Topic through Workflow")
	public void publishtoTopic() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.wfplusicon, webDriver, "Click on new workflow plus button");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.createnewwfbtn, webDriver, "New workflow button"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.actionSearchTextBox,PropertiesData.readInputData("searchJMSConnector"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.connectorJMS,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.loggerActionId,webDriver,"Mouseover on logger action"); 
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.firstActionConnector,com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.stopAction,webDriver,"Connect JMS action with stop action");
		SeleniumWebElementsUtils.openconnector(webDriver,"JMS");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.Connect_toJMS,webDriver, "Click on account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMS_1Account,webDriver, "selecting the JMS_1 account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Next Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.JMSdestinationNamelabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("JMSTopicName"), "Queue Textbox");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.StringLabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("TopicData"), "Queue Textbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.nextButton, webDriver, "Next2 Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.doneButton, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.editWFicon, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowfName, webDriver, "name  Action");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowfName, webDriver, "JMS_TopicPublisherWorkflow", "Name the consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowdoneButton, webDriver, "Done for workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.saveworkflowbutton, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.runworkflowbutton, webDriver, "Clicking on the Run Button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.exitworkflow, webDriver, "Waiting for back button to be vsisible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSCUMTests.ExternalJMSCCUMLocators.workflowbackbutton, webDriver, "Clicking on the back Button");
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