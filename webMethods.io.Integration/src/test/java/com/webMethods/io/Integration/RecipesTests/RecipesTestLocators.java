package com.webMethods.io.Integration.RecipesTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecipesTestLocators
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
}