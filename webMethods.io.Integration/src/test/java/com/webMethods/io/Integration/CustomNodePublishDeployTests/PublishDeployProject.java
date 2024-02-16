package com.webMethods.io.Integration.CustomNodePublishDeployTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class PublishDeployProject 
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

	@Test(priority = 0,groups = {"CustomNodeJSCLIConnector:PublishDeployProject"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"CustomNodeJSCLIConnector:PublishDeployProject"},description = "Project Publish")
	public void publishProject()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectSearchBox,"Backlog NodeJS CLI", webDriver,"Enter Backlog NodeJS CLI project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.searchedProject, webDriver, "Wait for project to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectEllipsis, webDriver, "Click on ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishProjectOption, webDriver, "Wait for publish project option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishProjectOption, webDriver, "Click on publish project option");
	}

	@Test(priority = 2,dependsOnMethods = {"publishProject"},groups = {"CustomNodeJSCLIConnector:PublishDeployProject"},description = "Verify NodeJS CLI connector name")
	public void cliConnectorName()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishNextButton, webDriver, "Click on Next button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.cliConnectorName, webDriver, "Verify CLI Custom NodeJS connector name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishNextButton, webDriver, "Click on Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishName, webDriver, "Click on input button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishName,"Backlog NodeJS CLI", webDriver,"Enter Backlog NodeJS CLI publish name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Tab actions");
		SeleniumKeyBoardActionUtils.keyBoardSendKeys(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME,"Send keys");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to select devrealm2 tenant");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishButton, webDriver, "Click project publish button");      
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectPublishMessage,webDriver,"Wait for publish messsage");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.addNewProjectButton, webDriver, "Wait for add new button is visible");
	}

	@Test(priority = 3,dependsOnMethods = {"cliConnectorName"},groups = {"CustomNodeJSCLIConnector:PublishDeployProject"},description = "Deploy Publish")
	public void deployProject()
	{
		webDriver.navigate().to(MavenArgumentConstants.DESTINATION_TENANT_URL);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.emailTextBox,MavenArgumentConstants.DESTINATION_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.password,MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.createNewProjectButton, webDriver,"Verify user logged in");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectSearchBox,"Backlog NodeJS CLI", webDriver,"Enter Backlog NodeJS CLI project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.deployedProjectName, webDriver, "Click on deployed project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.saveAndContinueButton, webDriver, "Wait for save and continue button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.saveAndContinueButton, webDriver, "Click on save and continue button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.assetModal, webDriver, "Wait for Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishNextButton, webDriver, "Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.accountModal, webDriver, "Wait for Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishNextButton, webDriver, "Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.triggerModal, webDriver, "Wait for Next button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishNextButton, webDriver, "Click on Next button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.triggerModal, webDriver, "Wait for Deploy button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.publishDeployButton, webDriver, "Click on Deploy button");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.deployMessage, webDriver, "Wait for project deploy message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.deployMessage, webDriver,"Project deploy message");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectSearchBox, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.projectSearchBox,"Backlog NodeJS CLI", webDriver,"Enter Backlog NodeJS CLI project to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.CustomNodePublishDeployTests.PublishDeployProjectLocators.deployedProjectName, webDriver,"Verify project deployed with CLI Connector");
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