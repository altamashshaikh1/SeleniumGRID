package com.webMethods.io.Integration.ConnConfigurationTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConnectorsConfigurationsTestLocators 
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

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='Default']")
	public static WebElement defaultProject;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflow;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitesPanel;

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement actionSearched;

	@FindBy(xpath = "//div[@id='a0']")
	public static WebElement loggerAction;

	@FindBy(xpath = "//span[@title='click for more options']")
	public static WebElement moreOptions;

	@FindBy(css = "span[title='Enable'] label[for='disabled-actions']")
	public static WebElement disableConnector;

	@FindBy(css = "span[title='Disable'] label[for='disabled-actions']")
	public static WebElement enableConnector;

	@FindBy(xpath = "//span[@title='Copy']")
	public static WebElement copyConnector;

	@FindBy(xpath = "//div[@id='a1']")
	public static WebElement copiedLoggerAction;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement loggerNextButton;

	@FindBy(xpath = "//span[@aid='Transform']//span[@class='activity-arrow-icon left']")
	public static WebElement transFormArrowButton;

	@FindBy(xpath = "//span[text()='Add New']")
	public static WebElement transFormAddButton;

	@FindBy(xpath = "//input[@aid='Transform Name']")
	public static WebElement transformInputBox;

	@FindBy(xpath = "//div[text()='Select...']")
	public static WebElement transformInputSelect2;

	@FindBy(xpath = "//*[text()='Get Current Date String']")
	public static WebElement searchedFunction;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement transFormDoneButton;

	@FindBy(xpath = "//span[@title='t1 : newFunction']")
	public static WebElement newFunction;
}