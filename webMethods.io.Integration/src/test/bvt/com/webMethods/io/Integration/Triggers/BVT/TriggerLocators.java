package com.webMethods.io.Integration.Triggers.BVT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TriggerLocators
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

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[starts-with(text(), 'PRBVTSUITE')]")
	public static WebElement createdProjectTitleCard;

	@FindBy(xpath = "//span[starts-with(text(), 'PRBVTSUITE')]")
	public static WebElement createdWorkflowTitleCard;

	@FindBy(xpath = "//a[@class='tooltipped edit-flow']")
	public static WebElement workflowEditOption;

	@FindBy(id = "start")
	public static WebElement startAction;

	@FindBy(xpath = "//div[@class='search-box ']//input[@type='search']")
	public static WebElement searchTrigger;

	@FindBy(xpath = "//span[@class='trigger-activity-icon']//i[@data-eventmap='metadata-trigger-clock']")
	public static WebElement searchedClockTrigger;

	@FindBy(xpath = "//button[text()='Next'][@type='button']]")
	public static WebElement nextButtonTriggerModal;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm'][text()='Done']")
	public static WebElement doneButtonTriggerModal;

	@FindBy(xpath = "//span[starts-with(@class, 'clock')]")
	public static WebElement clockTriggerApplied;

	@FindBy(xpath = "//ul[@class='triggers-list']//li[@class='list-item active']//div[@class='toggle-txt table-display']//div[@class='table-cell selection-stat-wrap']")
	public static WebElement clockTriggerUpsertList;

	@FindBy(xpath = "//i[@class='dd-icons dlt-icon-edit icon-mr']")
	public static WebElement editPencilTriggerEdit;

	@FindBy(xpath = "//button[@class='btn btn-primary save_flow_btn right primary-btn']//span[text()='Save']")
	public static WebElement saveWorkflowButton;

	@FindBy(xpath = "//i[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvasButton;

	@FindBy(xpath = "//button[@class='btn btn-link btn-delete btn-sm']//i[@class='dlt-icon-delete flow-icons']")
	public static WebElement deleteTriggerIcon;

	@FindBy(xpath = "//button[@class='btn btn-danger'][text()='Yes']")
	public static WebElement deleteYesOption;

	@FindBy(xpath = "//span[@class='dlt-icon-close']")
	public static WebElement triggerModalCloseIcon;

	@FindBy(xpath = "//a[@aid='hideDropdown'][@data-activates='1']")
	public static WebElement workflowEllipsis;

	@FindBy(xpath = "//ul[@id='1']//li[@aid='Delete']//a[@title='Delete Workflow']")
	public static WebElement triggerDeleteOption;

	@FindBy(xpath = "//button[@type='button'][text()='Delete']")
	public static WebElement triggerConfirmDeleteOption;

	@FindBy(xpath = "//a[@class='new-workflow'][text()='new Workflow']")
	public static WebElement newWorkflowLink;

	@FindBy(linkText = "Projects")
	public static WebElement projectLink;

	@FindBy(xpath = "//a[@data-activates='project-card-menu-0']//i[@class='dlt-icon-more-menu']")
	public static WebElement createdProjectEllipsis;

	@FindBy(xpath = "//ul[@aid='projectCardMenu'][@id='project-card-menu-0']//span//li//a[@aid='projectDeleteBtn']")
	public static WebElement projectDeleteOption;

	@FindBy(xpath = "//button[@data-testid='project-delete-button'][text()='Delete']")
	public static WebElement projectConfirmDeleteOption;

	@FindBy(xpath = "//*[text()='Project deleted successfully.']")
	public static WebElement projectDeleteMessage;
}