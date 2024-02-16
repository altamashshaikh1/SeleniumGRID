package com.webMethods.io.Integration.RunConnectorsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RunconnectorsLocators
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

	@FindBy(xpath="//span[contains(text(),'Runtrustedconnector')]")
	public static WebElement Runtrustedconnector;

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
}