/**
 *  This class consists of all java script executor implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class SeleniumJavaScriptExecutorUtils 
{	
	public static void javaScriptExecutor(String script, WebDriver tempDriver, String description, SoftAssertWrapper tempSoftAssert) 
	{
		JavascriptExecutor javascriptExecutor;

		try 
		{
			javascriptExecutor = (JavascriptExecutor) tempDriver;
			javascriptExecutor.executeScript(script);
			ExtentTestManager.getTest().pass(description);
		} 
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void javaScriptExecutor(String script, WebDriver tempDriver, String description) 
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)tempDriver;		
			js.executeScript(script);
			ExtentTestManager.getTest().log(Status.PASS	, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}
}