package com.webMethods.io.Integration.TriggersTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TriggerTestLocators 
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

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement defaultProjectCard;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflow;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanelString;

	@FindBy(id = "start")
	public static WebElement startAction;

	@FindBy(xpath = "//span[@title='Settings']")
	public static WebElement startSettingsIcon;

	@FindBy(xpath = "//i[@class='flow-icons rounded-block webhook icon-40']")
	public static WebElement webhookIconString;

	@FindBy(xpath = "//span[@title='Settings']")
	public static WebElement triggerSettingsIcon;

	@FindBy(xpath = "//span[text()='Webhook']")
	public static WebElement webhookOption;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement triggerModalNextButton;  

	@FindBy(xpath = "//button[@title='Back']")
	public static WebElement triggerModalPreviousButton; 

	@FindBy(xpath = "//button[@class='modal-action modal-close btn btn-link btn-sm']")
	public static WebElement closeTriggerModal;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement triggerSearchTextBox;

	@FindBy(xpath = "//i[@class='trello flow-icons icon-40 rounded-block']")
	public static WebElement trelloTriggerObject;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public static WebElement triggerNextButton;

	@FindBy(xpath = "//button[normalize-space()='Skip']")
	public static WebElement triggerSkip;

	@FindBy(xpath = "//button[normalize-space()='Done']")
	public static WebElement triggerDone;

	@FindBy(xpath = "//div[text()='Please Select']")
	public static WebElement triggerAuthDropdown;

	@FindBy(xpath = "//div[text()='Trello_1']")
	public static WebElement trelloAuthName;

	@FindBy(xpath = "//*[text()='Trigger saved successfully.']")
	public static WebElement triggerSaveMessage;

	@FindBy(xpath = "//li[text()='1. Go to Trello.']")
	public static WebElement trelloMessageString;

	@FindBy(xpath = "//div[@id='stop']")
	public static WebElement stopActionString;

	@FindBy(xpath = "//span[@title='New Board']")
	public static WebElement triggerAppliedObject;

	@FindBy(xpath = "//span[@title='Trello trigger #1']")
	public static WebElement triggerName;

	@FindBy(xpath = "//button[@class='btn btn-link btn-sm edit-btn']")
	public static WebElement triggerEditButton;

	@FindBy(xpath = "//*[text()='Trigger updated successfully.']")
	public static WebElement triggerUpdateMessage;

	@FindBy(xpath = "//button[@class='btn btn-link btn-delete btn-sm']")
	public static WebElement triggerDeleteButton;

	@FindBy(xpath = "//button[text()='Yes']")
	public static WebElement triggerDeleteYesButton;

	@FindBy(xpath = "//*[text()='Trigger removed successfully.']")
	public static WebElement triggerDeleteMessage;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement webhookNextButton;

	@FindBy(xpath = "//button[@class='modal-action btn btn-link btn-sm']")
	public static WebElement webhookPreviousButton; 
}