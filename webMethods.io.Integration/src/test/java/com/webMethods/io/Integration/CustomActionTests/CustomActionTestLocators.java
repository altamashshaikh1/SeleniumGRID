package com.webMethods.io.Integration.CustomActionTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomActionTestLocators
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

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//span[@title='HarshadUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath="//span[contains(text(),'ActionConfiguration')]")
	public static WebElement ActionConfiguration;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(id="a0")
	public static WebElement Cloudstream;

	@FindBy(xpath="//span[@title='updateLead']")
	public static WebElement test1;

	@FindBy(xpath = "//div[contains(text(),'Select Action')]")
	public static WebElement actionListdropdown;

	@FindBy(xpath="//div[@id='react-select-11-option-0-20']/div/button/i'")
	public static WebElement searchdropdown1;

	@FindBy(xpath = "//button[@aid='edit']")
	public static WebElement customOperEditIcon;

	@FindBy(xpath="//input[@tabindex='0']")
	public static WebElement actionsearchfield;

	@FindBy(xpath="//h1[text()='Edit Custom Operation']")
	public static WebElement EditCustomOperation;

	@FindBy(xpath = "//button[text()='Yes']") 
	public static WebElement YES;

	@FindBy(xpath="//span[contains(text(),'Account')]")
	public static WebElement AddCustomAction;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement Next;

	@FindBy(xpath = "//button[text()='Cancel']")
	public static WebElement Cancel;

	@FindBy(xpath="//span[contains(text(),'compositeRequest')]")
	public static WebElement CompositeRequest;

	@FindBy(xpath="//span[@class='labelvalue']")
	public static WebElement CompositeRequestlable;

	@FindBy(xpath="//span[@class='labelvalue']")
	public static WebElement BusinessObjectlable;

	@FindBy(xpath="//span[text()='Name']")
	public static WebElement create;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement loader;

	@FindBy(xpath="//span[text()='Available Business Object']")
	public static WebElement verifyaccount;

	@FindBy(xpath = "//button[@aid='create-1']")
	public static WebElement Edit;

	@FindBy(xpath = "//span[text()='AccountFromId']")
	public static WebElement AccountFromId;

	@FindBy(xpath = "//span[text()='AccountToId']")
	public static WebElement AccountToId;

	@FindBy(xpath = "//span[text()='OpportunityId']")
	public static WebElement OpportunityId;

	@FindBy(xpath = "//input[@id='compositeRequest']")
	public static WebElement compositeRequest;
}