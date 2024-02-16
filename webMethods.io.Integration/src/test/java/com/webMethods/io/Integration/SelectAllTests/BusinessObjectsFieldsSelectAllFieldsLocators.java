package com.webMethods.io.Integration.SelectAllTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BusinessObjectsFieldsSelectAllFieldsLocators
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

	@FindBy(xpath = "//span[@title='AS2TestData']")
	public static WebElement as2ProjectName;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflow;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanelString;

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	@FindBy(xpath = "//span[@title='Coupa']")
	public static WebElement actionSearched;

	@FindBy(id="a0")
	public static WebElement CoupaActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(id="a0")
	public static WebElement coupaActionId;

	@FindBy(xpath="//div[text()='Select Version']")
	public static WebElement selectDropDown;

	@FindBy(xpath="//div[text()='Please Select']")
	public static WebElement selectAccountDropDown;

	@FindBy(xpath="//div[text()='Select Value']")
	public static WebElement selectDropDown2;

	@FindBy(xpath="//div[text()='29']")
	public static WebElement versionNumber;

	@FindBy(xpath="//b[text()='Info']")
	public static WebElement infoMessageString;

	@FindBy(xpath="//a[@title='Add  Custom Action']")
	public static WebElement addNewCustomOperationButton;

	@FindBy(xpath="//a[@class='add-icon']")
	public static WebElement addNewCustomOperationButtonString;

	@FindBy(xpath="//div[text()='Coupa_1']")
	public static WebElement accountName;

	@FindBy(xpath="//input[@aid='Name']")
	public static WebElement customAccountName;

	@FindBy(xpath="//div[text()='Reference Data']")
	public static WebElement referenceDataField;

	@FindBy(xpath="//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath="//span[text()='Select the Operation']")
	public static WebElement operationModalString;

	@FindBy(xpath="//span[text()='Create']")
	public static WebElement createOpeartionOption;

	@FindBy(xpath="//button[text()='Headers']")
	public static WebElement headersString;

	@FindBy(xpath="//span[text()='Select the Business Object']")
	public static WebElement businessObjectModalString;

	@FindBy(xpath="//span[text()='account_validation_rules']")
	public static WebElement businessOperationName;

	@FindBy(xpath="//span[text()='Select the Data Fields']")
	public static WebElement dataModalString;

	@FindBy(xpath="//span[text()='Fields']")
	public static WebElement checkBox;
}