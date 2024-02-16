/**
 *  This class consists all constants required for FileUtilitiesHelperFunctions class and Maven Arguments.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.Constants;

import com.webMethods.io.Integration.FileUtilities.FileUtilitiesHelperFunctions;

public class FilePathConstants 
{
	public static final int BUFFERSIZE = 4096;

	public static final String LINE_FEED = "\r\n";

	public static long BYTESREAD = 0;

	/** FILE SEPARATOR VARIABLE COMMON **/
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/** PROJECT ROOT DIRECTORY FOLDER PATH **/
	public static final String ROOT_DIRECTORY_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(FILE_SEPARATOR);

	/** TEST AUTOMATION RESULTS FOLDER & FILES PATHS **/
	public static final String TEST_AUTOMATION_RESULTS_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Automation Results");

	public static final String APPLICATION_TEST_EXECUTION_LOGS_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Automation Results"+FILE_SEPARATOR+"Application Test Execution Logs");

	public static final String LOGFILE_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Automation Results"+FILE_SEPARATOR+"Application Test Execution Logs"+FILE_SEPARATOR+"Logfile.log");

	public static final String REPORT_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Automation Results"+FILE_SEPARATOR+"Report");

	public static final String TEST_FAILED_SCREEN_SHOTS_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Automation Results"+FILE_SEPARATOR+"Test Failed Screen Shots");

	/** TEST CONFIGURATIONS FOLDER & FILES PATHS **/
	public static final String TEST_CONFIGURATIONS_DATA_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Configurations Data");

	public static final String RUN_CONFIGURATIONS_PROPERTIES_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Configurations Data"+FILE_SEPARATOR+"run-configurations.properties");

	public static final String SELENIUM_GRID_CONFIGURATIONS_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Configurations Data"+FILE_SEPARATOR+"selenium-grid-configurations.properties");

	/** TEST DOWNLOADED FOLDER & FILES PATHS **/
	public static final String TEST_DOWNLOADED_FILES_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test Downloaded Files");

	/** TEST INPUT & OUTPUT DATA FOLDER & FILES PATHS **/ //TODO
	public static final String TEST_INPUT_DATA_PROPERTIES_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Input Data"+FILE_SEPARATOR+""+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+""+FILE_SEPARATOR+"Data.Properties");

	public static final String TEST_INPUT_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Input Data"+FILE_SEPARATOR+""+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+"");

	public static final String TEST_ASSERTION_DATA_PROPERTIES_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Output Data Assertion"+FILE_SEPARATOR+""+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+""+FILE_SEPARATOR+"Data.Properties");

	/** FOLDER PATH **/
	public static final String TEST_INPUT_OUTPUT_DATA_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data");

	public static final String TEST_INPUT_DATA_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Input Data"+FILE_SEPARATOR+""+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+"");

	public static final String TEST_OUTPUT_DATA_ASSERTION_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Output Data Assertion"+FILE_SEPARATOR+""+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+"");

	/** TEST TENANT DATA FOLDER & FILES PATHS
	public static final String TEST_TENANT_DATA_FOLDER_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Tenant Data");

	public static final String TEST_TENANT_DATA_PROPERTIES_FILE_PATH = FileUtilitiesHelperFunctions.absoluteFilePath(""+FILE_SEPARATOR+"Test InputOutput Data"+FILE_SEPARATOR+"Test Tenant Data"+FILE_SEPARATOR+"Data.Properties");**/
}