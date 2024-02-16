package com.webMethods.io.Integration.PreDefinedConnTests;

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

public class PredefinedConnectors
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
	public void softAssertInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"Predefined Connectors"},description = "Login user")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.loginBlock, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Predefined Connectors"},testName = "Predefined connector test",description=" Verify Predefined connector page Test")
	public void SearchworkflowTest() throws InterruptedException 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName"),webDriver,"Input project name"); 
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Click on search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.myUIautomationproject1, webDriver, "Wait till workflow Search box is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.myUIautomationproject1, webDriver, "Click on workflow search box");

		//Search workflow
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2,"PredefinedConnector",webDriver,"Input workflow name");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.accnamevalidatorcircle1,webDriver,softAssert,"Assert connector visible on canvas");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.PredefinedConnector,webDriver,softAssert,"Assert connector visible on canvas");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.PredefinedConnector,webDriver,"Clicking on Workflow tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.editWorkflow1,webDriver,"Editing Workflow ");

		//Add trigger
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.triggerSearchTextBox,PropertiesData.readInputData("trelloTriggerName"),webDriver,"Search Trello trigger"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.TrelloTriggerObject,webDriver,"Select Trello trigger after search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.triggerNextButton,webDriver,"Click on Next button after selecting trello trigger");

		//select trello
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.triggerAuthDropdown,webDriver,"click on the drop down to select trello account"); 		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Trello1, webDriver, "Click on trello_1 from dropdown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.triggerSaveButton, webDriver, "click on save");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.triggerskipButton, webDriver, "Click on skip");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.DoneButton,webDriver,"Click ons Done button");
		Thread.sleep(1000);

		//save workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.workflowSave,webDriver,"click on Save workflow");

		//Leave workflow
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Connectors, webDriver,softAssert, "Verify Configurations tab get display in list");

		//Verify Connectors predefined page
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Connectors,webDriver,"Click on connectors page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Wait for the search to be visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Wait for the search to be clickable");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2,"Trello",webDriver,"input trello to search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Downarrow,webDriver,"click on downarrow to expand trello acount to check trello trigger used");

		//Verify trigger
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Trello_1, webDriver, "Wait for the trello trigger to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Integrations1,webDriver,"Click on integration tab");

		//Search workflow
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2,"PredefinedConnector",webDriver,"Input workflow name");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.accnamevalidatorcircle1,webDriver,softAssert,"Assert connector visible on canvas");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.PredefinedConnector,webDriver,softAssert,"Assert connector visible on canvas");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.PredefinedConnector,webDriver,"Clicking on Workflow tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.editWorkflow1,webDriver,"Editing Workflow ");

		//Delete trigger
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.startAction,webDriver,"Double click on start action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.DeleteTrigger,webDriver,"click on delete Workflow ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Yes,webDriver,"click on yes to confirm deletion");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.triggerDeleteMessage,PropertiesData.readAssertionData("triggerDeleteMessage"),webDriver,"Assert trigger delete message",softAssert);
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.webhookOption,webDriver,softAssert,"Assert trigger deleted");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Cancel,webDriver,"close the add trigger page");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.startAction,webDriver,softAssert,"Assert trigger deleted");

		//save workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.workflowSave,webDriver,"click on Save workflow");

		//Leave workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.elementvisibleWithVerification(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Connectors, webDriver,softAssert, "Verify Configurations tab get display in list");

		//Verify Connectors predefined page
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Connectors,webDriver,"Click on connectors page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Wait for the search to be visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2, webDriver, "Wait for the search to be clickable");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.projectSearchTextBox2,"Trello",webDriver,"input trello to search");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Downarrow,webDriver,"click on downarrow to expand trello acount to check trello trigger used");

		//Verify trigger
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Trello_2, webDriver, "Wait for the trello trigger to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PreDefinedConnTests.PredefinedConnectorsLocators.Integrations1,webDriver,"Click on integration tab");
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