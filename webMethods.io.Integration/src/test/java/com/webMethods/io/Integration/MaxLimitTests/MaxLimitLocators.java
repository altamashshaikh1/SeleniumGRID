package com.webMethods.io.Integration.MaxLimitTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaxLimitLocators 
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

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewButtonString ;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addNewButtonString1 ;

	@FindBy(xpath ="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//span[@title='VarshithaUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath="//span[contains(text(),'MaxLimitTest')]")
	public static WebElement MaxLimitTest;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath="//button[@title= 'Workflow settings']")
	public static WebElement workflowsettings;

	@FindBy(xpath="//span[text()='Execution settings']")
	public static WebElement executionsettings;

	@FindBy(xpath="//input[starts-with(@title,'Retry executing failed actions')]")
	public static WebElement MaxLimitrange;

	@FindBy(xpath="//input[@value='1']")
	public static WebElement MaxLimitvalue1;

	@FindBy(xpath="//input[@value='2']")
	public static WebElement MaxLimitvalue2;

	@FindBy(xpath="//input[@value='3']")
	public static WebElement MaxLimitvalue3;

	@FindBy(xpath="//i[@class='dlt-icon-caret-up number-step-up-icon']")
	public static WebElement Increase;
}