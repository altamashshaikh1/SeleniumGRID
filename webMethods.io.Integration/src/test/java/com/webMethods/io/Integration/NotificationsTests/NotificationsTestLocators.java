package com.webMethods.io.Integration.NotificationsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationsTestLocators 
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

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loaderString;

	@FindBy(xpath = "//*[@class='badge']")
	public static WebElement notificationCount;

	@FindBy(xpath = "//i[@class='delite-icon dlt-icon-notification ']")
	public static WebElement notificationBellIcon;

	@FindBy(xpath = "//a[text()='Workflow AltamashUpdateAutomationWorkflow has been started manually.']")
	public static WebElement LogsExecutionModal;

	@FindBy(id = "usernotification")
	public static WebElement allNotifications;

	@FindBy(xpath = "//a[text()='Workflow AltamashUpdateAutomationWorkflow has been started manually.']")
	public static WebElement latestNotification;

	@FindBy(id = "monitor-activity-modal")
	public static WebElement logsModal;

	@FindBy(xpath = "//a[contains(text(),’Workflow’)]")
	public static WebElement latestNotificationData;

	@FindBy(xpath = "//div[@id='monitor-activity-modal']")
	public static WebElement executionLogsData;

	@FindBy(xpath = "//span[@class='title']")
	public static WebElement exportLogsString;

	@FindBy(xpath = "//a[text()='Export Logs']")
	public static WebElement exportLogs;
}