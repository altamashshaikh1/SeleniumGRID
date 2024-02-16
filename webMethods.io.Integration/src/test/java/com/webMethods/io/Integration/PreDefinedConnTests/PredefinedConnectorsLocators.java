package com.webMethods.io.Integration.PreDefinedConnTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PredefinedConnectorsLocators 
{
	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement loginBlock;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath ="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='HarshadUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//span[text()='PredefinedConnector']")
	public static WebElement PredefinedConnector;

	public static String accnamevalidatorcircle = "//div[@class='circle']";

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle1;

	@FindBy(id = "start")
	public static WebElement startAction;

	public static String webhookIconString = "//i[@class='flow-icons rounded-block webhook icon-40']";

	public static String loadingindicator= "//div[@class='loading-indicator-body']";

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement triggerSearchTextBox;

	@FindBy(xpath = "//span[@class='trello flow-icons icon-45 rounded-block']")
	public static WebElement TriggerIcon;

	@FindBy(xpath = "//i[@class='trello flow-icons icon-40 rounded-block']")
	public static WebElement TrelloTriggerObject;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement DoneButton;

	public static String TriggerSaveMessage_str = "//*[text()='Trigger saved successfully.']";

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	public static String workflowSaveMessage_str = "//*[text()='Workflow saved.']";

	@FindBy(xpath = "//*[text()='Trigger removed successfully.']")
	public static WebElement triggerDeleteMessage;

	public static String triggerDeleteMessage1 = "//*[text()='Trigger removed successfully.']";

	@FindBy(xpath = "//div[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//span[text()='Create a new event for']")
	public static WebElement AddTrigger;

	@FindBy(xpath = "//div[text()='Please Select']")
	public static WebElement triggerAuthDropdown;

	@FindBy(xpath = "//span[contains(text(),'Trello trigger')]")
	public static WebElement Trello_1;

	@FindBy(xpath = "//span[@class='no-operation-yet']")
	public static WebElement Trello_2;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public static WebElement triggerSaveButton;

	@FindBy(xpath = "//button[contains(text(),'Skip')]")
	public static WebElement triggerskipButton;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	public static WebElement triggerNextButton;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	public static String TriggerSaveMessage_str2 = "//*[text()='Trigger updated successfully.']";

	@FindBy(xpath="//a//span[contains(text(),'Connectors')]")
	public static WebElement Connectors;

	@FindBy(id="a0")
	public static WebElement Cloudstream;

	@FindBy(xpath="//a[contains(text(),'Predefined')]")
	public static WebElement Predefined;

	@FindBy(xpath="//span[@class='delite-icon dlt-icon-chevron-down icon-chevron-down']")
	public static WebElement Downarrow;

	@FindBy(xpath="//span[@class='delite-icon dlt-icon-chevron-up icon-chevron-down']")
	public static WebElement uparrow;

	@FindBy(xpath="//div[text()='Trello_1']")
	public static WebElement Trello1;

	public static String Integrations ="//span[text()='Integrations']";

	@FindBy(xpath="//span[text()='Integrations']")
	public static WebElement Integrations1;

	@FindBy(xpath="//span[@class='trello flow-icons icon-45 rounded-block']")
	public static String Trelloicon;

	@FindBy(xpath="//i[@class='dlt-icon-delete flow-icons']")
	public static WebElement DeleteTrigger;

	@FindBy(xpath="//button[text()='Yes']")
	public static WebElement Yes;

	@FindBy(xpath="//button[text()='Cancel']")
	public static WebElement Cancel;

	public static String Predefinedloader = "//div[@class='circle']";

	@FindBy(xpath = "//span[text()='Webhook']")
	public static WebElement webhookOption;





}