package com.webMethods.io.Integration.RolesBackLogTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RolesAndPermissionBacklogTestsLocators 
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//i[@class='delite-icon dlt-icon-profile']")
	public static WebElement profileButton;

	@FindBy(linkText = "User management")
	public static WebElement userManagementOption;

	@FindBy(xpath = "//button[normalize-space()='New Role']")
	public static WebElement newRoleButton;

	@FindBy(xpath = "//input[@name='role_name']")
	public static WebElement roleNameInputBox;

	@FindBy(xpath = "//button[normalize-space()='Done']")
	public static WebElement roleDoneButton;

	@FindBy(xpath = "//*[text()='Role added successfully.']")
	public static WebElement roleAddedMessage;

	@FindBy(xpath = "//span[normalize-space()='BackLogReadOnlyRole']")
	public static WebElement newRoleReadOnly;

	@FindBy(xpath = "//span[normalize-space()='BackLogExecuteOnlyRole']")
	public static WebElement newRoleExecuteOnly;

	@FindBy(xpath = "//span[normalize-space()='BackLogRoleAll']")
	public static WebElement newRoleAll;

	@FindBy(xpath = "//span[normalize-space()='BackLogReadOnlyRoleOtherProj']")
	public static WebElement newReadOnlyOtherProject;

	@FindBy(xpath = "//span[normalize-space()='BackLogExecuteOnlyRoleOtherProj']")
	public static WebElement newExeOnlyOtherProject;

	@FindBy(xpath = "//span[normalize-space()='BackLogAllRoleOtherProj']")
	public static WebElement newRoleAllOtherProject;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement searchRoleInputBox;

	@FindBy(xpath = "//div[@class='modal-overlay']")
	public static WebElement modalOverlay;

	@FindBy(xpath = "//span[@class='icons8-cross-icon-blue searchbox-cross-icon']")
	public static WebElement closeSearchResult;

	@FindBy(xpath = "//a[@id='role-edit-0']")
	public static WebElement firstRolePencilIcon;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	public static WebElement roleSaveButton;

	@FindBy(xpath = "//*[text()='Role update successfully.']")
	public static WebElement roleUpdateMessage;

	@FindBy(xpath = "//a[@id='role-delete-0']")
	public static WebElement firstRoleDeleteIcon;

	@FindBy(xpath = "//button[normalize-space()='Delete']")
	public static WebElement deleteRoleButton;

	@FindBy(xpath = "//*[text()='Role deleted successfully.']")
	public static WebElement roleDeleteMessage;

	@FindBy(xpath = "//a[@href='#/user-management/users']")
	public static WebElement usersOption;

	@FindBy(xpath = "//ul[@class='table mbxl']")
	public static WebElement userEmailLists;

	@FindBy(xpath = "//a[@id='edit-user-0']")
	public static WebElement firstUserEditOption;

	@FindBy(xpath = "//*[text()='Role assigned successfully.']")
	public static WebElement roleAssignedMessage;

	@FindBy(linkText = "Log out")
	public static WebElement LogOutOption;

	@FindBy(xpath = "//h1[contains(text(),'You don’t have necessary permissions to view proje')]")
	public static WebElement permissionMessage;

	@FindBy(xpath = "//label[@for='execute']")
	public static WebElement executeBoxOption;

	@FindBy(xpath = "//span[text()='Developer']")
	public static WebElement allRolesListing;	
}