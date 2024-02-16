package com.webMethods.io.Integration.AssetsDependenciesTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssetDependencyLocators 
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

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

	public static String login_block_1 = "//div[@id='kc-content']";

	@FindBy(xpath ="//input[@placeholder='Search']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='VarshithaUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//span[@title='JMS_Automation']")
	public static WebElement myUIautomationproject2;

	@FindBy(xpath = "//div[text()='Info: A new deployment is available. Do you want to update it?']")
	public static WebElement Info;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath="//span[contains(text(),'AssetValidation')]")
	public static WebElement AssetValidation;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath = "//i[@class=' dlt-icon-more-menu']")
	public static WebElement elopsis1;

	@FindBy(xpath = "//span[text()='Delete']")
	public static WebElement deleteworkflow;

	@FindBy(xpath = "//table//th[contains(text(),'Asset Name')]")
	public static WebElement TableR1;

	@FindBy(xpath = "//table//th[contains(text(),'Asset Type')]")
	public static WebElement TableR2;

	@FindBy(xpath = "(//td[contains(text(),'Assetsubscriber')])")
	public static WebElement TableR3;

	@FindBy(xpath = "(//tbody//td[contains(text(),'Subscriber')])")
	public static WebElement TableR4;
}