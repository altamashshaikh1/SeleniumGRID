package com.webMethods.io.Integration.AllRecipesPreviewTests;

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
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumKeyBoardActionUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class AllRecipesPreview
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

	@Test(priority = 0,groups = {"Preview-All Recipes"},description = "Verify user login")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Preview-All Recipes"},description = "Verify Preview option in recipe workflow card")
	public void verifyPreviewOptionWorkflowCard() 
	{
		String responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD,
				"Login user to get plan details");
		String responseBodyTwo = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user", 
				HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken"),
				HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie"),
				HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf"),
				"Get user info to get plan details");
		if(responseBodyTwo.contains("FFE")==true)
		{
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.closeIconFreeForeverStrip, webDriver, "Close FFE strips on header");
		}
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.recipesLink, webDriver,"Click on recipes link");
		SeleniumWaitUtils.waitForElementsVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.allRecipesCardClass, webDriver, "Wait for all recipes card to be visible under all recipes");
		SeleniumWebElementsUtils.elementsvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.allRecipesCardClass, webDriver, "Verify all recipes card visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.searchTextBox, webDriver,"Click on search text box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.searchTextBox, "Create card in Trello for new message posted in Slack starting with a particular word", webDriver, "Search recipe from all recipes");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press Enter to search recipe");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.recipePreviewLink, webDriver, "Wait for preview link to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.recipePreviewLink, webDriver,"Click on preview link");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.useRecipeButton, webDriver, "Wait for recipe canvas preview to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.useRecipeButton, webDriver, "Verify recipe canvas preview");
	}

	@Test(priority = 2,dependsOnMethods = {"verifyPreviewOptionWorkflowCard"},groups = {"Preview-All Recipes"},description = "Verify Drop down on canvas header on preview")
	public void verifyDropdownCanvas() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.recipeCanvasDropDown, webDriver,"Click on dropdown in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.connectorsDetailsCanvas, webDriver, "Verify connector details for recipe canvas in preview mode");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.recipeCanvasDropDown, webDriver,"Click on dropdown in preview mode");
	}

	@Test(priority = 3,dependsOnMethods = {"verifyDropdownCanvas"},groups = {"Preview-All Recipes"},description = "Verify details of recipe workflow on preview mode")
	public void detailsOptionRecipesCanvas() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.detailsLink, webDriver,"Click on details link in preview mode");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.detailsModal, webDriver, "Wait for recipe details modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.recipeDetailsHeader, webDriver, "Verify recipe details header for recipe canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.primaryDetails, webDriver, "Verify primary details for recipe canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.descriptionContainer, webDriver, "Verify description container for recipe canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.referencesContainer, webDriver, "Verify references container for recipe canvas in preview mode");
	}

	@Test(priority = 4,dependsOnMethods = {"detailsOptionRecipesCanvas"},groups = {"Preview-All Recipes"},description = "Verify use recipe button for import modal in details modal")
	public void useRecipeInDetailsModal() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.useRecipeLinkDetailsModal, webDriver,"Click on user recipe button from recipe details modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.importModal, webDriver, "Wait for import modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.importModal, webDriver, "Verify recipe import modal visible from recipe detail modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.importModalCancelButton, webDriver,"Click on cancel button to close import modal");
	}

	@Test(priority = 5,dependsOnMethods = {"useRecipeInDetailsModal"},groups = {"Preview-All Recipes"},description = "Verify reference in details recipe modal")
	public void referenceDetailsModal() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.referenceArrowButton, webDriver,"Click on arrow button in reference modal to check the connectors used in recipes");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.referenceTable, webDriver, "Verify all connectors visible in reference table");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.referenceDownArrowButton, webDriver,"Click on down arrow button in reference modal to close reference modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.detailsCloseButton, webDriver,"Close details modal");
	}

	@Test(priority = 6,dependsOnMethods = {"referenceDetailsModal"},groups = {"Preview-All Recipes"},description = "Verify Use recipe button from canvas page in preview mode")
	public void useRecipeButtonCanvas() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.startTriggerAction, webDriver, "Wait for recipe preview canvas to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.useRecipeCanvas, webDriver,"Click on use recipe button to open import recipes modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.importModal, webDriver, "Wait for import modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.importModal, webDriver, "Verify recipe import modal visible from recipe detail modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.importModalCancelButton, webDriver,"Click on cancel button to close import modal");
	}

	@Test(priority = 7,dependsOnMethods = {"useRecipeButtonCanvas"},groups = {"Preview-All Recipes"},description = "Verify actions/connectors and triggers applied in recipes workflow in preview mode")
	public void actionTriggersCanvas() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloAction, webDriver, "Wait for canvas modal in preview mode to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.startTriggerAction, webDriver, "Verify start trigger action to be visible on canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloAction, webDriver, "Verify trello action to be visible on canvas in preview mode");
	}

	@Test(priority = 8,dependsOnMethods = {"actionTriggersCanvas"},groups = {"Preview-All Recipes"},description = "Verify trello action config modal")
	public void actionConfigurationModalCanvas() 
	{
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloAction, webDriver, "Mouse hover on trello action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloActionSettings, webDriver,"Click on trello settings icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloActionConfigurationModal, webDriver, "Wait trello config modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloActionConfigurationModal, webDriver, "Verify trello config modal visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloConfigModalClose, webDriver,"Close config modal");
	}

	@Test(priority = 9,dependsOnMethods = {"actionConfigurationModalCanvas"},groups = {"Preview-All Recipes"},description = "Verify action/connectors options")
	public void actionConfigurationOption() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloAction, webDriver, "Wait for canvas modal in preview mode to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloAction, webDriver, "Mouse hover on trello action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloCopyDisabledIcon, webDriver, "Verify copy option is disabled in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloMoreOptionDisabledIcon, webDriver, "Verify more option is disabled in preview mode");
	}

	@Test(priority = 10,dependsOnMethods = {"actionConfigurationOption"},groups = {"Preview-All Recipes"},description = "Verify stop action configuration")
	public void stopActionConfigurationModal() 
	{
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.stopAction, webDriver, "Double click on stop action to open configuration modal for stop action");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloActionConfigurationModal, webDriver, "Wait for stop action config modal in preview mode to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloActionConfigurationModal, webDriver, "Verify stop action configuration modal to be visible in preview mode");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloConfigModalClose, webDriver,"Close config modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.trelloAction, webDriver, "Wait for canvas modal in preview mode to be visible");	
	}

	@Test(priority = 11,dependsOnMethods = {"stopActionConfigurationModal"},groups = {"Preview-All Recipes"},description = "Verify debugger option is disabled in preview mode")
	public void debuggerOptionDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.disabledDebuggerIcon, webDriver, "Verify debug icon disabled in preview mode for all recipes");
	}

	@Test(priority = 12,dependsOnMethods = {"debuggerOptionDisabled"},groups = {"Preview-All Recipes"},description = "Verify workflow notes is disabled in preview mode")
	public void workflowNotesDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.disabledWorkflowNotesIcon, webDriver, "Verify workflow notes icon disabled in preview mode for all recipes");
	}

	@Test(priority = 13,dependsOnMethods = {"workflowNotesDisabled"},groups = {"Preview-All Recipes"},description = "Verify workflow versioning is disabled in preview mode")
	public void workflowVersioningDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.disabledWorkflowVersioningIcon, webDriver, "Verify workflow versioning icon disabled in preview mode for all recipes");
	}

	@Test(priority = 14,dependsOnMethods = {"workflowVersioningDisabled"},groups = {"Preview-All Recipes"},description = "Verify keyboard shortcut icon is disabled in preview mode")
	public void keyBoardShortCutIconDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.disabledWorkflowKeyboardShorcutIcon, webDriver, "Verify workflow keyboard shortcut icon disabled in preview mode for all recipes");
	}

	@Test(priority = 15,dependsOnMethods = {"keyBoardShortCutIconDisabled"},groups = {"Preview-All Recipes"},description = "Verify take a tour icon is disabled in preview mode")
	public void takeTourOptionDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.disabledTakeTourIcon, webDriver, "Verify take tour icon disabled in preview mode for all recipes");
	}

	@Test(priority = 16,dependsOnMethods = {"takeTourOptionDisabled"},groups = {"Preview-All Recipes"},description = "Verify back button ")
	public void backButtonRecipeCanvas() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.AllRecipesPreviewTests.AllRecipesPreviewLocators.backArrowButton, webDriver,"Click on back button to visit recipes dashboard");
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