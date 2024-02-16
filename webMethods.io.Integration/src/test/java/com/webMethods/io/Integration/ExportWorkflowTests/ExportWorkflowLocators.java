package com.webMethods.io.Integration.ExportWorkflowTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExportWorkflowLocators 
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

	@FindBy(xpath = "//div[@aid='W2']")
	public static WebElement workflowW2Card;

	@FindBy(xpath = "//a[@data-activates='1']")
	public static WebElement workflowW2CardEllipsis;

	@FindBy(xpath = "//ul[@id='1']//a[contains(@title,'Export Workflow')]")
	public static WebElement publishExportOption;

	@FindBy(xpath = "//div[contains(@class,'project-export-desc')]")
	public static WebElement publishExportDescriptionOption;

	@FindBy(xpath = "//button[text()='Export']")
	public static WebElement exportButton;

	@FindBy(xpath = "//*[text()='Template Export successfully']")
	public static WebElement exportMessage;
}