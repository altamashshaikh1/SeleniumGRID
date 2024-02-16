package com.webMethods.io.Integration.PartnerCertsTests;

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
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumUploadDownloadFilesUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWaitUtils;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumWebElementsUtils;
import com.webMethods.io.Integration.SeleniumUtilities.ThreadLocalDriverFactory;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper; 

public class PartnerCertificate
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

	@Test(priority = 0,groups = {"PartnerCertificates"},description = "Login user : PartnerCertificates")
	public void userLogin()
	{
		//Open application
		ThreadLocalDriverFactory.openApplication(webDriver, MavenArgumentConstants.SOURCE_TENANT_URL);

		//Initialize pagefactory
		BaseTestUtils.initializePageFactory(webDriver, "com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators");

		//Login user
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.login_block, webDriver, "Wait for login block");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.emailTextBox,MavenArgumentConstants.SOURCE_TENANT_USERNAME, webDriver,"Enter user id or email id");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.password,MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD, webDriver,"Enter user password");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.loginButton, webDriver,"Click on login button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createNewProjectButton, webDriver,"Verify user logged in");
	}

	@Test(priority = 1,dependsOnMethods = {"userLogin"},groups = {"PartnerCertificates"},description = "Create new partner certificate")
	public void createNewPartnerCertificate() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createNewProjectButton,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.projectSearchTextBox,PropertiesData.readInputData("partnerCertificateProjectName"),webDriver,"Search created project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter to search created project");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.partnerCertificateProjectName,webDriver,"Click on partner certificate project");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.miniLoaderString,webDriver,"Wait for loader to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.configurationsLink,webDriver,"Click on configurations page");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.addedPartnerCertString,webDriver,"Wait for page to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.newCertificateButton,webDriver,"Click on New partner button");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.newPartnerOption,webDriver,"Click on New partner option");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.partnerNameInputbox,PropertiesData.readInputData("partnerCertificateName"),webDriver,"Input partner certificate name");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.TAB,"Press tab button");
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"SAMPLECRT.crt",com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.uploadRecipesButton, "Upload sample CRT file");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.uploadedCertFileName,webDriver,"Wait for file uploaded");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.uploadedCertFileName,webDriver,"Assert certificate file uploaded");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.doneButton,webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createdPartnerCerts,webDriver,"Assert partner certificate created");
	}

	@Test(priority = 2,dependsOnMethods = {"createNewPartnerCertificate"},groups = {"PartnerCertificates"},description = "Edit partner certificate")
	public void editNewPartnerCertificate() 
	{
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createdPartnerCerts,webDriver,"Wait for page to load");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.overlayModalString,webDriver,"Wait for overlay modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createPartnerEllipsis,webDriver,"Click on created partner ellipsis to eidt added partner certs");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Click on edit option");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Click on edit option");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.browseButtonString,webDriver,"Wait for browse button to visible");	
		SeleniumUploadDownloadFilesUtils.uploadFileJS(webDriver,"SAMPLECRT.crt", com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.uploadRecipesButton, "Upload Sample.crt cert");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.uploadedCertFileName,webDriver,"Wait for file uploaded");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.uploadedCertFileName,webDriver,"Assert certificate file uploaded");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.doneButton,webDriver,"Click on done button");
		SeleniumWebElementsUtils.elementvisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createdPartnerCerts,webDriver,"Assert partner certificate created");
	}

	@Test(priority = 3,dependsOnMethods = {"editNewPartnerCertificate"},groups = {"PartnerCertificates"},description = "Delete partner certificate")
	public void deleteNewPartnerCertificate() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.overlayModalString,webDriver,"Wait for page to load");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createdPartnerCerts,webDriver,"Wait for page to load");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.createPartnerEllipsis,webDriver,"Click on created partner ellipsis to delete added partner certs");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyBoardTabAction(webDriver,"Keyboard tab actions");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver, Keys.ENTER,"Click on delete option");
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.accnamevalidatorcircle,webDriver,"Wait for modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.deletePartnerButton,webDriver,"Click on delete button");
	}

	@Test(priority = 4,dependsOnMethods = {"deleteNewPartnerCertificate"},groups = {"PartnerCertificates"},description = "Partner certificate usages in project")
	public void partnerCertificateUsages() 
	{
		SeleniumWaitUtils.waitForElementNotVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.overlayModalStringProject,webDriver,"Wait for overlay modal to dissappear");
		SeleniumWebElementsUtils.click(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.projectTabLink,webDriver,"Click on project dashboard");
		SeleniumWaitUtils.waitForElementVisible(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.addNewProjectString,webDriver,"Wait for project dashboard to load");
		SeleniumWebElementsUtils.inputbox(com.webMethods.io.Integration.PartnerCertsTests.PartnerCertificateLocators.projectSearchTextBox,PropertiesData.readInputData("partnerCertificateProjectName"),webDriver,"Search created project");
		SeleniumKeyBoardActionUtils.keyboardActions(webDriver,Keys.ENTER,"Press enter to search created project");
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