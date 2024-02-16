package com.webMethods.io.Integration.FunctionalAreaSearchTests;

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

public class FunctionalAreaSearchOption
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

	@Test(priority = 0,groups = {"FunctionalAreaSearchOption"},description = "Login user : FunctionalAreaSearchOption")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"FunctionalAreaSearchOption"},description = "Create new workflow test")
	public void funcAreaSearchNewWorkflow() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.projectSearchTextBox,webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.projectSearchTextBox,PropertiesData.readInputData("functionalAreaSearchProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.myUIautomationproject,webDriver,"Click on default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.createNewWorkflowButton,webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
	}

	@Test(priority = 2,dependsOnMethods = {"funcAreaSearchNewWorkflow"},groups = {"FunctionalAreaSearchOption"},description = "Assert activites panel visible")
	public void funcAreaSearchActPanelVisible() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
	}

	@Test(priority = 3,dependsOnMethods = {"funcAreaSearchActPanelVisible"},groups = {"FunctionalAreaSearchOption"},description = "Assert canvas assets visible")
	public void funcAreaSearchCanvAssets()
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 4,dependsOnMethods = {"funcAreaSearchCanvAssets"},groups = {"FunctionalAreaSearchOption"},description = "Drag drop actions on canvas")
	public void funcAreaSearchDragDrop()
	{
		//Test Steps
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.actionSearchTextBox,PropertiesData.readInputData("SearchCoupaConnector"),webDriver,"Search coupa connector");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.actionSearched,webDriver,"Drag and drop Coupa connector on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.CoupaActionId,webDriver,"Assert coupa connector visible on canvas");
	}

	@Test(priority = 5,dependsOnMethods = {"funcAreaSearchDragDrop"},groups = {"FunctionalAreaSearchOption"},description = "Connect actions on canvas")
	public void funcAreaSearchActionConnect() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.CoupaActionId,webDriver,"Mouseover on coupa connector");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.firstActionConnector,com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.stopAction,webDriver,"Connect coupa connector with stop action");
	}

	@Test(priority = 6,dependsOnMethods = {"funcAreaSearchActionConnect"},groups = {"FunctionalAreaSearchOption"},description = "Configure actions")
	public void funcAreaSearchConfigAccounts() 
	{
		//To visit custom operation modal
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.CoupaActionId,webDriver,"Double click on Coupa connector");

		//To visit custom operation modal
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.CoupaActionId,webDriver,"Double click on Coupa connector");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.sel_version,webDriver, "Wait for select version option clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.sel_version,webDriver, "Wait for select version option clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.coupa_v29,webDriver, "Select coupa version 29");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.customOperAddOption,webDriver, "Wait for element visiable");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.customOperAddOption,webDriver, "Wait for add custom operation option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.customOperAddOption,webDriver,"Click on Add custom operation option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.customOperModal,webDriver, "Wait for custom operation modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.accSelDropdown,webDriver, "Click on Account selection dropdown arrow");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.coupaAccSelect,webDriver, "Wait for account list to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.coupaAccSelect,webDriver, "Select Coupa account");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.custOperName,PropertiesData.readInputData("functionalAreaSearchCustomOperationName"),webDriver, "Provide input");
	}

	@Test(priority = 7,dependsOnMethods = {"funcAreaSearchConfigAccounts"},groups = {"FunctionalAreaSearchOption"},description = "Configure custom Operation")
	public void funcAreaSearchActionTest()
	{
		//To verify searchbox is present
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.functionAeraDropdown,webDriver, "Click on Functional area dropdown");

		//To verify with invalid value in search box
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.functionAreaSearchbox,PropertiesData.readInputData("functionalAreaSearchInvalidValueForSearch"),webDriver, "Provide invalid value in search box");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.noResultMsg, "No data found",webDriver, "Verify Meesage",softAssert);
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, "a", "Select all");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.DELETE, "Delete");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.refData,webDriver, "Verify Refrence Data record is present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.transData,webDriver, "Verify Transaction Data record is present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.sharedResource,webDriver, "Verify Shared Resources record is present");

		//To verify with Valid value
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.functionAreaSearchbox,PropertiesData.readInputData("functionalAreaSearchValidRecord"),webDriver, "Provide valid value in search box");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.refData,webDriver, "Verify Refrence Data record is present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.transData,webDriver, "Verify Transaction Data record is present");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.refData,webDriver, "Click on Refrence Data record");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.selValue,webDriver, "Verify user selected value is get display in functional area field");

		//To proceed with custom operation creation
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.nextButton,webDriver, "Wait till next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.nextButton,webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.selectOperationModal,webDriver, "Wait for select oeration modal to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.firstRecordinList,webDriver, "Wait till operation list load");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.firstRecordinList,PropertiesData.readAssertionData("functionalAreaSearchFirstRecordinOperationList"),webDriver, "Verify first record in list",softAssert);

		//To go back on previous page
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.previousButton,webDriver, "Wait till Previous button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.previousButton,webDriver, "click on previous button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.customOperModal,webDriver, "Wait for custom operation modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.selValue,webDriver, "Verify previously user selected value is get display in functional area field");

		//To select record without search
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.selValue,webDriver, "Click on Functional area dropdown");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.functionAreaSearchbox,webDriver, "Veriry Searchbox is present");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.transData,webDriver, "Click on Transaction Data record");

		//To again proceed with custom operation creation with selected value
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.nextButton,webDriver, "Wait till next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.nextButton,webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.selectOperationModal,webDriver, "Wait for select oeration modal to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.strFirstRecinList1,webDriver, "Wait till operation list load");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.firstRecordinList1,PropertiesData.readAssertionData("functionalAreaSearchFirstRecordinOperationOption"),webDriver, "Verify first record in list",softAssert);
	}

	@Test(priority = 8,dependsOnMethods = {"funcAreaSearchActionTest"},groups = {"FunctionalAreaSearchOption"},description = "Delete Workflow")
	public void funcAreaSearchDeleteData() 
	{
		//Test Data deletion
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.cancelButton,webDriver, "Wait till Cancel button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.cancelButton,webDriver, "Click on Cancel button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.activitySettingModal,webDriver, "Wait for activity setting modal to load");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.actSettingCancelButton,webDriver, "Wait till Cancel button of activity setting modal is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.actSettingCancelButton,webDriver, "Click on Cancel button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.workflowSave,webDriver, "Wait till save button is Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"),webDriver,"Assert workflow save message",softAssert);

		// visit dashboard and Delete workflow
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.deleteWorkflow,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.deleteOption,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.FunctionalAreaSearchTests.FunctionalAreaSearchTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"),webDriver,"Assert workflow delete message",softAssert);
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