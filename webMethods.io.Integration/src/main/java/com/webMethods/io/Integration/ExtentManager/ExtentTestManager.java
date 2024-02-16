/**
 *  This class get the extent reports , start-test and end-test.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.ExtentManager;

import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager
{	
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	static ExtentReports extent = ExtentManager.getInstance();

	public static ExtentTest test;

	/** HELPER METHOD TO GET EXTENT TEST **/
	public static synchronized ExtentTest getTest() 
	{
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	/** HELPER METHOD TO END EXTENT TEST **/
	public static synchronized void endTest() 
	{
		extent.flush();
	}

	/** HELPER METHOD TO START AND LOG TEST DATA TO EXTENT TEST **/
	public static synchronized ExtentTest startTest(String testName)
	{
		test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}