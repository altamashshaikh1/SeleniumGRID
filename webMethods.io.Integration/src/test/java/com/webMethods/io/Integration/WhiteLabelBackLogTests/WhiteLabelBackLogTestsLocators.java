package com.webMethods.io.Integration.WhiteLabelBackLogTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WhiteLabelBackLogTestsLocators 
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

	@FindBy(css = ".delite-icon.dlt-icon-profile")
	public static WebElement profileButton;

	@FindBy(linkText = "Settings")
	public static WebElement settingsProfile;

	@FindBy(xpath = "//span[normalize-space()='White label']")
	public static WebElement whitelabelOption;

	@FindBy(xpath = "//a[@title='Edit theme']")
	public static WebElement whitelabelEditOption;

	@FindBy(xpath = "//span[normalize-space()=': Please enter the valid color code']")
	public static WebElement errorMessage;

	@FindBy(xpath = "//a[normalize-space()='Projects']")
	public static WebElement projectLinks;

	@FindBy(xpath = "(//a[@title='Preview theme'])[1]")
	public static WebElement themeEyeIcon;

	@FindBy(xpath = "//a[normalize-space()='Yes']")
	public static WebElement applyThemeYesButton;

	@FindBy(xpath = "//span[normalize-space()='Current']")
	public static WebElement currentButtonElement;
}