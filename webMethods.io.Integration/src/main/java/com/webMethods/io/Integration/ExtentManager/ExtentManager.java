/**
 *  This class get the extent reports instances, create instances for reports.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.ExtentManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTest;

public class ExtentManager 
{
	public static ExtentReports extent;

	public static ExtentSparkReporter reporter;

	/** HELPER METHOD TO GET EXTENT REPORTS INSTANCE **/
	public static synchronized ExtentReports getInstance()
	{
		if (extent == null)

			try 
		{
				createInstance();
		} 
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Get extent-report instance.",exception.getMessage());
		}

		return extent;

	}

	/** HELPER METHOD TO CREATE EXTENT REPORTS INSTANCE **/
	public static synchronized ExtentReports createInstance()  
	{
		extent = new ExtentReports();		

		reporter = new ExtentSparkReporter(BaseTest.AUTOMATION_REPORT_FILE_PATH);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName(BaseTest.AUTOMATION_REPORT_FILE_NAME);
		reporter.config().setDocumentTitle(BaseTest.AUTOMATION_REPORT_FILE_NAME);
		reporter.config().isTimelineEnabled();
		reporter.config().setEncoding("UTF-8");
		reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent.attachReporter(reporter);
		extent.setSystemInfo("HostName : ","Altamash Shaikh");
		extent.setSystemInfo("Operating System : ",PropertiesData.readConfigData("operatingSystemName"));
		extent.setSystemInfo("Java JDK : ",PropertiesData.readConfigData("jdkversion"));
		extent.setSystemInfo("Selenium Grid : ",PropertiesData.readConfigData("seleniumversion"));
		extent.setSystemInfo("WebDriver Manager : ",PropertiesData.readConfigData("webdrivermanagerversion"));
		extent.setSystemInfo("TestNG : ",PropertiesData.readConfigData("testngversion"));
		extent.setSystemInfo("ExtentReports : ",PropertiesData.readConfigData("mavenversion"));
		extent.setSystemInfo("Apache Maven : ",PropertiesData.readConfigData("extentreports"));
		extent.setSystemInfo("Apache Maven Compiler : ",PropertiesData.readConfigData("mavencompilerversion"));
		extent.setSystemInfo("Apache Maven Sure Fire : ",PropertiesData.readConfigData("mavensurefireversion"));
		extent.setSystemInfo("Apache Maven Embedd : ",PropertiesData.readConfigData("mavenembedderversion"));
		extent.setSystemInfo("Apache Maven Compat : ",PropertiesData.readConfigData("mavencompatversion"));
		extent.setSystemInfo("Apache Maven Build Helper : ",PropertiesData.readConfigData("buildhelpermavenversion"));
		extent.setSystemInfo("Log4j : ",PropertiesData.readConfigData("log4jversion"));
		extent.setSystemInfo("SLF4J API",PropertiesData.readConfigData("slf4japiversion"));
		extent.setSystemInfo("SLF4J : ",PropertiesData.readConfigData("slf4japiversion"));
		extent.setSystemInfo("Common.IO : ",PropertiesData.readConfigData("commonsioversion"));
		extent.setSystemInfo("Common Configuration : ",PropertiesData.readConfigData("commonsconfigurationversion"));
		extent.setSystemInfo("JSON Imp : ",PropertiesData.readConfigData("jsonsimpleversion"));
		extent.setSystemInfo("JSON : ",PropertiesData.readConfigData("jsonversion"));

		return extent;
	}
}