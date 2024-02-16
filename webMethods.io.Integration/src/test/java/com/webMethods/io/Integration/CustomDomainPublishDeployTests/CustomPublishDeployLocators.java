package com.webMethods.io.Integration.CustomDomainPublishDeployTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomPublishDeployLocators 
{
	@FindBy(xpath = "//span[text()='Assets']")
	public static WebElement assetsModalString;

	@FindBy(xpath = "//span[text()='Assets Name']")
	public static WebElement dependenciesModalString;

	@FindBy(xpath = "//span[text()='Publish settings']]")
	public static WebElement publishSettingsModalString;

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

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(xpath = "//a[@name='PublishProjectTest-more-icon']")
	public static WebElement projectEllipsis;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//a[@name='PublishProjectTest-publish']")
	public static WebElement projectPublishProject;

	@FindBy(xpath = "//div[@class='published-select-table']")
	public static WebElement assetsModal;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath = "//button[@class='btn btn-danger btn-sm btn-primary']")
	public static WebElement saveAndContinueButton;

	@FindBy(xpath = "//button[text()='Publish']")
	public static WebElement publishButton;

	@FindBy(xpath = "//button[text()='Deploy']")
	public static WebElement deployButton;

	@FindBy(xpath = "//span[text()='webhookWorkflow']")
	public static WebElement asset1;

	@FindBy(xpath = "//span[text()='getDate']")
	public static WebElement asset2;

	@FindBy(xpath = "//span[text()='RestConnectorTest']")
	public static WebElement asset3;

	@FindBy(xpath = "//span[text()='SoapConnectorTest']")
	public static WebElement asset4;

	@FindBy(xpath = "//span[text()='getDate']")
	public static WebElement asset5;

	@FindBy(xpath = "//span[text()='webhookWorkflow']")
	public static WebElement asset6;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement deploymentNameTextBox;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-caret-down']")
	public static WebElement publishDropDown;

	@FindBy(id = "react-select-10-input")
	public static WebElement publishProjectTextBox;

	@FindBy(xpath = "/div[text()='devrealm2']")
	public static WebElement destinationTenantName;

	@FindBy(xpath = "//*[text()='Automationtestdev2']")
	public static WebElement environmentName;

	@FindBy(xpath = "//input[@aid='Password']")
	public static WebElement passwordField;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(css = "a[title='Add Project']")
	public static WebElement addNewProjectButton;

	@FindBy(xpath = "//span[text()='PublishProjectTest']")
	public static WebElement projectDeployedName;

	@FindBy(xpath = "//h3[text()='Skip Assets']")
	public static WebElement skipassetsString;

	@FindBy(xpath = "//div[@data-testid='asset-skip']")
	public static WebElement assetModal;

	@FindBy(xpath = "//div[@id='Connect to JotForm']")
	public static WebElement configureAccountString;

	@FindBy(xpath = "//span[text()='add']")
	public static WebElement addAccountIcon;

	@FindBy(xpath = "//input[@aid='API Key']")
	public static WebElement jotformAPIKeyTextBox;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement addButtonConnection;

	@FindBy(xpath = "//*[text()='Configure triggers']")
	public static WebElement configureTriggerString;

	@FindBy(xpath = "//div[text()='Please Select']")
	public static WebElement dropdownAccountTrigger;

	@FindBy(xpath = "//div[text()='Jotform_1']")
	public static WebElement triggerAccountName;

	@FindBy(xpath = "//input[@class='inputElement textbox-edit']")
	public static WebElement jotformTextBox;

	@FindBy(xpath = "//*[text()='Configure parameters']")
	public static WebElement configureParameter;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement projectDeployementMessage;

	@FindBy(xpath = "//span[@title='PublishProjectTest']")
	public static WebElement deployedProject;

	@FindBy(xpath = "//a[text()='5 Workflows ']")
	public static WebElement numberOfWorkflows;

	@FindBy(xpath = "//a[text()='1 Flow service']")
	public static WebElement numberOfFlows;   

	@FindBy(xpath = "//div[@aid='WorkflowUsingRestSoapConnector']")
	public static WebElement workflow1;

	@FindBy(xpath = "//div[@aid='webhookWorkflow']")
	public static WebElement workflow2;

	@FindBy(xpath = "//div[@aid='ConnectionTriggerParamsWorkflow']")
	public static WebElement workflow3;

	@FindBy(xpath = "//div[@aid='workflowUsingFlow']")
	public static WebElement workflow4;

	@FindBy(xpath = "//div[@aid='runflowTest']")
	public static WebElement workflow5;

	@FindBy(xpath = "//*[text()='Flow services']")
	public static WebElement flowServicesLink;

	@FindBy(xpath = "//*[text()='getDate']")
	public static WebElement flowServices;

	@FindBy(xpath = "//*[text()='APIs']")
	public static WebElement apiTabLink;

	@FindBy(xpath = "//span[@title='RestAPI']")
	public static WebElement restAPI;

	@FindBy(linkText = "SOAP API")
	public static WebElement SoapAPILink;

	@FindBy(xpath = "//span[@title='SoapAPI']")
	public static WebElement soapAPI;

	@FindBy(xpath =  "//*[text()='Connectors ']")
	public static WebElement connectorsLink;

	@FindBy(xpath = "//span[@title='Jotform']")
	public static WebElement addedConnection;

	@FindBy(linkText = "REST")
	public static WebElement RestConnectorLink;

	@FindBy(linkText = "RestConnectorTest")
	public static WebElement RestConnector;

	@FindBy(linkText = "SOAP")
	public static WebElement SoapConnectorLink;

	@FindBy(linkText = "SoapConnectorTest")
	public static WebElement SoapConnector;

	@FindBy(xpath =  "//*[text()='Configurations']")
	public static WebElement configurationsLink;

	@FindBy(xpath =  "//*[text()='Workflow']")
	public static WebElement configurationWorkflow;

	@FindBy(linkText = "Connections")
	public static WebElement configurtionConnectionLink;

	@FindBy(xpath = "//span[text()='RestConnectorTest']")
	public static WebElement restConnectorAccount;

	@FindBy(xpath = "//span[text()='SoapConnectorTest']")
	public static WebElement soapConnectorAccount;

	@FindBy(xpath = "//span[text()='Jotform']")
	public static WebElement jotFormConnectorAccount;

	@FindBy(linkText = "Triggers")
	public static WebElement triggersConfigurationLink;

	@FindBy(xpath = "//span[@title='JotForm']")
	public static WebElement jotFormTrigger;

	@FindBy(xpath = "//span[text()='Deployments']")
	public static WebElement deploymentLink;

	@FindBy(xpath = "//span[text()='Current']")
	public static WebElement versionData;

	@FindBy(xpath = "//div[@class='sci-list-group margin-bottom-size-6']")
	public static WebElement enrvironmentLists;
}