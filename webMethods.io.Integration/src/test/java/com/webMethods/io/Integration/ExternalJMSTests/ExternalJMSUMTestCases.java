package com.webMethods.io.Integration.ExternalJMSTests;

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

public class ExternalJMSUMTestCases 
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

	@Test(priority = 0,groups = {"JMS - Universal Messaging  service"},description = "Login user : StagPromotion (Projects)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,groups = {"JMS - Universal Messaging  service"},dependsOnMethods = {"userLogin"},testName = "Creating JMS Based subscribers",description="Creating JMS Subscriber using Queue for External UM")
	public void createJMSSubscriberQueue() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.projectSearchTextBox,PropertiesData.readInputData("projectNameJMS"),webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMSprojectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.newSubscriberbutton,webDriver, "Click on add new subscriber");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.subscribertext, "JMS_UM_QueueSubscriber", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.connectortypedropdown,webDriver, "Selecting the Connector type dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.connectortypeJMS,webDriver, "Selecting the Connector type as JMS");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.addJMSaccount,webDriver, "Selecting the Connector type as JMS");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.AccountnameInput, webDriver, "JMS_UM_Provider", "Providing a name to the Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.cloudConatinerUMText,webDriver, "Click on Cloud Container Universal Messaging text from Dropdown");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.universalMessagingText,webDriver, "Click on Universal Messaging text from Dropdown");		
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Clicking on the next button");	
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.clusteredUMDefaultValue,webDriver,"Checking for Default value of Clustered UM to be disabled",softAssert);

		// Testcases related to protocol update for Provider URL and Failover
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.UMProviderURL, PropertiesData.readInputData("InvalidCCJMSProviderURL"), webDriver, "Providing the URL");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.addFailoverNodeButton,webDriver, "Click on Add Failover Node button");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.failoverURLTextArea, PropertiesData.readInputData("InvalidCCJMSProviderURL"), webDriver, "Providing the URL");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhpsprotocol,webDriver,"Assert nhps protocol in the Provider URL");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhpsfailover,webDriver,"Assert nhps protocol in the FAILOVER List");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhpsprotocol,webDriver, "Click on the Provider URL Protocol nhps");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nspsprotocol,webDriver, "Click and change the Provider URL Protocol to nsps");		
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nspsfailover,webDriver,"Assert nsps protocol in the FAILOVER List");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nspsprotocol,webDriver, "Click and change the Provider URL Protocol to nsps");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhp_protocol,webDriver, "Click and change the Provider URL Protocol to nhp");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhpfailover,webDriver,"Assert nhp protocol in the FAILOVER List");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhp_protocol,webDriver, "Click and change the Provider URL Protocol to nhp");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nsp_protocol,webDriver, "Click and change the Provider URL Protocol to nsp");		
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nspfailover,webDriver,"Assert nsp protocol in the FAILOVER List");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nsp_protocol,webDriver, "Click and change the Provider URL Protocol to nsp");		
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhpsprotocol,webDriver,"Assert nhps protocol in the Provider URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nhpsprotocol,webDriver, "Click and change the Provider URL Protocol to nhps");	
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.accclientID, "QueueTestID", webDriver, "Providing a name to the Subscriber");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.connfactorylookupname, "JMSConnectionFactory", webDriver, "Providing value for connection factory");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.accusername, "Metering", webDriver, "Providing the username");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.accpassword, "C!0udC0nt@!nerQ@2o24", webDriver, "Providing the password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maxRetryCount, "25", webDriver, "Providing the value for max retry count");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.toggleOffButton,webDriver, "Enabling the JMS Caching option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.toggleOffButton,webDriver, "Enabling the connection per subscriber option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savejmsaccountbutton,webDriver, "Clicking on SAVE Button of Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jmsDestinationname, "Queue_Automation", webDriver, "Providing a name to the destination");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.addflowbutton, webDriver, "Wait for the add new flowservice button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.addnewflowbutton,webDriver, "Clicking on addnew flowservice button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Flownameinsubscriber, "JMSUMConsumer_Queue", webDriver, "Providing name to the consumer Flowervice");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.saveconsumerflowbutton,webDriver, "Saving the consumer Flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.closeicon, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.closeicon, webDriver, "Click on Close Icon");
//		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savebutton,webDriver, "Saving the Subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.closeicon, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.closeicon, webDriver, "Click on Close Icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.newsubelement, webDriver, "Wait");
	}

	@Test(priority = 2,groups = {"JMS - Universal Messaging  service"},dependsOnMethods = {"createJMSSubscriberQueue"}, testName = "Creating Workflow using JMS Connector",description="Updating the JMS Account with Valid data and Failover")
	public void UpdateAccount() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.connectorsTab,webDriver, "Click on the connectors tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.RESTconnectorsTab,webDriver, "Click on the REST connectors tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.PredefinedconnectorsTab,webDriver, "Click on the PredefinedconnectorsTab connectors tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMSConnector,webDriver,"Mousehover on JMS Connector");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.searchboxInput,"JMS", webDriver, "Searching for JMS connector");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.DisableAccount,webDriver, "Expanding the JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.yesButton,webDriver, "Click on Yes Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EditAccount,webDriver, "Edit the JMS Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deleteProvider,webDriver, "Click on delete button");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.UMProviderURL, webDriver, PropertiesData.readInputData("CCJMSProviderURL"), "Updating the account with working Provider URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.addFailoverNodeButton,webDriver, "Click on Add Failover Node button");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.failoverURLTextArea, PropertiesData.readInputData("CCJMSProviderURL"), webDriver, "Providing the URL for failover");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savejmsaccountbutton,webDriver, "Saving the account");
	//	SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Collapse_connector,webDriver, "Click on collapse button");
	}

	@Test(priority =3 ,groups = {"JMS - Universal Messaging  service"},dependsOnMethods = {"UpdateAccount"}, testName = "Creating Workflow using JMS Connector",description="Validating Cluster Enabled Properties")
	public void clusterEnabledProperties() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.DisableAccount,webDriver, "Expanding the JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.yesButton,webDriver, "Click on Yes Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EditAccount,webDriver, "Edit the JMS Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.clusteredUMtoggleButton,webDriver, "Enable the Clustered UM Option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.enabledFollowTheMaster,"Enabled",webDriver,"Checking if Follow the master is enabled",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deleteProvider,webDriver, "verifying that the delete button has disappeared");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.failoverURLTextArea, webDriver, PropertiesData.readInputData("CCJMSProviderURL"), "Updating the account with working Provider URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.addRealmNodeButton,webDriver, "Click on the Add Realm Node Button");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.failoverURLTextArea, webDriver, "DummyValue", "Updating the account with working Provider URL");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deleteProvider,webDriver, "Click on delete button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.toggleOnButton,webDriver, "Disabling the JMS Caching option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savejmsaccountbutton,webDriver, "Saving the account");
	}

	@Test(priority = 4,groups = {"JMS - Universal Messaging  service"}, dependsOnMethods = {"clusterEnabledProperties"}, testName = "Validating for Retry Connection mode as Sequential",description="Validating for Retry Connection mode as Sequential")
	public void retrySequentially() 
	{
//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Collapse_connector,webDriver, "Click on collapse button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.DisableAccount,webDriver, "Expanding the JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.yesButton,webDriver, "Click on Yes Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EditAccount,webDriver, "Edit the JMS Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.followTheMasterToggleButton,webDriver, "Disable Follow the Master");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.retrySequentially,webDriver,"Assert retry sequentially Value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.toggleOffButton,webDriver, "Enabling the JMS Caching option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savejmsaccountbutton,webDriver, "Saving the account");
	}

	@Test(priority = 5,groups = {"JMS - Universal Messaging  service"}, testName = "Validating for Retry Connection mode as Random",description="Validating for Retry Connection mode as Random")
	public void retryRandomly() 
	{
	//	SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Collapse_connector,webDriver, "Click on collapse button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.DisableAccount,webDriver, "Expanding the JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.yesButton,webDriver, "Click on Yes Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EditAccount,webDriver, "Edit the JMS Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.retrySequentially,webDriver, "Select Retry Sequentially from dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.retryRandomly,webDriver, "Select Retry Randomly from dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minimumSessionPool,webDriver, "Click on Minimum session pool field");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savejmsaccountbutton,webDriver, "Saving the account");
	}

	@Test(priority = 6,groups = {"JMS - Universal Messaging  service"},dependsOnMethods = {"retryRandomly"}, testName = "Validating producer retry Properties",description="Validating producer retry Properties")
	public void producerRetry() 
	{
//		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Collapse_connector,webDriver, "Click on collapse button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.DisableAccount,webDriver, "Expanding the JMS Connector");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.yesButton,webDriver, "Click on Yes Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EditAccount,webDriver, "Edit the JMS Account");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minicircle, webDriver, "Wait for circle to disappear");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Wait for next button to be clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton,webDriver, "Click on next button");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maxRetryCount,webDriver, "101", "Providing the value for max retry count");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.errorValidator,"Error: Max retry count must be between 1 and 100.",webDriver,"Checking for maximum limit of Max Retry Count Field value",softAssert);
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maxRetryCount,webDriver, "2", "Providing the valid value for max retry count");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.retryInterval,webDriver,  "3601", "Providing the value for retry Interval");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.errorValidator,"Error: Retry interval must be between 4 and 3600.",webDriver,"Checking for maximum limit of Retry Interval Field value",softAssert);
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maxRetryCount,webDriver,  "0", "Providing 0 as the value for max retry count");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.retryIntervalDisabled,webDriver,"Assert for Retry Interval disabled field");		
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minimumSessionPool, webDriver, "501", "Updating the value of Minimum session pool size more than the maximum limit");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.errorValidator,"Error: Minimum pool size must be between 1 and 500.",webDriver,"Checking for maximum limit of Minimum session pool size field",softAssert);
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.minimumSessionPool, webDriver, "20", "Updating the value of Minimum session pool size with a valid value");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maximumSessionPool, webDriver, "501", "Updating the value of Maximum session pool size more than the limit");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.errorValidator,"Error: Maximum pool size must be between 1 and 500.",webDriver,"Checking for maximum limit of Maximum session pool size field",softAssert);
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maximumSessionPool, webDriver, "10", "Updating the value of Maximum session pool size less than the Minimum pool size");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.errorValidator,"Error: Maximim pool size must be greater than Minimum pool size",webDriver,"Checking for Maximim pool size must be greater than Minimum pool size ",softAssert);
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.maximumSessionPool, webDriver, "30", "Updating the value of Maximum session pool size more than the Minimum pool size");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.idleTimeout, webDriver, "901", "Updating the value of idle timeout more than the limit");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.errorValidator,"Error: Idle time out must be between 1 and 900.",webDriver,"Checking for maximum limit of idle timeout",softAssert);
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.idleTimeout, webDriver, "90", "Updating the value of idle timeout more than the limit");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.savejmsaccountbutton,webDriver, "Saving the account");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EnableAccount,webDriver, "Enable the account");
	}

	@Test(priority = 7,groups = {"JMS - Universal Messaging  service"}, testName = "Publishing a message to the Queue through workflow",description="Publishing a message to the Queue through workflow")
	public void publishtoUMQueue() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.workflowTab,webDriver, "Clicking on Workflow Tab Name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait for mini loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.wfplusicon, webDriver, "Click on new workflow plus button");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.createnewwfbtn, webDriver, "New workflow button"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.activitiesPanelString, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.actionSearchTextBox,PropertiesData.readInputData("searchJMSConnector"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.connectorJMS,webDriver,"Drag and drop logger action on canvas", -900, -20);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.loggerActionId,webDriver,"Mouseover on logger action"); 
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.firstActionConnector,com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumWebElementsUtils.openconnector(webDriver,"JMS");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Connect_toJMS,webDriver, "Click on account dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_UM_ProviderAccount,webDriver, "selecting the JMS_UM_Provider account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Next Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMSdestinationNamelabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("JMSQueueName"), "Queue Textbox");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.StringLabel, webDriver, "Click on StringLabel text");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("QueueData"), "Queue Textbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.nextButton, webDriver, "Next2 Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.doneButton, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.editWFicon, webDriver, "Done Action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.workflowfName, webDriver, "name  Action");
		SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.workflowfName, webDriver, "JMS_UMPublisherWorkflow", "Name the consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.workflowdoneButton, webDriver, "Done for workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.saveworkflowbutton, webDriver, "Click on the save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.canvasloader, webDriver, "Wait for back button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.runworkflowbutton, webDriver, "Clicking on the Run Button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.exitworkflow, webDriver, "Waiting for back button to be vsisible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.workflowbackbutton, webDriver, "Clicking on the back Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait for mini loader to disappear");
	}

	@Test(priority = 8,groups = {"JMS - Universal Messaging  service"},testName = "Verifying monitor Page",description="Verifying monitor Page for JMS Flowservice execution")
	public void ValidateFlowmonitorpage() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.ProjectsPage, webDriver, "Click on Projects page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.MonitorPage, webDriver, "Click on Monitor page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.FlowExecutionpage, webDriver, "Click on Flowservice execution page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Filterdropdown, webDriver, "Click on Filter dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.projectdropdown, webDriver, "Click on project dropdown");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.wfsearchbox, "JMS_Automation",webDriver,"Input Project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.selectmonitoringvalue, webDriver, "Select the project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.flowDropdown, webDriver, "Click on Flowservice filter");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.wfsearchbox, "JMSUMConsumer_Queue",webDriver,"Input Flow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.selectmonitoringvalue, webDriver, "Select the Flow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.applybutton, webDriver, "Click on Apply button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Flowexecutionlink, webDriver, "Click on Flow execution");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.Transactioncount_Value, webDriver, "1",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.ProjectsPage, webDriver, "Click on Projects page");
	}

	@Test(priority = 9,groups = {"JMS - Universal Messaging  service"}, testName = "Deleting Workflow and Susbcriber of JMS Connector",description="Deleting the Subscriber")
	public void deleteSubscriber() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.loadingindicator, webDriver, "Wait for loader to disappear"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.projectSearchTextBox,PropertiesData.readInputData("projectNameJMS"),webDriver,"Search for the Project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMSprojectName,webDriver, "Clicking on Project name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.miniloader, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.workflowTab,webDriver, "Clicking on Workflow tab");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.projectSearchTextBox,"JMS_UMPublisherWorkflow",webDriver,"Input Workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.moreoptions,webDriver, "Clicking on elipsis icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deleteaction,webDriver, "Clicking on First delete  button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deletebutton,webDriver, "Clicking on second delete  button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.closeicon,webDriver, "Clicking on Close icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.EventsTab,webDriver, "Clicking on Events Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.MessagingToggleButton, webDriver, "Click on Messaging Toggle button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.newsubelement, webDriver, "Wait");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deleteicon,webDriver, "Clicking on Delete icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deletebutton,webDriver, "Clicking on Delete button");
	}

	@Test(priority = 10,groups = {"JMS - Universal Messaging  service"}, testName = "Creating Workflow using JMS Connector",description="Deleting the Account")
	public void deleteAccount() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.connectorsTab,webDriver, "Click on the connectors tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.RESTconnectorsTab,webDriver, "Click on the REST connectors tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.PredefinedconnectorsTab,webDriver, "Click on the PredefinedconnectorsTab connectors tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMS_Title, webDriver,"Wait for JMS Connector in Connectors page");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.JMSConnector,webDriver,"Mousehover on JMS Connector");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.searchboxInput,"JMS", webDriver, "Searching for JMS connector");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.expand_connector,webDriver, "Expanding the JMS Connector");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.jms_UMAcc, webDriver,"Mousehover on JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.DeleteAccount,webDriver, "Deleting the JMS Account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ExternalJMSTests.ExternalJMSUMLocators.deletebutton,webDriver, "Clicking on Delete button");
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