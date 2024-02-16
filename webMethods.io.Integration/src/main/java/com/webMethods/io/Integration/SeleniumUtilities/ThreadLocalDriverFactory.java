/**
 *  This class consists of ThreadLocal<RemoteWebDriver> implementation handling, fetch webdriver instance, open browser , reset webdriver and close.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.Constants.TestDataConstants;
import com.webMethods.io.Integration.Constants.WebDriverWaitConstants;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import org.openqa.selenium.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadLocalDriverFactory
{
	/** HELPER METHOD TO RETRIVE BROWSER DESIRED CAPABILITIES **/
	public static DesiredCapabilities getCapabilities()
	{
		DesiredCapabilities capabilities = null;

		switch (MavenArgumentConstants.BROWSER_MAVEN_ARGUMENT) 
		{
		case "chrome":
			capabilities = getChromeCapabilities();
			break;

		case "chromeheadless":
			capabilities = getChromeHeadlessCapabilities();
			break;

		case "firefox":
			capabilities = getFirefoxCapabilities();	
			break;

		case "firefoxheadless":
			capabilities = getFirefoxHeadlessCapabilities();
			break;

		case "edge":
			capabilities = getEdgeCapabilities();	
			break;

		case "edgeheadless":
			capabilities = getEdgeHeadlessCapabilities();
			break;

		case "opera":
			capabilities = getOperaCapabilities();
			break;			

		case "operaheadless":
			capabilities = getOperaHeadlessCapabilities();
			break;

		case "safari":
			capabilities = getSafariCapabilities();
			break;

		case "internetexplorer":
			capabilities = getInternetExplorerCapabilities();
			break;
		}

		return capabilities;

	}

	/** HELPER METHOD FOR CHROME DESIRED CAPABILITIES**/
	public static DesiredCapabilities getChromeCapabilities() 
	{
		Map<String, Object> prefs;
		DesiredCapabilities capabilities = null;
		ChromeOptions chromeOptions;			
		Logger logger = Logger.getLogger("org.openqa.selenium");

		try
		{
			logger.setLevel(Level.OFF);
			prefs = new HashMap<String, Object>();
			prefs.put("profile.managed_default_content_settings_images",2);
			capabilities = new DesiredCapabilities();
			chromeOptions = new ChromeOptions();			
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--window-size="+TestDataConstants.WIDTH+","+TestDataConstants.HEIGHT+"");
			chromeOptions.addArguments("--blink-settings=imagesEnabled=false");			 			
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--disable-logging");
			chromeOptions.addArguments("--disable-infobars"); 
			chromeOptions.addArguments("--disable-gpu");	
			chromeOptions.addArguments("--disable-notifications");		
			chromeOptions.addArguments("enable-automation");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.LINUX);	

		}
		catch(Exception exception)
		{			
			CommonExceptionHandling.captureExceptionAndTerminateTest("While fetching browser capabilities.",exception.getMessage());
		}

		return capabilities;

	}

	/** HELPER METHOD FOR CHROMEHEADLESS DESIRED CAPABILITIES**/
	public static DesiredCapabilities getChromeHeadlessCapabilities() 
	{
		Map<String, Object> prefs;
		DesiredCapabilities capabilities = null;
		ChromeOptions chromeOptions;			
		Logger logger = Logger.getLogger("org.openqa.selenium");

		try
		{
			logger.setLevel(Level.OFF);
			prefs = new HashMap<String, Object>();
			prefs.put("profile.managed_default_content_settings_images",2);
			capabilities = new DesiredCapabilities();
			chromeOptions = new ChromeOptions();			
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--window-size="+TestDataConstants.WIDTH+","+TestDataConstants.HEIGHT+"");
			chromeOptions.addArguments("--blink-settings=imagesEnabled=false");			 			
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--disable-logging");
			chromeOptions.addArguments("--disable-infobars"); 
			chromeOptions.addArguments("--disable-gpu");	
			chromeOptions.addArguments("--disable-notifications");		
			chromeOptions.addArguments("enable-automation");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.LINUX);	

		}
		catch(Exception exception)
		{			
			CommonExceptionHandling.captureExceptionAndTerminateTest("While fetching browser capabilities.",exception.getMessage());
		}

		return capabilities;

	}

	/** HELPER METHOD FOR FIREFOX DESIRED CAPABILITIES**/
	public static DesiredCapabilities getFirefoxCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR FIREFOXHEADLESS DESIRED CAPABILITIES**/
	public static DesiredCapabilities getFirefoxHeadlessCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR EDGE DESIRED CAPABILITIES**/
	public static DesiredCapabilities getEdgeCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR EDGEHEADLESS DESIRED CAPABILITIES**/
	public static DesiredCapabilities getEdgeHeadlessCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR OPERA DESIRED CAPABILITIES**/
	public static DesiredCapabilities getOperaCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR OPERAHEADLESS DESIRED CAPABILITIES**/
	public static DesiredCapabilities getOperaHeadlessCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR SAFARI DESIRED CAPABILITIES**/
	public static DesiredCapabilities getSafariCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD FOR IE DESIRED CAPABILITIES**/
	public static DesiredCapabilities getInternetExplorerCapabilities() 
	{
		DesiredCapabilities capabilities = null;

		CommonFailureHandling.captureFailureAndTerminateSuite("Only chrome browser is available as docker instance. Automation execution will terminate.");

		return capabilities;
	}

	/** HELPER METHOD TO SET NEW THREAD SAFE SESSION **/
	public static void setThreadSafeSession(ThreadLocal<RemoteWebDriver> tempWebDriverThreadLocal,String gridHubURL,DesiredCapabilities tempCapabilities)   
	{
		try 
		{			
			tempWebDriverThreadLocal.set(new RemoteWebDriver(new URL(gridHubURL),tempCapabilities));
		} 
		catch (Exception exception) 
		{			
			CommonExceptionHandling.captureExceptionAndTerminateTest("While initializing thread safe session.",exception.getMessage());
		}
	}

	/** HELPER METHOD TO GET WEBDRIVER FROM THREAD SAFE SESSIONS **/
	public static WebDriver getDriver(ThreadLocal<RemoteWebDriver> tempWebDriverThreadLocal)
	{
		WebDriver tempDriver = tempWebDriverThreadLocal.get();

		((RemoteWebDriver) tempDriver).setFileDetector(new LocalFileDetector());

		return tempDriver;
	}

	/** HELPER METHOD TO OPEN APPLICATION IN BROWSER **/
	public static void openApplication(WebDriver tempWebDriver, String appURL)
	{
		try 
		{
			tempWebDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WebDriverWaitConstants.PAGE_LOAD_TIMEOUT_IN_SECONDS));
			tempWebDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(WebDriverWaitConstants.SCRIPT_TIMEOUT_IN_SECONDS)); 	
			tempWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverWaitConstants.IMPLICIT_WAIT_TIME_IN_SECONDS)); 
			tempWebDriver.get(appURL);
			tempWebDriver.manage().window().setSize(TestDataConstants.DIMENSIONS);
			tempWebDriver.manage().window().maximize();
		} 
		catch (TimeoutException timeoutException) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Timeout while opening the application URL.", timeoutException.getMessage());
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("An error occurred while opening the application URL.", exception.getMessage());
		} 
	}

	/** HELPER METHOD TO CLOSE BROWSER INSTANCE **/
	public static void closeWebBrowser(WebDriver tempWebDriver)
	{
		tempWebDriver.close();
	}

	/** HELPER METHOD TO QUIT WEBDRIVER INSTANCE **/
	public static void quitDriver(WebDriver tempWebDriver)
	{
		tempWebDriver.quit();
	}

	/** HELPER METHOD TO CLOSE AND TERMINATE THREAD SAFE SESSION **/
	public static void closeThreadSafeSession(ThreadLocal<RemoteWebDriver> tempWebDriverThreadLocal)
	{
		tempWebDriverThreadLocal.remove();
	}
}
