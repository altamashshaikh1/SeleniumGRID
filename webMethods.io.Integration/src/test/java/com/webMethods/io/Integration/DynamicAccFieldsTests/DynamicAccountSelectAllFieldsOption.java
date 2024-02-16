package com.webMethods.io.Integration.DynamicAccFieldsTests;

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

public class DynamicAccountSelectAllFieldsOption 
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

	@Test(priority=0,groups = {"DynamicAccountSelectAllFieldsOption"},description = "Login user : DynamicAccount SelectAllFields")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority=1,dependsOnMethods = {"userLogin"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Create new workflow test")
	public void dynamicAccountselectFieldsNewWorkflow() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.projectSearchTextBox,PropertiesData.readInputData("dynamicAccountProjectNameTwo"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.myUIautomationproject,webDriver,"Click on default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.createNewWorkflow,webDriver,"Click on new workflow link");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.createNewWorkflowButton, webDriver, "Wait for Option to clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.createNewWorkflowButton,webDriver,"Click on new workflow button");
	}

	@Test(priority=2,dependsOnMethods = {"dynamicAccountselectFieldsNewWorkflow"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Assert activites panel visible")
	public void dynamicAccountselectFieldsActivitiesPanel() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.activitiesPanel,webDriver,"Assert activities panel visible on canvas");
	}

	@Test(priority=3,dependsOnMethods = {"dynamicAccountselectFieldsActivitiesPanel"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Assert canvas assets visible")
	public void dynamicAccountselectFieldsCanvas() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.canvasAssets,webDriver,"Assert all canvas assets visible");
	}

	@Test(priority=4,dependsOnMethods = {"dynamicAccountselectFieldsCanvas"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Drag drop actions on canvas")
	public void dynamicAccountselectFieldsDragDrop() 
	{
		//Test Steps
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.actionSearchTextBox,PropertiesData.readInputData("SearchRestAWSS3RestConnector"),webDriver,"Search AWS s3 Rest connector");
		SeleniumKeyBoardActionUtils.dragDropBY(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.action1Searched,webDriver,"Drag and drop Rest connector on canvas", -900, -20);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.restConnectorActionId,webDriver,"Assert Rest connector visible on canvas");
	}

	@Test(priority=5,dependsOnMethods = {"dynamicAccountselectFieldsDragDrop"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Connect actions on canvas")
	public void dynamicAccountselectFieldsConnectionAction() 
	{
		//Test Steps
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.restActionId,webDriver,"Mouseover on Rest connector");
		SeleniumKeyBoardActionUtils.dragDrop(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.firstActionConnector,com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.stopAction,webDriver,"Connect Rest connector with stop action");
	}

	@Test(priority=6,dependsOnMethods = {"dynamicAccountselectFieldsConnectionAction"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Configure actions")
	public void dynamicAccountselectFieldsSelect() throws InterruptedException 
	{
		//To visit custom operation modal
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.restActionId,webDriver,"Double click on Rest connector");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.custom_oper_add_str, webDriver, "Wait for element visiable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.actionListdropdown, webDriver, "click on the action list dropdown arrow");

		for(int i=0; i<=15;i++)
		{
			try
			{
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.customOperEditIcon, webDriver, "Wait for for Edit button");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.customOperEditIcon, webDriver, "Click on Edit option");				
				break;
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().fail("Edit Custom Operation icon is not clickable");
				Assert.fail("Edit Custom Operation icon is not clickable");	
			}
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.toggleswitch, webDriver, "Click on Toggle switch");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.selectOperationModal, webDriver, "Wait for select oeration modal to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.firstselectallcheckbox, webDriver, "Click on First select all checkbox to unselect partial selected state");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.firstselectallcheckbox, webDriver, "Click on First select all checkbox to select all fields present under that");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutorTwo,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.secondselectallcheckbox, webDriver, "Click on Second select all checkbox to unselect partial selected state");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.secondselectallcheckbox, webDriver, "Click on second select all checkbox to select all fields present under that");
		//SeleniumWebElementsUtils.clearAndAddText(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accesskeyinputbox, webDriver,PropertiesData.readInputData("anotheraccaccesskey"), "Provide Another account access key");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accesskeyinputbox, webDriver,"Provide Another account access key");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accesskeyinputbox,PropertiesData.readInputData("anotheraccaccesskey"), webDriver,"Provide Another account access key");


		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.previousbutton, webDriver, "Wait till previous button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.previousbutton, webDriver, "Click on Previous button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "Wait till previous Next is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "Click on Next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "Wait till next button is clickable on next operation page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.doneButton, webDriver, "Wait till done button is clickable on final page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.doneButton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.miniloder1_str, webDriver, "Wait till saving modal closed");

		//To verify all fields present in action configuration modal or not
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accselectmodalnextbutton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accselectmodalnextbutton, webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.actionformsection, webDriver, "Wait for action configuration modal to laod");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.$connobjtext,PropertiesData.readAssertionData("$connobjtext"), webDriver, "Assert $connection field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.connobjtext,PropertiesData.readAssertionData("connobjtext"), webDriver, "Assert Connection field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.serverUrltext,PropertiesData.readAssertionData("serverURLFieldname"), webDriver, "Assert Server url field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.connretrycountfield,PropertiesData.readAssertionData("connectionretrycount"), webDriver, "Assert Connection Retry Count field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.retryresponsefailurefield,PropertiesData.readAssertionData("retryonresponsefailurefieldname"), webDriver, "Assert Retry on Response Failure field is present",softAssert);
		JavascriptExecutor javascriptExecutorThree = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutorThree,"Scroll page down");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.truststorealiasfield,PropertiesData.readAssertionData("truststoraliasfieldname"), webDriver, "Assert Trust store Alias field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.keystorealiasfield,PropertiesData.readAssertionData("keystorealiasfieldname"), webDriver, "Assert Keystore Alias field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.clientkeyaliasfield,PropertiesData.readAssertionData("clientkeyaliasfieldname"), webDriver, "Assert Client Key Alias field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.hostnamefield,PropertiesData.readAssertionData("hostnameverifierfieldname"), webDriver, "Assert Hostname verifier field is present",softAssert);
		JavascriptExecutor javascriptExecutorFour = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutorFour,"Scroll page down");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.enablecompressionfield,PropertiesData.readAssertionData("enablecompressionfieldname"), webDriver, "Assert Enable Compression field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.enablesnifield,PropertiesData.readAssertionData("enableSNIfieldname"), webDriver, "Assert Enable SNI field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.sniservernamefield,PropertiesData.readAssertionData("sniservernamefieldname"), webDriver, "Assert SNI Server Name field is present",softAssert);
		JavascriptExecutor javascriptExecutorFive = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutorFive,"Scroll page down");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.awss3signaturefield,PropertiesData.readAssertionData("awsS3signaturefieldname"), webDriver, "Assert AWS S3 Signature field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accesskeyfield,PropertiesData.readAssertionData("accesskeyfieldname"), webDriver, "Assert Access Key field is present",softAssert);
		String temp1 = SeleniumWebElementsUtils.fetchAttributeValue(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accesskeyinputboxonactionconfigmodal, "value", webDriver, "Assert value of Access Key present which is configured in dynamic form test",softAssert);

		if(temp1.equalsIgnoreCase(PropertiesData.readInputData("anotheraccaccesskey")))
		{
			ExtentTestManager.getTest().pass("Updated access key get display to user");
		}
		else
		{
			ExtentTestManager.getTest().fail("Updated access key not get display to user");
			Assert.fail("Updated access key not get display to user");
		}

		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.secretkeyfield,PropertiesData.readAssertionData("secretkeyfieldname"), webDriver, "Assert Secret Key field is present",softAssert);
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.regionfield,PropertiesData.readAssertionData("regionfieldname"), webDriver, "Assert Region field is present",softAssert);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accselectmodalnextbutton, webDriver, "Click on Next Option");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.testactionmodaldonebutton, webDriver, "Wait till Done button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.testactionmodaldonebutton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.workflowSave, webDriver, "Wait till save button is Clickable");
	}

	@Test(priority=7,dependsOnMethods = {"dynamicAccountselectFieldsSelect"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Configure actions")
	public void dynamicAccountselectFieldsDynOption() 
	{
		//To disable dynamic account option
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.restActionId,webDriver,"Double click on Rest connector");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.custom_oper_add_str, webDriver, "Wait for element visiable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.actionListdropdown, webDriver, "click on the action list dropdown arrow");

		for(int i=0; i<=15;i++)
		{
			try
			{
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.customOperEditIcon, webDriver, "Wait for for Edit button");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.customOperEditIcon, webDriver, "Click on Edit option");				
				break;
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().fail("Edit Custom Operation icon is not clickable");		
				Assert.fail("Edit Custom Operation icon is not clickable");		
			}
		}

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.toggleswitch, webDriver, "Click on Toggle switch");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "Wait till next button is clickable on next operation page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.nextButton, webDriver, "click on next button");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.doneButton, webDriver, "Wait till done button is clickable on final page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.doneButton, webDriver, "Click on Done button");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.miniloder1_str, webDriver, "Wait till saving modal closed");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accselectmodalcancelbutton, webDriver, "Wait till Cancel button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.accselectmodalcancelbutton, webDriver, "Click on Cancel button");

		//To Save the workflow
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.workflowSave, webDriver, "Wait till save button is Clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.workflowSave,webDriver,"Save workflow");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.workflowSaveMessage,PropertiesData.readAssertionData("workflowSaveMessage"), webDriver,"Assert workflow save message",softAssert);
	}

	@Test(priority=8,dependsOnMethods = {"dynamicAccountselectFieldsDynOption"},groups = {"DynamicAccountSelectAllFieldsOption"},description = "Delete Workflow")
	public void dynamicAccountselectFieldsDeleteData() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.leaveCanvas,webDriver,"Leave canvas page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.createdWorkflowEllipsis1,webDriver,"Click on 1st workflow ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.deleteWorkflow,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.deleteOption,webDriver,"Click on delete option");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.DynamicAccFieldsTests.DynamicAccountSelectAllFieldsTestLocators.workflowDeleteMessage,PropertiesData.readAssertionData("workflowDeleteMessage"), webDriver,"Assert workflow delete message",softAssert);
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