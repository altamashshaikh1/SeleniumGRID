package com.webMethods.io.Integration.DeleteWorkflowWithRoleTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteWorkflowWithCustomRoleTestLocators 
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

	@FindBy(xpath = "//span[@data-testid='pagination-left']")
	public static WebElement welcomeText;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loadingbody;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[@title='HarshadUIAutomation']")
	public static WebElement myUIautomationproject;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflow;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanel;

	@FindBy(id = "canvasContainer")
	public static WebElement canvasAssets;

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	@FindBy(xpath = "//span[@title='RestConnector1']")
	public static WebElement actionSearched;

	@FindBy(xpath = "//span[@title='RestConnector_AWSS3']")
	public static WebElement action1Searched;

	@FindBy(id="a0")
	public static WebElement restActionId;

	@FindBy(id="a0")
	public static WebElement restConnectorActionId;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//a[@data-activates='1']")
	public static WebElement createdWorkflowEllipsis1;

	@FindBy(xpath = "//span[text()='Delete']")
	public static WebElement deleteWorkflow;

	@FindBy(xpath = "//button[@data-eventmap='metadata-confirmDeleteModal-Are you sure you want to delete this Workflow? (Delete)']")
	public static WebElement deleteOption;

	@FindBy(xpath = "//*[text()='Workflow deleted successfully.']")
	public static WebElement workflowDeleteMessage;
}