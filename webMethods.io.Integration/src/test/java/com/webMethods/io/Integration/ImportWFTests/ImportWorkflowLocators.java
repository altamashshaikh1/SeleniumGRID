package com.webMethods.io.Integration.ImportWFTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportWorkflowLocators 
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

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block_1;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement projectSearchBox;

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement searchedProject;

	@FindBy(xpath = "//button[@class='btn secondary-btn btn-sm btn-import']")
	public static WebElement recipesInputButton;

	@FindBy(xpath = "//input[@id='myRecipiesfileInput']")
	public static WebElement recipesInputBox;

	@FindBy(xpath = "//div[@class='import-title']")
	public static WebElement importModalTitle;

	@FindBy(xpath = "//a[@class='add-icon']")
	public static WebElement addAccount;

	@FindBy(xpath = "//input[@aid='Username']")
	public static WebElement userName;

	@FindBy(xpath = "//input[@aid='password']")
	public static WebElement userPassword;

	@FindBy(xpath = "//button[text()='Add']")
	public static WebElement addButton;

	@FindBy(xpath = "//*[text()='Account saved successfully.']")
	public static WebElement accountSavedMessage;

	@FindBy(xpath = "//button[text()='Import']")
	public static WebElement importButton;

	@FindBy(xpath = "//*[text()='Workflow imported successfully']")
	public static WebElement workflowImportMessage;

	@FindBy(xpath = "//div[contains(@title,'W1')]")
	public static WebElement workflowW1;

	@FindBy(xpath = "//div[contains(@title,'W2')]")
	public static WebElement workflowW2;
}