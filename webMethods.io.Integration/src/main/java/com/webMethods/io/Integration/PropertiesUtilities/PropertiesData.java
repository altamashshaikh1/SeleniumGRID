/**
 * This Java class serves as a dedicated utility for efficiently extracting data from properties files. 
 * It encapsulates a set of methods designed to simplify the process of reading and retrieving key-value pairs from configuration files, ensuring seamless integration of external configuration data into your application.
 * The methods within this class provide a flexible and robust approach to handling properties files. You can use them to retrieve configuration settings, environment variables, or any other data stored in a key-value format within properties files.
 * Whether you need to load and access properties from a single file or manage multiple configuration sources dynamically, this utility class offers a convenient and consistent interface for accessing properties data.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.PropertiesUtilities;

import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PropertiesData 
{	
	/** HELPER METHOD TO READ CONFIGURATION DATA **/
	public static String readConfigData(String propertyName)
	{
		String configData = null;
		Properties properties;		
		InputStream input;

		try
		{
			input = new FileInputStream(FilePathConstants.RUN_CONFIGURATIONS_PROPERTIES_FILE_PATH);
			properties = new Properties();
			properties.load(input);
			configData = properties.getProperty(propertyName);

			if(properties.containsKey(propertyName) && !properties.isEmpty() && !configData.isBlank() && configData!=null)
			{
				return configData;
			}
			else
			{				
				CommonFailureHandling.captureFailureAndTerminateTest("Value for "+propertyName+" is null in "+FilePathConstants.RUN_CONFIGURATIONS_PROPERTIES_FILE_PATH+" file");
			}			
		}
		catch (FileNotFoundException fileNotFoundException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data.",fileNotFoundException.getMessage());
		}
		catch(IOException ioexception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data.",ioexception.getMessage());
		}

		return configData;

	}

	/** HELPER METHOD TO READ CONFIGURATION DATA **/
	public static String readSeleniumConfigData(String propertyName)
	{
		String seleniumConfigData = null;
		Properties properties;		
		InputStream input;

		try
		{
			input = new FileInputStream(FilePathConstants.SELENIUM_GRID_CONFIGURATIONS_FILE_PATH);
			properties = new Properties();
			properties.load(input);
			seleniumConfigData = properties.getProperty(propertyName);

			if(properties.containsKey(propertyName) && !properties.isEmpty() && !seleniumConfigData.isBlank() && seleniumConfigData!=null)
			{
				return seleniumConfigData;
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Value for "+propertyName+" is null in "+FilePathConstants.SELENIUM_GRID_CONFIGURATIONS_FILE_PATH+" in file");
			}			
		}
		catch (FileNotFoundException fileNotFoundException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data. ",fileNotFoundException.getMessage());
		}
		catch(IOException ioexception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data. ",ioexception.getMessage());
		}

		return seleniumConfigData;

	}

	/** HELPER METHOD TO READ INPUT DATA FROM PROPERTIES FILE **/
	public static String readInputData(String propertyName)
	{
		String inputData = null;
		Properties properties;		
		InputStream input;

		try
		{
			input = new FileInputStream(FilePathConstants.TEST_INPUT_DATA_PROPERTIES_FILE_PATH);
			properties = new Properties();
			properties.load(input);
			inputData = properties.getProperty(propertyName);

			if(properties.containsKey(propertyName) && !properties.isEmpty() && !inputData.isBlank() && inputData!=null)
			{
				return inputData;
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Value for "+propertyName+" is null in "+FilePathConstants.TEST_INPUT_DATA_PROPERTIES_FILE_PATH+" as inputdata in file");
			}			
		}
		catch (FileNotFoundException fileNotFoundException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data. ",fileNotFoundException.getMessage());
		}
		catch(IOException ioexception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data. ",ioexception.getMessage());
		}

		return inputData;

	}

	/** HELPER METHOD TO READ ASSERTION DATA FROM PROPERTIES FILE **/
	public static String readAssertionData(String propertyName)
	{
		String assertionData = null;
		Properties properties;		
		InputStream input;

		try
		{
			input = new FileInputStream(FilePathConstants.TEST_ASSERTION_DATA_PROPERTIES_FILE_PATH);
			properties = new Properties();
			properties.load(input);
			assertionData = properties.getProperty(propertyName);

			if(properties.containsKey(propertyName) && !properties.isEmpty() && !assertionData.isBlank() && assertionData!=null)
			{
				return assertionData;
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Value for "+propertyName+" is null in "+FilePathConstants.TEST_ASSERTION_DATA_PROPERTIES_FILE_PATH+" as inputdata in file");
			}			
		}
		catch (FileNotFoundException fileNotFoundException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data. ",fileNotFoundException.getMessage());
		}
		catch(IOException ioexception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("While reading value for "+propertyName+" from properties data. ",ioexception.getMessage());
		}

		return assertionData;

	}
}