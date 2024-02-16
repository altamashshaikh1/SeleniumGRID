package com.webMethods.io.Integration.ProfileDataTests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileDataTestLocators 
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//span[text()='Default']")
	public static WebElement defaultProjectString;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loaderString;

	@FindBy(xpath = "//i[@class='delite-icon dlt-icon-profile']")
	public static WebElement profileIcon;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='Profile']")
	public static WebElement profileOption;

	@FindBy(xpath = "//div[@class='user-profile-icon-container']")
	public static WebElement profileData;

	@FindBy(xpath = "//ul[@class='table mbxl brb-none']")
	public static WebElement userData;

	@FindBy(xpath = "//button[text()='Copy Key']")
	public static WebElement copyKey;

	@FindBy(xpath = "//button[text()='Generate Token']")
	public static WebElement genereateKey;

	@FindBy(xpath = "//input[@class='input-select']")
	public static WebElement developerKeyTextBox;

	@FindBy(xpath = "//button[@class='modal-action modal-close secondary-btn btn-sm']")
	public static WebElement closeDeveloperTokenModal;

	@FindBy(xpath = "//button[text()='Yes']")
	public static WebElement yesButton;

	@FindBy(xpath = "//span[text()='User management']")
	public static WebElement userManagementOption;

	@FindBy(xpath = "//button[@type='button']")
	public static WebElement newRoleButton;

	@FindBy(xpath = "//input[@aid='Role Name']")
	public static WebElement roleNameInputBox;

	@FindBy(xpath = "//input[@type='checkbox']")
	public static List<WebElement> allRolesCheckBox;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement roleDoneButton;

	@FindBy(xpath = "//div[@class='notification-message']")
	public static WebElement successMessage;

	@FindBy(xpath = "//span[@class='cross-icon dlt-icon-close']")
	public static WebElement closeMessage;

	@FindBy(xpath = "//span[text()='AutomationRole1']")
	public static WebElement createdRoleName;

	@FindBy(xpath = "//i[@class='dd-icons delite-icon dlt-icon-edit icon-mr']")
	public static WebElement editRolePencilIcon;

	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement roleSaveButton;

	@FindBy(xpath = "//i[@class='dd-icons delite-icon dlt-icon-delete icon-mr']")
	public static WebElement deleteRoleIcon;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement roleDeleteButton;

	@FindBy(xpath = "//span[text()='Users']")
	public static WebElement usersOption;

	@FindBy(xpath = "//span[text()='Settings']")
	public static WebElement profileSettingsOption;

	@FindBy(xpath = "//span[contains(text(),'White')]")
	public static WebElement whiteLabelOption;

	@FindBy(xpath = "//span[text()='AppliedThemeAutomation']")
	public static WebElement noSettingstext;

	@FindBy(xpath = "//button[text()='New theme']")
	public static WebElement newThemeButton;

	@FindBy(xpath = "//input[@aid='Theme name']")
	public static WebElement themeNameInputBox;

	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement themeSaveButton;

	@FindBy(xpath = "//a[@title='Edit theme']")
	public static WebElement themeEditPencilIcon;

	@FindBy(xpath = "//button[text()='Update']")
	public static WebElement themeUpdateButton;

	@FindBy(xpath = "//span[text()='AutomationTheme1']")
	public static WebElement addedTheme;

	@FindBy(xpath = "//i[@class='white-more-icon delite-icon dlt-icon-more-menu']")
	public static WebElement whiteLabelEllipsis;

	@FindBy(linkText = "Apply")
	public static WebElement applyTheme;

	@FindBy(linkText = "Delete")
	public static WebElement deleteTheme;

	@FindBy(xpath = "//button[@class='btn btn-danger btn-sm delete-btn-prmy primary-btn']")
	public static WebElement deletThemeButton; 

	@FindBy(xpath = "//span[text()='Log out']")
	public static WebElement logOutOption;

	@FindBy(xpath = "//*[text()='Users']")
	public static WebElement userLinkTab;

	@FindBy(xpath = "//ul[@class='table mbxl']")
	public static WebElement usersList;

	@FindBy(id = "edit-user-1")
	public static WebElement pencilIcon;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement doneButton;

	@FindBy(xpath = "//*[text()='Role assigned successfully.']")
	public static WebElement roleAssignedMessage; 
}