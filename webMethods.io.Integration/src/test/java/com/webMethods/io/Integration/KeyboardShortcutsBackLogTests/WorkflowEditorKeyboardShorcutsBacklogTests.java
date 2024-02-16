package com.webMethods.io.Integration.KeyboardShortcutsBackLogTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class WorkflowEditorKeyboardShorcutsBacklogTests 
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

	@Test(priority = 0,groups = {"KeyboardShorcutsBacklogTests"},description = "Verify login user : WorkflowEditorKeyboardShorcutsBacklogTests")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver, "Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods= {"userLogin"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify shortcuts panel SHIFT+K Functionality")
	public void keyBoardShortCutPanel() 
	{
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.searchProjectTextBox,"BACKLOG_TEST_DATA", webDriver, "Enter BACKLOG_TEST_DATA project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.backlogTestDataProjectName, webDriver, "Click on BACKLOG_TEST_DATA project card");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.searchWorkflowTextBox,"KeyBoard Shortcuts Backlog Test Data", webDriver, "Enter KeyBoard Shortcuts Backlog Test Data name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.workflowCard, webDriver, "Mouseover on workflow card");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.workflowCardEditButtonOption, webDriver, "Click on edit button to edit workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.startAction, webDriver, "Wait for canvas to load");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "K","Open keyboard short cut panel Shift+K");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.keyBoardShortCutModal, webDriver,"Verify keyboard short cut visible");
		SeleniumKeyBoardActionUtils.keyboardSingleKeyAction(webDriver, Keys.ESCAPE, "Close keyboard short cut panel"); 
	}

	@Test(priority = 2,dependsOnMethods= {"keyBoardShortCutPanel"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify IDs for all actions SHIFT+I Functionality")
	public void keyBoardShortCutID() 
	{
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "I","Open keyboard shot cut panel Shift+I");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a0activityID, webDriver,"Verify a0");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a1activityID, webDriver,"Verify a1");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a2activityID, webDriver,"Verify a2");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a3activityID, webDriver,"Verify a3");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "I","Open keyboard shot cut panel Shift+I");
	}

	@Test(priority = 3,dependsOnMethods= {"keyBoardShortCutID"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify workflow Settings panel SHIFT+S Functionality")
	public void keyBoardShortCutSettingsPanel() 
	{
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "S","Open keyboard shot cut panel Shift+S");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.workflowSettingsModal, webDriver,"Verify workflow settings modal");
		SeleniumKeyBoardActionUtils.keyboardSingleKeyAction(webDriver, Keys.ESCAPE, "Close workflow settings panel"); 
	}

	@Test(priority = 4,dependsOnMethods= {"keyBoardShortCutSettingsPanel"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify workflow debug panel SHIFT+D Functionality")
	public void keyBoardShortDebugPanel() 
	{
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "D","Open keyboard shot cut panel Shift+D");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.debugModal, webDriver,"Verify debug modal");
	}

	@Test(priority = 5,dependsOnMethods= {"keyBoardShortDebugPanel"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify ESC button functionality")
	public void keyBoardShortCutEscape() 
	{
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape button");
	}

	@Test(priority = 6,dependsOnMethods= {"keyBoardShortCutEscape"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify arrow key functionality for Traverse main Workflow from start action.")
	public void keyBoardShortCutArrowKeys() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.startActionID, webDriver,"Click on start action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_RIGHT, "Press Arrow key right");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_RIGHT, "Press Arrow key right");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_RIGHT, "Press Arrow key right");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape button");
	}

	@Test(priority = 7,dependsOnMethods= {"keyBoardShortCutArrowKeys"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify traverse actions inside Loop ALT+ Left arrow functionality")
	public void keyBoardShortCutLoopTraverse() 
	{
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a1activityID, webDriver, "Open loop action");
		SeleniumKeyBoardActionUtils.keyboardCombinationAltKey(webDriver, Keys.ARROW_RIGHT,"Traverse Alt + Arrow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.closeLoopModal, webDriver,"Close loop modal");
	}

	@Test(priority = 8,dependsOnMethods= {"keyBoardShortCutLoopTraverse"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify save current Workflow Ctrl+S functionality")
	public void keyBoardShortCutSaveWorkflow() 
	{
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, "s", "Workflow save Ctrl + S");
	}

	@Test(priority = 9,dependsOnMethods= {"keyBoardShortCutSaveWorkflow"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify execute current Workflow Ctrl+E functionality")
	public void keyBoardShortCutExecuteWorkflows() 
	{
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, "e", "Workflow execute Ctrl + E");
	}

	@Test(priority = 10,dependsOnMethods= {"keyBoardShortCutExecuteWorkflows"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify canvas zoom in Ctrl+Up arrow functionality")
	public void keyBoardShortCutCanvasZoomIn() 
	{
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, Keys.ARROW_UP, "Canvas zoom in Ctrl+Up arrow functionality");
	}

	@Test(priority = 11,dependsOnMethods= {"keyBoardShortCutCanvasZoomIn"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify canvas zoom out Ctrl+Down arrow functionality")
	public void keyBoardShortCutCanvasZoomOut() 
	{
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, Keys.ARROW_DOWN, "Canvas zoom in Ctrl+Down arrow functionality");
	}

	@Test(priority = 12,dependsOnMethods= {"keyBoardShortCutCanvasZoomOut"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify return to default canvas zoom level Ctrl+BACKSPACE arrow functionality")
	public void keyBoardShortCutCanvasZoomLevelReset() 
	{
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, Keys.BACK_SPACE, "Canvas zoom in Ctrl+BACKSPACE arrow functionality");
	}

	@Test(priority = 13,dependsOnMethods= {"keyBoardShortCutCanvasZoomLevelReset"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify select multiple actions Ctrl+ LEFT click functionality")
	public void keyBoardShortCutCtrlLeft() 
	{ 
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndLeftMouse(webDriver,com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a0activityID, com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.a2activityID,"Select multiple actions");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ESCAPE,"Press escape button");
	}

	@Test(priority = 14,dependsOnMethods= {"keyBoardShortCutCtrlLeft"},groups = {"KeyboardShorcutsBacklogTests"},description = "Verify arrow button traverse not supported once action is selected by selecting")
	public void keyBoardShortCutCtrlLeftArrow() 
	{
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ARROW_DOWN,"Press Arrow right button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.KeyboardShortcutsBackLogTests.WorkflowEditorKeyboardShorcutsBacklogTestsLocators.notificationMessage, webDriver, "Verify notification message if action is selected and arrow button is pressed to traverse");
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