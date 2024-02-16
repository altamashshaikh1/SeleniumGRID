package com.webMethods.io.Integration.MeteringInsightsTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class MeteringInsight 
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

	@Test(priority = 0,groups = {"MeteringAndMonitoringInsights"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"MeteringAndMonitoringInsights"},description = "Visit monitor page")
	public void overviewTest()
	{
		//Verify Insights tab
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.monitorLinktext,webDriver,"Click on Monitor link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.InsightsLinktext, webDriver,"Verify Insights is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.InsightsLinktext,webDriver,"Click on Insights link");

		//Verify Overview tab
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.OverviewLinktext, webDriver,"Verify Overview is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.OverviewLinktext,webDriver,"Click on Overview link");

		//Overview data page Elements visible
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.DataCards, webDriver,"Verify data cards Elements visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Daysdata, webDriver,"Verify 7 days data card is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Monthsdata, webDriver,"Verify Months data card is visible");

		//Overview Time page Elements Visible
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.InsightsTimeData, webDriver,"Verify Insights for time range Elements visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.TimeRange, webDriver,"Verify Insights for time range is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Message, webDriver,"Verify Insights for time range Message visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Refreshbutton,webDriver,"Click on Refresh");


		//Timeline graph
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinetitle,webDriver,"Wait for timeline Timelinetitle");

		//Share graph
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas,webDriver,"Wait for share canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharetitle,webDriver,"Wait for Share title");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.TotalTransactions,webDriver,"Wait for total transactions");

		//Date validation- 1week
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekOneOption,webDriver,"Select 1 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas,webDriver,"Wait for share canvas to load");

		//Date Validation - 2 weeks 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekTwoOption,webDriver,"Select 2 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas,webDriver,"Wait for share canvas to load");

		//Date Validation - 3 weeks 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekThreeOption,webDriver,"Select 3 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas,webDriver,"Wait for share canvas to load");

		//Date Validation - 4 weeks 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekFourOption,webDriver,"Select 4 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas,webDriver,"Wait for share canvas to load");
	}

	@Test(priority = 2 ,dependsOnMethods = {"overviewTest"},groups = {"MeteringAndMonitoringInsights"},description = "Visit Analytics page")
	public void analyticsTest()   
	{
		//Verify Overview tab
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.AnalyticsLinktext, webDriver,"Verify Analytics is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.AnalyticsLinktext,webDriver,"Click on Analytics link");

		//Overview Time page Elements Visible
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.InsightsTimeData, webDriver,"Verify Insights for time range Elements visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.TimeRange, webDriver,"Verify Insights for time range is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Message, webDriver,"Verify Insights for time range Message visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Refreshbutton,webDriver,"Click on Refresh");

		//Timeline graph
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinetitle,webDriver,"Wait for timeline Timelinetitle");

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharetitle,webDriver,"Wait for Share title");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.TotalTransactions,webDriver,"Wait for total transactions");

		//Date validation- 1week
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekOneOption,webDriver,"Select 1 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");

		//Date Validation - 2 weeks 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekTwoOption,webDriver,"Select 2 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");

		//Date Validation - 3 weeks 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekThreeOption,webDriver,"Select 3 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");

		//Date Validation - 4 weeks 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.weekFourOption,webDriver,"Select 4 Week option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");

		//Filter for services
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Filters, webDriver,"Verify Filters is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Filters,webDriver,"Click on filters link");

		//Service Type
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.ServiceType, webDriver,"Verify Service type is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectServiceType,webDriver,"Click on Service type link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectWorkflow,webDriver,"Click on workflow");

		//Project
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Project, webDriver,"Verify Project is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectProject,webDriver,"Click on Service type link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectDefault,webDriver,"Click on Project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.ApplyButton,webDriver,"Click on Apply");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.ResetButton,webDriver,"Click on Reset");

		//Filters for Projects
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Projects,webDriver,"Click on Projects");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Filters, webDriver,"Verify Filters is visible");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Filters,webDriver,"Click on filters link");

		//Service Type
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.ServiceType, webDriver,"Verify Service type is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectServiceType,webDriver,"Click on Service type link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectWorkflow,webDriver,"Click on workflow");

		//Project
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Project, webDriver,"Verify Project is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectProject,webDriver,"Click on Service type link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.SelectDefault,webDriver,"Click on Project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.ApplyButton,webDriver,"Click on Apply");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.ResetButton,webDriver,"Click on Reset");

		//Pagination
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Pagination, webDriver,"Verify Pagination is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Paginationnum, webDriver,"Verify Pagination number is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Paginationnum,webDriver,"Click on Pagination number");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.selectnum,webDriver,"Click on Pagination number");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Timelinecanvas2,webDriver,"Wait for timeline canvas to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MeteringInsightsTests.MeteringInsightsLocators.Sharecanvas2,webDriver,"Wait for share canvas to load");
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