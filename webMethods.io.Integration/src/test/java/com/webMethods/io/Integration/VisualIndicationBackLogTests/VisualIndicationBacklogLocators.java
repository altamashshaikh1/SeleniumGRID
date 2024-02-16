package com.webMethods.io.Integration.VisualIndicationBackLogTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VisualIndicationBacklogLocators
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
	public static WebElement inputSearchProjectBox;

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement defaultProjectName;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement newWorkflowButton;

	@FindBy(xpath = "//a[normalize-space()='Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath = "//ul[@id='list-container']")
	public static WebElement activitiesLists;

	@FindBy(xpath = "//input[@id='focusOn']")
	public static WebElement activitiesSearchTextBox;

	@FindBy(xpath = "//i[@aid='close']")
	public static WebElement searchTextBoxClearClose;

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement searchedLoggerAction;

	@FindBy(id="a0")
	public static WebElement loggerActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	public static WebElement nextLoggerActionButton;

	@FindBy(xpath = "//span[@aid='Transform']//span[@class='activity-arrow-icon left']")
	public static WebElement transformActionDropDown;

	@FindBy(xpath = "//span[text()='Add New']")
	public static WebElement addNewButton;

	@FindBy(xpath = "//input[@name='Transform Name']")
	public static WebElement transformNameTextBox;

	@FindBy(xpath = "//div[@aid='Pattern']//input[@type='text']")
	public static WebElement patternTextBox;

	@FindBy(xpath = "//button[normalize-space()='Done']")
	public static WebElement transformActionDoneButton;

	@FindBy(xpath = "//textarea[@class='inputElement textbox-edit']")
	public static WebElement loggerActionTextBox;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	public static WebElement loggerActionNextButton;

	@FindBy(xpath = "//button[normalize-space()='Done']")
	public static WebElement loggerActionDoneButton;

	@FindBy(xpath = "//span[@title='t1 : GetCurrentDateString']")
	public static WebElement transformActionFirst;

	@FindBy(xpath = "//div[@id='canvasContainer']//*[name()='svg'][@id='c1']")
	public static WebElement conditionActionLine;

	@FindBy(xpath = "//span[@class='settings-icon icons8-settings-icon icons8']")
	public static WebElement conditionLineSettingsIcon;

	@FindBy(xpath = "//div[@title='t2:GetCurrentDateString']")
	public static WebElement transformActionSecond;

	@FindBy(xpath = "//span[@title='Switch']")
	public static WebElement searchedSwitchAction;

	@FindBy(xpath = "//button[text()='Ok']")
	public static WebElement closeSwitchModal;

	@FindBy(id="a1")
	public static WebElement switchActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a1-Connector']")
	public static WebElement secondActionConnector;

	@FindBy(xpath="//div[text()='case 1']")
	public static WebElement switchCaseConnectorLine;

	@FindBy(xpath="//span[@class='settings-icon icons8-settings-icon icons8']")
	public static WebElement switchCaseSettingsIcon;

	@FindBy(xpath = "//div[@title='t3:GetCurrentDateString']")
	public static WebElement transformActionThird;

	@FindBy(xpath = "//a[contains(@title,'Flow services')]")
	public static WebElement flowServicesTab;

	@FindBy(xpath = "//span[@title='TransfromActionTestData']")
	public static WebElement transformActionFlows;

	@FindBy(id="a2")
	public static WebElement flowsActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a2-Connector']")
	public static WebElement flowsActionConnector;

	@FindBy(xpath = "//span[@title='t4 : GetCurrentDateString']")
	public static WebElement transformActionFourth;

	@FindBy(xpath = "//i[@class='flow-icons material-icons delite-icon dlt-icon-chevron-left icons8']")
	public static WebElement leaveCanvasButton;

	@FindBy(xpath = "//button[text()='Save and exit']")
	public static WebElement saveAndExitWorkflow;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement workflowSearchTextBox;

	@FindBy(xpath = "//a[@data-activates='1']")
	public static WebElement ellipsisOption;

	@FindBy(xpath = "//a[@title='Clone Workflow']")
	public static WebElement cloneWorkflow;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']//span[contains(text(),'Clone')]")
	public static WebElement cloneButton;

	@FindBy(xpath = "//span[text()='Copy of VisualIndicationWorkflowTestData']")
	public static WebElement clonedWorkflowName;

	@FindBy(xpath = "//input[@id='myRecipiesfileInput']")
	public static WebElement importWorkflowButton;

	@FindBy(xpath = "//button[normalize-space()='Import']")
	public static WebElement importButton;

	@FindBy(xpath = "//i[@data-eventmap='canvas-flow-edit-modal']")
	public static WebElement pencilIcon;

	@FindBy(xpath = "//input[@aid='Workflow Name']")
	public static WebElement workflowNameTextBox;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement workflowNameTextBoxDone;
}