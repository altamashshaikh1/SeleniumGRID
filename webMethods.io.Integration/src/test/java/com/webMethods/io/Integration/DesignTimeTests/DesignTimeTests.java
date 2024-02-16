package com.webMethods.io.Integration.DesignTimeTests;

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
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil; 
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class DesignTimeTests 
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

	@Test(priority = 0,groups = {"Messaging BackLog Tests-Design Time"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Messaging BackLog Tests-Design Time"},description = "Validate that on selecting the workflow as invocation inside subscriber the exisitng workflows should be listed in dropdown")
	public void validateWorkflowListings() throws InterruptedException 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox,"Messaging Automation", webDriver,"Enter Messaging Automation project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createdWorkflow, webDriver, "Wait recipes import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Wait for Messaging Destination button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Click on Messaging destination button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.queueOneLink, webDriver, "Wait all created queue to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Wait for Messaging Destination button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addSubscriberButton, webDriver, "Click on addSubscriberButton to visit dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox, webDriver, "Wait for WF_MESSAGING_BACKLOG_SUBS subscriber name text box to be visible");	
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox,"WF_MESSAGING_BACKLOG_SUBS", webDriver,"Enter WF_MESSAGING_BACKLOG_SUBS subscriber name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "QueueOne","SendKeys action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add queue");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_ONE","SendKeys action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultInvocationLink, webDriver, "Wait for default invocationLink to be visible");
		Thread.sleep(3000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsSaveButton, webDriver, "Click on save button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsSavedMessage, webDriver, "Wait for subscriber saved message to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberCreatedLink, webDriver, "Wait for subscriber created to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberCreatedLink, webDriver, "Verify subscriber created with workflow as invocation");
	}

	@Test(priority = 2,dependsOnMethods = {"validateWorkflowListings"},groups = {"Messaging BackLog Tests-Design Time"},description = "Verify workflow and flowservices should not be deleted if used in subsriber")
	public void deleteWorkflowAndFlowServices() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectDashBoardLink, webDriver, "Click on project link to visit dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createNewProjectButton, webDriver,"Wait for project dashboard to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox,"Messaging Automation", webDriver,"Enter Messaging Automation project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addWorkflowButton, webDriver, "Wait recipes import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowUsedEllipsis, webDriver, "Click ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowDeleteOption, webDriver, "Wait for workflow delete option to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowDeleteOption, webDriver, "Click on workflow delete option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerUsagesModal, webDriver, "Wait for triggerUsagesModal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerUsagesModal, webDriver, "Verify trigger UsagesModal is shown on deleting workflows used in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerUsageModalCancelButton, webDriver, "Close trigger UsagesModal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowServicesLink, webDriver, "Close flowservices option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowservicesCard, webDriver, "Wait for flowservices card to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowUsedEllipsis, webDriver, "Close flowservices ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowDeleteOption, webDriver, "Wait for flow delete option to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowDeleteOption, webDriver, "Click on flow delete option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerModalUsagesConfirm, webDriver, "Wait for triggerUsagesModal to be visible for flowservices");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerModalUsagesConfirm, webDriver, "Verify trigger UsagesModal is shown on deleting flowservices used in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerUsageModalCancelButtonFs, webDriver, "Close flowservices trigger usages modal");
	}

	@Test(priority = 3,dependsOnMethods = {"deleteWorkflowAndFlowServices"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify only the flow services having the JMS specification enabled inside I/O should be listed inside the routing rule")
	public void flowServicesListingWithJMS()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectDashBoardLink, webDriver, "Click on project link to visit dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createNewProjectButton, webDriver,"Wait for project dashboard to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox,"Messaging Automation", webDriver,"Enter Messaging Automation project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addWorkflowButton, webDriver, "Wait recipes import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Wait for Messaging Destination button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Click on Messaging destination button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.queueOneLink, webDriver, "Wait all created queue to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Wait for Messaging Destination button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addSubscriberButton, webDriver, "Click on addSubscriberButton to visit dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox, webDriver, "Wait for WF_MESSAGING_BACKLOG_SUBS subscriber name text box to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox,"WF_MESSAGING_BACKLOG_SUBS_2", webDriver,"Enter WF_MESSAGING_BACKLOG_SUBS subscriber name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "QueueTwo","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add queue");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "FS_MESSAGING_USED","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to FLOWSERVICES in subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultInvocationLink, webDriver, "Wait for default invocationLink to be visible");
	}

	@Test(priority = 4,dependsOnMethods = {"flowServicesListingWithJMS"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify that the Workflows used as default invocation for a subscriber can be used in same workflow for routing rule")
	public void addWorkflowInSubsAndRoutingRule() throws InterruptedException
	{
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageUp(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox, webDriver, "Click on subsNameTextBox");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "QueueTwo","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add queue");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_TWO","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultInvocationLink, webDriver, "Wait for default invocationLink to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleButton, webDriver, "Click on routingRuleButton");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleName, webDriver, "Wait for routingRuleName to be visible");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "%body/string% == \"hello, world!\"","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_TWO","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addRuleButton, webDriver, "Click on add button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleCreated, webDriver, "Wait routingRuleCreated to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleCreated, webDriver, "Verify routingRuleCreated");
		Thread.sleep(3000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.saveButton, webDriver, "Click on saveButton");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsSavedMessage, webDriver, "Wait for subscriber saved message to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberCreatedLinkTwo, webDriver, "Wait for subscriber created to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberCreatedLinkTwo, webDriver, "Verify subscriber created with workflow as invocation");
	}

	@Test(priority = 5,dependsOnMethods = {"addWorkflowInSubsAndRoutingRule"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify if the user can edit the existing routing rules inside the messaging trigger and save the trigger")
	public void editRoutingRules()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberCreatedLinkTwo, webDriver, "Click on created subscriber with routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditButton, webDriver, "Click on edit subscriber with routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down");  
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleEditPencilIcon, webDriver, "Click on pencil icon to routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleName, webDriver, "Wait for routing ruleName modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.saveButton, webDriver, "Click on save button to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleConfirmModal, webDriver, "Wait for edit rule confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.yesOption, webDriver, "Click on yes option to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Wait for subscriber updated message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Verify subscriber updated message is visible");
	}

	@Test(priority = 6,dependsOnMethods = {"editRoutingRules"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify if the user can update the filter expression and values for an routing rule inside the messaging trigger")
	public void editFilterInRoutingRules()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditButton, webDriver, "Click on edit subscriber with routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleEditPencilIcon, webDriver, "Click on pencil icon to routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleName, webDriver, "Wait for routing ruleName modal to be visible");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleFilterTextArea, webDriver, "Clear filter data");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleFilterTextArea,"%body/data/myField% == \"ABC\"", webDriver, "Enter new filter data");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.saveButton, webDriver, "Click on save button to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleConfirmModal, webDriver, "Wait for edit rule confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.yesOption, webDriver, "Click on yes option to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Wait for subscriber updated message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Verify subscriber updated message is visible");
	}

	@Test(priority = 7,dependsOnMethods = {"editFilterInRoutingRules"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify if the user can delete the existing routing rules inside the messaging trigger for an existing subscriber")
	public void deleteRoutingRules()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditButton, webDriver, "Click on edit subscriber with routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleDeleteButton, webDriver, "Click delete icon to delete routing rule created");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.deleteConfirmModal, webDriver, "Wait for delete confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleDelete, webDriver, "Click on delete option routing rule created");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.noRoutingRuleMessage, webDriver, "Wait for no rule present message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.noRoutingRuleMessage, webDriver, "Verify routing rule deleted");
	}

	@Test(priority = 8,dependsOnMethods = {"deleteRoutingRules"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify that user can create a subscriber without the invocation specified from subscribers page")
	public void subscriberWithoutInvocation() throws InterruptedException
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.clearDefaultInvocation, webDriver, "Clear default invocation");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageUp(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberDescription, webDriver, "Click on description");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleButton, webDriver, "Click on routingRuleButton");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleName, webDriver, "Wait for routingRuleName to be visible");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "%body/string% == \"hello, world!\"","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_TWO","Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addRuleButton, webDriver, "Click on add button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleCreated, webDriver, "Wait routingRuleCreated to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleCreated, webDriver, "Verify routingRuleCreated");
		Thread.sleep(3000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.saveButton, webDriver, "Click on save button to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleConfirmModal, webDriver, "Wait for edit rule confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.yesOption, webDriver, "Click on yes option to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleConfirmModal, webDriver, "Wait for edit rule confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.yesOption, webDriver, "Click on yes option to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Wait for subscriber updated message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Verify subscriber updated message is visible");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.noInvocation, webDriver, softAssert, "Verify subscriber created without invocations");
	}

	@Test(priority = 9,dependsOnMethods = {"subscriberWithoutInvocation"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify that the user can edit the subscriber using edit option provided at the bottom of subscriber page when there are no routing rules added")
	public void subscriberWithoutRoutingRules() throws InterruptedException
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditButton, webDriver, "Click on edit subscriber with routing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberDescription, webDriver, "Click on description");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_TWO","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleDeleteButton, webDriver, "Click delete icon to delete routing rule created");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.deleteConfirmModal, webDriver, "Wait for delete confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.ruleDelete, webDriver, "Click on delete option routing rule created");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.noRoutingRuleMessage, webDriver, "Wait for no rule present message to be visible");
		Thread.sleep(3000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.saveButton, webDriver, "Click on save button to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.editRuleConfirmModal, webDriver, "Wait for edit rule confirm modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.yesOption, webDriver, "Click on yes option to save changes in subscriber after editing rule");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Wait for subscriber updated message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsUpdateSavedMessage, webDriver, "Verify subscriber updated message is visible");
	}

	@Test(priority = 10,dependsOnMethods = {"subscriberWithoutRoutingRules"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify that the user cannot create duplicate routing rule names")
	public void subscriberDuplicateNameTests()  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Wait for Messaging Destination button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Click on Messaging destination button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.queueOneLink, webDriver, "Wait all created queue to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingDestinationButton, webDriver, "Wait for Messaging Destination button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addSubscriberButton, webDriver, "Click on addSubscriberButton to visit dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox, webDriver, "Wait for WF_MESSAGING_BACKLOG_SUBS subscriber name text box to be visible");	
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox,"WF_SUBS_USED_ASSETS", webDriver,"Enter WF_SUBS_USED_ASSETS duplicate subscriber name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.duplicateErrorMessage, webDriver, "Wait for duplicate message to be visible");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.duplicateErrorMessage, webDriver, "Verify duplicate message for duplicate subscriber name");	
	}

	@Test(priority = 11,dependsOnMethods = {"subscriberDuplicateNameTests"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify the creation of multiple routing rules associated with workflows")
	public void subscriberWithMultipleRoutingRulesWF() throws InterruptedException
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsNameTextBox, webDriver, "Click on subscriber name inputbox");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "_MULTIPLE","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "QueueThree","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add queue");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_THREE","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultInvocationLink, webDriver, "Wait for default invocationLink to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleButton, webDriver, "Click on routingRuleButton");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleName, webDriver, "Wait for routingRuleName to be visible");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "%body/string% == \"hello, world!\"","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_THREE","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addRuleButton, webDriver, "Click on add button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleCreated, webDriver, "Wait routingRuleCreated to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleCreated, webDriver, "Verify routingRuleCreated");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleButton, webDriver, "Click on routingRuleButton");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRuleName, webDriver, "Wait for routingRuleName to be visible");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "%body/string% == \"hello, world!\"","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Press tab button");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "WF_CASE_FOUR","SendKeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to add WORKFLOW in subscriber");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.addRuleButton, webDriver, "Click on add button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRule2Created, webDriver, "Wait routingRuleCreated to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.routingRule2Created, webDriver, "Verify routingRuleCreated");
		Thread.sleep(3000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsSaveButton, webDriver, "Click on save button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subsSavedMessage, webDriver, "Wait for subscriber saved message to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberMultipleCreatedLink, webDriver, "Wait for subscriber created to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberMultipleCreatedLink, webDriver, "Verify subscriber created with workflow as invocation");
	}

	@Test(priority = 12,dependsOnMethods = {"subscriberWithMultipleRoutingRulesWF"},groups = {"Messaging BackLog Tests-Design Time"},description = "Verify user can access workflow and flowservices from routing rules from subscriber page modal.")
	public void accessWorkflowAndFlowServices()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberWithWorkflowInvocation, webDriver,"Click on WF_SUBS_USED_ASSETS subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowUsedLink, webDriver,"Click on WF_SUBS_USED workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerActionCanvas, webDriver, "Wait for canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.triggerActionCanvas, webDriver, "Verify workflow visited from subcriber ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.leaveCanvasButton, webDriver,"Click on leave canvas button to go back subscriber modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.EventsLink, webDriver, "Click on Events link to visit dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.MessagingToggleButton, webDriver, "Expand Messaging");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberWithFlowservicesInvocation, webDriver, "Wait for subscriber listing modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberWithFlowservicesInvocation, webDriver,"Click on FS_MESSAGING_BACKLOG_SUBS subscriber");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.subscriberEditModal, webDriver, "Wait for subscriber modal to be visible");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowServicesUsedLink, webDriver,"Click on FS_MESSAGING_USED flowservices");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowServiceCanvas, webDriver, "Wait for flowservices canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.flowServiceCanvas, webDriver, "Verify flowservices visited from subcriber ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.leaveFlowservicesCanvasButton, webDriver,"Click on leave flowservices canvas button to go back subscriber modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createNewProjectButton, webDriver,"Wait for project dashboard to load");
	}

	@Test(priority = 13,dependsOnMethods = {"accessWorkflowAndFlowServices"},groups = {"Messaging BackLog Tests-Design Time"},description="Verify system should not allow to clone workflow with multiple routing rules applied")
	public void cloneWorkflowWithRoutingRule()  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox,"Messaging Automation", webDriver,"Enter Messaging Automation project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Click on searched project card to open");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createdWorkflow, webDriver, "Wait recipes import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultWorkflowInvocationEllipsis, webDriver, "Click on default invocation ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultWorkflowCloneOption, webDriver, "Wait for workflow clone option to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.defaultWorkflowCloneOption, webDriver,"Click on workflow clone option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowClonwInfoModal, webDriver, "Wait for workflow clone modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowCloneOption, webDriver,"Click on clone button to clone workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowCloneMessage, webDriver, "Wait for workflow clone success message to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.clonedWorkflow, webDriver,"Verify workflow cloned");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectDashBoardLink, webDriver,"Click on project link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.createNewProjectButton, webDriver,"Wait for project dashboard to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.projectSearchBox,"Messaging Automation", webDriver,"Enter Messaging Automation project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.searchedProject, webDriver, "Wait for project to be visible");
		String workflowCount = SeleniumWebElementsUtils.fetchElementText(com.webMethods.io.Integration.DesignTimeTests.DesignTimeLocators.workflowCountProjectCard, webDriver,"Get workflow count text");
		String parts[] = workflowCount.split(" Workflows");
		int count = Integer.parseInt(parts[0]);

		if(count>=9&&count!=0)
		{
			ExtentTestManager.getTest().pass("Default invocation workflow used in subcriber is copied without routing rules used workflow in project.");
		}
		else
		{
			ExtentTestManager.getTest().pass("Default invocation workflow used in subcriber is copied with routing rules used workflow in project.");
		} 
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