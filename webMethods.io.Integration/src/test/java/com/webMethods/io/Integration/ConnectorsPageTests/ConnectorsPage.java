package com.webMethods.io.Integration.ConnectorsPageTests;

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

public class ConnectorsPage
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

	@Test(priority = 0,groups = {"ConnectorsDashboard"},description = "Login user : Connectors Page")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ConnectorsDashboard"},description = "Visit connectors page")
	public void visitConnectorsPage()
	{
		//Visit connectors page 
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.projectSearchTextBox,PropertiesData.readInputData("connConfigProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.defaultProject,webDriver,"Click on default project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.importButton,webDriver,"Wait for workflow dashboard");
	}

	@Test(priority = 2,dependsOnMethods = {"visitConnectorsPage"},groups = {"ConnectorsDashboard"},description = "Predefined page")
	public void connPagePredefinedPageTest()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.connectorsLinkTab,webDriver,"Click on connectors page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.addedTrelloAuth,webDriver,"Wait for predefined page to get visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.addedTrelloAuth,webDriver,"Assert predefined page is loaded and assets are visible");		
	}

	@Test(priority = 3,dependsOnMethods = {"connPagePredefinedPageTest"},groups = {"ConnectorsDashboard"},description = "REST connector page")
	public void restConnectorPageTest()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.restConnectorLink,webDriver,"Click on REST connector link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.restConnector,webDriver,"Wait for REST connector page to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.restConnector,webDriver,"Assert REST connector page visible");
	}

	@Test(priority = 4,dependsOnMethods = {"restConnectorPageTest"},groups = {"ConnectorsDashboard"},description = "Soap connector page")
	public void soapConnectorPageTest()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.soapConnectorLink,webDriver,"Click on Soap connector link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.soapConnector,webDriver,"Wait for Soap connector page to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.soapConnector,webDriver,"Assert Soap connector page visible");
	}

	@Test(priority = 5,dependsOnMethods = {"soapConnectorPageTest"},groups = {"ConnectorsDashboard"},description = "On premises connector page")
	public void onPremisisConnectorPageTest()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.onPremisesConnectorLink,webDriver,"Click on On-Premises connector link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.onPremisesConnector,webDriver,"Wait for On-Premises connector page to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.onPremisesConnector,webDriver,"Assert On-Premises connector page visible");
	}

	@Test(priority = 6,dependsOnMethods = {"onPremisisConnectorPageTest"},groups = {"ConnectorsDashboard"},description = "Flat file connector page")
	public void flatFileConnectorPageTest()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.flatFileConnectorLink,webDriver,"Click on Flatfile connector link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.flatFileConnector,webDriver,"Wait for Flatfile connector page to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ConnectorsPageTests.ConnectorsPageTestLocators.flatFileConnector,webDriver,"Assert Flatfile connector page visible");
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