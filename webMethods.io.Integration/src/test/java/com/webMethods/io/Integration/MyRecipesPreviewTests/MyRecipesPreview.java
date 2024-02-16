package com.webMethods.io.Integration.MyRecipesPreviewTests;

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

public class MyRecipesPreview 
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

	@Test(priority = 0,groups = {"Preview-My Recipes"},description = "Verify user login")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"Preview-My Recipes"},description = "Verify Preview option in recipe workflow card")
	public void verifyPreviewOptionWorkflowCard() 
	{
		String responseBody = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user/token",
				MavenArgumentConstants.SOURCE_TENANT_USERNAME,
				MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD,
				"Login user to get plan details");
		String responseBodyTwo = HttpURLConnHelperFuntions.HTTP_GET(MavenArgumentConstants.SOURCE_TENANT_USERNAME+"/enterprise/v1/user", 
				HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "authtoken"),
				HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "cookie"),
				HttpURLConnHelperFuntions.getJsonKeyValue(responseBody, "csrf"),
				"Get user info to get plan details");
		if(responseBodyTwo.contains("FFE")==true)
		{
			SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.closeIconFreeForeverStrip, webDriver, "Close FFE strips on header");
		}
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.recipesLink, webDriver,"Click on recipes link");
		SeleniumWaitUtils.waitForElementsVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.allRecipesCardClass, webDriver, "Wait for all recipes card to be visible under all recipes");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.recipeFilterButton, webDriver,"Click on recipes filter to select my recipes option");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Tab");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.TAB,"Tab");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.SPACE,"Select My Recipes option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.myRecipeFlowCard, webDriver, "Wait for all recipes card to be visible under my recipes");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.myRecipesEllipsis, webDriver,"Click on My Recipes ellipsis");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.myRecipesEllipsisMenu, webDriver, "Wait for recipes ellipsis menu to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.myRecipesPreviewOption, webDriver,"Click on preview option");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.useRecipeButton, webDriver, "Wait for recipe canvas preview to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.useRecipeButton, webDriver, "Verify recipe canvas preview");
	}

	@Test(priority = 2,dependsOnMethods = {"verifyPreviewOptionWorkflowCard"},groups = {"Preview-My Recipes"},description = "Verify Drop down on canvas header on preview")
	public void verifyDropdownCanvas() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.recipeCanvasDropDown, webDriver,"Click on dropdown in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.connectorsDetailsCanvas, webDriver, "Verify connector details for recipe canvas in preview mode");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.recipeCanvasDropDown, webDriver,"Click on dropdown in preview mode");
	}

	@Test(priority = 3,dependsOnMethods = {"verifyDropdownCanvas"},groups = {"Preview-My Recipes"},description = "Verify details of recipe workflow on preview mode")
	public void detailsOptionRecipesCanvas() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.detailsLink, webDriver,"Click on details link in preview mode");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.detailsModal, webDriver, "Wait for recipe details modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.recipeDetailsHeader, webDriver, "Verify recipe details header for recipe canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.primaryDetails, webDriver, "Verify primary details for recipe canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.descriptionContainer, webDriver, "Verify description container for recipe canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.referencesContainer, webDriver, "Verify references container for recipe canvas in preview mode");
	}

	@Test(priority = 4,dependsOnMethods = {"detailsOptionRecipesCanvas"},groups = {"Preview-My Recipes"},description = "Verify use recipe button for import modal in details modal")
	public void useRecipeInDetailsModal() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.useRecipeLinkDetailsModal, webDriver,"Click on user recipe button from recipe details modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.importModal, webDriver, "Wait for import modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.importModal, webDriver, "Verify recipe import modal visible from recipe detail modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.importModalCancelButton, webDriver,"Click on cancel button to close import modal");
	}

	@Test(priority = 5,dependsOnMethods = {"useRecipeInDetailsModal"},groups = {"Preview-My Recipes"},description = "Verify reference in details recipe modal")
	public void referenceDetailsModal() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.referenceArrowButton, webDriver,"Click on arrow button in reference modal to check the connectors used in recipes");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.referenceTable, webDriver, "Verify all connectors visible in reference table");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.referenceDownArrowButton, webDriver,"Click on down arrow button in reference modal to close reference modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.detailsCloseButton, webDriver,"Close details modal");
	}

	@Test(priority = 6,dependsOnMethods = {"referenceDetailsModal"},groups = {"Preview-My Recipes"},description = "Verify Use recipe button from canvas page in preview mode")
	public void useRecipeButtonCanvas() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.startTriggerAction, webDriver, "Wait for recipe preview canvas to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.useRecipeCanvas, webDriver,"Click on use recipe button to open import recipes modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.importModal, webDriver, "Wait for import modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.importModal, webDriver, "Verify recipe import modal visible from recipe detail modal");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.importModalCancelButton, webDriver,"Click on cancel button to close import modal");
	}

	@Test(priority = 7,dependsOnMethods = {"useRecipeButtonCanvas"},groups = {"Preview-My Recipes"},description = "Verify actions/connectors and triggers applied in recipes workflow in preview mode")
	public void actionTriggersCanvas() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerAction, webDriver, "Wait for canvas modal in preview mode to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.startTriggerAction, webDriver, "Verify start trigger action to be visible on canvas in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerAction, webDriver, "Verify logger action to be visible on canvas in preview mode");
	}

	@Test(priority = 8,dependsOnMethods = {"actionTriggersCanvas"},groups = {"Preview-My Recipes"},description = "Verify trello action config modal")
	public void actionConfigurationModalCanvas() 
	{
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerAction, webDriver, "Mouse hover on logger action");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerActionSettings, webDriver,"Click on logger settings icon");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerActionConfigurationModal, webDriver, "Wait logger config modal to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerActionConfigurationModal, webDriver, "Verify logger config modal visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerConfigModalClose, webDriver,"Close config modal");
	}

	@Test(priority = 9,dependsOnMethods = {"actionConfigurationModalCanvas"},groups = {"Preview-My Recipes"},description = "Verify action/connectors options")
	public void actionConfigurationOption() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerAction, webDriver, "Wait for canvas modal in preview mode to be visible");
		SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerAction, webDriver, "Mouse hover on logger action");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerCopyDisabledIcon, webDriver, "Verify copy option is disabled in preview mode");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerMoreOptionDisabledIcon, webDriver, "Verify more option is disabled in preview mode");
	}

	@Test(priority = 10,dependsOnMethods = {"actionConfigurationOption"},groups = {"Preview-My Recipes"},description = "Verify stop action configuration")
	public void stopActionConfigurationModal() 
	{
		SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.stopAction, webDriver, "Double click on stop action to open configuration modal for stop action");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerActionConfigurationModal, webDriver, "Wait for stop action config modal in preview mode to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerActionConfigurationModal, webDriver, "Verify stop action configuration modal to be visible in preview mode");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerConfigModalClose, webDriver,"Close config modal");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.loggerAction, webDriver, "Wait for canvas modal in preview mode to be visible");	
	}

	@Test(priority = 11,dependsOnMethods = {"stopActionConfigurationModal"},groups = {"Preview-My Recipes"},description = "Verify debugger option is disabled in preview mode")
	public void debuggerOptionDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.disabledDebuggerIcon, webDriver, "Verify debug icon disabled in preview mode for all recipes");
	}

	@Test(priority = 12,dependsOnMethods = {"debuggerOptionDisabled"},groups = {"Preview-My Recipes"},description = "Verify workflow notes is disabled in preview mode")
	public void workflowNotesDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.disabledWorkflowNotesIcon, webDriver, "Verify workflow notes icon disabled in preview mode for all recipes");
	}

	@Test(priority = 13,dependsOnMethods = {"workflowNotesDisabled"},groups = {"Preview-My Recipes"},description = "Verify workflow versioning is disabled in preview mode")
	public void workflowVersioningDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.disabledWorkflowVersioningIcon, webDriver, "Verify workflow versioning icon disabled in preview mode for all recipes");
	}

	@Test(priority = 14,dependsOnMethods = {"workflowVersioningDisabled"},groups = {"Preview-My Recipes"},description = "Verify keyboard shortcut icon is disabled in preview mode")
	public void keyBoardShortCutIconDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.disabledWorkflowKeyboardShorcutIcon, webDriver, "Verify workflow keyboard shortcut icon disabled in preview mode for all recipes");
	}

	@Test(priority = 15,dependsOnMethods = {"keyBoardShortCutIconDisabled"},groups = {"Preview-My Recipes"},description = "Verify take a tour icon is disabled in preview mode")
	public void takeTourOptionDisabled() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.disabledTakeTourIcon, webDriver, "Verify take tour icon disabled in preview mode for all recipes");
	}

	@Test(priority = 16,dependsOnMethods = {"takeTourOptionDisabled"},groups = {"Preview-My Recipes"},description = "Verify back button ")
	public void backButtonRecipeCanvas() 
	{
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.MyRecipesPreviewTests.MyRecipesPreviewLocators.backArrowButton, webDriver,"Click on back button to visit recipes dashboard");
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