package com.webMethods.io.Integration.ProjectTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectCRUDLocators
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

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement searchBox;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewProject;

	@FindBy(id = "new-project")
	public static WebElement newProjectModalInputLabel;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement newProjectModalCreateButton;

	@FindBy(xpath = "//*[text()='Project updated successfully.']")
	public static WebElement projectUpdateSuccessMessage;

	@FindBy(xpath = "//span[@title='AltamashUpdateAutomationTest']")
	public static WebElement projectUpdatedName;

	@FindBy(xpath = "//div[@class='notifications-alert success']")
	public static WebElement notificationMessageString;

	@FindBy(xpath = "//span[@class='icons8-cross-icon-blue searchbox-cross-icon']")
	public static WebElement closeSearchIcon;

	@FindBy(xpath = "//input[@type='search']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='Default']")
	public static WebElement searchedProjectName;

	@FindBy(xpath = "//*[text()='Project created successfully.']")
	public static WebElement projectCreationSuccessMessage;

	@FindBy(xpath = "//span[@class='cross-icon dlt-icon-close']")
	public static WebElement closeMessage;

	@FindBy(xpath = "//span[@data-eventmap='project-dropdown']")
	public static WebElement projectDropDown;

	@FindBy(xpath = "//a[text()='Projects']")
	public static WebElement projectDashboardLink;

	@FindBy(linkText = "new Workflow")
	public static WebElement newWorkflowLink;

	@FindBy(xpath = "//a[@aid='filterDropdownBtn']")
	public static WebElement projectDropdown;

	@FindBy(xpath = "//li[@class='dashboard-list-item']//a[starts-with(text(), 'Flow')]")
	public static WebElement flowserviceTab;

	@FindBy(xpath = "//span[text()='APIs']")
	public static WebElement apiTab;

	@FindBy(xpath = "//span[text()='Connectors ']")
	public static WebElement connectorTab;

	@FindBy(xpath = "//span[text()='Configurations']")
	public static WebElement configurationsTab;

	@FindBy(xpath = "//span[text()='Deployments']")
	public static WebElement deploymentTab; 

	@FindBy(xpath = "//span[@title='AutomationTest']")
	public static WebElement createdProject; 

	@FindBy(xpath = "//a[@data-activates='project-card-menu-0']")
	public static WebElement createdProjectEllipsis;

	@FindBy(xpath = "//span[text()='Edit']")
	public static WebElement projectEditLink;

	@FindBy(xpath = "//input[@aid='Project Name']")
	public static WebElement editProjectTextBox;

	@FindBy(xpath = "//div[@id='notification-container']")
	public static WebElement editProjectMessage;

	@FindBy(xpath = "//a[@name='AltamashUpdateAutomationTest-delete']")
	public static WebElement projectDeleteLink;

	@FindBy(xpath = "//button[normalize-space()='Delete']")
	public static WebElement projectDeleteButton;

	@FindBy(xpath = "//div[@id='notification-container']")
	public static WebElement deleteProjectMessage;

	@FindBy(xpath = "//a[text()='1 Workflow ']")
	public static WebElement workflowCount;

	@FindBy(xpath = "//a[text()='1 FlowService']")
	public static WebElement flowsCount;   

	@FindBy(xpath = "//*[text()='Project deleted successfully.']")
	public static WebElement projectDeleteSuccessMessage;
}
