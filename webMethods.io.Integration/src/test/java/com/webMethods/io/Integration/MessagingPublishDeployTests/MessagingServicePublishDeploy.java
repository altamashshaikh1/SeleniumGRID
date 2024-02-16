package com.webMethods.io.Integration.MessagingPublishDeployTests;

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

public class MessagingServicePublishDeploy
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

	@Test(priority = 0,groups = {"StagPromotion MAAS"},description = "Login user : StagPromotion (MAAS)")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"StagPromotion MAAS"},description = "Select project assets")
	public void maasStagPromotionSelectAssets() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.addNewButtonString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectSearchTextBox,PropertiesData.readInputData("messagingWorkflowProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectPublishProject,webDriver,"Wait for project publish option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectPublishProject,webDriver,"Click on Project publish option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.assetsLabel,webDriver,"Wait for assets modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.assetsModal,webDriver,"Assert all assets are visible");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 2,dependsOnMethods = {"maasStagPromotionSelectAssets"},groups = {"StagPromotion MAAS"},description = "Confirm assets dependencies")
	public void maasStagPromotionVerifyDependencies()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.dependenciesModalString,webDriver,"Wait for dependencies modal to be visible");
		JavascriptExecutor javascriptExecutorOne = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorOne,"Scroll page down"); 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.assetDependentModal,webDriver,"Verify dependent assets");
		JavascriptExecutor javascriptExecutorTwo = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutorTwo,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.nextButton,webDriver,"Click on Next button");
	}

	@Test(priority = 3,dependsOnMethods = {"maasStagPromotionVerifyDependencies"},groups = {"StagPromotion MAAS"},description = "Publish project")
	public void maasStagPromotionProjectPublish() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.publishSettingsModal,webDriver,"Wait for publish settings modal");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.deploymentNameTextBox,PropertiesData.readInputData("messagingWorkflowDeploymentName"),webDriver,"Enter deployment name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver, "devrealm2", "Enter domain name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.publishButton,webDriver,"Click on Publish button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
	}

	@Test(priority = 4,dependsOnMethods = {"maasStagPromotionProjectPublish"},groups = {"StagPromotion MAAS"},description = "Create project in IC")
	public void maasStagPromotionCreateProjectInIC() 
	{
		//Test Steps
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.emailTextBox,MavenArgumentConstants.DESTINATION_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.password,MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectSearchTextBox,PropertiesData.readInputData("messagingWorkflowProjectName"),webDriver,"Input published project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectDeployedName,webDriver,"Click on Deployed project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.saveAndContinueButton,webDriver,"Click on Save and continue button to create project in IC");
	}

	@Test(priority = 5,dependsOnMethods = {"maasStagPromotionCreateProjectInIC"},groups = {"StagPromotion MAAS"},description = "Deploy project")
	public void maasStagPromotionDeployProject()
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
			SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.skipassetsString,webDriver,"Wait for configure account modal");
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.nextButton,webDriver,"Click on Next button");
		}
		else 
		{
			ExtentTestManager.getTest().info("Skip and Override Feature is disabled in this tenant");
		}

		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.configureAccountString,webDriver,"Wait for configure account modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.nextButton,webDriver,"Click on next button");
		JavascriptExecutor javascriptExecutor = null;
		SeleniumPageScrollUtils.scrollPageDown(webDriver, javascriptExecutor,"Scroll page down"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.nextButton,webDriver,"Click on next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.deployButton,webDriver,"Click on deploy button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.yesdeploy,webDriver,"Click on Yes button to proceed with the deployment");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectDeployementMessage,webDriver,"Wait for project deployment message");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectDeployementMessage,PropertiesData.readAssertionData("messagingServiceProjectDeploymentMessage"),webDriver,"Assert project deployment message",softAssert);
	}

	@Test(priority = 6,dependsOnMethods = {"maasStagPromotionDeployProject"},groups = {"StagPromotion MAAS"},description = "Project visible after deployement")
	public void maasStagPromotionProjectDeployed() 
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.projectSearchTextBox,PropertiesData.readInputData("messagingWorkflowProjectName"),webDriver,"Input published project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.deployedProjectName,webDriver,"Assert project deployed with messaging data");
	}

	@Test(priority = 7,dependsOnMethods = {"maasStagPromotionProjectDeployed"},groups = {"StagPromotion MAAS"},description = "Workflows visible after deployement")
	public void maasStagPromotionWorkflowDeployed() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.totalWorkflows,webDriver,"Assert workflow deployed");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.deployedProjectName,webDriver,"Click on deployed project");
	}

	@Test(priority = 8,dependsOnMethods = {"maasStagPromotionWorkflowDeployed"},groups = {"StagPromotion MAAS"},description = "Subscriber visible after deployement")
	public void maasStagPromotionSubscribed()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.addNewWorkflowButtonString,webDriver,"Wait for workflow dashboard");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.messageLink,webDriver,"Click on messaging link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.subscriberLinkText,webDriver,"Click on subscriber link");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MessagingPublishDeployTests.MessagingServicePublishTestLocators.subscriberList,webDriver,"Assert all subscriber visible after deployment");
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