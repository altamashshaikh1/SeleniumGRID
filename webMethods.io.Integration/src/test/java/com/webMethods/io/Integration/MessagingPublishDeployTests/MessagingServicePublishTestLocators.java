package com.webMethods.io.Integration.MessagingPublishDeployTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagingServicePublishTestLocators 
{
	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewButtonString;

	@FindBy(xpath = "//span[text()='Assets Name']")
	public static WebElement dependenciesModalString;

	@FindBy(xpath = "//span[text()='Publish settings']")
	public static WebElement publishSettingsModal;

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

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='MaaS_E2ETest']")
	public static WebElement maasprojectDeployedName;

	@FindBy(xpath = "//span[@title='MaaS_E2ETest']")
	public static WebElement maasdeployedProjectName;

	@FindBy(xpath = "//a[@name='WF_MessagingAutomationDataProject-publish']")
	public static WebElement projectPublishProject;

	@FindBy(xpath = "//a[@name='WF_MessagingAutomationDataProject-more-icon']")
	public static WebElement projectEllipsis;

	@FindBy(xpath = "//a[@name='MaaS_E2ETest-publish']")
	public static WebElement maasProject;

	@FindBy(xpath = "//a[@name='MaaS_E2ETest-more-icon']")
	public static WebElement maasprojectEllipsis;

	@FindBy(xpath = "//a[@name='JMS_Automation-publish']")
	public static WebElement jms_Project;

	@FindBy(xpath = "//a[@name='JMS_Automation-more-icon']")
	public static WebElement jms_Ellipsis;

	@FindBy(xpath = "//div[@class='published-select-table']")
	public static WebElement assetsModal;

	@FindBy(xpath = "//span[text()='Assets']")
	public static WebElement assetsLabel;

	@FindBy(xpath = "//button[@class='btn btn-danger btn-sm btn-primary']")
	public static WebElement saveAndContinueButton;

	@FindBy(xpath = "//button[text()='Publish']")
	public static WebElement publishButton;

	@FindBy(xpath = "//div[@class='published-confirmation-inner-content']")
	public static WebElement assetDependentModal;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement deploymentNameTextBox;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(xpath = "//span[text()='WF_MessagingAutomationDataProject']")
	public static WebElement projectDeployedName;

	@FindBy(xpath = "//span[text()='JMS_Automation']")
	public static WebElement jms_projectDeployedName;

	@FindBy(xpath = "//span[@title='JMS_Automation']")
	public static WebElement jms_deployedProjectName;

	@FindBy(xpath = "//h3[text()='Skip Assets']")
	public static WebElement skipassetsString;

	@FindBy(xpath = "//*[text()='Configure accounts']")
	public static WebElement configureAccountString;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath = "//span[text()='add']")
	public static WebElement addAccount;

	@FindBy(xpath = "//button[text()='Deploy']")
	public static WebElement deployButton;

	@FindBy(xpath = "//button[text()='No']")
	public static WebElement Donotdeploy;

	@FindBy(xpath = "//button[text()='Yes']")
	public static WebElement yesdeploy;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement projectDeployementMessage;

	@FindBy(xpath = "//span[@title='WF_MessagingAutomationDataProject']")
	public static WebElement deployedProjectName;

	@FindBy(xpath = "//a[text()='6 Workflows ']")
	public static WebElement totalWorkflows;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addNewWorkflowButtonString;

	@FindBy(xpath = "//span[text()='Messaging']")
	public static WebElement messageLink;

	@FindBy(linkText =  "Subscribers")
	public static WebElement subscriberLinkText;

	@FindBy(xpath = "//span[text()='Topic_Publisher']")
	public static WebElement TopicPublisher;

	@FindBy(linkText = "FlowServices")
	public static WebElement flowTab;

	@FindBy(xpath="//li[@data-flow-title='Run']")
	public static WebElement runFlowbutton;

	@FindBy(linkText ="Integrations")
	public static WebElement IntegrationsTab;

	@FindBy(xpath = "//div[@class='messaging-list-table-body']")
	public static WebElement subscriberList;
}