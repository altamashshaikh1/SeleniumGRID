package com.webMethods.io.Integration.PublishDeployExecutionLogsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
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

public class PublishDeployWorkflowExecutionLogEnableDisableOption
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static String responseBody;

	public static String authtoken;

	public static String cookie;

	public static String csrftoken;

	public static boolean skipandoverridestatus;

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

	@Test(priority = 0,groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Login user : WorkflowExecutionLogs enable option (PublishDeploy)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "WorkflowExecutionLogs enable option (PublishDeploy) : New project")
	public void publishDeployWorkflowExecutionLogEnableOptionEnable() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Click on Add new project icon");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.newProjectModalInputLabel,PropertiesData.readInputData("executionLogsProjectName"),webDriver,"Input project name");       
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.newProjectModalCreateButton,webDriver,"Click on create new button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectCreationSuccessMessage,PropertiesData.readAssertionData("workflowExeLogsProjectCreationMesssage"),webDriver,"Assert project created",softAssert);
	}

	@Test(priority = 2,dependsOnMethods = {"publishDeployWorkflowExecutionLogEnableOptionEnable"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "WorkflowExecutionLogs enable option (PublishDeploy) : New workflow")
	public void publishDeployWorkflowExecutionLogEnableOptionLogsEnable()  
	{
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflowButton, webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//To verify Maintain execution logs status
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingsopt, webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingmodal, webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowExecutionSettings, webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintain_exe_log_opt_str, webDriver, "Wait till options load");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowruntime3, webDriver, "Click on Workflow runtime dropdown");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowruntimelist, webDriver, "Wait till list get load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowruntimelist, webDriver, "Select workflow runtime");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is OFF");	
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsoption, webDriver, "Click on Maintain Execution logs");
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox+" Option is ON");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Visualization line options is OFF");	
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox, webDriver, "Click on Visualization line option");
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox+" Option is ON");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSettingsclosedopt, webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");

		//Drag Logger action onto canvas
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.actionSearchTextBox,PropertiesData.readInputData("executionLogsSearchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.action2Searched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.trelloConnectorActionId,webDriver,"Assert logger action visible on canvas");

		//Configure logger action
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.trelloConnectorActionId,webDriver,"Mouseover on Logger connector");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.firstActionConnector,com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.stopAction,webDriver,"Connect Logger connector with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.trelloConnectorActionId,webDriver,"Double click on logger action");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectParatext, "Project parameters", webDriver, "Assert Action configure text",softAssert);
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.loggerActionTextArea,PropertiesData.readInputData("loggerActionInputData1"),webDriver,"Input data in logger action");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.testactionmodaldonebutton, webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.testactionmodaldonebutton, webDriver, "Click on Done button");

		//Rename workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.editPencilIcon, webDriver, "Click on pencil icon to rename workflow");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowRenameTextbox,webDriver,"Clear test area");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowRenameTextbox,PropertiesData.readInputData("renameWorkflowName2"),webDriver,"Rename workflow");	   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowRenameDoneButton,webDriver,"Click on done button");

		//Save workflow
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSave, webDriver, "Wait till save button is Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"), webDriver,"Assert workflow save message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSaveMessage, webDriver, "Wait till workflow save message disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addworkfloButtonString,webDriver,"Wait for project dashboard to load");
	}

	@Test(priority = 3,dependsOnMethods = {"publishDeployWorkflowExecutionLogEnableOptionLogsEnable"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "WorkflowExecutionLogs enable option (PublishDeploy) : Project publish to tenant")
	public void publishDeployWorkflowExecutionLogEnableOptionProLogs() 
	{
		//Publish project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishProject,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishProject,webDriver,"Click on P	roject publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.assetsModal,webDriver,"Assert all assets are visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowscheckbox, webDriver, "Click on Workflow checkbox");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.publishSettingsModalString,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deploymentNameTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.ProjectpublishButton, webDriver, "Wait till publish button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.ProjectpublishButton, webDriver, "Click on publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishMessage,PropertiesData.readAssertionData("projectPublishMessage"),webDriver,"Assert Project Publish message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishMessage, webDriver, "Wait till message disappear");
	}

	@Test(priority = 4,dependsOnMethods = {"publishDeployWorkflowExecutionLogEnableOptionProLogs"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "WorkflowExecutionLogs enable option (PublishDeploy) : Project deploy in tenant")
	public void publishDeployWorkflowExecutionLogEnableOptionDeployLogs() 
	{
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.ssoLoginLink, webDriver, "Click on SSO login Link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProjectButton, webDriver,"Verify user logged in");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject,webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject,webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deployprojectnamemodal,webDriver, "Wait for project name modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.saveandcontinueButton,webDriver, "click on save and continue option");

		//To Check Skip and Override Capabilities is enabled or disabled for given tenant
		//Login user first and extract authtoken,cookie and csrf token
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, 
				"Login account to get authtoken, cookie and CSRF token to make tenant capabilities API request");
		authtoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken");
		cookie = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie");
		csrftoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf");

		//To Send capabilities call and check status of Skip and override feature
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/integration/rest/tenant/capabilities",
				authtoken,
				cookie,
				csrftoken,
				"Make tenant capabilities request");

		//extract skip and override status
		skipandoverridestatus =  HttpURLConnHelperFuntions.getJsonKeyBooleanValue(responseBody, "integration", "tenantCapabilities","skipAssetsEnabled");

		if(skipandoverridestatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.skipassetsString,webDriver,"Wait for configure account modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton,webDriver,"Click on Next button");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.configureAccountString,webDriver,"Wait for configure account modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.configureTriggerString,webDriver,"Wait for configure trigger modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.configureParameterString,webDriver,"Wait for configure parameter modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectdeployButton,webDriver, "Click on Deploy option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeployementMessage,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeployementMessage,PropertiesData.readAssertionData("workflowExeLogsEnableProjectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeployementMessage,webDriver, "Wait for message to disappear");
	}

	@Test(priority = 5,dependsOnMethods = {"publishDeployWorkflowExecutionLogEnableOptionDeployLogs"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "WorkflowExecutionLogs enable option (PublishDeploy) : Debugger option")
	public void publishDeployWorkflowExecutionLogEnableOptDebugEnable() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject,webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject,webDriver, "Click on Project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsONworkflow,webDriver, "Verify Deployed workflow get display in list");

		//Visit canvas and check state of Maintain execution logs option
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsONworkflow,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is ON");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox+" Option is OFF");
			Assert.fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox+" Option is OFF");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckboxstate.isSelected()== true)
		{
			ExtentTestManager.getTest().pass("Visualization line options is ON");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckboxstate+" Option is OFF");
			Assert.fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckboxstate+" Option is OFF");
		}

		//To verify the workflow runtime value is same as original workflow
		String temp1 = com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowruntimeinput.getText();
		if(temp1.equalsIgnoreCase("12"))
		{
			ExtentTestManager.getTest().pass("Workflow Runtime value in destination tenant workflow is same as source tenant workflow");
		}
		else
		{
			ExtentTestManager.getTest().fail("Workflow Runtime value in destination tenant workflow is not same as source tenant workflow");
			Assert.fail("Workflow Runtime value in destination tenant workflow is not same as source tenant workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSettingsclosedopt, webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 6,dependsOnMethods = {"publishDeployWorkflowExecutionLogEnableOptDebugEnable"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Create new workflow")
	public void publishDeployWorkflowExecutionLogDisableOptionPublishDeploy() 
	{
		webDriver.navigate().to(MavenArgumentConstants.SOURCE_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject, webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflowButton, webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");

		//Drag Logger action onto canvas
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.actionSearchTextBox,PropertiesData.readInputData("executionLogsSearchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.action2Searched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.trelloConnectorActionId,webDriver,"Assert logger action visible on canvas");

		//Configure logger action
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.trelloConnectorActionId,webDriver,"Mouseover on Logger connector");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.firstActionConnector,com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.stopAction,webDriver,"Connect Logger connector with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.trelloConnectorActionId,webDriver,"Double click on logger action");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectParatext, "Project parameters", webDriver, "Assert Action configure text",softAssert);
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.loggerActionTextArea,PropertiesData.readInputData("loggerActionInputData1"),webDriver,"Input data in logger action");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.testactionmodaldonebutton, webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.testactionmodaldonebutton, webDriver, "Click on Done button");

		//Rename workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.editPencilIcon, webDriver, "Click on pencil icon to rename workflow");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowRenameTextbox,webDriver,"Clear test area");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowRenameTextbox,PropertiesData.readInputData("renameWorkflowName3"),webDriver,"Rename workflow");	   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowRenameDoneButton,webDriver,"Click on done button");

		//Save workflow
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSave, webDriver, "Wait till save button is Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"), webDriver,"Assert workflow save message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSaveMessage, webDriver, "Wait till workflow save message disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
	}

	@Test(priority = 7,dependsOnMethods = {"publishDeployWorkflowExecutionLogDisableOptionPublishDeploy"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Publish project")
	public void publishDeployWorkflowExecutionLogDisableOptionExeLogs()
	{
		//Publish project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishProject,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.assetsModal,webDriver,"Assert all assets are visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowscheckbox, webDriver, "Click on Workflow checkbox");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.publishSettingsModalString,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deploymentNameTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keybaord actions tab");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keybaord actions tab");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keybaord actions tab");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.ProjectpublishButton, webDriver, "Wait till publish button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.ProjectpublishButton, webDriver, "Click on publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishMessage,PropertiesData.readAssertionData("projectPublishMessage"),webDriver,"Assert Project Publish message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectPublishMessage, webDriver, "Wait till message disappear");
	}

	@Test(priority = 8,dependsOnMethods = {"publishDeployWorkflowExecutionLogDisableOptionExeLogs"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Delete Test Data")
	public void publishDeployWorkflowExecutionLogDisableOptionDeleteData() 
	{
		//To Delete Workflows
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject, webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowslist,webDriver,"Wait for Workflow dashboard to load");

		try
		{
			java.util.List<WebElement> workflows = webDriver.findElements(By.xpath("//span[contains(@class,'truncate title-flow-name left ')]"));
			for(int i=0;i<workflows.size();i++)
			{
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createdWorkflowEllipsis1,webDriver,"Click on workflow ellipsis");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.menu,webDriver,"Wait for menu load");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteWorkflow,webDriver,"Click on Delete");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.confirmDelete,webDriver,"Wait for confirmation window");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteOption,webDriver,"Click on Delete Button");
				SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"), webDriver,"Assert workflow delete message",softAssert);
				SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowDeleteMessage, webDriver, "Wait till workflow delete message disappear");
			}
		}
		catch(WebDriverException e)
		{
			ExtentTestManager.getTest().fail("Delete Workflows"+". "+"Please check below for errors");
			Assert.fail("Delete Workflows"+". "+"Please check below for errors");
		}

		//To Delete Project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteproject,webDriver,"Wait for project delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteproject, webDriver, "click on delete option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteprojectmodalString, webDriver, "Wait for confirmantion message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeletecnfbutton, webDriver, "Click on Delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeleteSuccessMessage,PropertiesData.readAssertionData("projectDeleteMessage"),webDriver,"Assert project delete message",softAssert);
	}

	@Test(priority = 9,dependsOnMethods = {"publishDeployWorkflowExecutionLogDisableOptionDeleteData"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Deploy project test")
	public void publishDeployWorkflowExecutionLogDisableOptionDeploy() 
	{
		webDriver.get(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str,webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject,webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deploymenytab,webDriver, "Wait till Deployment tab is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deploymenytab,webDriver, "Click on Deployment Tab");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deployoption,webDriver, "Wait till Deploy Option is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deployoption,webDriver, "Click on Deploy Option");

		//To Check Skip and Override Capabilities is enabled or disabled for given tenant
		//Login user first and extract authtoken,cookie and csrf token
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, 
				"Login account to get authtoken, cookie and CSRF token to make tenant capabilities API request");
		authtoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken");
		cookie = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie");
		csrftoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf");

		//To Send capabilities call and check status of Skip and override feature
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/integration/rest/tenant/capabilities",
				authtoken,
				cookie,
				csrftoken,
				"Make tenant capabilities request");

		//extract skip and override status
		skipandoverridestatus =  HttpURLConnHelperFuntions.getJsonKeyBooleanValue(responseBody, "integration", "tenantCapabilities","skipAssetsEnabled");

		if(skipandoverridestatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.skipassetsString,webDriver,"Wait for configure account modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.nextButton,webDriver,"Click on Next button");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.configureAccountString,webDriver,"Wait for configure account modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.configureTriggerString,webDriver,"Wait for configure trigger modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.configureParameterString,webDriver,"Wait for configure parameter modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectdeployButton,webDriver, "Click on Deploy option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeployementMessage,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeployementMessage,PropertiesData.readAssertionData("workflowExeLogsEnableProjectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeployementMessage,webDriver, "Wait for message to disappear");
	}

	@Test(priority = 10,dependsOnMethods = {"publishDeployWorkflowExecutionLogDisableOptionDeploy"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Assert debugger option test")
	public void publishDeployWorkflowExecutionLogDisableOptionAssertExeLog()

	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str,webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexeLogTestproject,webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.loaderbody,webDriver, "Wait till page load properly");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Wait till workflow Search box is clickable");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("renameWorkflowName3"),webDriver,"Input workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on serachbox");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on workflow search box");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accnamevalidatorcircle,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsOFFworkflow,webDriver, "Verify  workflow get display in list");

		//Visit canvas and check state of Maintain execution logs option
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsOFFworkflow,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Maintain exeution logs options is OFF");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainActitivylogcheckbox+" Option is ON");
		}

		//TO verify Visualization line checkbox status
		if(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox.isSelected()== false)
		{
			ExtentTestManager.getTest().pass("Visualization line options is OFF");	
		}
		else
		{
			ExtentTestManager.getTest().fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox+" Option is ON");
			Assert.fail(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.visualizationlinecheckbox+" Option is ON");
		}

		//To verify the workflow runtime value is same as original workflow 
		String temp1 = com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowruntimeinput.getText();

		if(temp1.equalsIgnoreCase("3"))
		{
			ExtentTestManager.getTest().pass("Workflow Runtime value in destination tenant workflow is same as source tenant workflow");
		}
		else
		{
			ExtentTestManager.getTest().fail("Workflow Runtime value in destination tenant workflow is not same as source tenant workflow");
			Assert.fail("Workflow Runtime value in destination tenant workflow is not same as source tenant workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Wait till workflow Search box is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("renameWorkflowName2"),webDriver,"Input workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on workflow search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on workflow search box");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.accnamevalidatorcircle,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsONworkflow,webDriver, "Verify workflow get display in list");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsONworkflow,webDriver, "Mouse hover on Edit Option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workfloweditoption,webDriver, "Click on Edit Option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingsopt,webDriver, "Click on Workflow Setting option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowsettingmodal,webDriver, "Wait for workflow setting modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowExecutionSettings,webDriver, "Click on Workflow Execution settings");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintain_exe_log_opt_str,webDriver, "Wait till options load");

		String temp2 = com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowruntimeinput.getText();

		if(temp2.equalsIgnoreCase("12"))
		{
			ExtentTestManager.getTest().pass("After Redeployment Workflow Runtime value in destination tenant workflow is same as source tenant workflow");
		}
		else
		{
			ExtentTestManager.getTest().fail("After Redeployment Workflow Runtime value in destination tenant workflow is not same as source tenant workflow");
			Assert.fail("After Redeployment Workflow Runtime value in destination tenant workflow is not same as source tenant workflow");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowSettingsclosedopt,webDriver, "Click on Workflow Settings closed button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 11,dependsOnMethods = {"publishDeployWorkflowExecutionLogDisableOptionAssertExeLog"},groups={"ExecutionLogsEnableDisabled - StagPromotion"},description = "Delete Assets")
	public void publishDeployWorkflowExecutionLogDisableOptionDataDelete() 
	{
		//To Delete Workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createNewWorkflow,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainexelogsONworkflow,webDriver, "Verify Trello workflow get display in list");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowslist,webDriver,"Wait for Workflow dashboard to load");

		try
		{
			java.util.List<WebElement> workflows = webDriver.findElements(By.xpath("//span[contains(@class,'truncate title-flow-name left ')]"));

			for(int i=0;i<workflows.size();i++)
			{
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.createdWorkflowEllipsis1,webDriver,"Click on workflow ellipsis");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.menu,webDriver,"Wait for menu load");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteWorkflow,webDriver,"Click on Delete");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.confirmDelete,webDriver,"Wait for confirmation window");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteOption,webDriver,"Click on Delete Button");
				SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"),webDriver,"Assert workflow delete message",softAssert);
				SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.workflowDeleteMessage,webDriver, "Wait till workflow delete message disappear");
			}
		}
		catch(WebDriverException webdriverexception)
		{
			ExtentTestManager.getTest().fail("Exception caught on workflow delete. "+webdriverexception.getMessage());
			Assert.fail("Exception caught on workflow delete. "+webdriverexception.getMessage());
		}

		//To Delete Project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.addNewProject,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectSearchTextBox,PropertiesData.readInputData("executionLogsEnableProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.maintainExelogTestProject_str,webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteproject,webDriver,"Wait for project delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteproject,webDriver, "click on delete option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.deleteprojectmodalString,webDriver, "Wait for confirmantion message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeletecnfbutton,webDriver, "Click on Delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployExecutionLogsTests.PublishDeployWorkflowExecutionLogEnableDisableOptionLocators.projectDeleteSuccessMessage,PropertiesData.readAssertionData("projectDeleteMessage"),webDriver,"Assert project delete message",softAssert);
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