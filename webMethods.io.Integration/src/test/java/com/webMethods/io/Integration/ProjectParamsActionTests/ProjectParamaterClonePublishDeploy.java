package com.webMethods.io.Integration.ProjectParamsActionTests;

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

public class ProjectParamaterClonePublishDeploy 
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

	@Test(priority = 0,groups = {"ProjParam : Clone&Publish"},description = "Login account")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators");

		//Login user	
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.usernameInput,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.passwordInput,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"ProjParam : Clone&Publish"},description="Verify search project")
	public void Projectparameter() throws InterruptedException  
	{		
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2, webDriver, "Click on project search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,PropertiesData.readInputData("ProjectName1"),webDriver,"Input project name");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.myUIautomationproject1, webDriver, "Click on searched project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");

		//Check Parameters
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Parameter,webDriver,"click on Patameter");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test5, webDriver, "check for the test5 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test4, webDriver, "check for the test4 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test3, webDriver, "check for the test3 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test2, webDriver, "check for the test2 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test1, webDriver, "check for the test1 to be visible");
	}

	@Test(priority = 2,dependsOnMethods = {"Projectparameter"},groups = {"ProjParam : Clone&Publish"},description="Verify clone to project2")
	public void Projectparameterclone1() throws InterruptedException  
	{
		//Clone to project2 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Integration,webDriver,"Wait for integration to visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2, webDriver, "Click on search box");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,"ParameterCheck",webDriver,"Input workflow name");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2, webDriver, "Click on workflow search box");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParameterCheck,webDriver,"Assert connector visible on canvas");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.elopsis1,webDriver,"Click on Parametercheck elopsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Clone,webDriver,"Click on Parametercheck clone");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Element, webDriver, "Wait for Clone Workflow : ParameterCheck");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.triggerAuthDropdown,webDriver,"click on the drop down to clone to HarshadUIAutomation project"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.actionsearchfield,"HarshadUI", webDriver,"Input HarshadUIAutomation project name to clone");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.SelectProject2,webDriver,"click on the HarshadUIAutomation project"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Clone1,webDriver,"Click on clone");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflowclonedMessage_str, webDriver, "Wait till clone confirmation message");
	}

	@Test(priority = 3,dependsOnMethods = {"Projectparameterclone1"},groups = {"ProjParam : Clone&Publish"},description="Verify move to project2 and validate parameters")
	public void Projectparameterclone2()
	{
		//move to Project2 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.triggerAuthDropdown1,webDriver,"click on the drop down to move to HarshadUIAutomation project"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.actionsearchfield1,"HarshadUI", webDriver,"Input HarshadUIAutomation project name to move");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.SelectProject2,webDriver,"click on the HarshadUIAutomation project"); 

		//Check Parameters
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Parameter,webDriver,"click on Parameter");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test5, webDriver, "check for the test5 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test4, webDriver, "check for the test4 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test3, webDriver, "check for the test3 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test2, webDriver, "check for the test2 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test1, webDriver, "check for the test1 to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Integration,webDriver,"click on Integration");
	}

	@Test(priority = 4,dependsOnMethods = {"Projectparameterclone2"},groups = {"ProjParam : Clone&Publish"},description="Verify clone to project3")
	public void Projectparameterclone3() throws InterruptedException  
	{
		//Clone to project3		
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loadingindicator,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,webDriver,"Click on search");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,"Copy of ParameterCheck",webDriver,"Input workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,webDriver,"Click on search");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Workflowname1,webDriver, "check for workflow name");
		Thread.sleep(1000);
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.addWorkflowIcon,webDriver,"Wait for workflow dashboard to load");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.elopsis1,webDriver,"Click on Copy of ParameterCheck elopsis ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Clone,webDriver,"Click on Copy of ParameterCheck clone");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Element2, webDriver, "Wait for Clone Workflow : copy of ParameterCheck");

		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.triggerAuthDropdownProject2,webDriver,"click on the drop down to clone to JMS automation project"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.actionsearchfield,"JMS_A", webDriver,"Input JMS_automation project name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.SelectProject3,webDriver,"click on JMS_Autimation project"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Clone1,webDriver,"Click on clone");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflowclonedMessage_str, webDriver, "Wait till clone confirmation message");
	}

	@Test(priority = 4,dependsOnMethods = {"Projectparameterclone3"},groups = {"ProjParam : Clone&Publish"},description="delete parameter from project2")
	public void deleteparameter2() throws InterruptedException  
	{
		//delete project2
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loadingindicator,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,webDriver,"Click on search");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,"Copy of ParameterCheck",webDriver,"Input workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,webDriver,"Click on search");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		Thread.sleep(1000);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loadingindicator,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Workflowname1,webDriver, "check for workflow name");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.elopsis1,webDriver,"Click on workflow elopsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow,webDriver,"delete the workflow");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.popup1, webDriver, "Wait for popup to diaply");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1,webDriver,"Click on delete project");

		//delete parameters in project2 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Parameter,webDriver,"click on Parameter");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till workflow delete message disappear");

		//Parameter1
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test5 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-0");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-0 delete message disappear");
		Thread.sleep(1000);

		//Parameter2
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test4 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-1");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-1 delete message disappear");
		Thread.sleep(1000);

		//Parameter3
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test3 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-2");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-2 delete message disappear");
		Thread.sleep(1000);

		//Parameter4
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test2 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-3");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-3 delete message disappear");
		Thread.sleep(1000);

		//Parameter5
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test1 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-4");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-4 delete message disappear");
	}

	@Test(priority = 5,dependsOnMethods = {"deleteparameter2"},groups = {"ProjParam : Clone&Publish"},description="Verify move to project3 and validate parameters")
	public void ProjectparameterMove1()
	{
		//move to Project2 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.triggerAuthDropdown2,webDriver,"click on the drop down to move to JMS_Automation project"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.actionsearchfield1,"JMS_A", webDriver,"Input JMS_Automation project name to move");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.SelectProject3,webDriver,"click on the HarshadUIAutomation project"); 

		//Check Parameters
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Parameter,webDriver,"click on Parameter");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test5, webDriver, "check for the test5 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test4, webDriver, "check for the test4 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test3, webDriver, "check for the test3 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test2, webDriver, "check for the test2 to be visible");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.test1, webDriver, "check for the test1 to be visible");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Integration,webDriver,"click on Integration");
	}
	@Test(priority = 6,dependsOnMethods = {"ProjectparameterMove1"},groups = {"ProjParam : Clone&Publish"},description="delete parameter from project3")
	public void deleteparameter3() throws InterruptedException  
	{
		//delete project2
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loadingindicator,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,webDriver,"Click on search");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,"Copy of Copy of ParameterCheck",webDriver,"Input workflow name");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,webDriver,"Click on search");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		Thread.sleep(1000);
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loadingindicator,webDriver, "Wait for loader to disappear");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Workflowname2,webDriver, "check for workflow name");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.elopsis1,webDriver,"Click on workflow elopsis");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow,webDriver,"delete the workflow");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.popup1, webDriver, "Wait for popup to diaply");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1,webDriver,"Click on delete project");

		//delete parameters in project3
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Configurations,webDriver,"click on configuration");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.workflow,webDriver,"click on workflow");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Parameter,webDriver,"click on Parameter");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till workflow delete message disappear");

		//Parameter1
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test5 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-0");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-0 delete message disappear");
		Thread.sleep(1000);

		//Parameter2
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test4 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-1");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-1 delete message disappear");
		Thread.sleep(1000);

		//Parameter3
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test3 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-2");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-2 delete message disappear");
		Thread.sleep(1000);

		//Parameter4
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test2 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-3");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-3 delete message disappear");
		Thread.sleep(1000);

		//Parameter5
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.DeleteParameter01,webDriver,"click on test1 elopises");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Delete1,webDriver,"click on delete param-4");	
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.deleteworkflow1YES,webDriver,"Click on delete confirm");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.ParamdeletedMessage_str, webDriver, "Wait till param-4 delete message disappear");
	}

	@Test(priority = 7,dependsOnMethods = {"deleteparameter3"},groups = {"ProjParam : Clone&Publish"},description="deploy")
	public void deployparameter3() throws InterruptedException  
	{
		//deploy to dev realm2
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Projects,webDriver,"click on Projects");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.loadingindicator,webDriver, "Wait for loader to disappear");
		Thread.sleep(1000);
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.projectSearchTextBox2,"VarshithaUIAutomation",webDriver,"Input VarshithaUIAutomation name");
		Thread.sleep(1000);
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER, "Press enter to search project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.elopsis,webDriver,"Click on VarshithaUIAutomation elopsis ");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Publishproject,webDriver,"Click on Publish project");

		//Select Assets 
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Assetworkflows,webDriver,"check for element workflow visible");

		//click(com.projectParameterTestPageObjects.Locators.Assetworkflows,driver,"check box"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Next,webDriver,"click on next"); 
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Next,webDriver,"click on next"); 
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.InputName,"TestParameter",webDriver,"Input name to publish workflow");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.InputDescription,"TestParameter testing",webDriver,"Input name to publish workflow");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Dropdown,webDriver,"click on Dropdown");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Key board tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Key board tab actions");
		SeleniumKeyBoardActionUtils.keyboardActionsData(webDriver,MavenArgumentConstants.DESTINATION_TENANT_NAME, "Enter destination tenant name");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Key board tab actions");
		SeleniumWaitUtils.waitForElementToBeClickable(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Publish, webDriver, "Wait till publish button is clickable");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.ProjectParamsActionTests.ProjectParamaterClonePublishDeployLocators.Publish,webDriver,"click on publish");
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