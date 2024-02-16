package com.webMethods.io.Integration.SupportLinkTrialTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupportLinkTrialTestLocators 
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

	@FindBy(id = "my-cloud")
	public static WebElement appSwticher;

	@FindBy(id = "help")
	public static WebElement helpIconclick;

	@FindBy(xpath ="//i[@class='delite-icon dlt-icon-help']")
	public static WebElement helpIconVisible;		

	@FindBy(xpath = "//span[text()='Support']")
	public static WebElement supportString;

	@FindBy(xpath = "//a[@href=\"https://empower.softwareag.com\"]")
	public static WebElement supportLinkPaid;

	@FindBy(xpath = "//a[@href=\"https://tech.forums.softwareag.com/tag/webMethods-io-Integration\"]")
	public static WebElement supportLinkTrial;
}