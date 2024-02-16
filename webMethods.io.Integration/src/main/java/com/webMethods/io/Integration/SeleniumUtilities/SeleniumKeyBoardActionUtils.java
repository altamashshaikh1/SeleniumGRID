/**
 *  This class consists of all keyboard and mouse related operation implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.Constants.WebDriverWaitConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;

public class SeleniumKeyBoardActionUtils
{
	public static void dragDropBY(WebElement element, WebDriver tempDriver, String description, int xoff, int yoff) 
	{
		try
		{		
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			Actions actions = new Actions(tempDriver);	
			actions.dragAndDropBy(element, xoff, yoff).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void resizeElement(WebElement element, WebDriver tempDriver, String description, int xoff, int yoff) 
	{
		try
		{       
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			Actions actions = new Actions(tempDriver);    
			actions.clickAndHold(element).moveByOffset(xoff, yoff).release().build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void mousehover(WebElement element, WebDriver tempDriver, String description)
	{
		try
		{     	
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			Actions actions = new Actions(tempDriver);    
			actions.moveToElement(element).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void mouseHoverAndClick(WebElement element, WebDriver tempDriver, String description) 
	{
		try
		{   
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			Actions actions = new Actions(tempDriver);    
			actions.moveToElement(element).click(element).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);	
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void rightClick(WebElement element, WebDriver tempDriver, String description) 
	{
		try
		{     	
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			Actions actions = new Actions(tempDriver);    
			actions.contextClick(element).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);

		}
	}

	public static void dragDrop(WebElement sourceElement, WebElement destinationElement, WebDriver tempDriver, String description) 
	{
		try
		{    
			WebDriverWait webDriverWaitSourceWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWaitSourceWait.until(ExpectedConditions.visibilityOf(sourceElement));
			WebDriverWait webDriverWaitDestination = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWaitDestination.until(ExpectedConditions.visibilityOf(destinationElement));
			Actions actions = new Actions(tempDriver);    
			actions.dragAndDrop(sourceElement, destinationElement).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);

		}
	}

	public static void keyboardShiftCombination(WebDriver tempDriver,String key,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);			
			actions.keyDown(Keys.SHIFT).sendKeys(key).keyUp(Keys.SHIFT).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{

			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyBoardSendKeys(WebDriver tempDriver,String data, String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.sendKeys(data).build().perform();			
			ExtentTestManager.getTest().log(Status.PASS, description+". Enter "+data+" in text box");
		}
		catch (WebDriverException webdriverException) 
		{			
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyBoardTabAction(WebDriver tempDriver, String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.sendKeys(Keys.TAB).build().perform();		
			ExtentTestManager.getTest().log(Status.PASS,description+". Keyboard tab pressed");
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardActions(WebDriver tempDriver,Keys key,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.sendKeys(key).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardBackSpaceActions(WebDriver tempDriver,Keys key,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);

			for (int i=0;i<=50;i++)
			{
				actions.sendKeys(key).build().perform();	 
			}
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardSingleKeyAction(WebDriver tempDriver, Keys key, String description) 
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.keyDown(key).keyUp(key).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardCombinationKeysAction(WebDriver tempDriver,Keys key,String keyString,String description) 
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.keyDown(key).sendKeys(keyString).keyUp(key).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardCombinationAltKey(WebDriver tempDriver,Keys key,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.keyDown(Keys.ALT).keyDown(key).keyUp(key).keyUp(Keys.ALT);
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardActionsData(WebDriver tempDriver,String keyString,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.sendKeys(keyString).build().perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardBackSpaceAndSendKeys(WebElement element,WebDriver tempDriver,String stringData,String description)
	{
		try
		{
			element.sendKeys(Keys.BACK_SPACE);
			element.sendKeys(stringData);
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardCombinationControlAndDelete(WebElement element,WebDriver tempDriver,String key,String description)
	{
		try
		{
			String controlKeyData = Keys.chord(Keys.CONTROL,"a");			
			element.sendKeys(controlKeyData);
			element.sendKeys(Keys.DELETE);
			element.sendKeys(key);
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardCombinationControlAndKeys(WebDriver tempDriver,String key,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver); 
			actions.keyDown(Keys.CONTROL).sendKeys(key).keyUp(Keys.CONTROL).perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardCombinationControlAndKeys(WebDriver tempDriver,Keys key,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.keyDown(Keys.CONTROL).keyDown(key).keyUp(key).keyUp(Keys.CONTROL).perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void keyboardCombinationControlAndLeftMouse(WebDriver tempDriver,WebElement elementOne,WebElement elementTwo,String description)
	{
		try
		{
			Actions actions = new Actions(tempDriver);
			actions.keyDown(Keys.CONTROL).perform();
			elementOne.click();
			elementTwo.click();
			actions.keyUp(Keys.CONTROL).perform();
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void doubleClick(WebElement element, WebDriver tempDriver, String description) 
	{
		try
		{     	
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			Actions actions = new Actions(tempDriver);    
			actions.doubleClick(element).build().perform();  
			ExtentTestManager.getTest().log(Status.PASS, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}
}