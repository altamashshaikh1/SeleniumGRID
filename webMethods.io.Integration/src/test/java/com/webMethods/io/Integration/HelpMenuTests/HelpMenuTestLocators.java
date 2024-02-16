package com.webMethods.io.Integration.HelpMenuTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpMenuTestLocators
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
	public static WebElement helpIcon;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loaderString;

	@FindBy(xpath = "//span[text()='About']")
	public static WebElement aboutLink;

	@FindBy(xpath = "//div[@class='about-inner-content']")
	public static WebElement aboutContent;

	@FindBy(xpath = "//div[@class='version-information']//div//a[@target='_blank']")
	public static WebElement documentsLinkonVersion;    

	@FindBy(xpath = "//span[text()='Getting started']")
	public static WebElement gettingStarted;

	@FindBy(xpath = "//div[@class='welcome-login']")
	public static WebElement gettingStartedContent;

	@FindBy(xpath = "//div[@class='vex-close aptr-engagement-close-btn px-close-button']")
	public static WebElement closeGettingStarted;

	@FindBy(xpath = "//span[text()='Product updates']")
	public static WebElement productUpdates;

	@FindBy(xpath = "//*[starts-with(@class,'title')]")
	public static WebElement productUpdatesContents;

	@FindBy(xpath = "//span[text()='Documentation']")
	public static WebElement documentString;

	@FindBy(xpath = "//a[@data-event='support']")
	public static WebElement documentsLink;	   

	@FindBy(xpath = "//div[@class='vex-overlay']")
	public static WebElement overlayModalProfileString;	   

	@FindBy(xpath = "//i[@class='delite-icon dlt-icon-profile']")
	public static WebElement profileIcon;

	@FindBy(xpath = "//span[text()='Support']")
	public static WebElement supportLink;

	@FindBy(xpath = "//span[text()='Log out']")
	public static WebElement logOutOption;  
}