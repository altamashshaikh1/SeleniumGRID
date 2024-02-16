/**
 *  This class consists of @BeforeSuite and @AfterSuite implementation.
 *  Notifying teams on slack channel and share reports post executions. 
 *  Auto PV reporting post executions for CloudCycle reporting.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterSuite;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.AutoPVReporting;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.SlackAPI;
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Constants.SupportedMavenArgumentValues;
import com.webMethods.io.Integration.Constants.TestDataConstants;
import com.webMethods.io.Integration.FileUtilities.FileUtilitiesHelperFunctions;
import com.webMethods.io.Integration.Listeners.ListenersTestNG;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;

@Listeners(ListenersTestNG.class)
public class BaseTest 
{
	public static String UI_BUILD_VERSION;

	public static String AUTOMATION_REPORT_FILE_NAME = "";

	public static String AUTOMATION_REPORT_FILE_PATH = "";

	public static String ZIP_FILE_NAME = "";

	public static String ZIP_FILE_PATH = "";

	public static String PR_NAME = "";

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws InterruptedException
	{	
		/** VERIFY ALL MAVEN ARGUMENTS VALUES WITH SUPPORTED MAVEN ARGUMENTS **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying all maven argument values from maven command.");
		BaseTestUtils.verifyEnvironmentArgument(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT, SupportedMavenArgumentValues.SUPPORTED_ENVIRONMENTS);
		BaseTestUtils.verifyBrowserArgument(MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT, SupportedMavenArgumentValues.SUPPORTED_BROWSERS);
		BaseTestUtils.verifySuiteXMLFile(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT, SupportedMavenArgumentValues.SUPPORTED_XML_FILE_NAMES);

		/** VERIFY ENVIRONMENT & SRC/DEST TENANT URL VALUES COMPATIBILITIES **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying environment compatibility with Src & Dest tenant urls.");
		BaseTestUtils.verifyEnvironmentAndTenantURL(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT, MavenArgumentConstants.SOURCE_TENANT_URL);
		BaseTestUtils.verifyEnvironmentAndTenantURL(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT, MavenArgumentConstants.DESTINATION_TENANT_URL);
		System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant URLs are valid with "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT);
		LoggerUtil.INFO("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant URLs are valid with "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT);;

		/** VERIFY SRC TENANT & DEST TENANT URL COMPATIBILITIES **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying Src & Dest tenant url compatibilities.");
		BaseTestUtils.tenantURLCompatilitiesCheck(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT, MavenArgumentConstants.SOURCE_TENANT_URL, MavenArgumentConstants.DESTINATION_TENANT_URL);

		/** VERIFY ENVIRONMENT & TESTNG XML FILE COMPATIBILITIES **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying Environment & suiteXMLFile name compatibilities.");
		BaseTestUtils.environmentAndXMLFileCompatibilitiesCheck(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT, MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT);

		/** VERIFY ALL PROPERTIES KEY VALUE (run-configurations.properties & selenium-grid-configurations.properties) **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying properties file for duplicate/null/properties key not present (run-configurations.properties & selenium-grid-configurations.properties.");
		BaseTestUtils.scanPropertiesFile(FilePathConstants.RUN_CONFIGURATIONS_PROPERTIES_FILE_PATH);
		BaseTestUtils.scanPropertiesFile(FilePathConstants.SELENIUM_GRID_CONFIGURATIONS_FILE_PATH);
		BaseTestUtils.scanPropertiesFile(FilePathConstants.TEST_INPUT_DATA_PROPERTIES_FILE_PATH);
		BaseTestUtils.scanPropertiesFile(FilePathConstants.TEST_ASSERTION_DATA_PROPERTIES_FILE_PATH);
		System.out.println("  Pre-Check: Verify duplicate key from properties file. No Duplicate key found.");
		LoggerUtil.INFO("Pre-Check: Verify duplicate key from properties file. No Duplicate key found.");
		System.out.println("  Pre-Check: Verify duplicate key from properties file. No Null value found.");
		LoggerUtil.INFO("Pre-Check: Verify duplicate key from properties file. No Null value found.");
		System.out.println("  Pre-Check: Verify duplicate key from properties file. No Missing keys found.");
		LoggerUtil.INFO("Pre-Check: Verify duplicate key from properties file. No Missing keys found.");

		///** BRING UP LOCAL SELENIUM GRID ( OPTIONAL ) **/
		/* BaseTestUtils.bringUpLocalGRID(); */

		/** VERIFY SELENIUM GRID URL **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying Selenium GRID URL.");
		BaseTestUtils.verifySeleniumGRIDURL(BaseTestUtils.getGridURL());

		/** VERIFY ENVIRONMENT AVAILABILITY **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying environment availability.");
		HttpURLConnHelperFuntions.verifyTenantHealthStatus(MavenArgumentConstants.SOURCE_TENANT_URL+"/build-info.json");
		System.out.println("  Pre-Check: Verifying environment availability. "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+" is up and accessible. Automation suite execution will begin now.");
		LoggerUtil.INFO(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+" is up and accessible. Automation suite execution will begin now.");

		/** VERIFY SRC TENANT & DEST TENANT URL CREDENTIALS**/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verifying source and destination tenant credentials.");
		HttpURLConnHelperFuntions.verifyUserCredentialsWithTenant(MavenArgumentConstants.SOURCE_TENANT_URL+"/enterprise/v1/user/token", MavenArgumentConstants.SOURCE_TENANT_USERNAME, MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD);
		Thread.sleep(5000);
		HttpURLConnHelperFuntions.verifyUserCredentialsWithTenant(MavenArgumentConstants.DESTINATION_TENANT_URL+"/enterprise/v1/user/token", MavenArgumentConstants.DESTINATION_TENANT_USERNAME,MavenArgumentConstants.DESTINATION_TENANT_USERPASSWORD);
		System.out.println("  Pre-Check: Verify credentails. Valid credentials entered.");
		LoggerUtil.INFO("Pre-Check: Verify credentails. Valid credentials entered.");
		
		/** FETCH CURRENT UI BUILD VERSION **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Verify fetching current UI BuildVersion.");
		UI_BUILD_VERSION = "v"+BaseTestUtils.getBuildVersion(MavenArgumentConstants.SOURCE_TENANT_URL);
		System.out.println("  Pre-Check: Verify UI Build Version");
		String[] parts = UI_BUILD_VERSION.split("_");
		PR_NAME = parts[1];
		
		/** CREATE REPORT & FAILED SCREEN SHOTS FOLDER **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Creating Report and FailedScreenShots folder");
		FileUtilitiesHelperFunctions.createFolder(FilePathConstants.REPORT_FOLDER_PATH);
		FileUtilitiesHelperFunctions.createFolder(FilePathConstants.TEST_FAILED_SCREEN_SHOTS_FOLDER_PATH);
		System.out.println("  Pre-Check: Report folder created.");
		System.out.println("  Pre-Check: Test Failed Screen Shots folder created.");

		/** FINAL AUTOMATION REPORT FILE NAME (.html) **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Generating Automation report and zip file name.");		
		AUTOMATION_REPORT_FILE_NAME = FileUtilitiesHelperFunctions.generateReportFileName();
		AUTOMATION_REPORT_FILE_PATH = FilePathConstants.REPORT_FOLDER_PATH+FilePathConstants.FILE_SEPARATOR+AUTOMATION_REPORT_FILE_NAME;
		System.out.println("  PreCheck : Verify report file name. Automation report .html file "+AUTOMATION_REPORT_FILE_NAME+" created.");

		/** FINAL AUTOMATION .ZIP FILE NAME  **/		
		ZIP_FILE_NAME = FileUtilitiesHelperFunctions.generateZipFileName(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT, MavenArgumentConstants.SOURCE_TENANT_URL, MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT);
		ZIP_FILE_PATH = FilePathConstants.ROOT_DIRECTORY_PATH+FilePathConstants.FILE_SEPARATOR;
		System.out.println("  PreCheck : Verify zip file name. Automation .zip file "+ZIP_FILE_NAME+" created.");

		/** AUTOMATION SUITE START MESSAGE **/
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		BaseTestUtils.automationSuiteStartMessages(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT, MavenArgumentConstants.SOURCE_TENANT_URL);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown()
	{		
		/** AUTOMATION SUITE END MESSAGE **/
		BaseTestUtils.automationSuiteEndMessages(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT, MavenArgumentConstants.SOURCE_TENANT_URL);

		/** COLLECT TEST SUITE RESULTS **/
		int successTests = TestDataConstants.totalSuccessTests;
		int failureTests = TestDataConstants.totalFailureTests;
		int skippedTests = TestDataConstants.totalSkipTests;
		System.out.println("Execution Summary");
		System.out.println("SUCCESS : "+successTests);
		System.out.println("FAILED : "+failureTests);
		System.out.println("SKIPPED : "+skippedTests);
		System.out.println("Total number of test cases count : "+TestDataConstants.totalTestCount);
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");

		/** CREATE ZIP FILE **/
		FileUtilitiesHelperFunctions.createResultsZipFile(FilePathConstants.TEST_AUTOMATION_RESULTS_FOLDER_PATH, ZIP_FILE_PATH+ZIP_FILE_NAME);

		/** SEARCH ZIP FILE IF FOUND SHARE IT TO TEAM **/
		SlackAPI.uploadAndShareReportFile(ZIP_FILE_NAME);

		/** AUTO REPORT PV IF CLOUD CYCLE SUITE IS SELECTED**/
		if(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT.equalsIgnoreCase("TestSuiteCloudCycle.xml"))
		{
			if(failureTests == 0)
			{
				AutoPVReporting.HTTP_POST_PV_REPORT(AutoPVReporting.returnProductName(MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT),AutoPVReporting.returnPlatformName(MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT),MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT,TestDataConstants.totalTestCount, failureTests, UI_BUILD_VERSION);
			}
			else
			{
				System.out.println("!!! FAILURE !!! : Auto PV report request terminate since we have failures in Automation Suite.");
				SlackAPI.postMessageInSlack("!!! FAILURE !!! : Automation suite execution consists of Test failures hence _*"+MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT+"*_ CloudCycle auto report request terminated. <"+PropertiesData.readConfigData("manualReportPVURL")+"|*Click Here*> to report _*"+MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT+"*_ CloudCycle manually.");
			}
		}
		else
		{
			System.out.println("Auto PV reporting only available for CloudCycle suites.");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		}
	}
}