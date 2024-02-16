package com.webMethods.io.Integration.CustomNodePublishDeployTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PublishDeployProjectLocators 
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

	@FindBy(xpath = "//span[@title='Backlog NodeJS CLI']")
	public static WebElement searchedProject;

	@FindBy(xpath = "//a[@name='Backlog NodeJS CLI-more-icon']")
	public static WebElement projectEllipsis;

	@FindBy(xpath = "//span[@title='Publish Project']")
	public static WebElement publishProjectOption;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement publishNextButton;

	@FindBy(xpath = "//div[@class='inline-message']")
	public static WebElement cliConnectorName;

	@FindBy(xpath = "//input[@placeholder='Name']")
	public static WebElement publishName;

	@FindBy(xpath = "//button[normalize-space()='Publish']")
	public static WebElement publishButton;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(css = "a[title='Add Project']")
	public static WebElement addNewProjectButton;

	@FindBy(xpath = "//span[text()='PublishProjectTest']")
	public static WebElement projectDeployedName;

	@FindBy(xpath = "//span[@title='Backlog NodeJS CLI']")
	public static WebElement deployedProjectName;

	@FindBy(xpath = "//div[@data-testid='asset-skip']")
	public static WebElement assetModal;

	@FindBy(xpath = "//button[text()='Save and continue']")
	public static WebElement saveAndContinueButton;

	@FindBy(xpath = "//span[text()='add']")
	public static WebElement addCLIAccount;

	@FindBy(xpath = "//input[@aid='Username']")
	public static WebElement accountUserName;

	@FindBy(xpath = "//input[contains(@type,'password')]")
	public static WebElement accountUserPassword;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	public static WebElement addAccountButton;

	@FindBy(xpath = "//*[text()='Account saved successfully.']")
	public static WebElement accountSavedMessage;

	@FindBy(xpath = "//button[text()='Deploy']")
	public static WebElement publishDeployButton;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement deployMessage;

	@FindBy(xpath = "//span[@class='section-wrapper-title']")
	public static WebElement titleName;

	@FindBy(xpath = "//span[text()='BacklogNodejsCLI']")
	public static WebElement accountModal;

	@FindBy(xpath = "//span[text()='No data']")
	public static WebElement triggerModal;
}