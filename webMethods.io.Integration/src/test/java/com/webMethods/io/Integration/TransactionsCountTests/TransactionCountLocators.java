package com.webMethods.io.Integration.TransactionsCountTests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransactionCountLocators 
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	public static String defaultProjectString = "//span[text()='Default']";

	@FindBy(linkText = "Monitor")
	public static WebElement monitorLinktext;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//div[@class='dashboard-card dashboard-card-total']")
	public static WebElement workflowExecutionCount;

	@FindBy(linkText = "Workflow execution")
	public static WebElement workflowExecutionLinkText;

	@FindBy(id = "execution-Chart")
	public static WebElement pieChart;

	@FindBy(xpath = "//span[text()='1 day']")
	public static WebElement dayOneOption;

	@FindBy(xpath =  "//div[@class='monitor-workflow-inner-content']")
	public static WebElement workflowExecutionTable;

	@FindBy(xpath = "//a[@class ='dropdown-button setting-icon']")
	public static WebElement monitorpageSettingIcon;

	@FindBy(xpath = "//ul[@class='dropdown-content dropdown-setting-content  ']")
	public static WebElement settingtablecontent;

	public static String TranasctionCountString = "//label[@for='tx_cnt']";

	@FindBy(xpath = "//input[@id ='tx_cnt']")
	public static WebElement transactioncountcheckbox;

	@FindBy(xpath = "//span[text() ='Transaction Count']")
	public static WebElement transactioncountopt;

	@FindBy(xpath = "//button[text() ='Save']")
	public static WebElement saveButton;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//span[@class='inner-row-title'][contains(.,'Transaction count')]")
	public static WebElement txcntcolumnintable;

	@FindBy(xpath = "//span[text()='Filters']")
	public static WebElement Filters;

	@FindBy(xpath = "//label[@htmlfor='select-input']")
	public static List<WebElement> Filtersorder;

	@FindBy(xpath = "//span[contains(text(),'Select execution status ')]")
	public static WebElement Selectexecutionstatus ;

	@FindBy(xpath="//input[@placeholder='Search execution status']")
	public static WebElement actionsearchfield;

	@FindBy(xpath="//input[@placeholder='Search projects']")
	public static WebElement actionsearchfield1;

	@FindBy(xpath="//label[text()='Success']")
	public static WebElement SelectSuccess;

	@FindBy(xpath = "//span[contains(text(),'Select projects ')]")
	public static WebElement Selectprojects;

	@FindBy(xpath="//label[text()='Auto_UIAPI_TransactionCount']")
	public static WebElement SelectProject;

	@FindBy(xpath="//button[text()='Apply']")
	public static WebElement SelectApply;

	@FindBy(xpath="//span[@title='Refresh']")
	public static WebElement refreshOption;

	@FindBy(xpath="//span[@id='tx_cnt-6']")
	public static WebElement txcntoptionintable;

	@FindBy(xpath="(//span[@aid='Workflow B'][text()='-'])[1]")
	public static WebElement workflowBtxcnt;

	@FindBy(xpath="(//span[@aid='Workflow C'][text()='-'])[1]")
	public static WebElement workflowCtxcnt;

	@FindBy(xpath="(//span[@aid='Workflow A'][@title='1'])[1]")
	public static WebElement workflowAtxcnt;

	@FindBy(xpath="//span[@aid='Workflow C']//span[@title='Transaction Count is unavailable because the workflow is being invoked by another workflow or an HTTP Request action (synchronously), or there may be an issue preventing its execution.']")
	public static WebElement workflowCinfomessage;

	@FindBy(xpath="(//a[@title='Workflow C'])[1]")
	public static WebElement workflowC1stentry;

	@FindBy(xpath="//div[@class='monitor-activity-header clearfix']")
	public static WebElement workflowdetailslogsinfosec;

	@FindBy(xpath="//span[@class='left'][@aid='transcation_count']")
	public static WebElement txcntofchildwfindetaillogs;

	@FindBy(xpath="//span[@class='dlt-icon-close']")
	public static WebElement detaillogsclosedopt;

	@FindBy(xpath="(//a[@title='Workflow A'])[1]")
	public static WebElement workflowA1stentry;

	@FindBy(xpath="//span[@aid='transcation_count'][contains(.,'1')]")
	public static WebElement txcntofwfindetaillogs;
}