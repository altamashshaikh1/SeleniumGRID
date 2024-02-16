package com.webMethods.io.Integration.OriginSyncByPass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OriginSyncByPassLocators
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
	public static WebElement loaderString;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='Default']")
	public static WebElement defaultProjectString;

	@FindBy(linkText = "Recipes")
	public static WebElement recipesLinkText;

	@FindBy(xpath = "//div[@class='recipe-list row  recipe-card-list ']")
	public static WebElement allRecipes;

	@FindBy(xpath = "//a[@class='dlt-icon-list ']")
	public static WebElement recipesListViewIcon;

	@FindBy(xpath = "//div[@class='recipe-list row  recipe-table-list ']")
	public static WebElement allListViewRecipe;

	@FindBy(xpath = "//a[@class='dropdown-button action-menu']")
	public static WebElement recipesFilterIcon;

	@FindBy(xpath = "//span[text()='My recipes']")
	public static WebElement myRecipesOption; 

	@FindBy(xpath = "//span[text()='FlowServices']")
	public static WebElement flowServicesLinkText;   

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement projectSearchBox;

	@FindBy(xpath = "//span[@title='DO_NOT_DELETE']")
	public static WebElement searchedProject;

	@FindBy(xpath = "//div[@title='OriginWorkFlow']")
	public static WebElement createdWF;

	@FindBy(xpath = "//div[@title='OriginWorkFlow']")
	public static WebElement mouseHoverWF;

	@FindBy(xpath = "//div[@title='OriginWorkFlow']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF;

	@FindBy(xpath = "//span[normalize-space()='OriginWorkFlow']")
	public static WebElement WFPencil;

	@FindBy(xpath = "//div[@data-eventmap='metadata-clickedActivity- (OriginFlow)']")
	public static WebElement OriginFlowConnector;

	@FindBy(xpath = "//span[@data-eventmap='metadata-activitySettingsIcon- (OriginFlow)']")
	public static WebElement OriginFlowConnectorSetting;

	@FindBy(xpath = "//h1[normalize-space()='OriginFlow']")
	public static WebElement OriginFlowConnectorInfo;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	public static WebElement NextButton;

	@FindBy(xpath = "//span[normalize-space()='Action configure']")
	public static WebElement ActionConfigure;

	@FindBy(xpath = "//h1[contains(text(),'Test this action')]")
	public static WebElement TestThisAction;

	@FindBy(xpath = "//button[contains(text(),'Sync')]")
	public static WebElement SyncButton;

	@FindBy(xpath = "//span[contains(text(),'Successfully synced')]")
	public static WebElement SuccessfullySynced;
}