package com.webMethods.io.Integration.ImportWFDependentTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportWorkflowDependentTestLocators 
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

	@FindBy(xpath = "//span[text()='AssetDependentTest']")
	public static WebElement cloneProjectName;

	@FindBy(xpath = "//span[text()='Workflow1']")
	public static WebElement workflowWithDependentData;

	@FindBy(xpath = "//a[@data-activates='1']")
	public static WebElement workflowEllipsis;

	@FindBy(xpath = "//span[text()='Clone']")
	public static WebElement workflowClone;

	@FindBy(xpath = "//input[@aid='project_selection']")
	public static WebElement cloneInputBox;

	@FindBy(xpath = "//div[@title='Default']")
	public static WebElement defaultProjectName;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement cloneButton;

	@FindBy(xpath = "//*[text()='Workflow cloned successfully.']")
	public static WebElement workflowCloneMessage;

	@FindBy(linkText = "Projects")
	public static WebElement projectLink;

	@FindBy(xpath = "//span[text()='Copy of Workflow1']")
	public static WebElement copiedWorkflow;

	@FindBy(linkText = "FlowServices")
	public static WebElement flowServicesLInk;

	@FindBy(xpath = "//span[text()='getDate']")
	public static WebElement copiedFlowServices;

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement defaultProject;

	@FindBy(xpath = "//span[@class='import-title']")
	public static WebElement inputBox;

	@FindBy(id = "myRecipiesfileInput")
	public static WebElement uploadFileTextBox;

	@FindBy(xpath = "//button[@class='btn secondary-btn btn-sm btn-import']")
	public static WebElement importInputBox;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm right btn-submit']")
	public static WebElement importButtonToProject;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addProjectButton;

	@FindBy(id = "myRecipiesfileInput")
	public static WebElement importButtonBox;

	@FindBy(xpath = "//button[text()='Import']")
	public static WebElement importButton;

	@FindBy(xpath = "//span[text()='Workflow1']")
	public static WebElement uploadedWorkflow; 
}