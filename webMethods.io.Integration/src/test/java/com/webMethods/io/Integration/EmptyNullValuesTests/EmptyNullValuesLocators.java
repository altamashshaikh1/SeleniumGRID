package com.webMethods.io.Integration.EmptyNullValuesTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmptyNullValuesLocators 
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

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

	@FindBy(xpath ="//input[@placeholder='Search']")
	public static WebElement projectSearchTextBox2;

	public static String login_blockString = "//div[@id='kc-content']";

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	public static String login_block_1 = "//div[@id='kc-content']";

	public static String addNewButtonString = "//a[@title='Add Project']";

	@FindBy(xpath ="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox1;

	//@FindBy(xpath = "//*[@id='project_section']/section/div[2]/div[2]/div/input")
	//public static WebElement projectSearchTextBox1;

	@FindBy(xpath = "//span[contains(text(),'HarshadUIAutomation')]")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[@title='HarshadUIAutomation']")
	public static WebElement myUIautomationproject;

	public static String loadingindicator= "//div[@class='loading-indicator-body']";

	@FindBy(id="a0")
	public static WebElement restActionId;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;
	// delite-icon dlt-icon-search searchbox-search-icon

	@FindBy(xpath="//span[@class='delite-icon dlt-icon-search searchbox-search-icon']")
	public static WebElement ClickSearch;

	//@FindBy(xpath="//span[@class='edit-flow']")
	//public static WebElement editWorkflow2;

	@FindBy(xpath="//span[contains(text(),'Okta_Null')]")
	public static WebElement Okta_Null;

	@FindBy(xpath="//span[@class='activity-id']")
	public static WebElement okta1;

	@FindBy(id="a0")
	public static WebElement Oktaconnector;

	@FindBy(id="a0")
	public static WebElement Oktaconnector2;

	@FindBy(xpath="//span[@title='Update User']")
	public static WebElement UpdateUser;

	@FindBy(xpath="//span[@title='Update User']")
	public static WebElement UpdateUser2;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	//@FindBy(xpath="//div[class=activity-box-wrapper'")
	//public static WebElement Oktaconnector;

	//@FindBy(xpath="//span[@class='modal-content pt-0 pb-0 clearfix modal-height-content default-modal-height-content overflow-visible']")
	//public static WebElement UpdateUser;
	public static String miniloader= "//div[@aid='miniLoader']";

	@FindBy(xpath="//button[contains(text(),'Next')]")
	public static WebElement Nextclick;

	@FindBy(xpath="//button[contains(text(),'Next')]")
	public static WebElement Nextclick1;

	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	public static WebElement Cancel;

	@FindBy(xpath="//button[contains(text(),'Done')]")
	public static WebElement Done;

	@FindBy(xpath="//button[contains(text(),'Done')]")
	public static WebElement Done1;

	//@FindBy(id="prune-checked")
	//public static WebElement AllowEmptyValue;

	@FindBy(xpath="//span[text()='Allow empty values']")
	public static WebElement AllowEmptyValue;

	@FindBy(xpath="//span[text()='Allow empty values']")
	public static WebElement AllowEmptyValue1;


	//public static String Unchecked = "//input[@state='unchecked']";

	@FindBy(xpath="//input[@state='unchecked']")   //xpath="//span[@class='new-checkbox-label']")     
	public static WebElement Unchecked;

	//@FindBy(xpath="//button[contains(text(),'Next')]")
	//public static WebElement Nextclick;

	@FindBy(xpath="//input[@state='checked']")
	public static WebElement checked;

	@FindBy(xpath="//span[@class='activity-box-wrapper']")
	public static WebElement Oktahover;

	@FindBy(xpath="//div[@title='Settings']")
	public static WebElement Settings;

	@FindBy(xpath="//button[contains(text(),'Save')]")
	public static WebElement Save;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas1;

	public static String addworkfloButton = "//a[@title='Add Workflow']";

	@FindBy(xpath="//button[contains(text(),'Save and exit')]")
	public static WebElement Saveandexit;

	@FindBy(id = "canvasContainer")
	public static WebElement canvasAssets1;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workSave;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessagedisplay;



	@FindBy(xpath="//span[contains(text(),'Copyworkflow')]")
	public static WebElement Copyworkflow;


	@FindBy(xpath="//button[contains(text(),'Save and exit')]")
	public static WebElement Exit;

}
