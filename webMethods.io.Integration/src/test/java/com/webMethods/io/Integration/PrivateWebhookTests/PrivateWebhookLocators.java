package com.webMethods.io.Integration.PrivateWebhookTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivateWebhookLocators 
{
	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath ="//input[@placeholder='Search']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='VarshithaUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//span[@title='JMS_Automation']")
	public static WebElement myUIautomationproject2;

	@FindBy(xpath = "//div[text()='Info: A new deployment is available. Do you want to update it?']")
	public static WebElement Info;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath="//span[contains(text(),'Privatewebhook')]")
	public static WebElement Privatewebhook;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(id="a1")
	public static WebElement ContentStack;

	@FindBy(xpath = "//h1[@title='Get Entries']")
	public static WebElement Getentries;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement Next;

	public static String Next1= "//button[text()='Next']";

	@FindBy(xpath = "//span[text()='Action configure']")
	public static WebElement configure;

	@FindBy(xpath = "//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement workflowPlayButton;

	@FindBy(xpath = "//*[text()='Workflow testing started.']")
	public static WebElement workflowExecutionStartMessage;

	@FindBy(xpath = "//*[text()='Workflow testing completed.']")
	public static WebElement workflowExecutionCompletedMessage;

	@FindBy(xpath = "//span[@title='AMQP']")
	public static WebElement activitiesPanel;

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "start")
	public static WebElement startAction;

	@FindBy(xpath="//i[@class='flow-icons rounded-block webhook icon-40']")
	public static WebElement webhookIconString;

	@FindBy(xpath="//input[@value = 'https://devrealm1.dev-int-aws-us.webmethods.io/private/runflow/run/wlf0Zg6tI']")
	public static WebElement webhookURLon;

	@FindBy(xpath="//div[@id = 'is_private_webhook']")
	public static WebElement privatewebhook ;

	@FindBy(xpath="//div[@id='is_private_webhook']//span[@class='switch-handle small']")
	public static WebElement privatetoggleon ;

	@FindBy(xpath="//input[@value='https://devrealm1.dev-int-aws-us.webmethods.io/runflow/run/wlf0Zg6tI']")
	public static WebElement webhookURLoff;

	@FindBy(xpath="//div[@id='is_private_webhook']//span[@class='switch-handle small']")
	public static WebElement privatetoggleoff ;

	@FindBy(xpath="//button[text() = 'Done']")
	public static WebElement Done ;

	@FindBy(xpath="//span[text() = 'Save']")
	public static WebElement Save ;

	@FindBy(xpath = "//div[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//a[text() = 'Projects']")
	public static WebElement Project;

	@FindBy(xpath = "//a[@name='VarshithaUIAutomation-more-icon']")
	public static WebElement elopsis;

	@FindBy(xpath = "//a[@name='VarshithaUIAutomation-publish']//span[text()='Publish Project']")
	public static WebElement Publishproject;

	@FindBy(xpath="//span[text()='Workflows']")
	public static WebElement Assetworkflows;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement InputName;

	@FindBy(xpath = "//textarea[@aid='Description']")
	public static WebElement InputDescription ;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-caret-down']")
	public static WebElement Dropdown ;

	@FindBy(xpath = "//button[text()='Publish']")
	public static WebElement Publish ;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewProjectButton;

	@FindBy(xpath = "//div[@class='deploy-right-header right-project-name']")
	public static WebElement deployprojectnamemodal;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Save and continue')]")
	public static WebElement saveandcontinueButton;
}
