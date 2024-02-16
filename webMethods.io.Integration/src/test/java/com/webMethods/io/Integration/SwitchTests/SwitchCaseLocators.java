package com.webMethods.io.Integration.SwitchTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwitchCaseLocators
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

	@FindBy(xpath ="//input[@placeholder='Search']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='VarshithaUIAutomation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//span[@title='SwitchConfiguration']")
	public static WebElement myUIautomationproject2;

	@FindBy(xpath="//span[contains(text(),'SwitchConfiguration')]")
	public static WebElement SwitchConfiguration;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath="//span[contains(text(),'AssetValidation')]")
	public static WebElement AssetValidation;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath="//span[@data-eventmap='canvas-connectors-settings-icon']")
	public static WebElement setting;

	@FindBy(xpath="//div[starts-with(@id,'jsPlumb_')]")
	public static WebElement Label;

	@FindBy(xpath="//div[contains(text(),'Default')]")
	public static WebElement defaultselect;

	@FindBy(xpath="//div[@class='select2-common__indicator select2-common__dropdown-indicator css-tlfecz-indicatorContainer']")
	public static WebElement Dropdown;

	@FindBy(xpath="//div[text()='Case 1']")
	public static WebElement Case1select;

	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement Done;







}
