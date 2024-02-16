package com.webMethods.io.Integration.ProjectVariablesTests;

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

public class ProjectVariables 
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

	@Test(priority = 0,groups = {"ProjectVariables"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ProjectVariables"},description="Verify Project variable creation")
	public void VariableCreation() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName3"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.myUIautomationproject1, webDriver, "Click on searched project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Configurationtab, webDriver, "Click on Configuration page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Variablestab, webDriver, "Click on Variables");

		//Validate the variables page
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Variables, webDriver,"Verify Variables is visible");

		//Variable creation
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newvariablebutton1, webDriver, "Click on Create Variables");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.NewVariable, webDriver,"Verify NewVariables is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,"SalesforceUsername", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,"vidya.js@softwareag.com", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.CreateVariable,webDriver,"click on name textbox"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Msg_variablecreated,webDriver,"Wait for variable created message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newvariablebutton, webDriver, "Click on Create Variables");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.NewVariable, webDriver,"Verify NewVariables is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,"SalesforcePassword", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,"Pwd@0178N0CMWVkhNrlhqxvo4ZvCFvZJC", webDriver,"Select Failed status");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.CreateVariable,webDriver,"click on name textbox"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Msg_variablecreated,webDriver,"Wait for variable created message");

		//Variable creation
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newvariablebutton, webDriver, "Click on Create Variables");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.NewVariable, webDriver,"Verify NewVariables is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.toggleText, webDriver,"Verify toggleText is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.toggleselect,webDriver,"Toggle on"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,"SalesforceUsernamegbl", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,"vidya.js@softwareag.com", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.AddVariable,webDriver,"click on name textbox"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Msggbl_variablecreated,webDriver,"Wait for variable created message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newvariablebutton, webDriver, "Click on Create Variables");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.NewVariable, webDriver,"Verify NewVariables is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.toggleText, webDriver,"Verify toggleText is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,"SalesforcePasswordgbl", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.toggleselect,webDriver,"Toggle on"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterValue,"Pwd@0178N0CMWVkhNrlhqxvo4ZvCFvZJC", webDriver,"Select Failed status");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.AddVariable,webDriver,"click on name textbox"); 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Msggbl_variablecreated,webDriver,"Wait for variable created message");

		//Add to account
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Connectors,webDriver,"Wait for connector to visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Connectors,webDriver,"Click on connectors page");
		//SeleniumWaitUtils.waitForElementNotVisible(com.wmio.Integration.triggerspredefinedconnectorTestCases.PredefinedConnectorsLocators.Predefinedloader, webDriver, "Wait till loader to disappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.projectSearchTextBox2, webDriver, "Wait for the search to be visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.projectSearchTextBox2, webDriver, "Wait for the search to be clickable");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.projectSearchTextBox2,"Salesforce",webDriver,"input Salesforce to search");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Salesforceaccount,webDriver,"Wait for salesforce account to load");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Salesforceaccount,webDriver,"Mouse over on searched workflow");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newaccount, webDriver, "Wait for the search to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newaccount,webDriver,"Click on new salesforce account");

		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Addaccount, webDriver, "Wait for the search to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.selectauth,webDriver,"Click on new salesforce account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Credentials,webDriver,"Click on credential");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Next,webDriver,"Click on credential");

		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.InputUsername, webDriver, "Wait for the search to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.InputUsername,"%SalesforceUsernamegbl%",webDriver,"input slaesforce name to search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.globalusername,webDriver,"Click on credential");

		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.InputPassword, webDriver, "Wait for the search to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.InputPassword,"%SalesforcePasswordgbl%",webDriver,"input slaesforce password to search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.globalpassword,webDriver,"Click on credential");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Add,webDriver,"Click on Add");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Msg_Accountadded,webDriver,"Wait for Account successfully saved message");
		Thread.sleep(1000);

		//Execution with global variables
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Integration,webDriver,"Click on Integration");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.addWorkflowIcon,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.actionSearchTextBox,PropertiesData.readInputData("searchAction1"),webDriver,"Search logger action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.actionSearched,webDriver,"Drag and drop logger action on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.ActionId,webDriver,"Assert logger action visible on canvas");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.ActionId,webDriver,"Mouseover on logger action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.firstActionConnector,com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.stopAction,webDriver,"Connect logger action with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.ActionId,webDriver,"Double click on logger action");
	}

	/* //Importing referance from vault
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Newvariablebutton, webDriver, "Click on Create Variables");	
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.NewVariable, webDriver,"Verify NewVariables is visible");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.NewVariable, webDriver,"Click on NewVariable");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.toggleText, webDriver,"Verify toggleText is visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.toggleselect,webDriver,"Toggle on"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,webDriver,"click on name testbox"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.EnterName,"globalv", webDriver,"Select Failed status");
	}
		//Delete Variables
	/*	SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Configurationtab, webDriver, "Click on Configuration page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Variablestab, webDriver, "Click on Variables");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Deleteusername, webDriver, "Delete username");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.ConfirmDelete, webDriver, "confirm Delete username");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.Deletepassword, webDriver, "Delete password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectVariablesTests.ProjectVariablesLocators.ConfirmDelete, webDriver, "confirm Delete username");
		Thread.sleep(1000);
	 */

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