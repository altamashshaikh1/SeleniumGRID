package com.webMethods.io.Integration.Triggers.BVT;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil; 
import com.webMethods.io.Integration.SeleniumUtilities.BaseTestUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
//import com.webMethods.io.Integration.TestDataCommonFeatures.LoginUserOperations;
//import com.webMethods.io.Integration.TestDataCommonFeatures.ProjectCRUDOperations;
//import com.webMethods.io.Integration.TestDataCommonFeatures.WorkFlowsCRUDOperations;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test; 

public class Triggers 
{
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<RemoteWebDriver> webDriverThreadLocal = new ThreadLocal<>();

	public static WebDriver webDriver;

	public static SoftAssertWrapper softAssert;

	public static String userAuthtoken;

	public static String userCookies;

	public static String loginUserResponse;

	public static String projectName;

	public static String newProjectPayload;

	public static String workflowName;

	public static String newWorkflowPayload;

	public static String projectUID;

	public static String createProjectResponse;

	public static String workflowUID;

	public static String createWorkflowResponse;

	public static String updateWorkflowPayload;

	public static String updateWorkflowResponse;

	@BeforeTest
	public void startRemoteWebDriverSession()
	{
		//Set desired capabilities
		capabilities = ThreadLocalDriverFactory.getCapabilities();

		//Initiate ThreadSafe Session
		ThreadLocalDriverFactory.setThreadSafeSession(webDriverThreadLocal,BaseTestUtils.getGridURL(), capabilities);

		//Get webdriver from threadlocal session
		webDriver = ThreadLocalDriverFactory.getDriver(webDriverThreadLocal);
	}

	@BeforeMethod
	public void softAssertionInitialization() 
	{
		//Initialize soft assertion object.
		softAssert = SoftAssertWrapper.initializingSoftAssertionWrapper(softAssert);
	}

	@Test(priority = 0,groups = {"Triggers"},description = "Login user : Triggers CRUD")
	public void triggerLogin() 
	{		
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver,"com.webMethods.io.Integration.Triggers.BVT.TriggerLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"triggerLogin"},groups = {"Recipes"},description = "Create and apply trigger to workflow")
	public void createNewTriggerTest() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createNewProjectButton, webDriver,"Create new trigger test");

		//		/**DYNAMICA DATA**/
		//		//Login user to create session
		//		loginUserResponse = LoginUserOperations.loginUser(MavenArgumentConstants.SOURCE_TENANT_URL, MavenArgumentConstants.SOURCE_TENANT_USERNAME, MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD);
		//		userAuthtoken = LoginUserOperations.getAuthtoken(loginUserResponse);
		//		userCookies = LoginUserOperations.getCookies(loginUserResponse);
		//
		//		//Create new project 
		//		projectName = "PRBVTSUITE_NEW_PROJECT_"+BaseTestUtils.generateString(4)+"";
		//		newProjectPayload = "{\"name\":\""+projectName+"\"}";
		//		createProjectResponse = ProjectCRUDOperations.CREATE_NEW_PROJECT(MavenArgumentConstants.SOURCE_TENANT_URL, userAuthtoken, userCookies, newProjectPayload);
		//		//Get project uid
		//		projectUID = ProjectCRUDOperations.getProjectUID(createProjectResponse);
		//
		//		//Create new workflow
		//		workflowName = "PRBVTSUITE_NEW_WF_"+BaseTestUtils.generateString(4)+"";
		//		newWorkflowPayload = "{\"name\":\""+workflowName+"\",\"description\":\"\",\"tags\":[],\"container_size\":256}";
		//		createWorkflowResponse = WorkFlowsCRUDOperations.CREATE_NEW_WORKFLOW(MavenArgumentConstants.SOURCE_TENANT_URL,userAuthtoken,userCookies, projectUID, newWorkflowPayload); 
		//		//Get workflow uid
		//		workflowUID = WorkFlowsCRUDOperations.getWorkflowUID(createWorkflowResponse);
		//
		//		//Update workflow created
		//	    updateWorkflowPayload = FileUtilitiesHelperFunctions.readSampleJSONPayload("WorkflowUpdateLoggerAction.txt");
		//		updateWorkflowResponse = WorkFlowsCRUDOperations.UPDATE_WORKFLOW(MavenArgumentConstants.SOURCE_TENANT_URL,userAuthtoken,userCookies, workflowUID, projectUID, updateWorkflowPayload);
		//		//Get workflow uid
		//		workflowUID = WorkFlowsCRUDOperations.getWorkflowUID(updateWorkflowResponse);
		//		/**DYNAMICA DATA**/
		//
		//				//Apply trigger to workflow
		//				SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.projectSearchTextBox,projectName,webDriver,"Input project name");       
		//				SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		//				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createdProjectTitleCard, webDriver, "Click on created project card");
		//				SeleniumKeyBoardActionUtils.mousehover(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createdWorkflowTitleCard, webDriver, "Click on workflow created.");
		//				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.workflowEditOption,webDriver,"Click on edit option");
		//				SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Double click on start action.");
		//				SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.searchTrigger,"Clock",webDriver,"Search Wufoo trigger to add.");       
		//				SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.searchedClockTrigger,webDriver,"Click on click trigger");
		//				//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.nextButtonTriggerModal,webDriver,"Click on next to add wufoo details");
		//				//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.doneButtonTriggerModal,webDriver,"Click on done to add wufoo details");
		//				//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.clockTriggerApplied, webDriver, "Verify clock trigger applied");
	}

	@Test(priority = 2,dependsOnMethods = {"createNewTriggerTest"},groups = {"Recipes"},description = "Upsert trigger to workflow")
	public void upsertTriggerTest() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createNewProjectButton, webDriver,"Upsert new trigger test");
		//Double click on added trigger and select to upsert the added trigger in project
		//SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Double click on start action.");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.clockTriggerUpsertList, webDriver, "Select on added clock trigger to upsert");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.doneButtonTriggerModal, webDriver, "Click on Done button to upsert trigger");
	}

	@Test(priority = 3,dependsOnMethods = {"upsertTriggerTest"},groups = {"Recipes"},description = "Edit trigger to workflow")
	public void editTriggerTest() 
	{
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createNewProjectButton, webDriver,"Edit new trigger test");		//Double click on adde trigger and select update or edit to update the trigger added in project
		//SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Wait for start action to be clickable.");
		//SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Double click on start action.");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.editPencilTriggerEdit, webDriver, "Click on edit pencil to edit trigger.");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.doneButtonTriggerModal, webDriver, "Click on Done button to upsert trigger");
	}

	@Test(priority = 4,dependsOnMethods = {"editTriggerTest"},groups = {"Recipes"},description = "Delete trigger to workflow")
	public void removeTrigger() 
	{
		//Double click on start action and remove applied trigger in workflow 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createNewProjectButton, webDriver,"Remove new trigger test");		//SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Wait for start action to be clickable.");
		//SeleniumKeyBoardActionUtils.doubleClick(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Double click on start action.");
		//SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.startAction, webDriver, "Wait for start action to be clickable.");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.deleteTriggerIcon, webDriver, "Click on delete button to delete workflow.");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.deleteYesOption, webDriver, "Click on yes to delete workflow.");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.triggerModalCloseIcon, webDriver, "Close trigger modal.");

		//Save workflow
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.saveWorkflowButton, webDriver, "Click on save button to save workflow.");
		//SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.leaveCanvasButton, webDriver, "Wait for leave button to be clickable");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.leaveCanvasButton, webDriver, "Click on leave canvas button.");
	}

	@Test(priority = 5,dependsOnMethods = {"removeTrigger"},groups = {"Recipes"},description = "Delete trigger to workflow")
	public void deleteTriggerTest() 
	{
		//Delete workflow and project to delete trigger
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createNewProjectButton, webDriver,"Delete new trigger test");		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.workflowEllipsis, webDriver, "Click ellipsis option");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.triggerDeleteOption, webDriver, "Click delete option to delete workflow");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.triggerConfirmDeleteOption, webDriver, "Click confirm delete option to delete workflow");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.newWorkflowLink, webDriver, "Trigger deleted (Workflow delete)");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.projectLink, webDriver, "Visit project dashboard");
		//SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.projectSearchTextBox,projectName,webDriver,"Input project name");       
		//SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.createdProjectEllipsis, webDriver, "Click ellipsis option on project card to delete");
		////SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.projectDeleteOption, webDriver, "Click delete option on project card to delete");
		//SeleniumWebElementsUtils.click(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.projectConfirmDeleteOption, webDriver, "Click pn confirm delete option project");
		//SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.Triggers.BVT.TriggerLocators.projectDeleteMessage, webDriver, "Trigger deleted (Project delete)");
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