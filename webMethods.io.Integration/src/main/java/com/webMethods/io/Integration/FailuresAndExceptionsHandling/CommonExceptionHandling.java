/**
 *  Class includes implementation for handling common exceptions for overall framework.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.FailuresAndExceptionsHandling;

import com.webMethods.io.Integration.AutomationUtilitiesMethods.SlackAPI;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.SeleniumUtilities.SeleniumTakeScreenShot;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.Constants.TestDataConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonExceptionHandling
{
	/** COMMON EXCEPTIONS FOR ALL @AfterSuite ANNOTED METHODS**/
	public static void captureException(String descriptionMessage, String exceptionMessage)
	{
		System.out.println("!!! EXCEPTION !!! : "+descriptionMessage);
		SlackAPI.postMessageInSlack("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
		LoggerUtil.ERROR("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
	}

	/** COMMON EXCEPTIONS & TERMINATE TEST CASE EXECUTIONS **/
	public static void captureExceptionAndTerminateTest(String descriptionMessage, String exceptionMessage, WebDriver tempWebDriver)
	{
		System.out.println("!!! EXCEPTION !!! : "+descriptionMessage);
		ExtentTestManager.getTest().log(Status.FAIL, descriptionMessage);
		ExtentTestManager.getTest().log(Status.INFO, "<pre>" + exceptionMessage + "</pre>");
		// CHAT GPT TODO
		LoggerUtil.ERROR("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
		SeleniumTakeScreenShot.takeScreenshot(tempWebDriver);
		Assert.fail("!!! EXCEPTION !!! : "+descriptionMessage);	
	}

	/** COMMON FAILURES & TERMINATE TESTS (WITHOUT TAKESCREEN-SHOT) **/
	public static void captureExceptionAndTerminateTest(String descriptionMessage, String exceptionMessage)
	{
		System.out.println("!!! EXCEPTION !!! : "+descriptionMessage);
		ExtentTestManager.getTest().log(Status.FAIL, descriptionMessage);
		ExtentTestManager.getTest().log(Status.INFO, "<pre>" + exceptionMessage + "</pre>");
		// CHAT GPT TODO
		LoggerUtil.ERROR("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
		Assert.fail("!!! EXCEPTION !!! : "+descriptionMessage);
	}

	/** COMMON EXCEPTIONS FOR ALL @BeforeSuite ANNOTED METHODS**/
	public static void captureExceptionAndTerminateSuite(String descriptionMessage, String exceptionMessage)
	{
		System.out.println("!!! EXCEPTION !!! : "+descriptionMessage);
		SlackAPI.postMessageInSlack("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
		LoggerUtil.ERROR("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
		System.exit(1);
	}

	/** COMMON EXCEPTIONS & CONTINUE TEST**/
	public static void captureExceptionAndContinueTest(WebDriver tempWebDriver, String descriptionMessage, String exceptionMessage,SoftAssertWrapper tempSoftAssertWrapper)
	{
		TestDataConstants.totalFailureTests++;
		System.out.println("!!! EXCEPTION !!! : "+descriptionMessage);
		ExtentTestManager.getTest().log(Status.FAIL, descriptionMessage);
		ExtentTestManager.getTest().log(Status.INFO, "<pre>" + exceptionMessage + "</pre>");
		// CHAT GPT TODO
		LoggerUtil.ERROR("!!! EXCEPTION !!! : "+descriptionMessage+" -> "+exceptionMessage);
		SeleniumTakeScreenShot.takeScreenshot(tempWebDriver);
		tempSoftAssertWrapper.getSoftAssertions().fail("!!! EXCEPTION !!! : "+descriptionMessage);
	}
}