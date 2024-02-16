package com.webMethods.io.Integration.Webhooks.BVT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebhookLocators
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

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewButtonString;

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

	@FindBy(xpath = "//li[@data-id='webhook-trigger']")
	public static WebElement webhookOption;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement webhookNextButton;

	@FindBy(xpath = "//input[@class='input-element-webhook']")
	public static WebElement webhookURLTextBox;

	@FindBy(id = "webhook-url")
	public static WebElement webhookURLTextBox2;

	@FindBy(xpath = "//span[text()='None']")
	public static WebElement noneOption;

	@FindBy(xpath = "//span[@class='switch-handle small']")
	public static WebElement returnActionSwitch;

	@FindBy(xpath="//*[name()='svg']//*[@aid='start-Connector']")
	public static WebElement startActionConnector;

	@FindBy(id = "a0")
	public static WebElement returnActionWebhookAction;

	@FindBy(xpath = "//span[@class=' webhook icon-48 trigger-icon']")
	public static WebElement webhookAppliedActionStart;	
	
	@FindBy(xpath = "//button[@class='modal-action btn btn-primary btn-sm']")
	public static WebElement actionNextButton;

	@FindBy(xpath = "//button[text()='Test']")
	public static WebElement actionTestButton;

	@FindBy(xpath = "//div[@class='object-key-val']")
	public static WebElement actionTestData;
	
	@FindBy(xpath = "//textarea[@class='inputElement textbox-edit']")
	public static WebElement loggerActionTextArea;
	
	@FindBy(id="stop")
	public static WebElement stopAction;
	
	@FindBy(xpath = "//input[@id='focusOn']")
	public static WebElement actionSearchTextBox;	
	
	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement actionSearched;
	
	@FindBy(id="a0")
	public static WebElement loggerActionId;
	
	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;
	
	@FindBy(xpath = "//button[@class='modal-action btn btn-primary btn-sm']")
	public static WebElement modalNextButton;

	@FindBy(xpath = "//textarea[@class='inputElement textbox-edit']")
	public static WebElement returnWebhookTextBox;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	@FindBy(xpath="//button[text()='Save']")
	public static WebElement webhookSave;

	@FindBy(xpath="//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessageString;

	@FindBy(xpath = "//span[@class='webhook-refresh-icon webhook-btn dlt-icon-refresh']")
	public static WebElement refreshWebhookSid;

	@FindBy(xpath = "//div[@class='btn-coppied-right']")
	public static WebElement copyWebhookSid;

	@FindBy(xpath = "//div[@class='bubble-on-hover width-size']")
	public static WebElement WebhookSidcopied;

	@FindBy(xpath = "//*[text()='Please enter a valid JSON object in the header field.']")
	public static WebElement workflowinputString;

	@FindBy(xpath = "//*[text()='Webhook id updated successfully.']")
	public static WebElement webhookIDMessage;

	@FindBy(xpath = "//span[@class='webhook-delete-icon dlt-icon-delete']")
	public static WebElement deleteWebhookIcon;

	@FindBy(xpath = "//span[@class='dlt-edit-webhook dlt-icon-edit']")
	public static WebElement editwebhookpayload;

	@FindBy(xpath = "//span[text()='Webhook Payload ']")
	public static WebElement webhookpayload;

	@FindBy(xpath = "//div[@id='headers']//textarea[@class='ace_text-input']")
	public static WebElement webhookheaders;

	@FindBy(xpath = "//span[text()='Headers']")
	public static WebElement Headers;

	@FindBy(xpath = "//div[@id='headers']//textarea[@class='ace_text-input']")
	public static WebElement webHookPayloadElement;

	@FindBy(xpath = "//span[text()='Body']")
	public static WebElement Body;

	@FindBy(xpath = "//div[@id='body']//textarea[@class='ace_text-input']")
	public static WebElement Bodyclick;

	@FindBy(xpath = "//div[@id='query']//textarea[@class='ace_text-input']")
	public static WebElement BodyQueryclick;

	@FindBy(xpath = "//span[text()='Query']")
	public static WebElement Query;

	@FindBy(xpath="//button[text()='Cancel']")
	public static WebElement cancel;

	@FindBy(xpath="//button[text()='Previous']")
	public static WebElement previous;

	@FindBy(xpath="//button[text()='Save']")
	public static WebElement Save;

	@FindBy(xpath="//button[text()='Done']")
	public static WebElement Done;

	@FindBy(xpath="//button[text()='Fetch']")
	public static WebElement Fetch;

	@FindBy(xpath="//span[text()='Tenant Credentials']")
	public static WebElement TenantCredRadio;
}