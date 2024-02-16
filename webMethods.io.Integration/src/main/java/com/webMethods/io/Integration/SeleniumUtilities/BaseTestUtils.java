/**
 *  All helper methods implementation that can be used to have pre-check invocations before starting the execution.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.SlackAPI;
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Constants.TestDataConstants;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import com.webMethods.io.Integration.FileUtilities.FileUtilitiesHelperFunctions;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;

public class BaseTestUtils 
{
	/** HELPER METHOD TO VERIFY ENVIRONMENT MAVEN ARGUMENT VALUE **/
	public static void verifyEnvironmentArgument(String environmentName, List<String> supportedEnvironmentList)
	{
		if(!supportedEnvironmentList.contains(environmentName))
		{
			CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment. " +environmentName+" environment entered is not supported.");
		}
		else
		{
			System.out.println("  Pre-Check : Verifying Environment. " +environmentName+" selected.");
			LoggerUtil.INFO("Pre-Check : Verifying Environment. " +environmentName+" selected.");
		}
	}

	/** HELPER METHOD TO VERIFY BROWSER MAVEN ARGUMENT VALUE **/
	public static void verifyBrowserArgument(String browserName, List<String> supportedBrowserList)
	{
		if(!supportedBrowserList.contains(browserName))
		{
			CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Browser. "+browserName+" browser entered is not supported.");
		}
		else
		{
			System.out.println("  Pre-Check : Verifying Browser. "+browserName+" selected.");
			LoggerUtil.INFO("Pre-Check : Verifying Browser. "+browserName+" selected.");
		}
	}

	/** HELPER METHOD TO VERIFY CLOUD-CYCLE MAVEN ARGUMENT VALUE **/
	public static void verifyTestSuiteTypeArgument(String testSuiteName, List<String> supportedTestSuiteList)
	{
		if(!supportedTestSuiteList.contains(testSuiteName))
		{
			CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying TestSuite type. "+testSuiteName+" entered is not supported.");
		}
		else
		{
			System.out.println("  Pre-Check : Verifying TestSuite type. "+testSuiteName+" selected.");
			LoggerUtil.INFO("Pre-Check : Verifying TestSuite type. "+testSuiteName+" selected.");
		}
	}

	/** HELPER METHOD TO VERIFY DOCKER ENVIRONMENT MAVEN ARGUMENT VALUE **/
	public static void verifyDockerEnvironmentName(String dockerName, List<String> supportedDockerEnvironmentList)
	{
		if(!supportedDockerEnvironmentList.contains(dockerName))
		{
			CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Docker Environment. "+dockerName+" docker environment entered is not supported.");
		}
		else
		{
			System.out.println("  Pre-Check : Verifying Docker Environment. "+dockerName+" selected.");
			LoggerUtil.INFO("Pre-Check : Verifying Docker Environment. "+dockerName+" selected.");
		}
	}

	/** HELPER METHOD TO VERIFY SUITE XML FILE NAME MAVEN ARGUMENT VALUE **/
	public static void verifySuiteXMLFile(String suiteFileName, List<String> supportedTestngXMLFile)
	{
		if(!supportedTestngXMLFile.contains(suiteFileName))
		{
			CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying SuiteXML File. " +suiteFileName+" suite XML file entered is not supported.");
		}
		else
		{
			System.out.println("  Pre-Check : Verifying SuiteXML File. " +suiteFileName+" selected.");
			LoggerUtil.INFO("Pre-Check : Verifying SuiteXML File. " +suiteFileName+" selected.");
		}
	}

	/** HELPER METHOD TO VERIFY ENVIRONMENT AND TENANT URL  **/
	public static void verifyEnvironmentAndTenantURL(String environmentName, String tenantURL)
	{
		switch(environmentName)
		{
		case "ComponentBVT" :
		case "PreDev" :
			if(tenantURL.contains("stag-int-aws-us.webmethods.io"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
			break;

		case "Developement" :
			if(tenantURL.contains("dev-int-aws-us.webmethods.io"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
			break;

		case "Integration" :
			if(tenantURL.contains("int-aw-us1.webmethods-int.io"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
			break;

		case "Stag" :
			if(tenantURL.contains("stag-int-aws-us.webmethods.io") || tenantURL.contains("int-az-us.webmethods-stage.io"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
			break;

		case "Spro" :
			if(tenantURL.contains("spro-int-aws-us.webmethods.io") || tenantURL.contains("int-az-us.webmethods-spro.io"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
			break;

		case "PreProd" :
			if(tenantURL.contains("preprod-int-aws-us.webmethods.io") || tenantURL.contains("int-az-us.webmethods-preprod.io"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
			break;

		case "Production" :
			if(tenantURL.contains("int-aws-us.webmethods.io") || tenantURL.contains("int-aw-au.webmethods.io") || 
					tenantURL.contains("int-aws-de.webmethods.io") || tenantURL.contains("int-az-us.webmethods.io") || 
					tenantURL.contains("int-az-au.webmethods.io") || tenantURL.contains("int-az-us1.webmethods.io") || 
					tenantURL.contains("int-az-us2.webmethods.io") || tenantURL.contains("acmeco.cloud"))
			{
				System.out.println("  Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is valid with "+environmentName);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Environment & TenantURL compatibility. Tenant "+tenantURL+" is invalid with "+environmentName);
			}
		}
	}

	/** HELPER METHOD TO CHECK SRC & DEST TENANT URL's COMPATIBILITIES **/
	public static void tenantURLCompatilitiesCheck(String environmentName, String srcURL, String destURL)
	{
		switch(environmentName)
		{
		case "ComponentBVT" :
		case "PreDev" :
			if( srcURL.contains("stag-int-aws-us.webmethods.io") && destURL.contains("stag-int-aws-us.webmethods.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;

			/** case "PreDev not available for Azure" : **/

		case "Developement" :
			if( srcURL.contains("dev-int-aws-us.webmethods.io") && destURL.contains("dev-int-aws-us.webmethods.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;

			/** case "Development not available for Azure" : **/

		case "Integration" :
			if(srcURL.contains("int-aw-us1.webmethods-int.io") && destURL.contains("int-aw-us1.webmethods-int.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;

		case "Stag" :
			if(srcURL.contains("stag-int-aws-us.webmethods.io") && destURL.contains("stag-int-aws-us.webmethods.io") ||
					srcURL.contains("int-az-us.webmethods-stage.io") && destURL.contains("int-az-us.webmethods-stage.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;

		case "Spro" :
			if(srcURL.contains("spro-int-aws-us.webmethods.io") && destURL.contains("spro-int-aws-us.webmethods.io") ||
					srcURL.contains("int-az-us.webmethods-spro.io") && destURL.contains("int-az-us.webmethods-spro.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;

		case "PreProd" :
			if(srcURL.contains("preprod-int-aws-us.webmethods.io") && destURL.contains("preprod-int-aws-us.webmethods.io") ||
					srcURL.contains("int-az-us.webmethods-preprod.io") && destURL.contains("int-az-us.webmethods-preprod.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;

		case "Production" :
			if(srcURL.contains("int-aws-us.webmethods.io") && destURL.contains("int-aws-us.webmethods.io") ||
					srcURL.contains("int-aws-de.webmethods.io") && destURL.contains("int-aws-de.webmethods.io") ||
					srcURL.contains("int-aw-au.webmethods.io") && destURL.contains("acmeco.cloud") || 
					srcURL.contains("int-aw-au.webmethods.io") && destURL.contains("int-aw-au.webmethods.io") || 
					srcURL.contains("int-az-us.webmethods.io") && destURL.contains("int-az-us.webmethods.io") ||
					srcURL.contains("int-az-us1.webmethods.io") && destURL.contains("int-az-us1.webmethods.io") ||
					srcURL.contains("int-az-us2.webmethods.io") && destURL.contains("int-az-us2.webmethods.io") || 
					srcURL.contains("int-az-eu.webmethods.io") && destURL.contains("int-az-eu.webmethods.io") || 
					srcURL.contains("int-az-au.webmethods.io") && destURL.contains("int-az-au.webmethods.io"))
			{
				System.out.println("  Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
				LoggerUtil.INFO("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. Source & Destination Tenant URL are valid and compatible.");
			}
			else
			{	
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying Source & Destination tenant URLs compatibilities. " +"Source Tenant URL : "+srcURL+" is not compatible with Destination Tenant URL : "+destURL);
			}
			break;
		}
	}

	/** HELPER METHOD TO CHECK ENVIRONMENT AND TESTNGXML FILE COMPATIBILITIES **/
	public static void environmentAndXMLFileCompatibilitiesCheck(String testNGFileName, String environmentName)
	{
		switch(testNGFileName)
		{		
		case "PreDev.xml" :
		case "ComponentBVT.xml":
			if(!environmentName.equalsIgnoreCase("PreDev"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "Developement.xml" :
			if(!environmentName.equalsIgnoreCase("Developement"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "Integration.xml" :
			if(!environmentName.equalsIgnoreCase("Integration"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "Stag.xml" :
			if(!environmentName.equalsIgnoreCase("Stag"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "Spro.xml" :
			if(!environmentName.equalsIgnoreCase("Spro"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "PreProd.xml" :
			if(!environmentName.equalsIgnoreCase("PreProd"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "Production.xml" :
			if(!environmentName.equalsIgnoreCase("Production"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}
			break;

		case "TestSuiteCloudCycle.xml" :
			if(!environmentName.equalsIgnoreCase("Developement") || !environmentName.equalsIgnoreCase("Integration") ||
					!environmentName.equalsIgnoreCase("Stag") || !environmentName.equalsIgnoreCase("Spro") || !environmentName.equalsIgnoreCase("PreProd") ||
					!environmentName.equalsIgnoreCase("Production"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}

		case "LocalTest.xml" :
			if(!environmentName.equalsIgnoreCase("Developement") || !environmentName.equalsIgnoreCase("Integration") ||
					!environmentName.equalsIgnoreCase("Stag") || !environmentName.equalsIgnoreCase("Spro") || !environmentName.equalsIgnoreCase("PreProd") ||
					!environmentName.equalsIgnoreCase("Production"))
			{
				System.out.println(testNGFileName+" TestNG xml file is not supported for "+environmentName+" environment.");
			}

			break;
		}
	}

	/** HELPER METHOD TO SCAN ALL PROPERTIES FILES FOR INVALID, DUPLICATE OR NULL VALUES **/
	public static void scanPropertiesFile(String filePath)
	{
		Properties properties = new Properties();

		try 
		{
			FileInputStream fileInputStream = new FileInputStream(filePath);
			properties.load(fileInputStream);
			fileInputStream.close();

			Set<String> keys = new HashSet<>();
			Enumeration<?> propertyNames = properties.propertyNames();

			while (propertyNames.hasMoreElements()) 
			{
				String key = (String) propertyNames.nextElement();
				String value = properties.getProperty(key);

				//Check for duplicate key
				if (keys.contains(key))
				{								
					CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying duplicate keys in properties file. Duplicate key "+key+" found in "+filePath+" properties file.");
				}

				keys.add(key);

				// Check for null value
				if (value == null || value.trim().isEmpty()) 
				{								
					CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying null value for keys in properties file. Null value for key "+key+" found in "+filePath+" properties file.");
				}
			}

			// Check for missing keys
			keys.removeAll(properties.stringPropertyNames());

			if (!keys.isEmpty()) 
			{								
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verifying missing key in properties file. Properties key "+keys+" no present in "+filePath+" properties file.");
			}
		}
		catch (IOException ioexception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Scan all properties files. ",ioexception.getMessage());
		}
	}

	/** HELPER METHOD TO GET CURRENT UI BUILD VERSION  **/
	public static String getBuildVersion(String applicationURL)
	{
		String buildVersion = HttpURLConnHelperFuntions.getUIBuildVersion(applicationURL+"/build-info.json").replace("-", "_");

		return buildVersion;
	}

	/** RETURN LOCAL GRID NETWORK STATUS **/
	public static int getLocalGRIDStatusCode()
	{
		URL url = null;
		HttpURLConnection connection;
		int statusCode = 0;

		try 
		{
			url = new URL("http://localhost:4444/status");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//Set a reasonable timeout
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			statusCode = connection.getResponseCode();
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Pre-Check : Verify docker status. Docker health status. "+url+". ",exception.getMessage());
		}

		return statusCode;
	}

	/** COMMAND LINE EXECUTOR METHOD **/
	public static void executeCommand(String command) throws IOException, InterruptedException
	{
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;

		while ((line = reader.readLine()) != null) 
		{
			System.out.println(line);
		}

		process.waitFor();
		process.destroy();
	}

	/** CREATE SELENIUM GRID HUB START **/
	public static void startSeleniumHub() 
	{
		try 
		{
			String changeDirectory = "cd "+FileUtilitiesHelperFunctions.absoluteFilePath(""+FilePathConstants.FILE_SEPARATOR+"SeleniumGRIDFiles");
			executeCommand(changeDirectory);
			String seleniumHubCommand = "java -jar selenium-server-4.16.1.jar hub";
			executeCommand(seleniumHubCommand);
		} 
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("While creating selneium hub for GRID network.", exception.getMessage());
		}
	}

	/** CREATE SELENIUM GRID NODE START **/
	public static void startSeleniumNode(String portNumber)
	{
		try 
		{
			String changeDirectory = "cd "+FileUtilitiesHelperFunctions.absoluteFilePath(""+FilePathConstants.FILE_SEPARATOR+"SeleniumGRIDFiles");
			executeCommand(changeDirectory);
			String chromeDriverPath = FileUtilitiesHelperFunctions.absoluteFilePath(""+FilePathConstants.FILE_SEPARATOR+"SeleniumGRIDFiles"+FilePathConstants.FILE_SEPARATOR+"chromedriver.exe");
			String command =  "java -Dwebdriver.chrome.driver="+chromeDriverPath+" -jar selenium-server-4.16.1.jar node --port "+portNumber+"";
			executeCommand(command);
		} 
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("While creating selneium Node for GRID network.", exception.getMessage());
		}
	}

	/** BRING LOCAL SELENIUM GRID INSTANCES  **/
	public static void bringUpLocalGRID()
	{
		if(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT.equalsIgnoreCase("LocalTest.xml"))
		{
			if (getLocalGRIDStatusCode() == 200)
			{
				System.out.println("  Local Selenium GRID is already up.");
			}
			else
			{
				System.out.println("---------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Bringing up Selenium GRID local instances");
				startSeleniumHub();
				startSeleniumNode("5555");
				startSeleniumNode("5556");
				startSeleniumNode("5557");
				startSeleniumNode("5558");
				startSeleniumNode("5559");
				startSeleniumNode("5560");
			}		
		}
	}

	/** HELPER METHOD TO VERIFY SELENIUM GRID URL  **/
	public static void verifySeleniumGRIDURL(String gridURL)
	{
		URL url = null;

		try 
		{
			url = new URL(gridURL+"/status");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//Set a reasonable timeout
			connection.setConnectTimeout(15000);
			connection.setReadTimeout(15000);

			if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300)
			{
				LoggerUtil.INFO("Docker "+url+" is available.");
				System.out.println("  Pre-Check : Verify GRID URL. Selenium GRID "+url+" is available.");
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check : Verify GRID URL. Selenium GRID "+url+" is not up and running or may not be available. Automation suite will terminated.");
			}
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Pre-Check : Verify GRID URL. Selenium GRID health status. "+url+". ",exception.getMessage());
		}		
	}

	/** HELPER METHOD TO FETCH GRID URL **/
	public static String getGridURL()
	{
		String gridURL = "";

		if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("true"))
		{
			return PropertiesData.readSeleniumConfigData("GRID_LOCAL");
		}
		else if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("false"))
		{
			switch(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT)
			{			
			case "Developement.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_BVT_01");
				break;

			case "Integration.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_BVT_02");
				break;

			case "Stag.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_BVT_03");
				break;

			case "Spro.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_BVT_04");
				break;

			case "TestSuiteCloudCycle.xml" :
			case "ComponentBVT.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_DEV_01");
				break;

			case "PreProd.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_DEV_03");
				break;

			case "PreDev.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_DEV_04");
				break;

			case "Production.xml" :
				gridURL = PropertiesData.readSeleniumConfigData("GRID_DC_WMIO_DEV_05");
				break;
			}
		}

		return gridURL;

	}

	/** HELPER METHOD FOR AUTOMATION SUITE START MESSAGES  **/
	public static void automationSuiteStartMessages(String suiteXMLFileName, String sourceTenantURL)
	{		
		if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("true"))
		{			
			System.out.println("[LOCAL] : UI Automation Suite triggered for "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+", Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			SlackAPI.postMessageInSlack("[LOCAL] : UI Automation Suite triggered.\n1.Environment : "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+"\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
		}
		else if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("false"))
		{
			switch(suiteXMLFileName)
			{
			case "TestSuiteCloudCycle.xml" :
				System.out.println("UI Automation Suite triggered for "+MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT+" cloudcycle request, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				System.out.println("---------------------------------------------------------------------------------------------------------------------------");
				SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+"\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL()+"\n5.CloudCycle : "+MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT);
				break;

			case "PreDev.xml" :
				if(sourceTenantURL.contains(".stag-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for PreDevAWS-US PR-"+BaseTest.PR_NAME+" request, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : PreDevAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL()+"\n5.PR-Request : "+BaseTest.PR_NAME);
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "ComponentBVT.xml" :
				if(sourceTenantURL.contains(".stag-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ComponentBVT-PreDevAWS-US Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ComponentBVT-PreDevAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL()+"\n5.PR-Request : "+BaseTest.PR_NAME);
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Developement.xml" :
				if(sourceTenantURL.contains(".dev-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for DevelopementAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : DevelopementAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Integration.xml" :
				if(sourceTenantURL.contains("int-aw-us1.webmethods-int.io"))
				{			
					System.out.println("UI Automation Suite triggered for IntegrationAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : IntegrationAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains(" "))
				{
					/** Integration ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Stag.xml" :
				if(sourceTenantURL.contains("stag-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for StagAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : StagAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-stage.io"))
				{			
					System.out.println("UI Automation Suite triggered for StagAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : StagAZ-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				break;

			case "Spro.xml" :
				if(sourceTenantURL.contains("spro-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for SproAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : SproAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-spro.io"))
				{			
					System.out.println("UI Automation Suite triggered for SproAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : SproAZ-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				break;	

			case "PreProd.xml" :
				if(sourceTenantURL.contains("preprod-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for PreProdAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : PreProdAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-preprod.io"))
				{			
					System.out.println("UI Automation Suite triggered for PreProdAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : PreProdAZ-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				break;

			case "Production.xml" :
				if(sourceTenantURL.contains("int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAWS-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods.io") || sourceTenantURL.contains("int-az-us1.webmethods.io") || sourceTenantURL.contains("int-az-us2.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAZ-US\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-aws-de.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAWS-DE, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAWS-DE\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-az-eu.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAZ-EU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAZ-EU\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-aws-de.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAWS-DE, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAWS-DE\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-aw-au.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAWS-AU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAWS-AU\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				else if(sourceTenantURL.contains("int-az-au.webmethods.io"))
				{			
					System.out.println("UI Automation Suite triggered for ProductionAZ-AU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation Suite triggered.\n1.Environment : ProductionAZ-AU\n2.BuildVersion : "+BaseTest.UI_BUILD_VERSION+"\n3.Browser : "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+"\n4.Docker GRID : "+BaseTestUtils.getGridURL());
				}
				break;
			}
		}
	}

	/** HELPER METHOD FOR AUTOMATION SUITE END MESSAGES  **/
	public static void automationSuiteEndMessages(String suiteXMLFileName, String sourceTenantURL)
	{
		if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("true"))
		{			
			System.out.println("[LOCAL] : UI Automation completed for "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+", Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			SlackAPI.postMessageInSlack("[LOCAL] : UI Automation completed for "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+", Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
		}
		else if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("false"))
		{
			switch(suiteXMLFileName)
			{
			case "TestSuiteCloudCycle.xml" :
				System.out.println("UI Automation completed for "+MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT+" cloudcycle request, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				System.out.println("---------------------------------------------------------------------------------------------------------------------------");
				SlackAPI.postMessageInSlack("UI Automation completed for "+MavenArgumentConstants.CLOUD_CYCLE_NAME_MAVEN_ARGUMENT+" cloudcycle request, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				break;

			case "PreDev.xml" :
				if(sourceTenantURL.contains(".stag-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for PreDevAWS-US PR-"+BaseTest.PR_NAME+" request, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for PreDevAWS-US PR-"+BaseTest.PR_NAME+" request, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "ComponentBVT.xml" :
				if(sourceTenantURL.contains(".stag-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ComponentBVT_PreDevAWS-US Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ComponentBVT_PreDevAWS-US Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;				

			case "Developement.xml" :
				if(sourceTenantURL.contains(".dev-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for DevelopementAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for DevelopementAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Integration.xml" :
				if(sourceTenantURL.contains("int-aw-us1.webmethods-int.io"))
				{			
					System.out.println("UI Automation completed for IntegrationAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for IntegrationAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains(" "))
				{
					/** Integration ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Stag.xml" :
				if(sourceTenantURL.contains("stag-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for StagAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for StagAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-stage.io"))
				{			
					System.out.println("UI Automation completed for StagAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for StagAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				break;

			case "Spro.xml" :
				if(sourceTenantURL.contains("spro-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for SproAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for SproAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-spro.io"))
				{			
					System.out.println("UI Automation completed for SproAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for SproAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				break;	

			case "PreProd.xml" :
				if(sourceTenantURL.contains("preprod-int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for PreProdAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for PreProdAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-preprod.io"))
				{			
					System.out.println("UI Automation completed for PreProdAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for PreProdAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				break;

			case "Production.xml" :
				if(sourceTenantURL.contains("int-aws-us.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAWS-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods.io") || sourceTenantURL.contains("int-az-us1.webmethods.io") || sourceTenantURL.contains("int-az-us2.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAZ-US, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-aws-de.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAWS-DE, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAWS-DE, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-az-eu.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAZ-EU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAZ-EU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-aws-de.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAWS-DE, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAWS-DE, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-aw-au.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAWS-AU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAWS-AU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				else if(sourceTenantURL.contains("int-az-au.webmethods.io"))
				{			
					System.out.println("UI Automation completed for ProductionAZ-AU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
					System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					SlackAPI.postMessageInSlack("UI Automation completed for ProductionAZ-AU, Build Version: "+BaseTest.UI_BUILD_VERSION+", Browser: "+MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT+".");
				}
				break;
			}
		}
	}

	/** HELPER METHOD TO INITIALIZE PAGEFACTORY WITH PAGE LOCATORS FOR WEBPAGE  **/
	@SuppressWarnings("deprecation")
	public static void initializePageFactory(WebDriver tempDriver,String locatorClassName) 
	{  	
		@SuppressWarnings("rawtypes")
		Class testCaseClass;
		Object objectTestCase;

		try
		{
			testCaseClass = Class.forName(locatorClassName);
			objectTestCase = testCaseClass.newInstance();
			PageFactory.initElements(tempDriver,objectTestCase);
		}
		catch (ClassNotFoundException classNotFoundException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While initializing page factory for all webelements.",classNotFoundException.getMessage());
		}
		catch (InstantiationException instantiationException)
		{		
			CommonExceptionHandling.captureExceptionAndTerminateTest("While initializing page factory for all webelements. ",instantiationException.getMessage());
		}
		catch (IllegalAccessException illegalaccessException)
		{		
			CommonExceptionHandling.captureExceptionAndTerminateTest("While initializing page factory for all webelements. ",illegalaccessException.getMessage());
		}
	}

	/** GENERATE RANDOM STRING **/
	public static String generateString(int count) 
	{
		StringBuilder builder = new StringBuilder();

		while (count-- != 0)
		{
			int character = (int)(Math.random()*TestDataConstants.ALPHA_NUMERIC_STRING.length()); 
			builder.append(TestDataConstants.ALPHA_NUMERIC_STRING.charAt(character));
		} 

		return builder.toString();

	}
}