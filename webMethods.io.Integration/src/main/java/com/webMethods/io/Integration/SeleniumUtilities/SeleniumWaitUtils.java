/**
 *  This class consists of all selenium wait implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.Constants.WebDriverWaitConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException; 
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumWaitUtils
{
	public static boolean resultElementStatus;

	public static WebElement fluentWait(WebElement element, WebDriver tempDriver, String description) 
	{
		WebElement tempWebElement = null;

		try 
		{
			if (element == null || tempDriver == null) 
			{	
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}

			FluentWait<WebDriver> wait = new FluentWait<>(tempDriver)
					.withTimeout(Duration.ofSeconds(WebDriverWaitConstants.FLUENT_WAIT_TIME_IN_SECONDS))
					.pollingEvery(Duration.ofSeconds(WebDriverWaitConstants.POLLING_TIME_IN_SECONDS))
					.ignoring(NoSuchElementException.class);

			tempWebElement = wait.until(driver -> element);
			LoggerUtil.INFO("Fluent wait for " + element + " was successful.");
		}
		catch (TimeoutException timeoutException) 
		{		
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}

		return tempWebElement;

	}

	public static WebElement waitForElementVisible(WebElement element, WebDriver tempDriver, String description) 
	{
		if (element == null || tempDriver == null) 
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			wait.until(ExpectedConditions.visibilityOf(element));
			LoggerUtil.INFO("Wait for " + element + " visibility was successful.");
			ExtentTestManager.getTest().log(Status.PASS, description);
		} 
		catch (TimeoutException timeoutException) 
		{		
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}

		return element;

	}

	public static WebElement waitForElementToBeClickable(WebElement element, WebDriver tempDriver, String description)
	{
		if (element == null || tempDriver == null) 
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			LoggerUtil.INFO("Wait for " + element + " to be clickable was successful.");
			ExtentTestManager.getTest().log(Status.PASS, description);
		} 
		catch (TimeoutException timeoutException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}

		return element;

	}

	/**    APPROACH #1
	 ** 1. We define a custom wait condition called customCondition that checks both the invisibility of the target element and the loader element.
	 ** 2. The ExpectedConditions.invisibilityOf(element) checks if the target element is invisible, and ExpectedConditions.invisibilityOfElementLocated(loaderLocator) 
	 **    checks if the loader element is invisible (replace "loaderLocator" with the actual locator of your loader element).
	 ** 3. The custom condition returns true only when both the target element and the loader element are invisible simultaneously.   
	 ** 4. We use this custom condition in the WebDriverWait to wait for the desired state.
	 */
	public static boolean waitForElementNotVisible(WebElement element, WebDriver tempDriver, By loaderLocator) 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_NOTVISIBLE_TIMEOUT_IN_SECONDS));
			ExpectedCondition<Boolean> customCondition = driver ->
			{
				try 
				{
					// Check if the target element is invisible
					boolean elementInvisible = ExpectedConditions.invisibilityOf(element).apply(driver);

					// Check if the loader element is invisible (replace "loaderLocator" with the actual locator)
					boolean loaderInvisible = ExpectedConditions.invisibilityOfElementLocated(loaderLocator).apply(driver);

					if( elementInvisible && loaderInvisible )
					{
						// Return true if both conditions are met
						resultElementStatus = true;						
						return resultElementStatus;
					}
					else
					{
						// Return false if both conditions are met
						boolean resultElementStatus = false;						
						return resultElementStatus;
					}
				}
				catch (Exception exception) 
				{
					boolean resultElementStatus = false;						
					return resultElementStatus;
				}
			};

			wait.until(customCondition);
		} 
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Wait for " + element + " in-visibility. " , webdriverException.getMessage(),tempDriver);
		}

		return resultElementStatus;

	}

	public static void waitForElementNotVisible(WebElement element, WebDriver tempDriver, By loaderLocator, String description)
	{
		if( waitForElementNotVisible(element, tempDriver, loaderLocator) == false )
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}
	}

	/** APPROACH #2 **/
	public static void waitForElementNotVisible(WebElement element, WebDriver tempDriver, String description) 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(tempDriver,Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_NOTVISIBLE_TIMEOUT_IN_SECONDS));		
			wait.until(ExpectedConditions.invisibilityOf(element));
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void waitForPageToLoad(WebDriver tempDriver, String description) 
	{
		if (tempDriver == null)
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.PAGE_LOAD_TIMEOUT_IN_SECONDS));
			wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			ExtentTestManager.getTest().log(Status.PASS, description + " - Page loaded successfully.");
		} 
		catch (TimeoutException timeoutException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}
	}

	public static void waitForPageLoadAndDOMRender(WebDriver tempDriver, String description) 
	{
		if (tempDriver == null)
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try
		{
			waitForPageToLoad(tempDriver, description);
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.DOM_RENDER_TIMEOUT_IN_SECONDS));
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete' && jQuery.active === 0"));
			ExtentTestManager.getTest().log(Status.PASS, description + " - Page DOM rendered successfully.");
		} 
		catch (TimeoutException timeoutException)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}
	}

	public static List<WebElement> waitForElementsVisible(List<WebElement> elements, WebDriver tempDriver, String description) 
	{
		if (elements == null || tempDriver == null) 
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			LoggerUtil.INFO("Wait for elements " + elements + " to be visible was successful.");
		} 
		catch (TimeoutException timeoutException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}

		return elements;

	}
}