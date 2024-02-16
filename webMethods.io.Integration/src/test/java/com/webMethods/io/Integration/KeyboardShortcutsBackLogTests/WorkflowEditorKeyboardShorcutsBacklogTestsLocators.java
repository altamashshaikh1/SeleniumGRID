package com.webMethods.io.Integration.KeyboardShortcutsBackLogTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkflowEditorKeyboardShorcutsBacklogTestsLocators 
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

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement searchProjectTextBox;

	@FindBy(xpath = "//span[@title='BACKLOG_TEST_DATA']")
	public static WebElement backlogTestDataProjectName;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement searchWorkflowTextBox;

	@FindBy(xpath = "//span[normalize-space()='KeyBoard Shortcuts Backlog Test Data']")
	public static WebElement workflowCard;

	@FindBy(xpath = "//span[normalize-space()='Edit']")
	public static WebElement workflowCardEditButtonOption;

	@FindBy(xpath = "//i[@data-eventmap='start-activity']")
	public static WebElement startAction;

	@FindBy(id = "keyboard-shortcut-modal")
	public static WebElement keyBoardShortCutModal;

	@FindBy(id = "a0")
	public static WebElement a0activityID;

	@FindBy(id = "a1")
	public static WebElement a1activityID;

	@FindBy(id = "a2")
	public static WebElement a2activityID;

	@FindBy(id = "a3")
	public static WebElement a3activityID;

	@FindBy(id = "flowSettingsModal")
	public static WebElement workflowSettingsModal;

	@FindBy(id = "view_log")
	public static WebElement debugModal;

	@FindBy(id = "start")
	public static WebElement startActionID;

	@FindBy(xpath = "//i[@class='material-icons']")
	public static WebElement closeLoopModal;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessage;

	@FindBy(xpath = "Workflow testing started.")
	public static WebElement workflowTestStartedMessage;

	@FindBy(xpath = "Workflow testing completed.")
	public static WebElement workflowTestCompletedMessage;

	@FindBy(xpath = "//div[@class='notification-message']")
	public static WebElement notificationMessage;
}