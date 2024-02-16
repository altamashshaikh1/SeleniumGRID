package com.webMethods.io.Integration.RunTimeTests;

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
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
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

public class RunTimeTests 
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

	@Test(priority = 0,groups = {"Messaging BackLog Tests Run Time"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.RunTimeTests.RunTimeLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Messaging BackLog Tests Run Time"},description = "Verify queue status for disabled subscriber")
	public void verifyQueueDisabledSubscriber() throws InterruptedException  
	{
		/**
		    Disable the subscriber and notice that the messages are going to pending state. 
		 */

		//Execute workflow via webhook with below payload		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.projectSearchBox,"WF_MESSAGING_BACKLOG", webDriver,"Enter WF_MESSAGING_BACKLOG project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.createdWorkflow, webDriver, "Wait recipes import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.messagingLink, webDriver, "Click messaging Link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.queueOneLink, webDriver, "Wait all created queue to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.subsriberLink, webDriver, "Click subsriber Link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueueLink, webDriver, "Wait for runnable QueueLink to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueueLink, webDriver, "Click runnable QueueLink");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.disableSubscriberOption, webDriver, "Click disable option to disable subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.disableConfirmModal, webDriver, "Wait for confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.confirmButton, webDriver, "Click confirm button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.subscriberDisableMessage, webDriver, "Wait for subscriber Disable Message to be visible");

		//Execute workflow via webhook to publish message in queue
		String postJsonData = "{\"assetName\":\"WF\"}";
		HttpURLConnHelperFuntions.HTTP_POST_REQUEST(PropertiesData.readInputData("publisherWorkflow"), postJsonData, "Execute workflow via webhook url to check queue status for disabled subscriber");
		Thread.sleep(3000);

		//Visit queues
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.queuesLink, webDriver, "Click queues link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueue, webDriver, "Wait all queues to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueue, webDriver, "Click runnable queue link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.queueMetricsTable, webDriver, "Wait queue option to be visible");

		//Fetch pending message value after making subscriber disabled and publishing message to disabled subscriber (Before publishing message)
		int pendingMessage = Integer.parseInt(SeleniumWebElementsUtils.fetchElementText(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.pendingMessageLocator, webDriver, "Fetching pending message value before publishing message."));
		ExtentTestManager.getTest().info("Pending value in RUNNABLE_QUEUE after disabling subscriber and publishing message in queue: "+pendingMessage);

		if(pendingMessage<=0)
		{
			ExtentTestManager.getTest().fail("Pending value is not getting reflected in RUNNABLE_QUEUE after publishing message for disabled subscriber. "+pendingMessage);
			Assert.fail("Pending value is not getting reflected in RUNNABLE_QUEUE after publishing message for disabled subscriber. "+pendingMessage);;
		}
		else
		{
			ExtentTestManager.getTest().pass("Pending value is getting reflected in RUNNABLE_QUEUE after publishing message for disabled subscriber. "+pendingMessage);
		}
	}

	@Test(priority = 2,dependsOnMethods = {"verifyQueueDisabledSubscriber"},groups = {"Messaging BackLog Tests Run Time"},description = "Verify queue status for enabled subscriber")
	public void verifyQueueEnabledSubscriber() 
	{
		/**
		   Verify pending to success
		 */
		//Fetch published message value, consumed message and pending message values (Before revisiting and making subscriber enabled)
		int publishedMessageBeforeRevisit = Integer.parseInt(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.publishMessageLocator.getText());
		int consumedMessageBeforeRevisit  = Integer.parseInt(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.consumedMessageLocator.getText());
		int pendingMessageBeforeRevisit   = Integer.parseInt(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.pendingMessageLocator.getText());
		ExtentTestManager.getTest().info("Published message value in RUNNABLE_QUEUE before revisting : "+publishedMessageBeforeRevisit);
		ExtentTestManager.getTest().info("Consumed message value in RUNNABLE_QUEUE before revisting : "+consumedMessageBeforeRevisit);
		ExtentTestManager.getTest().info("Pending message value in RUNNABLE_QUEUE before revisting : "+pendingMessageBeforeRevisit);

		//Revisiting Queues listing after enabling subscriber
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.subsriberLink, webDriver, "Click subsriber Link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueueLink, webDriver, "Wait for runnable QueueLink to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableSubscriberEllipsisOption, webDriver, "Click RUNNABLE_SUBS ellipsis option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableSubscriberEnableOption, webDriver, "Click on enable option to make RUNNABLE_SUBS subscriber enabled");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.subscriberEnableMessage, webDriver, "Wait for subscriber enable message to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.queuesLink, webDriver, "Click queues link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueue, webDriver, "Wait all queues to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.runnableQueue, webDriver, "Click runnable queue link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.queueMetricsTable, webDriver, "Wait queue option to be visible");

		//Fetch published message value, consumed message and pending message values (After revisiting and making subscriber enabled)
		int publishedMessageAfterRevisit = Integer.parseInt(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.publishMessageLocator.getText());
		int consumedMessageAfterRevisit  = Integer.parseInt(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.consumedMessageLocator.getText());
		int pendingMessageAfterRevisit   = Integer.parseInt(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.pendingMessageLocator.getText());

		if(publishedMessageAfterRevisit<0||publishedMessageAfterRevisit==0||
				consumedMessageAfterRevisit<0||consumedMessageAfterRevisit==0||
				pendingMessageAfterRevisit==pendingMessageBeforeRevisit||pendingMessageAfterRevisit<0)
		{
			ExtentTestManager.getTest().fail("Published message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+publishedMessageAfterRevisit);
			Assert.fail("Published message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+publishedMessageAfterRevisit);
			ExtentTestManager.getTest().fail("Consumed message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+consumedMessageAfterRevisit);
			Assert.fail("Consumed message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+consumedMessageAfterRevisit);
			ExtentTestManager.getTest().fail("Pending message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+pendingMessageAfterRevisit);
			Assert.fail("Pending message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+pendingMessageAfterRevisit);
		}
		else
		{
			ExtentTestManager.getTest().pass("Published message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+publishedMessageAfterRevisit);
			ExtentTestManager.getTest().pass("Consumed message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+consumedMessageAfterRevisit);
			ExtentTestManager.getTest().pass("Pending message value in RUNNABLE_QUEUE after enabling subscriber and revisiting the queues : "+pendingMessageAfterRevisit);
		}
	}	

	@Test(priority = 3,dependsOnMethods = {"verifyQueueEnabledSubscriber"},groups = {"Messaging BackLog Tests Run Time"},description = "Verify default invocation is executed when routing rule validations are not true or success ")
	public void verifyDefaultInvocation()  
	{
		//Visit workflow dashboard and save run count for DEFAULT_INVOCATION_WF workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.integrationsLink, webDriver, "Click integrations link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.addWorkflowButton, webDriver, "Wait for workflow dashboard to be visible");
		String executionCountBeforePublishing = com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.executionCount.getText();
		String partsBeforePublish[] = executionCountBeforePublishing.split("times run");
		int countBeforePublish = Integer.parseInt(partsBeforePublish[0]);
		ExtentTestManager.getTest().info("DEFAULT_INVOCATION_WF execution count before publishing message to Default invocation."+countBeforePublish);

		//Execute workflow via webhook with invalid routing rule filter value so that default invocation workflow should execute
		String postJsonData = "{\"assetName\":\"DEF\"}";
		HttpURLConnHelperFuntions.HTTP_POST_REQUEST(PropertiesData.readInputData("publisherWorkflow"), postJsonData, "Execute workflow via webhook url to check queue status for disabled subscriber");

		//Refresh page
		webDriver.navigate().refresh();
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.addWorkflowButton, webDriver, "Wait for workflow dashboard to be visible");

		//Verify run count for DEFAULT_INVOCATION_WF workflow after publishing message to invoke DEFAULT_INVOCATION OR Check runtime value for DEFAULT_INVOCATION_WF workflow
		String executionCountAfterPublish = com.webMethods.io.Integration.RunTimeTests.RunTimeLocators.executionCount.getText();
		String partsAfterPublish[] = executionCountAfterPublish.split("times run");
		int countAfterPublish = Integer.parseInt(partsAfterPublish[0]);

		if(countAfterPublish<=countBeforePublish)
		{
			ExtentTestManager.getTest().fail("Default invocation is not executed if routing rule validations are not success.");
			ExtentTestManager.getTest().info("DEFAULT_INVOCATION_WF invocation count is : "+countAfterPublish);
		}
		else
		{
			ExtentTestManager.getTest().pass("Default invocation is executed if routing rule validations are not success. "+countAfterPublish);
		}	
	}

	//	/*@Test(priority = 4,dependsOnMethods = {"defaultInvocation"},groups = {"Messaging BackLog Tests Run Time"},description = "Verify update routing rule and execute (New Expression)")
	//	public void updateRuleAndExecuteNewExpression() 
	//	{
	//		/**
	//			    For an current working routing rule update the routing rule with new expression and save the subscriber. 
	//				Validate that the Flow or workflow associated with default invocation is executed
	//		 */
	//	}
	//
	//	@Test(priority = 5,dependsOnMethods = {"updateRuleAndExecuteNewExpression"},groups = {"Messaging BackLog Tests Run Time"},description = "Verify update routing rule and execute (New Values)")
	//	public void updateRuleAndExecuteNewValues() 
	//	{
	//		/**
	//			    For an current working routing rule update the routing rule with new values and save the subscriber. 
	//				Validate that the Flow or workflow associated with default invocation is executed
	//		 */
	//	}
	//
	//	@Test(priority=6,dependsOnMethods = {"updateRuleAndExecuteNewValues"},groups = {"Messaging BackLog Tests Run Time"},description = "Verify replace invocation assets and execute")
	//	public void replaceInvocationAndExecute()
	//	{
	//		/**
	//			    Publish messages continuously for the invocation of a workflow associated with routing rule. 
	//				Update the routing rule with Flowservice as invocation and validate the invocation of flowservice
	//		 */
	//	}*/

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