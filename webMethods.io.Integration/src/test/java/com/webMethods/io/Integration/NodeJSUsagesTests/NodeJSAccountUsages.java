package com.webMethods.io.Integration.NodeJSUsagesTests;

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

public class NodeJSAccountUsages 
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

	@Test(priority = 0,groups = {"NodeJSAccountUsages"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"NodeJSAccountUsages"},description = "Verify Pubnub connector account name used in workflow on connectors page")
	public void connectorsPageWorkflow()
	{
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.searchProjectTextBox,"BACKLOG_TEST_DATA", webDriver, "Enter BACKLOG_TEST_DATA project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.backlogTestDataProjectName, webDriver, "Click on BACKLOG_TEST_DATA project card");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewWorkflowButton, webDriver, "Wait for workflow dashboard to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectorsLinkText, webDriver,"Click on connectors linktext to visit Predefined connectors in project");
		SeleniumWaitUtils.waitForElementsVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.allConnectorsIcon, webDriver, "Wait for connectors page to load completely");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.hideAvailableConnectorPanelButton, webDriver,"Hide available connector panel on connectors page");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectorsPageDownArrowService, webDriver,"Click on down arrow to expand Pubnub service used inside project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubNubAccountNameConfigured, webDriver, "Wait for account used in service list to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.workflowNameUsingPubnubAccount, webDriver, "Verify workflow name using Pubnub account on connectors page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.operationNameUsingPubnubAccount, webDriver, "Verify operation name using Pubnub account on connectors page");
	}

	@Test(priority = 2,dependsOnMethods = {"connectorsPageWorkflow"},groups = {"NodeJSAccountUsages"},description = "Verify Pubnub connector account name used in flowservices on connectors page")
	public void connectorsPageFlowServices()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.flowServiceNameUsingPubnubAccount, webDriver, "Verify flowservices name using Pubnub account on connectors page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.operationflowServiceNamePubnubAccount, webDriver, "Verify operation name in flowservices using Pubnub account on connectors page");
	}

	@Test(priority = 3,dependsOnMethods = {"connectorsPageFlowServices"},groups = {"NodeJSAccountUsages"},description = "Verify workflow usages on connectors page")
	public void connectorsPageWorkflowUsages()
	{
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubNubAccountNameConfigured, webDriver, "Mousehover on Pubnub_1 account name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.deleteIconPubnubAccount, webDriver, "Wait for delete icon to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.deleteIconPubnubAccount, webDriver,"Click on delete icon to delete Pubnub_1 account");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.usagesModal, webDriver, "Wait for usages modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.workflowUsedPubnub, webDriver, "Verify workflow name is shown used in Pubnub_1 in workflow");
	}

	@Test(priority = 4,dependsOnMethods = {"connectorsPageWorkflowUsages"},groups = {"NodeJSAccountUsages"},description = "Verify flowservices usages on connectors page")
	public void connectorsPageFlowservicesUsages()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.flowservicesUsedPubnub, webDriver, "Verify flowservices name is shown used in Pubnub_1 in flowservices");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.cancelbuttonUsagesModal, webDriver,"Close usages modal");		
	}

	@Test(priority = 5, dependsOnMethods = {"connectorsPageFlowservicesUsages"},groups = {"NodeJSAccountUsages"},description = "Verify user cannot add new acount for used NodeJS connector with same name or label on connectors page")
	public void nodejsConnectorName()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.expandAvailableConnectorPanel, webDriver,"Expand available connectors panel");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.searchIcon, webDriver,"Click on search icon");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectorPanelSearchBox, webDriver, "Wait for searchbox to be visible");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectorPanelSearchBox,"Pubnub",webDriver,"Enter Pubnub service in panel to search");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.searchedConnectorService, webDriver, "Wait for pubnub to be visible after search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.searchedConnectorService, webDriver,"Click on searched Pubnub service to add new connection");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewConnectionModal, webDriver, "Wait for add new connection modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionTextBoxName, webDriver,"Click or focus on text box to rename connection name");		
		SeleniumKeyBoardActionUtils.keyboardBackSpaceActions(webDriver, Keys.BACK_SPACE, "Clear Pubnub_2 name to add new");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionTextBoxName,"PubNub_1",webDriver,"Enter PubNub_1 name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubnubNameExistsMessage, webDriver, "Wait for same name message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubnubNameExistsMessage, webDriver, "Verify same account name is not allowed to rename Pubnub service account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.closeAccountNameModal, webDriver,"Close name modal");		
	}

	@Test(priority = 6,dependsOnMethods = {"nodejsConnectorName"},groups = {"NodeJSAccountUsages"},description="Verify workflow and flowservices used in Pubnub_1 account in service")
	public void configurationsFlowServicesAndWorkflowName()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.configurationsLink, webDriver,"Close on configurations link");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.nocertificatesMessage, webDriver, "Wait for configurations link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.workflowOptionsConfigPage, webDriver,"Click on workflow option on configuration page");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionsLink, webDriver, "Wait for Connections link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionsLink, webDriver,"Click connections link");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.accountsConfiguredText, webDriver, "Wait connectors page to be displayed");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.accountsConfiguredText, webDriver, "Verify workflow and flow services using Pubnub_1 account in pubnub service");
	}

	@Test(priority = 7,dependsOnMethods = {"configurationsFlowServicesAndWorkflowName"},groups = {"NodeJSAccountUsages"},description = "Verify workflow usages on configurations page")
	public void configPageWorkflowUsages()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.downArrowButtonConfig, webDriver,"Click arrow down to expand pubnub accounts");		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addConnectionButton, webDriver, "Wait for all connectors added in PubNub services");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.select2DropdownButton, webDriver, "Click on select 2 drop down");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubNubConnectorDeleteIcon, webDriver, "Wait for all delete button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubNubConnectorDeleteIcon, webDriver, "Click on delete icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.usagesModal, webDriver, "Wait for usages modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.workflowUsedPubnub, webDriver, "Verify workflow usages name is shown used in Pubnub_1 in workflow on configurations page");
	}

	@Test(priority = 8,dependsOnMethods = {"configPageWorkflowUsages"},groups = {"NodeJSAccountUsages"},description = "Verify flowservices usages on configurations page")
	public void configPageFlowservicesUsages()
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.flowservicesUsedPubnub, webDriver, "Verify flowservices usages name is shown used in Pubnub_1 in flowservices on configurations page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.cancelbuttonUsagesModal, webDriver, "Close usages modal on configurations page");
	}

	@Test(priority = 9, dependsOnMethods = {"configPageFlowservicesUsages"},groups = {"NodeJSAccountUsages"},description = "Verify user cannot add new acount for used NodeJS connector with same name or label on configurations page")
	public void nodejsConnectorNameConfigPage()
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addConnectionButton, webDriver, "Wait for add new button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addConnectionButton, webDriver, "Close on add new button on configurations page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewConnectionModalConfigPage, webDriver, "Wait for new auth/connection modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionTextBoxName, webDriver,"Click or focus on text box to rename connection name");		
		SeleniumKeyBoardActionUtils.keyboardBackSpaceActions(webDriver, Keys.BACK_SPACE, "Clear Pubnub_2 name to add new");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionTextBoxName,"PubNub_1",webDriver,"Enter PubNub_1 name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubnubNameExistsMessage, webDriver, "Wait for same name message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubnubNameExistsMessage, webDriver, "Verify same account name is not allowed to rename Pubnub service account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.closeAccountNameModal, webDriver,"Close name modal");		
	}

	@Test(priority = 10, dependsOnMethods = {"nodejsConnectorNameConfigPage"},groups = {"NodeJSAccountUsages"},description = "Verify project publish with workflow and flowservices having NodeJS CLI connectors")
	public void publishProjectNodeJSUsages()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectsLink, webDriver, "Close on project link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectSearchTextBox,"BACKLOG_TEST_DATA",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectPublishProject,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.deploymentNameTextBox,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.deploymentNameTextBox,"NodeJSUsages",webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectPublishMessage,webDriver,"Wait for publish messsage"); 
	}

	@Test(priority = 11, dependsOnMethods = {"publishProjectNodeJSUsages"},groups = {"NodeJSAccountUsages"},description = "Verify project deploy with workflow and flowservices having NodeJS CLI connectors")
	public void deployProjectNodeJSUsages()
	{
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.emailTextBox,MavenArgumentConstants.DESTINATION_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.password,MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.loginButton, webDriver,"Click on login button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectSearchTextBox,"BACKLOG_TEST_DATA",webDriver,"Input published project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectDeployedName,webDriver,"Assert project deployed is shown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectDeployedName,webDriver,"Click on Deployed project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.saveAndContinueButton,webDriver,"Click on Save and continue button to create project in IC");
	}

	@Test(priority = 12,dependsOnMethods = {"deployProjectNodeJSUsages"},groups = {"NodeJSAccountUsages"},description = "Verify system should not allow to rename duplicate account name for NodeJS connector")
	public void nodeJSAccountNameProjectDeploy() throws InterruptedException
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addAccountScreenDeployment,webDriver,"Wait for add new connection dropdown to be visible");
		Thread.sleep(4000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewConnectionIcon,webDriver,"Click add button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewConnectionDeploymentModal,webDriver,"Wait for add new connection modal to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.publishKeyTextBox,"demo",webDriver,"Input publish key");       
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.subscriberKeyTextBox,"demo",webDriver,"Input subscribe key");       
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewButtonConnection,webDriver,"Click add button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.accountSavedMessage,webDriver,"Wait for add new connection message to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewConnectionIcon,webDriver,"Click add new button to add new connection");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.addNewConnectionDeploymentModal,webDriver,"Wait for add new connection modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionTextBoxName, webDriver,"Click or focus on text box to rename connection name");		
		SeleniumKeyBoardActionUtils.keyboardBackSpaceActions(webDriver, Keys.BACK_SPACE, "Clear Pubnub_2 name to add new");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.connectionTextBoxName,"PubNub_1",webDriver,"Input duplicate connection name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubnubNameExistsMessage, webDriver, "Wait for same name message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.pubnubNameExistsMessage, webDriver, "Verify same account name is not allowed to rename Pubnub service account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.closeEditAccountModal, webDriver,"Close name modal");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.deployButton,webDriver,"Click on deploy button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.deployMessage, webDriver, "Wait for deployment message to be visible");
	}

	@Test(priority = 13,dependsOnMethods = {"nodeJSAccountNameProjectDeploy"},groups = {"NodeJSAccountUsages"},description = "Verify project is deployed in desitnation tenant")
	public void deploymentAssets()
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.projectSearchTextBox,"BACKLOG_TEST_DATA",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.backlogTestDataProjectName, webDriver, "Wait for deployed project to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.NodeJSUsagesTests.NodeJSAccountUsagesLocators.backlogTestDataProjectName,webDriver,"Verify project is deployed");
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