package com.webMethods.io.Integration.RolesBackLogTests;

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

public class RolesAndPermissionBacklogTests 
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

	@Test(priority = 0,groups = {"RolesAndPermissionBacklogTest"},description = "Verify login user : RolesAndPermissionBacklogTests")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver, "Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify Create role with only Read permission for Default project")
	public void createRoleReadOnly() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.profileButton, webDriver, "Click on profile");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.userManagementOption, webDriver,"Click on User management option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.allRolesListing, webDriver, "Wait for all project loading");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleButton, webDriver,"Click on new role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleNameInputBox,"BackLogReadOnlyRole", webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action"); 
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ReadOnly for Default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAddedMessage, webDriver,"Verify role added message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleReadOnly, webDriver,"Verify role added");
	}

	@Test(priority = 2,dependsOnMethods = {"createRoleReadOnly"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify Create role with only Execute permission for Default project")
	public void createRoleExecuteOnly() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleButton, webDriver,"Click on new role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleNameInputBox,"BackLogExecuteOnlyRole", webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ExecuteOnly for Default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAddedMessage, webDriver,"Verify role added message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleExecuteOnly, webDriver,"Verify role added");
	}

	@Test(priority = 3,dependsOnMethods = {"createRoleExecuteOnly"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify Create role with Read/Write/Execute permission for Default project")
	public void createRoleReadWriteExecuteOnly() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleButton, webDriver,"Click on new role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleNameInputBox,"BackLogRoleAll", webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ReadOnly for Default project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select Write for Default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAddedMessage, webDriver,"Verify role added message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleAll, webDriver,"Verify role added");
	}

	@Test(priority = 4,dependsOnMethods = {"createRoleReadWriteExecuteOnly"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify Create role with Readonly permission for other project's")
	public void createRoleReadOnlyOtherProjects() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleButton, webDriver,"Click on new role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleNameInputBox,"BackLogReadOnlyRoleOtherProj", webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ReadOnly for Other project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAddedMessage, webDriver,"Verify role added message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newReadOnlyOtherProject, webDriver,"Verify role added");
	}

	@Test(priority = 5,dependsOnMethods = {"createRoleReadOnlyOtherProjects"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify Create role with only Execute permission for other project's")
	public void createRoleExecuteOnlyOtherProjects() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleButton, webDriver,"Click on new role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleNameInputBox,"BackLogExecuteOnlyRoleOtherProj", webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ExecuteOnly for Other project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAddedMessage, webDriver,"Verify role added message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newExeOnlyOtherProject, webDriver,"Verify role added");
	}

	@Test(priority = 6,dependsOnMethods = {"createRoleExecuteOnlyOtherProjects"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify Create role with Read/Write/Execute permission for other project's")
	public void createRoleReadWriteExecuteOtherProjects() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleButton, webDriver,"Click on new role button");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleNameInputBox,"BackLogAllRoleOtherProj", webDriver,"Enter role name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ReadOnly for Default project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB, "Tab action");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE, "Select ExecuteOnly for Default project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAddedMessage, webDriver,"Verify role added message");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.newRoleAllOtherProject, webDriver,"Verify role added");
	}

	@Test(priority = 7,dependsOnMethods = {"createRoleReadWriteExecuteOtherProjects"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ReadOnlyRole update")
	public void updateRoleReadOnlyDefaultProject() 
	{
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogReadOnlyRole", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRolePencilIcon, webDriver,"Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleUpdateMessage, webDriver,"Verify ReadOnlyRole update message");
	}

	@Test(priority = 8,dependsOnMethods = {"updateRoleReadOnlyDefaultProject"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ExecuteOnlyRole update")
	public void updateRoleWriteOnlyDefaultProject() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogExecuteOnlyRole", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRolePencilIcon, webDriver,"Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleUpdateMessage, webDriver,"Verify ExecuteOnlyRole update message");
	}

	@Test(priority = 9,dependsOnMethods = {"updateRoleWriteOnlyDefaultProject"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify RoleAll update")
	public void updateRoleAllDefaultProject()
	{		
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogRoleAll", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRolePencilIcon, webDriver,"Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleUpdateMessage, webDriver,"Verify RoleAll update message");
	}

	@Test(priority = 10,dependsOnMethods = {"updateRoleAllDefaultProject"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ReadOnlyRoleOtherProj update")
	public void updateRoleReadOnlyOtherProject() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogReadOnlyRoleOtherProj", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRolePencilIcon, webDriver,"Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleUpdateMessage, webDriver,"Verify ReadOnlyRoleOtherProj update message");
	}

	@Test(priority = 11,dependsOnMethods = {"updateRoleReadOnlyOtherProject"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ExecuteOnlyRoleOtherProj update")
	public void updateRoleWriteOnlyOtherProject() 
	{		
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogExecuteOnlyRoleOtherProj", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRolePencilIcon, webDriver,"Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleUpdateMessage, webDriver,"Verify ExecuteOnlyRoleOtherProj update message");
	}

	@Test(priority = 12,dependsOnMethods = {"updateRoleWriteOnlyOtherProject"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify AllRoleOtherProj update")
	public void updateRoleAllOtherProject() 
	{		
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogAllRoleOtherProj", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRolePencilIcon, webDriver,"Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleSaveButton, webDriver, "Click on save button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleUpdateMessage, webDriver,"Verify AllRoleOtherProj update message");
	}

	@Test(priority = 13,dependsOnMethods = {"updateRoleAllOtherProject"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ReadOnlyRole deleted")
	public void deleteRoleReadOnlyRole()
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogReadOnlyRole", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRoleDeleteIcon, webDriver,"Click on delete icon to delete ReadOnlyRole");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.deleteRoleButton, webDriver, "Click delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDeleteMessage, webDriver, "Verify ReadOnlyRole deleted message");
	}

	@Test(priority = 14,dependsOnMethods = {"deleteRoleReadOnlyRole"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ExecuteOnlyRole deleted")
	public void deleteRoleExecuteOnlyRole()
	{		
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogExecuteOnlyRole", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRoleDeleteIcon, webDriver,"Click on delete icon to delete ExecuteOnlyRole");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.deleteRoleButton, webDriver, "Click delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDeleteMessage, webDriver, "Verify ExecuteOnlyRole deleted message");
	}

	@Test(priority = 15,dependsOnMethods = {"deleteRoleExecuteOnlyRole"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify role RoleAll")
	public void deleteRoleAll()
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogRoleAll", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRoleDeleteIcon, webDriver,"Click on delete icon to deleteRoleAll");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.deleteRoleButton, webDriver, "Click delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDeleteMessage, webDriver, "Verify role RoleAll message");
	}

	@Test(priority = 16,dependsOnMethods = {"deleteRoleAll"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify ReadOnlyRoleOtherProj deleted")
	public void deleteRoleReadOnlyRoleOtherProj()
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogReadOnlyRoleOtherProj", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRoleDeleteIcon, webDriver,"Click on delete icon to delete ReadOnlyRoleOtherProj");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.deleteRoleButton, webDriver, "Click delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDeleteMessage, webDriver, "Verify ReadOnlyRoleOtherProj deleted message");
	}

	@Test(priority = 17,dependsOnMethods = {"deleteRoleReadOnlyRoleOtherProj"},groups = {"RolesAndPermissionBacklogTest"},description ="Verify ExecuteOnlyRoleOtherProj deleted")
	public void deleteRoleExecuteOnlyRoleOtherProj()
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogExecuteOnlyRoleOtherProj", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRoleDeleteIcon, webDriver,"Click on delete icon to delete ExecuteOnlyRoleOtherProj");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.deleteRoleButton, webDriver, "Click delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDeleteMessage, webDriver, "Verify ExecuteOnlyRoleOtherProj deleted message");
	}

	@Test(priority = 18,dependsOnMethods = {"deleteRoleExecuteOnlyRoleOtherProj"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify AllRoleOtherProj deleted")
	public void deleteRoleAllRoleOtherProj()
	{	
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.modalOverlay, webDriver,"Wait for modal overlay to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox, webDriver,"Click on inputbox");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.closeSearchResult, webDriver,"Clear inputbox");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"BackLogAllRoleOtherProj", webDriver,"Enter role name to be searched");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Enter action to search created role");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstRoleDeleteIcon, webDriver,"Click on delete icon to delete AllRoleOtherProj");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.deleteRoleButton, webDriver, "Click delete button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDeleteMessage, webDriver, "Verify AllRoleOtherProj deleted message");
	}

	@Test(priority = 19,dependsOnMethods = {"deleteRoleAllRoleOtherProj"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify readonly role assigned to user")
	public void assignReadOnlyRole()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.usersOption, webDriver,"Click on users option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.userEmailLists, webDriver,"Wait for all lists to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.searchRoleInputBox,"altamashwmiotest2@gmail.com", webDriver,"Enter user email to be searched");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.firstUserEditOption, webDriver, "Click on pencil icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleDoneButton, webDriver, "Click on done button to apply role");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.roleAssignedMessage, webDriver,"Verify role assigned");
	}

	@Test(priority = 20,dependsOnMethods = {"assignReadOnlyRole"},groups = {"RolesAndPermissionBacklogTest"},description = "Verify user logged in with readonly role applied")
	public void loginUserWithReadOnlyRole()
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.profileButton, webDriver, "Click on profile icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.LogOutOption, webDriver,"Logout user");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.login_block, webDriver, "Wait for login black to be visible");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.usernameInput,PropertiesData.readInputData("rolesAppliedUserID"), webDriver, "Enter user id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.passwordInput,PropertiesData.readInputData("rolesAppliedUserPassword"), webDriver, "Enter password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RolesBackLogTests.RolesAndPermissionBacklogTestsLocators.loginButton, webDriver,"Click on login button");
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