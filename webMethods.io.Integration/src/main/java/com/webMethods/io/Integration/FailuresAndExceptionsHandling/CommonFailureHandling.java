/**
 *  Class includes implementation for handling common failures for overall framework.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.FailuresAndExceptionsHandling;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.SlackAPI;
import com.webMethods.io.Integration.Constants.TestDataConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumTakeScreenShot;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class CommonFailureHandling 
{
	/** COMMON FAILURES FOR ALL @AfterSuite ANNOTED METHODS**/
	public static void captureFailure(String failureMessage)
	{
		System.out.println("!!! FAILURE !!! : "+failureMessage);
		SlackAPI.postMessageInSlack("!!! FAILURE !!! : "+failureMessage);
		LoggerUtil.ERROR("!!! FAILURE !!! : "+failureMessage);
	}

	/** COMMON FAILURES & TERMINATE TEST CASE EXECUTIONS (WITH TAKESCREEN-SHOT) **/
	public static void captureFailureAndTerminateTest(String failureMessage, WebDriver tempWebDriver)
	{
		System.out.println("!!! FAILURE !!! : "+failureMessage);
		ExtentTestManager.getTest().log(Status.FAIL,failureMessage);
		// CHAT GPT TODO
		LoggerUtil.ERROR("!!! FAILURE !!! : "+failureMessage);
		SeleniumTakeScreenShot.takeScreenshot(tempWebDriver);
		Assert.fail("!!! FAILURE !!! : "+failureMessage);
	}

	/** COMMON FAILURES & TERMINATE TESTS (WITHOUT TAKESCREEN-SHOT) **/
	public static void captureFailureAndTerminateTest(String failureMessage)
	{
		System.out.println("!!! FAILURE !!! : "+failureMessage);
		ExtentTestManager.getTest().log(Status.FAIL,failureMessage);
		// CHAT GPT TODO
		LoggerUtil.ERROR("!!! FAILURE !!! : "+failureMessage);
		Assert.fail("!!! FAILURE !!! : "+failureMessage);
	}

	/** COMMON FAILURES FOR ALL @BeforeSuite ANNOTED METHODS**/
	public static void captureFailureAndTerminateSuite(String failureMessage)
	{
		System.out.println("!!! FAILURE !!! : "+failureMessage);
		SlackAPI.postMessageInSlack("!!! FAILURE !!! : "+failureMessage);
		LoggerUtil.ERROR("!!! FAILURE !!! : "+failureMessage);
		System.exit(1);
	}

	/** COMMON FAILURES & CONTINUE TEST**/
	public static void captureFailureAndContinueTest(WebDriver tempWebDriver, String failureMessage, SoftAssertWrapper tempSoftAssertWrapper)
	{
		TestDataConstants.totalFailureTests++;
		System.out.println("!!! FAILURE !!! : "+failureMessage);
		ExtentTestManager.getTest().log(Status.FAIL,failureMessage);
		// CHAT GPT TODO
		LoggerUtil.ERROR("!!! FAILURE !!! : "+failureMessage);
		SeleniumTakeScreenShot.takeScreenshot(tempWebDriver);
		tempSoftAssertWrapper.getSoftAssertions().fail("!!! FAILURE !!! : "+failureMessage);
	}
}