package com.webMethods.io.Integration.MyRecipesPreviewTests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyRecipesPreviewLocators 
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

	@FindBy(xpath = "//span[@class='icons8-cross-icon-blue free-tier-close-icon']")
	public static WebElement closeIconFreeForeverStrip;

	@FindBy(linkText = "Recipes")
	public static WebElement recipesLink;

	@FindBy(className = "recipe-card")
	public static List<WebElement> allRecipesCardClass;

	@FindBy(xpath = "//a[@class='dropdown-button action-menu']")
	public static WebElement recipeFilterButton;

	@FindBy(xpath = "//div[@class='radio-btn-new undefined']//input[@id='myrecipes-filter-radio']")
	public static WebElement myRecipesOption;

	@FindBy(xpath = "//div[@title='MyRecipesPreviewBackLogTestData']")
	public static WebElement myRecipeFlowCard;

	@FindBy(xpath = "//span[@data-activates='recipe-card-menu-workflow_rec_0']")
	public static WebElement myRecipesEllipsis;

	@FindBy(xpath = "//ul[@id='recipe-card-menu-workflow_rec_0']")
	public static WebElement myRecipesEllipsisMenu;

	@FindBy(xpath = "//ul[@id='recipe-card-menu-workflow_rec_0']//span[@aid='preview']")
	public static WebElement myRecipesPreviewOption;

	@FindBy(xpath = "//input[contains(@placeholder,'Search')]")
	public static WebElement searchTextBox;

	@FindBy(xpath = "//div[@class='recipe-view']//a[text()='Preview']")
	public static WebElement recipePreviewLink;

	@FindBy(xpath = "//button[@class='btn primary-btn small use-recipe-button']")
	public static WebElement useRecipeButton;

	@FindBy(xpath = "//i[@class='dlt-icon-dropdown']")
	public static WebElement recipeCanvasDropDown;

	@FindBy(xpath = "//div[@class='connectors-container']")
	public static WebElement connectorsDetailsCanvas;

	@FindBy(xpath = "//div[@class='details-link']")
	public static WebElement detailsLink;

	@FindBy(xpath = "//div[text()='Recipe details']")
	public static WebElement detailsModal;

	@FindBy(xpath = "//div[@class='recipe-details-header']")
	public static WebElement recipeDetailsHeader;

	@FindBy(xpath = "//div[@class='primary-details-container row']")
	public static WebElement primaryDetails;

	@FindBy(xpath = "//div[@class='description-container row']")
	public static WebElement descriptionContainer;

	@FindBy(xpath = "//div[@class='references-container']")
	public static WebElement referencesContainer;

	@FindBy(xpath = "//button[@class='btn tertiary-btn small use-recipe-button']")
	public static WebElement useRecipeLinkDetailsModal;

	@FindBy(xpath = "//div[@class='modal-dialog']")
	public static WebElement importModal;

	@FindBy(xpath = "//button[@class='modal-action modal-close btn btn-link btn-sm']")
	public static WebElement importModalCancelButton;

	@FindBy(xpath = "//div[@class='delite-icon dlt-icon-chevron-right']")
	public static WebElement referenceArrowButton;

	@FindBy(xpath = "//div[@class='Reference-list-table']")
	public static WebElement referenceTable;

	@FindBy(xpath = "//div[@class='delite-icon dlt-icon-chevron-down']")
	public static WebElement referenceDownArrowButton;

	@FindBy(xpath = "//span[@class='dlt-icon-close recipe-details-close-icon']")
	public static WebElement detailsCloseButton;

	@FindBy(id = "start")
	public static WebElement startTriggerAction;

	@FindBy(xpath = "//button[@class='btn primary-btn small use-recipe-button']")
	public static WebElement useRecipeCanvas;

	@FindBy(id = "a0")
	public static WebElement loggerAction;

	@FindBy(xpath = "//div[@id='a0']//span[@title='Settings']")
	public static WebElement loggerActionSettings;

	@FindBy(id = "activity-settings")
	public static WebElement loggerActionConfigurationModal;

	@FindBy(xpath = "//button[@class='btn btn-link btn-sm']")
	public static WebElement loggerConfigModalClose;

	@FindBy(xpath = "//span[@class='composer-activity-icon btns copy-btn icons8-copy view-mode version-mode']")
	public static WebElement loggerCopyDisabledIcon;

	@FindBy(xpath = "//span[@class='composer-activity-icon btns copy-btn view-mode version-mode']")
	public static WebElement loggerMoreOptionDisabledIcon;

	@FindBy(id = "stop")
	public static WebElement stopAction;

	@FindBy(xpath = "//button[@id='debug-panel-icon'][@disabled='']")
	public static WebElement disabledDebuggerIcon;

	@FindBy(xpath = "//button[@title='Notes'][@disabled='']")
	public static WebElement disabledWorkflowNotesIcon;

	@FindBy(xpath = "//button[@title='Version history'][@disabled='']")
	public static WebElement disabledWorkflowVersioningIcon;

	@FindBy(xpath = "//button[@title='Keyboard shortcuts'][@disabled='']")
	public static WebElement disabledWorkflowKeyboardShorcutIcon;

	@FindBy(xpath = "//a[@class='btn btn-link nova-regular dropdown-button  disabled-click  with-auto']")
	public static WebElement disabledTakeTourIcon;

	@FindBy(xpath = "//i[@class='dlt-icon-arrow-left']")
	public static WebElement backArrowButton;

	@FindBy(xpath = "//span[@class='composer-activity-icon btns copy-btn icons8-copy view-mode version-mode']")
	public static WebElement trelloCopyDisabledIcon;

	@FindBy(xpath = "//span[@class='composer-activity-icon btns copy-btn view-mode version-mode']")
	public static WebElement trelloMoreOptionDisabledIcon;
}