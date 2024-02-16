package com.webMethods.io.Integration.DynamicAccEnableDisableTests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumPageScrollUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class DynamicAccountEnableDisableOption 
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

	@Test(priority = 0,groups = {"DynamicAccountEnableDisableOption"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"DynamicAccountEnableDisableOption"},description = "Create new workflow test")
	public void dynAcctEnableDisableNewWorkflow() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.projectSearchTextBox,PropertiesData.readInputData("dynamicAccountProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.myUIautomationproject,webDriver,"Click on default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.createNewWorkflowButton, webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
	}

	@Test(priority = 2,dependsOnMethods = {"dynAcctEnableDisableNewWorkflow"},description = "Assert activites panel visible")
	public void dynAcctEnableDisableActPanelVisible()
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
	}

	@Test(priority = 3,dependsOnMethods = {"dynAcctEnableDisableActPanelVisible"},groups = {"DynamicAccountEnableDisableOption"},description = "Assert canvas assets visible")
	public void dynAcctEnableDisableCanvasAssets()
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority = 4,dependsOnMethods = {"dynAcctEnableDisableCanvasAssets"},groups = {"DynamicAccountEnableDisableOption"},description = "Drag drop actions on canvas")
	public void dynAcctEnableDisableDragDropActions() 
	{
		//Test Steps
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionSearchTextBox,PropertiesData.readInputData("dynamicAccountSearchRestConnector"),webDriver,"Search Rest connector");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionSearched,webDriver,"Drag and drop Rest connector on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.restConnectorActionId,webDriver,"Assert Rest connector visible on canvas");
	}

	@Test(priority = 5,dependsOnMethods = {"dynAcctEnableDisableDragDropActions"},groups = {"DynamicAccountEnableDisableOption"},description = "Connect actions on canvas")
	public void dynAcctEnableDisableConnAction() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.restActionId,webDriver,"Mouseover on Rest connector");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.firstActionConnector,com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.stopAction,webDriver,"Connect Rest connector with stop action");
	}

	@Test(priority = 6,dependsOnMethods = {"dynAcctEnableDisableConnAction"},groups = {"DynamicAccountEnableDisableOption"},description = "Configure actions")
	public void dynAcctEnableDisableConfigConn()
	{
		//To visit custom operation modal
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.restActionId,webDriver,"Double click on Rest connector");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.custom_oper_add_str, webDriver, "Wait for element visiable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionListdropdown, webDriver, "click on the action list dropdown arrow");

		for(int i=0; i<=15;i++)
		{
			try
			{
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperEditIcon, webDriver, "Wait for for Edit button");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperEditIcon, webDriver, "Click on Edit option");				
				break;
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().fail("Edit Custom Operation icon is not clickable");
				Assert.fail("Edit Custom Operation icon is not clickable");
			}
		}

		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperModal, webDriver, "Wait for custom operation modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.dynamicAccOption, webDriver, "Verify Dynamic account option is present");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.dynamicaccText,PropertiesData.readAssertionData("dynamicAccText"), webDriver, "Verify Enable Dynamic Account Text",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.optionOffText,PropertiesData.readAssertionData("toggleSwitchOff"), webDriver, "Asset option is in Off state",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.toggleswitch, webDriver, "Click on Toggle switch");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.optionOnText,PropertiesData.readAssertionData("toggleSwitchOn"), webDriver, "Asset option is in On state",softAssert);
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.secondsteps, webDriver, "Verify second step is added in chain");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.secondsteptext,PropertiesData.readAssertionData("secondStepText"), webDriver, "Assert second step text",softAssert);

		//Create custom operation with dynamic account option enable
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.selectOperationModal, webDriver, "Wait for select oeration modal to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.serverUrl, webDriver, "Wait till first field get display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "Wait till next button is clickable on next operation page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.doneButton, webDriver, "Wait till done button is clickable on final page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.doneButton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.activitySettingModal, webDriver, "Wait for activity setting modal to load");

		//To visit on action configuration modal and check dynamic auth field is present or not
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.infoMessage,PropertiesData.readAssertionData("infoMessageOne"), webDriver, "Assert Info message Text",softAssert);
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionformsection, webDriver, "Wait for action configuration modal to laod");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.$connobjtext,PropertiesData.readAssertionData("$connobjtext"), webDriver, "Assert $connnection obj field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connobjtext,PropertiesData.readAssertionData("connobjtext"), webDriver, "Assert connnection obj field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.serverUrltext,PropertiesData.readAssertionData("serverURLFieldname"), webDriver, "Assert server URL field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.credobjText,PropertiesData.readAssertionData("credntialFieldname"), webDriver, "Assert Credentials field is present",softAssert);
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.passwordtext,PropertiesData.readAssertionData("passwordFieldname"), webDriver, "Assert Password field is present",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Click on Next Option");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionmodaldonebutton, webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionmodaldonebutton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.workflowSave, webDriver, "Wait till save button is Clickable");
	}

	@Test(priority = 7,dependsOnMethods = {"dynAcctEnableDisableConfigConn"},groups = {"DynamicAccountEnableDisableOption"},description = "Edit Custom Operation")
	public void dynAcctEnableDisableEditCusOpt() 
	{
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.restActionId,webDriver,"Double click on Rest connector");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.custom_oper_add_str, webDriver, "Wait for element visiable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionListdropdown, webDriver, "click on the action list dropdown arrow");

		for(int i=0; i<=4;i++)
		{
			try
			{
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperEditIcon, webDriver, "Wait for for Edit button");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperEditIcon, webDriver, "Click on Edit option");				
				break;
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().fail("Edit Custom Operation icon is not clickable");
				Assert.fail("Edit Custom Operation icon is not clickable");
			}
		}

		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperModal, webDriver, "Wait for custom operation modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.dynamicAccOption, webDriver, "Verify Dynamic account option is present");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.selectOperationModal, webDriver, "Wait for select oeration modal to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.serverUrl, webDriver, "Wait till first field get display");

		//To update the dynamic account fields
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connretrycountfield, webDriver, "Select Connection Retry Count field");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connretrycountinputbox, webDriver, "Click on Connection Retry Count input box");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, "a", "Select all");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.DELETE, "Delete");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connectionsection, webDriver, "Click on Connection Section");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connretrycountinputbox,PropertiesData.readInputData("connectionRetryinput"),webDriver,"Provide input to Connection Retry Count field");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");

		String temp2 = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.dynamicaccformusernameinputbox, "value", webDriver, "Assert Predefine value present in field",softAssert);

		if(temp2.equalsIgnoreCase("altamash.shaikh@softwareag.com"))
		{
			ExtentTestManager.getTest().pass("Predefine value present in field");
		}
		else
		{
			ExtentTestManager.getTest().fail("Predefine value not present in field");
			Assert.fail("Predefine value not present in field");
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.usernamefield, webDriver, "Select Username field");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.usernameinputbox, webDriver, "Click on Username input box");
		SeleniumKeyBoardActionUtils.keyboardCombinationControlAndKeys(webDriver, "a", "Select all");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.DELETE, "Delete");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.credentialsection, webDriver, "Click on Credential section");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.usernameinputbox,PropertiesData.readInputData("updatedUsername"),webDriver,"Provide input to Username field");

		//Save the Custom operation
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "Wait till next button is clickable on next operation page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.doneButton, webDriver, "Wait till done button is clickable on final page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.doneButton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.miniloder1_str, webDriver, "Wait till Loader is ");

		//To check updated fields get display in action configuration modal or not
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionformsection, webDriver, "Wait for action configuration modal to laod");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nametextfield,PropertiesData.readInputData("recordname"), webDriver, "Provide input to record name field");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.$connobjtext,PropertiesData.readAssertionData("$connobjtext"), webDriver, "Assert $connnection obj field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connobjtext,PropertiesData.readAssertionData("connobjtext"), webDriver, "Assert connnection obj field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.serverUrltext,PropertiesData.readAssertionData("serverURLFieldname"), webDriver, "Assert server URL field is present",softAssert);
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connretrycounttextfield,PropertiesData.readInputData("connectionRetryinput"), webDriver, "Provide input to Connection Retry Count field");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connretrycountfield,PropertiesData.readAssertionData("connectionretrycount"), webDriver, "Assert Connection Retry Count field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.credobjText,PropertiesData.readAssertionData("credntialfieldname"), webDriver, "Assert Credentials field is present",softAssert);
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutorTwo,"Page scroll down");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.usernamefield,PropertiesData.readAssertionData("usernamefield"), webDriver, "Assert Username field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.passwordtext,PropertiesData.readAssertionData("passwordfieldname"), webDriver, "Assert Password field is present",softAssert);
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.passwordfield,PropertiesData.readInputData("password_1"), webDriver, "Provide input to Password field");

		//To perform Test action
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Click on Next Option");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionmodaldonebutton, webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testButton, webDriver, "click on Test Button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionoutputscreen, webDriver, "Wait till Test action output screen get display");
		JavascriptExecutor javascriptExecutorThree = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutorThree,"Page scroll down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionmodaldonebutton, webDriver, "Click on Done button");

		//Save the workflow
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.workflowSave, webDriver, "Wait till save button is Clickable");
	}

	@Test(priority = 8,dependsOnMethods = {"dynAcctEnableDisableEditCusOpt"},groups = {"DynamicAccountEnableDisableOption"},description = "Configure actions")
	public void disableDynamiAccOptionTest() 
	{
		//Edit the custom operation 
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.restActionId,webDriver,"Double click on Rest connector");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.custom_oper_add_str, webDriver, "Wait for element visiable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionListdropdown, webDriver, "click on the action list dropdown arrow");

		for(int i=0; i<=4;i++)
		{
			try
			{
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperEditIcon, webDriver, "Wait for for Edit button");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperEditIcon, webDriver, "Click on Edit option");				
				break;
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().fail("Edit Custom Operation icon is not clickable");
				Assert.fail("Edit Custom Operation icon is not clickable");
			}
		}

		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.customOperModal, webDriver, "Wait for custom operation modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.dynamicAccOption, webDriver, "Verify Dynamic account option is present");

		//Disable the Dynamic account option and update custom operation
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.toggleswitch, webDriver, "Click on Toggle switch");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.optionOffText,PropertiesData.readAssertionData("toggleSwitchOff"), webDriver, "Assert option is in Off state",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "Wait till next button is clickable on next operation page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.doneButton, webDriver, "Wait till done button is clickable on final page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.doneButton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.miniloder1_str, webDriver, "Wait till Loader is ");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.actionformsection, webDriver, "Wait for action configuration modal to laod");
		SeleniumWebElementsUtils.elementnotvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.connobjtext,webDriver,"Assert connection field is not present");
		SeleniumWebElementsUtils.elementnotvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.serverUrltext,webDriver,"Assert Server Url field is not present");
		SeleniumWebElementsUtils.elementnotvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.credobjText,webDriver,"Assert Credential field is not present");
		SeleniumWebElementsUtils.elementnotvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.usernamefield,webDriver,"Assert Username field is not present");
		SeleniumWebElementsUtils.elementnotvisible(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.passwordtext,webDriver,"Assert Password field is not present");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.accselectmodalnextbutton, webDriver, "Click on Next Option");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionmodaldonebutton, webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.testactionmodaldonebutton, webDriver, "Click on Done button");

		//Save the workflow
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.workflowSave, webDriver, "Wait till save button is Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"), webDriver,"Assert workflow save message",softAssert);
	}

	@Test(priority = 9,dependsOnMethods = {"disableDynamiAccOptionTest"},groups = {"DynamicAccountEnableDisableOption"},description = "Delete Workflow")
	public void dynDisableEnableDelete() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.deleteWorkflow,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.deleteOption,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccEnableDisableTests.DynamicAccountEnableDisableTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"), webDriver,"Assert workflow delete message",softAssert);
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