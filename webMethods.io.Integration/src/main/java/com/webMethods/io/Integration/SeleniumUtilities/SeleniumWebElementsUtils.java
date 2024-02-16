/**
 *  This class consists of all action and operations that are carried out during executions.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.Constants.WebDriverWaitConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import com.webMethods.io.Integration.TestNGAssertions.SoftAssertWrapper;

public class SeleniumWebElementsUtils
{
	public static void elementvisibleWithVerification(WebElement element, WebDriver tempDriver, SoftAssertWrapper tempSoftAssertWrapper, String description)   
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if(element.isDisplayed()&&element!=null)
			{  
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
			else
			{
				CommonFailureHandling.captureFailureAndContinueTest(tempDriver,description, tempSoftAssertWrapper);
			}
		} 
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndContinueTest(tempDriver,description,webdriverException.getMessage(), tempSoftAssertWrapper);
		}
	}

	public static void elementvisible(WebElement element, WebDriver tempDriver, String description) 
	{
		if (element == null || tempDriver == null)
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed())
			{
				ExtentTestManager.getTest().log(Status.PASS, description);
			} 
			else 
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}
		} 
		catch (TimeoutException timeoutException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,timeoutException.getMessage(),tempDriver);
		}
	}

	public static void elementsvisible(List<WebElement> element, WebDriver tempDriver, String description)  
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));

			for(int allElements=0;allElements<element.size();allElements++)
			{
				if(element.get(allElements).isDisplayed())
				{  
					ExtentTestManager.getTest().log(Status.PASS	, description);
				}
				else
				{
					CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
				}
			} 
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void elementnotvisible(WebElement element, WebDriver tempDriver, String description)
	{
		if (element == null || tempDriver == null)
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			if (element.isDisplayed()) 
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}
		} 
		catch (WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, webdriverException.getMessage(),tempDriver);
		}
	}

	public static void printAllTextValueOfElements(List<WebElement> elements, WebDriver tempDriver, String description)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));

			int elementsCount = elements.size();

			if(elementsCount==0)
			{
				ExtentTestManager.getTest().log(Status.INFO, "No recipes added under All Recipes");
			}
			else
			{
				for(int index=1 ; index==elementsCount ; index++)
				{
					ExtentTestManager.getTest().log(Status.INFO, "Recipe "+elements.get(index).getText()+" is visible and available under All Recipes");
				}
			}
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void highlighElement(WebElement element, WebDriver tempDriver, SoftAssertWrapper tempSoftAssertWrapper, String description)
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor javascriptexecutor = (JavascriptExecutor) tempDriver;
			javascriptexecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
			ExtentTestManager.getTest().log(Status.PASS	, description);
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndContinueTest(tempDriver,description,webdriverException.getMessage(), tempSoftAssertWrapper);
		}
	}

	public static void highlightElement(WebElement element, WebDriver tempDriver, String description) 
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor javascriptexecutor = (JavascriptExecutor) tempDriver;
			javascriptexecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
			ExtentTestManager.getTest().log(Status.PASS	, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void inputbox(WebElement element, String inputText, WebDriver tempDriver, String description)
	{
		if (element == null || tempDriver == null)
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

			if (element.isDisplayed() && element.isEnabled())
			{
				if (inputText == null) 
				{

					CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
				}
				else 
				{
					element.sendKeys(inputText);
					ExtentTestManager.getTest().log(Status.PASS, description);
				}
			} 
			else 
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}
		} 
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void click(WebElement element, WebDriver tempDriver, String description) 
	{
		if (element == null || tempDriver == null) 
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

			if (element.isDisplayed() && element.isEnabled())
			{
				element.click();
				ExtentTestManager.getTest().log(Status.PASS, description);
			} 
			else 
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}
		}
		catch (WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, webdriverException.getMessage(),tempDriver);
		}
	}

	public static void selectAllCheckboxOption(List<WebElement> element, WebDriver tempDriver, String description) 
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));

			for(int i=0; i<=element.size();i++)
			{
				element.get(i).click();
			}

			ExtentTestManager.getTest().log(Status.PASS	, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void selectValueDropDown(WebElement element, String dropDownValueText, WebDriver tempDriver, String description) 
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			Select ddValue = new Select(element);
			ddValue.selectByVisibleText(dropDownValueText);
			ExtentTestManager.getTest().log(Status.PASS	, description);
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void clear(WebElement element, WebDriver tempDriver, String description)
	{
		if (element == null || tempDriver == null) 
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed() && element.isEnabled()) 
			{
				element.clear();
				ExtentTestManager.getTest().log(Status.PASS, description);
			} 
			else 
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}
		} 
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void clearAndAddText(WebElement element, WebDriver tempDriver, String data, String description) 
	{
		if (element == null || tempDriver == null) 
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}

		try 
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed() && element.isEnabled()) 
			{
				element.clear();

				if (data != null) 
				{
					element.sendKeys(data);
				}

				ExtentTestManager.getTest().log(Status.PASS, description);
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
			}
		} 
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, webdriverException.getMessage(),tempDriver);
		}
	}

	public static String returnClipBoardData(String description,WebDriver tempDriver)
	{
		Clipboard clipBoardData = null;

		String data = null;

		try 
		{
			clipBoardData = Toolkit.getDefaultToolkit().getSystemClipboard();

			if (clipBoardData.isDataFlavorAvailable(DataFlavor.stringFlavor)) 
			{
				data = (String) clipBoardData.getData(DataFlavor.stringFlavor);
				ExtentTestManager.getTest().log(Status.PASS, description);
			} 
			else 
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description, tempDriver);
			}   
		} 
		catch (UnsupportedFlavorException unsupportedFlavorException)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,unsupportedFlavorException.getMessage(),tempDriver);
		} 
		catch (IOException | HeadlessException exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,exception.getMessage(),tempDriver);
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,exception.getMessage(),tempDriver);
		}

		return data;

	}

	public static void acceptAlertMessage(WebDriver tempDriver, String description) 
	{
		try
		{
			tempDriver.switchTo().alert().accept();
			ExtentTestManager.getTest().log(Status.PASS	, "Scroll page till element is visible");
		}
		catch (WebDriverException webdriverException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}
	}

	public static void textContains(WebElement element, String expectedString, WebDriver tempDriver, String description, SoftAssertWrapper tempSoftAssertWrapper)
	{
		try
		{   
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			String actualString = element.getText();

			if (actualString.equalsIgnoreCase(expectedString))
			{			  
				ExtentTestManager.getTest().log(Status.PASS, "Actual text: "+"<span style='font-weight:bold;'>"+actualString+"</span>" +" contains expected text "+"<span style='font-weight:bold;'>"+expectedString+"</span>");
			}
			else 
			{
				CommonFailureHandling.captureFailureAndContinueTest(tempDriver,description+"Actual text: "+"<span style='font-weight:bold;'>"+actualString+"</span>" +" not contains expected text "+"<span style='font-weight:bold;'>"+expectedString+"</span>", tempSoftAssertWrapper);
			}
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndContinueTest(tempDriver,description,webdriverException.getMessage(), tempSoftAssertWrapper);
		}
	}

	public static void textContainsPrTest(WebElement element, String expectedString, WebDriver tempDriver, String description, SoftAssertWrapper tempSoftAssertWrapper)
	{
		try
		{   
			ExtentTestManager.getTest().pass(description);			 
		}
		catch(WebDriverException webdriverException)
		{
			ExtentTestManager.getTest().fail(description);			
		}
	}

	public static void textContains(WebElement element, WebDriver tempDriver, String description, SoftAssertWrapper tempSoftAssertWrapper)
	{
		try
		{ 	
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if (element.getText()==null)
			{

				CommonFailureHandling.captureFailureAndContinueTest(tempDriver,description+"Text is null.", tempSoftAssertWrapper);
			}
			else
			{
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndContinueTest(tempDriver,description,webdriverException.getMessage(), tempSoftAssertWrapper);
		}
	}

	public static void assertAttributeValueNotNull(WebElement element, String attributeValue, WebDriver tempDriver,String description, SoftAssertWrapper tempSoftAssertWrapper)
	{
		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if(element.getAttribute(attributeValue).toString()==null)
			{

				CommonFailureHandling.captureFailureAndContinueTest(tempDriver,description+"Value for attribute is null.", tempSoftAssertWrapper);
			}
			else
			{
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndContinueTest(tempDriver,description,webdriverException.getMessage(), tempSoftAssertWrapper);
		}
	}

	public static String fetchAttributeValue(WebElement element, String attributeName, WebDriver tempDriver,String description, SoftAssertWrapper tempSoftAssertWrapper)
	{
		String value = null;

		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));

			if(element.getAttribute(attributeName).toString()==null)
			{
				CommonFailureHandling.captureFailureAndContinueTest(tempDriver,description+"Attibute value is null.", tempSoftAssertWrapper);
			}
			else
			{
				value = element.getAttribute(attributeName).toString();
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndContinueTest(tempDriver,description,webdriverException.getMessage(), tempSoftAssertWrapper);
		}

		return value;

	}

	public static String fetchAtributeValueFromElements(List<WebElement> element,String attributeName)
	{
		String tempElement = null;

		tempElement = element.get(0).getAttribute(attributeName);	

		return tempElement;
	}

	public static void assertAttributeValues(String sourceAttributeValue,String destinationAttributeValue,String description,WebDriver tempDriver)
	{
		if(!sourceAttributeValue.isEmpty()&&!!destinationAttributeValue.isEmpty())
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}
		else
		{
			if(sourceAttributeValue.equalsIgnoreCase(destinationAttributeValue))
			{
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
			else
			{
				ExtentTestManager.getTest().log(Status.FAIL	, description);
			}
		}
	}

	public static void assertAttributeValuesInverse(String sourceAttributeValue,String destinationAttributeValue,String description,WebDriver tempDriver)
	{
		if(!sourceAttributeValue.isEmpty()&&!!destinationAttributeValue.isEmpty())
		{
			CommonFailureHandling.captureFailureAndTerminateTest(description,tempDriver);
		}
		else
		{
			if(sourceAttributeValue.equalsIgnoreCase(destinationAttributeValue))
			{
				ExtentTestManager.getTest().log(Status.FAIL	, description);
			}
			else
			{
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
		}
	}

	public static String fetchElementText(WebElement element,WebDriver tempDriver,String description)
	{
		String tempString = null;

		try
		{
			WebDriverWait webDriverWait = new WebDriverWait(tempDriver, Duration.ofSeconds(WebDriverWaitConstants.WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
			tempString = element.getText();
			ExtentTestManager.getTest().log(Status.PASS	, description);
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,webdriverException.getMessage(),tempDriver);
		}

		return tempString;

	}

	public static void pagetitle(String expectedPageTitle, WebDriver tempDriver, String description, SoftAssertWrapper tempSoftAssertWrapper)
	{
		String actualPageTitle = tempDriver.getTitle();

		if(actualPageTitle!=null)
		{
			if(actualPageTitle.equals(expectedPageTitle))
			{
				ExtentTestManager.getTest().log(Status.PASS	, description);
			}
			else
			{
				CommonFailureHandling.captureFailureAndContinueTest(tempDriver,"Title "+"<span style='font-weight:bold;'>"+actualPageTitle+"</span>"+" not Matched with "+"<span style='font-weight:bold;'>"+expectedPageTitle+"</span>", tempSoftAssertWrapper);
			}
		}
		else
		{
			CommonFailureHandling.captureFailureAndContinueTest(tempDriver,"<span style='font-weight:bold;'>Title is blank.</span>", tempSoftAssertWrapper);
		}
	}

	public static void openconnector(WebDriver tempDriver,String ServiceName)
	{
		try
		{
			WebElement ele = tempDriver.findElement(By.xpath("//div[@data-eventmap='metadata-clickedActivity- ("+ServiceName+")']"));
			Actions action = new Actions(tempDriver);
			action.doubleClick(ele).build().perform();
			ExtentTestManager.getTest().pass("Open messaging connector");
		}
		catch(WebDriverException webdriverException)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Double click on connector : ",webdriverException.getMessage(),tempDriver);
		}
	}
}