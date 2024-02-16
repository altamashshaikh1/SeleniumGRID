package com.webMethods.io.Integration.WorkflowCreateTriggerTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkflowCreateTriggerCreateLocators 
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

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanels;

	@FindBy(id = "canvasContainer")
	public static WebElement canvasAssets;

	@FindBy(id = "//span[@title='AMQP']")
	public static WebElement activitiesPanelStrings;

	@FindBy(id = "start")
	public static WebElement startAction;

	@FindBy(xpath = "//i[@class='flow-icons rounded-block webhook icon-40']")
	public static WebElement TriggerIconString;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement triggerSearchTextBox;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath ="//span[contains(text(),'Integrations')]")
	public static WebElement Integrations;

	@FindBy(xpath ="//input[@placeholder='Search']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement defaultProjects;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflows;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButtons;

	@FindBy(xpath = "//i[@class='clock flow-icons icon-40 rounded-block']")
	public static WebElement clockTriggerObject;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	public static WebElement triggerNextButton;

	@FindBy(xpath = "//button[contains(text(),'Done')	]")
	public static WebElement triggerDoneButton;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement DoneButton;

	@FindBy(xpath = "//span[@title='New Board']")
	public static WebElement triggerAppliedObject;

	@FindBy(id="a0")
	public static WebElement loggerActionId;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement actionSearched;

	@FindBy(xpath = "//button[@class='modal-action btn btn-primary btn-sm']")
	public static WebElement actionNextButton;

	@FindBy(xpath = "//textarea[@class='inputElement textbox-edit']")
	public static WebElement loggerActionTextArea;

	@FindBy(xpath = "//button[text()='Test']")
	public static WebElement actionTestButton;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessage;

	@FindBy(xpath = "//*[text()=' Trigger saved successfully.']")
	public static WebElement triggerSavedMessagesString;

	@FindBy(xpath = "//div[@class='object-key-val']")
	public static WebElement actionTestData;

	@FindBy(xpath = "//*[text()='Trigger saved successfully.']")
	public static WebElement TriggerSaveMessage;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//*[@data-eventmap='canvas-flow-edit-modal']")
	public static WebElement workflowname;

	@FindBy(xpath = "//button[@data-eventmap='metadata-EditWorkflowDetails-Untitled Workflow12(fld3e312e2ab842b6f194ba6)']")
	public static WebElement editworkflowname;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement Doneworkflow;

	@FindBy(xpath = "//i[@data-eventmap='canvas-flow-edit-modal']") 
	public static WebElement editPencilIcon;

	@FindBy(xpath = "//input[@aid='Workflow Name']")
	public static WebElement workflowRenameTextbox;

	@FindBy(xpath = "//button[text()='Exit without saving']")
	public static WebElement exitpage;

	@FindBy(xpath = "//span[text()='Configurations']")
	public static WebElement Configurations;

	@FindBy(xpath="//span[text()='Workflow']")
	public static WebElement workflow;

	@FindBy(xpath = "//*[text()='Triggers']")
	public static WebElement Triggers;

	@FindBy(xpath = "//span[text() ='Clock trigger #1']")
	public static WebElement ClockTriggerAvailablity;

	@FindBy(xpath = "//td[text() = 'You have not created any triggers in this project.']")
	public static WebElement ClockTriggerUnavailablity;

	@FindBy(xpath="//span[contains(text(),'Workflowcreate')]")
	public static WebElement Workflowname;

	@FindBy(xpath = "//i[@class=' dlt-icon-more-menu']")
	public static WebElement elopsis;

	@FindBy(xpath = "//span[text()='Delete']")
	public static WebElement deleteworkflow;

	@FindBy(xpath = "//*[text()='Are you sure you want to delete this Workflow?']")
	public static WebElement popup1;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deleteworkflow1;

	@FindBy(xpath ="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox1;

	@FindBy(xpath ="//i[@class='dd-icons delite-icon dlt-icon-delete icon-mr']")
	public static WebElement deleteicon;

	@FindBy(xpath ="//h1[text()='Delete Clock trigger #1']")
	public static WebElement clockmessage;

	@FindBy(xpath ="//button[text()='Cancel']")
	public static WebElement cancel;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

}