/**
 *  This class consists page scroll operation implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException; 
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling; 

public class SeleniumPageScrollUtils 
{
	public static void scrollPageUp(WebDriver tempDriver, JavascriptExecutor javascriptexecutor, String description)
	{
		try
		{
			javascriptexecutor = (JavascriptExecutor)tempDriver;
			javascriptexecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight);");			
			ExtentTestManager.getTest().log(Status.PASS,"Scroll page up");
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void scrollPageDown(WebDriver tempDriver, JavascriptExecutor javascriptExecutor, String description)
	{
		try
		{
			javascriptExecutor = (JavascriptExecutor)tempDriver;
			javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			ExtentTestManager.getTest().log(Status.PASS,"Scroll page down");
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void scrollByVisibleElement(WebElement element,WebDriver tempDriver, JavascriptExecutor javascriptExecutor, String description)
	{
		try
		{
			javascriptExecutor = (JavascriptExecutor)tempDriver;
			javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
			ExtentTestManager.getTest().log(Status.PASS,"Scroll page till element is visible");
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}
}