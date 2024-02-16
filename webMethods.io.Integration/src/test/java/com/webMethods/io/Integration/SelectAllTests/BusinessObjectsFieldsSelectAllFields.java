package com.webMethods.io.Integration.SelectAllTests;

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

public class BusinessObjectsFieldsSelectAllFields
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

	@Test(priority = 0,groups = {"BusinessObjectsFields"},description = "Login user : BusinessObjectsFields")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"BusinessObjectsFields"},description = "Select all fields")
	public void createNewCustomOperationBusinessObject() 
	{
		//Test steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.projectSearchTextBox,PropertiesData.readInputData("as2ProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.as2ProjectName,webDriver,"Click on AS2 project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.activitiesPanelString,webDriver,"Wait for canvas to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.actionSearchTextBox,PropertiesData.readInputData("coupaActionName"),webDriver,"Search Coupa action");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.actionSearched,webDriver,"Drag and drop Coupa action on canvas", -900, -20);	   
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.CoupaActionId,webDriver,"Mouseover on Coupa action");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.firstActionConnector,com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.stopAction,webDriver,"Connect Coupa action with stop action");
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.coupaActionId,webDriver,"Double click on Coupa action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.selectDropDown,webDriver,"Select on drop down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.versionNumber,webDriver,"Select version number");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.infoMessageString,webDriver,"Wait for modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.addNewCustomOperationButton,webDriver, "Click on add new custom operation button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.addNewCustomOperationButtonString,webDriver,"Wait for modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.selectAccountDropDown,webDriver,"Select dropdown button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.accountName,webDriver,"Select added account");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.customAccountName,PropertiesData.readInputData("customOperationName"),webDriver,"Input custom operation name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab action");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Page scroll down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.selectDropDown2,webDriver,"Select dropdown button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.referenceDataField,webDriver,"Select functional option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.nextButton,webDriver,"Click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.operationModalString,webDriver,"Wait for opration modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.createOpeartionOption,webDriver,"Select operation name");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.headersString,webDriver,"Wait for header button to vsi");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.nextButton,webDriver,"Click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.businessObjectModalString,webDriver,"Wait for business modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.businessOperationName,webDriver,"Select business object operation option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.nextButton,webDriver,"Click on next button");
	}

	@Test(priority = 2,dependsOnMethods = {"createNewCustomOperationBusinessObject"},groups = {"BusinessObjectsFields"},description = "Unselect all fields")
	public void unSelectAllFieldsBusinessObjects()
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.dataModalString,webDriver,"Wait for data fields modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.checkBox,webDriver,"Unselect all fields");
	}

	@Test(priority = 3,dependsOnMethods = {"unSelectAllFieldsBusinessObjects"},groups = {"BusinessObjectsFields"},description = "Select all fields")
	public void selectAllFieldsBusinessObject()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.SelectAllTests.BusinessObjectsFieldsSelectAllFieldsLocators.checkBox,webDriver,"Select all fields");
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