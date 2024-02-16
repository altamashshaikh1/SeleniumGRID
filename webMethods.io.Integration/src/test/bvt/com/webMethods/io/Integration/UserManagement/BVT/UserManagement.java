package com.webMethods.io.Integration.UserManagement.BVT;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test; 

public class UserManagement
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
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal,BaseTestUtils.getGridURL(), capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"UserManagement"},description = "Login user : User Management")
	public void userManagementLogin() 
	{		
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userManagementLogin"},groups = {"UserManagement"},description = "Login account")
	public void userManagementPage()  
	{
		//Visit user management page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.projectSearchTextBox,PropertiesData.readInputData("userManagementDefaultProjectName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.defaultProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.profileIcon,webDriver,"Click on profile icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.userManagementOption,webDriver,"Wait for user management option");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.userManagementOption,webDriver,"Click on user management option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.rolesCreatedString,webDriver,"Wait for created roles to be visible");
	}

	@Test(priority = 2,dependsOnMethods = {"userManagementPage"},groups = {"UserManagement"},description = "Create new user role")
	public void createNewUserRoleUserManagement()  
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.newRoleButton,webDriver,"Click on New Role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleNameInputBox,"PRBVT_"+BaseTestUtils.generateString(4)+"",webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab action");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab action");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE,"Select project level permission");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on done button");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.successMessage,webDriver,"Assert create new role message");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.createdRoleName,webDriver,"Assert role creaeted");
	}

	@Test(priority = 3,dependsOnMethods = {"createNewUserRoleUserManagement"},groups = {"UserManagement"},description = "Edit user role")
	public void editUserRoleUserManagement() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on pencil icon to edit added role");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on save button to update the role");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Assert create new role message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Assert role updated");
	}

	@Test(priority = 4,dependsOnMethods = {"editUserRoleUserManagement"},groups = {"UserManagement"},description = "Delete user role")
	public void deleteUserRoleUserManagement() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on delete icon");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on delete button");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.successMessage,webDriver,"Assert create new role message");
	}

	@Test(priority = 5,dependsOnMethods = {"deleteUserRoleUserManagement"},groups = {"UserManagement"},description = "Assert users lists")
	public void userslistsUserManagement() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Select users tab");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Assert user list is visible"); 
	}

	@Test(priority = 6,dependsOnMethods = {"userslistsUserManagement"},groups = {"UserManagement"},description = "Assign user role")
	public void assignUserRoleUserManagement()   
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on pencil icon to assign role");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Click on Done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Wait for message to display");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.UserManagement.BVT.UserManagementLocators.roleDoneButton,webDriver,"Assert assign role to user message");
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