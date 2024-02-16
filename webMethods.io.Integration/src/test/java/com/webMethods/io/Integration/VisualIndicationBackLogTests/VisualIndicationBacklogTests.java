package com.webMethods.io.Integration.VisualIndicationBackLogTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumUploadDownloadFilesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class VisualIndicationBacklogTests
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

	@Test(priority = 0,groups = {"VisualIndicationBacklogTests"},description = "Verify login user : VisualIndicationBacklogTests")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver, "Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"VisualIndicationBacklogTests"},description = "Verify when user add transform in the any connector then visual indicator of that transfrom get display on that action or not")
	public void visualIndicationConnector() 
	{
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.inputSearchProjectBox, "Default", webDriver, "Enter Default project name to search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.defaultProjectName, webDriver, "Click on Default project card");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.newWorkflowButton, webDriver,"Click on new workflow button/link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.createNewWorkflowButton, webDriver,"Click on new Create New Workflow button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.activitiesLists, webDriver,"Wait for all elements visible on canvas");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.activitiesSearchTextBox,"Logger", webDriver,"Enter logger action to search");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.searchedLoggerAction, webDriver,"Wait for logger action visible");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.searchedLoggerAction, webDriver,"Drag and drop logger action on canvas", -900, -20);	
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loggerActionId,webDriver,"Mouseover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.firstActionConnector,com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loggerActionId, webDriver,"Double click on logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.nextLoggerActionButton, webDriver,"Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDropDown, webDriver,"Click on transform action dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.addNewButton, webDriver,"Click on Add New button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformNameTextBox,"GetCurrentDateString", webDriver,"Enter transform action name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press Tab keyboard button");		
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "Get Current Date String","Send");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Enter to search action");	
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.patternTextBox,"YYYY-MM-DD HH:mm:ss.SSS Z", webDriver,"Enter date pattern");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loggerActionTextBox,"Test", webDriver,"Enter data for logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loggerActionNextButton, webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loggerActionDoneButton, webDriver,"Click on Done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFirst, webDriver,"Verify transform action attached to logger action");
	}

	@Test(priority = 2,dependsOnMethods = {"visualIndicationConnector"},groups = {"VisualIndicationBacklogTests"},description = "Verify when user add transform in the condition action then visual indicator of that transfrom get display on that condition action or not")
	public void visualIndicationConditionAction() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.conditionActionLine, webDriver,"Click on condition line");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.conditionLineSettingsIcon, webDriver,"Click on settings icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDropDown, webDriver,"Click on transform action dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.addNewButton, webDriver,"Click on Add New button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformNameTextBox,"GetCurrentDateString", webDriver,"Enter transform action name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press Tab keyboard button");		
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "Get Current Date String","Send");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Enter to search action");	
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.patternTextBox,"YYYY-MM-DD HH:mm:ss.SSS Z", webDriver,"Enter date pattern");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionSecond, webDriver,"Verify transform action attached to condition action");
	}

	@Test(priority = 3,dependsOnMethods = {"visualIndicationConditionAction"},groups = {"VisualIndicationBacklogTests"},description = "Verify when user add transform in the Switch action then visual indicator of that transfrom get display on that condition action or not")
	public void visualIndicationSwitch() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.activitiesSearchTextBox, webDriver, "Click on search text box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.searchTextBoxClearClose, webDriver, "Click clear textbox");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.activitiesSearchTextBox,"Switch",webDriver, "Enter switch action to search");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.searchedSwitchAction, webDriver, "Drag drop switch action on canvas",-600,-50);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.closeSwitchModal, webDriver, "Close switch action info modal");		
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.switchActionId,webDriver,"Mouseover on switch action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.secondActionConnector,com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.stopAction,webDriver,"Connect switch action with stop action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.switchCaseConnectorLine, webDriver, "Click on case 1 label");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.switchCaseSettingsIcon, webDriver, "Click on settings icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDropDown, webDriver,"Click on transform action dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.addNewButton, webDriver,"Click on Add New button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformNameTextBox,"GetCurrentDateString", webDriver,"Enter transform action name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press Tab keyboard button");		
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "Get Current Date String","Send");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Enter to search action");	
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.patternTextBox,"YYYY-MM-DD HH:mm:ss.SSS Z", webDriver,"Enter date pattern");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionThird, webDriver,"Verify transform action attached to switch action");
	}

	@Test(priority = 4,dependsOnMethods = {"visualIndicationSwitch"},groups = {"VisualIndicationBacklogTests"},description = "Verify when user add transform in the in the FlowService then visual indicator of that transfrom get display on that FlowService action or not")
	public void visualIndicationFlowServices() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.activitiesSearchTextBox, webDriver, "Click on search text box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.searchTextBoxClearClose, webDriver, "Click clear textbox");		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.flowServicesTab, webDriver,"Click on flowservices tab");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFlows,webDriver,"Drag drop flow services on canvas",-300,-70);
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.flowsActionId,webDriver,"Mouseover on flow services action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.flowsActionConnector,com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.stopAction,webDriver,"Connect flowservices action with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.flowsActionId, webDriver, "Double click on flowservices action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.nextLoggerActionButton, webDriver,"Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDropDown, webDriver,"Click on transform action dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.addNewButton, webDriver,"Click on Add New button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformNameTextBox,"GetCurrentDateString", webDriver,"Enter transform action name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press Tab keyboard button");		
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "Get Current Date String","Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Enter to search action");	
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.patternTextBox,"YYYY-MM-DD HH:mm:ss.SSS Z", webDriver,"Enter date pattern");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.loggerActionNextButton, webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFourth, webDriver, "Verify transform action applied to flow services action on canvas");
	}

	@Test(priority = 5,dependsOnMethods = {"visualIndicationFlowServices"},groups = {"VisualIndicationBacklogTests"},description = "Verify when hover on transfrom indicator then information realted to that transform should get display to user or not")
	public void visualIndicationHover() 
	{
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFirst, webDriver, "Mouseover on logger transform action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFirst, webDriver, "Verify hover text is shown");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionSecond, webDriver, "Mouseover on logger condition transform action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionSecond, webDriver, "Verify hover text is shown");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionThird, webDriver, "Mouseover on switch transform action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionThird, webDriver, "Verify hover text is shown");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFourth, webDriver, "Mouseover on flowservices transform action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.transformActionFourth, webDriver, "Verify hover text is shown");
	}

	@Test(priority = 6,dependsOnMethods = {"visualIndicationHover"},groups = {"VisualIndicationBacklogTests"},description = "Verify if user clone workflow with Transform then in cloned workflow Transform indicator should get display or not")
	public void visualIndicationCloneWorkflow() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.pencilIcon, webDriver, "Click on pencil icon");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.workflowNameTextBox, webDriver,"Clear workflow name");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.workflowNameTextBox,"VisualIndicationWorkflowTestData", webDriver,"Search VisualIndicationWorkflowTestData Workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.workflowNameTextBoxDone, webDriver, "Click on done button to rename workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.leaveCanvasButton, webDriver, "Click on leave canvas button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.saveAndExitWorkflow, webDriver, "Click on Save and Exit workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.newWorkflowButton, webDriver,"Wait for new workflow button/link");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.workflowSearchTextBox,"VisualIndicationWorkflowTestData", webDriver,"Search VisualIndicationWorkflowTestData workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Enter to search action");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.ellipsisOption, webDriver,"Click on ellipsis option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.cloneWorkflow, webDriver, "Click on clone workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.cloneButton, webDriver, "Click on clone button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.workflowSearchTextBox,"Copy of VisualIndicationWorkflowTestData", webDriver,"Search copied VisualIndicationWorkflowTestData workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Enter to search action");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.clonedWorkflowName, webDriver,"Verify workflow cloned");
	}

	@Test(priority = 7,dependsOnMethods = {"visualIndicationCloneWorkflow"},groups = {"VisualIndicationBacklogTests"},description = "Verify if user import workflow with Transform then in imported workflow Transform indicator should get display or not")
	public void visualIndicationImportWorkflow() 
	{
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"Visual Indication BackLog.zip",com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.importWorkflowButton, "Upload .zip file");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.importButton, webDriver,"Wait for import button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.VisualIndicationBackLogTests.VisualIndicationBacklogLocators.importButton, webDriver,"Click on import button");
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