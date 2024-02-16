package com.webMethods.io.Integration.MessagingServiceTests;

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

public class MessagingService 
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

	@Test(priority = 0,groups = {"MessagingServices"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"MessagingServices"},description="Verifing messaging service promotion publish")
	public void PublishAssetTest() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName2"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.elopsis, webDriver, "Click on elopsis to publish project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Publish, webDriver, "Click on publish project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"Click on Next button");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.dependencyModalString,webDriver,"Assert all Dependency are visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.SubQueueWF,webDriver,"Assert Subscriber queue workflow Dependency are visible");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		Thread.sleep(1000);
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.defaultTopicWorkflowReceiver1,webDriver,"Assert defaultTopicWorkflowReceiver Dependency are visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.TopicWF,webDriver,"Assert Topic workflow Dependency are visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.defaultQueueWorkflowReceiver1,webDriver,"Assert defaultQueueWorkflowReceiver Dependency are visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.QueueWF,webDriver,"Assert queue walkflow Dependency are visible");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"Click on Next button");	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.deploymentNameTextBox,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.deploymentNameTextBox,PropertiesData.readInputData("deploymentName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,PropertiesData.readInputData("destinationName"),"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
	}
	@Test(priority = 2,dependsOnMethods = {"PublishAssetTest"},groups = {"MessagingServices"},description="Verifing messaging service promotion deployment")
	public void deploytodestinationTest()
	{
		webDriver.navigate().to(PropertiesData.readInputData("loginDestinationTenantURL"));
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.ssoLoginLink,webDriver,"Click on SSO login link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox,PropertiesData.readInputData("ProjectName2"),webDriver,"Input published project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectDeployedName,webDriver,"Assert project deployed is shown");
	}

	@Test(priority = 3,dependsOnMethods = {"deploytodestinationTest"},groups = {"MessagingServices"},description="Verifing messaging service promotion deployed assets")
	public void DeployAssetTest() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectDeployedName,webDriver,"Click on Deployed project name");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectDeployedvisible,webDriver,"Verify Notification message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Saveandcontnu,webDriver,"Click on saveand continue deploy project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.loader, webDriver, "wait for element not to visbile");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Messagingconnector,webDriver,"Wait for Messaging connector to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Messagingconnector,webDriver,"verify and select connector account");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.skipAssets,webDriver,"verify account visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"verify account selected");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectaccount,webDriver,"verify account visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectaccount,webDriver,"verify account selected");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.ConfigureTrigger,webDriver,"verify Configure Trigger visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.ConfigureParameter,webDriver,"verify Configure Trigger visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Deploy,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Yes,webDriver,"Click on yes button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectDeployementMessage,webDriver,"Wait for project deployment message");
	}

	@Test(priority = 4,dependsOnMethods = {"DeployAssetTest"},groups = {"MessagingServices"},description="Verifing messaging service promotion deployed workflow execution")
	public void ValidateexecutionAssetTest() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.loader,webDriver,"Wait for Circle Loader to be invisible");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName2"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Search workflow
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,"MessagingPublisherWorkflow",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.MessagingPublisherWorkflow,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editWorkflow1,webDriver,"Editing Workflow ");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.activitiesPanel, webDriver, "Wait for activities panel on canvas");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowPlayButton,webDriver,"Click on play button to execute workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowExecutionStartMessage,PropertiesData.readInputData("workflowExecutionStartMessage"), webDriver,"Assert workflow execution start message",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowExecutionCompletedMessage,PropertiesData.readInputData("workflowExecutionCompleteMessage"), webDriver,"Assert workflow execution complete message",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.leaveCanvas,webDriver,"Leave canvas");
		Thread.sleep(1000);
	}

	//Add 
	/*@Test(priority = 4,dependsOnMethods = {"ValidateexecutionAssetTest"},groups = {"MessagingServices"},description="Verifing messaging service promotion deployed assets")
	public void ValidateAssetTest5() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
        SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Messaging,webDriver,"Click on play button to execute workflow");
      // SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Messaging,webDriver,"Click on play button to execute workflow");
    }

	@Test(priority = 5,dependsOnMethods = {"ValidateAssetTest5"},groups = {"MessagingServices"},description="Verifing messaging service promotion deployed workflow execution")
	public void ValidateFlowserviceexecutionAssetTest() 
	{

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,PropertiesFileUtilitiesHelperFunctions.getPropertiesData("ProjectName2",CommonAutomationData.TEST_INPUT_DATA_FILE_PATH),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Search flowservice
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.FlowServices, webDriver, "Click on flowservice tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.addFlowServiceIcon,webDriver,"Wait for Flowservice dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on Flowservice search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,"MessagingPublisherFlow",webDriver,"Input Flowservice name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow");
		SeleniumWebElementsUtils.mousehover(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.MessagingPublisherFlow,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.MessagingPublisherFlow,webDriver,"Editing Flowservice ");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.MessagingPublisherFlow, webDriver, "Wait for activities panel on canvas");
        SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.FlowServicesplaybuttom,webDriver,"Click on play button to execute flowservice");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Flowservicesussessmessage,webDriver,"Wait for flowservice success message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Leaveflow,webDriver,"Leave flow canvas");
		Thread.sleep(1000);
	}
	 */

	@Test(priority = 5,groups = {"MessagingServices"},description="reloginToSourceTenant")
	public void reloginToSourceTenant() 
	{
		webDriver.navigate().to(PropertiesData.readInputData("loginSourceTenantURL"));
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName2"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.myUIautomationproject1, webDriver, "Click on searched project");

		//Update subscriber 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.messagingTab, webDriver, "Click on Messaging Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.subscribersTab, webDriver, "Click on Subscribers Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Subscriber_QueueFlow, webDriver, "Click on Subscriber Queue Workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.disableButton, webDriver, "Click on Disable button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.confirmButton, webDriver, "Click on Confirm button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.disabledStatusButton, webDriver,"Verify disabled status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.subscribersTab, webDriver, "Click on Subscribers Tab");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Subscriber_TopicWorkflow, webDriver,"Wait for subscribers listing");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Subscriber_TopicWorkflow, webDriver, "Click on Subscriber Topic Workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.suspendButton, webDriver, "Click on Suspend button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.confirmButton, webDriver, "Click on Confirm button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.suspendedStatusButton,webDriver,"Wait for suspended status");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editSubscriberLink, webDriver, "Click on edit Subscriber link at the bottom of page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.destinationtypevaluetopic, webDriver, "Clicking on the Topic selected as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.destinationtypevaluequeue, webDriver, "Clicking on the Queue as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectdestinationdropdown,webDriver, "Selecting destination asset dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectqueueDestinationName,webDriver, "Selecting the queue for flow as destination asset");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.destinationtypevaluequeue, webDriver, "Clicking on the Topic as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowInvocationType, webDriver, "Clicking on the Workflow as Invocation Type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.flowServiceInvocationType, webDriver, "Clicking on the FlowService as Invocation Type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.flowServiceInvocationTypeNOTSET, webDriver, "Clicking on the Invocation name space NOT SET");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.addNewConsumerService, webDriver, "Clicking on the add new Flowservice button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Flownameinsubscriber,"MessagingPublisherFlow",webDriver,"Input Flowservice name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.saveconsumerflowbutton, webDriver, "Clicking on the save button to save the subscriber");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.duplicateFlowError, webDriver, "Validate the duplicate error");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.cancelConsumerflowbutton, webDriver, "Click on Cancel button");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.addNewConsumerService, webDriver, "addNewConsumerService");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Flownameinsubscriber,"newConsumerFlowService",webDriver,"Input Flowservice name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.saveconsumerflowbutton, webDriver, "save");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.infoIcon, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.savebutton, webDriver, "Clicking on the Save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.yesButton, webDriver, "Clicking on the Yes button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectqueueDestinationName, webDriver,"Verify user logged in");
	} 

	@Test(priority = 6,groups = {"MessagingServices"},description="DisabledSubscriberQueueError")
	public void DisabledSubscriberQueueError() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.subscribersTab, webDriver, "Click on Subscribers Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Subscriber_QueueFlow, webDriver, "Click on Subscriber name for Subscriber_QueueFlow ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.enableButton, webDriver, "Click on Enable button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.QueueError, webDriver, "Error validation for queue");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editSubscriberButton, webDriver, "Click on Edit subscriber button");
	}

	@Test(priority = 7,groups = {"MessagingServices"},description="EditDisabledSubscriberQueue")
	public void EditDisabledSubscriberQueue() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.destinationtypevaluequeue, webDriver, "Clicking on the Queue selected as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.destinationtypevaluetopic, webDriver, "Clicking on the Topic as destination value");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectdestinationdropdown,webDriver, "Selecting destination name dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.selectTopicDestinationName,webDriver, "Selecting the destination name of Topic");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.flowServiceInvocationType, webDriver, "select Default Flow invocation Type");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowInvocationType, webDriver, "select invocation Type as Workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.addNewConsumerService,webDriver, "Clicking on addnew workflow button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Workflownameinsubscriber, "new Consumer Workflow", webDriver, "Providing a name to the Consumer workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.saveconsumerflowbutton, webDriver, "Clicking on save workflow button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editRule, webDriver, "Click on edit rule button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editRuleFilter,webDriver,"Click on the filter rule edit");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, "a", "Select all");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.DELETE, "Delete");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editRuleFilter,"%body/string% == 'testRule'", webDriver, "Updating the Filter Rule");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.flowServiceRuleInvocationType, webDriver, "Click on invocation Type as FlowService");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowRuleInvocationType, webDriver, "Click on invocation Type as Workflow");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.pleaseSelectSpace, "new Consumer Workflow", webDriver, "Searching for the existing workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to select workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.saveconsumerflowbutton, webDriver, "Clicking on the save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.infoIcon, webDriver, "Clicking on information Icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.savebutton, webDriver, "Clicking on the Topic as destination value");
	}

	@Test(priority = 8,groups = {"MessagingServices"},description="Triggercheck")
	public void Triggercheck() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.IntegrationsTab,webDriver, "Clicking on Integrations Tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.workflowTab,webDriver, "Clicking on Workflow tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewWorkflowButton, webDriver, "Wait");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,"defaultTopicWorkflowReceiver" ,webDriver, "Searching for the workflow topic");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search workflow"); 
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.defaultTopicWorkflowReceiver,webDriver, "Clicking on Workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.editWorkflow1,webDriver, "Editing  Workflow ");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.activitiesPanel, webDriver, "Wait");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.StartActivity, webDriver, "Validating if the trigger is detatched from the workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.leaveCanvas,webDriver,"Leave canvas");
	}

	@Test(priority = 9,dependsOnMethods = {"Triggercheck"},groups = {"MessagingServices"},description="Verifing messaging service promotion publish")
	public void RepublishAssestTest() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName2"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.elopsis, webDriver, "Click on elopsis to publish project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.Publish, webDriver, "Click on publish project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"Click on Next button");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.dependencyModalString,webDriver,"Assert all Dependency are visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.SubQueueWF,webDriver,"Assert Subscriber queue workflow Dependency are visible");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		Thread.sleep(1000);
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.defaultTopicWorkflowReceiver1,webDriver,"Assert defaultTopicWorkflowReceiver Dependency are visible");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.TopicWF,webDriver,"Assert Topic workflow Dependency are visible");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.defaultQueueWorkflowReceiver1,webDriver,"Assert defaultQueueWorkflowReceiver Dependency are visible");
		//		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.QueueWF,webDriver,"Assert queue walkflow Dependency are visible");
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.nextButton,webDriver,"Click on Next button");	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.deploymentNameTextBox,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.deploymentNameTextBox,PropertiesData.readInputData("deploymentName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,PropertiesData.readInputData("destinationName"),"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectPublishMessage, PropertiesData.readInputData("projectPublishMessage"), webDriver, "desc", softAssert);
	}

	@Test(priority = 2,dependsOnMethods = {"RepublishAssestTest"},groups = {"MessagingServices"},description="Verifing messaging service promotion deployment")
	public void RedeployAssets() 
	{
		webDriver.navigate().to(PropertiesData.readInputData("loginDestinationTenantURL"));
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.loader,webDriver,"Wait for Circle Loader to be invisible");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName2"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingServiceTests.MessagingServiceLocators.myUIautomationproject1, webDriver, "Click on searched project");
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