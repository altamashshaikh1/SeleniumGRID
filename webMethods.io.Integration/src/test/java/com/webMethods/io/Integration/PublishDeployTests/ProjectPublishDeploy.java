package com.webMethods.io.Integration.PublishDeployTests;

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

public class ProjectPublishDeploy  
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static String responseBody;

	public static String authtoken;

	public static String cookie;

	public static String csrftoken;

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

	@Test(priority = 0,groups = {"Projects - Publish Deploy"},description = "PublishDeploy Project : Verify user login")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Projects - Publish Deploy"},description = "Select project assets")
	public void stagePromotionSelectAssets() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectSearchTextBox,PropertiesData.readInputData("projectPublishName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementsVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.ellipsisLists,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 2,dependsOnMethods = {"stagePromotionSelectAssets"},groups = {"Projects - Publish Deploy"},description = "Confirm assets dependencies")
	public void stagePromotionVerifyDependencies()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Scroll page");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset1,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset2,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset3,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset4,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset5,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset6,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 3,dependsOnMethods = {"stagePromotionVerifyDependencies"},groups = {"Projects - Publish Deploy"},description = "Publish project")
	public void stagePromotionPublishProject() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymentNameTextBox,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymentNameTextBox,PropertiesData.readInputData("deploymentName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
	}

	@Test(priority = 4,dependsOnMethods = {"stagePromotionPublishProject"},groups = {"Projects - Publish Deploy"},description = "Assert first project deployed visible")
	public void stagePromotionVerifyDeployment() 
	{
		//Test Steps
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.usernameInput,MavenArgumentConstants.DESTINATION_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.passwordInput,MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.loginButton, webDriver,"Click on login button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectSearchTextBox,PropertiesData.readInputData("projectPublishName"),webDriver,"Input published project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectDeployedName,webDriver,"Assert project deployed is shown");
	}

	@Test(priority = 5,dependsOnMethods = {"stagePromotionVerifyDeployment"},groups = {"Projects - Publish Deploy"},description = "Create project in IC")
	public void stagePromotionCreateNewProject() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectDeployedName,webDriver,"Click on Deployed project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.saveAndContinueButton,webDriver,"Click on Save and continue button to create project in IC");
	}

	@Test(priority = 6,dependsOnMethods = {"stagePromotionCreateNewProject"},groups = {"Projects - Publish Deploy"},description = "Create accounts")
	public void stagePromotionCreateNewAccount() throws InterruptedException
	{
		//To Check Skip and Override Capabilities is enabled or disabled for given tenant
		//Login user first and extract authtoken,cookie and csrf token
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, 
				"Login account to get authtoken, cookie and CSRF token to make tenant capabilities API request");
		authtoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken");
		cookie = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie");
		csrftoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf");

		//To Send capabilities call and check status of Skip and override feature
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/integration/rest/tenant/capabilities",
				authtoken,
				cookie,
				csrftoken,
				"Make tenant capabilities request");

		//extract skip and override status
		skipandoverridestatus =  HttpURLConnHelperFuntions.getJsonKeyBooleanValue(responseBody, "integration", "tenantCapabilities","skipAssetsEnabled");

		if(skipandoverridestatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.skipassetsString,webDriver,"Wait for configure account modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}

		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configureAccount, webDriver, "Wait for Configure account section");
		Thread.sleep(4000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.addAccountIcon,webDriver,"Click on Add icon to add jotform account");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.connectionModal, webDriver, "Wait connection modal to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.JotformAPIKeyTextBox,PropertiesData.readInputData("projectDeployjotformAPIKey"),webDriver,"Enter jotform API key");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.addButtonConnection,webDriver,"Click on Add button to add account");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.triggerAccountName,webDriver,"Wait for account added is displayed");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 7,dependsOnMethods = {"stagePromotionCreateNewAccount"},groups = {"Projects - Publish Deploy"},description = "Create trigger")
	public void stagePromotionCreateNewTrigger() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configureTriggerString,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.dropdownAccountTrigger,webDriver,"Click on drop down to add trigger account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.triggerAccountName,webDriver,"Select jotform account");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.JotformTextBox,PropertiesData.readInputData("projectDeployJotformFormID"),webDriver,"Enter jotform ID");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 8,dependsOnMethods = {"stagePromotionCreateNewTrigger"},groups = {"Projects - Publish Deploy"},description = "Create project parameters")
	public void stagePromotionCreateNewProjectParams() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configureParameter,webDriver,"Wait for configure project parameter modal");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configureParameter,webDriver,"Assert project params created");   
	}

	@Test(priority = 9,dependsOnMethods = {"stagePromotionCreateNewProjectParams"},groups = {"Projects - Publish Deploy"},description = "Project deploy")
	public void stagePromotionProjectDeploy() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deployButton,webDriver,"Click on deploy button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectDeployementMessage,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectDeployementMessage,PropertiesData.readAssertionData("projectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
	}

	@Test(priority = 10,dependsOnMethods = {"stagePromotionProjectDeploy"},groups = {"Projects - Publish Deploy"},description = "Assert project data after deployment")
	public void stagePromotionAssertProjectData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deployedProject,webDriver,"Assert project deployed");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.numberOfWorkflows,webDriver,"Assert workflow count shown on project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.numberOfFlows,webDriver,"Assert flow services count shown on project");
	}

	@Test(priority = 11,dependsOnMethods = {"stagePromotionAssertProjectData"},groups = {"Projects - Publish Deploy"},description = "Assert workflow data after deployment")
	public void stagePromotionAssertWorkflowData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deployedProject,webDriver,"Click on deployed project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.workflow1,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.workflow1,webDriver,"Assert workflow 1 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.workflow2,webDriver,"Assert workflow 2 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.workflow3,webDriver,"Assert workflow 3 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.workflow4,webDriver,"Assert workflow 4 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.workflow5,webDriver,"Assert workflow 5 is visible");
	}

	@Test(priority = 12,dependsOnMethods = {"stagePromotionAssertWorkflowData"},groups = {"Projects - Publish Deploy"},description = "Assert flowservices data after deployment")
	public void stagePromotionAssertFlowServicesData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.flowServicesLink,webDriver,"Click on flowservices tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.flowServices,webDriver,"Wait for flowservices dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.flowServices,webDriver,"Assert flowservices visible");
	}

	@Test(priority = 13,dependsOnMethods = {"stagePromotionAssertFlowServicesData"},groups = {"Projects - Publish Deploy"},description = "Assert Rest API data after deployment")
	public void stagePromotionAssertRestAPIData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.apiTabLink,webDriver,"Click on API tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restAPI,webDriver,"Wait for REST API dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restAPI,webDriver,"Assert REST API visible");
	}

	@Test(priority = 14,dependsOnMethods = {"stagePromotionAssertRestAPIData"},groups = {"Projects - Publish Deploy"},description = "Assert Soap API data after deployment")
	public void stagePromotionAssertSOAPAPIData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.SoapAPILink,webDriver,"Select Soap API option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapAPI,webDriver,"Wait for Soap API dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapAPI,webDriver,"Assert Soap API visible");
	}

	@Test(priority = 15,dependsOnMethods = {"stagePromotionAssertSOAPAPIData"},groups = {"Projects - Publish Deploy"},description = "Assert connectors data after deployment")
	public void stagePromotionAssertConnectorsData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.connectorsLink,webDriver,"Click on connectors tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.addedConnection,webDriver,"Wait for connectors dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.addedConnection,webDriver,"Assert Jotform connection visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.RestConnectorLink,webDriver,"Click on Rest connector link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.RestConnector,webDriver,"Assert Rest connector visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.SoapConnectorLink,webDriver,"Click on Soap connector link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.SoapConnector,webDriver,"Assert Soap connector visible");
	}

	@Test(priority = 16,dependsOnMethods = {"stagePromotionAssertConnectorsData"},groups = {"Projects - Publish Deploy"},description = "Assert configuration after deployment")
	public void stagePromotionAssertConfigurationsData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configurationsLink,webDriver,"Click on configuration tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configurationWorkflow,webDriver,"Click on workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configurtionConnectionLink,webDriver,"Click on connection link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.JotformConnectorAccount,webDriver,"Assert jotform connector account visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.triggersConfigurationLink,webDriver,"Click on trigger option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.JotformTrigger,webDriver,"Assert jotform trigger visible");
	}

	@Test(priority = 17,dependsOnMethods = {"stagePromotionAssertConfigurationsData"},groups = {"Projects - Publish Deploy"},description = "Assert deployment version after deployment")
	public void stagePromotionAssertVersionData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymentLink,webDriver,"Click on deployments link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.versionData,webDriver,"Assert version data is visible");
	}

	@Test(priority = 18,dependsOnMethods = {"stagePromotionAssertConfigurationsData"},groups = {"Projects - Publish Deploy"},description="Verify project Republish")
	public void projectRepublish()
	{
		//Test Steps
		webDriver.navigate().to(MavenArgumentConstants.SOURCE_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectSearchTextBox,PropertiesData.readInputData("projectPublishName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectPublishProject,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Page scroll down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");

		//Dependent assets Page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		JavascriptExecutor javascriptExecutor1 = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor1,"Page scroll down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.asset1,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");

		//RePublish Project
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymentNameTextBox,webDriver,"Wait for publish settings modal");		
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymentNameTextBox,"Version2",webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectPublishMessage,webDriver,"Wait for publish messsage disappear",softAssert);
	}

	@Test(priority = 19,dependsOnMethods = {"projectRepublish"},groups = {"Projects - Publish Deploy"},description="Verify Skip and Override UI Functionality")
	public void verifyProjectReDeployed() throws InterruptedException 
	{
		//Test Steps to search project and go to deployment form
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectSearchTextBox,PropertiesData.readInputData("projectPublishName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectDeployedName,webDriver,"Assert project deployed is shown");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.projectDeployedName,webDriver,"Click on project name");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymenytab, webDriver, "Wait till Deployment tab is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deploymenytab, webDriver, "Click on Deployment Tab");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deployoption, webDriver, "Wait till Deploy Option is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deployoption, webDriver, "Click on Deploy Option");

		//To Check Skip and Override Capabilities is enabled or disabled AND Functional UI of same
		//Login user first and extract authtoken,cookie and csrf token
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.DESTINATION_TENANT_USERNAME,
				MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, 
				"Login account to get authtoken, cookie and CSRF token to make tenant capabilities API request");
		authtoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken");
		cookie = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie");
		csrftoken = HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf");

		//To Send capabilities call and check status of Skip and override feature
		responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.DESTINATION_TENANT_URL+"/integration/rest/tenant/capabilities",
				authtoken,
				cookie,
				csrftoken,
				"Make tenant capabilities request");

		//extract skip and override status
		skipandoverridestatus =  HttpURLConnHelperFuntions.getJsonKeyBooleanValue(responseBody, "integration", "tenantCapabilities","skipAssetsEnabled");

		if(skipandoverridestatus==true)
		{
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.skipassetsString,webDriver,"Wait for Skip Assets modal");

			//To check Status of Checkboxes for All assets,Flowservice,Rest and Soap API are already not selected
			if(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsskipcheckbox.isSelected()== false && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allflowservicecheckbox.isSelected()== false
					&& com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allrestapicheckbox.isSelected()== false && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allsoapapicheckbox.isSelected()== false)
			{
				ExtentTestManager.getTest().pass("Checkboxes of All assets,Flowservice,Rest and Soap API are already not selected");
			}
			else
			{
				ExtentTestManager.getTest().fail("Please check below errors.Checkboxes of All assets,Flowservice,Rest and Soap API are already selected");
				ExtentTestManager.getTest().info("Checkboxes of All assets,Flowservice,Rest and Soap API are already selected");
			}

			/*To check Functionality of select all option
			 This will check once user click on all assets option then all available assets from available list are get selected or not */

			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allAssetsOption,webDriver,"Click on All assets options");
			if(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsskipcheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allflowservicecheckbox.isSelected()== true &&
					com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allrestapicheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allsoapapicheckbox.isSelected()== true)
			{
				ExtentTestManager.getTest().pass("Checkboxes of All assets,Flowservice,Rest and Soap API are selected");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.flowservicedropdownicon,webDriver,"Click on Flowservice Dropdown arrow");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.getDateflow,webDriver,"Wait for Flowservice Records");
				SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.usedbyflowserive,webDriver,"Assert Dependet assets of FlowService");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restapidropdownicon,webDriver,"Click on Rest API Dropdown arrow");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restApi,webDriver,"Wait for Rest API records");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapapidropdownicon,webDriver,"Click on Soap API Dropdown arrow");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapApi,webDriver,"Wait for Soap Api records");

				//IF condition for actual assets selections
				if(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.getdateflowserviceheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restapicheckbox.isSelected()== true &&
						com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapapicheckbox.isSelected()== true)
				{
					ExtentTestManager.getTest().pass("Checkboxes of All assets under Flowservice,Rest and Soap API are selected");
				}
				else
				{
					ExtentTestManager.getTest().fail("Please check below errors.Checkboxes of All assets under Flowservice,Rest and Soap API are not selected");
					ExtentTestManager.getTest().info("Checkboxes of All assets under Flowservice,Rest and Soap API are not selected");
				}	
			}
			else
			{
				ExtentTestManager.getTest().fail("Please check below errors.Checkboxes of All assets,Flowservice,Rest and Soap API are not selected");
				ExtentTestManager.getTest().info("Checkboxes of All assets,Flowservice,Rest and Soap API are not selected");
			}

			/*To check Next and Previous option
			 This will check checkboxes state get maintained or not when user get backs from nexr page */
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.nextButton,webDriver,"Click on Next button");
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.configureAccount,webDriver,"Wait for configure account modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.previousButton,webDriver,"Click on Previosu button");
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.skipassetsString,webDriver,"Wait for Skip Assets modal");

			//To check State of Previosuly selected all optionn
			//To check Status of Checkboxes for All assets,Flowservice,Rest and Soap API are selected
			if(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsskipcheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allflowservicecheckbox.isSelected()== true &&
					com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allrestapicheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allsoapapicheckbox.isSelected()== true)
			{
				ExtentTestManager.getTest().pass("Checkboxes of All assets,Flowservice,Rest and Soap API are selected");
			}
			else
			{
				ExtentTestManager.getTest().fail("Please check below errors.Checkboxes of All assets,Flowservice,Rest and Soap API are not selected");
				ExtentTestManager.getTest().info("Checkboxes of All assets,Flowservice,Rest and Soap API are not selected");
			}

			/*To check Partial select option
			 This will check when user unselect any option then only that option get unselect or not */
			//Unselect Flowservice and check functionality
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.flowserviceOption,webDriver,"Click on FlowService Option");

			if(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.assetsskipcheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allflowservicecheckbox.isSelected()== false &&
					com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allrestapicheckbox.isSelected()== true && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.allsoapapicheckbox.isSelected()== true)
			{
				ExtentTestManager.getTest().pass("Checkboxes of All assets,Rest and Soap API are selected and Checkbox of FlowService is not selected");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.flowservicedropdownicon,webDriver,"Click on Flowservice Dropdown arrow");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.getDateflow,webDriver,"Wait for Flowservice Records");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restapidropdownicon,webDriver,"Click on Rest API Dropdown arrow");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restApi,webDriver,"Wait for Rest API records");
				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapapidropdownicon,webDriver,"Click on Soap API Dropdown arrow");
				SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapApi,webDriver,"Wait for Soap Api records");
				Thread.sleep(5000);

				//IF condition for actual assets selections
				if(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.getdateflowserviceheckbox.isSelected()== false && com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.restapicheckbox.isSelected()== true &&
						com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.soapapicheckbox.isSelected()== true)
				{
					ExtentTestManager.getTest().pass("Checkboxes of All assets under Rest and Soap API are selected and Flowservice are not selected");
				}
				else
				{
					ExtentTestManager.getTest().fail("Please check below errors.Checkboxes of All assets under Rest and Soap API are selected and Flowservice are not selected");
					ExtentTestManager.getTest().info("Checkboxes of All assets under Flowservice,Rest and Soap API are not selected");
				}	
			}
			else
			{
				ExtentTestManager.getTest().fail("Please check below errors.Checkboxes of All assets,Rest and Soap API are selected and Checkbox of FlowService is not selected");
				ExtentTestManager.getTest().info("Checkboxes of All assets under Rest and Soap API are selected and Flowservice are not selected");
			}
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.canceloption,webDriver,"Click Cancel option");
			SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.PublishDeployTests.ProjectPublishDeployLocators.deployoption, webDriver, "Wait till Deploy Option is clickable");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}
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