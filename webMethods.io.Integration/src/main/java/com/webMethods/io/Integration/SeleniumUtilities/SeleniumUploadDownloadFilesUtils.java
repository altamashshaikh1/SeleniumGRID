/**
 *  This class consists of all file upload and download related operation implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling; 

public class SeleniumUploadDownloadFilesUtils
{	
	public static void uploadFileAutolt(WebDriver tempDriver, String filePath, String description) 
	{
		try
		{
			Runtime.getRuntime().exec(filePath);
			ExtentTestManager.getTest().log(Status.PASS, description);		
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,exception.getMessage(),tempDriver);
		}
	}

	public static void uploadFile(WebDriver tempDriver, String filePath, String description) 
	{
		try
		{
			StringSelection ss = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			//Imitate mouse events like ENTER, CTRL+C, CTRL+V
			Robot robot = new Robot();
			robot.delay(250);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(50);
			robot.keyRelease(KeyEvent.VK_ENTER);
			ExtentTestManager.getTest().log(Status.PASS, description);			
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,exception.getMessage(),tempDriver);
		}
	}

	public static void uploadFileJS(WebDriver tempDriver,String fileName,WebElement element,String description)
	{
		try
		{
			((RemoteWebDriver) tempDriver).setFileDetector(new LocalFileDetector());
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor)tempDriver;
			String visibilityEnable = "arguments[0].style.visibility = 'visible';";
			javascriptExecutor.executeScript(visibilityEnable, element);
			element.sendKeys(FilePathConstants.TEST_INPUT_DATA_FOLDER_PATH+FilePathConstants.FILE_SEPARATOR+fileName);
			String visibilityDisable = "arguments[0].style.visibility = 'hidden';";
			javascriptExecutor.executeScript(visibilityDisable, element);
			ExtentTestManager.getTest().log(Status.PASS, description);	
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description,exception.getMessage(),tempDriver);
		}
	}
}