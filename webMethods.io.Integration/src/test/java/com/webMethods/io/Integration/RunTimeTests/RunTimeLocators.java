package com.webMethods.io.Integration.RunTimeTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RunTimeLocators 
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

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement projectSearchBox;

	@FindBy(xpath = "//span[@title='WF_MESSAGING_BACKLOG']")
	public static WebElement searchedProject;

	@FindBy(xpath = "//button[@class='btn secondary-btn btn-sm btn-import']")
	public static WebElement newWorkflowButton;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowButton;

	@FindBy(xpath = "//div[@title='WF_CASE_TWO']")
	public static WebElement createdWorkflow;

	@FindBy(linkText = "Messaging")
	public static WebElement messagingLink;

	@FindBy(linkText = "Subscribers")
	public static WebElement subsriberLink;

	@FindBy(linkText = "QueueOne")
	public static WebElement queueOneLink;

	@FindBy(linkText = "RUNNABLE_SUBS")
	public static WebElement runnableQueueLink;

	@FindBy(xpath = "//div[@aid='name_value']")
	public static WebElement subscriberEditModal;

	@FindBy(xpath = "//span[text()='Metrics']")
	public static WebElement queueMetricsTable;

	@FindBy(xpath = "//button[text()='Disable']")
	public static WebElement disableSubscriberOption;

	@FindBy(xpath = "//div[@data-testid='deleteModal']")
	public static WebElement disableConfirmModal;

	@FindBy(xpath = "//button[text()='Confirm']")
	public static WebElement confirmButton;

	@FindBy(xpath = "//*[text()='Successfully disabled the subscriber.']")
	public static WebElement subscriberDisableMessage;

	@FindBy(linkText = "Queues")
	public static WebElement queuesLink;

	@FindBy(linkText = "RUNNABLE_QUEUE")
	public static WebElement runnableQueue;

	@FindBy(xpath = "//button[text()='Enable']")
	public static WebElement enableSubscriberOption;

	@FindBy(xpath = "//*[text()='Successfully enabled the subscriber.']")
	public static WebElement subscriberEnableMessage;

	@FindBy(xpath = "//div[@aid='published_value']")
	public static WebElement publishMessageLocator;

	@FindBy(xpath = "//div[@aid='consumed_value']")
	public static WebElement consumedMessageLocator;

	@FindBy(xpath = "//div[@aid='pending_value']")
	public static WebElement pendingMessageLocator;

	@FindBy(xpath = "//button[text()='Disabled']")
	public static WebElement disabledButton;

	@FindBy(xpath = "//a[@name='RUNNABLE_SUBS_menu'][@data-activates='2']")
	public static WebElement runnableSubscriberEllipsisOption;

	@FindBy(xpath = "//ul[@id='2']//a[@title='Enable Subscriber']")
	public static WebElement runnableSubscriberEnableOption;

	@FindBy(linkText = "Integrations")
	public static WebElement integrationsLink;

	@FindBy(xpath = "//div[@title='DEFAULT_INVOCATION_WF']//span[@class='stats']")
	public static WebElement executionCount;
}