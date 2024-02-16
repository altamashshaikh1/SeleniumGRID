package com.webMethods.io.Integration.TransactionsCountTests;

import org.openqa.selenium.By;
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
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
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

public class TransactionCount 
{
	public static String trncntworkflowWebhookURL;

	public static String userName;

	public static String userPassword;

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

	@Test(priority = 0,groups ={"TransactionCount"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups ={"TransactionCount"},description="Check Transaction count entry in settings modal")
	public void transactionCountFieldSetting() throws InterruptedException 
	{
		//Visit monitor page
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.monitorLinktext,webDriver,"Click on Monitor link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowExecutionCount,webDriver,"Assert total workflow execution count after executing workflows");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowExecutionLinkText,webDriver,"Click on Workflow Executions text");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.pieChart,webDriver,"Assert data on pie chart after workflow executions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.dayOneOption,webDriver,"Select 1 Day option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 day)");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.monitorpageSettingIcon, webDriver, "Click on Monitor page table column Setting icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.settingtablecontent,webDriver,"Wait to load Transaction count entry from list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.settingtablecontent,webDriver,"Assert Tranasction count field");

		//TO verify Maintain Execution logs checkbox status
		if(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.transactioncountcheckbox.isSelected()== true)
		{
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.saveButton, webDriver, "Click on Save Button");
			SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.txcntcolumnintable,webDriver,"Assert Tranasction Count Column in Monitor page table");
		}
		else
		{
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
			Thread.sleep(2000);
			SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Press enter to search project");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.saveButton, webDriver, "Click on Save Button");
			SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
			SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.txcntcolumnintable,webDriver,"Assert Tranasction Count Column in Monitor page table");
		}
	}

	@Test(priority = 2,dependsOnMethods = {"transactionCountFieldSetting"},groups ={"TransactionCount"},description="Visit monitor page")
	public void transactioncountvisibility() throws InterruptedException 
	{		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.Filters, webDriver, "Click on filters drop down box");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.Filtersorder.get(0),"Execution Status",webDriver,"Check for execution status",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.Filtersorder.get(1),"Projects",webDriver,"Check for projects tab",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.Filtersorder.get(2),"Workflows",webDriver,"Check for workflows tab",softAssert);

		//Select Success status
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.Selectexecutionstatus,webDriver,"click on the drop down to vissible"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.actionsearchfield,"Success", webDriver,"Select Failed status");
		webDriver.findElement(By.xpath("//label[text()='Success']")).isSelected();	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.SelectSuccess,webDriver,"select status via check box");

		//select project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.Selectprojects ,webDriver,"click on the drop down to vissible "); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.actionsearchfield1,"Auto_UIAPI_TransactionCount", webDriver,"Input project name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.SelectProject,webDriver,"Assert project visible");
		webDriver.findElement(By.xpath("//label[text()='Auto_UIAPI_TransactionCount']")).isSelected();		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.SelectProject,webDriver,"select project via check box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.SelectApply,webDriver,"Click Apply");

		trncntworkflowWebhookURL = PropertiesData.readInputData("transactionCountWebhookURL");
		userName = MavenArgumentConstants.SOURCE_TENANT_USERNAME;
		userPassword = MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD;
		HttpURLConnHelperFuntions.executeWorkflowHavingICAssets(trncntworkflowWebhookURL, userName, userPassword,"Execute workflow having child workflows");

		//Refresh option
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.refreshOption,webDriver,"Click on Refresh option to fetch latest execution logs");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 day)");
		//scrollPageToElement(driver, com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.txcntoptionintable, "Scroll Down to Transaction Count column in table");

		//assert transaction count of 1st record of workflow a,b and c
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowCtxcnt,webDriver,"Assert workflow C Execution");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowCinfomessage,webDriver,"Workflow C info message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowBtxcnt,webDriver,"Assert workflow B Execution");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowAtxcnt,webDriver,"Assert workflow A Execution");

		//Go inside Workflow C details logs and check transaction count
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowC1stentry, webDriver, "Click on Workflow C 1st entry");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowdetailslogsinfosec,webDriver,"Assert workflow logs info section");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.txcntofchildwfindetaillogs,webDriver,"Assert '-' as transaction count for workflow C in Details logs section");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.detaillogsclosedopt, webDriver, "Click on Closed icon of details logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 day)");

		//Go inside Workflow A details logs and check transaction count
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowA1stentry, webDriver, "Click on Workflow A 1st entry");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowdetailslogsinfosec,webDriver,"Assert workflow logs info section");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.txcntofwfindetaillogs,webDriver,"Assert '1' as transaction count for workflow A in Details logs section");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.detaillogsclosedopt, webDriver, "Click on Closed icon of details logs");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.workflowExecutionTable,webDriver,"Assert workflow execution table after executions (1 day)");

		//Deselect the transaction count from setting
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.monitorpageSettingIcon, webDriver, "Click on Monitor page table column Setting icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.settingtablecontent,webDriver,"Wait to load Transaction count entry from list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.settingtablecontent,webDriver,"Assert Tranasction count field");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Press enter to search project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.saveButton, webDriver, "Click on Save Button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.elementnotvisible(com.webMethods.io.Integration.TransactionsCountTests.TransactionCountLocators.txcntcolumnintable,webDriver,"Assert Tranasction Count Column in Monitor page tableis disappear");	
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