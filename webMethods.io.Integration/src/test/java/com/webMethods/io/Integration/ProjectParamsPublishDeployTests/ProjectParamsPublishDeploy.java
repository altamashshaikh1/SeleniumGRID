package com.webMethods.io.Integration.ProjectParamsPublishDeployTests;

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
import com.webMethods.io.Integration.Constants.FilePathConstants;
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

public class ProjectParamsPublishDeploy
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static String authtoken;

	public static String cookie;

	public static String csrftoken;

	public static String projectCreatePayload;

	public static String createProjectResponseBody;

	public static String projectUID;

	public static String filePath;

	public static String responseBodyMultiPart;

	public static String importedWorkflowUID;

	public static String workflowUID;

	public static String userResponseBody;

	public static String skipOverRideResponseBody;

	public static boolean skipandoverridestatus;

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

	@Test(priority = 0,groups = {"Project Parameters-Publish & Deploy"},description = "Verify account login")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Project Parameters-Publish & Deploy"},description="Create Project and Workflow")
	public void createAssets() 
	{
		//Payload 
		projectCreatePayload = "{\"name\":\"ProjectParameterPublishDeploy\"}";

		//Make API request to create project
		createProjectResponseBody = HttpURLConnHelperFuntions.HTTP_PUBLIC_API_POST_REQUEST(MavenArgumentConstants.SOURCE_TENANT_URL+"/apis/v1/rest/projects",
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, 
				projectCreatePayload);

		//Extract project UID
		projectUID = HttpURLConnHelperFuntions.getJsonKey(createProjectResponseBody, "uid"); 

		//Import workflow with project Parameter
		//Make API request
		filePath = FilePathConstants.TEST_INPUT_DATA_FOLDER_PATH+FilePathConstants.FILE_SEPARATOR+"ProjectParameterWorkflow.zip";
		responseBodyMultiPart = HttpURLConnHelperFuntions.HTTP_POST_MULTIPART_PUBLIC_API(MavenArgumentConstants.SOURCE_TENANT_URL+"/apis/v1/rest/projects/"+projectUID+"/workflow-import", 
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, 
				filePath, 
				"recipe");

		//Assert response body
		importedWorkflowUID =  HttpURLConnHelperFuntions.getJsonKey(responseBodyMultiPart, "uid");
	}

	@Test(priority = 2,dependsOnMethods = {"createAssets"},groups = {"Project Parameters-Publish & Deploy"},description="Verify Publish Project")
	public void selectAssets()  
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProjectString,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.workflowscheckbox, webDriver, "Click on Workflow checkbox");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.dependenciesModal,webDriver,"Wait for dependencies modal to be visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton, webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.publishSettingsModalString,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deploymentNameTextBox,"First Time Publish",webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");	
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, MavenArgumentConstants.DESTINATION_TENANT_NAME,"Sendkeys");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.ProjectpublishButton, webDriver, "Wait till publish button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.ProjectpublishButton, webDriver, "Click on publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPubishMessageString,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishMessage,PropertiesData.readAssertionData("projectPublishMessage"),webDriver,"Assert Project Publish message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPubishMessageString, webDriver, "Wait till message disappear");
	}

	@Test(priority = 3,dependsOnMethods = {"selectAssets"},groups = {"Project Parameters-Publish & Deploy"},description="Deploy Project")
	public void deployWorkflowTest() throws InterruptedException 
	{
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.ssoLoginLink, webDriver, "Click on SSO login Link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectparamTestbeforeProjectcreate_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.prjparamsproject, webDriver, "Click on Project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deployprojectnamemodal, webDriver, "Wait for project name modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.saveandcontinueButton, webDriver, "click on save and continue option");

		//To Check Skip and Override Capabilities is enabled or disabled for given tenant
		//Login user first and extract authtoken,cookie and csrf token
		userResponseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token", 
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, 
				"Make GET : /enterprise/v1/user/token API request to API Server");
		cookie = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "cookie");
		authtoken = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "authtoken");
		csrftoken = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "csrf");

		//To Send capabilities call and check status of Skip and override feature
		skipOverRideResponseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/integration/rest/tenant/capabilities",
				authtoken, cookie, csrftoken, "To Get Status of Skip and Override feature");

		//extract skip and override status
		skipandoverridestatus = HttpURLConnHelperFuntions.getJsonKeyBooleanValue(skipOverRideResponseBody, "integration", "tenantCapabilities","skipAssetsEnabled");

		if(skipandoverridestatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.skipassetsString,webDriver,"Wait for Skip Assets modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configureAccountString,webDriver,"Wait for configure account modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.accselectmodalnextbutton, webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configureTriggerString,webDriver,"Wait for configure trigger modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.accselectmodalnextbutton, webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configureParameterString,webDriver,"Wait for configure parameter modal");

		//Verify Both params get display in 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.paramsinnermodal, webDriver, "Verify Params inner modal get load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1params, webDriver, "Verify Test1 Param");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2params, webDriver, "Verify Test2 Param");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramssourcevalue, webDriver, "Verify Test1 Param value");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2paramsvalue, webDriver, "Verify Test2 Param value");

		//Update Value of Parameter 1
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1parameditpencilicon, webDriver, "Click on Edit icon of Test1 parameter");
		Thread.sleep(2000);
		SeleniumKeyBoardActionUtils.keyboardBackSpaceActions(webDriver, Keys.BACK_SPACE, "Enter backspce to clear field");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver, "11","Sendkeys");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1params, webDriver, "Click on Test1 Parameter Key");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectdeployButton, webDriver, "Click on Deploy option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDeployementMessageString,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDeployementMessage,PropertiesData.readAssertionData("projectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDeployementMessageString, webDriver, "Wait for message to disappear");
	}

	@Test(priority = 4,dependsOnMethods = {"deployWorkflowTest"},groups = {"Project Parameters-Publish & Deploy"},description="Verify Deployed Assets")
	public void assetsVerificationAfterdeployment()  
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.prjparamTestproject, webDriver, "Click on Project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectparamworkflow, webDriver, "Verify Deployed workflow get display in list");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configurationsLink,webDriver,"Click on configuration tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configurationWorkflow,webDriver,"Select workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.parameterConfigurationLink,webDriver,"Select parameter option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configPageParamsModal,webDriver,"Wait for params modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest1param, webDriver, "Verify Deployed workflow get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest2param, webDriver, "Verify Deployed workflow get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramsupdatedvalue, webDriver, "Verify Deployed workflow get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2paramsconfigpagevalue, webDriver, "Verify Deployed workflow get display in list");
	}

	@Test(priority = 5,dependsOnMethods = {"assetsVerificationAfterdeployment"},groups = {"Project Parameters-Publish & Deploy"},description="Verify RePublish Project with Updated Project Parameter")
	public void republishAssets() throws InterruptedException 
	{
		//Test Steps
		webDriver.navigate().to(MavenArgumentConstants.SOURCE_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.prjparamTestproject, webDriver, "Click on Project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectparamworkflow, webDriver, "Verify  workflow get display in list");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configurationsLink,webDriver,"Click on configuration tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configurationWorkflow,webDriver,"Select workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.parameterConfigurationLink,webDriver,"Select parameter option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configPageParamsModal,webDriver,"Wait for params modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest1param, webDriver, "Verify Test1 Parameter get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest2param, webDriver, "Verify Test2 Parameter  get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramsoldvalue, webDriver, "Verify Test1 Parameter value  get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2paramsconfigpagevalue, webDriver, "Verify Test2 Parameter value get display in list");

		//Update project Params
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.firstParamsEllipsis,webDriver,"Click on ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.paramsEditOptionString,webDriver,"Wait for edit option to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.paramsEditOption,webDriver,"Select edit option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2ParamKeyField_key,webDriver,"Wait for Edit Parameter Modal");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2ParamKeyFieldwithval,webDriver,"Clear Key Name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.namefield,webDriver,"Click on name field");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.kyenameinput,"Test22",webDriver,"Provide Updated key name for Test2 Parameter");
		Thread.sleep(2000);
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2Paramispwdcheckbox,webDriver,"Click on isPassword Checkbox");
		Thread.sleep(2000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.updateParamsButton,webDriver,"Click on Update button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectParamUpdateMessageString,webDriver,"Wait for Update Project Parameter Message");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectParamUpdateMessageString,webDriver,"Wait for Update Project Parameter Message dissappear");

		//Add New Project Parameter
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewParameterOption,webDriver,"Click on New Parameter option");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.kyenameinput,"Test3",webDriver,"Enter new parameter name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.createParamsButton,webDriver,"Click on Create button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addedParamString3,webDriver,"Wait for new params added");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addedParam3,webDriver,"Assert new parameter added");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectParamCreateMessageString,webDriver,"Wait for Create Project Parameter Message dissappear");

		//RePublish Project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProjectString,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page down");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.dependenciesModal,webDriver,"Wait for dependencies modal to be visible");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver, "Wait till Next button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.publishSettingsModalString,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deploymentNameTextBox,"Second Time Publish",webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME, "Enter destination tenant name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.ProjectpublishButton,webDriver, "Wait till publish button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.ProjectpublishButton,webDriver, "Click on publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPubishMessageString,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishMessage,PropertiesData.readAssertionData("projectPublishMessage"),webDriver,"Assert Project Publish message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPubishMessageString,webDriver, "Wait till message disappear");
	}

	@Test(priority = 6,dependsOnMethods = {"republishAssets"},groups = {"Project Parameters-Publish & Deploy"},description="Verify Assets Deletion")
	public void deleteAssetsAfterRepublish()
	{
		//To Check Skip and Override Capabilities is enabled or disabled for given tenant
		//Login user first and extract authtoken,cookie and csrf token
		userResponseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user/token", 
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, 
				"Make GET : /enterprise/v1/user/token API request to API Server");
		cookie = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "cookie");
		authtoken = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "authtoken");
		csrftoken = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "csrf");

		//Get Project Uid
		String tempResponse = HttpURLConnHelperFuntions.HTTP_PUBLIC_API_GET_REQUEST(MavenArgumentConstants.SOURCE_TENANT_URL+"/apis/v1/rest/projects/ProjectParameterPublishDeploy",
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD);

		//extract Project uid
		projectUID = HttpURLConnHelperFuntions.getJsonKey(tempResponse, "uid");

		//Get workflow uid
		String tempResponseWorkflows = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/flows", authtoken, cookie, csrftoken,projectUID,"Get Workflows of project");

		//extract workflow uid
		workflowUID = HttpURLConnHelperFuntions.getJsonArrayKeyValue(tempResponseWorkflows, "uid");

		//Delete workflow
		HttpURLConnHelperFuntions.HTTP_DELETE_PUBLIC_API(MavenArgumentConstants.SOURCE_TENANT_URL+"/apis/v1/rest/projects/"+projectUID+"/workflows/"+workflowUID+"", 
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD,  
				"{\"test\":\"test\"}");

		//Delete Project
		HttpURLConnHelperFuntions.HTTP_DELETE_PUBLIC_API(MavenArgumentConstants.SOURCE_TENANT_URL+"/apis/v1/rest/projects/"+projectUID+"",
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, 
				"{\"test\":\"test\"}");
	}

	@Test(priority = 7,dependsOnMethods = {"deleteAssetsAfterRepublish"},groups = {"Project Parameters-Publish & Deploy"},description="ReDeploy Project with Updated Parameter")
	public void deployWorkflowAfterRepublish()
	{
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectparamTestbeforeProjectcreate_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.prjparamsproject, webDriver, "Click on Deployment Tab");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deploymenytab, webDriver, "Wait till Deployment tab is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deploymenytab, webDriver, "Click on Deployment Tab");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deployoption, webDriver, "Wait till Deploy Option is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.deployoption, webDriver, "Click on Deploy Option");

		//To Check Skip and Override Capabilities is enabled or disabled for given tenant
		//Login user first and extract authtoken,cookie and csrf token
		userResponseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token", 
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, 
				"Make GET : /enterprise/v1/user/token API request to API Server");
		cookie = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "cookie");
		authtoken = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "authtoken");
		csrftoken = HttpURLConnHelperFuntions.getJsonKey(userResponseBody, "csrf");

		//To Send capabilities call and check status of Skip and override feature
		skipOverRideResponseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/integration/rest/tenant/capabilities",
				authtoken, cookie, csrftoken, "To Get Status of Skip and Override feature");

		//extract skip and override status
		skipandoverridestatus = HttpURLConnHelperFuntions.getJsonKeyBooleanValue(skipOverRideResponseBody, "integration", "tenantCapabilities","skipAssetsEnabled");

		if(skipandoverridestatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.skipassetsString,webDriver,"Wait for Skip Assets modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configureAccountString,webDriver,"Wait for configure account modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configureTriggerString,webDriver,"Wait for configure trigger modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.accselectmodalnextbutton,webDriver, "Click on Next option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configureParameterString,webDriver,"Wait for configure parameter modal");

		//Verify Both params get display in 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.paramsinnermodal,webDriver, "Verify Params inner modal get load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2updatedparams,webDriver, "Verify Test2 Param with updated key name");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2pwdformvalue,webDriver, "Verify Test2 Param Value in Password Form");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2pwdicon,webDriver, "Click on Eye icon of Test2 parameter");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test22paramsvalue_str,webDriver,"Wait for Test2 Parmas value in normal fomr");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test22paramsvalue,webDriver, "Verify Test2 Param value");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test2pwdicondisable,webDriver, "Verify Test2 Param eye icon is in disabled state");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test3params,webDriver, "Verify Test3 Param");

		//Verify Previously Configured Parameter Section
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.prevparamconfigopenIcon,webDriver, "Click on Previously Configured Parameters option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1params_str,webDriver,"Wait for previously configured parameters section to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1params,webDriver, "Verify Test1 Param");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramsdestinatationvalue,webDriver, "Verify Test1 Param value");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1checkboxicon,webDriver, "Wait till Test1 Parameter checkbox clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1checkboxicon,webDriver, "Click on overrite checkbox of Test1 Parameter");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramsvalue_str,webDriver,"Wait for Test1 Parameter Overite value");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramssourcevalue,webDriver, "Verify Test1 Param override value");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1editicondisabled,webDriver, "Verify Test1 Param Checkbox is disabled");

		//Deploy Project
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectdeployButton,webDriver, "Click on Deploy option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDeployementMessageString,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDeployementMessage,PropertiesData.readAssertionData("projectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectDeployementMessageString, webDriver, "Wait for message to disappear");
	}

	@Test(priority = 8,dependsOnMethods = {"deployWorkflowAfterRepublish"},groups = {"Project Parameters-Publish & Deploy"},description="Verify Deployed Assets")
	public void assetsVerificationAfterRedeployment() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectSearchTextBox,"ProjectParameterPublishDeploy",webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectPublishProject_str, webDriver, "Wait for project to display");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.prjparamTestproject, webDriver, "Click on Project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.projectparamworkflow, webDriver, "Verify Deployed workflow get display in list");

		//Asserts Project Parameter
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configurationsLink,webDriver,"Click on configuration tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configurationWorkflow,webDriver,"Select workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.parameterConfigurationLink,webDriver,"Select parameter option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configPageParamsModal,webDriver,"Wait for params modal to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest1param, webDriver, "Verify Deployed Test1 Params get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest2paramupdatedkey, webDriver, "Verify Deployed Test2 Params get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.configpagetest3param, webDriver, "Verify Deployed Test3 Params get display in list");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsPublishDeployTests.ProjectParamsPublishDeployLocators.test1paramsoldvalue, webDriver, "Verify Deployed workflow get display in list");
	}

	@Test(priority = 9,dependsOnMethods = {"assetsVerificationAfterRedeployment"},groups = {"Project Parameters-Publish & Deploy"},description="Verify Assetes Deletion")
	public void deleteAssetsSecondPublishDeploy()
	{
		//Get Project Uid
		String tempResponseProject = HttpURLConnHelperFuntions.HTTP_PUBLIC_API_GET_REQUEST(MavenArgumentConstants.DESTINATION_TENANT_URL+"/apis/v1/rest/projects/ProjectParameterPublishDeploy",
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD);

		//Extract Project uid
		String projectUID = HttpURLConnHelperFuntions.getJsonKey(tempResponseProject, "uid");

		//Get workflow uid
		String tempResponseWorkflow = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/flows", 
				authtoken, authtoken, csrftoken,projectUID,"Get Workflows of project");

		//Extract workflow uid
		String workflowtUID = HttpURLConnHelperFuntions.getJsonArrayKeyValue(tempResponseWorkflow, "uid");

		//Delete workflow
		HttpURLConnHelperFuntions.HTTP_DELETE_PUBLIC_API(MavenArgumentConstants.DESTINATION_TENANT_URL+"/apis/v1/rest/projects/"+projectUID+"/workflows/"+workflowtUID+"", 
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD,
				"{\"test\":\"test\"}");

		//Delete Project
		HttpURLConnHelperFuntions.HTTP_DELETE_PUBLIC_API(MavenArgumentConstants.DESTINATION_TENANT_URL+"/apis/v1/rest/projects/"+projectUID+"",
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD,
				"{\"test\":\"test\"}");
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