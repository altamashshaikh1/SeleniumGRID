package com.webMethods.io.Integration.MonitorDashboardTests;

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
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FileUtilities.FileUtilitiesHelperFunctions;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class MonitorDashboard
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

	@Test(priority = 0,groups = {"MonitorDashboard"},description = "Login user : MonitorDashboard")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"MonitorDashboard"},description = "Visit monitor page")
	public void visitMonitorTest() 
	{
		//Visit monitor page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.projectSearchTextBox,PropertiesData.readInputData("monitorDefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.defaultProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorLinktext,webDriver,"Click on Monitor link");
	}

	@Test(priority = 2,dependsOnMethods = {"visitMonitorTest"},groups = {"MonitorDashboard"},description = "All workflow executions data table")
	public void verifyMonitorDashboardPageTest()
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionCount,webDriver,"Assert total workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowSuccessExecutionCount,webDriver,"Assert successfull workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowFailedExecutionCount,webDriver,"Assert failed workflow execution count after executing workflows");
	}

	@Test(priority = 3,dependsOnMethods = {"verifyMonitorDashboardPageTest"},groups = {"MonitorDashboard"},description = "Workflows/Flowservices executions counts")
	public void verifyMonitorDashboardCountTest()
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.totalExecutionCountNumber,webDriver,"Assert total workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowSuccessExecutionCountNumber,webDriver,"Assert successfull workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowFailedExecutionCountNumber,webDriver,"Assert failed workflow execution count after executing workflows");
	}

	@Test(priority = 4,dependsOnMethods = {"verifyMonitorDashboardCountTest"},groups = {"MonitorDashboard"},description = "Workflow executions graph data")
	public void verifyMonitorDashboardBarGraphTest() 
	{
		//Test Steps
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorChart,webDriver,"Assert workflow execution chart");
	}

	@Test(priority = 5,dependsOnMethods = {"verifyMonitorDashboardBarGraphTest"},groups = {"MonitorDashboard"},description = "Workflow executions data")
	public void logsMonitoreDashboardDayWise()
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.dayOneOption,webDriver,"Select 1 Day option");
		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorChart,webDriver,"Assert 1 Day workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekOneOption,webDriver,"Select 1 Week option");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down");  
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorChart,webDriver,"Assert 1 Week workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekTwoOption,webDriver,"Select 2 Weeks option");
		JavascriptExecutor javascriptExecutorThree = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorThree,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorChart,webDriver,"Assert 2 Weeks workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekThreeOption,webDriver,"Select 3 Weeks option");
		JavascriptExecutor javascriptExecutorFour = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorFour,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorChart,webDriver,"Assert 3 Weeks workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekFourOption,webDriver,"Select 4 Weeks option");
		JavascriptExecutor javascriptExecutorFive = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorFive,"Scroll page down");  
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.monitorChart,webDriver,"Assert 4 Weeks workflow execution chart");
	}

	@Test(priority = 6,dependsOnMethods = {"logsMonitoreDashboardDayWise"},groups = {"MonitorDashboard"},description = "Workflow executions pie chart data")
	public void pieMonitorDashboardChartTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionLinkText,webDriver,"Click on Workflow Executions text");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.pieChart,webDriver,"Assert data on pie chart after workflow executions");
	}

	@Test(priority = 7,dependsOnMethods = {"pieMonitorDashboardChartTest"},groups = {"MonitorDashboard"},description = "Workflow executions logs table")
	public void logsMonitorDashboardTableTest() 
	{
		//Test Steps
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions");
	}

	@Test(priority = 8,dependsOnMethods = {"logsMonitorDashboardTableTest"},groups = {"MonitorDashboard"},description = "Workflow executions logs table")
	public void logsMonitorDashboardTableDayWiseTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.dayOneOption,webDriver,"Select 1 Day option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 day)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekOneOption,webDriver,"Select 1 Week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 Week)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekTwoOption,webDriver,"Select 2 Weeks option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (2 Weeks)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekThreeOption,webDriver,"Select 3 Weeks option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (3 Weeks)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekFourOption,webDriver,"Select 4 Weeks option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (4 Weeks)");
	}

	@Test(priority = 9,dependsOnMethods = {"logsMonitorDashboardTableDayWiseTest"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditMonitorDashboardLogsData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.generalText,webDriver,"Click on general link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsLink,webDriver,"Click on audit logs link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert current date audit logs data");
	}

	@Test(priority = 10,dependsOnMethods = {"auditMonitorDashboardLogsData"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardDataDayWise() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.dayOneOption,webDriver,"Select 1 Day option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert audit logs data 1 Day");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekOneOption,webDriver,"Select 1 Week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert audit logs data 1 Week");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekTwoOption,webDriver,"Select 2 Week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert audit logs data 2 Week");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekThreeOption,webDriver,"Select 3 week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert audit logs data 3 Week");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.weekFourOption,webDriver,"Select 4 week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert audit logs data 4 week");
	}

	@Test(priority = 11,dependsOnMethods = {"auditLogsMonitorDashboardDataDayWise"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardProjectSearchFilter() 
	{
		//Test Steps
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,PropertiesData.readInputData("projectQuery"),webDriver,"Enter project search query");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.querySearchButton,webDriver,"Click on search button to search project audit logs entry");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert project audit logs after search via query");
	}

	@Test(priority = 12,dependsOnMethods = {"auditLogsMonitorDashboardProjectSearchFilter"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardWorkflowSearchFilter()
	{
		//Test Steps
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,webDriver,"Clear search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,PropertiesData.readInputData("workflowQuery"),webDriver,"Enter workflow search query");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.querySearchButton,webDriver,"Click on search button to search workflow audit logs entry");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert workflow audit logs after search via query");
	}

	@Test(priority = 13,dependsOnMethods = {"auditLogsMonitorDashboardWorkflowSearchFilter"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorWorkflowAlertRuleSearchFilter()
	{
		//Test Steps
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,webDriver,"Clear search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,PropertiesData.readInputData("alertRulesQuery"),webDriver,"Enter workflow alert rule search query");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.querySearchButton,webDriver,"Click on search button to search workflow alert rule audit logs entry");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert workflow alert rule audit logs after search via query");
	}

	@Test(priority = 14,dependsOnMethods = {"auditLogsMonitorWorkflowAlertRuleSearchFilter"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorTenantRoleSearchFilter() 
	{
		//Test Steps
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,webDriver,"Clear search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,PropertiesData.readInputData("tenantRoleQuery"),webDriver,"Enter tenant role search query");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.querySearchButton,webDriver,"Click on search button to search tenant role audit logs entry");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert tenant role audit logs after search via query");
	}

	@Test(priority = 15,dependsOnMethods = {"auditLogsMonitorTenantRoleSearchFilter"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorAccountSearchFilter() 
	{
		//Test Steps
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,webDriver,"Clear search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsQuerySearchBox,PropertiesData.readInputData("accountLoginQuery"),webDriver,"Enter account search query");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.querySearchButton,webDriver,"Click on search button to search account audit logs entry");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.auditLogsTable,webDriver,"Assert account audit logs after search via query");
	}

	@Test(priority = 16,dependsOnMethods = {"auditLogsMonitorAccountSearchFilter"},groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardDownload() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.downloadAuditLogsButton,webDriver,"Click on Download logs button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.downloadCSVButton,webDriver,"Click on Download CSV logs button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.downloadAuditLogsButton,webDriver,"Click on Download logs button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.downloadJSONButton,webDriver,"Click on Download CSV logs button");
	}

	@Test(priority = 17,dependsOnMethods = {"auditLogsMonitorDashboardDownload"},groups = {"MonitorDashboard"},description = "Assert audit logs json downloaded")
	public void auditLogsMonitorDashboardDownloadedFile() 
	{
		if(MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT.contains("headless"))
		{
			ExtentTestManager.getTest().skip("DOWNLOAD NOT SUPPORTED IN HEADLESS MODE");
		}
		else
		{
			//Test Steps
			FileUtilitiesHelperFunctions.fileExists(FilePathConstants.TEST_DOWNLOADED_FILES_FOLDER_PATH,"Assert audit logs json file downloaded");
		}
	}

	@Test(priority = 18,dependsOnMethods = {"auditLogsMonitorDashboardDownloadedFile"},groups = {"MonitorDashboard"},description = "Transaction usages data")
	public void usagesDataMonitorDashboard()  
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.usagesLink,webDriver,"Click on usages link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.transactionUsagesTable,webDriver,"Assert Transactions data shown");
	}

	@Test(priority = 19,dependsOnMethods = {"usagesDataMonitorDashboard"},groups = {"MonitorDashboard"},description = "Create new workflow alert rule")
	public void createNewWorkflowAlertRuleMonitorDashboard() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.alertRuleText,webDriver,"Click on Alert Rules option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.alertRuleLink,webDriver, "Click on Workflow Alerts link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.noAlertRule,webDriver,"Wait for workflow alert rules modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.alertNewRuleButton,webDriver,"Click on Add New alert rule button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.alertNewRuleName,PropertiesData.readInputData("alertRuleName"),webDriver,"Enter alert rule name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab key");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab key");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab key");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE,"Select workflows");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.failedStatus,webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.timeoutStatus,webDriver,"Select Timeout status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.successStatus,webDriver,"Select Success status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.alertRuleUserBoxDropDown,webDriver,"Click on user name dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.emailID,webDriver,"Select email id");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.alertRuleUserBoxDropDown,webDriver,"Click on user name dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.saveAlertRulesButton,webDriver,"Click on save button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.createNewAlertRuleMessage,PropertiesData.readAssertionData("alertRuleSaveMessage"),webDriver,"Assert alert rule creation message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.createdAlertRule,webDriver,"Assert alert rule created");
	}

	@Test(priority = 20,dependsOnMethods = {"createNewWorkflowAlertRuleMonitorDashboard"},groups = {"MonitorDashboard"},description = "Edit workflow alert rule")
	public void editWorkflowAlertRuleMonitorDashboard() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.editAlertRulePencil,webDriver,"Click on pencil icon to edit alert rules");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.editAlertRuleModal,webDriver,"Wait for edit modal to load");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.saveAlertRulesButton,webDriver,"Click on update button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.editAlertRuleMessage,PropertiesData.readAssertionData("alertRuleUpdateMessage"),webDriver,"Assert alert rule edit message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.createdAlertRule,webDriver,"Assert alert rule created");
	}

	@Test(priority = 21,dependsOnMethods = {"editWorkflowAlertRuleMonitorDashboard"},groups = {"MonitorDashboard"},description = "Delete workflow alert rule")
	public void deleteWorkflowAlertRuleMonitorDashboard() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.deleteAlertRulePencil,webDriver,"Click on delete link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.deleteAlertRuleButton,webDriver,"Click on delete button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.deleteAlertRuleMessage,PropertiesData.readAssertionData("alertRuleDeleteMessage"),webDriver,"Assert alert rule delete message",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboardTests.MonitorDashboardTestLocators.noAlertRuleMessage,webDriver,"Assert alert rule deleted");
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