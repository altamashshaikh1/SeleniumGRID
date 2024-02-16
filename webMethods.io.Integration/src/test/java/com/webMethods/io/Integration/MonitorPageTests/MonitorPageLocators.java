package com.webMethods.io.Integration.MonitorPageTests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonitorPageLocators
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

	public static String addNewButtonString1 = "//a[@title='Add Project']";

	@FindBy(xpath ="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle1;

	public static String accnamevalidatorcircle = "//div[@class='circle']";

	@FindBy(xpath = "//span[@title='VarshithaUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath="//span[contains(text(),'RestartFail')]")
	public static WebElement RestartFail;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath = "//a[text()='Monitor']")
	public static WebElement Monitor ;

	@FindBy(xpath = "//a[text()='Projects']")
	public static WebElement Projects ;

	@FindBy(xpath = "//a[text()='Workflow execution']")
	public static WebElement Workflowexecution ;

	@FindBy(xpath = "//span[text()='Filters']")
	public static WebElement Filters;

	@FindBy(xpath = "//label[@htmlfor='select-input']")
	public static List<WebElement> Filtersorder;

	@FindBy(xpath = "//span[starts-with(@class, 'toggle-arrows delite-icon')]")
	public static WebElement tempDropDownStatus;

	@FindBy(xpath = "//span[@class='toggle-arrows delite-icon dlt-icon-chevron-up']")
	public static WebElement upDropDown;

	@FindBy(xpath = "//span[@class='toggle-arrows delite-icon dlt-icon-chevron-down']")
	public static WebElement downDropDown;
	//div[@class="dashboard-main"]

	@FindBy(xpath = "//div[@class='dashboard-main']")
	public static WebElement Overview;

	@FindBy(xpath = "//div[@class='successful-executions']")
	public static WebElement SuccessfullExecutions;

	@FindBy(xpath = "//div[@class='failed-executions']")
	public static WebElement FailedExecutions;

	@FindBy(xpath = "//div[@class='running-executions']")
	public static WebElement RunningExecutions;

	@FindBy(xpath = "//div[@class='pending-executions']")
	public static WebElement PendingExecutions;

	@FindBy(xpath = "//span[contains(text(),'Select projects ')]")
	public static WebElement Selectprojects  ;

	@FindBy(xpath = "//span[contains(text(),'Select execution status ')]")
	public static WebElement Selectexecutionstatus ;

	@FindBy(xpath = "//span[contains(text(),'Select Workflows ')]")
	public static WebElement SelectWorkflows   ;

	@FindBy(xpath="//input[@placeholder='Search execution status']")
	public static WebElement actionsearchfield;

	@FindBy(xpath="//input[@placeholder='Search projects']")
	public static WebElement actionsearchfield1;

	@FindBy(xpath="//label[text()='VarshithaUIAutomation']")
	public static WebElement selectprojectdropdown;

	@FindBy(xpath="//input[@placeholder='Search Workflows']")
	public static WebElement actionsearchfield2;

	@FindBy(xpath="//label[text()='RestartFail']")
	public static WebElement selectworkflowdropdown;

	@FindBy(xpath="//label[text()='Failed']")
	public static WebElement SelectFailed;

	@FindBy(xpath="//label[text()='VarshithaUIAutomation']")
	public static WebElement SelectProject;

	@FindBy(xpath="//label[text()='RestartFail']")
	public static WebElement Selectworkflow;

	@FindBy(xpath="//button[text()='Apply']")
	public static WebElement SelectApply;

	@FindBy(xpath = "//span[@class='inner-text resubmit']")
	public static List<WebElement> ClickRestart;

	@FindBy(xpath = "//span//label[@class='new-checkbox-label']")
	public static WebElement Restartcheckbox;

	//Create method 
	public static List<WebElement> getindividualcheckbox(String workflowname, WebDriver driver)
	{
		List<WebElement> Element = driver.findElements(By.xpath("//a[text()='"+workflowname+"']/preceding-sibling::div/label"));
		return Element;
	}

	//button[text()='Restart']
	@FindBy(xpath = "//button[text()='Restart']")
	public static WebElement Restartbutton;

	@FindBy(xpath = "//button[text()='Close']")
	public static WebElement Closebutton;

	@FindBy(xpath = "//button[text()='OK']")
	public static WebElement OKbutton;

	@FindBy(xpath = "//span[@title= 'Refresh']")
	public static WebElement Refreshbutton;

	public static String Executions = "//div//div[@class='monitor-workflow-title monitor-title']";

	@FindBy(xpath = "//div[@class='notification-message'][contains(text(),'Your request is submitted.')]")
	public static WebElement notificationMessage1;

	@FindBy(xpath = "//div[@class='notification-message'][contains(text(),'Your request to resubmit bulk executions has been processed successfully. Kindly refresh to get the latest executions summary.')]")
	public static WebElement notificationMessage2;

	//public static String notificationMessage1 = "//div[@class='notification-message'][contains(text(),'Your request is submitted.')]";

	//public static String notificationMessage2 = "//div[@class='notification-message'][contains(text(),'Your request to resubmit bulk executions has been processed successfully. Kindly refresh to get the latest executions summary.')]";

	@FindBy(xpath = "//a[text()='RestartFail']/ancestor::span[contains(@class,'inner-detail-content resubmit')]/following-sibling::span/span[@title='failed']/following-sibling::span[@class='restart-tag'][contains(text(),'Restarted')]")
	public static WebElement Restartlable;

	@FindBy(xpath = "//a[text()='RestartFail']/ancestor::span[contains(@class,'resubmit')]/following-sibling::span/span[@title='failed']/ancestor::span/following-sibling::span/i[contains(@class,'refresh')]/following-sibling::i[contains(@title,'Resume')]")
	public static WebElement RestartandResumebutton;

}
