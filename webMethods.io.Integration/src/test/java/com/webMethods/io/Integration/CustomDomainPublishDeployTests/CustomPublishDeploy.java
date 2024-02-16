package com.webMethods.io.Integration.CustomDomainPublishDeployTests;

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

public class CustomPublishDeploy 
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

	@Test(priority = 0,groups = {"CustomDomain ProjectPublisDeploy"},description = "Login user")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Select project assets")
	public void stagePromotionSelectAssets() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectSearchTextBox,PropertiesData.readInputData("projectPublishName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectPublishProject,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.assetsModalString,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Page scroll down");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 2,dependsOnMethods = {"stagePromotionSelectAssets"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Confirm assets dependencies")
	public void stagePromotionVerifyDependencies()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver,javascriptExecutor,"Page scroll down");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.asset1,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.asset2,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.asset3,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.asset4,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.asset5,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.asset6,webDriver,"Verify dependent assets");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 3,dependsOnMethods = {"stagePromotionVerifyDependencies"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Publish project")
	public void stagePromotionPublishProject() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.deploymentNameTextBox,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.deploymentNameTextBox,PropertiesData.readInputData("deploymentName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab keyboard actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab keyboard actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Keyboard sendkeys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select destination tenant");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
	}

	@Test(priority = 4,dependsOnMethods = {"stagePromotionPublishProject"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert first project deployed visible")
	public void stagePromotionVerifyDeployment() 
	{
		//Test Steps
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.ssoLoginLink,webDriver,"Click on SSO login link");	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectSearchTextBox,PropertiesData.readInputData("projectPublishName"),webDriver,"Input published project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectDeployedName,webDriver,"Assert project deployed is shown");
	}

	@Test(priority = 5,dependsOnMethods = {"stagePromotionVerifyDeployment"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Create project in IC")
	public void stagePromotionCreateNewProject() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectDeployedName,webDriver,"Click on Deployed project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.saveAndContinueButton,webDriver,"Click on Save and continue button to create project in IC");
	}

	@Test(priority = 6,dependsOnMethods = {"stagePromotionCreateNewProject"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Create accounts")
	public void stagePromotionCreateNewAccount() throws InterruptedException
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.assetModal, webDriver, "Wait for Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.nextButton,webDriver,"Click next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configureAccountString,webDriver,"Wait for configure account modal");
		Thread.sleep(4000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.addAccountIcon,webDriver,"Click on Add icon to add jotform account");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.jotformAPIKeyTextBox,PropertiesData.readInputData("projectDeployjotformAPIKey"),webDriver,"Enter jotform API key");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.addButtonConnection,webDriver,"Click on Add button to add account");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.triggerAccountName,webDriver,"Wait for account added is displayed");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 7,dependsOnMethods = {"stagePromotionCreateNewAccount"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Create trigger")
	public void stagePromotionCreateNewTrigger() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configureTriggerString,webDriver,"Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.dropdownAccountTrigger,webDriver,"Click on drop down to add trigger account");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.triggerAccountName,webDriver,"Select jotform account");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.jotformTextBox,PropertiesData.readInputData("projectDeployJotformFormID"),webDriver,"Enter jotform ID");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 8,dependsOnMethods = {"stagePromotionCreateNewTrigger"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Create project parameters")
	public void stagePromotionCreateNewProjectParams() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configureParameter,webDriver,"Wait for configure project parameter modal");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configureParameter,webDriver,"Assert project params created");   
	}

	@Test(priority = 9,dependsOnMethods = {"stagePromotionCreateNewProjectParams"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Project deploy")
	public void stagePromotionProjectDeploy() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.deployButton,webDriver,"Click on deploy button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectDeployementMessage,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.projectDeployementMessage,PropertiesData.readAssertionData("projectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
	}

	@Test(priority = 10,dependsOnMethods = {"stagePromotionProjectDeploy"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert project data after deployment")
	public void stagePromotionAssertProjectData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.deployedProject,webDriver,"Assert project deployed");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.numberOfWorkflows,webDriver,"Assert workflow count shown on project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.numberOfFlows,webDriver,"Assert flow services count shown on project");
	}

	@Test(priority = 11,dependsOnMethods = {"stagePromotionAssertProjectData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert workflow data after deployment")
	public void stagePromotionAssertWorkflowData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.deployedProject,webDriver,"Click on deployed project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.workflow1,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.workflow1,webDriver,"Assert workflow 1 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.workflow2,webDriver,"Assert workflow 2 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.workflow3,webDriver,"Assert workflow 3 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.workflow4,webDriver,"Assert workflow 4 is visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.workflow5,webDriver,"Assert workflow 5 is visible");
	}

	@Test(priority = 12,dependsOnMethods = {"stagePromotionAssertWorkflowData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert flowservices data after deployment")
	public void stagePromotionAssertFlowServicesData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.flowServicesLink,webDriver,"Click on flowservices tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.flowServices,webDriver,"Wait for flowservices dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.flowServices,webDriver,"Assert flowservices visible");
	}

	@Test(priority = 13,dependsOnMethods = {"stagePromotionAssertFlowServicesData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert Rest API data after deployment")
	public void stagePromotionAssertRestAPIData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.apiTabLink,webDriver,"Click on API tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.restAPI,webDriver,"Wait for REST API dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.restAPI,webDriver,"Assert REST API visible");
	}

	@Test(priority = 14,dependsOnMethods = {"stagePromotionAssertRestAPIData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert Soap API data after deployment")
	public void stagePromotionAssertSOAPAPIData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.SoapAPILink,webDriver,"Select Soap API option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.soapAPI,webDriver,"Wait for Soap API dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.soapAPI,webDriver,"Assert Soap API visible");
	}

	@Test(priority = 15,dependsOnMethods = {"stagePromotionAssertSOAPAPIData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert connectors data after deployment")
	public void stagePromotionAssertConnectorsData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.connectorsLink,webDriver,"Click on connectors tab");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.addedConnection,webDriver,"Wait for connectors dashboard");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.addedConnection,webDriver,"Assert Jotform connection visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.RestConnectorLink,webDriver,"Click on Rest connector link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.RestConnector,webDriver,"Assert Rest connector visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.SoapConnectorLink,webDriver,"Click on Soap connector link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.SoapConnector,webDriver,"Assert Soap connector visible");
	}

	@Test(priority = 16,dependsOnMethods = {"stagePromotionAssertConnectorsData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert configuration after deployment")
	public void stagePromotionAssertConfigurationsData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configurationsLink,webDriver,"Click on configuration tab");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configurationWorkflow,webDriver,"Click on workflow option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.configurtionConnectionLink,webDriver,"Click on connection link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.jotFormConnectorAccount,webDriver,"Assert jotform connector account visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.triggersConfigurationLink,webDriver,"Click on trigger option");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.jotFormTrigger,webDriver,"Assert jotform trigger visible");
	}

	@Test(priority = 17,dependsOnMethods = {"stagePromotionAssertConfigurationsData"},groups = {"CustomDomain ProjectPublisDeploy"},description = "Assert deployment version after deployment")
	public void stagePromotionAssertVersionData() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.deploymentLink,webDriver,"Click on deployments link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomDomainPublishDeployTests.CustomPublishDeployLocators.versionData,webDriver,"Assert version data is visible");
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