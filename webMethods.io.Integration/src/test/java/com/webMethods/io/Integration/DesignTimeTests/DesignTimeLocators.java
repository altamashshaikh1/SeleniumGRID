package com.webMethods.io.Integration.DesignTimeTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DesignTimeLocators
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
	public static WebElement projectSearchBox;
	
	@FindBy(xpath = "//span[@title='Messaging Automation']")
	public static WebElement searchedProject;
	
	@FindBy(xpath = "//button[@class='btn secondary-btn btn-sm btn-import']")
	public static WebElement newWorkflowButton;
	
	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowButton;
	
	@FindBy(xpath = "//div[@title='WF_CASE_TWO']")
	public static WebElement createdWorkflow;
	
	@FindBy(linkText = "Messaging")
	public static WebElement messagingLink;
	
	@FindBy(linkText = "Events")
	public static WebElement EventsLink;
	//span[contains(text(),'Events')]
	
	@FindBy(xpath = "//button[contains(text(),'Messaging destinations')]")
	public static WebElement MessagingDestinationButton;
	//button[contains(text(),'Messaging destinations')]
	
	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-chevron-up icon-chevron-down']")
	public static WebElement ExpandButtonMessaging;
	//span[@class='delite-icon dlt-icon-chevron-up icon-chevron-down']
	
	@FindBy(xpath = "//span[@data-testid='toggle-button-Messaging']")
	public static WebElement MessagingToggleButton;
	
	@FindBy(linkText = "QueueOne")
	public static WebElement queueOneLink;
	
	@FindBy(linkText = "Subscribers")
	public static WebElement subsriberLink;
	
	@FindBy(xpath = "//button[normalize-space()='Add subscriber']")
	public static WebElement addSubscriberButton;
	
	@FindBy(id = "subscriberName")
	public static WebElement subsNameTextBox;
	
	@FindBy(linkText = "Clear default invocation")
	public static WebElement defaultInvocationLink;
	
	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement subsSaveButton;
	
	@FindBy(xpath = "//*[text()='Subscriber created successfully.']")
	public static WebElement subsSavedMessage;
	
	@FindBy(xpath = "//div[text()='Subscribers']")
	public static WebElement subscriberBackLink;
	
	@FindBy(linkText = "WF_MESSAGING_BACKLOG_SUBS")
	public static WebElement subscriberCreatedLink;
	
	@FindBy(linkText = "Projects")
	public static WebElement projectDashBoardLink;
	
	@FindBy(xpath = "//div[@aid='WF_SUBS_USED']//a[@aid='hideDropdown']")
	public static WebElement workflowUsedEllipsis;
 
	@FindBy(xpath = "//div[@aid='WF_SUBS_USED']//a[@title='Delete Workflow']")
	public static WebElement workflowDeleteOption;
	
	@FindBy(xpath = "//h1[text()='Delete Workflow']")
	public static WebElement triggerUsagesModal;
	
	@FindBy(xpath = "//button[@class='modal-action modal-close btn btn-link btn-sm']")
	public static WebElement triggerUsageModalCancelButton;
	
	@FindBy(linkText = "Flow services")
	public static WebElement flowServicesLink;
	
	@FindBy(xpath = "//span[text()='FS_MESSAGING_USED']")
	public static WebElement flowservicesCard;
	
	@FindBy(xpath = "//div[@aid='FS_MESSAGING_USED']//a[@aid='hideDropdown']")
	public static WebElement flowUsedEllipsis;
	
	@FindBy(xpath = "//div[@aid='FS_MESSAGING_USED']//a[@title='Delete Flow service']")
	public static WebElement flowDeleteOption;
	
	@FindBy(xpath = "//h1[@id='myModalLabel']")
	public static WebElement triggerModalUsagesConfirm;
	
	@FindBy(xpath = "//button[@class='ut-flow-btn primary ut-no-margin']")
	public static WebElement triggerUsageModalCancelButtonFs;
	
	@FindBy(id = "addRoutingRuleBtn")
	public static WebElement routingRuleButton;
	
	@FindBy(id = "routingRuleName")
	public static WebElement routingRuleName;
	
	@FindBy(xpath = "//button[text()='Add']")
	public static WebElement addRuleButton;
	
	@FindBy(xpath = "//div[@title='Rule1']")
	public static WebElement routingRuleCreated;
	
	@FindBy(xpath = "//div[@title='Rule2']")
	public static WebElement routingRule2Created;
	
	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement saveButton;
	
	@FindBy(linkText = "WF_MESSAGING_BACKLOG_SUBS_2")
	public static WebElement subscriberCreatedLinkTwo;
	
	@FindBy(xpath = "//div[@aid='name_value']")
	public static WebElement subscriberEditModal;
	
	@FindBy(xpath = "//button[text()='Edit']")
	public static WebElement subscriberEditButton;
	
	@FindBy(xpath = "//i[@title='Edit']")
	public static WebElement ruleEditPencilIcon;
	
	@FindBy(xpath = "//div[@class='modal-footer action-panel']//button[text()='Save']")
	public static WebElement editRuleSaveButton;
	
	@FindBy(xpath = "//div[@data-testid='deleteModal']")
	public static WebElement editRuleConfirmModal;
	
	@FindBy(xpath = "//button[text()='Yes']")
	public static WebElement yesOption;
	
	@FindBy(xpath = "//*[text()='Subscriber updated successfully.']")
	public static WebElement subsUpdateSavedMessage;
	
	@FindBy(xpath = "//textarea[@id='routingRuleFilter']")
	public static WebElement ruleFilterTextArea;
	
	@FindBy(xpath = "//i[@name='Rule1_delete']")
	public static WebElement ruleDeleteButton;
	
	@FindBy(xpath = "//div[@data-testid='deleteModal']")
	public static WebElement deleteConfirmModal;
	
	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement ruleDelete;
	
	@FindBy(xpath = "//div[@class='no-table-data']")
	public static WebElement noRoutingRuleMessage;
	
	@FindBy(linkText = "Clear default invocation")
	public static WebElement clearDefaultInvocation;
	
	@FindBy(xpath = "//div[text()='Not set']")
	public static WebElement invocationNotSet;
	
	@FindBy(id ="description")
	public static WebElement subscriberDescription;
	
	@FindBy(xpath = "//span[text()='-']")
	public static WebElement noInvocation;
	
	@FindBy(linkText = "Messaging")
	public static WebElement messagingLinkDashboard;
	
	@FindBy(xpath = "//span[contains(text(),': MessagingSubscriber with name WF_SUBS_USED_ASSET')]")
	public static WebElement duplicateErrorMessage;
	
	@FindBy(linkText = "WF_SUBS_USED_ASSETS_MULTIPLE")
	public static WebElement subscriberMultipleCreatedLink;
	
	@FindBy(linkText = "WF_SUBS_USED_ASSETS")
	public static WebElement subscriberWithWorkflowInvocation;
	
	@FindBy(xpath = "//div[@title='WF_SUBS_USED']//a[text()='WF_SUBS_USED']")
	public static WebElement workflowUsedLink;
	
	@FindBy(id = "start")
	public static WebElement triggerActionCanvas;
	
	@FindBy(xpath = "//div[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvasButton;
	
	@FindBy(xpath = "//div[text()='Subscribers']")
	public static WebElement subscriberLink;
	
	@FindBy(linkText = "FS_MESSAGING_BACKLOG_SUBS")
	public static WebElement subscriberWithFlowservicesInvocation;
	
	@FindBy(xpath = "//div[@title='FS_MESSAGING_USED']//a[text()='FS_MESSAGING_USED']")
	public static WebElement flowServicesUsedLink;
	
	@FindBy(id = "ut-input-field-displayname")
	public static WebElement flowServiceCanvas;
	
	@FindBy(xpath = "//*[@title='Go back']")
	public static WebElement leaveFlowservicesCanvasButton;
	
	@FindBy(linkText = "Integrations")
	public static WebElement integrationsLink;
	
	@FindBy(xpath = "//div[@title='WF_CASE_FOUR']")
	public static WebElement workflowCaseFourCard;
	
	@FindBy(xpath = "//div[@aid='DEFAULT_INVOCATION_WF']//a[@aid='hideDropdown']")
	public static WebElement defaultWorkflowInvocationEllipsis;	
	
	@FindBy(xpath = "//div[@aid='DEFAULT_INVOCATION_WF']//a[@title='Clone Workflow']")
	public static WebElement defaultWorkflowCloneOption;
	
	@FindBy(id = "info-modal")
	public static WebElement workflowClonwInfoModal;
	
	@FindBy(xpath = "//button[@data-testid='clone-btn']")
	public static WebElement workflowCloneOption;
 	
	@FindBy(xpath = "//*[text()='Workflow cloned successfully.']")
	public static WebElement workflowCloneMessage;
	
	@FindBy(xpath = "//div[@title='Copy of DEFAULT_INVOCATION_WF']")
	public static WebElement clonedWorkflow;
	
	@FindBy(xpath = "//a[@aid='flowCount']")
	public static WebElement workflowCountProjectCard;
}