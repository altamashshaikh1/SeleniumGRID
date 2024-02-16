package com.webMethods.io.Integration.MonitorDashboard.BVT;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test; 

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
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal,BaseTestUtils.getGridURL(), capabilities);

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
	public void monitorDashboardLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,groups = {"MonitorDashboard"},description = "Visit monitor page")
	public void visitMonitorTest()  
	{
		//Visit monitor page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.projectSearchTextBox,PropertiesData.readInputData("monitorDefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.defaultProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorLinktext,webDriver,"Click on Monitor link");
	}

	@Test(priority = 2,groups = {"MonitorDashboard"},description = "All workflow executions data table")
	public void verifyMonitorDashboardPageTest() throws InterruptedException
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionCount,webDriver,"Assert total workflow execution count after executing workflows");
		Thread.sleep(5000);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowSuccessExecutionCount,webDriver,"Assert successfull workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowFailedExecutionCount,webDriver,"Assert failed workflow execution count after executing workflows");
	}

	@Test(priority = 3,groups = {"MonitorDashboard"},description = "Workflows/Flowservices executions counts")
	public void verifyMonitorDashboardCountTest()
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.totalExecutionCountNumber,webDriver,"Assert total workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowSuccessExecutionCountNumber,webDriver,"Assert successfull workflow execution count after executing workflows");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowFailedExecutionCountNumber,webDriver,"Assert failed workflow execution count after executing workflows");
	}

	@Test(priority = 4,groups = {"MonitorDashboard"},description = "Workflow executions graph data")
	public void verifyMonitorDashboardBarGraphTest() 
	{
		//Test Steps
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"PageScrolldown"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorChart,webDriver,"Assert workflow execution chart");
	}

	@Test(priority = 5,groups = {"MonitorDashboard"},description = "Workflow executions data")
	public void logsMonitoreDashboardDayWise()
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.dayOneOption,webDriver,"Select 1 Day option");
		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorChart,webDriver,"Assert 1 Day workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekOneOption,webDriver,"Select 1 Week option");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down");  
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorChart,webDriver,"Assert 1 Week workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekTwoOption,webDriver,"Select 2 Weeks option");
		JavascriptExecutor javascriptExecutorThree = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorThree,"Scroll page down");  
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorChart,webDriver,"Assert 2 Weeks workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekThreeOption,webDriver,"Select 3 Weeks option");
		JavascriptExecutor javascriptExecutorFour = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorFour,"Scroll page down");  
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorChart,webDriver,"Assert 3 Weeks workflow execution chart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekFourOption,webDriver,"Select 4 Weeks option");
		JavascriptExecutor javascriptExecutorFive = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorFive,"Scroll page down");  
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.monitorChart,webDriver,"Assert 4 Weeks workflow execution chart");
	}

	@Test(priority = 6,groups = {"MonitorDashboard"},description = "Workflow executions pie chart data")
	public void pieMonitorDashboardChartTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionLinkText,webDriver,"Click on Workflow Executions text");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.pieChart,webDriver,"Assert data on pie chart after workflow executions");
	}

	@Test(priority = 7,groups = {"MonitorDashboard"},description = "Workflow executions logs table")
	public void logsMonitorDashboardTableTest() 
	{
		//Test Steps
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"PageScrolldown"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions");
	}

	@Test(priority = 8,groups = {"MonitorDashboard"},description = "Workflow executions logs table")
	public void logsMonitorDashboardTableDayWiseTest() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.dayOneOption,webDriver,"Select 1 Day option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 day)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekOneOption,webDriver,"Select 1 Week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 Week)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekTwoOption,webDriver,"Select 2 Weeks option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (2 Weeks)");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekThreeOption,webDriver,"Select 3 Weeks option");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (3 Weeks)");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.weekFourOption,webDriver,"Select 4 Weeks option");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (4 Weeks)");
	}

	@Test(priority = 9,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditMonitorDashboardLogsData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on general link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on audit logs link");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.auditLogsTable,webDriver,"Assert current date audit logs data");
	}

	@Test(priority = 10,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardDataDayWise() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Select 1 Day option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Select 1 Week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Select 2 Week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Select 3 week option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Select 4 week option");
	}

	@Test(priority = 11,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardProjectSearchFilter()  
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Enter project search query");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on search button to search project audit logs entry");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert project audit logs after search via query");
	}

	@Test(priority = 12,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardWorkflowSearchFilter() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Clear search box");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Enter workflow search query");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on search button to search workflow audit logs entry");
	}

	@Test(priority = 13,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorWorkflowAlertRuleSearchFilter() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Clear search box");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Enter workflow alert rule search query");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on search button to search workflow alert rule audit logs entry");
	}

	@Test(priority = 14,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorTenantRoleSearchFilter()  
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Clear search box");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Enter tenant role search query");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on search button to search tenant role audit logs entry");
	}

	@Test(priority = 15,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorAccountSearchFilter()  
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Clear search box");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Enter account search query");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on search button to search account audit logs entry");
	}

	@Test(priority = 16,groups = {"MonitorDashboard"},description = "Audit logs")
	public void auditLogsMonitorDashboardDownload() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on Download logs button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on Download CSV logs button");
	}

	@Test(priority = 17,groups = {"MonitorDashboard"},description = "Assert audit logs json downloaded")
	public void auditLogsMonitorDashboardDownloadedFile() 
	{
		if(MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT.contains("headless"))
		{
			ExtentTestManager.getTest().skip("DOWNLOAD NOT SUPPORTED IN HEADLESS MODE");
		}
		else
		{
			FileUtilitiesHelperFunctions.fileExists(FilePathConstants.TEST_DOWNLOADED_FILES_FOLDER_PATH,"Assert audit logs json file downloaded");
		}
	}

	@Test(priority = 18,groups = {"MonitorDashboard"},description = "Transaction usages data")
	public void usagesDataMonitorDashboard()  
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Click on usages link");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.workflowExecutionTable,webDriver,"Assert Transactions data shown");
	}

	@Test(priority = 19,groups = {"MonitorDashboard"},description = "Create new workflow alert rule")
	public void createNewWorkflowAlertRuleMonitorDashboard()  
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleText,webDriver,"Click on Alert Rules option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver, "Click on Workflow Alerts link");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Wait for workflow alert rules modal to load");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Click on Add New alert rule button");
		//SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab key");
		//SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab key");
		//SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press tab key");
		//SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE,"Select workflows");
		//JavascriptExecutor javascriptExecutor = null;
		//SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"PageScrolldown"); 
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Select Failed status");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Select Timeout status");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Select Success status");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Click on save button");
	}

	@Test(priority = 20,groups = {"MonitorDashboard"},description = "Edit workflow alert rule")
	public void editWorkflowAlertRuleMonitorDashboard()  
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Click on pencil icon to edit alert rules");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Wait for edit modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Click on update button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Assert alert rule created");
	}

	@Test(priority = 21,groups = {"MonitorDashboard"},description = "Delete workflow alert rule")
	public void deleteWorkflowAlertRuleMonitorDashboard()  
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Click on delete link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Click on delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorDashboard.BVT.MonitorDashboardLocators.alertRuleLink,webDriver,"Assert alert rule deleted");
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
