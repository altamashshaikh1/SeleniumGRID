package com.webMethods.io.Integration.EnableDisableWorkflowsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkflowsEnableDisableBackLogTestsLocators
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

	@FindBy(xpath = "//span[normalize-space()='WorkflowEnableDisableTestData']")
	public static WebElement workflowCard;

	@FindBy(xpath = "//span[normalize-space()='Edit']")
	public static WebElement workflowCardEditButtonOption;

	@FindBy(xpath = "//i[@data-eventmap='start-activity']")
	public static WebElement startAction;

	@FindBy(id = "a0")
	public static WebElement loggerAction;

	@FindBy(xpath= "//span[contains(@title,'click for more options')]")
	public static WebElement moreOption;

	@FindBy(xpath= "//div[@id='a2']//span[@title='click for more options']")
	public static WebElement moreOptionLoggerSwitch;

	@FindBy(xpath = "//span[@aid='disabled-actions']")
	public static WebElement actionDisable;

	@FindBy(xpath = "//span[@aid='disabled-actions']")
	public static WebElement actionEnable;

	@FindBy(id = "a2")
	public static WebElement loggerSwitchAction;

	@FindBy(xpath = "//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement playButton;

	@FindBy(xpath = "//*[text()='Workflow testing completed.']")
	public static WebElement testCompleteMessage;

	@FindBy(xpath = "//button[@title='Console panel']")
	public static WebElement debugPanelButton;

	@FindBy(xpath = "//span[contains(@class,'single-workflow-title cursr-pntr')]")
	public static WebElement executionSourceLocator;

	@FindBy(xpath = "//span[normalize-space()='$start Start']")
	public static WebElement startActionLog;

	@FindBy(xpath = "//span[normalize-space()='$a0 Logger']")
	public static WebElement loggerActionLog;

	@FindBy(xpath = "//span[normalize-space()='$a1 Switch']")
	public static WebElement switchActionLog;

	@FindBy(xpath = "//span[normalize-space()='$stop Stop']")
	public static WebElement stopActionLog;

	@FindBy(xpath = "//div[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addNewWorkflow;

	@FindBy(xpath = "//a[normalize-space()='Projects']")
	public static WebElement projectLink;

	@FindBy(id = "myRecipiesfileInput")
	public static WebElement importWorkflowButton;

	@FindBy(css = "button[type='submit']")
	public static WebElement importButton;

	@FindBy(xpath = "//span[text()='KeyBoard Shortcuts Backlog Test Data']")
	public static WebElement workflowImported;

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement defaultProjectName;
}