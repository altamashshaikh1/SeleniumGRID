/**
 * This Java class serves as a valuable resource for implementing and customizing TestNG listeners in your automation framework. 
 * It provides a comprehensive set of helper methods and utilities to enhance test case management, reporting, and automation execution control through TestNG's powerful listener architecture.
 * The methods within this class offer flexibility in integrating listeners for various aspects of test execution, such as test start and finish events, suite-level actions, and reporting enhancements. You can easily adapt and extend these methods to tailor your automation suite's behavior to specific project requirements.
 * These listener-related utilities are indispensable for building robust and maintainable test automation frameworks, ensuring effective test case management and comprehensive reporting for your software testing initiatives.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.Listeners;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.webMethods.io.Integration.Constants.TestDataConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentManager;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import org.testng.ITestListener;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;

public class ListenersTestNG extends TestListenerAdapter implements ITestListener
{		
	/** private static XmlSuite xmlSuite = new XmlSuite(); **/

	//	private boolean validateTestNGConfigurations(ITestContext context)
	//	{
	//		// TODO: Implement validation logic for TestNG configurations
	//		return true; // Return true if configurations are valid, false otherwise
	//	}
	//
	//	private boolean checkTestAnnotations(ITestContext context) 
	//	{
	//		// TODO: Implement logic to check @Test annotations
	//		return true; // Return true if all annotations are valid, false otherwise
	//	}
	//
	//	private boolean checkDependOnMethods(ITestContext context) 
	//	{
	//		// TODO: Implement logic to check dependOnMethods
	//		return true; // Return true if all methods have dependOnMethods, false otherwise
	//	}
	//
	//	private boolean validateListenersClassName(ITestContext context) 
	//	{
	//		// TODO: Implement validation logic for listeners class name
	//		return true; // Return true if class name is valid, false otherwise
	//	}
	//
	//	private boolean hasDuplicateTestNames(ITestContext context)
	//	{
	//		// TODO: Implement logic to check for duplicate test names
	//		return false; // Return true if duplicates are found, false otherwise
	//	}

	/**
	 * @implNote : This method check TestNG, Maven via command line configuration failure.
	 *             			  
	 */
	@Override //TODO
	public void onStart(ITestContext context)
	{
		// MISSING MAVEN PROFILE ID
		//String mavenProfile = System.getProperty("maven.profile.id");

		//if (mavenProfile == null || mavenProfile.isEmpty()) 
		//{
		//// Handle missing Maven profile ID
		//System.out.println("Missing Maven profile ID. Sending notification...");
		// Add your notification logic here
		//}

		// INVALID TESTNG CONFIGURATIONS
		//if (!validateTestNGConfigurations(context))
		//{
		//System.out.println("Invalid TestNG configurations. Sending notification...");
		// Add your notification logic here
		//}

		// CHECK IF @Test ANNOTATIONS HAVE EMPTY testName OR description
		//if (!checkTestAnnotations(context)) 
		//{
		//System.out.println("Some @Test annotations have empty testName or description. Sending notification...");
		// Add your notification logic here
		//}

		// CHECK IF dependOnMethods ATTRIBUTE IS MISSING
		//if (!checkDependOnMethods(context)) 
		//{
		//System.out.println("dependOnMethods attribute is missing in some test methods. Sending notification...");
		// Add your notification logic here
		//}

		// INVALID CONFIG: INVALID LISTENERS CLASS NAME
		//if (!validateListenersClassName(context)) 
		//{
		//System.out.println("Invalid TestNG listeners class name. Sending notification...");
		// Add your notification logic here
		//}

		// CHECK FOR DUPLICATE TEST NAMES
		//if (hasDuplicateTestNames(context)) 
		//{
		//System.out.println("Duplicate test names found. Sending notification...");
		// Add your notification logic here
		//}
	}

	/**
	 * @implNote : This method check TestNG configuration failure.
	 *             			  
	 */
	@Override
	public void onConfigurationFailure(ITestResult result) 
	{
		// INVALID TESTNG CONFIGURATIONS TODO
		//System.out.println("Configuration failure detected. Sending notification...");
		// Add your notification logic here
	}

	/**
	 * @implNote : This method is invoked before any tests method is invoked.  
	 *             This can be used to indicate that the particular test method has been started.
	 */
	@Override
	public void onTestStart(ITestResult Result)
	{		
		TestDataConstants.totalTestCount++;
		LoggerUtil.INFO("TEST-NG LISTENERS onTestStart(ITestResult Result) METHOD : TEST CASE "+Result.getMethod().getDescription()+" STARTED.");
		ExtentTestManager.startTest(Result.getMethod().getDescription());

		for (String group : Result.getMethod().getGroups())
		{
			ExtentTestManager.test.assignCategory(group);
		}
	}

	/**
	 * @implNote : This method is invoked after all tests methods gets executed.
	 *             This can be used to store information of all the tests that were run.
	 */
	@Override
	public void onFinish(ITestContext context) 
	{ 
		LoggerUtil.INFO("TEST CASE "+context.getName()+" EXECUTED.");
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	/**
	 * @implNote : This method is invoked when any test method gets succeeded. 
	 *             This can be used to indicate that the particular test method has successfully finished its execution.
	 */
	@Override
	public void onTestSuccess(ITestResult Result)
	{
		TestDataConstants.totalSuccessTests++;
		LoggerUtil.INFO("TEST "+Result.getMethod().getDescription()+" : PASSED");
		System.out.println("");
		System.out.println("TEST "+Result.getMethod().getDescription()+" : PASSED");
		System.out.println("");
	}

	/**
	 * @implNote : This method is invoked when any test method gets failed. 
	 *             This can be used to indicate that the particular test method has been failed.
	 * 			   You can create an event of taking a screenshot which would show where the test has been failed.
	 */
	@Override
	public void onTestFailure(ITestResult Result) 
	{
		TestDataConstants.totalFailureTests++;
		LoggerUtil.ERROR("TEST "+Result.getMethod().getDescription()+" : FAILED");
		System.out.println("");
		System.out.println("TEST "+Result.getMethod().getDescription()+" : FAILED");
		System.out.println("");
	}

	/**
	 * @implNote : This method is invoked when each test method is skipped. 
	 *             This can be used to indicate that the particular test method has been skipped.
	 */
	@Override
	public void onTestSkipped(ITestResult Result) 
	{
		TestDataConstants.totalSkipTests++;
		ExtentTestManager.getTest().skip("TEST "+Result.getMethod().getDescription()+" Test Case : SKIPPED");
		LoggerUtil.WARN("TEST "+Result.getMethod().getDescription()+" Test Case : SKIPPED");
		System.out.println("");
		System.out.println("TEST "+Result.getMethod().getDescription()+" : SKIPPED");
		System.out.println("");
	}
}