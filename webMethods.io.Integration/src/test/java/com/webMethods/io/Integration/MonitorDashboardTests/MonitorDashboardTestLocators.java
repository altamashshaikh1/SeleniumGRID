package com.webMethods.io.Integration.MonitorDashboardTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonitorDashboardTestLocators
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

	public static String login_block_1 = "//div[@id='kc-content']";

	@FindBy(xpath = "//span[text()='Default']")
	public static WebElement defaultProjectString;

	@FindBy(linkText = "Monitor")
	public static WebElement monitorLinktext;

	public static String addNewButtonString = "//a[@title='Add Project']";

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//div[@class='dashboard-card dashboard-card-total']")
	public static WebElement workflowExecutionCount;

	@FindBy(xpath = "//div[@class='dashboard-card dashboard-card-successful']")
	public static WebElement workflowSuccessExecutionCount;

	@FindBy(xpath = "//div[@class='dashboard-card dashboard-card-failed']")
	public static WebElement workflowFailedExecutionCount;

	@FindBy(xpath = "//div[@class='card-total-count-number total']")
	public static WebElement totalExecutionCountNumber;

	@FindBy(xpath = "//div[@class='card-total-count-number successful']")
	public static WebElement workflowSuccessExecutionCountNumber;

	@FindBy(xpath = "//div[@class='card-total-count-number failed']")
	public static WebElement workflowFailedExecutionCountNumber;

	@FindBy(id = "monitorChart XXXXXX")
	public static WebElement monitorChart;

	@FindBy(xpath = "//span[text()='1 day']")
	public static WebElement dayOneOption;

	@FindBy(xpath = "//span[text()='1 week']")
	public static WebElement weekOneOption;

	@FindBy(xpath = "//span[text()='2 weeks']")
	public static WebElement weekTwoOption;

	@FindBy(xpath = "//span[text()='3 weeks']")
	public static WebElement weekThreeOption;

	@FindBy(xpath = "//span[text()='4 weeks']")
	public static WebElement weekFourOption;

	@FindBy(linkText = "Workflow execution")
	public static WebElement workflowExecutionLinkText;

	@FindBy(id = "execution-Chart")
	public static WebElement pieChart;

	@FindBy(xpath =  "//div[@class='monitor-workflow-inner-content']")
	public static WebElement workflowExecutionTable;

	@FindBy(xpath =  "//div[@class='loading-indicator-body']")
	public static WebElement loaderString;

	@FindBy(xpath = "//span[text()='Alert rules']")
	public static WebElement alertRuleText;

	@FindBy(linkText = "Workflow alerts")
	public static WebElement alertRuleLink;

	public static String noAlertRuleText = "//p[text()='No alert rule present']";

	@FindBy(linkText = "Add alert")
	public static WebElement alertNewRuleButton;

	@FindBy(xpath  = "//input[@aid='Name']")
	public static WebElement alertNewRuleName;

	@FindBy(xpath  = "//input[@type='checkbox']")
	public static WebElement alertRuleCheckBox;

	@FindBy(xpath  = "//span[text()='Failed']")
	public static WebElement failedStatus;

	@FindBy(xpath  = "//span[text()='Timeout']")
	public static WebElement timeoutStatus;

	@FindBy(xpath  = "//span[text()='Successful']")
	public static WebElement successStatus;

	@FindBy(xpath  = "//div[text()='Add user']")
	public static WebElement alertRuleUserBox;

	@FindBy(xpath  = "//span[@class='delite-icon dlt-icon-caret-down']")
	public static WebElement alertRuleUserBoxDropDown;

	@FindBy(xpath  = "//*[text()='altamash.shaikh@softwareag.com']")
	public static WebElement emailID;

	@FindBy(xpath  = "//button[@class='btn primary-btn btn-sm save-btn']")
	public static WebElement saveAlertRulesButton;

	@FindBy(xpath = "//*[text()='Alert rule created successfully']")
	public static WebElement createNewAlertRuleMessage;

	@FindBy(xpath = "//*[text()='Alert rule updated successfully']")
	public static WebElement editAlertRuleMessage;

	@FindBy(xpath = "//*[text()='Alert rule deleted successfully']")
	public static WebElement deleteAlertRuleMessage;

	@FindBy(xpath = "//*[text()='No alert rule present']")
	public static WebElement noAlertRuleMessage;

	@FindBy(xpath = "//span[@class='cross-icon dlt-icon-close']")
	public static WebElement closeMessage;

	@FindBy(xpath = "//span[text()='AlertRuleTest']")
	public static WebElement createdAlertRule;

	@FindBy(xpath = "//a[@class='delite-icon dlt-icon-edit']")
	public static WebElement editAlertRulePencil;

	@FindBy(xpath = "//span[text()='Edit workflow alert']")
	public static WebElement editAlertRuleModal;

	@FindBy(xpath = "//a[@class='delite-icon dlt-icon-delete']")
	public static WebElement deleteAlertRulePencil;

	@FindBy(xpath = "//button[@class='btn secondary-btn delete-btn-prmy btn-sm']")
	public static WebElement deleteAlertRuleButton;

	@FindBy(xpath = "//p[text()='No alert rule present']")
	public static WebElement noAlertRule;

	@FindBy(xpath = "//span[text()='General']")
	public static WebElement generalText;

	@FindBy(linkText = "Audit logs")
	public static WebElement auditLogsLink;

	@FindBy(xpath  = "//tbody[@class='no-border']")
	public static WebElement auditLogsTable;

	@FindBy(xpath  = "//input[@class='search-box-input']")
	public static WebElement auditLogsQuerySearchBox;

	@FindBy(xpath  = "//button[@class='btn primary-btn large btn-sm audit-search-btn']")
	public static WebElement querySearchButton;

	@FindBy(xpath  = "//a[@class='dropdown-button primary-button']")
	public static WebElement downloadAuditLogsButton;

	@FindBy(xpath  = "//a[text()='in CSV Format']")
	public static WebElement downloadCSVButton;

	@FindBy(xpath  = "//a[text()='in JSON Format ']")
	public static WebElement downloadJSONButton;

	@FindBy(linkText = "Usage")
	public static WebElement usagesLink;

	@FindBy(xpath = "//div[@class='transactions-consumed']")
	public static WebElement transactionUsagesTable;
}