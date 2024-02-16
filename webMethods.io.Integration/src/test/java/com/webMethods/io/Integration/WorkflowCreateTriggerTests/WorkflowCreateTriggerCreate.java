package com.webMethods.io.Integration.WorkflowCreateTriggerTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class WorkflowCreateTriggerCreate
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

	@Test(priority = 0,groups = {"WorkflowCreateTriggerCreate"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"WorkflowCreateTriggerCreate"},description="Search project and create workflow")
	public void createNewWorkflowTests() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.projectSearchTextBox2,PropertiesData.readInputData("defaultProjectName"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.defaultProjects, webDriver, "Click on searched project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.createNewWorkflows,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.createNewWorkflowButtons,webDriver,"Click on new workflow button");
	}

	@Test(priority = 2,dependsOnMethods = {"createNewWorkflowTests"},groups = {"WorkflowCreateTriggerCreate"},description="Verify activities panel visible test")
	public void assertActivitiesPanelVisibleTest() 
	{		
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.activitiesPanels,webDriver,"Assert activities panel visible on canvas");
	}

	@Test(priority = 3,dependsOnMethods = {"assertActivitiesPanelVisibleTest"},groups = {"WorkflowCreateTriggerCreate"},description="Verify canvas assets visible test")
	public void canvasAssetsTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 4,dependsOnMethods = {"canvasAssetsTest"},groups = {"WorkflowCreateTriggerCreate"},description="Verify Create trigger test")
	public void createTriggerTest() 
	{
		//Test Steps
		//drag and drop logger
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.loggerActionId,webDriver,"Assert logger action visible on canvas");

		//connection 
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.loggerActionId,webDriver,"Mouseover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.firstActionConnector,com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.stopAction,webDriver,"Connect logger action with stop action");

		//Add trigger 
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.startAction,webDriver,"Double click on start action");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.TriggerIconString,webDriver,"Wait for webhook icon to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.triggerSearchTextBox,PropertiesData.readInputData("clockTriggerName"),webDriver,"Search clock trigger"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.clockTriggerObject,webDriver,"Select clock trigger after search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.triggerNextButton,webDriver,"Click on Next button after selecting trello trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.DoneButton,webDriver,"Click on Done button");

		//rename workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.editPencilIcon, webDriver, "Click on pencil icon to rename workflow");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.workflowRenameTextbox,webDriver,"Clear test area");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.workflowRenameTextbox,PropertiesData.readInputData("Workflowcreate"),webDriver,"Rename workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.DoneButton,webDriver,"Click ons Done button");

		//save workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.workflowSave,webDriver,"Save workflow");
		//SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.workflowSaveMessage, webDriver, "Wait till workflow save message disappear");

		//leave workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.leaveCanvas,webDriver,"Leave canvas page");		
	}

	@Test(priority = 5,dependsOnMethods = {"createTriggerTest"},groups = {"WorkflowCreateTriggerCreate"},description="Verify and delete trigger")
	public void Verifyanddelete() throws InterruptedException 
	{
		//verify workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Triggers,webDriver,"click on Triggers");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.ClockTriggerAvailablity, webDriver, "Wait for the trello trigger to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.ClockTriggerAvailablity,webDriver,"Mouse over on clock trigger");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.deleteicon,webDriver,"click on delete");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.clockmessage,webDriver,"verify clock message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.cancel,webDriver,"click on cancel");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Integrations,webDriver,"click on Integration");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.projectSearchTextBox2,"Workflowcreate",webDriver,"Input workflow name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Workflowname,webDriver, "Verify Workflowcreate workflow get display in list");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.elopsis,webDriver,"Click on default project");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.deleteworkflow,webDriver,"Click on delete workflow");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.popup1, webDriver, "Wait for popup to diaply");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.deleteworkflow1,webDriver,"Click on delete workflow");
	}

	@Test(priority = 6,dependsOnMethods = {"Verifyanddelete"},groups = {"WorkflowCreateTriggerCreate"},description="Verify configuration test")
	public void configurationTest() throws InterruptedException 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Configurations,webDriver,"click on configuration");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.Triggers,webDriver,"click on Triggers");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.WorkflowCreateTriggerTests.WorkflowCreateTriggerCreateLocators.ClockTriggerUnavailablity, webDriver, "Check for clock trigger deleted");
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