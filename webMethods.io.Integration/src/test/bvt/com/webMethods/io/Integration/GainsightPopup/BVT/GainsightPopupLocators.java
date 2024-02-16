package com.webMethods.io.Integration.GainsightPopup.BVT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GainsightPopupLocators 
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

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement modalOverlayString;

	@FindBy(id = "help")
	public static WebElement helpIocn;

	@FindBy(xpath = "//span[text()='Getting started']")
	public static WebElement gettingStartedOption;

	@FindBy(xpath = "//div[@class='apt-dialog-content-wrapper']")
	public static WebElement gainSigtModalPopUp;

	@FindBy(xpath = "//div[@class='welcome-wrapper']")
	public static WebElement gainSigtModalPopUpWrapper;

	@FindBy(xpath = "//div[@class='slideshow-container']")
	public static WebElement gainSigtModalSlideContent;

	@FindBy(id = "dot2")
	public static WebElement flowServiceContentSlide;

	@FindBy(id = "dot3")
	public static WebElement connectorsContentSlide;
}