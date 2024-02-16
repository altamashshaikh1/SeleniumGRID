package com.webMethods.io.Integration.ProjectTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import org.openqa.selenium.Keys;

public class ProjectCRUD
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

	@Test(priority = 0,groups = {"Projects"},description = "Projects : Verify User Account Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Projects"},description = "Create new project")
	public void createNewProjectTest()
	{
		//Test Steps		
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.addNewProject,webDriver,"Click on Add new project icon");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.newProjectModalInputLabel,PropertiesData.readInputData("createNewProjectName")+BaseTestUtils.generateString(4),webDriver,"Input project name");       
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.newProjectModalCreateButton,webDriver,"Click on create new button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectCreationSuccessMessage,PropertiesData.readAssertionData("projectCreationMesssage"),webDriver,"Assert project created",softAssert);
	}

	@Test(priority = 2,dependsOnMethods = {"createNewProjectTest"},groups = {"Projects"},description = "Project dashboard assets")
	public void verifyProjectDashboardAssetsTest()
	{
		//Test Steps
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.newWorkflowLink,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.flowserviceTab,webDriver,"Assert flow services present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.apiTab,webDriver,"Assert api tab present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.connectorTab,webDriver,"Assert connector tab present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.configurationsTab,webDriver,"Assert configuration tab present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.deploymentTab,webDriver,"Assert deployement present");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.newWorkflowLink,webDriver,"Assert new workflow link");
	}

	@Test(priority = 3,dependsOnMethods = {"verifyProjectDashboardAssetsTest"},groups = {"Projects"},description = "Edit project")
	public void editProjectTest()
	{
		//Test Steps   
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectDashboardLink,webDriver,"Click on Projects link to visit project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.searchBox,PropertiesData.readInputData("searchProjectText"),webDriver,"Search created project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter to search created project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.createdProjectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectEditLink,webDriver,"Click on edit link");
		SeleniumWebElementsUtils.clear(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.editProjectTextBox,webDriver,"Clear project name");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.editProjectTextBox,PropertiesData.readInputData("editProjectName"),webDriver,"Enter project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.newProjectModalCreateButton,webDriver,"Click on save button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectUpdateSuccessMessage,PropertiesData.readAssertionData("projectUpdateMessage"),webDriver,"Assert project update message",softAssert);	   
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectUpdatedName,webDriver,"Assert project updated");
	}

	@Test(priority = 4,dependsOnMethods = {"editProjectTest"},groups = {"Projects"},description = "Delete project")
	public void deleteProjectTest()
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.createdProjectEllipsis,webDriver,"Click on project ellipsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectDeleteLink,webDriver,"Click on delete link");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectDeleteButton,webDriver,"Click on delete project button");
		SeleniumWebElementsUtils.textContains(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectDeleteSuccessMessage,PropertiesData.readAssertionData("projectDeleteMessage"),webDriver,"Assert project delete message",softAssert);
	}

	@Test(priority = 5,dependsOnMethods = {"deleteProjectTest"},groups = {"Projects"},description = "Search project")
	public void searchProjectTest()
	{
		//Test Steps   
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.notificationMessageString,webDriver,"Wait for loader to disabled");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectSearchTextBox,webDriver,"Clear search box");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.closeSearchIcon,webDriver,"Clear search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.projectSearchTextBox,"Default",webDriver,"Enter project name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectTests.ProjectCRUDLocators.searchedProjectName,webDriver,"Assert project searched");
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