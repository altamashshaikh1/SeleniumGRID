/**
 * This Java class serves as a comprehensive utility for various file handling operations. 
 * It encapsulates a collection of methods to simplify and streamline tasks related to file manipulation, such as reading, writing, copying, moving, and deleting files and directories.
 * The methods within this class are designed to provide a versatile and robust set of tools for developers, making it easier to manage files and directories in both local and remote file systems.
 * Whether you need to read data from a file, write data to a new file, copy files between directories, move files to different locations, or safely delete files and directories, this utility class offers a convenient and reliable solution.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.FileUtilities;

import java.io.File; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.io.FileReader;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipOutputStream;
import java.util.ArrayList; 
import java.util.zip.ZipEntry;
import org.json.simple.parser.JSONParser;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.SlackAPI;
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.SeleniumUtilities.BaseTest;

public class FileUtilitiesHelperFunctions
{
	public static JSONParser parser = new JSONParser();

	/** HELPER METHOD TO UPDATE WORKFLOW PAYLOAD PARSE AND RETURN STRING DATA AS PAYLOAD **/
	public static String readSampleJSONPayload(String fileName)
	{
		String data = null;

		try
		{
			Object obj = parser.parse(new FileReader(FilePathConstants.TEST_INPUT_DATA_FOLDER_PATH+FilePathConstants.FILE_SEPARATOR+fileName));
			data = obj.toString();
		}
		catch(Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading JSON data required by API as payload.", exception.getMessage());
		}

		return data;

	}

	/** HELPER METHOD TO RETURN CURRENT PLATFORM NAME **/
	public static String getCurrentPlatform()
	{
		String operatingSystemName = null;

		operatingSystemName = System.getProperty("os.name");

		return operatingSystemName;
	}

	/** HELPER METHOD TO ABSOLUTE FILE RELATIVE PATH **/
	public static File fileAbsolute(String relativeFilePath)
	{
		File file = null;

		if(getCurrentPlatform().contains("Windows"))
		{
			file = new File(System.getProperty("user.dir")+relativeFilePath);
		}
		else if(getCurrentPlatform().contains("Linux")||getCurrentPlatform().contains("nux")||getCurrentPlatform().contains("aix"))
		{
			file = new File(System.getProperty("user.dir")+relativeFilePath);
		}
		else if(getCurrentPlatform().contains("mac"))
		{
			file = new File(System.getProperty("user.home")+relativeFilePath);
		}

		return file;

	}

	/** HELPER METHOD TO ABSOLUTE FILEPATH **/
	public static String absoluteFilePath(String relativeFilePath)
	{
		return fileAbsolute(relativeFilePath).getAbsolutePath();
	}

	/** HELPER METHOD TO GET CURRENT DATE AS STRING **/
	public static String getCurrentDate()
	{
		LocalDate currentDate;
		String dayOfWeek;
		int dayOfMonth;
		String dayOfMonthString;
		String month;
		int year;
		String formattedDate = "";

		try
		{
			currentDate = LocalDate.now();
			dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			dayOfMonth = currentDate.getDayOfMonth();
			dayOfMonthString = dayOfMonth + getDayOfMonthSuffix(dayOfMonth);
			month = currentDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
			year = currentDate.getYear();
			formattedDate = dayOfWeek + " " + dayOfMonthString + " " + month + " " + year;
		}
		catch(Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Get current data as string.", exception.getMessage());
		}

		return formattedDate;

	}

	/** HELPER METHOD TO GET DAY OF MONTH AS SUFFIX **/
	public static String getDayOfMonthSuffix(int day) 
	{
		if (day >= 11 && day <= 13) 
		{
			return "th";
		}

		switch (day % 10) 
		{
		case 1:
			return "st";

		case 2:
			return "nd";

		case 3:
			return "rd";

		default:
			return "th";
		}
	}

	/** HELPER METHOD TO RETURN AUTOMATION REPORT FILE NAME (.html) **/
	public static String generateReportFileName()
	{
		String reportName = null;

		if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("true"))
		{
			reportName = "[Local] UI Automation Report.html";
		}
		else if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("false"))
		{
			switch(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT)
			{
			case "ComponentBVT" :
				reportName = "ComponentBVT_"+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+".html"; 
				break;

			case "PreDev" :
				reportName = ""+BaseTest.PR_NAME+" PreDevAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+".html";
				break;

			case "Developement" :
			case "Integration" :
			case "Stag" :
			case "Spro" :
			case "PreProd" :
			case "Production" :
				reportName = "UI Automation Report.html";
				break;
			}
		}
		
		return reportName;

	}

	/** HELPER METHOD TO SANITIZE STRING **/
	public static String sanitizeFileName(String input)
	{
		return input.replaceAll("[^a-zA-Z0-9.-]", "_");
	}

	/** HELPER METHOD TO RETURN ZIP FILE NAME (.zip) **/
	public static String generateZipFileName(String environmentName, String sourceTenantURL,String cloudCycleName)
	{
		String zipFileName = null;

		if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("true"))
		{
			zipFileName = ""+environmentName+" [LOCAL_TEST] UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
		}
		else if(MavenArgumentConstants.LOCAL_MAVEN_ARGUMENT.equalsIgnoreCase("false"))
		{
			switch(MavenArgumentConstants.SUITE_XML_FILE_MAVEN_ARGUMENT)
			{
			case "PreDev.xml" :
				if(sourceTenantURL.contains(".stag-int-aws-us.webmethods.io"))
				{			
					zipFileName = "PR"+BaseTest.PR_NAME+" PreDevAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "ComponentBVT.xml" :
				if(sourceTenantURL.contains(".stag-int-aws-us.webmethods.io"))
				{			
					zipFileName = "ComponentBVT"+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else
				{
					/** PreDev ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;					

			case "Developement.xml" :			
				if(sourceTenantURL.contains(".dev-int-aws-us.webmethods.io"))
				{			
					zipFileName = "DevelopementAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else
				{
					/** Developement ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Integration.xml" :
				if(sourceTenantURL.contains("int-aw-us1.webmethods-int.io"))
				{
					zipFileName = "IntegrationAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains(" "))
				{
					/** Integration ENVIRONMENT NOT SUPPORTED FOR AZURE **/
				}
				break;

			case "Stag.xml" :
				if(sourceTenantURL.contains("stag-int-aws-us.webmethods.io"))
				{
					zipFileName = "StagAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-stage.io"))
				{
					zipFileName = "StagAZ-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				break;

			case "Spro.xml" :
				if(sourceTenantURL.contains("spro-int-aws-us.webmethods.io"))
				{
					zipFileName = "SproAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-spro.io"))
				{
					zipFileName = "SproAZ-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				break;	

			case "PreProd.xml" :
				if(sourceTenantURL.contains("preprod-int-aws-us.webmethods.io"))
				{			
					zipFileName = "PreProdAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods-preprod.io"))
				{
					zipFileName = "PreProdAZ-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				break;

			case "Production.xml" :
				if(sourceTenantURL.contains("int-aws-us.webmethods.io"))
				{			
					zipFileName = "ProductionAWS-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-az-us.webmethods.io") || sourceTenantURL.contains("int-az-us1.webmethods.io") || sourceTenantURL.contains("int-az-us2.webmethods.io"))
				{
					zipFileName = "ProductionAZ-US Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-aws-de.webmethods.io"))
				{
					zipFileName = "ProductionAWS-DE Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-az-eu.webmethods.io"))
				{
					zipFileName = "ProductionAZ-EU Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-aws-de.webmethods.io"))
				{
					zipFileName = "ProductionAWS-DE Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-aw-au.webmethods.io"))
				{
					zipFileName = "ProductionAWS-AU Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				else if(sourceTenantURL.contains("int-az-au.webmethods.io"))
				{
					zipFileName = "ProductionAZ-AU Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				}
				break;

			case "TestSuiteCloudCycle.xml" :
				zipFileName = cloudCycleName+" Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				break;

			case "TestSuiteInfraUpdate.xml" :
				zipFileName = "InfraIndependent "+environmentName+" Report UI "+BaseTest.UI_BUILD_VERSION+" "+getCurrentDate()+" AutomationData.zip";
				break;
			}	
		}

		return zipFileName;
	}

	/** HELPER METHOD TO CREATE FOLDERS **/
	public static void createFolder(String folderPath)
	{
		File folder;

		try
		{
			folder = new File(folderPath); 

			if (folder.mkdir() == true) 
			{
				LoggerUtil.INFO("Folder "+folderPath+" already exists.");
				System.out.println("Pre-Check : Create folder. Folder "+folderPath+" already exists.");
			}
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Pre-Check : Create folder. While creating "+folderPath+" folder to store automation data.",exception.getMessage());
		}
	}

	/** HELPER METHOD FOR ZIP CREATION **/
	public static void zip(String[] files, String destZipFile) throws IOException
	{
		List<File> listFiles = new ArrayList<File>();

		for (int i = 0; i < files.length; i++)
		{
			listFiles.add(new File(files[i]));
		}

		zip(listFiles, destZipFile);
	}

	/** HELPER METHOD FOR ZIP CREATION **/
	public static void zip(List<File> listFiles, String destZipFile) throws IOException
	{
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));

		for (File file : listFiles)
		{
			if (file.isDirectory())
			{
				zipDirectory(file, file.getName(), zos);
			}
			else
			{
				zipFile(file, zos);
			}
		}

		zos.flush(); 
		zos.close();
	}

	/** HELPER METHOD FOR ZIP CREATION **/
	public static void zipDirectory(File folder,String parentFolder,ZipOutputStream zos) throws IOException
	{
		for(File file : folder.listFiles())
		{
			if(file.isDirectory())
			{
				zipDirectory(file,parentFolder+"/"+file.getName(),zos);
				continue;
			}

			zos.putNextEntry(new ZipEntry(parentFolder+"/"+file.getName()));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			FilePathConstants.BYTESREAD = 0;
			byte[] bytesIn = new byte[FilePathConstants.BUFFERSIZE];
			int read = 0;

			while((read=bis.read(bytesIn))!=-1)
			{
				zos.write(bytesIn,0,read);
				FilePathConstants.BYTESREAD+=read;
			}

			zos.closeEntry();
			bis.close();
		}
	}

	/** HELPER METHOD FOR ZIP CREATION **/
	public static void zipFile(File file, ZipOutputStream zos) throws IOException
	{
		zos.putNextEntry(new ZipEntry(file.getName()));

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		FilePathConstants.BYTESREAD = 0;
		byte[] bytesin = new byte[FilePathConstants.BUFFERSIZE];
		int read = 0;

		while ((read = bis.read(bytesin)) != -1)
		{
			zos.write(bytesin, 0, read);
			FilePathConstants.BYTESREAD += read;
		}

		zos.closeEntry();
		bis.close();
	}

	/** HELPER METHOD FOR ZIP CREATION **/
	public static void createResultsZipFile(String testAutomationResultsFolder, String zipFilePath)
	{
		String[] automationResultFolder = {testAutomationResultsFolder};

		try
		{
			System.out.println("Creating .zip file for automation results with reports and browser data");
			zip(automationResultFolder, zipFilePath);
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureException("While creating .zip file storing all automation executions data.",exception.getMessage());
		}
	}

	/** HELPER METHOD TO CHECK FILE EXISTENCE IN FOLDER **/
	public static void fileExistsAndUploadReportToSlack(String rootDirectoryPath, String zipFileName, String description)
	{
		List<File> matchingFiles = findFilesByExtension(new File(FilePathConstants.ROOT_DIRECTORY_PATH),".zip");

		if (matchingFiles.isEmpty()) 
		{
			System.out.println("No .zip files found in the directory: " + rootDirectoryPath);
		} 
		else 
		{
			for (File file : matchingFiles)
			{
				if(file.getName().equalsIgnoreCase(zipFileName))
				{
					SlackAPI.uploadAndShareReportFile(zipFileName);
				}
				else
				{
					CommonFailureHandling.captureFailure("Automation results .zip file "+rootDirectoryPath+file.getName()+" not found or created.");
				}
			}
		}
	}

	/** HELPER METHOD FILE BY EXTENSIONS **/
	public static List<File> findFilesByExtension(File directory, String targetExtension) 
	{
		List<File> matchingFiles = new ArrayList<>();

		if (directory.isDirectory()) 
		{
			File[] files = directory.listFiles();

			if (files != null) 
			{
				for (File file : files) 
				{
					if (file.isDirectory()) 
					{
						matchingFiles.addAll(findFilesByExtension(file, targetExtension));
					} 
					else if (file.isFile() && file.getName().toLowerCase().endsWith(targetExtension)) 
					{
						matchingFiles.add(file);
					}
				}
			}
		}

		return matchingFiles;

	}

	/** HELPER METHOD TO CHECK THE FILE AFTER DOWNLOAD **/
	public static void fileExists(String filePath,String description)
	{
		try
		{
			ExtentTestManager.getTest().pass(filePath+" downloaded");

			File f = new File(filePath);

			if(f.exists())
			{
				ExtentTestManager.getTest().pass(filePath+" downloaded");
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("File "+filePath+" not found or created.");
			}	
		}
		catch(Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description+". ",exception.getMessage());
		}
	}
}