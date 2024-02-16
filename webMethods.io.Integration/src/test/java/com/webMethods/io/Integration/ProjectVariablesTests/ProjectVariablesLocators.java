package com.webMethods.io.Integration.ProjectVariablesTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectVariablesLocators 
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

	@FindBy(xpath = "//span[@title='ProjectVariablesTest']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//*[text()='Configurations']")
	public static WebElement Configurationtab;

	@FindBy(xpath="//a[text()='Variables']")
	public static WebElement Variablestab;

	@FindBy(xpath="//span[text()='Variables']")
	public static WebElement Variables;

	@FindBy(xpath="//span[text()='Name']")
	public static WebElement Name;

	@FindBy(xpath="//span[text()='Value']")
	public static WebElement Value;

	@FindBy(xpath="//a[text()='New variable']")
	public static WebElement Newvariablebutton ;

	@FindBy(xpath="//a[text()='Create new variable']")
	public static WebElement Newvariablebutton1 ;

	@FindBy(xpath="//h1[text()='New Variable']")
	public static WebElement NewVariable ;

	@FindBy(xpath="//input[@placeholder ='Enter variable name']")
	public static WebElement EnterName;

	@FindBy(xpath="//input[@placeholder ='Enter variable value']")
	public static WebElement EnterValue;

	@FindBy(xpath="//button[text() ='Create variable']")
	public static WebElement CreateVariable;

	@FindBy(xpath="//span[contains(text(),'Integrations')]")
	public static WebElement Integration;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	@FindBy(xpath = "//span[@title='Salesforce® CRM SOAP']")
	public static WebElement actionSearched;

	@FindBy(id="a0")
	public static WebElement ActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(xpath="//button[text()='Add variable']")
	public static WebElement AddVariable;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath="//a//span[contains(text(),'Connectors')]")
	public static WebElement Connectors;

	@FindBy(xpath="//span[@class = 'com.softwareag.cloudstreams.salesforce_v53 icon-40 icon-40 inner-view-icon']")
	public static WebElement Salesforceaccount;

	@FindBy(xpath="//button[text() ='New account']")
	public static WebElement Newaccount;

	@FindBy(xpath="//h1[text() ='Add Account']")
	public static WebElement Addaccount;

	@FindBy(xpath="//div[text() ='Select Authorization Type']")
	public static WebElement selectauth;

	@FindBy(xpath="//div[text() ='Credentials']")
	public static WebElement Credentials;

	@FindBy(xpath="//button[text() ='Next']")
	public static WebElement Next;

	@FindBy(xpath="//span[text() ='Username']")
	public static WebElement Username;

	@FindBy(xpath="//input[@name ='Username']")
	public static WebElement InputUsername;

	@FindBy(xpath="//label[@for='cr.username']/span[text()='Substitute project variables']")
	public static WebElement globalusername;

	@FindBy(xpath="//input[@name ='Password']")
	public static WebElement InputPassword;

	@FindBy(xpath="//label[@for='cr.password']/span[text()='Substitute project variables']")
	public static WebElement globalpassword;

	@FindBy(xpath="//button[text()='Add']")
	public static WebElement Add;

	@FindBy(xpath="//div[text()='Account saved successfully.']")
	public static WebElement Msg_Accountadded;

	@FindBy(xpath="//div[@aid='SalesforceUsername-4']/div/div/a[@class='delite-icon dlt-icon-delete delete-icon-variable']")
	public static WebElement Deleteusername;

	@FindBy(xpath="//div[@aid='SalesforcePassword-4']/div/div/a[@class='delite-icon dlt-icon-delete delete-icon-variable']")
	public static WebElement Deletepassword;

	@FindBy(xpath="//button[text()='Delete']")
	public static WebElement ConfirmDelete;

	@FindBy(xpath="//div[text()= 'Variable created successfully.']")
	public static WebElement Msg_variablecreated;

	@FindBy(xpath="//div[text()= 'Variable added successfully.']")
	public static WebElement Msggbl_variablecreated;

	@FindBy(xpath="//span[text()='Select from vault']")
	public static WebElement toggleText;

	@FindBy(xpath="//span[@data-key='asset_store']")
	public static WebElement toggleselect;

	@FindBy(xpath="//span[text()= 'DONOTDELETE']")
	public static WebElement globalvariable;

}