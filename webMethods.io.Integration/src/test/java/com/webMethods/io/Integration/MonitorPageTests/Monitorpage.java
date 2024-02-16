package com.webMethods.io.Integration.MonitorPageTests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class Monitorpage 
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static SoftAssertWrapper softAssert;

	private String workflowWebhookURL;

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

	@Test(priority = 0,groups = {"MonitorPage"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"MonitorPage"},description="Verify MonitorTest test")
	public void MonitorTest() throws InterruptedException  
	{
		//Monitor Page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Monitor,webDriver,"Wait for Monitor dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Monitor, webDriver, "Click on Monitor");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.loadingindicator, webDriver, "Wait for loader to disappear");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Workflowexecution, webDriver, "Click on workflow execution");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Filters,webDriver,"Check for filters for workflow executions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Filters, webDriver, "Click on filters drop down box");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Filtersorder.get(0),"Execution Status",webDriver,"Check for execution status",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Filtersorder.get(1),"Projects",webDriver,"Check for projects tab",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Filtersorder.get(2),"Workflows",webDriver,"Check for workflows tab",softAssert);
	}

	@Test(priority = 2,dependsOnMethods = {"MonitorTest"},groups = {"MonitorPage"},description="Verify overview test")
	public void OverviewTest()
	{	
		String tempDropDownStatus = com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.tempDropDownStatus.getAttribute("class");

		//SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.loadingindicator, webDriver, "Wait for loader to disappear");

		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.loadingindicator, webDriver, "Wait for loader to disappear");


		if(tempDropDownStatus.equalsIgnoreCase("toggle-arrows delite-icon dlt-icon-chevron-up"))
		{
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Overview,webDriver,"Check for workflow execution on overview tab expandable");
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.SuccessfullExecutions,webDriver,"Check for successfull executions on overview tab");
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.FailedExecutions,webDriver,"Check for Failed Executions executions on overview tab");
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.RunningExecutions,webDriver,"Check for Running executions on overview tab");
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.PendingExecutions,webDriver,"Check for Pending executions on overview tab");
		}
		else
		{
			com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.upDropDown.click();
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Overview,webDriver,"Check for workflow execution on overview tab collapsible");
		}
		com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.upDropDown.click();
	}

	@Test(priority = 3,dependsOnMethods = {"OverviewTest"},groups = {"MonitorPage"},description="Verify Restart test")
	public void RestartTest() 
	{
		workflowWebhookURL = PropertiesData.readInputData("webhookURL1");
		HttpURLConnHelperFuntions.executeWorkflowHavingICAssets(workflowWebhookURL,MavenArgumentConstants.SOURCE_TENANT_USERNAME,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD,"Execute workflow having restart/resume)");
	}

	@Test(priority = 4,dependsOnMethods = {"OverviewTest"},groups = {"MonitorPage"},description="Verify restart execution test")
	public void RestartexecutionTest() throws InterruptedException  
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Selectexecutionstatus,webDriver,"click on the drop down to vissible"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.actionsearchfield,"Failed", webDriver,"Select Failed status");
		webDriver.findElement(By.xpath("//label[text()='Failed']")).isSelected();	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.SelectFailed,webDriver,"select status via check box");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Selectprojects ,webDriver,"click on the drop down to vissible "); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.actionsearchfield1,"VarshithaUIAutomation", webDriver,"Input project name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.selectprojectdropdown,webDriver,"Assert project visible");
		webDriver.findElement(By.xpath("//label[text()='VarshithaUIAutomation']")).isSelected();		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.SelectProject,webDriver,"select project via check box");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.SelectWorkflows ,webDriver,"click on the drop down to vissible "); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.actionsearchfield2,"RestartFail", webDriver,"Input project name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.selectworkflowdropdown,webDriver,"Assert workflow visible");
		webDriver.findElement(By.xpath("//label[text()='RestartFail']")).isSelected();	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Selectworkflow,webDriver,"check box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.SelectApply,webDriver,"Click Apply");

		//Check Restart button is visible
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Restartcheckbox,webDriver,"Check for filter tab");

		//check restart button is visible
		List<WebElement> Element = com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.getindividualcheckbox("RestartFail",webDriver);
		SeleniumWebElementsUtils.click(Element.get(0),webDriver,"Selecting the checkbox");
		SeleniumWebElementsUtils.click(Element.get(1),webDriver,"Selecting the checkbox");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Restartbutton,webDriver,"Check for restart button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Closebutton,webDriver,"Check for close button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Restartbutton,webDriver,"Select restart");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.OKbutton,webDriver,"Click OK");

		//check for notification 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.notificationMessage1,webDriver,"Validate the notification1");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.notificationMessage2,webDriver,"Validate the notification2");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Refreshbutton,webDriver,"refresh page");
	}

	@Test(priority = 5,dependsOnMethods = {"RestartexecutionTest"},groups = {"MonitorPage"},description="Verify restarted lable for failed executions test")
	public void RestartlableTest() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.Restartlable,webDriver,"Check for Restart lable");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MonitorPageTests.MonitorPageLocators.RestartandResumebutton,webDriver,"Check for Restart lable");
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