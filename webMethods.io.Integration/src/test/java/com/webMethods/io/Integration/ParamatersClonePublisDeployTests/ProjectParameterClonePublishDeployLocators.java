package com.webMethods.io.Integration.ParamatersClonePublisDeployTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectParameterClonePublishDeployLocators 
{
	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(xpath ="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='VarshithaUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//span[@title='JMS_Automation']")
	public static WebElement myUIautomationproject2;

	@FindBy(xpath = "//div[text()='Info: A new deployment is available. Do you want to update it?']")
	public static WebElement Info;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle1;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//button[text()='YES']")
	public static WebElement YES ;

	@FindBy(xpath = "//button[text()='Save and continue']")
	public static WebElement SaveandContinue ;

	@FindBy(xpath = "//span[text()='Configurations']")
	public static WebElement Configurations;

	@FindBy(xpath="//span[text()='Workflow']")
	public static WebElement workflow;

	@FindBy(xpath="//span[text()='Workflows']")
	public static WebElement Assetworkflows;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement Next;

	@FindBy(xpath = "//h3[text()='Skip Assets']")
	public static WebElement SkipAssets;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement InputName;

	@FindBy(xpath = "//textarea[@aid='Description']")
	public static WebElement InputDescription ;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-caret-down']")
	public static WebElement Dropdown ;

	@FindBy(xpath = "//span[text()='devrealm2']")
	public static WebElement Devreaml2 ;

	@FindBy(xpath = "//button[text()='Publish']")
	public static WebElement Publish ;

	@FindBy(xpath = "//*[text()='Parameter']")
	public static WebElement Parameter;

	@FindBy(xpath="//span[contains(text(),'ParameterCheck')]")
	public static WebElement ParameterCheck;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath = "//span[contains(text(),'Integrations')]")
	public static WebElement Integrations;

	@FindBy(xpath = "//a[text()='Projects']")
	public static WebElement Projects;

	@FindBy(xpath="//span[contains(text(),'Integrations')]")
	public static WebElement Integration;

	@FindBy(xpath="//span[contains(text(),'ParameterCheck')]")
	public static WebElement Workflowname;

	@FindBy(xpath="//span[contains(text(),'Copy of ParameterCheck')]")
	public static WebElement Workflowname1;

	@FindBy(xpath="//span[contains(text(),'Copy of Copy of ParameterCheck')]")
	public static WebElement Workflowname2;

	@FindBy(xpath = "//i[@class=' dlt-icon-more-menu']")
	public static WebElement elopsis1;

	@FindBy(xpath = "//a[@name='VarshithaUIAutomation-more-icon']")
	public static WebElement elopsis;

	@FindBy(xpath = "//a[@name='VarshithaUIAutomation-publish']//span[text()='Publish Project']")
	public static WebElement Publishproject;

	@FindBy(xpath = "//button[text()='Deploy']")
	public static WebElement deployButton;

	@FindBy(xpath = "//span[text()='Delete']")
	public static WebElement deleteworkflow;

	@FindBy(xpath = "//*[text()='Are you sure you want to delete this Workflow?']")
	public static WebElement popup1;

	@FindBy(xpath = "//*[text()='Are you sure you want to delete this project ? This will automatically delete all the existing assets and configurations of the project.']")
	public static WebElement popup2;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deleteworkflow1;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deleteworkflow1YES;


	@FindBy(xpath = "//span[text()='Clone']")
	public static WebElement Clone;

	@FindBy(xpath = "//button//span[text()='Clone']")
	public static WebElement Clone1;

	@FindBy(xpath = "//*[text()='Workflow cloned successfully.']")
	public static WebElement workflowclonedMessage_str;

	@FindBy(xpath = "//*[text()='Project param deleted successfully.']")
	public static WebElement ParamdeletedMessage_str;

	@FindBy(xpath = "//div[@aid='project_selection']//div//div//div//div[text()='VarshithaUIAutomation']")
	public static WebElement triggerAuthDropdown;

	@FindBy(xpath = "//div[@aid='material-select-id']//div//div//div//div[text()='VarshithaUIAutomation']")
	public static WebElement triggerAuthDropdown1;

	@FindBy(xpath = "//div[@aid='material-select-id']//div//div//div//div[text()='HarshadUIAutomation']")
	public static WebElement triggerAuthDropdown2;

	@FindBy(xpath="//div[@aid='project_selection']//div//div//div//div//div//input[@tabindex='0']")
	public static WebElement actionsearchfield;

	@FindBy(xpath="//div[@aid='material-select-id']//div//div//div//div//div//input[@tabindex='0']")
	public static WebElement actionsearchfield1;

	@FindBy(xpath = "//div[text()='HarshadUIAutomation']")
	public static WebElement SelectProject2;

	@FindBy(xpath = "//div[text()='JMS_Automation']")
	public static WebElement SelectProject3;

	@FindBy(xpath="//button[@data-eventmap='flow-settings']")
	public static WebElement Settings;

	@FindBy(xpath="//span[text()='Parameters']")
	public static WebElement Parameterclick;

	@FindBy(xpath="//div[@class='row margin-1']//div[1]//div//span[text()='test5']")
	public static WebElement test5;

	@FindBy(xpath="//div[@class='row margin-1']//div[2]//div//span[text()='test4']")
	public static WebElement test4;

	@FindBy(xpath="//div[@class='row margin-1']//div[3]//div//span[text()='test3']")
	public static WebElement test3;

	@FindBy(xpath="//div[@class='row margin-1']//div[4]//div//span[text()='test2']")
	public static WebElement test2;

	@FindBy(xpath="//div[@class='row margin-1']//div[5]//div//span[text()='test1']")
	public static WebElement test1;

	@FindBy(xpath="//h1[@title='Clone Workflow : ParameterCheck']")
	public static WebElement Element;

	@FindBy(xpath="//h1[@title='Clone Workflow : Copy of ParameterCheck']")
	public static WebElement Element2;

	@FindBy(xpath = "//div[@aid='project_selection']//div//div//div//div[text()='HarshadUIAutomation']")
	public static WebElement triggerAuthDropdownProject2;

	@FindBy(xpath = "//a[@data-activates='param-0']")
	public static WebElement DeleteParameter01;

	@FindBy(xpath = "//span[@class=' drop-down-params']//ul[@id='param-0']//li//a//span[text()='Delete']")
	public static WebElement Delete1;

	@FindBy(xpath = "//span[@class=' drop-down-params']//ul[@id='param-1']//li//a//span[text()='Delete']")
	public static WebElement Delete2;

	@FindBy(xpath = "//span[@class=' drop-down-params']//ul[@id='param-2']//li//a//span[text()='Delete']")
	public static WebElement Delete3;

	@FindBy(xpath = "//span[@class=' drop-down-params']//ul[@id='param-3']//li//a//span[text()='Delete']")
	public static WebElement Delete4;

	@FindBy(xpath = "//span[@class=' drop-down-params']//ul[@id='param-4']//li//a//span[text()='Delete']")
	public static WebElement Delete5;

	@FindBy(xpath = "//*[text()='Configure accounts']")
	public static WebElement configureAccountString;

	@FindBy(xpath = "//h3[text()='Configure triggers']")
	public static WebElement Configuretriggers;

	@FindBy(xpath = "//h3[text()='Configure parameters']")
	public static WebElement Configureparameters;

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	//@FindBy(xpath = "//div[contains(.,'Log in with environment')]")
	//public static WebElement ssoLoginLink;

}
