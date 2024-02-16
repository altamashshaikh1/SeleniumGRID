package com.webMethods.io.Integration.SyncWorkFlowXAccelTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SyncWorkflowXaccelLocators
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

	@FindBy(xpath = "//span[@title='SyncV2_UIAPI']")
	public static WebElement searchedProject;

	@FindBy(xpath = "//div[@title='XaccelWorkFlow']")
	public static WebElement createdWF;

	@FindBy(xpath = "//div[@title='XaccelWorkFlow']']")
	public static WebElement mouseHoverWF;

	@FindBy(xpath = "//div[@title='XaccelWorkFlow']']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF;

	@FindBy(xpath = "//span[normalize-space()='XaccelWorkFlow']']")
	public static WebElement WFPencil;

	@FindBy(xpath = "//span[@class=' webhook icon-48 trigger-icon']")
	public static WebElement WebhookTrigger;

	@FindBy(xpath = "//span[@class='composer-activity-icon btns material-icons settings-icon delite-icon dlt-icon-settings']")
	public static WebElement WebhookSetting;

	@FindBy(xpath = "//span[@class='webhook-url-title']")
	public static WebElement WebhookURL;

	@FindBy(xpath = "//*[@value='https://devrealm1.dev-int-aws-us.webmethods.io/runflow/run/sync/v2/1UFTXZgEtW']")
	public static WebElement Syncv2Url;

	@FindBy(xpath = "//*[contains(text(),'sync/v2')]")
	public static WebElement Syncv2UpdatedURLNote;

	@FindBy(xpath = "//span[@class='dlt-icon-close modal-close']")
	public static WebElement CloseButton;

	@FindBy(xpath = "//i[@class='flow-icons material-icons delite-icon dlt-icon-chevron-left icons8']")
	public static WebElement BackButton;

	@FindBy(xpath = "//span[normalize-space()='Events']")
	public static WebElement EventsPage;

	@FindBy(xpath = "//span[@title='Webhooks']")
	public static WebElement WebhookTabEventsPage;

	@FindBy(xpath = "//span[@data-testid='toggle-button-Webhooks']")
	public static WebElement WebhookToggleEventsPage;

	@FindBy(xpath = "//span[@title='XaccelWorkFlow']")
	public static WebElement CreatedWFInEventsPage;

	@FindBy(xpath = "//span[@class='dlt-icon dlt-icon-info bulb-tooltip sync-info--icon tooltipstered active']")
	public static WebElement WebhookURLInfoTipEventsPage;

	@FindBy(xpath = "//*[contains(text(),'URL is updated from')]")
	public static WebElement URLInfoEventsPage;
}