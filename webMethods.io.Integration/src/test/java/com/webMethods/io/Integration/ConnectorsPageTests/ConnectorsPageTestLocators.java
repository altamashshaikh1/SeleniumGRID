package com.webMethods.io.Integration.ConnectorsPageTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConnectorsPageTestLocators 
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

	@FindBy(xpath = "//span[text()='Import']")
	public static WebElement importButton;

	@FindBy(xpath = "//span[text()='Connectors ']")
	public static WebElement connectorsLinkTab;

	@FindBy(xpath = "//span[text()='1 Account configured']")
	public static WebElement addedTrelloAuth;

	@FindBy(linkText = "REST")
	public static WebElement restConnectorLink;

	@FindBy(xpath = "//div[@class='row connector-list-container']")
	public static WebElement restConnector;

	@FindBy(linkText = "SOAP")
	public static WebElement soapConnectorLink;

	@FindBy(xpath = "//*[text()='No connector(s) created yet!']")
	public static WebElement soapConnector;

	@FindBy(linkText = "On-premises")
	public static WebElement onPremisesConnectorLink;

	@FindBy(xpath = "//span[text()='Automation_APIAPP_DONOTDelete']")
	public static WebElement onPremisesConnector;

	@FindBy(linkText = "Flat file")
	public static WebElement flatFileConnectorLink;

	@FindBy(xpath = "//*[text()='No connector(s) created yet!']")
	public static WebElement flatFileConnector;
}