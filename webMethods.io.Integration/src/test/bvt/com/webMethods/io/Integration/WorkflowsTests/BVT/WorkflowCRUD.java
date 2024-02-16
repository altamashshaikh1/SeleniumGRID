package com.webMethods.io.Integration.WorkflowsTests.BVT;

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

public class WorkflowCRUD 
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static String webhookURL;

	public static String webhookURLTwo;

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

	@Test(priority = 0,groups = {"Workflows"},description = "Workflows : Verify User Account Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);
												
		//Initialize pagefactory						
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators");
		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Workflows"},description = "Create new workflow test")
	public void createNewWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.projectSearchTextBox,PropertiesData.readInputData("defaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.defaultProject,webDriver,"Click on default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
	}

	@Test(priority = 2,dependsOnMethods = {"createNewWorkflowDashboardTest"},groups = {"Workflows"},description = "Assert activites panel visible")
	public void activitiesPanelVisibleWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
	}

	@Test(priority = 3,dependsOnMethods = {"activitiesPanelVisibleWorkflowDashboardTest"},groups = {"Workflows"},description = "Assert canvas assets visible")
	public void canvasAssetsWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 4,dependsOnMethods = {"canvasAssetsWorkflowDashboardTest"},groups = {"Workflows"},description = "Drag drop actions on canvas")
	public void dragDropActionsWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.loggerActionId,webDriver,"Assert logger action visible on canvas");
	}

	@Test(priority = 5,dependsOnMethods = {"dragDropActionsWorkflowDashboardTest"},groups = {"Workflows"},description = "Connect actions on canvas")
	public void connectActionsWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.loggerActionId,webDriver,"Mouseover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.firstActionConnector,com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.stopAction,webDriver,"Connect logger action with stop action");
	}

	@Test(priority = 6,dependsOnMethods = {"connectActionsWorkflowDashboardTest"},groups = {"Workflows"},description = "Configure actions")
	public void configureActionsWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.loggerActionId,webDriver,"Double click on logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionNextButton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.loggerActionTextArea,PropertiesData.readInputData("loggerActionInputData"),webDriver,"Input data in logger action");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionNextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 7,dependsOnMethods = {"configureActionsWorkflowDashboardTest"},groups = {"Workflows"},description = "Configure actions")
	public void actionWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionTestButton,webDriver,"Click on action test button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionTestData,webDriver,"Assert logger test data");
	}

	@Test(priority = 8,dependsOnMethods = {"actionWorkflowDashboardTest"},groups = {"Workflows"},description = "Save workflow")
	public void saveWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.actionNextButton,webDriver,"Click on Done button to compelte configurations");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowSave,webDriver,"Save workflow");
	}

	@Test(priority = 9,dependsOnMethods = {"saveWorkflowDashboardTest"},groups = {"Workflows"},description = "Rename workflow")
	public void renameWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.editPencilIcon, webDriver, "Click on pencil icon to rename workflow");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowRenameTextbox,webDriver,"Clear test area");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowRenameTextbox,PropertiesData.readInputData("renameWorkflowData"),webDriver,"Rename workflow");	   
	}

	@Test(priority = 10,dependsOnMethods = {"renameWorkflowDashboardTest"},groups = {"Workflows"},description = "Add workflow tags")
	public void workflowTagsWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,PropertiesData.readInputData("workflowTagName"),"Add workflow tags");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowRenameDoneButton,webDriver,"Click on done button to rename workflow and add workflow tags");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.renamedWorkflowName,PropertiesData.readAssertionData("renameWorkflowData"),webDriver,"Assert workflow renamed",softAssert);
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.leaveCanvasButton, webDriver, "Wait for leave canvas button to be clickable and visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.leaveCanvasButton, webDriver,"Click on leave canvas button to go back");
	}

	@Test(priority = 11,dependsOnMethods = {"workflowTagsWorkflowDashboardTest"},groups = {"Workflows"},description = "Execute workflow")
	public void verifyWorkflowDashboardExecuteTest() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.addNewWorkflowButton, webDriver, "Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.searchWorkflowTextbox,PropertiesData.readInputData("renameWorkflowData"), webDriver, "Search renamed workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.renamedWorkflowName,webDriver,"Mouseover on created/updated workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowEditOption,webDriver,"Click on edit option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowPlayButton,webDriver,"Wait for play button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowPlayButton,webDriver,"Click on play button to execute workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionStartMessage, webDriver, "Wait for workflow execution start message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionStartMessage,PropertiesData.readAssertionData("workflowExecutionStartMessage"), webDriver,"Assert workflow execution start message",softAssert);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionCompletedMessage, webDriver, "Wait for workflow execution completed message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionCompletedMessage,PropertiesData.readAssertionData("workflowExecutionCompleteMessage"), webDriver,"Assert workflow execution complete message",softAssert);
	}

	@Test(priority = 12,dependsOnMethods = {"verifyWorkflowDashboardExecuteTest"},groups = {"Workflows"},description = "Debug panel")
	public void debugPanelWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.leaveCanvasButton,webDriver,"Click on play button to execute workflow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.addNewWorkflowButton, webDriver, "Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.searchWorkflowTextbox,PropertiesData.readInputData("renameWorkflowData"), webDriver, "Search renamed workflow");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Enter to search workflow");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.renamedWorkflowName,webDriver,"Mouseover on created/updated workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowEditOption,webDriver,"Click on edit option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowPlayButton,webDriver,"Wait for play button to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.collapseActivityPanel, webDriver,"Collapse activity panel on canvas");
		SeleniumKeyBoardActionUtils.keyboardShiftCombination(webDriver, "D","Open debug panel");
	}

	@Test(priority = 13,dependsOnMethods = {"debugPanelWorkflowDashboardTest"},groups = {"Workflows"},description = "Workflow execution status")
	public void workflowDashboardExecutionTest() 
	{
		//Test steps
		SeleniumWebElementsUtils.textContainsPrTest(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionStatus,PropertiesData.readAssertionData("workflowExecutionStatus"), webDriver,"Assser workflow executed successfully",softAssert);
	}

	@Test(priority = 14,dependsOnMethods = {"workflowDashboardExecutionTest"},groups = {"Workflows"},description = "Click on export workflow logs")
	public void workflowDashboardExecutionLogsExportTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionExportLogs,webDriver,"Export workflow logs");
	}

	/*@Test(priority = 15,dependsOnMethods = {"workflowDashboardExecutionLogsExportTest"},groups = {"Workflows"},description = "Assert logs file downloaded")
	public void asserDownloadedFileWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.fetchWorkflowExecutionLogsFile(CommonAutomationData.BROWSER_MAVEN_ARGUMENT,CommonAutomationData.TEST_INPUT_FILE_PATH+"AuditLogs.json","JSON","Assert workflow execution logs downloaded");
	}*/

	@Test(priority = 16,dependsOnMethods = {"workflowDashboardExecutionTest"},groups = {"Workflows"},description = "View workflow execution logs")
	public void workflowDashboardViewLogsTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowExecutionLogs,webDriver,"View workflow logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowLogs, webDriver,"Assert workflow logs visible in debug panel");
	}

	@Test(priority = 17,dependsOnMethods = {"workflowDashboardViewLogsTest"},groups = {"Workflows"},description = "Assert workflow execution count on project dashboard")
	public void workflowDashboardExecutionCountTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.leaveCanvas,webDriver,"Leave canvas page");   
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowRunCount,PropertiesData.readAssertionData("workflowRunFlowCount"),webDriver,"Assert workflow execution count increased after excecuting workflow",softAssert);
	}

	@Test(priority = 18,dependsOnMethods = {"workflowDashboardExecutionCountTest"},groups = {"Workflows"},description = "Assert workflow count on project dashboard")
	public void workflowDashboardCountTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.searchBox,"Default",webDriver,"Search default project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter to search created project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.searchedProjectName,webDriver,"Assert project searched");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowCount,webDriver,"Assert workflow count updated after creating new workflows",softAssert);
	}

	@Test(priority = 19,dependsOnMethods = {"workflowDashboardCountTest"},groups = {"Workflows"},description = "Assert workflow search workflow")
	public void searchWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.defaultProject,webDriver,"Click on Default project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowCardString,webDriver,"Wait for workflow dashboard");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.projectSearchTextBox,PropertiesData.readInputData("renameWorkflowData"),webDriver,"Enter workflow name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter button to search the workflow");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.searchedWorkflow,webDriver,"Assert workflow searched");
	}

	@Test(priority = 20,dependsOnMethods = {"searchWorkflowDashboardTest"},groups = {"Workflows"},description = "Assert workflow search workflow with tags")
	public void searchWorkflowDashboardTagsTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowTagsTextBox,webDriver,"Click on workflow search tags text box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowTagName,webDriver,"Select created workflow tag");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.searchedWorkflow,webDriver,"Assert workflow searched with tags");
	}

	@Test(priority = 21,dependsOnMethods = {"searchWorkflowDashboardTagsTest"},groups = {"Workflows"},description = "Clone workflow")
	public void cloneWorkflowDashboardtest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createdWorkflowEllipsis1,webDriver,"Click on first workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.workflowClone,webDriver,"Click on clone option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.cloneButton,webDriver,"Click on clone button");
	}

	@Test(priority = 22,dependsOnMethods = {"cloneWorkflowDashboardtest"},groups = {"Workflows"},description = "Delete workflow")
	public void workflowDashboardDeleteTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.deleteWorkflow,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.deleteOption,webDriver,"Click on delete option");
	}

	@Test(priority = 23,dependsOnMethods = {"workflowDashboardDeleteTest"},groups = {"Workflows"},description = "Deactivate workflow")
	public void deactivateWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.switchIcon,webDriver,"Click on switch to deactivate workflow");
	}

	@Test(priority = 24,dependsOnMethods = {"deactivateWorkflowDashboardTest"},groups = {"Workflows"},description = "Activate workflow")
	public void activateWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.switchIcon,webDriver,"Click on switch to activate workflow");
	}

	@Test(priority = 25,dependsOnMethods = {"activateWorkflowDashboardTest"},groups = {"Workflows"},description = "Export workflow")
	public void exportWorkflowDashboardTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.createdWorkflowEllipsis1,webDriver,"Click on workflow ellipsis");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Select export option");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.quillEditorTextBox, "test", webDriver, "Enter description");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowsTests.BVT.WorkflowCRUDTestLocators.exportButton,webDriver,"Click on Export button");
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