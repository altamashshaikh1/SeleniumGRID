package com.webMethods.io.Integration.RecipesTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class Recipes 
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static SoftAssertWrapper softAssert;

	@BeforeTest
	public void startRemoteWebDriverSession()
	{
		//Set desired capabilities
		capabilities = ThreadLocalDriverFactory.getCapabilities();

		//Initiate ThreadSafe Session
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal, BaseTestUtils.getGridURL(), capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"Recipes"},description = "Login user : Recipes")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.RecipesTests.RecipesTestLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Recipes"},description = "Verify recipe page")
	public void visitRecipesPage()
	{
		//Visit recipes page
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.projectSearchTextBox,PropertiesData.readInputData("recipesDefaultName"),webDriver,"Input project name");       
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.defaultProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.loaderString,webDriver,"Wait for 1st loader to dissappears");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.loaderString,webDriver,"Wait for 2nd loader to dissappears");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.recipesLinkText,webDriver,"Click on Recipes link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.allRecipes,webDriver,"Wait till all recipes displayed (Grid view)");
	}

	@Test(priority = 2,dependsOnMethods = {"visitRecipesPage"},groups = {"Recipes"},description = "Recipes grid view (workflows)")
	public void allRecipesListGridView() 
	{
		//Test Steps
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.allRecipes,webDriver,"Assert all recipes are shown (Grid view)");
	}

	@Test(priority = 3,dependsOnMethods = {"allRecipesListGridView"},groups = {"Recipes"},description = "Recipes list view (workflows)")
	public void allRecipesListListView()
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.recipesListViewIcon,webDriver,"Click on List icon to get all recipes in list view");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.allListViewRecipe,webDriver,"Wait till all recipes displayed (List view)");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.allListViewRecipe,webDriver,"Assert all recipes are shown (List view)");
	}

	@Test(priority = 4,dependsOnMethods = {"allRecipesListListView"},groups = {"Recipes"},description = "My recipes")
	public void myRecipes() 
	{
		//Test Steps
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.recipesFilterIcon,webDriver,"Click on filter icon");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.RecipesTests.RecipesTestLocators.myRecipesOption,webDriver,"Select My recipes option");
	}

	@AfterMethod
	public void testStatusCapture(ITestResult result)
	{
		//Continue dependsOnMethod on soft assertion failures
		if (softAssert.shouldContinueOnFailure() || !result.isSuccess())
		{
			softAssert.assertAll();
		}

		//Collect test status and record as logs
		switch (result.getStatus()) 
		{
		case ITestResult.SUCCESS:
			LoggerUtil.INFO(result.getMethod().getMethodName()+" SUCCESS");
			break;

		case ITestResult.FAILURE:
			LoggerUtil.ERROR(result.getMethod().getMethodName()+" FAILURE. " + result.getThrowable());
			break;

		case ITestResult.SKIP:
			LoggerUtil.DEBUG(result.getMethod().getMethodName()+" SKIP.");
			break;

		default:
			LoggerUtil.DEBUG(result.getMethod().getMethodName()+" Status UNKNOWN.");
			break;
		}
	}

	@AfterTest
	public void endRemoteWebDriverSession()
	{
		ThreadLocalDriverFactory.closeWebBrowser(webDriver);
		ThreadLocalDriverFactory.quitDriver(webDriver);
		ThreadLocalDriverFactory.closeThreadSafeSession(webDriverThreadLocal);
	}
}