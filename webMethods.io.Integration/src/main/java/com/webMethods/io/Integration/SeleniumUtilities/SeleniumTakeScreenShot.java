/**
 *  This class consists of screen shot related operation implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import org.openqa.selenium.WebDriver;
import java.util.Base64; 
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status; 
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;

public class SeleniumTakeScreenShot 
{
	public static String createSnapShotFile(String directoryPath) throws IOException
	{
		File file = null;
		String ext;

		ext = "png";
		File dir = new File(directoryPath);
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(7), ext);
		file = new File(dir, "ScreenShot_"+name);
		file.createNewFile();

		return file.getAbsolutePath().toString();
	}

	public static String imageToBase64String(String filePath) 
	{
		String base64 = "";

		InputStream iSteamReader;
		byte[] imageBytes;

		try
		{
			iSteamReader = new FileInputStream(filePath); 
			imageBytes = IOUtils.toByteArray(iSteamReader); 
			base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		catch(Exception exception)
		{
			System.out.println("Exception caught while converting image to base 64 string. "+exception.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "Exception caught while converting image to base 64 string. "+exception.getMessage());
			// CHAT GPT TODO
		}

		return base64;

	}

	public static void takeScreenshot(WebDriver tempDriver)
	{
		try
		{
			String tempPath = createSnapShotFile(FilePathConstants.TEST_FAILED_SCREEN_SHOTS_FOLDER_PATH);
			//tempDriver = (RemoteWebDriver) new Augmenter().augment(tempDriver);	
			TakesScreenshot scrShot =((TakesScreenshot)tempDriver); 
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE); 
			File DestFile = new File(tempPath); 
			FileUtils.copyFile(SrcFile, DestFile);

			String imageBase64String = imageToBase64String(DestFile.getAbsolutePath());

			if(imageBase64String.isEmpty())
			{
				System.out.println("imageBase64String is null or empty");
				ExtentTestManager.getTest().log(Status.FAIL, "imageBase64String is null or empty");
				// CHAT GPT TODO
			}
			else
			{
				ExtentTestManager.getTest().info("Click on below button to expand screenshot: ",MediaEntityBuilder.createScreenCaptureFromBase64String(imageBase64String).build());
			}
		}
		catch(ScreenshotException screenshotexception)
		{
			System.out.println("ScreenshotException caught while taking/capturing screenshot. "+screenshotexception.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "ScreenshotException caught while taking/capturing screenshot. "+screenshotexception.getMessage());
			// CHAT GPT TODO
		}
		catch(IOException ioexception)
		{
			System.out.println("IOException caught while taking/capturing screenshot. "+ioexception.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "IOException caught while taking/capturing screenshot. "+ioexception.getMessage());
			// CHAT GPT TODO
		}
	}
}