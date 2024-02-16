package com.webMethods.io.Integration.ContextIDTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextIDLocators
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

	@FindBy(xpath = "//span[@title='ContextID']")
	public static WebElement searchedProject;

	@FindBy(xpath = "//div[@title='WF1']")
	public static WebElement createdWF1;

	@FindBy(xpath = "//div[@title='WF1']")
	public static WebElement mouseHoverWF1;


	@FindBy(xpath = "//div[@title='WF1']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF1;

	@FindBy(xpath = "//span[normalize-space()='WF1']")
	public static WebElement WF1Pencil;

	@FindBy(xpath = "//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement workflowPlayButton;

	@FindBy(xpath = "//*[text()='Workflow testing started.']")
	public static WebElement workflowExecutionStartMessage;

	@FindBy(xpath = "//*[text()='Workflow testing completed.']")
	public static WebElement workflowExecutionCompletedMessage;

	@FindBy(xpath = "//i[@class='flow-icons material-icons delite-icon dlt-icon-chevron-left icons8']")
	public static WebElement BackIcon;

	@FindBy(xpath = "//div[@title='WF2']")
	public static WebElement createdWF2;

	@FindBy(xpath = "//div[@title='WF2']")
	public static WebElement mouseHoverWF2;

	@FindBy(xpath = "//div[@title='WF2']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF2;

	@FindBy(xpath = "//span[normalize-space()='WF2']")
	public static WebElement WF2Pencil;

	@FindBy(xpath = "//div[@title='WF3']")
	public static WebElement createdWF3;

	@FindBy(xpath = "//div[@title='WF3']")
	public static WebElement mouseHoverWF3;

	@FindBy(xpath = "//div[@title='WF3']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF3;

	@FindBy(xpath = "//span[normalize-space()='WF3']")
	public static WebElement WF3Pencil;

	@FindBy(xpath = "//div[@title='WF4']")
	public static WebElement createdWF4;

	@FindBy(xpath = "//div[@title='WF4']")
	public static WebElement mouseHoverWF4;

	@FindBy(xpath = "//div[@title='WF4']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF4;

	@FindBy(xpath = "//span[normalize-space()='WF4']")
	public static WebElement WF4Pencil;

	@FindBy(xpath = "//div[@title='WF5']")
	public static WebElement createdWF5;

	@FindBy(xpath = "//div[@title='WF5']")
	public static WebElement mouseHoverWF5;

	@FindBy(xpath = "//div[@title='WF5']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF5;

	@FindBy(xpath = "//span[normalize-space()='WF5']")
	public static WebElement WF5Pencil;

	@FindBy(xpath = "//div[@title='WF6']")
	public static WebElement createdWF6;

	@FindBy(xpath = "//div[@title='WF6']")
	public static WebElement mouseHoverWF6;

	@FindBy(xpath = "//div[@title='WF6']//span[@class='edit-flow'][normalize-space()='Edit']")
	public static WebElement EditWF6;

	@FindBy(xpath = "//span[normalize-space()='WF6']")
	public static WebElement WF6Pencil;

	@FindBy(xpath = "//a[normalize-space()='Monitor']")
	public static WebElement MonitorPage;

	@FindBy(xpath = "//a[normalize-space()='Workflow execution']")
	public static WebElement WorkflowExecution;

	@FindBy(xpath = "//div[@class='monitor-workflow-title monitor-title']")
	public static WebElement ExecutionsTab;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-settings']")
	public static WebElement SettingsIcon;

	@FindBy(xpath = "//span[normalize-space()='Context id']")
	public static WebElement ContextIDCheckBox;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	public static WebElement Save;

	@FindBy(xpath = "//span[contains(text(),'Context Id')]")
	public static WebElement ContextIDColumn;

	@FindBy(xpath = "//span[@class='filter-toggle-arrows delite-icon dlt-icon-chevron-down']")
	public static WebElement ExpandFilterIcon;

	@FindBy(xpath = "//span[@class='left max-width truncate']")
	public static WebElement FilterTabContextID;

	@FindBy(xpath = "//input[@placeholder='Provide a Context Id']")
	public static WebElement ContextIDSearchBox;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	public static WebElement ApplyButton;

	@FindBy(xpath = "//span[@class='inner-text context-id-container']//span[@title='Context12']")
	public static WebElement ListSearchedContextID;

	@FindBy(xpath = "//a[@title='WF6']")
	public static WebElement WF6HavingContextIDContext12;

	@FindBy(xpath = "//span[contains(@class,'title')][normalize-space()='WF6']")
	public static WebElement WF6ExecutionLogs;

	@FindBy(xpath = "//span[contains(@class,'activity-log-title executed-by')][normalize-space()='Context Id']")
	public static WebElement ContextIDExecutionLogs;

	@FindBy(xpath = "//span[contains(@class,'activity-log-value truncate')][normalize-space()='Context12']")
	public static WebElement WF6ContextIDInExecutionLogs;
}